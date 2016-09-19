package de.iisys.liferay.portlet.camundaACM.caseStakeholders;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class StakeholderActions extends MVCPortlet {
	
	
	
	/* User Autocomplete: */
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		String resourceID = resourceRequest.getResourceID();
		if(resourceID.equals("autocomplete")) {
			getUsersForAutocomplete(resourceRequest, resourceResponse);
		} else if(resourceID.equals("userFullName")) {
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
	
	private void getUsersForAutocomplete(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException,	PortletException {
		String keywords = ParamUtil.getString(resourceRequest, "keywords");
		 
		JSONObject json = JSONFactoryUtil.createJSONObject();
		JSONArray results = JSONFactoryUtil.createJSONArray();
		json.put("response", results);
 
		try {
			DynamicQuery query = DynamicQueryFactoryUtil
					.forClass(User.class);
//			query.add(PropertyFactoryUtil.forName("firstName").like(
//					StringPool.PERCENT + keywords + StringPool.PERCENT));
			Criterion criterion = RestrictionsFactoryUtil.ilike("firstName",
					StringPool.PERCENT + keywords + StringPool.PERCENT);
			Criterion criterion2 = RestrictionsFactoryUtil.ilike("lastName",
					StringPool.PERCENT + keywords + StringPool.PERCENT);
			
			query.add(RestrictionsFactoryUtil.or(criterion, criterion2));
			
			@SuppressWarnings("unchecked")
			List<User> userNames = UserLocalServiceUtil.dynamicQuery(query);
 
			for (User user : userNames) {
				JSONObject object = JSONFactoryUtil.createJSONObject();
				object.put("userId", user.getScreenName());
				object.put("fullName", user.getFullName());
				results.put(object);
			}
		} catch (SystemException e) {
			e.printStackTrace();
		}
 
		writeJSON(resourceRequest, resourceResponse, json);
	}

}
