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

<%@ page import="java.util.Map,java.util.HashMap,org.apache.shindig.common.crypto.BasicBlobCrypter,java.io.File" %>


<%
	String userName = user.getScreenName();

	//see AbstractSecurityToken for keys
	Map<String, String> token = new HashMap<String, String>();
	//application
	token.put("i", "AcmStakeholders-portlet");
	//viewer
	token.put("v", userName);
	
	String shindigToken = "default:" + new BasicBlobCrypter(new File(PortletProps.get("token_secret"))).wrap(token);

%>