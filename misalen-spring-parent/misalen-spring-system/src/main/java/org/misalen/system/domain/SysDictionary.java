package org.misalen.system.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
public class SysDictionary extends BaseDomain {

	@ModelComment("名称")
	@Column(length = 50, nullable = false)
	private String text;

	@ModelComment("码")
	@Column(length = 50, unique = true, nullable = false)
	private String code;

	@OneToMany(targetEntity = SysDictionaryData.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "DT_ID", updatable = false)
	@ModelComment("与字典数据的关系")
	private Set<SysDictionaryData> sysDictionaryDatas;

	/**
	 * 获取text
	 * 
	 * @return text text
	 */
	public String getText() {
		return text;
	}

	/**
	 * 设置text
	 * 
	 * @param text
	 *            text
	 */
	public void setText(String text) {
		this.text = text;
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

	/**
	 * 获取sysDictionaryDatas
	 * 
	 * @return sysDictionaryDatas sysDictionaryDatas
	 */
	public Set<SysDictionaryData> getSysDictionaryDatas() {
		return sysDictionaryDatas;
	}

	/**
	 * 设置sysDictionaryDatas
	 * 
	 * @param sysDictionaryDatas
	 *            sysDictionaryDatas
	 */
	public void setSysDictionaryDatas(Set<SysDictionaryData> sysDictionaryDatas) {
		this.sysDictionaryDatas = sysDictionaryDatas;
	}

}
