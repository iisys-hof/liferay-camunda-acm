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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import de.iisys.liferay.portlet.camundaACM.caseOverview.service.CaseInstanceLocalServiceUtil;
import de.iisys.liferay.portlet.camundaACM.caseOverview.service.ClpSerializer;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Christian Ochsenkühn
 */
public class CaseInstanceClp extends BaseModelImpl<CaseInstance>
	implements CaseInstance {
	public CaseInstanceClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return CaseInstance.class;
	}

	@Override
	public String getModelClassName() {
		return CaseInstance.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _liferayCaseInstanceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLiferayCaseInstanceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _liferayCaseInstanceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("liferayCaseInstanceId", getLiferayCaseInstanceId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("caseInstanceId", getCaseInstanceId());
		attributes.put("caseDefinitionKey", getCaseDefinitionKey());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long liferayCaseInstanceId = (Long)attributes.get(
				"liferayCaseInstanceId");

		if (liferayCaseInstanceId != null) {
			setLiferayCaseInstanceId(liferayCaseInstanceId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String caseInstanceId = (String)attributes.get("caseInstanceId");

		if (caseInstanceId != null) {
			setCaseInstanceId(caseInstanceId);
		}

		String caseDefinitionKey = (String)attributes.get("caseDefinitionKey");

		if (caseDefinitionKey != null) {
			setCaseDefinitionKey(caseDefinitionKey);
		}
	}

	@Override
	public long getLiferayCaseInstanceId() {
		return _liferayCaseInstanceId;
	}

	@Override
	public void setLiferayCaseInstanceId(long liferayCaseInstanceId) {
		_liferayCaseInstanceId = liferayCaseInstanceId;

		if (_caseInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _caseInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setLiferayCaseInstanceId",
						long.class);

				method.invoke(_caseInstanceRemoteModel, liferayCaseInstanceId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_caseInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _caseInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_caseInstanceRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_caseInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _caseInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_caseInstanceRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_caseInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _caseInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_caseInstanceRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_caseInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _caseInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_caseInstanceRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_caseInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _caseInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_caseInstanceRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCaseInstanceId() {
		return _caseInstanceId;
	}

	@Override
	public void setCaseInstanceId(String caseInstanceId) {
		_caseInstanceId = caseInstanceId;

		if (_caseInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _caseInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setCaseInstanceId",
						String.class);

				method.invoke(_caseInstanceRemoteModel, caseInstanceId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCaseDefinitionKey() {
		return _caseDefinitionKey;
	}

	@Override
	public void setCaseDefinitionKey(String caseDefinitionKey) {
		_caseDefinitionKey = caseDefinitionKey;

		if (_caseInstanceRemoteModel != null) {
			try {
				Class<?> clazz = _caseInstanceRemoteModel.getClass();

				Method method = clazz.getMethod("setCaseDefinitionKey",
						String.class);

				method.invoke(_caseInstanceRemoteModel, caseDefinitionKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getCaseInstanceRemoteModel() {
		return _caseInstanceRemoteModel;
	}

	public void setCaseInstanceRemoteModel(BaseModel<?> caseInstanceRemoteModel) {
		_caseInstanceRemoteModel = caseInstanceRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _caseInstanceRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_caseInstanceRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			CaseInstanceLocalServiceUtil.addCaseInstance(this);
		}
		else {
			CaseInstanceLocalServiceUtil.updateCaseInstance(this);
		}
	}

	@Override
	public CaseInstance toEscapedModel() {
		return (CaseInstance)ProxyUtil.newProxyInstance(CaseInstance.class.getClassLoader(),
			new Class[] { CaseInstance.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		CaseInstanceClp clone = new CaseInstanceClp();

		clone.setLiferayCaseInstanceId(getLiferayCaseInstanceId());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setCaseInstanceId(getCaseInstanceId());
		clone.setCaseDefinitionKey(getCaseDefinitionKey());

		return clone;
	}

	@Override
	public int compareTo(CaseInstance caseInstance) {
		int value = 0;

		value = getCaseInstanceId().compareTo(caseInstance.getCaseInstanceId());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CaseInstanceClp)) {
			return false;
		}

		CaseInstanceClp caseInstance = (CaseInstanceClp)obj;

		long primaryKey = caseInstance.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{liferayCaseInstanceId=");
		sb.append(getLiferayCaseInstanceId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", caseInstanceId=");
		sb.append(getCaseInstanceId());
		sb.append(", caseDefinitionKey=");
		sb.append(getCaseDefinitionKey());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append(
			"de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>liferayCaseInstanceId</column-name><column-value><![CDATA[");
		sb.append(getLiferayCaseInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>caseInstanceId</column-name><column-value><![CDATA[");
		sb.append(getCaseInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>caseDefinitionKey</column-name><column-value><![CDATA[");
		sb.append(getCaseDefinitionKey());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _liferayCaseInstanceId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userUuid;
	private Date _createDate;
	private Date _modifiedDate;
	private String _caseInstanceId;
	private String _caseDefinitionKey;
	private BaseModel<?> _caseInstanceRemoteModel;
}