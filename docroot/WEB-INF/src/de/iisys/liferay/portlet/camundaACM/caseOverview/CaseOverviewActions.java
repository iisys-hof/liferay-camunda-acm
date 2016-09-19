package de.iisys.liferay.portlet.camundaACM.caseOverview;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLink;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetLinkLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance;
import de.iisys.liferay.portlet.camundaACM.caseOverview.service.CaseInstanceLocalServiceUtil;

public class CaseOverviewActions extends MVCPortlet {

	public void render(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		User usuario;
		ServiceContext serviceContext = new ServiceContext();
		try {
			usuario = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			serviceContext.setScopeGroupId(usuario.getGroupId());
			serviceContext.setUserId(usuario.getUserId());
		} catch (PortalException | SystemException e1) {
			e1.printStackTrace();
		}
		
		
		HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(
				PortalUtil.getHttpServletRequest(request)); 
		String caseInstanceId = httpRequest.getParameter("caseInstanceId");
		String caseDefinitionKey = httpRequest.getParameter("caseDefinitionKey");
		String caseCreateTime = httpRequest.getParameter("caseCreateTime");
		
		if(caseInstanceId!=null && !caseInstanceId.equals("")) {
			if(caseDefinitionKey==null) caseDefinitionKey = "";
			try {
				if(caseCreateTime!=null) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
					try {
						Date createTime = formatter.parse(caseCreateTime);
						CaseInstance caseInst = CaseInstanceLocalServiceUtil.addCaseInstance(caseInstanceId, caseDefinitionKey, serviceContext, themeDisplay.getUserId(), themeDisplay.getCompanyGroupId(), createTime);
					} catch (ParseException e) {}
				} else {
					System.out.println("No createTime parameter found.");
					CaseInstance caseInst = CaseInstanceLocalServiceUtil.addCaseInstance(caseInstanceId, caseDefinitionKey, serviceContext, themeDisplay.getUserId(), themeDisplay.getCompanyGroupId());
				}
			} catch (SystemException | PortalException e) {
				e.printStackTrace();
			}
		}
		
		super.render(request, response);
	}
	
	public void updateCaseInstanceAsset(ActionRequest request, ActionResponse response) throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
                CaseInstance.class.getName(), request);

		long casePK = ParamUtil.getLong(request, "liferayCaseInstanceId");
		
	    CaseInstanceLocalServiceUtil.updateAssetsForCaseInstance(casePK, serviceContext);
	}

}
