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

package de.iisys.liferay.portlet.camundaACM.caseOverview.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link de.iisys.liferay.portlet.camundaACM.caseOverview.service.http.CaseInstanceServiceSoap}.
 *
 * @author Christian OchsenkÃ¼hn
 * @see de.iisys.liferay.portlet.camundaACM.caseOverview.service.http.CaseInstanceServiceSoap
 * @generated
 */
public class CaseInstanceSoap implements Serializable {
	public static CaseInstanceSoap toSoapModel(CaseInstance model) {
		CaseInstanceSoap soapModel = new CaseInstanceSoap();

		soapModel.setLiferayCaseInstanceId(model.getLiferayCaseInstanceId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCaseInstanceId(model.getCaseInstanceId());
		soapModel.setCaseDefinitionKey(model.getCaseDefinitionKey());

		return soapModel;
	}

	public static CaseInstanceSoap[] toSoapModels(CaseInstance[] models) {
		CaseInstanceSoap[] soapModels = new CaseInstanceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CaseInstanceSoap[][] toSoapModels(CaseInstance[][] models) {
		CaseInstanceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CaseInstanceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CaseInstanceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CaseInstanceSoap[] toSoapModels(List<CaseInstance> models) {
		List<CaseInstanceSoap> soapModels = new ArrayList<CaseInstanceSoap>(models.size());

		for (CaseInstance model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CaseInstanceSoap[soapModels.size()]);
	}

	public CaseInstanceSoap() {
	}

	public long getPrimaryKey() {
		return _liferayCaseInstanceId;
	}

	public void setPrimaryKey(long pk) {
		setLiferayCaseInstanceId(pk);
	}

	public long getLiferayCaseInstanceId() {
		return _liferayCaseInstanceId;
	}

	public void setLiferayCaseInstanceId(long liferayCaseInstanceId) {
		_liferayCaseInstanceId = liferayCaseInstanceId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getCaseInstanceId() {
		return _caseInstanceId;
	}

	public void setCaseInstanceId(String caseInstanceId) {
		_caseInstanceId = caseInstanceId;
	}

	public String getCaseDefinitionKey() {
		return _caseDefinitionKey;
	}

	public void setCaseDefinitionKey(String caseDefinitionKey) {
		_caseDefinitionKey = caseDefinitionKey;
	}

	private long _liferayCaseInstanceId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _caseInstanceId;
	private String _caseDefinitionKey;
}