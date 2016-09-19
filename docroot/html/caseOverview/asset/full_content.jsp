<%@ include file="/html/init.jsp" %>

<%@ page import="de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance" %>
<%@ page import="de.iisys.liferay.portlet.camundaACM.caseOverview.service.CaseInstanceLocalServiceUtil" %>

<%
	CaseInstance inst = (CaseInstance)request.getAttribute("caseInstance");

	inst = inst.toEscapedModel();
%>

<dl>
        <dt><liferay-ui:message key="de.iisys.ACMCases.case" /></dt>
        <dd><%= inst.getCaseDefinitionKey() %></dd>
        <dt><liferay-ui:message key="de.iisys.ACMCases.caseInstance" /></dt>
        <dd><%= inst.getCaseInstanceId() %></dd>
</dl>