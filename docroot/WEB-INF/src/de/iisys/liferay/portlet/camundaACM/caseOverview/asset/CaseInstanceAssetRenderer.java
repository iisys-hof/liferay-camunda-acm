package de.iisys.liferay.portlet.camundaACM.caseOverview.asset;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance;

public class CaseInstanceAssetRenderer extends BaseAssetRenderer {

	private CaseInstance _instance;
	
	public CaseInstanceAssetRenderer(CaseInstance caseInstance) {
		_instance = caseInstance;
	}
	
	@Override
	public String getClassName() {
		return CaseInstance.class.getName();
	}

	@Override
	public long getClassPK() {
		return _instance.getPrimaryKey();
	}

	@Override
	public long getGroupId() {
		return _instance.getGroupId();
	}

	@Override
	public String getSummary(Locale arg0) {
		return "Id: "+_instance.getCaseInstanceId();
	}

	@Override
	public String getTitle(Locale locale) {
		String title = "";
		SimpleDateFormat simpleFormat= new SimpleDateFormat("yyyy/MM/dd hh:mm");
		
		if(locale.equals(LocaleUtil.GERMANY) || locale.equals(LocaleUtil.GERMAN)) {
			title = "Instanz des Falls "+_instance.getCaseDefinitionKey()+" ("+simpleFormat.format(_instance.getCreateDate())+")";	
		} else {
			title = "Case Instance of "+_instance.getCaseDefinitionKey()+" ("+simpleFormat.format(_instance.getCreateDate())+")";
		}
		return title;
	}

	@Override
	public long getUserId() {
		return _instance.getUserId();
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String render(RenderRequest request, RenderResponse response, String template)
			throws Exception {
		System.out.println("template: "+template);
		if(template.equals("full_content")) {
			request.setAttribute("caseInstance", _instance);
			return "/html/caseOverview/asset/"+"full_content"+".jsp";
		} else {
			request.setAttribute("caseInstance", _instance);
			return "/html/caseOverview/asset/"+template+".jsp";
		}
	}
	
	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {

	        return themeDisplay.getURLPortal()
	                        + "/CamundaACM-portlet/images/camunda.png";

	}

}
