package org.misalen.generator.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.misalen.common.annotations.ModelComment;
import org.misalen.db.jpa.base.domain.BaseDomain;

@Entity
@Table
@ModelComment(value = "")
public class SysTableInfo extends BaseDomain {

	@ModelComment("名称")
	@Column(length = 255, nullable = false)
	private String name;

	@ModelComment("映射类型表或者视图")
	@Column(length = 100, nullable = false, updatable = false)
	@ColumnDefault("'TABLE'")
	private String ormType;

	@ModelComment("映射名称")
	@Column(length = 100, nullable = false, updatable = false)
	private String ormName;

	@OneToMany(targetEntity = SysTableColumn.class, cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "TABLE_ID", updatable = false)
	@ModelComment("与字典数据的关系")
	private Set<SysTableColumn> sysTableColumns;

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
	 * 获取ormType
	 * 
	 * @return ormType ormType
	 */
	public String getOrmType() {
		return ormType;
	}

	/**
	 * 设置ormType
	 * 
	 * @param ormType
	 *            ormType
	 */
	public void setOrmType(String ormType) {
		this.ormType = ormType;
	}

	/**
	 * 获取ormName
	 * 
	 * @return ormName ormName
	 */
	public String getOrmName() {
		return ormName;
	}

	/**
	 * 设置ormName
	 * 
	 * @param ormName
	 *            ormName
	 */
	public void setOrmName(String ormName) {
		this.ormName = ormName;
	}

	/**
	 * 获取sysTableColumns
	 * 
	 * @return sysTableColumns sysTableColumns
	 */
	public Set<SysTableColumn> getSysTableColumns() {
		return sysTableColumns;
	}

	/**
	 * 设置sysTableColumns
	 * 
	 * @param sysTableColumns
	 *            sysTableColumns
	 */
	public void setSysTableColumns(Set<SysTableColumn> sysTableColumns) {
		this.sysTableColumns = sysTableColumns;
	}

}
