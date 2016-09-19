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

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance;
import de.iisys.liferay.portlet.camundaACM.caseOverview.service.CaseInstanceLocalServiceUtil;

/**
 * @author Christian OchsenkÃ¼hn
 * @generated
 */
public abstract class CaseInstanceActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public CaseInstanceActionableDynamicQuery() throws SystemException {
		setBaseLocalService(CaseInstanceLocalServiceUtil.getService());
		setClass(CaseInstance.class);

		setClassLoader(de.iisys.liferay.portlet.camundaACM.caseOverview.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("liferayCaseInstanceId");
	}
}