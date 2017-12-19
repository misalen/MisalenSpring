package org.misalen.generator.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.misalen.common.annotations.ModelComment;
import org.misalen.db.jpa.base.domain.BaseDomain;

@Entity
@Table
public class SysFormInfo extends BaseDomain {

	@ModelComment("名称")
	@Column(length = 255, nullable = false)
	private String name;

	@ModelComment("实体类名称")
	@Column(length = 255, nullable = false)
	private String className;

	@ModelComment("模块名称")
	@Column(length = 255, nullable = false)
	private String moduleName;

	@ModelComment("功能名称")
	@Column(length = 255, nullable = false)
	private String functionName;

	@OneToMany(targetEntity = SysFormField.class, cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "FORM_ID", updatable = false)
	@ModelComment("关系")
	@OrderBy("SEQ ASC")
	private Set<SysFormField> sysFormFields;

	/**
	 * 获取moduleName
	 * 
	 * @return moduleName moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * 设置moduleName
	 * 
	 * @param moduleName
	 *            moduleName
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * 获取functionName
	 * 
	 * @return functionName functionName
	 */
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * 设置functionName
	 * 
	 * @param functionName
	 *            functionName
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	/**
	 * 获取sysFormFields
	 * 
	 * @return sysFormFields sysFormFields
	 */
	public Set<SysFormField> getSysFormFields() {
		return sysFormFields;
	}

	/**
	 * 设置sysFormFields
	 * 
	 * @param sysFormFields
	 *            sysFormFields
	 */
	public void setSysFormFields(Set<SysFormField> sysFormFields) {
		this.sysFormFields = sysFormFields;
	}

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
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取className
	 * 
	 * @return className className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * 设置className
	 * 
	 * @param className
	 *            className
	 */
	public void setClassName(String className) {
		this.className = className;
	}

}
