package org.misalen.generator.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.misalen.common.annotations.ModelComment;
import org.misalen.db.jpa.base.domain.BaseDomain;

@Entity
@Table
public class SysFlowInfo extends BaseDomain {

	@ModelComment("名称")
	@Column(length = 255, nullable = false)
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
	public void setName(String name) {
		this.name = name;
	}

}
