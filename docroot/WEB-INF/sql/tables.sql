create table ACMCaseOverview_CaseInstance (
	liferayCaseInstanceId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	caseInstanceId VARCHAR(75) null,
	caseDefinitionKey VARCHAR(75) null
);

create table CamundaACM_CaseInstance (
	liferayCaseInstanceId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	caseInstanceId VARCHAR(75) null,
	caseDefinitionKey VARCHAR(75) null
);