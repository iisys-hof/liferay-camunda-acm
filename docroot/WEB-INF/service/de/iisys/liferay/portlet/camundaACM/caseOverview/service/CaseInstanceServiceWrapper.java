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
 * Provides a wrapper for {@link CaseInstanceService}.
 *
 * @author Christian OchsenkÃ¼hn
 * @see CaseInstanceService
 * @generated
 */
public class CaseInstanceServiceWrapper implements CaseInstanceService,
	ServiceWrapper<CaseInstanceService> {
	public CaseInstanceServiceWrapper(CaseInstanceService caseInstanceService) {
		_caseInstanceService = caseInstanceService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _caseInstanceService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_caseInstanceService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _caseInstanceService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.portlet.wiki.model.WikiPage getWikiPage(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _caseInstanceService.getWikiPage(resourcePrimKey);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public CaseInstanceService getWrappedCaseInstanceService() {
		return _caseInstanceService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedCaseInstanceService(
		CaseInstanceService caseInstanceService) {
		_caseInstanceService = caseInstanceService;
	}

	@Override
	public CaseInstanceService getWrappedService() {
		return _caseInstanceService;
	}

	@Override
	public void setWrappedService(CaseInstanceService caseInstanceService) {
		_caseInstanceService = caseInstanceService;
	}

	private CaseInstanceService _caseInstanceService;
}