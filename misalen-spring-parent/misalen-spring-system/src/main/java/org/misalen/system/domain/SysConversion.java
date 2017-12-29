package org.misalen.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.misalen.common.annotations.ModelComment;
import org.misalen.db.jpa.base.domain.BaseDomain;

@Entity
@Table
public class SysConversion extends BaseDomain {
	@ModelComment("原始")
	@Column(length = 255, nullable = false, unique = true)
	private String original;

	@ModelComment("转义")
	@Column(name="ESCAPE_", length = 255, nullable = false, unique = true)
	private String escape;

	/**  
	 * 获取original  
	 * @return original original  
	 */
	public String getOriginal() {
		return original;
	}
	

	/**  
	 * 设置original  
	 * @param original original  
	 */
	public void setOriginal(String original) {
		this.original = original;
	}
	

	/**  
	 * 获取escape  
	 * @return escape escape  
	 */
	public String getEscape() {
		return escape;
	}
	

	/**  
	 * 设置escape  
	 * @param escape escape  
	 */
	public void setEscape(String escape) {
		this.escape = escape;
	}

}
