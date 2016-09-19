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

package de.iisys.liferay.portlet.camundaACM.caseOverview.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance;

import java.util.List;

/**
 * The persistence utility for the case instance service. This utility wraps {@link CaseInstancePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Christian OchsenkÃ¼hn
 * @see CaseInstancePersistence
 * @see CaseInstancePersistenceImpl
 * @generated
 */
public class CaseInstanceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(CaseInstance caseInstance) {
		getPersistence().clearCache(caseInstance);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CaseInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CaseInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CaseInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static CaseInstance update(CaseInstance caseInstance)
		throws SystemException {
		return getPersistence().update(caseInstance);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static CaseInstance update(CaseInstance caseInstance,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(caseInstance, serviceContext);
	}

	/**
	* Returns all the case instances where caseDefinitionKey = &#63;.
	*
	* @param caseDefinitionKey the case definition key
	* @return the matching case instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance> findByCaseDefinitionKey(
		java.lang.String caseDefinitionKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCaseDefinitionKey(caseDefinitionKey);
	}

	/**
	* Returns a range of all the case instances where caseDefinitionKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.iisys.liferay.portlet.camundaACM.caseOverview.model.impl.CaseInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param caseDefinitionKey the case definition key
	* @param start the lower bound of the range of case instances
	* @param end the upper bound of the range of case instances (not inclusive)
	* @return the range of matching case instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance> findByCaseDefinitionKey(
		java.lang.String caseDefinitionKey, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCaseDefinitionKey(caseDefinitionKey, start, end);
	}

	/**
	* Returns an ordered range of all the case instances where caseDefinitionKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.iisys.liferay.portlet.camundaACM.caseOverview.model.impl.CaseInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param caseDefinitionKey the case definition key
	* @param start the lower bound of the range of case instances
	* @param end the upper bound of the range of case instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching case instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance> findByCaseDefinitionKey(
		java.lang.String caseDefinitionKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCaseDefinitionKey(caseDefinitionKey, start, end,
			orderByComparator);
	}

	/**
	* Returns the first case instance in the ordered set where caseDefinitionKey = &#63;.
	*
	* @param caseDefinitionKey the case definition key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching case instance
	* @throws de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException if a matching case instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance findByCaseDefinitionKey_First(
		java.lang.String caseDefinitionKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException {
		return getPersistence()
				   .findByCaseDefinitionKey_First(caseDefinitionKey,
			orderByComparator);
	}

	/**
	* Returns the first case instance in the ordered set where caseDefinitionKey = &#63;.
	*
	* @param caseDefinitionKey the case definition key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching case instance, or <code>null</code> if a matching case instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance fetchByCaseDefinitionKey_First(
		java.lang.String caseDefinitionKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCaseDefinitionKey_First(caseDefinitionKey,
			orderByComparator);
	}

	/**
	* Returns the last case instance in the ordered set where caseDefinitionKey = &#63;.
	*
	* @param caseDefinitionKey the case definition key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching case instance
	* @throws de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException if a matching case instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance findByCaseDefinitionKey_Last(
		java.lang.String caseDefinitionKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException {
		return getPersistence()
				   .findByCaseDefinitionKey_Last(caseDefinitionKey,
			orderByComparator);
	}

	/**
	* Returns the last case instance in the ordered set where caseDefinitionKey = &#63;.
	*
	* @param caseDefinitionKey the case definition key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching case instance, or <code>null</code> if a matching case instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance fetchByCaseDefinitionKey_Last(
		java.lang.String caseDefinitionKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCaseDefinitionKey_Last(caseDefinitionKey,
			orderByComparator);
	}

	/**
	* Returns the case instances before and after the current case instance in the ordered set where caseDefinitionKey = &#63;.
	*
	* @param liferayCaseInstanceId the primary key of the current case instance
	* @param caseDefinitionKey the case definition key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next case instance
	* @throws de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException if a case instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance[] findByCaseDefinitionKey_PrevAndNext(
		long liferayCaseInstanceId, java.lang.String caseDefinitionKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException {
		return getPersistence()
				   .findByCaseDefinitionKey_PrevAndNext(liferayCaseInstanceId,
			caseDefinitionKey, orderByComparator);
	}

	/**
	* Removes all the case instances where caseDefinitionKey = &#63; from the database.
	*
	* @param caseDefinitionKey the case definition key
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCaseDefinitionKey(
		java.lang.String caseDefinitionKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCaseDefinitionKey(caseDefinitionKey);
	}

	/**
	* Returns the number of case instances where caseDefinitionKey = &#63;.
	*
	* @param caseDefinitionKey the case definition key
	* @return the number of matching case instances
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCaseDefinitionKey(
		java.lang.String caseDefinitionKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCaseDefinitionKey(caseDefinitionKey);
	}

	/**
	* Returns the case instance where caseInstanceId = &#63; or throws a {@link de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException} if it could not be found.
	*
	* @param caseInstanceId the case instance ID
	* @return the matching case instance
	* @throws de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException if a matching case instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance findByCaseInstanceId(
		java.lang.String caseInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException,
			de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException {
		return getPersistence().findByCaseInstanceId(caseInstanceId);
	}

	/**
	* Returns the case instance where caseInstanceId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param caseInstanceId the case instance ID
	* @return the matching case instance, or <code>null</code> if a matching case instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance fetchByCaseInstanceId(
		java.lang.String caseInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByCaseInstanceId(caseInstanceId);
	}

	/**
	* Returns the case instance where caseInstanceId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param caseInstanceId the case instance ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching case instance, or <code>null</code> if a matching case instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance fetchByCaseInstanceId(
		java.lang.String caseInstanceId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCaseInstanceId(caseInstanceId, retrieveFromCache);
	}

	/**
	* Removes the case instance where caseInstanceId = &#63; from the database.
	*
	* @param caseInstanceId the case instance ID
	* @return the case instance that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance removeByCaseInstanceId(
		java.lang.String caseInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException,
			de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException {
		return getPersistence().removeByCaseInstanceId(caseInstanceId);
	}

	/**
	* Returns the number of case instances where caseInstanceId = &#63;.
	*
	* @param caseInstanceId the case instance ID
	* @return the number of matching case instances
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCaseInstanceId(java.lang.String caseInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCaseInstanceId(caseInstanceId);
	}

	/**
	* Caches the case instance in the entity cache if it is enabled.
	*
	* @param caseInstance the case instance
	*/
	public static void cacheResult(
		de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance caseInstance) {
		getPersistence().cacheResult(caseInstance);
	}

	/**
	* Caches the case instances in the entity cache if it is enabled.
	*
	* @param caseInstances the case instances
	*/
	public static void cacheResult(
		java.util.List<de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance> caseInstances) {
		getPersistence().cacheResult(caseInstances);
	}

	/**
	* Creates a new case instance with the primary key. Does not add the case instance to the database.
	*
	* @param liferayCaseInstanceId the primary key for the new case instance
	* @return the new case instance
	*/
	public static de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance create(
		long liferayCaseInstanceId) {
		return getPersistence().create(liferayCaseInstanceId);
	}

	/**
	* Removes the case instance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param liferayCaseInstanceId the primary key of the case instance
	* @return the case instance that was removed
	* @throws de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException if a case instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance remove(
		long liferayCaseInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException,
			de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException {
		return getPersistence().remove(liferayCaseInstanceId);
	}

	public static de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance updateImpl(
		de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance caseInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(caseInstance);
	}

	/**
	* Returns the case instance with the primary key or throws a {@link de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException} if it could not be found.
	*
	* @param liferayCaseInstanceId the primary key of the case instance
	* @return the case instance
	* @throws de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException if a case instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance findByPrimaryKey(
		long liferayCaseInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException,
			de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException {
		return getPersistence().findByPrimaryKey(liferayCaseInstanceId);
	}

	/**
	* Returns the case instance with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param liferayCaseInstanceId the primary key of the case instance
	* @return the case instance, or <code>null</code> if a case instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance fetchByPrimaryKey(
		long liferayCaseInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(liferayCaseInstanceId);
	}

	/**
	* Returns all the case instances.
	*
	* @return the case instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the case instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.iisys.liferay.portlet.camundaACM.caseOverview.model.impl.CaseInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of case instances
	* @param end the upper bound of the range of case instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of case instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the case instances from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of case instances.
	*
	* @return the number of case instances
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static CaseInstancePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CaseInstancePersistence)PortletBeanLocatorUtil.locate(de.iisys.liferay.portlet.camundaACM.caseOverview.service.ClpSerializer.getServletContextName(),
					CaseInstancePersistence.class.getName());

			ReferenceRegistry.registerReference(CaseInstanceUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(CaseInstancePersistence persistence) {
	}

	private static CaseInstancePersistence _persistence;
}