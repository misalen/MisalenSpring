package org.misalen.generator.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.misalen.common.annotations.ModelComment;
import org.misalen.db.jpa.base.domain.BaseDomain;
import org.misalen.hibernate.tool.util.Named;

@Entity
@Table
public class SysFormField extends BaseDomain {

	@ModelComment("元素ID")
	@Column(length = 255, nullable = false)
	private String elementId;

	@ModelComment("表单ID")
	@Column(length = 255, nullable = false)
	private String formId;

	@ModelComment("标题")
	@Column(length = 255, nullable = false)
	private String title;

	@ModelComment("类型")
	@Column(length = 255, nullable = false)
	private String type;

	@Column
	@ModelComment("提示")
	private String placeholder;

	@Column
	@ModelComment("正则校验")
	private String regularity;

	@Column
	@ModelComment("文字长度")
	private Integer length;

	@Column
	@ModelComment("最大值")
	private Integer max;

	@Column
	@ModelComment("最小值")
	private Integer min;

	@Column
	@ModelComment("小数位数")
	private Integer decimals;

	@Column
	@ModelComment("字典标识")
	private String dtcode;

	@Column
	@ModelComment("后缀")
	private String suffix;

	@ModelComment("是否必填")
	@Column(nullable = false)
	private Boolean mandatory;

	@ModelComment("排序")
	@Column(nullable = false, name = "SEQ")
	private Integer index;

	@Transient
	@ModelComment("名称,模板生成用到")
	private Named named;

	@Transient
	@ModelComment("java类型,模板生成用到")
	private String javaType;

	/**
	 * 获取suffix
	 * 
	 * @return suffix suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * 设置suffix
	 * 
	 * @param suffix
	 *            suffix
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * 获取index
	 * 
	 * @return index index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * 设置index
	 * 
	 * @param index
	 *            index
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * 获取type
	 * 
	 * @return type type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置type
	 * 
	 * @param type
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取named
	 * 
	 * @return named named
	 */
	public Named getNamed() {
		return named;
	}

	/**
	 * 设置named
	 * 
	 * @param named
	 *            named
	 */
	public void setNamed(Named named) {
		this.named = named;
	}

	/**
	 * 获取javaType
	 * 
	 * @return javaType javaType
	 */
	public String getJavaType() {
		return javaType;
	}

	/**
	 * 设置javaType
	 * 
	 * @param javaType
	 *            javaType
	 */
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	/**
	 * 获取elementId
	 * 
	 * @return elementId elementId
	 */
	public String getElementId() {
		return elementId;
	}

	/**
	 * 设置elementId
	 * 
	 * @param elementId
	 *            elementId
	 */
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	/**
	 * 获取formId
	 * 
	 * @return formId formId
	 */
	public String getFormId() {
		return formId;
	}

	/**
	 * 设置formId
	 * 
	 * @param formId
	 *            formId
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}

	/**
	 * 获取title
	 * 
	 * @return title title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置title
	 * 
	 * @param title
	 *            title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取placeholder
	 * 
	 * @return placeholder placeholder
	 */
	public String getPlaceholder() {
		return placeholder;
	}

	/**
	 * 设置placeholder
	 * 
	 * @param placeholder
	 *            placeholder
	 */
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	/**
	 * 获取regularity
	 * 
	 * @return regularity regularity
	 */
	public String getRegularity() {
		return regularity;
	}

	/**
	 * 设置regularity
	 * 
	 * @param regularity
	 *            regularity
	 */
	public void setRegularity(String regularity) {
		this.regularity = regularity;
	}

	/**
	 * 获取length
	 * 
	 * @return length length
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * 设置length
	 * 
	 * @param length
	 *            length
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * 获取max
	 * 
	 * @return max max
	 */
	public Integer getMax() {
		return max;
	}

	/**
	 * 设置max
	 * 
	 * @param max
	 *            max
	 */
	public void setMax(Integer max) {
		this.max = max;
	}

	/**
	 * 获取min
	 * 
	 * @return min min
	 */
	public Integer getMin() {
		return min;
	}

	/**
	 * 设置min
	 * 
	 * @param min
	 *            min
	 */
	public void setMin(Integer min) {
		this.min = min;
	}

	/**
	 * 获取decimals
	 * 
	 * @return decimals decimals
	 */
	public Integer getDecimals() {
		return decimals;
	}

	/**
	 * 设置decimals
	 * 
	 * @param decimals
	 *            decimals
	 */
	public void setDecimals(Integer decimals) {
		this.decimals = decimals;
	}

	/**
	 * 获取dtcode
	 * 
	 * @return dtcode dtcode
	 */
	public String getDtcode() {
		return dtcode;
	}

	/**
	 * 设置dtcode
	 * 
	 * @param dtcode
	 *            dtcode
	 */
	public void setDtcode(String dtcode) {
		this.dtcode = dtcode;
	}

	/**
	 * 获取mandatory
	 * 
	 * @return mandatory mandatory
	 */
	public Boolean getMandatory() {
		return mandatory;
	}

	/**
	 * 设置mandatory
	 * 
	 * @param mandatory
	 *            mandatory
	 */
	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

}
