/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package de.iisys.liferay.portlet.camundaACM.caseOverview.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;

import de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException;
import de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance;
import de.iisys.liferay.portlet.camundaACM.caseOverview.service.base.CaseInstanceLocalServiceBaseImpl;

/**
 * The implementation of the case instance local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link de.iisys.liferay.portlet.camundaACM.caseOverview.service.CaseInstanceLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Christian Ochsenkühn
 * @see de.iisys.liferay.portlet.camundaACM.caseOverview.service.base.CaseInstanceLocalServiceBaseImpl
 * @see de.iisys.liferay.portlet.camundaACM.caseOverview.service.CaseInstanceLocalServiceUtil
 */
public class CaseInstanceLocalServiceImpl
	extends CaseInstanceLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link de.iisys.liferay.portlet.camundaACM.caseOverview.service.CaseInstanceLocalServiceUtil} to access the case instance local service.
	 */
	
	public CaseInstance addCaseInstance(String caseInstanceId, String caseDefinitionKey, ServiceContext sc, long userId, long groupId) throws SystemException, PortalException {
		return addCaseInstance(caseInstanceId, caseDefinitionKey, sc, userId, groupId, sc.getCreateDate(new Date()));
	}

	public CaseInstance addCaseInstance(String caseInstanceId, String caseDefinitionKey, ServiceContext sc, long userId, long groupId, Date createTime) throws SystemException, PortalException {
		long liferyCaseInstanceId = counterLocalService.increment(CaseInstance.class.getName());
		
		try {
			CaseInstance ci = caseInstancePersistence.findByCaseInstanceId(caseInstanceId);
			System.out.println("Already exists: instance with caseInstanceId="+caseInstanceId);
			return ci;
		} catch (NoSuchCaseInstanceException e1) {
			System.out.println("Case instance does not exist yet. Trying to create it.");
		}
		
		
		CaseInstance caseInst = caseInstancePersistence.create(liferyCaseInstanceId);
		caseInst.setCaseInstanceId(caseInstanceId);
		caseInst.setCaseDefinitionKey(caseDefinitionKey);
		
		User user = userPersistence.findByPrimaryKey(userId);
		caseInst.setUserId(user.getUserId());
		caseInst.setCompanyId(user.getCompanyId());

		caseInst.setGroupId(groupId);

		caseInst.setCreateDate(createTime);
		caseInst.setModifiedDate(createTime);
		
		
		super.addCaseInstance(caseInst);
		try {
			SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");
			String title = "Case Instance of "+caseInst.getCaseDefinitionKey()+" ("+simpleFormat.format(createTime)+")";
			simpleFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
			String title_DE = "Instanz des Falls "+caseInst.getCaseDefinitionKey()+" ("+simpleFormat.format(createTime)+")";
			String url = null;
			
			AssetEntry assetEntry = assetEntryLocalService.updateEntry(user.getUserId(), groupId,
					createTime, createTime,
					CaseInstance.class.getName(), caseInst.getPrimaryKey(), null, 0,
					sc.getAssetCategoryIds(), sc.getAssetTagNames(), true, null, null, null,
					ContentTypes.TEXT_XML_UTF8, title, caseInst.getCaseDefinitionKey(),
					"", url, null, 0, 0, null, false);
			
			assetEntry.setTitle(title_DE, LocaleUtil.GERMANY);
			
			assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
                    sc.getAssetLinkEntryIds(),
                    AssetLinkConstants.TYPE_RELATED);
			
			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(CaseInstance.class);
			indexer.reindex(caseInst);
		} catch (PortalException e) {
			e.printStackTrace();
		}
		
		return caseInst;
	}
	
	public CaseInstance updateAssetsForCaseInstance(long casePK, ServiceContext sc) throws PortalException, SystemException {
		CaseInstance ci = caseInstancePersistence.fetchByPrimaryKey(casePK);
		
		SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");
		String title = "Case Instance of "+ci.getCaseDefinitionKey()+" ("+simpleFormat.format(ci.getCreateDate())+")";
		simpleFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		String title_DE = "Instanz des Falls "+ci.getCaseDefinitionKey()+" ("+simpleFormat.format(ci.getCreateDate())+")";
		
		Date modifiedDate = sc.getModifiedDate(new Date());
	
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(ci.getUserId(), ci.getGroupId(),
				ci.getCreateDate(), modifiedDate,
				CaseInstance.class.getName(), ci.getPrimaryKey(), null, 0,
				sc.getAssetCategoryIds(), sc.getAssetTagNames(), true, null, null, null,
				ContentTypes.TEXT_XML_UTF8, title, ci.getCaseDefinitionKey(),
				"", null, null, 0, 0, null, false);
		
		assetEntry.setTitle(title_DE, LocaleUtil.GERMANY);
		
		assetLinkLocalService.updateLinks(sc.getUserId(), assetEntry.getEntryId(),
		                sc.getAssetLinkEntryIds(),
		                AssetLinkConstants.TYPE_RELATED);
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(CaseInstance.class);
		indexer.reindex(ci);
		
		return ci;
	}
	
	
	public CaseInstance findCaseInstanceById(String caseInstanceId) throws NoSuchCaseInstanceException, SystemException {
		return caseInstancePersistence.findByCaseInstanceId(caseInstanceId);
	}
	
	public List<CaseInstance> findCaseInstancesByDefinitionKey(String caseDefinitionKey) throws SystemException {
		return caseInstancePersistence.findByCaseDefinitionKey(caseDefinitionKey);
	}
	public List<CaseInstance> findCaseInstancesByDefinitionKey(String caseDefinitionKey, int start, int end) throws SystemException {
		return caseInstancePersistence.findByCaseDefinitionKey(caseDefinitionKey, start, end);
	}
	
	public int getInstancesCountByDefinitionKey(String caseDefinitionKey) throws SystemException {
		return caseInstancePersistence.countByCaseDefinitionKey(caseDefinitionKey);
	}
	
	public void deleteAllCaseInstances() throws SystemException {
		caseInstancePersistence.removeAll();
	}
}