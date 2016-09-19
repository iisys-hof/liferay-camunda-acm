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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CaseInstance}.
 * </p>
 *
 * @author Christian OchsenkÃ¼hn
 * @see CaseInstance
 * @generated
 */
public class CaseInstanceWrapper implements CaseInstance,
	ModelWrapper<CaseInstance> {
	public CaseInstanceWrapper(CaseInstance caseInstance) {
		_caseInstance = caseInstance;
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

	/**
	* Returns the primary key of this case instance.
	*
	* @return the primary key of this case instance
	*/
	@Override
	public long getPrimaryKey() {
		return _caseInstance.getPrimaryKey();
	}

	/**
	* Sets the primary key of this case instance.
	*
	* @param primaryKey the primary key of this case instance
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_caseInstance.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the liferay case instance ID of this case instance.
	*
	* @return the liferay case instance ID of this case instance
	*/
	@Override
	public long getLiferayCaseInstanceId() {
		return _caseInstance.getLiferayCaseInstanceId();
	}

	/**
	* Sets the liferay case instance ID of this case instance.
	*
	* @param liferayCaseInstanceId the liferay case instance ID of this case instance
	*/
	@Override
	public void setLiferayCaseInstanceId(long liferayCaseInstanceId) {
		_caseInstance.setLiferayCaseInstanceId(liferayCaseInstanceId);
	}

	/**
	* Returns the company ID of this case instance.
	*
	* @return the company ID of this case instance
	*/
	@Override
	public long getCompanyId() {
		return _caseInstance.getCompanyId();
	}

	/**
	* Sets the company ID of this case instance.
	*
	* @param companyId the company ID of this case instance
	*/
	@Override
	public void setCompanyId(long companyId) {
		_caseInstance.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this case instance.
	*
	* @return the group ID of this case instance
	*/
	@Override
	public long getGroupId() {
		return _caseInstance.getGroupId();
	}

	/**
	* Sets the group ID of this case instance.
	*
	* @param groupId the group ID of this case instance
	*/
	@Override
	public void setGroupId(long groupId) {
		_caseInstance.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this case instance.
	*
	* @return the user ID of this case instance
	*/
	@Override
	public long getUserId() {
		return _caseInstance.getUserId();
	}

	/**
	* Sets the user ID of this case instance.
	*
	* @param userId the user ID of this case instance
	*/
	@Override
	public void setUserId(long userId) {
		_caseInstance.setUserId(userId);
	}

	/**
	* Returns the user uuid of this case instance.
	*
	* @return the user uuid of this case instance
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _caseInstance.getUserUuid();
	}

	/**
	* Sets the user uuid of this case instance.
	*
	* @param userUuid the user uuid of this case instance
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_caseInstance.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this case instance.
	*
	* @return the create date of this case instance
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _caseInstance.getCreateDate();
	}

	/**
	* Sets the create date of this case instance.
	*
	* @param createDate the create date of this case instance
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_caseInstance.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this case instance.
	*
	* @return the modified date of this case instance
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _caseInstance.getModifiedDate();
	}

	/**
	* Sets the modified date of this case instance.
	*
	* @param modifiedDate the modified date of this case instance
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_caseInstance.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the case instance ID of this case instance.
	*
	* @return the case instance ID of this case instance
	*/
	@Override
	public java.lang.String getCaseInstanceId() {
		return _caseInstance.getCaseInstanceId();
	}

	/**
	* Sets the case instance ID of this case instance.
	*
	* @param caseInstanceId the case instance ID of this case instance
	*/
	@Override
	public void setCaseInstanceId(java.lang.String caseInstanceId) {
		_caseInstance.setCaseInstanceId(caseInstanceId);
	}

	/**
	* Returns the case definition key of this case instance.
	*
	* @return the case definition key of this case instance
	*/
	@Override
	public java.lang.String getCaseDefinitionKey() {
		return _caseInstance.getCaseDefinitionKey();
	}

	/**
	* Sets the case definition key of this case instance.
	*
	* @param caseDefinitionKey the case definition key of this case instance
	*/
	@Override
	public void setCaseDefinitionKey(java.lang.String caseDefinitionKey) {
		_caseInstance.setCaseDefinitionKey(caseDefinitionKey);
	}

	@Override
	public boolean isNew() {
		return _caseInstance.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_caseInstance.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _caseInstance.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_caseInstance.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _caseInstance.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _caseInstance.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_caseInstance.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _caseInstance.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_caseInstance.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_caseInstance.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_caseInstance.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new CaseInstanceWrapper((CaseInstance)_caseInstance.clone());
	}

	@Override
	public int compareTo(
		de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance caseInstance) {
		return _caseInstance.compareTo(caseInstance);
	}

	@Override
	public int hashCode() {
		return _caseInstance.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance> toCacheModel() {
		return _caseInstance.toCacheModel();
	}

	@Override
	public de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance toEscapedModel() {
		return new CaseInstanceWrapper(_caseInstance.toEscapedModel());
	}

	@Override
	public de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance toUnescapedModel() {
		return new CaseInstanceWrapper(_caseInstance.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _caseInstance.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _caseInstance.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_caseInstance.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CaseInstanceWrapper)) {
			return false;
		}

		CaseInstanceWrapper caseInstanceWrapper = (CaseInstanceWrapper)obj;

		if (Validator.equals(_caseInstance, caseInstanceWrapper._caseInstance)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public CaseInstance getWrappedCaseInstance() {
		return _caseInstance;
	}

	@Override
	public CaseInstance getWrappedModel() {
		return _caseInstance;
	}

	@Override
	public void resetOriginalValues() {
		_caseInstance.resetOriginalValues();
	}

	private CaseInstance _caseInstance;
}