package org.misalen.hibernate.tool.util;

import org.misalen.common.utils.TextUtil;

public class Named {
	/** 首字母小写 */
	private String lowerCaseFirstName;
	/** 首字母大写 */
	private String upperCaseFirstName;
	/** 下划线 */
	private String underlineName;
	/** 驼峰 */
	private String humpName;
	/** 原始 */
	private String name;

	public Named(String name) {
		setName(name);
		setHumpName(TextUtil.camelCaseName(name));
		setUnderlineName(TextUtil.underscoreName(name));
		setLowerCaseFirstName(TextUtil.toLowerCaseFirstOne(getHumpName()));
		setUpperCaseFirstName(TextUtil.toUpperCaseFirstOne(getHumpName()));
	}

	/**
	 * 获取原始
	 * 
	 * @return name 原始
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置原始
	 * 
	 * @param name
	 *            原始
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取首字母小写
	 * 
	 * @return lowerCaseFirstName 首字母小写
	 */
	public String getLowerCaseFirstName() {
		return lowerCaseFirstName;
	}

	/**
	 * 设置首字母小写
	 * 
	 * @param lowerCaseFirstName
	 *            首字母小写
	 */
	public void setLowerCaseFirstName(String lowerCaseFirstName) {
		this.lowerCaseFirstName = lowerCaseFirstName;
	}

	/**
	 * 获取首字母打写
	 * 
	 * @return upperCaseFirstName 首字母打写
	 */
	public String getUpperCaseFirstName() {
		return upperCaseFirstName;
	}

	/**
	 * 设置首字母打写
	 * 
	 * @param upperCaseFirstName
	 *            首字母打写
	 */
	public void setUpperCaseFirstName(String upperCaseFirstName) {
		this.upperCaseFirstName = upperCaseFirstName;
	}

	/**
	 * 获取下划线
	 * 
	 * @return underlineName 下划线
	 */
	public String getUnderlineName() {
		return underlineName;
	}

	/**
	 * 设置下划线
	 * 
	 * @param underlineName
	 *            下划线
	 */
	public void setUnderlineName(String underlineName) {
		this.underlineName = underlineName;
	}

	/**
	 * 获取驼峰
	 * 
	 * @return humpName 驼峰
	 */
	public String getHumpName() {
		return humpName;
	}

	/**
	 * 设置驼峰
	 * 
	 * @param humpName
	 *            驼峰
	 */
	public void setHumpName(String humpName) {
		this.humpName = humpName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Named [lowerCaseFirstName=" + lowerCaseFirstName + ", upperCaseFirstName=" + upperCaseFirstName
				+ ", underlineName=" + underlineName + ", humpName=" + humpName + "]";
	}

}
