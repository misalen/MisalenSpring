package org.misalen.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.misalen.generator.domain.SysFormInfo;
import org.misalen.hibernate.tool.util.Named;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class ModelGenerator extends BaseGenerator {
	static Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
	static {
		configuration.setClassForTemplateLoading(MainGenerator.class, "/");
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

	}

	private SysFormInfo sysFormInfo;

	/**
	 * 获取sysFormInfo
	 * 
	 * @return sysFormInfo sysFormInfo
	 */
	public SysFormInfo getSysFormInfo() {
		return sysFormInfo;
	}

	/**
	 * 设置sysFormInfo
	 * 
	 * @param sysFormInfo
	 *            sysFormInfo
	 */
	public ModelGenerator setSysFormInfo(SysFormInfo sysFormInfo) {
		this.sysFormInfo = sysFormInfo;
		return this;
	}

	@Override
	public void save() throws Exception {
		String type = "domain";
		String savePath = getFileString(type);
		Named named = new Named(sysFormInfo.getClassName());
		Map<String, Object> data = new HashMap<>();
		data.put("name", named);
		data.put("packageName", getPackageName());
		data.put("type", type);
		data.put("sysFormInfo", sysFormInfo);
		Template template = configuration.getTemplate(getTemplateName());
		FileOutputStream fos = new FileOutputStream(
				new File(savePath + "\\" + named.getUpperCaseFirstName() + ".java"));
		template.process(data, new OutputStreamWriter(fos, "utf-8"));
	}
}
