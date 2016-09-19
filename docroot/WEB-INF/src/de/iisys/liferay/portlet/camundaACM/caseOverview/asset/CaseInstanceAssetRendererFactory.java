package de.iisys.liferay.portlet.camundaACM.caseOverview.asset;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;

import de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance;
import de.iisys.liferay.portlet.camundaACM.caseOverview.service.CaseInstanceLocalServiceUtil;

public class CaseInstanceAssetRendererFactory extends BaseAssetRendererFactory {
	
	public static final String CLASS_NAME = CaseInstance.class.getName();
	
	public static final String TYPE = "caseInstance";

	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type)
			throws PortalException, SystemException {

		CaseInstance instance = CaseInstanceLocalServiceUtil.getCaseInstance(classPK);
		return new CaseInstanceAssetRenderer(instance);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	 @Override
     public boolean isLinkable() {
             return _LINKABLE;
     }

     private static final boolean _LINKABLE = true;

}
