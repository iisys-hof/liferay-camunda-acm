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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

import de.iisys.liferay.portlet.camundaACM.caseOverview.service.base.CaseInstanceServiceBaseImpl;

/**
 * The implementation of the case instance remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link de.iisys.liferay.portlet.camundaACM.caseOverview.service.CaseInstanceService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Christian OchsenkÃ¼hn
 * @see de.iisys.liferay.portlet.camundaACM.caseOverview.service.base.CaseInstanceServiceBaseImpl
 * @see de.iisys.liferay.portlet.camundaACM.caseOverview.service.CaseInstanceServiceUtil
 */
public class CaseInstanceServiceImpl extends CaseInstanceServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link de.iisys.liferay.portlet.camundaACM.caseOverview.service.CaseInstanceServiceUtil} to access the case instance remote service.
	 */
	
	public WikiPage getWikiPage(long resourcePrimKey) throws PortalException, SystemException {
		// return WikiPageLocalServiceUtil.fetchWikiPage(pageId);
		return WikiPageLocalServiceUtil.getPage(resourcePrimKey);
	}
	
}