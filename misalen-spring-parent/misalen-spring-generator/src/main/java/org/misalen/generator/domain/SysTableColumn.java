package org.misalen.generator.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.misalen.common.annotations.ModelComment;
import org.misalen.db.jpa.base.domain.BaseDomain;

@Entity
@Table
public class SysTableColumn extends BaseDomain {

	@ModelComment("表Id")
	@Column(length = 255, nullable = false)
	private String tableId;

	@ModelComment("映射名称")
	@Column(length = 100, nullable = false)
	private String name;

	@ModelComment("注释")
	@Column(length = 50, nullable = false)
	private String remarks;

	@ModelComment("类型")
	@Column(length = 50, nullable = false)
	private String type;

	@ModelComment("是否必填")
	@Column(nullable = false)
	private Boolean nullable;

	@Column
	@ModelComment("小数位数")
	private Integer scale;

	@Column
	@ModelComment("长度")
	private Integer length;
	
	@ModelComment("是否到添加页面")
	@Column(nullable = false)
	private Boolean accessAdd=true;
	
	@ModelComment("是否到更新页面")
	@Column(nullable = false)
	private Boolean accessUpdate=true;
	
	@ModelComment("是否到list字段")
	@Column(nullable = false)
	private Boolean accessList=true;
	
	@ModelComment("是否是搜索条件")
	@Column(nullable = false)
	private Boolean accessSearch=false;
	
	
	/**  
	 * 获取accessAdd  
	 * @return accessAdd accessAdd  
	 */
	public Boolean getAccessAdd() {
		return accessAdd;
	}
	

	/**  
	 * 设置accessAdd  
	 * @param accessAdd accessAdd  
	 */
	public void setAccessAdd(Boolean accessAdd) {
		this.accessAdd = accessAdd;
	}
	

	/**  
	 * 获取accessUpdate  
	 * @return accessUpdate accessUpdate  
	 */
	public Boolean getAccessUpdate() {
		return accessUpdate;
	}
	

	/**  
	 * 设置accessUpdate  
	 * @param accessUpdate accessUpdate  
	 */
	public void setAccessUpdate(Boolean accessUpdate) {
		this.accessUpdate = accessUpdate;
	}
	

	/**  
	 * 获取accessList  
	 * @return accessList accessList  
	 */
	public Boolean getAccessList() {
		return accessList;
	}
	

	/**  
	 * 设置accessList  
	 * @param accessList accessList  
	 */
	public void setAccessList(Boolean accessList) {
		this.accessList = accessList;
	}
	

	/**  
	 * 获取accessSearch  
	 * @return accessSearch accessSearch  
	 */
	public Boolean getAccessSearch() {
		return accessSearch;
	}
	

	/**  
	 * 设置accessSearch  
	 * @param accessSearch accessSearch  
	 */
	public void setAccessSearch(Boolean accessSearch) {
		this.accessSearch = accessSearch;
	}
	

	/**
	 * 获取scale
	 * 
	 * @return scale scale
	 */
	public Integer getScale() {
		return scale;
	}

	/**
	 * 设置scale
	 * 
	 * @param scale
	 *            scale
	 */
	public void setScale(Integer scale) {
		this.scale = scale;
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
	 * 获取tableId
	 * 
	 * @return tableId tableId
	 */
	public String getTableId() {
		return tableId;
	}

	/**
	 * 设置tableId
	 * 
	 * @param tableId
	 *            tableId
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
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
	 * 获取remarks
	 * 
	 * @return remarks remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 设置remarks
	 * 
	 * @param remarks
	 *            remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	 * 获取nullable
	 * 
	 * @return nullable nullable
	 */
	public Boolean getNullable() {
		return nullable;
	}

	/**
	 * 设置nullable
	 * 
	 * @param nullable
	 *            nullable
	 */
	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

}
