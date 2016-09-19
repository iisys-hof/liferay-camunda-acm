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

<%@include file="/html/caseStakeholders/init.jsp" %>
<%@include file="/html/username.jsp" %>

<%
	String currentURL = PortalUtil.getCurrentURL(renderRequest);
	
	HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(
			PortalUtil.getHttpServletRequest(renderRequest)); 
	String caseInstanceId = httpRequest.getParameter("caseInstanceId");
	if(caseInstanceId==null) caseInstanceId = "";
%>

<liferay-portlet:resourceURL id="autocomplete" var="autocompleteURL" />

<div class="portlet-msg-success" style="display:none;" id="<portlet:namespace/>successMsg"></div>

<c:choose>

<c:when test="<%= caseInstanceId.isEmpty() %>">
	<p><liferay-ui:message key="de.iisys.ACMStakeholders.choose" />.</p>
</c:when>

<c:otherwise>

	<aui:button-row>
		<aui:input id="userName" name="userName" type="text" cssClass="full-width" label="" value="" placeholder="de.iisys.ACMStakeholders.FindExpertByName" />
		<aui:input id="userNameBySkill" name="userNameBySkill" type="text" label="" value="" placeholder="de.iisys.ACMStakeholders.FindExpertBySkill" />
	</aui:button-row>
	
	<div id="<portlet:namespace/>caseStakeholders" style="clear:both;"></div>
	
</c:otherwise>
</c:choose>

<div id="<portlet:namespace/>loader"></div>


<aui:script>
	var <portlet:namespace/>LIFERAY_PROFILE_URL = '<%= PortletProps.get("liferay_profile_url") %>';
	
	var <portlet:namespace/>SHINDIG_URL = '<%= PortletProps.get("shindig_url") %>';
	var <portlet:namespace/>SHINDIG_TOKEN = '<%= shindigToken %>';
	var <portlet:namespace/>SKILLS_AUTOCOMPLETE_FRAG = "/social/rest/autocomplete/skills";
	var <portlet:namespace/>USER_SKILL_FRAG = "/social/rest/user?filterBy=@skills&filterOp=contains";
	
	var <portlet:namespace/>CASE_INSTANCE_ID = '<%= caseInstanceId %>';
	
	var <portlet:namespace/>STAKEHOLDERS = [];
	var <portlet:namespace/>STAKEHOLDERS_CASEPOSITION = [];
	var <portlet:namespace/>STAKEHOLDERS_IS_REMOVABLE = [];
	
	//temp:
	var <portlet:namespace/>tempCandidateUsers = [];
	var <portlet:namespace/>tempCandidateTaskNames = [];
	var <portlet:namespace/>TASKS_TO_SEARCH = 0;

	
	// control:
	
	function <portlet:namespace/>init() {
		if(<portlet:namespace/>CAMUNDA_REST_ENGINE==null || <portlet:namespace/>CAMUNDA_REST_ENGINE=='')
			<portlet:namespace/>CAMUNDA_REST_ENGINE = <portlet:namespace/>CAMUNDA_ENGINE;
		
		if(<portlet:namespace/>CASE_INSTANCE_ID!="") {
			<portlet:namespace/>getInstanceUserTasks(<portlet:namespace/>CASE_INSTANCE_ID);
		}
	}

	
	// control (GET):
		
	function <portlet:namespace/>getInstanceUserTasks(caseInstanceId) {
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/history/task"
				+ '?caseInstanceId='+caseInstanceId;
		
		<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>useInstanceUserTasks);
	}
	
	function <portlet:namespace/>getInstanceVariables(caseInstanceId) {
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/case-instance/"
				+ caseInstanceId + "/variables";
		
		console.log(url);
		<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>useInstanceVariables);
	}
	
	function <portlet:namespace/>getTaskCandidates(taskId, taskName) {
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/task"
			+ "/"+taskId + "/identity-links";
		
		var taskNameId = [taskId];
		taskNameId.push(taskName)

		<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>useTaskCandidates, null, taskNameId);
	}
	
	
	function <portlet:namespace/>getUserSkills(userId) {
		var url = <portlet:namespace/>SHINDIG_URL + "/social/rest/skills/" + userId;
		if(<portlet:namespace/>SHINDIG_TOKEN != null) url += "?st="+<portlet:namespace/>SHINDIG_TOKEN;
		
		// console.log("url: "+url);
		
		<portlet:namespace/>sendAsyncRequest('GET', url, <portlet:namespace/>showUserSkills, null, userId);
	}
	
	
	// control (PUT):
		
	function <portlet:namespace/>addVariableToCaseInstance(caseInstanceId, varName, varValue, varType, skill) {
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/case-instance/"
				+ caseInstanceId + "/variables/" + varName;
		
//		if(skill)
			// do something

		var theVar = {"value":varValue, "type":varType};
		
		<portlet:namespace/>sendAsyncRequest('PUT', url, <portlet:namespace/>showNewStakeholderSuccess, theVar);
	}
	
	// control (DELETE):
	
	function <portlet:namespace/>deleteVariableFromCaseInstance(caseInstanceId, varName, userId) {
		var url = <portlet:namespace/>CAMUNDA_URL +"/"+ <portlet:namespace/>CAMUNDA_REST_ENGINE +"/case-instance/"
				+ caseInstanceId + "/variables/" + varName;
		
		<portlet:namespace/>sendAsyncRequest('DELETE', url, <portlet:namespace/>removeStakeholderFromTable, null, userId);
	}
	
	
	// control:
		
	function <portlet:namespace/>addStakeholderToMainCaseExecution(userId, skill) {
		<portlet:namespace/>addVariableToCaseInstance(<portlet:namespace/>CASE_INSTANCE_ID, 'expert-'+userId, userId, 'String', skill);
	}
	

	// view (callback):
	
	// step 1:
	function <portlet:namespace/>useInstanceUserTasks(data) {
		// console.log("Stakeholders, step 1...");
		
		var tempPosArr;
		var stakeholdersTaskCount = [];
		var possibleTaskCandidates = [];
		var possibleTaskCandidatesNames = [];
		
		for(var i=0; i<data.length; i++) {
			if(data[i].assignee && data[i].assignee !== "null") {
				tempPosArr = <portlet:namespace/>STAKEHOLDERS.indexOf(data[i].assignee);
				if(tempPosArr===-1) {
					<portlet:namespace/>STAKEHOLDERS.push(data[i].assignee);
					<portlet:namespace/>STAKEHOLDERS_CASEPOSITION.push('<liferay-ui:message key="de.iisys.ACMStakeholders.assignee" /> ('+'<a title="'+data[i].id+'" href="'+<portlet:namespace/>CAMUNDA_URL+<portlet:namespace/>CAMUNDA_TASKLIST_FRAG+'?task='+data[i].id+'" target="_blank">'+data[i].name+'</a>)');
					<portlet:namespace/>STAKEHOLDERS_IS_REMOVABLE.push(false);
					stakeholdersTaskCount.push(1);
				} else {
					<portlet:namespace/>STAKEHOLDERS_CASEPOSITION[tempPosArr] = '<liferay-ui:message key="de.iisys.ACMStakeholders.assignee" /> ('+ ++stakeholdersTaskCount[tempPosArr] +' <liferay-ui:message key="de.iisys.ACMStakeholders.tasks" />)';
				}
			} else {
				possibleTaskCandidates.push(data[i].id);
				possibleTaskCandidatesNames.push(data[i].name);
			}
		}
		
		if(possibleTaskCandidates.length>0) { // to step 2
			<portlet:namespace/>TASKS_TO_SEARCH = possibleTaskCandidates.length;
			for(var j=0; j<possibleTaskCandidates.length; j++) {
				<portlet:namespace/>getTaskCandidates(possibleTaskCandidates[j], possibleTaskCandidatesNames[j]);
			}
		} else { // forward to step 3
			<portlet:namespace/>getInstanceVariables(<portlet:namespace/>CASE_INSTANCE_ID);
		}
	}
	
	// step 2a:
	function <portlet:namespace/>useTaskCandidates(data, taskIdName) {
		if(data) {
			// console.log("Stakeholders, step 2a...");
			for(var i=0; i<data.length; i++) {
				if(data[i].type==="candidate" && data[i].userId!=null) {
						<portlet:namespace/>tempCandidateUsers.push(data[i].userId);
						<portlet:namespace/>tempCandidateTaskNames.push(taskIdName);
				}
			}
		}
		<portlet:namespace/>TASKS_TO_SEARCH--;
		if(<portlet:namespace/>TASKS_TO_SEARCH < 1)
			<portlet:namespace/>useAllTaskCandidates();
	}
	// step 2b:
	function <portlet:namespace/>useAllTaskCandidates() {
		// console.log("Stakeholders, step 2b...");
		var tempPosArr;
		for(var i=0; i < <portlet:namespace/>tempCandidateUsers.length; i++) {	
			var tempPosArr = <portlet:namespace/>STAKEHOLDERS.indexOf(<portlet:namespace/>tempCandidateUsers[i]);
			if(tempPosArr===-1) { // candidate is not in list yet
				<portlet:namespace/>STAKEHOLDERS.push(<portlet:namespace/>tempCandidateUsers[i]);
				<portlet:namespace/>STAKEHOLDERS_CASEPOSITION.push('<liferay-ui:message key="de.iisys.ACMStakeholders.Candidate" /> ('+'<a title="'+<portlet:namespace/>tempCandidateTaskNames[i][0]+'" href="'+<portlet:namespace/>CAMUNDA_URL+<portlet:namespace/>CAMUNDA_TASKLIST_FRAG+'?task='+<portlet:namespace/>tempCandidateTaskNames[i][0]+'" target="_blank">'+<portlet:namespace/>tempCandidateTaskNames[i][1]+'</a>)');
				<portlet:namespace/>STAKEHOLDERS_IS_REMOVABLE.push(false);
			} else {
				<portlet:namespace/>STAKEHOLDERS_CASEPOSITION[tempPosArr] += ', <liferay-ui:message key="de.iisys.ACMStakeholders.Candidate" />';
			}
		}
		
		// next step:
		<portlet:namespace/>getInstanceVariables(<portlet:namespace/>CASE_INSTANCE_ID);
	}
	
	// step 3:
	function <portlet:namespace/>useInstanceVariables(data) {
		// console.log("Stakeholders, step 3...");
		if(data) {
			var keys = Object.keys(data);
			
			for(var i=0; i<keys.length; i++) {
				if(keys[i].indexOf('expert-')!==-1) {
					tempPosArr = <portlet:namespace/>STAKEHOLDERS.indexOf(data[keys[i]].value);
					var expertString = '<liferay-ui:message key="de.iisys.ACMStakeholders.Expert" />';
					if(data[keys[i]].valueInfo && data[keys[i]].valueInfo.objectTypeName)
						expertString += ' ('+data[keys[i]].valueInfo.objectTypeName+')';
					
					if(tempPosArr===-1) {
						<portlet:namespace/>STAKEHOLDERS.push(data[keys[i]].value);
						<portlet:namespace/>STAKEHOLDERS_CASEPOSITION.push(expertString);
						<portlet:namespace/>STAKEHOLDERS_IS_REMOVABLE.push(true);
					} else {
						<portlet:namespace/>STAKEHOLDERS_CASEPOSITION[tempPosArr] += ', '+expertString;
						<portlet:namespace/>STAKEHOLDERS_IS_REMOVABLE[tempPosArr] = true;
					}
				}
			}
		}
		// last step:
		<portlet:namespace/>showStakeholdersInHtml();
	}
	
	function <portlet:namespace />showFullNameInHtml(userId, fullName) {
		if(userId!==fullName) {
			var stakeholders = document.getElementById('<portlet:namespace/>caseStakeholders').getElementsByClassName("user-"+userId);
			for(var i=0; i<stakeholders.length; i++) {
				stakeholders[i].innerHTML = fullName;
			}
			if(i>0) {
				// show images:
				<portlet:namespace/>getUserPicSrc(userId);
				// show skills:
				<portlet:namespace/>getUserSkills(userId);
			}
		}
	}
	function <portlet:namespace/>getUserPicSrc(userId) {
		var url = <portlet:namespace/>SHINDIG_URL + "/pictures/"+userId+".png";
		var stakeholderImgs = document.getElementById('<portlet:namespace/>caseStakeholders').getElementsByClassName("user-img-"+userId);
		for(var i=0; i<stakeholderImgs.length; i++) {
			stakeholderImgs[i].src = url;
		}
	}
	
	function <portlet:namespace/>showUserSkills(data, userId) {
		if(data && data.list && data.list.length > 0) {
			var stakeholders = document.getElementById('<portlet:namespace/>caseStakeholders').getElementsByClassName("user-skills-"+userId);
			var html = '<i class="icon-tags" style="color:#999;"></i> ';
			
			var skillCount = 0;
			for(var j=0; j<data.list.length; j++) {
				if(skillCount>2) break;
				
				if(data.list[j].confirmed===true) {
					if(skillCount>0) html += ', ';
					html += data.list[j].name;
					skillCount++;
				}
			}
			
			for(var i=0; i<stakeholders.length; i++) {
				stakeholders[i].innerHTML = html;
			}
		}
	}
	
	function <portlet:namespace/>showNewStakeholderSuccess(data) {
		/*
		var successElement = document.getElementById('<portlet:namespace/>'+'successMsg');
		
		successElement.className = 'portlet-msg-success';
		successElement.innerHTML = '<liferay-ui:message key="de.iisys.ACMActivities.claimSuccess" />';
		successElement.style.display = "block";		*/
	}
		

	// view:
	
	function <portlet:namespace/>createTableRow(userId, userName, casePosition, isRemovableVar) {
		var imgSrc = '/image/user_female_portrait';
		
		var html = '<tr id="<portlet:namespace/>stakeholder-'+userId+'">';
		html += '<td class="table-cell first last">';
			
		html += '<div class="taglib-user-display display-style-2"> <a href=""> '+
			'<span class="user-profile-image"> '+
				'<img alt="" class="avatar user-img-'+userId+'" src="'+imgSrc+'">'+
			' </span> '+
			'<span class="user-name user-'+userId+'"> '+userName+
			' </span> </a> <div class="user-details"> </div> </div>';
			
		html += '<div class="last-thread"> <div class="stakeholder-link"> '+
				'<span class="author-sender">' +
				'<a href="'+<portlet:namespace/>LIFERAY_PROFILE_URL + userId +'">'+
					'<span class="user-'+userId+'">'+userName+'</span>'+
				'</a> </span>'+
				'<span class="date">&nbsp;';
		if(isRemovableVar) {
			html += '<a href="" onclick="<portlet:namespace/>deleteVariableFromCaseInstance(\''+<portlet:namespace/>CASE_INSTANCE_ID+'\''+
						', \'expert-'+userId+'\''+
						', \''+userId+'\'); return false;" style="color:#666;"><i class="icon-remove"></i></a>';
		}
		html += '</span> '+
				'<div class="subject"> '+casePosition+'</div> '+
				'<div class="body user-skills-'+userId+'">'+
					'<i class="icon-tags" style="color:#999;"></i> ' + '' +
				' </div>'+
			'</div></div>';	
				
		html += '</td></tr>';
		
		return html;
	}
	
	// last step:
	function <portlet:namespace/>showStakeholdersInHtml() {
		var html = '<table class="table table-bordered table-hover table-striped">'+
			'<tbody class="table-data" id="<portlet:namespace/>stakeholdersTableBody">';
			
		for(var j=0; j < <portlet:namespace/>STAKEHOLDERS.length; j++) {
			html += <portlet:namespace/>createTableRow(<portlet:namespace/>STAKEHOLDERS[j], <portlet:namespace/>STAKEHOLDERS[j], <portlet:namespace/>STAKEHOLDERS_CASEPOSITION[j], <portlet:namespace/>STAKEHOLDERS_IS_REMOVABLE[j]);
		}	
		
		html += '</tbody></table>';
		document.getElementById('<portlet:namespace/>caseStakeholders').innerHTML = html;
		
		// add user fullnames:
		for(var j=0; j < <portlet:namespace/>STAKEHOLDERS.length; j++) {
			<portlet:namespace/>getUserFullNameFromServer(<portlet:namespace/>STAKEHOLDERS[j]);
		}
	}
	
	
	function <portlet:namespace/>addStakeholderToTable(userId, userName, casePosition) {
		/*
		var html = <portlet:namespace/>createTableRow(userId, userName, casePosition, true)
		
		var bodyContent = document.getElementById('<portlet:namespace/>stakeholdersTableBody');
		bodyContent.innerHTML = html + bodyContent.innerHTML; */
		location.reload();
	}
	
	function <portlet:namespace/>removeStakeholderFromTable(data, userId) {
/*		var row = document.getElementById('<portlet:namespace/>stakeholder-'+userId);
		row.parentNode.removeChild(row); */
		location.reload();
	}
	
// autocomplete:
	
	if(<portlet:namespace/>CASE_INSTANCE_ID!="") {
		
		//skill autocomplete:
		AUI().use('autocomplete-list',function(A) {
			var <portlet:namespace />autoComplete = new A.AutoCompleteList({
				allowBrowserAutocomplete: false,
				activateFirstItem: true,
				inputNode: '#<portlet:namespace />userNameBySkill',
				maxResults: 10,
				on: {
					select: function(event) {
						var result = event.result.raw;
						if(result.skill) {
							A.one('#<portlet:namespace/>userNameBySkill').val('');
							<portlet:namespace/>addStakeholderToTable(result.userId, result.displayName, '<liferay-ui:message key="de.iisys.ACMStakeholders.Expert" />'+' ('+result.skill+')');
							<portlet:namespace/>addStakeholderToMainCaseExecution(result.userId, result.skill);
						} else {
							A.one('#<portlet:namespace/>userNameBySkill').val(result);
						}
					}
				},
				render: true,
				source: function(query, response) {
						var urlSkill = <portlet:namespace/>SHINDIG_URL + <portlet:namespace/>SKILLS_AUTOCOMPLETE_FRAG + '?fragment=' + query;
						urlSkill += <portlet:namespace/>SHINDIG_TOKEN ? '&st=' + <portlet:namespace/>SHINDIG_TOKEN : '';
			          
					  	<portlet:namespace/>sendRequest('GET', urlSkill, function(data) {
					  		
//						  	response(data.list);
							var skills = [];
							for(var j=0; j<data.list.length; j++) {
								skills
							}

							var urlUser = <portlet:namespace/>SHINDIG_URL + <portlet:namespace/>USER_SKILL_FRAG + '&filterValue=' + data.list[0];
							urlUser += <portlet:namespace/>SHINDIG_TOKEN ? '&st=' + <portlet:namespace/>SHINDIG_TOKEN : '';
							<portlet:namespace/>sendRequest('GET', urlUser, function(data, skills) {
//								response(data.list);

//								console.log('skill: '+skill+', users: '+JSON.stringify(data.list));
								var theList = [];
								for(var i=0; i<data.list.length; i++) {
									theList.push({
										"skill" : skills[0],
										"displayName" : data.list[i].name.formatted,
										"userId" : data.list[i].id
									});
								}
								skills.shift();
								response(theList.concat(skills));
							}, null, data.list);
				   		});
				    	
//				    	response(['Open Source Software', 'Drucker', 'Linux', 'Windows', 'Curling', 'Bowling', 
//								  'Zen', 'Roccat', 'Änderungsmanagement', 'Research & Development']);
				  },
/*
				resultListLocator: function (response) {
					var responseData = A.JSON.parse(response[0].responseText);
					return responseData.response;
				}, */
				resultTextLocator: function (result) {
//					return result.name.formatted;
					if(result.skill)
						return result.skill+': '+result.displayName;
					else
						return result;
				},
				resultHighlighter: 'phraseMatch'
			});
		});
		
		// user autocomplete:
		AUI().use('autocomplete-list','datasource-io',function(A) {
			var <portlet:namespace />datasource = new A.DataSource.IO({
				source: '<%= autocompleteURL %>'
			});	
			
			var <portlet:namespace />autoComplete = new A.AutoCompleteList({
				allowBrowserAutocomplete: false,
				activateFirstItem: true,
				inputNode: '#<portlet:namespace />userName',
				maxResults: 10,
				on: {
					select: function(event) {
						var result = event.result.raw;
						A.one('#<portlet:namespace/>userName').val('');
						<portlet:namespace/>addStakeholderToTable(result.userId, result.fullName, '<liferay-ui:message key="de.iisys.ACMStakeholders.Expert" />');
						<portlet:namespace/>addStakeholderToMainCaseExecution(result.userId);
					}
				},
				render: true,
				source: <portlet:namespace />datasource,
				requestTemplate: '&<portlet:namespace />keywords={query}',
				resultListLocator: function (response) {
					var responseData = A.JSON.parse(response[0].responseText);
					return responseData.response;
				},
				resultTextLocator: function (result) {
					return result.fullName;
				},
				resultHighlighter: 'phraseMatch'
			});
		});
	}
	
	// start:
	<portlet:namespace/>init();
	
</aui:script>