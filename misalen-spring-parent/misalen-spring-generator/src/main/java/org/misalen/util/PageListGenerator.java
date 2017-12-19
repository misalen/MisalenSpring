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

public class PageListGenerator extends BaseGenerator {
	static Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
	static {
		configuration.setClassForTemplateLoading(MainGenerator.class, "/");
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}
	// sysFormFields
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
	public PageListGenerator setSysFormInfo(SysFormInfo sysFormInfo) {
		this.sysFormInfo = sysFormInfo;
		return this;
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
	public PageListGenerator setName(String name) {
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
	 * @return
	 */
	public PageListGenerator setPath(String path) {
		this.path = path;
		return this;
	}

	public void save() throws Exception {
		Named named = new Named(name);
		Map<String, Object> data = new HashMap<>();
		data.put("name", named);
		data.put("path", path);
		data.put("list", getSysFormInfo().getSysFormFields());
		String savePath = getViewFileString(path);
		Template template = configuration.getTemplate(getTemplateName());
		FileOutputStream fos = new FileOutputStream(new File(savePath + "/list.html"));
		template.process(data, new OutputStreamWriter(fos, "utf-8"));
		Template template2 = configuration.getTemplate(getTemplateName().substring(0, getTemplateName().length() - 4)
				+ ".th" + getTemplateName().substring(getTemplateName().length() - 4, getTemplateName().length()));
		FileOutputStream fos2 = new FileOutputStream(new File(savePath + "/list.th.xml"));
		template2.process(new HashMap<String, Object>(), new OutputStreamWriter(fos2, "utf-8"));
	}
}
