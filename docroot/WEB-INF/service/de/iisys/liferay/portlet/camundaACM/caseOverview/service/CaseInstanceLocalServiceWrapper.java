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

package de.iisys.liferay.portlet.camundaACM.caseOverview.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CaseInstanceLocalService}.
 *
 * @author Christian OchsenkÃ¼hn
 * @see CaseInstanceLocalService
 * @generated
 */
public class CaseInstanceLocalServiceWrapper implements CaseInstanceLocalService,
	ServiceWrapper<CaseInstanceLocalService> {
	public CaseInstanceLocalServiceWrapper(
		CaseInstanceLocalService caseInstanceLocalService) {
		_caseInstanceLocalService = caseInstanceLocalService;
	}

	/**
	* Adds the case instance to the database. Also notifies the appropriate model listeners.
	*
	* @param caseInstance the case instance
	* @return the case instance that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance addCaseInstance(
		de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance caseInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.addCaseInstance(caseInstance);
	}

	/**
	* Creates a new case instance with the primary key. Does not add the case instance to the database.
	*
	* @param liferayCaseInstanceId the primary key for the new case instance
	* @return the new case instance
	*/
	@Override
	public de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance createCaseInstance(
		long liferayCaseInstanceId) {
		return _caseInstanceLocalService.createCaseInstance(liferayCaseInstanceId);
	}

	/**
	* Deletes the case instance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param liferayCaseInstanceId the primary key of the case instance
	* @return the case instance that was removed
	* @throws PortalException if a case instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance deleteCaseInstance(
		long liferayCaseInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.deleteCaseInstance(liferayCaseInstanceId);
	}

	/**
	* Deletes the case instance from the database. Also notifies the appropriate model listeners.
	*
	* @param caseInstance the case instance
	* @return the case instance that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance deleteCaseInstance(
		de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance caseInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.deleteCaseInstance(caseInstance);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _caseInstanceLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.iisys.liferay.portlet.camundaACM.caseOverview.model.impl.CaseInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.iisys.liferay.portlet.camundaACM.caseOverview.model.impl.CaseInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance fetchCaseInstance(
		long liferayCaseInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.fetchCaseInstance(liferayCaseInstanceId);
	}

	/**
	* Returns the case instance with the primary key.
	*
	* @param liferayCaseInstanceId the primary key of the case instance
	* @return the case instance
	* @throws PortalException if a case instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance getCaseInstance(
		long liferayCaseInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.getCaseInstance(liferayCaseInstanceId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the case instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.iisys.liferay.portlet.camundaACM.caseOverview.model.impl.CaseInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of case instances
	* @param end the upper bound of the range of case instances (not inclusive)
	* @return the range of case instances
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance> getCaseInstances(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.getCaseInstances(start, end);
	}

	/**
	* Returns the number of case instances.
	*
	* @return the number of case instances
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getCaseInstancesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.getCaseInstancesCount();
	}

	/**
	* Updates the case instance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param caseInstance the case instance
	* @return the case instance that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance updateCaseInstance(
		de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance caseInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.updateCaseInstance(caseInstance);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _caseInstanceLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_caseInstanceLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _caseInstanceLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance addCaseInstance(
		java.lang.String caseInstanceId, java.lang.String caseDefinitionKey,
		com.liferay.portal.service.ServiceContext sc, long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.addCaseInstance(caseInstanceId,
			caseDefinitionKey, sc, userId, groupId);
	}

	@Override
	public de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance addCaseInstance(
		java.lang.String caseInstanceId, java.lang.String caseDefinitionKey,
		com.liferay.portal.service.ServiceContext sc, long userId,
		long groupId, java.util.Date createTime)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.addCaseInstance(caseInstanceId,
			caseDefinitionKey, sc, userId, groupId, createTime);
	}

	@Override
	public de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance updateAssetsForCaseInstance(
		long casePK, com.liferay.portal.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.updateAssetsForCaseInstance(casePK, sc);
	}

	@Override
	public de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance findCaseInstanceById(
		java.lang.String caseInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException,
			de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException {
		return _caseInstanceLocalService.findCaseInstanceById(caseInstanceId);
	}

	@Override
	public java.util.List<de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance> findCaseInstancesByDefinitionKey(
		java.lang.String caseDefinitionKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.findCaseInstancesByDefinitionKey(caseDefinitionKey);
	}

	@Override
	public java.util.List<de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance> findCaseInstancesByDefinitionKey(
		java.lang.String caseDefinitionKey, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.findCaseInstancesByDefinitionKey(caseDefinitionKey,
			start, end);
	}

	@Override
	public int getInstancesCountByDefinitionKey(
		java.lang.String caseDefinitionKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceLocalService.getInstancesCountByDefinitionKey(caseDefinitionKey);
	}

	@Override
	public void deleteAllCaseInstances()
		throws com.liferay.portal.kernel.exception.SystemException {
		_caseInstanceLocalService.deleteAllCaseInstances();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public CaseInstanceLocalService getWrappedCaseInstanceLocalService() {
		return _caseInstanceLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedCaseInstanceLocalService(
		CaseInstanceLocalService caseInstanceLocalService) {
		_caseInstanceLocalService = caseInstanceLocalService;
	}

	@Override
	public CaseInstanceLocalService getWrappedService() {
		return _caseInstanceLocalService;
	}

	@Override
	public void setWrappedService(
		CaseInstanceLocalService caseInstanceLocalService) {
		_caseInstanceLocalService = caseInstanceLocalService;
	}

	private CaseInstanceLocalService _caseInstanceLocalService;
}