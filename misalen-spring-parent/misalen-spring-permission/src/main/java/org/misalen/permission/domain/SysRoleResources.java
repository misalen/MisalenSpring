package org.misalen.permission.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
public class SysRoleResources extends BaseDomain {

	@ModelComment("资源id")
	@Column
	private String resourcesId;

	@ModelComment("角色id")
	@Column
	private String roleId;

	public String getResourcesId() {
		return resourcesId;
	}

	public void setResourcesId(String resourcesId) {
		this.resourcesId = resourcesId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
