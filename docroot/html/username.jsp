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

<liferay-portlet:resourceURL id="userFullName" var="userFullNameURL" />	

<script type="text/javascript">
		
	// AJAX server calls:
		
	function <portlet:namespace/>getUserFullNameFromServer(screenName) {
		
		AUI().use('aui-io-request', function(A){
	        A.io.request('<%=userFullNameURL.toString()%>', {
	               method: 'post',
	               data: {
	            	   <portlet:namespace />screenName: screenName
	               },
	               on: {
	                    success: function() {
	                     	<portlet:namespace />showFullNameInHtml(screenName, this.get('responseData'));
	               		}
	               }
	            });
	    });
		
	}
	
</script>