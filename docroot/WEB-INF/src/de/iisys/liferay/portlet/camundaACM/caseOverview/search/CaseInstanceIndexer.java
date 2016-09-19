package de.iisys.liferay.portlet.camundaACM.caseOverview.search;

import java.util.Locale;

import javax.portlet.PortletURL;

import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.service.ClassNameLocalServiceUtil;

import de.iisys.liferay.portlet.camundaACM.caseOverview.model.CaseInstance;
import de.iisys.liferay.portlet.camundaACM.caseOverview.service.CaseInstanceLocalServiceUtil;

public class CaseInstanceIndexer extends BaseIndexer {
	
	public static final String[] CLASS_NAMES = { CaseInstance.class.getName() };
	
	public static final String PORTLET_ID = Long.toString(ClassNameLocalServiceUtil.getClassNameId(CaseInstance.class));

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getPortletId() {
		return PORTLET_ID;
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		CaseInstance inst = (CaseInstance) obj;
		deleteDocument(inst.getCompanyId(), inst.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		CaseInstance inst = (CaseInstance) obj;
		Document document = getBaseModelDocument(PORTLET_ID, inst);
		
		document.addDate(Field.MODIFIED_DATE, inst.getModifiedDate());
        document.addText(Field.CONTENT, inst.getCaseDefinitionKey());
        document.addText(Field.TITLE, inst.getCaseInstanceId());
        document.addText("caseInstanceId", inst.getCaseInstanceId());
        document.addText("caseDefinitionKey", inst.getCaseDefinitionKey());
        document.addKeyword(Field.GROUP_ID, getSiteGroupId(inst.getGroupId()));
        document.addKeyword(Field.SCOPE_GROUP_ID, inst.getGroupId());

        return document;
	}

	@Override
	protected Summary doGetSummary(Document doc, Locale locale, String snippet,
			PortletURL portletURL) throws Exception {
		Summary summary = createSummary(doc);

        summary.setMaxContentLength(200);

        return summary;
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		CaseInstance inst = (CaseInstance) obj;

        Document document = getDocument(inst);

        SearchEngineUtil.updateDocument(
                getSearchEngineId(), inst.getCompanyId(), document);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		CaseInstance inst = CaseInstanceLocalServiceUtil.getCaseInstance(classPK);
		doReindex(inst);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}
	

}
