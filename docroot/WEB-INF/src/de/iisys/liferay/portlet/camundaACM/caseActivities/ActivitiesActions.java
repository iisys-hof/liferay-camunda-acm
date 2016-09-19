package de.iisys.liferay.portlet.camundaACM.caseActivities;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class ActivitiesActions extends MVCPortlet {
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		String resourceID = resourceRequest.getResourceID();
		if(resourceID.equals("userFullName")) {
			getFullNameByScreenName(resourceRequest, resourceResponse);
		} else {
			super.serveResource(resourceRequest, resourceResponse);
		}
	}

	private void getFullNameByScreenName(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException,	PortletException {
		String screenName = ParamUtil.getString(resourceRequest, "screenName");
		
		User user = null;
		try {
			user = UserLocalServiceUtil.getUserByScreenName(CompanyThreadLocal.getCompanyId(), screenName);
		} catch (NoSuchUserException nsuE) {
			System.out.println("com.liferay.portal.NoSuchUserException | screenName="+screenName);
		} catch (PortalException | SystemException e) {
			e.printStackTrace();
		}
		
		resourceResponse.setContentType("text/html");
		PrintWriter writer = resourceResponse.getWriter();
		if(user==null)
			writer.print(screenName);
		else
			writer.print(user.getFullName());
		
		writer.flush();
		writer.close();
		super.serveResource(resourceRequest, resourceResponse);
	}
}
