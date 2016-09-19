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

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException;
import de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance;
import de.iisys.liferay.portlet.camundaACM.caseOverview.model.impl.CaseInstanceImpl;
import de.iisys.liferay.portlet.camundaACM.caseOverview.model.impl.CaseInstanceModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the case instance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Christian OchsenkÃ¼hn
 * @see CaseInstancePersistence
 * @see CaseInstanceUtil
 * @generated
 */
public class CaseInstancePersistenceImpl extends BasePersistenceImpl<CaseInstance>
	implements CaseInstancePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CaseInstanceUtil} to access the case instance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CaseInstanceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CaseInstanceModelImpl.FINDER_CACHE_ENABLED, CaseInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CaseInstanceModelImpl.FINDER_CACHE_ENABLED, CaseInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CaseInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CASEDEFINITIONKEY =
		new FinderPath(CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CaseInstanceModelImpl.FINDER_CACHE_ENABLED, CaseInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCaseDefinitionKey",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CASEDEFINITIONKEY =
		new FinderPath(CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CaseInstanceModelImpl.FINDER_CACHE_ENABLED, CaseInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCaseDefinitionKey", new String[] { String.class.getName() },
			CaseInstanceModelImpl.CASEDEFINITIONKEY_COLUMN_BITMASK |
			CaseInstanceModelImpl.CASEINSTANCEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CASEDEFINITIONKEY = new FinderPath(CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CaseInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCaseDefinitionKey", new String[] { String.class.getName() });

	/**
	 * Returns all the case instances where caseDefinitionKey = &#63;.
	 *
	 * @param caseDefinitionKey the case definition key
	 * @return the matching case instances
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<CaseInstance> findByCaseDefinitionKey(String caseDefinitionKey)
		throws SystemException {
		return findByCaseDefinitionKey(caseDefinitionKey, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<CaseInstance> findByCaseDefinitionKey(
		String caseDefinitionKey, int start, int end) throws SystemException {
		return findByCaseDefinitionKey(caseDefinitionKey, start, end, null);
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
	@Override
	public List<CaseInstance> findByCaseDefinitionKey(
		String caseDefinitionKey, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CASEDEFINITIONKEY;
			finderArgs = new Object[] { caseDefinitionKey };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CASEDEFINITIONKEY;
			finderArgs = new Object[] {
					caseDefinitionKey,
					
					start, end, orderByComparator
				};
		}

		List<CaseInstance> list = (List<CaseInstance>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CaseInstance caseInstance : list) {
				if (!Validator.equals(caseDefinitionKey,
							caseInstance.getCaseDefinitionKey())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CASEINSTANCE_WHERE);

			boolean bindCaseDefinitionKey = false;

			if (caseDefinitionKey == null) {
				query.append(_FINDER_COLUMN_CASEDEFINITIONKEY_CASEDEFINITIONKEY_1);
			}
			else if (caseDefinitionKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CASEDEFINITIONKEY_CASEDEFINITIONKEY_3);
			}
			else {
				bindCaseDefinitionKey = true;

				query.append(_FINDER_COLUMN_CASEDEFINITIONKEY_CASEDEFINITIONKEY_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CaseInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCaseDefinitionKey) {
					qPos.add(caseDefinitionKey);
				}

				if (!pagination) {
					list = (List<CaseInstance>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<CaseInstance>(list);
				}
				else {
					list = (List<CaseInstance>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public CaseInstance findByCaseDefinitionKey_First(
		String caseDefinitionKey, OrderByComparator orderByComparator)
		throws NoSuchCaseInstanceException, SystemException {
		CaseInstance caseInstance = fetchByCaseDefinitionKey_First(caseDefinitionKey,
				orderByComparator);

		if (caseInstance != null) {
			return caseInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("caseDefinitionKey=");
		msg.append(caseDefinitionKey);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCaseInstanceException(msg.toString());
	}

	/**
	 * Returns the first case instance in the ordered set where caseDefinitionKey = &#63;.
	 *
	 * @param caseDefinitionKey the case definition key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching case instance, or <code>null</code> if a matching case instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CaseInstance fetchByCaseDefinitionKey_First(
		String caseDefinitionKey, OrderByComparator orderByComparator)
		throws SystemException {
		List<CaseInstance> list = findByCaseDefinitionKey(caseDefinitionKey, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CaseInstance findByCaseDefinitionKey_Last(String caseDefinitionKey,
		OrderByComparator orderByComparator)
		throws NoSuchCaseInstanceException, SystemException {
		CaseInstance caseInstance = fetchByCaseDefinitionKey_Last(caseDefinitionKey,
				orderByComparator);

		if (caseInstance != null) {
			return caseInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("caseDefinitionKey=");
		msg.append(caseDefinitionKey);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCaseInstanceException(msg.toString());
	}

	/**
	 * Returns the last case instance in the ordered set where caseDefinitionKey = &#63;.
	 *
	 * @param caseDefinitionKey the case definition key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching case instance, or <code>null</code> if a matching case instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CaseInstance fetchByCaseDefinitionKey_Last(
		String caseDefinitionKey, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByCaseDefinitionKey(caseDefinitionKey);

		if (count == 0) {
			return null;
		}

		List<CaseInstance> list = findByCaseDefinitionKey(caseDefinitionKey,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CaseInstance[] findByCaseDefinitionKey_PrevAndNext(
		long liferayCaseInstanceId, String caseDefinitionKey,
		OrderByComparator orderByComparator)
		throws NoSuchCaseInstanceException, SystemException {
		CaseInstance caseInstance = findByPrimaryKey(liferayCaseInstanceId);

		Session session = null;

		try {
			session = openSession();

			CaseInstance[] array = new CaseInstanceImpl[3];

			array[0] = getByCaseDefinitionKey_PrevAndNext(session,
					caseInstance, caseDefinitionKey, orderByComparator, true);

			array[1] = caseInstance;

			array[2] = getByCaseDefinitionKey_PrevAndNext(session,
					caseInstance, caseDefinitionKey, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CaseInstance getByCaseDefinitionKey_PrevAndNext(Session session,
		CaseInstance caseInstance, String caseDefinitionKey,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CASEINSTANCE_WHERE);

		boolean bindCaseDefinitionKey = false;

		if (caseDefinitionKey == null) {
			query.append(_FINDER_COLUMN_CASEDEFINITIONKEY_CASEDEFINITIONKEY_1);
		}
		else if (caseDefinitionKey.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CASEDEFINITIONKEY_CASEDEFINITIONKEY_3);
		}
		else {
			bindCaseDefinitionKey = true;

			query.append(_FINDER_COLUMN_CASEDEFINITIONKEY_CASEDEFINITIONKEY_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CaseInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCaseDefinitionKey) {
			qPos.add(caseDefinitionKey);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(caseInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CaseInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the case instances where caseDefinitionKey = &#63; from the database.
	 *
	 * @param caseDefinitionKey the case definition key
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCaseDefinitionKey(String caseDefinitionKey)
		throws SystemException {
		for (CaseInstance caseInstance : findByCaseDefinitionKey(
				caseDefinitionKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(caseInstance);
		}
	}

	/**
	 * Returns the number of case instances where caseDefinitionKey = &#63;.
	 *
	 * @param caseDefinitionKey the case definition key
	 * @return the number of matching case instances
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCaseDefinitionKey(String caseDefinitionKey)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CASEDEFINITIONKEY;

		Object[] finderArgs = new Object[] { caseDefinitionKey };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CASEINSTANCE_WHERE);

			boolean bindCaseDefinitionKey = false;

			if (caseDefinitionKey == null) {
				query.append(_FINDER_COLUMN_CASEDEFINITIONKEY_CASEDEFINITIONKEY_1);
			}
			else if (caseDefinitionKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CASEDEFINITIONKEY_CASEDEFINITIONKEY_3);
			}
			else {
				bindCaseDefinitionKey = true;

				query.append(_FINDER_COLUMN_CASEDEFINITIONKEY_CASEDEFINITIONKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCaseDefinitionKey) {
					qPos.add(caseDefinitionKey);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CASEDEFINITIONKEY_CASEDEFINITIONKEY_1 =
		"caseInstance.caseDefinitionKey IS NULL";
	private static final String _FINDER_COLUMN_CASEDEFINITIONKEY_CASEDEFINITIONKEY_2 =
		"caseInstance.caseDefinitionKey = ?";
	private static final String _FINDER_COLUMN_CASEDEFINITIONKEY_CASEDEFINITIONKEY_3 =
		"(caseInstance.caseDefinitionKey IS NULL OR caseInstance.caseDefinitionKey = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_CASEINSTANCEID = new FinderPath(CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CaseInstanceModelImpl.FINDER_CACHE_ENABLED, CaseInstanceImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByCaseInstanceId",
			new String[] { String.class.getName() },
			CaseInstanceModelImpl.CASEINSTANCEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CASEINSTANCEID = new FinderPath(CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CaseInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCaseInstanceId",
			new String[] { String.class.getName() });

	/**
	 * Returns the case instance where caseInstanceId = &#63; or throws a {@link de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException} if it could not be found.
	 *
	 * @param caseInstanceId the case instance ID
	 * @return the matching case instance
	 * @throws de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException if a matching case instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CaseInstance findByCaseInstanceId(String caseInstanceId)
		throws NoSuchCaseInstanceException, SystemException {
		CaseInstance caseInstance = fetchByCaseInstanceId(caseInstanceId);

		if (caseInstance == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("caseInstanceId=");
			msg.append(caseInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCaseInstanceException(msg.toString());
		}

		return caseInstance;
	}

	/**
	 * Returns the case instance where caseInstanceId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param caseInstanceId the case instance ID
	 * @return the matching case instance, or <code>null</code> if a matching case instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CaseInstance fetchByCaseInstanceId(String caseInstanceId)
		throws SystemException {
		return fetchByCaseInstanceId(caseInstanceId, true);
	}

	/**
	 * Returns the case instance where caseInstanceId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param caseInstanceId the case instance ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching case instance, or <code>null</code> if a matching case instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CaseInstance fetchByCaseInstanceId(String caseInstanceId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { caseInstanceId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CASEINSTANCEID,
					finderArgs, this);
		}

		if (result instanceof CaseInstance) {
			CaseInstance caseInstance = (CaseInstance)result;

			if (!Validator.equals(caseInstanceId,
						caseInstance.getCaseInstanceId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_CASEINSTANCE_WHERE);

			boolean bindCaseInstanceId = false;

			if (caseInstanceId == null) {
				query.append(_FINDER_COLUMN_CASEINSTANCEID_CASEINSTANCEID_1);
			}
			else if (caseInstanceId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CASEINSTANCEID_CASEINSTANCEID_3);
			}
			else {
				bindCaseInstanceId = true;

				query.append(_FINDER_COLUMN_CASEINSTANCEID_CASEINSTANCEID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCaseInstanceId) {
					qPos.add(caseInstanceId);
				}

				List<CaseInstance> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CASEINSTANCEID,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"CaseInstancePersistenceImpl.fetchByCaseInstanceId(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					CaseInstance caseInstance = list.get(0);

					result = caseInstance;

					cacheResult(caseInstance);

					if ((caseInstance.getCaseInstanceId() == null) ||
							!caseInstance.getCaseInstanceId()
											 .equals(caseInstanceId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CASEINSTANCEID,
							finderArgs, caseInstance);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CASEINSTANCEID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CaseInstance)result;
		}
	}

	/**
	 * Removes the case instance where caseInstanceId = &#63; from the database.
	 *
	 * @param caseInstanceId the case instance ID
	 * @return the case instance that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CaseInstance removeByCaseInstanceId(String caseInstanceId)
		throws NoSuchCaseInstanceException, SystemException {
		CaseInstance caseInstance = findByCaseInstanceId(caseInstanceId);

		return remove(caseInstance);
	}

	/**
	 * Returns the number of case instances where caseInstanceId = &#63;.
	 *
	 * @param caseInstanceId the case instance ID
	 * @return the number of matching case instances
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCaseInstanceId(String caseInstanceId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CASEINSTANCEID;

		Object[] finderArgs = new Object[] { caseInstanceId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CASEINSTANCE_WHERE);

			boolean bindCaseInstanceId = false;

			if (caseInstanceId == null) {
				query.append(_FINDER_COLUMN_CASEINSTANCEID_CASEINSTANCEID_1);
			}
			else if (caseInstanceId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CASEINSTANCEID_CASEINSTANCEID_3);
			}
			else {
				bindCaseInstanceId = true;

				query.append(_FINDER_COLUMN_CASEINSTANCEID_CASEINSTANCEID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCaseInstanceId) {
					qPos.add(caseInstanceId);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CASEINSTANCEID_CASEINSTANCEID_1 = "caseInstance.caseInstanceId IS NULL";
	private static final String _FINDER_COLUMN_CASEINSTANCEID_CASEINSTANCEID_2 = "caseInstance.caseInstanceId = ?";
	private static final String _FINDER_COLUMN_CASEINSTANCEID_CASEINSTANCEID_3 = "(caseInstance.caseInstanceId IS NULL OR caseInstance.caseInstanceId = '')";

	public CaseInstancePersistenceImpl() {
		setModelClass(CaseInstance.class);
	}

	/**
	 * Caches the case instance in the entity cache if it is enabled.
	 *
	 * @param caseInstance the case instance
	 */
	@Override
	public void cacheResult(CaseInstance caseInstance) {
		EntityCacheUtil.putResult(CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CaseInstanceImpl.class, caseInstance.getPrimaryKey(), caseInstance);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CASEINSTANCEID,
			new Object[] { caseInstance.getCaseInstanceId() }, caseInstance);

		caseInstance.resetOriginalValues();
	}

	/**
	 * Caches the case instances in the entity cache if it is enabled.
	 *
	 * @param caseInstances the case instances
	 */
	@Override
	public void cacheResult(List<CaseInstance> caseInstances) {
		for (CaseInstance caseInstance : caseInstances) {
			if (EntityCacheUtil.getResult(
						CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
						CaseInstanceImpl.class, caseInstance.getPrimaryKey()) == null) {
				cacheResult(caseInstance);
			}
			else {
				caseInstance.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all case instances.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CaseInstanceImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CaseInstanceImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the case instance.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CaseInstance caseInstance) {
		EntityCacheUtil.removeResult(CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CaseInstanceImpl.class, caseInstance.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(caseInstance);
	}

	@Override
	public void clearCache(List<CaseInstance> caseInstances) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CaseInstance caseInstance : caseInstances) {
			EntityCacheUtil.removeResult(CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
				CaseInstanceImpl.class, caseInstance.getPrimaryKey());

			clearUniqueFindersCache(caseInstance);
		}
	}

	protected void cacheUniqueFindersCache(CaseInstance caseInstance) {
		if (caseInstance.isNew()) {
			Object[] args = new Object[] { caseInstance.getCaseInstanceId() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CASEINSTANCEID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CASEINSTANCEID,
				args, caseInstance);
		}
		else {
			CaseInstanceModelImpl caseInstanceModelImpl = (CaseInstanceModelImpl)caseInstance;

			if ((caseInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_CASEINSTANCEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { caseInstance.getCaseInstanceId() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CASEINSTANCEID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CASEINSTANCEID,
					args, caseInstance);
			}
		}
	}

	protected void clearUniqueFindersCache(CaseInstance caseInstance) {
		CaseInstanceModelImpl caseInstanceModelImpl = (CaseInstanceModelImpl)caseInstance;

		Object[] args = new Object[] { caseInstance.getCaseInstanceId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CASEINSTANCEID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CASEINSTANCEID, args);

		if ((caseInstanceModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CASEINSTANCEID.getColumnBitmask()) != 0) {
			args = new Object[] {
					caseInstanceModelImpl.getOriginalCaseInstanceId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CASEINSTANCEID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CASEINSTANCEID,
				args);
		}
	}

	/**
	 * Creates a new case instance with the primary key. Does not add the case instance to the database.
	 *
	 * @param liferayCaseInstanceId the primary key for the new case instance
	 * @return the new case instance
	 */
	@Override
	public CaseInstance create(long liferayCaseInstanceId) {
		CaseInstance caseInstance = new CaseInstanceImpl();

		caseInstance.setNew(true);
		caseInstance.setPrimaryKey(liferayCaseInstanceId);

		return caseInstance;
	}

	/**
	 * Removes the case instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param liferayCaseInstanceId the primary key of the case instance
	 * @return the case instance that was removed
	 * @throws de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException if a case instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CaseInstance remove(long liferayCaseInstanceId)
		throws NoSuchCaseInstanceException, SystemException {
		return remove((Serializable)liferayCaseInstanceId);
	}

	/**
	 * Removes the case instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the case instance
	 * @return the case instance that was removed
	 * @throws de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException if a case instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CaseInstance remove(Serializable primaryKey)
		throws NoSuchCaseInstanceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			CaseInstance caseInstance = (CaseInstance)session.get(CaseInstanceImpl.class,
					primaryKey);

			if (caseInstance == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCaseInstanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(caseInstance);
		}
		catch (NoSuchCaseInstanceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CaseInstance removeImpl(CaseInstance caseInstance)
		throws SystemException {
		caseInstance = toUnwrappedModel(caseInstance);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(caseInstance)) {
				caseInstance = (CaseInstance)session.get(CaseInstanceImpl.class,
						caseInstance.getPrimaryKeyObj());
			}

			if (caseInstance != null) {
				session.delete(caseInstance);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (caseInstance != null) {
			clearCache(caseInstance);
		}

		return caseInstance;
	}

	@Override
	public CaseInstance updateImpl(
		de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance caseInstance)
		throws SystemException {
		caseInstance = toUnwrappedModel(caseInstance);

		boolean isNew = caseInstance.isNew();

		CaseInstanceModelImpl caseInstanceModelImpl = (CaseInstanceModelImpl)caseInstance;

		Session session = null;

		try {
			session = openSession();

			if (caseInstance.isNew()) {
				session.save(caseInstance);

				caseInstance.setNew(false);
			}
			else {
				session.merge(caseInstance);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CaseInstanceModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((caseInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CASEDEFINITIONKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						caseInstanceModelImpl.getOriginalCaseDefinitionKey()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CASEDEFINITIONKEY,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CASEDEFINITIONKEY,
					args);

				args = new Object[] { caseInstanceModelImpl.getCaseDefinitionKey() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CASEDEFINITIONKEY,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CASEDEFINITIONKEY,
					args);
			}
		}

		EntityCacheUtil.putResult(CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
			CaseInstanceImpl.class, caseInstance.getPrimaryKey(), caseInstance);

		clearUniqueFindersCache(caseInstance);
		cacheUniqueFindersCache(caseInstance);

		return caseInstance;
	}

	protected CaseInstance toUnwrappedModel(CaseInstance caseInstance) {
		if (caseInstance instanceof CaseInstanceImpl) {
			return caseInstance;
		}

		CaseInstanceImpl caseInstanceImpl = new CaseInstanceImpl();

		caseInstanceImpl.setNew(caseInstance.isNew());
		caseInstanceImpl.setPrimaryKey(caseInstance.getPrimaryKey());

		caseInstanceImpl.setLiferayCaseInstanceId(caseInstance.getLiferayCaseInstanceId());
		caseInstanceImpl.setCompanyId(caseInstance.getCompanyId());
		caseInstanceImpl.setGroupId(caseInstance.getGroupId());
		caseInstanceImpl.setUserId(caseInstance.getUserId());
		caseInstanceImpl.setCreateDate(caseInstance.getCreateDate());
		caseInstanceImpl.setModifiedDate(caseInstance.getModifiedDate());
		caseInstanceImpl.setCaseInstanceId(caseInstance.getCaseInstanceId());
		caseInstanceImpl.setCaseDefinitionKey(caseInstance.getCaseDefinitionKey());

		return caseInstanceImpl;
	}

	/**
	 * Returns the case instance with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the case instance
	 * @return the case instance
	 * @throws de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException if a case instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CaseInstance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCaseInstanceException, SystemException {
		CaseInstance caseInstance = fetchByPrimaryKey(primaryKey);

		if (caseInstance == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCaseInstanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return caseInstance;
	}

	/**
	 * Returns the case instance with the primary key or throws a {@link de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException} if it could not be found.
	 *
	 * @param liferayCaseInstanceId the primary key of the case instance
	 * @return the case instance
	 * @throws de.iisys.liferay.portlet.camundaACM.caseOverview.NoSuchCaseInstanceException if a case instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CaseInstance findByPrimaryKey(long liferayCaseInstanceId)
		throws NoSuchCaseInstanceException, SystemException {
		return findByPrimaryKey((Serializable)liferayCaseInstanceId);
	}

	/**
	 * Returns the case instance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the case instance
	 * @return the case instance, or <code>null</code> if a case instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CaseInstance fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		CaseInstance caseInstance = (CaseInstance)EntityCacheUtil.getResult(CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
				CaseInstanceImpl.class, primaryKey);

		if (caseInstance == _nullCaseInstance) {
			return null;
		}

		if (caseInstance == null) {
			Session session = null;

			try {
				session = openSession();

				caseInstance = (CaseInstance)session.get(CaseInstanceImpl.class,
						primaryKey);

				if (caseInstance != null) {
					cacheResult(caseInstance);
				}
				else {
					EntityCacheUtil.putResult(CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
						CaseInstanceImpl.class, primaryKey, _nullCaseInstance);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(CaseInstanceModelImpl.ENTITY_CACHE_ENABLED,
					CaseInstanceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return caseInstance;
	}

	/**
	 * Returns the case instance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param liferayCaseInstanceId the primary key of the case instance
	 * @return the case instance, or <code>null</code> if a case instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CaseInstance fetchByPrimaryKey(long liferayCaseInstanceId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)liferayCaseInstanceId);
	}

	/**
	 * Returns all the case instances.
	 *
	 * @return the case instances
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<CaseInstance> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<CaseInstance> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	@Override
	public List<CaseInstance> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<CaseInstance> list = (List<CaseInstance>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CASEINSTANCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CASEINSTANCE;

				if (pagination) {
					sql = sql.concat(CaseInstanceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CaseInstance>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<CaseInstance>(list);
				}
				else {
					list = (List<CaseInstance>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the case instances from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (CaseInstance caseInstance : findAll()) {
			remove(caseInstance);
		}
	}

	/**
	 * Returns the number of case instances.
	 *
	 * @return the number of case instances
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CASEINSTANCE);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the case instance persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CaseInstance>> listenersList = new ArrayList<ModelListener<CaseInstance>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<CaseInstance>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(CaseInstanceImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CASEINSTANCE = "SELECT caseInstance FROM CaseInstance caseInstance";
	private static final String _SQL_SELECT_CASEINSTANCE_WHERE = "SELECT caseInstance FROM CaseInstance caseInstance WHERE ";
	private static final String _SQL_COUNT_CASEINSTANCE = "SELECT COUNT(caseInstance) FROM CaseInstance caseInstance";
	private static final String _SQL_COUNT_CASEINSTANCE_WHERE = "SELECT COUNT(caseInstance) FROM CaseInstance caseInstance WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "caseInstance.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CaseInstance exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CaseInstance exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CaseInstancePersistenceImpl.class);
	private static CaseInstance _nullCaseInstance = new CaseInstanceImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CaseInstance> toCacheModel() {
				return _nullCaseInstanceCacheModel;
			}
		};

	private static CacheModel<CaseInstance> _nullCaseInstanceCacheModel = new CacheModel<CaseInstance>() {
			@Override
			public CaseInstance toEntityModel() {
				return _nullCaseInstance;
			}
		};
}