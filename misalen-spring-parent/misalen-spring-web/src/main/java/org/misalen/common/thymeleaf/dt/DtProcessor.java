package org.misalen.common.thymeleaf.dt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.misalen.web.domain.SysDictionaryData;
import org.misalen.web.service.SysDictionaryDataService;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.AttributeValueQuotes;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

public class DtProcessor extends AbstractElementTagProcessor {

	private static final String TAG_NAME = "html";
	private static final int PRECEDENCE = 1;

	private static final String SELECT = "select";
	private static final String INPUT = "input";

	public DtProcessor(final String dialectPrefix) {
		super(TemplateMode.HTML, // This processor will apply only to HTML mode
				dialectPrefix, // Prefix to be applied to name for matching
				TAG_NAME, // Tag name: match specifically this tag
				true, // Apply dialect prefix to tag name
				null, // No attribute name: will match by tag name
				false, // No prefix to be applied to attribute name
				PRECEDENCE); // Precedence (inside dialect's own precedence)
	}

	private String getSelectHtml(ITemplateContext context, String code, String value) {
		final ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		final SysDictionaryDataService dictionaryDataService = appCtx.getBean(SysDictionaryDataService.class);
		List<SysDictionaryData> datas = dictionaryDataService.findByDtCode(code);
		StringBuffer option = new StringBuffer();
		for (SysDictionaryData sysDictionaryData : datas) {
			option.append("<option value=\"");
			option.append(HtmlEscape.escapeHtml5(sysDictionaryData.getCode()));
			option.append("\"");
			if (value != null && value.equals(sysDictionaryData.getCode())) {
				option.append(" selected");
			}
			option.append(">");
			option.append(HtmlEscape.escapeHtml5(sysDictionaryData.getName()));
			option.append("</option>");
		}
		return option.toString();
	}

	private String getInput(ITemplateContext context, String code) {
		final ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		final SysDictionaryDataService dictionaryDataService = appCtx.getBean(SysDictionaryDataService.class);
		List<SysDictionaryData> datas = dictionaryDataService.findByDtCode(code);
		StringBuffer option = new StringBuffer();
		option.append("<div id=\"" + code + "\">");
		for (int i = 0; i < datas.size(); i++) {
			option.append("<input type=\"hidden\" name=");
			option.append("\"" + datas.get(i).getCode() + "\"");
			option.append(" value=");
			option.append("\"" + datas.get(i).getName() + "\">");
		}
		option.append("</div>");
		return option.toString();
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		final String code = tag.getAttributeValue("code");
		final String type = tag.getAttributeValue("type");
		final String value = tag.getAttributeValue("value");

		Map<String, String> attributes = new HashMap<String, String>();
		if (tag.hasAttribute("id")) {
			attributes.put("id", tag.getAttributeValue("id"));
		}
		if (tag.hasAttribute("name")) {
			attributes.put("name", tag.getAttributeValue("name"));
		}
		if (tag.hasAttribute("class")) {
			attributes.put("class", tag.getAttributeValue("class"));
		}

		final IModelFactory modelFactory = context.getModelFactory();
		final IModel model = modelFactory.createModel();
		if (SELECT.equals(type)) {
			model.add(modelFactory.createOpenElementTag(type, attributes, AttributeValueQuotes.NONE, false));
			model.add(modelFactory.createText(getSelectHtml(context, code, value)));
			model.add(modelFactory.createCloseElementTag(type));
		} else if (INPUT.equals(type)) {
			model.add(modelFactory.createText(getInput(context, code)));
		}
		structureHandler.replaceWith(model, false);
	}
}
