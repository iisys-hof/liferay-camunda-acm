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
<%@ page import="java.text.SimpleDateFormat" %>

<%
	String currentURL = PortalUtil.getCurrentURL(renderRequest);

	HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(
			PortalUtil.getHttpServletRequest(renderRequest)); 
	String caseInstanceId = httpRequest.getParameter("caseInstanceId");
	String caseDefinitionKey = httpRequest.getParameter("caseDefinitionKey");
	String caseCreateTime = httpRequest.getParameter("caseCreateTime");
	
	
	CaseInstance caseInst = null;
	AssetEntry assetEntry = null;
	
	try {
		caseInst = CaseInstanceLocalServiceUtil.findCaseInstanceById(caseInstanceId);
		assetEntry = AssetEntryLocalServiceUtil.getEntry(CaseInstance.class.getName(), caseInst.getPrimaryKey());
	} catch(PortalException e) {
		System.out.println("No case instance found in view.jsp ("+caseInstanceId+")");
	}
	
	if(caseInstanceId==null) caseInstanceId = "";
	if(caseDefinitionKey==null) caseDefinitionKey = "";
	if(caseCreateTime==null) caseCreateTime = "";
%>

<div class="portlet-msg-success" style="display:none;" id="<portlet:namespace/>successMsg"></div>

<c:choose>

<c:when test="<%= caseInstanceId.isEmpty() %>">
	<div style="width:49%; float:left;">
		<liferay-ui:header title="de.iisys.ACMCases.deployedCases" />
		<div id="<portlet:namespace/>definitions"></div>
		<div class="pagination pagination-mini pull-right">
			<ul id="<portlet:namespace/>caseDefinitions-pages" class="pagination-content"></ul>
		</div>
	</div>
	
	<div style="width:49%; float:right;">
		<liferay-ui:header title="de.iisys.ACMCases.instances" />
		<p><liferay-ui:message key="de.iisys.ACMCases.instancesOf" /> <span id="<portlet:namespace/>instancesOfDefinition"></span></p>
		<div id="<portlet:namespace/>instances"></div>
		<div class="pagination pagination-mini pull-right">
			<ul id="<portlet:namespace/>caseInstances-pages" class="pagination-content"></ul>
		</div>
	</div>
</c:when>

<c:otherwise>
	<portlet:renderURL var="viewURL" />
	<liferay-ui:header title="de.iisys.ACMCases.caseInstance" backURL="<%= viewURL %>" />
	
	<div style="width:49%; float:left;">
		<aui:button type="submit" cssClass="pull-right upper-button" value="de.iisys.ACMCases.showCMMNModel" primary="<%= true %>" icon="icon-th" />
		<p><liferay-ui:message key="de.iisys.ACMCases.instanceOfDef" /> <strong><span id="<portlet:namespace/>instancesOfDefinition"></span></strong></p>
		<div style="clear:right;" id="<portlet:namespace/>singleInstance"></div>
	</div>
	
	<div style="width:49%; float:right;">
		
		<portlet:renderURL var="editCaseInstanceMeta">
			<portlet:param name="caseInstanceId" value="<%= caseInstanceId %>" />
			<portlet:param name="mvcPath" value="/html/caseOverview/edit.jsp" />
		</portlet:renderURL>
		
		<% String fullEditCaseInstanceMetaURL = editCaseInstanceMeta + "&caseInstanceId="+caseInstanceId + "&caseDefinitionKey="+caseDefinitionKey + "&caseCreateTime="+caseCreateTime; %>
	     
	     <aui:button href="<%= fullEditCaseInstanceMetaURL %>" cssClass="pull-left upper-button-left" value="de.iisys.ACMCases.editMeta" primary="<%= false %>" icon="icon-edit" />   
        
        <p style="padding-top:5px;">
			<liferay-ui:asset-tags-summary
			    className="<%= CaseInstance.class.getName() %>"
			    classPK="<%= (caseInst!=null) ? caseInst.getPrimaryKey() : 0 %>" />
		</p>
			
		
		<div  style="clear:left;" id="<portlet:namespace/>mainCasePlanModel"></div>
		
	   <liferay-ui:asset-categories-summary 
	   		classPK="<%= (caseInst!=null) ? caseInst.getPrimaryKey() : 0 %>" 
	   		className="<%= CaseInstance.class.getName() %>" />
	        
        <liferay-ui:asset-links
			assetEntryId="<%=(assetEntry != null) ? assetEntry.getEntryId() : 0%>"
			className="<%=CaseInstance.class.getName()%>"
			classPK="<%=(caseInst!=null) ? caseInst.getPrimaryKey() : 0 %>" />
        
	</div>
</c:otherwise>

</c:choose>

<div style="clear:both;"></div>

<div id="<portlet:namespace/>loader"></div>



<script type="text/javascript">

	var <portlet:namespace/>CASES_PER_PAGE = 10;

	var <portlet:namespace/>LIFERAY_URL = '<%= PortletProps.get("liferay_url") %>';
	var <portlet:namespace/>NUXEO_URL = '<%= PortletProps.get("nuxeo_url") %>';
	
	var <portlet:namespace/>CASE_INSTANCE_ID = '<%= caseInstanceId %>';
	var <portlet:namespace/>CASE_DEFINITION_KEY = '<%= caseDefinitionKey %>';
	var <portlet:namespace/>CASE_CREATE_TIME = '<%= caseCreateTime %>';
	
	// temp:
	var <portlet:namespace/>curCaseDefinitions = 0;
	var <portlet:namespace/>curCaseInstances = 0;
	
	var <portlet:namespace/>curCaseDefinitionId;
	var <portlet:namespace/>curCaseDefinitionKey;
	
	// control:
	
	function <portlet:namespace/>init() {
		if(<portlet:namespace/>CAMUNDA_REST_ENGINE==null || <portlet:namespace/>CAMUNDA_REST_ENGINE=='')
			<portlet:namespace/>CAMUNDA_REST_ENGINE = <portlet:namespace/>CAMUNDA_ENGINE;
		
		if(<portlet:namespace/>CASE_INSTANCE_ID=="")
			<portlet:namespace/>getCaseDefinitions();
		else {
			<portlet:namespace/>getSingleCaseInstance(<portlet:namespace/>CASE_INSTANCE_ID, <portlet:namespace/>CASE_DEFINITION_KEY);
			<portlet:namespace/>getMainCasePlanModel(<portlet:namespace/>CASE_INSTANCE_ID)
		}
	}
	
	// control (GET):
	
	function <portlet:namespace/>getCaseDefinitions() {
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/case-definition";
		if(<portlet:namespace/>CAMUNDA_VERSION > 7.2)
			url += "?latestVersion=true";
		else
			url += "?latest=true";
		url += '&firstResult='+<portlet:namespace/>curCaseDefinitions
			+  '&maxResults='+<portlet:namespace/>CASES_PER_PAGE;
		
		<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>showCaseDefinitions);
	}
	 /* OLD: does not use "/history". therefore cannot show createTime
	function <portlet:namespace/>getCaseInstances(caseDefinitionId, caseDefinitionKey) {
		if(!caseDefinitionId)
			var caseDefinitionId = <portlet:namespace/>curCaseDefinitionId;
		else
			<portlet:namespace/>curCaseDefinitionId = caseDefinitionId;
			
		if(!caseDefinitionKey)
			var caseDefinitionKey = <portlet:namespace/>curCaseDefinitionKey;
		else
			<portlet:namespace/>curCaseDefinitionKey = caseDefinitionKey;
		
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/case-instance"
				+ '?caseDefinitionKey='+caseDefinitionKey
				+ '&sortBy=caseDefinitionId'
				+ '&sortOrder=desc'
				+ '&firstResult='+<portlet:namespace/>curCaseInstances
				+ '&maxResults='+<portlet:namespace/>CASES_PER_PAGE;
		
		<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>showCaseInstances, null, caseDefinitionKey);
		<portlet:namespace/>showInstanceOfDefinitionDetails(caseDefinitionId, caseDefinitionKey);
	}
	*/
	
	function <portlet:namespace/>getCaseInstances(caseDefinitionId, caseDefinitionKey) {
		if(!caseDefinitionId)
			var caseDefinitionId = <portlet:namespace/>curCaseDefinitionId;
		else
			<portlet:namespace/>curCaseDefinitionId = caseDefinitionId;
			
		if(!caseDefinitionKey)
			var caseDefinitionKey = <portlet:namespace/>curCaseDefinitionKey;
		else
			<portlet:namespace/>curCaseDefinitionKey = caseDefinitionKey;
		
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/history/case-instance"
				+ '?caseDefinitionKey='+caseDefinitionKey
				+ '&notClosed=true'
				+ '&sortBy=createTime'
				+ '&sortOrder=desc'
				+ '&firstResult='+<portlet:namespace/>curCaseInstances
				+ '&maxResults='+<portlet:namespace/>CASES_PER_PAGE;
		// sic: sortBy=definitionId and NOT caseDefinitionId here
		
		<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>showCaseInstances, null, caseDefinitionKey);
		<portlet:namespace/>showInstanceOfDefinitionDetails(caseDefinitionId, caseDefinitionKey);
	}
	
	function <portlet:namespace/>getSingleCaseInstance(caseInstanceId, caseDefinitionKey) {
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/case-instance"
			+ '/'+caseInstanceId;

		<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>showSingleCaseInstance, null, caseDefinitionKey);
	}
	
	function <portlet:namespace/>getMainCasePlanModel(caseInstanceId) {
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/case-execution"
			+ '/'+caseInstanceId;

		<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>showMainCasePlanModel);
	}
	
	function <portlet:namespace/>getDefinitionsCount() {
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/case-definition/count";
		if(<portlet:namespace/>CAMUNDA_VERSION > 7.2)
			url += "?latestVersion=true";
		else
			url += "?latest=true";

		<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>useCountForUpdatePaginationView, null, 'caseDefinitions');
	}
	
	function <portlet:namespace/>getInstancesCount(caseDefinitionKey, isActive, optTarget) {
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/case-instance/count"
				+ '?caseDefinitionKey='+caseDefinitionKey;
		if(isActive===true) {
			url += '&active=true';
			<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>showRunningInstancesCount, null, caseDefinitionKey);
		} else {
			<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>useCountForUpdatePaginationView, null, optTarget);
		}
	}
	
	// control (POST):
		
	function <portlet:namespace/>createCaseInstance(caseDefinitionId) {
		var businessKey = document.getElementById('<portlet:namespace/>'+'createDoc-'+caseDefinitionId).value;
		
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/case-definition/"
				+ caseDefinitionId+"/"
				+ "create";
	}
	
	// view (callback):
	
	function <portlet:namespace/>showCaseDefinitions(data) {
		if(data) {

			<portlet:namespace/>getDefinitionsCount();
			
			var html = '<table class="table table-bordered table-condensed">'
					+'<thead><tr>'
						+'<th>Name</th>'
						+'<th>Latest Version</th>'
						+'<th>Running Instances</th>'
						+'<th>Actions</th>'
					+'</tr></thead>'
					+'<tbody>';
					
			for(var i=0; i<data.length; i++) {
				if(data[i].name != null)
					var name = data[i].name+' ('+data[i].key+')';
				else
					var name = data[i].key;
				
				html += '<tr';
				if(i===0) html += ' class="highlighted"';
				html += '>'
						+'<td>'+name+'</td>'
						+'<td>'+data[i].version+'</td>'
						+'<td>'+'<span id="<portlet:namespace/>running-'+data[i].key+'">?</span> '
	//						+'(<a href="#" onclick="'
	//								+'<portlet:namespace/>getCaseInstances(\''+data[i].id+'\', \''+data[i].key+'\'); <portlet:namespace/>highlightThisRow(this.parentNode.parentNode);">'
	//								+'<liferay-ui:message key="de.iisys.ACMCases.showAll" />'+'</a>)'
						+'</td>'
						+'<td>'
							+'<button class="btn" type="submit"> <i class="icon-th"></i> <liferay-ui:message key="de.iisys.ACMCases.showModel" /> </button>'
							+' <button class="btn btn-primary" onclick="'
									+'<portlet:namespace/>getCaseInstances(\''+data[i].id+'\', \''+data[i].key+'\'); <portlet:namespace/>highlightThisRow(this.parentNode.parentNode);" >'
								+' <liferay-ui:message key="de.iisys.ACMCases.showInstances" /> </button>'
						+'</td>'
					+'</tr>';
			}		
					
			html += '</tbody></table>';
			document.getElementById('<portlet:namespace/>'+'definitions').innerHTML = html;
			
			// add running instances:
			for(var j=0; j<data.length; j++) {
				<portlet:namespace/>getInstancesCount(data[j].key, true);
			}
			
			<portlet:namespace/>getCaseInstances(data[0].id, data[0].key);
		}
	}
	
	function <portlet:namespace/>showCaseInstances(data, caseDefinitionKey) {
		if(data) {

			<portlet:namespace/>getInstancesCount(caseDefinitionKey, false, 'caseInstances');
			
			var html = '<table class="table table-bordered table-condensed">'
					+'<thead><tr>'
						+'<th>Instance Start <i class="icon-caret-down"></i></th>'
						+'<th>Related To</th>'
						+'<th>Status</th>'
						+'<th>Case Version</th>'
					+'</tr></thead>'
					+'<tbody>';
			
			var createTime = null;
			for(var i=0; i<data.length; i++) {
				if(data[i].createTime)
					createTime = data[i].createTime;
				html += <portlet:namespace/>getInstanceTableRow(data[i].id, data[i].caseDefinitionId, caseDefinitionKey, createTime, data[i].active, data[i].completed, data[i].terminated, data[i].businessKey, true);
			}		
					
			html += '</tbody></table>';
			document.getElementById('<portlet:namespace/>'+'instances').innerHTML = html;
		}
	}
	
	function <portlet:namespace/>showSingleCaseInstance(data, caseDefinitionKey) {
		if(data) {			
			var html = '<table class="table table-bordered table-condensed">'
					+'<thead><tr>'
						+'<th>Instance Start</th>'
						+'<th>Related To</th>'
						+'<th>Status</th>'
						+'<th>Case Version</th>'
					+'</tr></thead>'
					+'<tbody>';
								
			html += <portlet:namespace/>getInstanceTableRow(data.id, data.caseDefinitionId, caseDefinitionKey, <portlet:namespace/>CASE_CREATE_TIME, data.active, data.completed, data.terminated, data.businessKey, false);	
					
			html += '</tbody></table>';
			
/*			var html = '<p>'
				+ '<strong>Id</strong> '+data.id+'<br />'
				+ '<strong>Case Definition Id</strong> '+'<span id="<portlet:namespace/>instancesOfDefinition"></span>'+'<br />'
				+ '<strong>Active</strong> '+data.active+'<br />'
				+ '<strong>Completed</strong> '+data.completed
				+ '</p>';
			*/
			
			document.getElementById('<portlet:namespace/>'+'singleInstance').innerHTML = html;
				
			<portlet:namespace/>showInstanceOfDefinitionDetails(data.caseDefinitionId, caseDefinitionKey);
		}
	}
	
	function <portlet:namespace/>showMainCasePlanModel(data) {
		if(data) {
			var desc = data.activityDescription
			if(desc===null) desc = "-";
			
			var html = '<table class="table table-bordered table-condensed">'
					+'<thead><tr>'
						+'<th>Main CasePlanModel</th>'
						+'<th>Description</th>'
					+'</tr></thead>'
					+'<tbody>';
								
			html += '<tr>'+
					'<td>'+data.activityName+'</td>'+
					'<td>'+desc+'</td>'+
					'</tr>';
				
			html += '</tbody></table>';
			
			document.getElementById('<portlet:namespace/>'+'mainCasePlanModel').innerHTML = html;
		}
	}
	
	function <portlet:namespace/>showRunningInstancesCount(data, caseDefinitionKey) {
		if(data)
			document.getElementById('<portlet:namespace/>'+'running-'+caseDefinitionKey).innerHTML = data.count;
	}
	
	function <portlet:namespace/>useCountForUpdatePaginationView(data, targetBox) {
		var tempCur = 0;
		switch(targetBox) {
			case "caseDefinitions":
				tempCur = <portlet:namespace/>curCaseDefinitions; break;
			case "caseInstances":
				tempCur = <portlet:namespace/>curCaseInstances; break;
		}
		console.log(targetBox+": "+data.count);
		
		<portlet:namespace/>updatePaginationView(targetBox,data.count,tempCur);
	}
	
	
	// view:
		
	function <portlet:namespace/>showInstanceOfDefinitionDetails(caseDefinitionId, caseDefinitionName) {
		var html = "<strong>"+caseDefinitionName+"</strong>.";
		document.getElementById('<portlet:namespace/>'+'instancesOfDefinition').innerHTML = html;
	}
	
	function <portlet:namespace/>getInstanceTableRow(id, caseDefinitionId, caseDefinitionKey, createTime, active, completed, terminated, businessKey, showShortURL) {
		var curURL = '<%= currentURL %>';
		if(curURL.indexOf("?")===-1)
			curURL += '?';
		else {
			curURL = curURL.substring(0, curURL.indexOf("?")) + '?';
		}
		
		var idShort = id;
		if(createTime) {
			idShort = <portlet:namespace/>displayTime(createTime);
		} else if(showShortURL && idShort.length > 10) {
			idShort = idShort.substring(0,8)+'...';
		}

		
		if(businessKey==null)
			businessKey = '<em>&lt;documentName&gt;</em>';
		else
			businessKey = '<a href="'+<portlet:namespace/>NUXEO_URL+'/'+businessKey+'">'
				+ businessKey + '</a>';
		
		var html = '<tr';
		if(active===true) html += ' class="active"';
		html += '>'
				+'<td><a href="'+curURL+'caseInstanceId='+id+'&caseDefinitionKey='+caseDefinitionKey+
					'&caseCreateTime='+createTime+
					'" title="'+id+'">'+idShort+'</a></td>'
				+'<td>'+businessKey+'</td>'
				+'<td>';
		if(active===true)
			html += '<i class="icon-ok" style="color:#333;"></i> active';
		else if(completed===true)
			html += '<i class="icon-ok" style="color:green;"></i> completed';
		else if(terminated===true)
			html += '<i class="icon-remove" style="color:red;"></i> terminated';
				
		html += '</td>'
				+'<td>'+<portlet:namespace/>getVersionFromCaseDefinitionId(caseDefinitionId)+'</td>'
			+'</tr>';
			
		return html;
	}
	
	function <portlet:namespace/>highlightThisRow(rowElement) {
		var otherRows = rowElement.parentNode.getElementsByTagName("tr");
		for(var i=0; i<otherRows.length; i++) {
			otherRows[i].className = "";
		}
		
		rowElement.className += "highlighted";
		
		return false;
	}
	
	function <portlet:namespace/>showCreateInstance(caseDefinitionId, buttonElement) {
		buttonElement.disabled = "disabled";
		document.getElementById('<portlet:namespace/>'+'createInst-'+caseDefinitionId).style.display = 'block';
	}
	
	// helper:
	
	/**
	caseDefinitionId hast to be in the manner <caseDefinitionKey>:<version>:<distinct Id>
	*/
	function <portlet:namespace/>getVersionFromCaseDefinitionId(caseDefinitionId) {
		var firstColon = caseDefinitionId.indexOf(":");
		
		if(firstColon===-1)
			return "?";
		else
			return caseDefinitionId.substring(firstColon+1,firstColon+2);
	}
	
	
	// pagination:
	
	function <portlet:namespace/>updatePaginationView(targetBox,totalCases,curCaseStart) {
		var totalPages = Math.ceil( totalCases / <portlet:namespace/>CASES_PER_PAGE );
		var curPage = Math.ceil( (curCaseStart+1) / <portlet:namespace/>CASES_PER_PAGE );
		
		var THREE_DOTS = '<li class="disabled"><a href="#" onclick="return false;">...</a></li>';

		
		// page "prev":
		var temp = '<li'; 
		if(curPage===1) temp += ' class="disabled"';
		temp += '><a href="#" onclick="';
		if(curPage > 1) temp += "<portlet:namespace/>prevPage('" +targetBox+ "'); ";
		temp+= 'return false;" class="icon-chevron-left"></a></li>';
		// page 1:
		temp += '<li';
		if(curPage===1) temp += ' class="active"';
		temp += '><a href="#" onclick="';
		if(curPage!=1) temp += '<portlet:namespace/>changePage(\''+targetBox+'\',1); ';
		temp += 'return false;">1</a></li>';
		// three dots:
		if(curPage > 2) temp += THREE_DOTS;
		// current page:
		if(curPage > 1  &&  curPage < totalPages)
			temp += '<li class="active"><a href="#" onclick="return false;">'+curPage+'</a></li>';
		// three dots:
		if(curPage < (totalPages-1)) temp += THREE_DOTS;
		// last page:
		if(totalPages > 1) {
			temp += '<li';
			if(curPage===totalPages) temp += ' class="active"';
			temp += '><a href="#" onclick="';
			if(curPage!=totalPages) temp += '<portlet:namespace/>changePage(\''+targetBox+'\','+totalPages+'); ';
			temp += 'return false;">'+totalPages+'</a></li>';
		}
		// page "next":
		temp += '<li';
		if(totalPages<=curPage) temp += ' class="disabled"';
		temp += '><a href="#" onclick="';
		if(curPage < totalPages) temp += '<portlet:namespace/>nextPage(\''+targetBox+'\'); ';
		temp += 'return false;" class="icon-chevron-right"></a></li>';
		
		document.getElementById('<portlet:namespace/>'+targetBox+'-pages').innerHTML = temp;
	}
	
	function <portlet:namespace/>nextPage(targetBox) {
		<portlet:namespace/>changePage(targetBox,"+1");
	}
	function <portlet:namespace/>prevPage(targetBox) {
		<portlet:namespace/>changePage(targetBox,"-1");
	}
	function <portlet:namespace/>changePage(targetBox,pageNr) {
		var tempCur;
		
		switch(targetBox) {
			case "caseDefinitions":
				tempCur = <portlet:namespace/>curCaseDefinitions; break;
			case "caseInstances":
				tempCur = <portlet:namespace/>curCaseInstances; break;
		}
		
		switch(pageNr) {
			case "+1":
				tempCur += <portlet:namespace/>CASES_PER_PAGE;
				break;
			case "-1":
				tempCur -= <portlet:namespace/>CASES_PER_PAGE;
				if(tempCur < 0) tempCur = 0;
				break;
			default:
				tempCur = (pageNr-1) * <portlet:namespace/>CASES_PER_PAGE;
		}
		
		switch(targetBox) {
			case "caseDefinitions":
				<portlet:namespace/>curCaseDefinitions = tempCur;
				<portlet:namespace/>getCaseDefinitions();
				break;
			case "caseInstances":
				<portlet:namespace/>curCaseInstances = tempCur;
				<portlet:namespace/>getCaseInstances();
				break;
		}
	}
		
	// start:
	<portlet:namespace/>init();
	
</script>