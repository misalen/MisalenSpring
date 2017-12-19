package org.misalen.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.misalen.common.annotations.ModelComment;
import org.misalen.db.jpa.base.domain.BaseDomain;

/**
 * 字典
 * 
 * @author guochao
 *
 */
@Entity
@Table
public class SysDictionaryData extends BaseDomain {

	@ModelComment("字典id")
	@Column(nullable = false)
	private String dtId;

	@ModelComment("名称")
	@Column(length = 50, nullable = false)
	private String name;

	@ModelComment("编码")
	@Column(length = 50, nullable = false)
	private String code;

	/**
	 * 获取dtId
	 * 
	 * @return dtId dtId
	 */
	public String getDtId() {
		return dtId;
	}

	/**
	 * 设置dtId
	 * 
	 * @param dtId
	 *            dtId
	 */
	public void setDtId(String dtId) {
		this.dtId = dtId;
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
	 * 获取code
	 * 
	 * @return code code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置code
	 * 
	 * @param code
	 *            code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String toJson() {
		return "{\"code\":\"" + code + "\",\"name\":\"" + name + "\"}";
	}
}
