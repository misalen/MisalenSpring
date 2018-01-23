package org.misalen.permission.domain;

import java.util.LinkedList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.misalen.common.annotations.ModelComment;
import org.misalen.db.jpa.base.domain.BaseDomain;

/**
 * 资源表
 * 
 * @author guochao
 *
 */
@Entity
@Table
public class SysResources extends BaseDomain {

	@ModelComment("资源名称")
	@Column(name = "RESOURCE_NAME", length = 50, nullable = false)
	private String text;

	@ModelComment("自关联id")
	@Column
	private String parentId;

	@ModelComment("图标")
	@Column(length = 50)
	private String icon;

	@ModelComment("排序字段")
	@Column(length = 10, nullable = false)
	private Integer seq;

	@ModelComment("资源地址")
	@Column
	private String resourceUrl;

	@ModelComment("保存类型")
	@Column(length = 20, nullable = false)
	private String saveType;

	@ModelComment("状态")
	@Column(nullable = false, updatable = false)
	private String usingState;

	@ModelComment("角色资源关系")
	@OneToMany(targetEntity = SysRoleResources.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "RESOURCES_ID", updatable = false)
	private Set<SysRoleResources> sysRoleResources;

	@ModelComment("自关联关系")
	@OneToMany(targetEntity = SysResources.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID", updatable = false)
	@OrderBy("seq ASC")
	private Set<SysResources> nodes;

	@Transient
	@ModelComment("子菜单")
	private LinkedList<SysResources> children;

	/**
	 * 获取nodes
	 * 
	 * @return nodes nodes
	 */
	public Set<SysResources> getNodes() {
		return nodes;
	}

	/**
	 * 设置nodes
	 * 
	 * @param nodes
	 *            nodes
	 */
	public void setNodes(Set<SysResources> nodes) {
		this.nodes = nodes;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取children
	 * 
	 * @return children children
	 */
	public LinkedList<SysResources> getChildren() {
		return children;
	}

	/**
	 * 设置children
	 * 
	 * @param children
	 *            children
	 */
	public void setChildren(LinkedList<SysResources> children) {
		this.children = children;
	}

	public Set<SysRoleResources> getSysRoleResources() {
		return sysRoleResources;
	}

	public void setSysRoleResources(Set<SysRoleResources> sysRoleResources) {
		this.sysRoleResources = sysRoleResources;
	}

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public String getSaveType() {
		return saveType;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	public String getUsingState() {
		return usingState;
	}

	public void setUsingState(String usingState) {
		this.usingState = usingState;
	}

	public static enum SaveType {
		menu1, menu2, button
	}
}
