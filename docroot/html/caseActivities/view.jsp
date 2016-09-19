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
<%@include file="/html/username.jsp" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>

<%
	String curUser = user.getScreenName();

	String currentURL = PortalUtil.getCurrentURL(renderRequest);

	HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(
			PortalUtil.getHttpServletRequest(renderRequest)); 
	String caseInstanceId = httpRequest.getParameter("caseInstanceId");
	if(caseInstanceId==null) caseInstanceId = "";
	
	String tabNames = LanguageUtil.get(locale, "de.iisys.ACMActivities.taskOverview")
			+ "," + LanguageUtil.get(locale, "de.iisys.ACMActivities.userTasks")
			+ "," + LanguageUtil.get(locale, "de.iisys.ACMActivities.milestones")
			+ "," + LanguageUtil.get(locale, "de.iisys.ACMActivities.variables");
%>

<div class="portlet-msg-success" style="display:none;" id="<portlet:namespace/>successMsg"></div>

<c:choose>

<c:when test="<%= caseInstanceId.isEmpty() %>">
	<p><liferay-ui:message key="de.iisys.ACMActivities.choose" />.</p>
</c:when>

<c:otherwise>
	<liferay-ui:tabs names="<%= tabNames %>" refresh="false">
		<liferay-ui:section>
			<div id="<portlet:namespace/>taskOverview"></div>
		</liferay-ui:section>
		
		<liferay-ui:section>
			<div id="<portlet:namespace/>userTasks"></div>
		</liferay-ui:section>
		
		<liferay-ui:section>
			<div id="<portlet:namespace/>milestones"></div>
		</liferay-ui:section>
		
		<liferay-ui:section>
			<div id="<portlet:namespace/>variables"></div>
		</liferay-ui:section>
	</liferay-ui:tabs>
</c:otherwise>

</c:choose>

<div id="<portlet:namespace/>loader"></div>


<aui:script>
	var <portlet:namespace/>LIFERAY_URL = '<%= PortletProps.get("liferay_url") %>';
	var <portlet:namespace/>LIFERAY_PROFILE_URL = '<%= PortletProps.get("liferay_profile_url") %>';
	
	var <portlet:namespace/>CASE_INSTANCE_ID = '<%= caseInstanceId %>';
	
	var <portlet:namespace/>CUR_USER = '<%= curUser %>';
	
	
	var <portlet:namespace/>claimedTask = "";
	
	// control:
	
	function <portlet:namespace/>init() {
		if(<portlet:namespace/>CAMUNDA_REST_ENGINE==null || <portlet:namespace/>CAMUNDA_REST_ENGINE=='')
			<portlet:namespace/>CAMUNDA_REST_ENGINE = <portlet:namespace/>CAMUNDA_ENGINE;
		
		if(<portlet:namespace/>CASE_INSTANCE_ID!="") {
			<portlet:namespace/>getInstanceActivities(<portlet:namespace/>CASE_INSTANCE_ID);
			<portlet:namespace/>getInstanceUserTasks(<portlet:namespace/>CASE_INSTANCE_ID);
			<portlet:namespace/>getInstanceVariables(<portlet:namespace/>CASE_INSTANCE_ID);
		}
	}
	
	// control (GET):
	
	function <portlet:namespace/>getInstanceActivities(caseInstanceId) {
//		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/case-execution"
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/history/case-activity-instance"
				+ '?caseInstanceId='+caseInstanceId;
		
		<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>showInstanceActivities);
	}
	
	function <portlet:namespace/>getInstanceUserTasks(caseInstanceId) {
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/history/task"
				+ '?caseInstanceId='+caseInstanceId;
		
		<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>showInstanceUserTasks);
	}
	
	function <portlet:namespace/>getInstanceVariables(caseInstanceId) {
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/case-execution"
				+ "/"+caseInstanceId + "/variables";
		
		<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>showInstanceVariables);
	}
	
	function <portlet:namespace/>getTaskCandidates(taskId) {
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/task"
			+ "/"+taskId + "/identity-links";

		<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>showTaskCandidates, null, taskId);
	}
	
	// control (POST):
		
	function <portlet:namespace/>claimTask(taskId) {
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/task"
				+ "/"+taskId
				+ '/claim';
		var requestBody =  {
				"userId" : <portlet:namespace/>CUR_USER
			};
		
		<portlet:namespace/>claimedTask = taskId;
		<portlet:namespace/>sendAsyncRequest('POST', url, <portlet:namespace/>showClaimSuccess, requestBody);
	}
	
	function <portlet:namespace/>unclaimTask(taskId) {
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/task"
				+ "/"+taskId
				+ '/unclaim';
		
		<portlet:namespace/>claimedTask = taskId;
		<portlet:namespace/>sendAsyncRequest('POST', url, <portlet:namespace/>showUnclaimSuccess);
	}
	
	
	// view (callback):
		
	function <portlet:namespace/>showInstanceActivities(data) {
		if(data) {
			var html = '<table class="table table-bordered table-condensed">'
					+'<thead><tr>'
						+'<th>Name</th>'
//						+'<th><liferay-ui:message key="de.iisys.ACMActivities.Description" /></th>'
						+'<th><liferay-ui:message key="de.iisys.ACMActivities.Type" /></th>'
						+'<th><liferay-ui:message key="de.iisys.ACMActivities.Completed" /></th>'
					+'</tr></thead>'
					+'<tbody>';
			
			var htmlMilestones = html;
			var milestonesCount = 0;
					
			for(var i=0; i<data.length; i++) {
				var status = "active";
				if(data[i].completed===true)
					status = "completed";
				else if(data[i].terminated===true)
					status = "terminated";
				else if(data[i].active===false) {
					if(data[i].caseActivityType==="milestone")
						status = "open";
					else
						status = "pending";
				}

				
				switch(data[i].caseActivityType) {
					case("humanTask"): var type = '<liferay-ui:message key="de.iisys.ACMActivities.humanTask" />'; break;
					case("milestone"): var type = '<liferay-ui:message key="de.iisys.ACMActivities.milestone" />'; break;
					default: var type = data[i].caseActivityType;
				}
				
//				var tempRow = <portlet:namespace/>getInstanceTableRow(data[i].id, data[i].activityName, data[i].activityDescription, data[i].activityType, data[i].active);
				var tempRow = <portlet:namespace/>getInstanceTableRow(data[i].id, data[i].caseActivityName, '', type, status, data[i].endTime);
				html += tempRow;
				if(data[i].caseActivityType==="milestone") {
					htmlMilestones+= tempRow;
					milestonesCount++;
				}
			}	
					
			html += '</tbody></table>';
			
			if(milestonesCount==0)
				htmlMilestones += '<tr><td  colspan="4">'+'<i class="icon-ok" style="color:green;"></i> <liferay-ui:message key="de.iisys.ACMActivities.noMilestones" />'+'</td></tr>';
			htmlMilestones += '</tbody></table>';
			
			// taskOverview:
			document.getElementById('<portlet:namespace/>'+'taskOverview').innerHTML = html;
			
			// milstones:
			document.getElementById('<portlet:namespace/>'+'milestones').innerHTML = htmlMilestones;
		}
	}
	
	function <portlet:namespace/>showInstanceUserTasks(data) {
		if(data) {
			var html = '<table class="table table-bordered table-condensed">'
					+'<thead><tr>'
						+'<th>Name</th>'
						+'<th>Created</th>'
						+'<th><liferay-ui:message key="de.iisys.ACMActivities.Candidates" /></th>'
						+'<th style="text-align:center;"><i class="icon-ok" style="color:#333;"></i></th>'
						+'<th>Actions</th>'
					+'</tr></thead>'
					+'<tbody>';
					
			for(var i=0; i<data.length; i++) {			
				html += <portlet:namespace/>getUserTasksTableRow(data[i].id, data[i].name, data[i].description, data[i].assignee, data[i].startTime, data[i].endTime, data[i].due);
			}
			
			html += '<tr><td colspan="7" style="text-align:center; padding:12px 0;"> '
				+'<a class="btn btn-primary" href="'+<portlet:namespace/>CAMUNDA_URL +"/camunda/app/tasklist/"+ <portlet:namespace/>CAMUNDA_ENGINE+'/" target="_blank"> Show Tasklist </a>'
				+' </td></tr>';
					
			html += '</tbody></table>';
			document.getElementById('<portlet:namespace/>'+'userTasks').innerHTML = html;
			
			// add candidates:
			for(var j=0; j<data.length; j++) {
				if(data[j].assignee!=null)	// add user fullnames:
					<portlet:namespace/>getUserFullNameFromServer(data[j].assignee);
				else 						// add candidates:
					<portlet:namespace/>getTaskCandidates(data[j].id);
			}
		}
	}
	
	function <portlet:namespace/>showInstanceVariables(data) {
		if(data) {			
			var html = '<table class="table table-bordered table-condensed">'
					+'<thead><tr>'
						+'<th>Variable</th>'
						+'<th><liferay-ui:message key="de.iisys.ACMActivities.value" /></th>'
					+'</tr></thead>'
					+'<tbody>';
					
			var keys = Object.keys(data);			
			for(var i=0; i<keys.length; i++) {
				if(keys[i].indexOf('expert-')===-1) {
					html += '<tr>'+'<td>'+keys[i]+'</td>'
						+'<td>'+data[keys[i]].value+'</td>'
						+'</tr>';
				}
			}
					
			html += '</tbody></table>';
			document.getElementById('<portlet:namespace/>'+'variables').innerHTML = html;
		}
	}
	
	function <portlet:namespace/>showTaskCandidates(data, taskId) {
		if(data) {
			var html = "";
			var groups = [];
			var users = [];
			
			for(var i=0; i<data.length; i++) {
				if(data[i].type==="candidate") {
					if(data[i].userId!=null)
						users.push(data[i].userId);
					else if(data[i].groupId!=null)
						groups.push(data[i].groupId);		
				}
			}
			
			for(var j=0; j<groups.length; j++) {
				if(j>0)
					html += ', ';
				else
					html += 'Group: ';
				
				html += groups[j];
				console.log(groups[j]+', id: '+taskId);
			}
			for(var k=0; k<users.length; k++) {
				if(k>0)
					html += ', ';
				else if(j>0)
					html += '; Users: ';

				html += '<a href="'+<portlet:namespace/>LIFERAY_PROFILE_URL+users[k]+'">'
							+ '<span class="<portlet:namespace/>user-'+users[k]+'">'+users[k]+'</span></a>';
			}
			
			if(i>0) {
				document.getElementById('<portlet:namespace/>'+'candidates-'+taskId).innerHTML = html;
				
				for(var l=0; l<users.length; l++) {
					<portlet:namespace/>getUserFullNameFromServer(users[l]);
				}
			}
				
			
			
		}
	}
	
	function <portlet:namespace/>showClaimSuccess(data) {
		console.log("Successful claim.");
		var successElement = document.getElementById('<portlet:namespace/>'+'successMsg');
		
		successElement.className = 'portlet-msg-success';
		successElement.innerHTML = '<liferay-ui:message key="de.iisys.ACMActivities.claimSuccess" />';
		successElement.style.display = "block";
		
		document.getElementById('<portlet:namespace/>'+'assignee-task_'+<portlet:namespace/>claimedTask).innerHTML = <portlet:namespace/>CUR_USER;
	}
	
	function <portlet:namespace/>showUnclaimSuccess(data) {
		var successElement = document.getElementById('<portlet:namespace/>'+'successMsg');
		
		successElement.className = 'portlet-msg-success';
		successElement.innerHTML = '<liferay-ui:message key="de.iisys.ACMActivities.unclaimSuccess" />';
		successElement.style.display = "block";
		
		document.getElementById('<portlet:namespace/>'+'assignee-task_'+<portlet:namespace/>claimedTask).innerHTML = "-";
	}
	
	function <portlet:namespace />showFullNameInHtml(userId, fullName) {
		var users = document.getElementById('<portlet:namespace/>userTasks').getElementsByClassName("<portlet:namespace/>user-"+userId);
		for(var i=0; i<users.length; i++) {
			var firstName = fullName.substring(fullName.indexOf(' ')+1, fullName.length);
			users[i].innerHTML = firstName;
			users[i].parentNode.title = fullName;
		}
	}
	
	
	// view:
		
	function <portlet:namespace/>getInstanceTableRow(id, name, desc, type, status, endTime) {
		switch(status) {
		case("completed"):
			name = "<em>"+name+"</em>";
			if(endTime!=null)
				status = '<i class="icon-ok" style="color:green;"></i> '+<portlet:namespace/>displayTime(endTime);
			else
				status = '<i class="icon-ok" style="color:green;"></i> '+'<liferay-ui:message key="de.iisys.ACMActivities.completed" />';
			break;
		case("terminated"):
			name = "<em>"+name+"</em>";
			status = '<i class="icon-ok" style="color:red;"></i> '+'<liferay-ui:message key="de.iisys.ACMActivities.terminated" />';
			break;
		case("pending"):
			name = "<em>"+name+"</em>";
			status = '<liferay-ui:message key="de.iisys.ACMActivities.waiting" />';
			break;
		default: status = '<liferay-ui:message key="de.iisys.ACMActivities.open" />';
		}
		
		var html = '<tr>'
			+'<td><span title="'+id+'">'+name+'</span></td>'
			+'<td>'+type+'</td>'
			+'<td>'+status+'</td>'
		+'</tr>';
			
		return html;
	}
	
	function <portlet:namespace/>getUserTasksTableRow(id, name, desc, assignee, startTime, endTime, due) {
		var completed = "";
		if(endTime!=null) {
			name = '<em>'+name+'</em>';
			completed = '<i class="icon-ok" style="color:green;"></i> '+<portlet:namespace/>displayTime(endTime);
		} else if(due!=null) {
			completed = '<liferay-ui:message key="de.iisys.ACMActivities.due" />: '+<portlet:namespace/>displayTime(due);
		} else {
			completed = '<liferay-ui:message key="de.iisys.ACMActivities.open" />';
		}
		
		
		var html = '<tr>'
				+'<td>';
		if(endTime!=null)
			html+='<span title="'+id+'">';
		else
			html+='<a title="'+id+'" href="'+<portlet:namespace/>CAMUNDA_URL+<portlet:namespace/>CAMUNDA_TASKLIST_FRAG+'?task='+id+'" target="_blank">';
		html += name;
		if(endTime!=null)
			html+='</span>';
		else
			html+='</a>';
		html += '</td>'
				+'<td>'+<portlet:namespace/>displayTime(startTime)+'</td>'
				+'<td>';
			if(assignee!=null) {
				html += '<liferay-ui:message key="de.iisys.ACMActivities.assignee" />: <a href="'+<portlet:namespace/>LIFERAY_PROFILE_URL+assignee+'">'
					+ '<span class="<portlet:namespace/>user-'+assignee+'">'+assignee+'</span></a>';
			} else {
				html += '<span id="<portlet:namespace/>candidates-'+id+'"> - </span>';
			}
			html += '</td>'
				+'<td>'+completed+'</td>'
				+'<td>';
		if(assignee==<portlet:namespace/>CUR_USER && endTime==null) {
			html += '<button class="btn" '
				+' onclick="'+'<portlet:namespace/>unclaimTask(\''+id+'\'); return false;">'
				+' <liferay-ui:message key="de.iisys.ACMActivities.unclaim" /> </button>'
		} else {
		html +=	'<button class="btn btn-primary" '
					+' onclick="'+'<portlet:namespace/>claimTask(\''+id+'\'); return false;"';
		if(assignee!=null || endTime!=null) html += ' disabled="disabled"';
		html 		+= '> <liferay-ui:message key="de.iisys.ACMActivities.claim" /> </button>';
		}
		
		html += '</td>'+'</tr>';
		
		
		// description row:
		/*
		if(desc!=null) {
			html += '<tr><td colspan="5">'+desc+'</td></tr>';
		}
		*/
			
		return html;
	}
	
	
	// start:
	<portlet:namespace/>init();
	
</aui:script>