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

public class ControllerGenerator extends BaseGenerator {
	static Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
	static {
		configuration.setClassForTemplateLoading(MainGenerator.class, "/");
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

	}
	private String name;
	private String path;

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
	public ControllerGenerator setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * 获取path
	 * 
	 * @return path path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 设置path
	 * 
	 * @param path
	 *            path
	 */
	public ControllerGenerator setPath(String path) {
		this.path = path;
		return this;
	}

	public void save() throws Exception {
		String type = "controller";
		Named named = new Named(name);
		String savePath = getFileString(type);
		Map<String, Object> data = new HashMap<>();
		data.put("name", named);
		data.put("packageName", getPackageName());
		data.put("type", type);
		data.put("path", getPath());
		Template template = configuration.getTemplate(getTemplateName());
		FileOutputStream fos = new FileOutputStream(
				new File(savePath+ "\\" + named.getUpperCaseFirstName() + "Controller.java"));
		template.process(data, new OutputStreamWriter(fos, "utf-8"));
	}
}
