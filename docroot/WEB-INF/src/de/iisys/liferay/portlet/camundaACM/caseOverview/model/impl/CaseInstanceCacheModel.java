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

package de.iisys.liferay.portlet.camundaACM.caseOverview.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CaseInstance in entity cache.
 *
 * @author Christian OchsenkÃ¼hn
 * @see CaseInstance
 * @generated
 */
public class CaseInstanceCacheModel implements CacheModel<CaseInstance>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{liferayCaseInstanceId=");
		sb.append(liferayCaseInstanceId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", caseInstanceId=");
		sb.append(caseInstanceId);
		sb.append(", caseDefinitionKey=");
		sb.append(caseDefinitionKey);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CaseInstance toEntityModel() {
		CaseInstanceImpl caseInstanceImpl = new CaseInstanceImpl();

		caseInstanceImpl.setLiferayCaseInstanceId(liferayCaseInstanceId);
		caseInstanceImpl.setCompanyId(companyId);
		caseInstanceImpl.setGroupId(groupId);
		caseInstanceImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			caseInstanceImpl.setCreateDate(null);
		}
		else {
			caseInstanceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			caseInstanceImpl.setModifiedDate(null);
		}
		else {
			caseInstanceImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (caseInstanceId == null) {
			caseInstanceImpl.setCaseInstanceId(StringPool.BLANK);
		}
		else {
			caseInstanceImpl.setCaseInstanceId(caseInstanceId);
		}

		if (caseDefinitionKey == null) {
			caseInstanceImpl.setCaseDefinitionKey(StringPool.BLANK);
		}
		else {
			caseInstanceImpl.setCaseDefinitionKey(caseDefinitionKey);
		}

		caseInstanceImpl.resetOriginalValues();

		return caseInstanceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		liferayCaseInstanceId = objectInput.readLong();
		companyId = objectInput.readLong();
		groupId = objectInput.readLong();
		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		caseInstanceId = objectInput.readUTF();
		caseDefinitionKey = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(liferayCaseInstanceId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (caseInstanceId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(caseInstanceId);
		}

		if (caseDefinitionKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(caseDefinitionKey);
		}
	}

	public long liferayCaseInstanceId;
	public long companyId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String caseInstanceId;
	public String caseDefinitionKey;
}