package org.misalen.permission.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.misalen.common.annotations.ModelComment;
import org.misalen.db.jpa.base.domain.BaseDomain;

/**
 * 管理员
 * 
 * @author guochao
 *
 */
@Entity
@Table
@DynamicUpdate
@DynamicInsert
public class SysAdmin extends BaseDomain {

	@ModelComment("用户名")
	@Column(length = 50, nullable = false)
	private String username;

	@ModelComment("昵称")
	@ColumnDefault("''")
	@Column(length = 50)
	private String nickname;

	@ModelComment("登录密码")
	@Column(length = 50, nullable = false)
	private String loginPwd;

	@OneToMany(targetEntity = SysAdminRole.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "ADMIN_ID", updatable = false)
	@ModelComment("用户角色关系")
	private Set<SysAdminRole> sysAdminRoles;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Set<SysAdminRole> getSysAdminRoles() {
		return sysAdminRoles;
	}

	public void setSysAdminRoles(Set<SysAdminRole> sysAdminRoles) {
		this.sysAdminRoles = sysAdminRoles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

}
