package org.misalen.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.misalen.hibernate.tool.util.Named;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class ServiceGenerator extends BaseGenerator {
	static Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
	static {
		configuration.setClassForTemplateLoading(MainGenerator.class, "/");
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

	}
	private String name;

	/**
	 * 获取name
	 * 
	 * @return name name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name
	 * 
	 * @param name
	 *            name
	 */
	public ServiceGenerator setName(String name) {
		this.name = name;
		return this;
	}

	public void save() throws Exception {
		String type = "service";
		String savePath = getFileString(type);
		Named named = new Named(name);
		Map<String, Object> data = new HashMap<>();
		data.put("name", named);
		data.put("packageName", getPackageName());
		data.put("type", type);
		Template template = configuration.getTemplate(getTemplateName());
		FileOutputStream fos = new FileOutputStream(
				new File(savePath + "\\" + named.getUpperCaseFirstName() + "Service.java"));
		template.process(data, new OutputStreamWriter(fos, "utf-8"));
	}
}
