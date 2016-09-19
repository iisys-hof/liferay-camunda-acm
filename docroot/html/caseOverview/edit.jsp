<%
/**
 * Copyright (c) 2015-present. All rights reserved.
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
 *
 * Created for: Social Collaboration Hub (www.sc-hub.de)
 * Created at: Institute for Information Systems (www.iisys.de/en)
 * @author: Christian Ochsenkühn
 */
%>

<%@include file="/html/init.jsp" %>

<%@ page import="de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance"%>
<%@ page import="de.iisys.liferay.portlet.camundaACM.caseOverview.service.CaseInstanceLocalServiceUtil"%>
<%@ page import="com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.asset.model.AssetEntry" %>
<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>

<%
	HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(
			PortalUtil.getHttpServletRequest(renderRequest)); 
	String caseInstanceId = httpRequest.getParameter("caseInstanceId");
	String caseDefinitionKey = httpRequest.getParameter("caseDefinitionKey");
	String caseCreateTime = httpRequest.getParameter("caseCreateTime");

	CaseInstance caseInst = CaseInstanceLocalServiceUtil.findCaseInstanceById(caseInstanceId);
%>


<portlet:renderURL var="viewURL">
        <portlet:param name="mvcPath" value="/html/caseOverview/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="updateCaseInstanceAsset" var="editCaseInstanceURL" />

<% String fullViewURL = viewURL + "&caseInstanceId="+caseInstanceId + "&caseDefinitionKey="+caseDefinitionKey + "&caseCreateTime="+caseCreateTime;
	String fullEditCaseInstanceURL = editCaseInstanceURL + "&caseInstanceId="+caseInstanceId + "&caseDefinitionKey="+caseDefinitionKey + "&caseCreateTime="+caseCreateTime; %>
	
<aui:form action="<%= fullEditCaseInstanceURL %>" name="<portlet:namespace/>fm">
	<aui:model-context bean="<%= caseInst %>" model="<%= CaseInstance.class %>" />
	
	<aui:input type="hidden" name="liferayCaseInstanceId"
                              value='<%= caseInst == null ? "" : caseInst.getLiferayCaseInstanceId() %>' />

	<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="insultAssetLinksPanel" persistState="<%= true %>" title="related-assets">
	    <aui:fieldset>
	        <liferay-ui:input-asset-links 
   				className="<%=CaseInstance.class.getName()%>"
   				classPK="<%=(caseInst!=null) ? caseInst.getPrimaryKey() : 0%>" />
	    </aui:fieldset>
	</liferay-ui:panel>
	
	<liferay-ui:asset-categories-error />
       <liferay-ui:asset-tags-error />
       <liferay-ui:panel defaultState="closed" extended="<%= false %>" id="caseInstanceCategorizationPanel" persistState="<%= true %>" title="categorization">
               <aui:fieldset>
               			<aui:input name="categories" type="assetCategories" />
               
                       <aui:input name="tags" type="assetTags" />
               </aui:fieldset>
       </liferay-ui:panel>
      
      	<aui:button-row>
                      <aui:button type="submit" />
                      <aui:button type="cancel" onClick="<%= fullViewURL %>"></aui:button>
      	</aui:button-row>
      
</aui:form>