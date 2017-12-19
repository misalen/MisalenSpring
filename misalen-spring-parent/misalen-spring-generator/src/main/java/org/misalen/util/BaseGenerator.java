package org.misalen.util;

import java.io.File;

public abstract class BaseGenerator {
	private String projectName;

	private String packageName;
	private String templateName;

	/**
	 * 获取templateName
	 * 
	 * @return templateName templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * 设置templateName
	 * 
	 * @param templateName
	 *            templateName
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * 获取projectName
	 * 
	 * @return projectName projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * 设置projectName
	 * 
	 * @param projectName
	 *            projectName
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;

	}

	/**
	 * 获取packageName
	 * 
	 * @return packageName packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * 设置packageName
	 * 
	 * @param packageName
	 *            packageName
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	abstract void save() throws Exception;

	protected String getFileString(String type) {
		File directory = new File(new File("").getAbsolutePath()).getParentFile();
		StringBuffer buffer = new StringBuffer();
		buffer.append(directory.getAbsolutePath());
		buffer.append("\\");
		buffer.append(projectName);
		buffer.append("\\");
		buffer.append("src\\main\\java\\");
		buffer.append(packageName.replaceAll("\\.", "\\\\"));
		buffer.append("\\");
		buffer.append(type);
		File file = new File(buffer.toString());
		if (!file.exists()) {
			file.mkdirs();
		}
		return buffer.toString();
	}
	protected String getViewFileString(String path) {
		File directory = new File(new File("").getAbsolutePath()).getParentFile();
		StringBuffer buffer = new StringBuffer();
		buffer.append(directory.getAbsolutePath());
		buffer.append("\\");
		buffer.append(projectName);
		buffer.append("\\");
		buffer.append("src\\main\\resources\\templates\\");
		buffer.append(path);
		File file = new File(buffer.toString());
		if (!file.exists()) {
			file.mkdirs();
		}
		return buffer.toString();
	}
}
