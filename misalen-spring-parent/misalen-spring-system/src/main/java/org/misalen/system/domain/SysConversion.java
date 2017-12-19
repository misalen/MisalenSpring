package org.misalen.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.misalen.common.annotations.ModelComment;
import org.misalen.db.jpa.base.domain.BaseDomain;

@Entity
@Table
public class SysConversion extends BaseDomain {
	@ModelComment("汉字")
	@Column(length = 255, nullable = false, unique = true)
	private String chinese;

	@ModelComment("汉语拼音")
	@Column(length = 255, nullable = false, unique = true)
	private String pinyin;

	/**
	 * 获取chinese
	 * 
	 * @return chinese chinese
	 */
	public String getChinese() {
		return chinese;
	}

	/**
	 * 设置chinese
	 * 
	 * @param chinese
	 *            chinese
	 */
	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	/**
	 * 获取pinyin
	 * 
	 * @return pinyin pinyin
	 */
	public String getPinyin() {
		return pinyin;
	}

	/**
	 * 设置pinyin
	 * 
	 * @param pinyin
	 *            pinyin
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

}
