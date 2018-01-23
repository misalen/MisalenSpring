package org.misalen.permission.domain;

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
 * 角色/组织机构
 * 
 * @author guochao
 *
 */
@Entity
@Table
public class SysRole extends BaseDomain {

	@ModelComment("名称")
	@Column(name = "ROLE_NAME", length = 50, nullable = false)
	private String text;

	@ModelComment("描述")
	@Column(name = "ROLE_DESC", length = 50)
	private String desc;

	@ModelComment("编码")
	@Column(length = 50, nullable = false)
	private String code;

	@ModelComment("状态")
	@Column(nullable = false, updatable = false)
	private String usingState;

	@ModelComment("用户角色关系")
	@OneToMany(targetEntity = SysAdminRole.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", updatable = false)
	private Set<SysAdminRole> sysAdminRoles;

	@ModelComment("角色资源关系")
	@OneToMany(targetEntity = SysRoleResources.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", updatable = false)
	private Set<SysRoleResources> sysRoleResources;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUsingState() {
		return usingState;
	}

	public void setUsingState(String usingState) {
		this.usingState = usingState;
	}

	public Set<SysAdminRole> getSysAdminRoles() {
		return sysAdminRoles;
	}

	public void setSysAdminRoles(Set<SysAdminRole> sysAdminRoles) {
		this.sysAdminRoles = sysAdminRoles;
	}

	public Set<SysRoleResources> getSysRoleResources() {
		return sysRoleResources;
	}

	public void setSysRoleResources(Set<SysRoleResources> sysRoleResources) {
		this.sysRoleResources = sysRoleResources;
	}

}
