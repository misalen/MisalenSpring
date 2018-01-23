package org.misalen.permission.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class SysAdminRole extends BaseDomain {

	@ModelComment("用户id")
	@Column
	private String adminId;

	@ModelComment("角色id")
	@Column
	private String roleId;

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
