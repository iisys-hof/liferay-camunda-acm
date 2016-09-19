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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>

<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="com.liferay.util.portlet.PortletProps" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="com.liferay.portal.kernel.util.Constants"%>


<portlet:defineObjects />
<liferay-theme:defineObjects />


<script type="text/javascript">
	var <portlet:namespace/>CAMUNDA_URL = '<%= PortletProps.get("camunda_url") %>';
	var <portlet:namespace/>CAMUNDA_ENGINE = '<%= PortletProps.get("camunda_engine") %>';
	var <portlet:namespace/>CAMUNDA_REST_ENGINE = '<%= PortletProps.get("camunda_rest_engine") %>';
	var <portlet:namespace/>CAMUNDA_VERSION = '<%= PortletProps.get("camunda_version") %>';
	var <portlet:namespace/>CAMUNDA_TASKLIST_FRAG = '/camunda/app/tasklist/default/#/';

	// requests:
	
	function <portlet:namespace/>sendAsyncRequest(method, url, callback, payload, callbackValue) {
		
		AUI().use('aui-io-request', function(A)
		{
			<portlet:namespace/>animationOnOff(true, A);
			
			if(payload && payload!=="") {
			  A.io.request(url, {
				  dataType: 'json',
				  method : method,
				  headers: {
					  'Content-Type': 'application/json; charset=utf-8'
				  },
				  data : JSON.stringify(payload),
				  on: {
					success: function() {
						<portlet:namespace/>animationOnOff(false, A);
						if(callbackValue)
							callback(this.get('responseData'),callbackValue);
						else
					  		callback(this.get('responseData'));
					},
					failure: function() {
						<portlet:namespace/>showError(this.get('responseData'));
						<portlet:namespace/>animationOnOff(false, A);
					}
				  }
			  });
			} else {
				A.io.request(url, {
				  dataType: 'json',
				  method : method,
				  on: {
					success: function() {
						<portlet:namespace/>animationOnOff(false, A);
						if(callbackValue)
							callback(this.get('responseData'),callbackValue);
						else
					  		callback(this.get('responseData'));
					},
					failure: function() {
						<portlet:namespace/>showError(this.get('responseData'));
						<portlet:namespace/>animationOnOff(false, A);
					}
				  }
				});
			}
		});
	}
	
	function <portlet:namespace/>sendRequest(method, url, callback, payload, callbackValue) {
		AUI().use('aui-io-request', function(A) {
		  if(payload && payload!=null) {
			  A.io.request(url, {
				  dataType: 'json',
				  method : method,
				  headers: {
					  'Content-Type': 'application/json; charset=utf-8'
				  },
				  data : JSON.stringify(payload),
				  on: {
					success: function() {
					  	if(callbackValue)
							callback(this.get('responseData'),callbackValue);
						else
					  		callback(this.get('responseData'));
					},
					failure: function() {
						<portlet:namespace/>showError(this.get('responseData'));
						<portlet:namespace/>animationOnOff(false, A);
					}
				  }
			  });
		  } else {
			  A.io.request(url, {
				  dataType: 'json',
				  method : method,
				  on: {
					success: function() {
						if(callbackValue)
							callback(this.get('responseData'),callbackValue);
						else
					  		callback(this.get('responseData'));
					},
					failure: function() {
						<portlet:namespace/>showError(this.get('responseData'));
						<portlet:namespace/>animationOnOff(false, A);
					}
				  }
			  });
		  }
		});
	  }
	
	// helper:

	function <portlet:namespace/>animationOnOff(on, A) {
		if(on)
			A.one('#<portlet:namespace/>loader').setHTML(A.Node.create('<div class="loading-animation" />'));
		else
			A.one('#<portlet:namespace/>loader').setHTML(A.Node.create('<div />'));
	}
	
	function <portlet:namespace/>showError(data) {
		console.log("Request-Error!");
		var successElement = document.getElementById('<portlet:namespace/>'+'successMsg');
		successElement.className = 'portlet-msg-error';
		successElement.innerHTML = 'Request-Error';
		if(data && data.type)
			successElement.innerHTML += ' ('+data.type+')';
		if(data && data.message)
			successElement.innerHTML += ': '+data.message;
		
		successElement.style.display = "block";
	}
	
	function <portlet:namespace/>displayTime(time) {
	    var date = new Date(time);
	  	
	    // year:
	    var display = date.getUTCFullYear() + '/';
	    // month:
	    var month = date.getUTCMonth() + 1;
	    if(month < 10)
	    {
	      month = '0' + month;
	    }
	    display += month + '/';
	    
	    
	    var days = date.getUTCDate();
	    if(days < 10)
	    {
	    	days = '0' + days;
	    }    
	    display += days + ' ';
	    
	    var hours = date.getUTCHours();
	    if(hours < 10)
	    {
	      display += '0';
	    }
	    display += hours + ':';
	    
	    var minutes = date.getUTCMinutes();
	    if(minutes < 10)
	    {
	      display += '0';
	    }
	    display += minutes;
	    
	    return display;
	}

</script>