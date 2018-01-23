/**
 *
 */
package org.misalen.permission.common;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * shiro用户对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * 
 * @author guochao
 *
 */
public class ShiroUser implements Serializable {

	private static final long serialVersionUID = -1373760761780840081L;
	private String userId;
	private String username;
	private Set<String> roleSet = new HashSet<String>();
	private String nickname;

	public ShiroUser(String userId, String username) {
		super();
		this.userId = userId;
		this.username = username;
	}

	/**
	 * 获取nickname
	 * 
	 * @return nickname nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置nickname
	 * 
	 * @param nickname
	 *            nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取serialVersionUID
	 * 
	 * @return serialVersionUID serialVersionUID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<String> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<String> roleSet) {
		this.roleSet = roleSet;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return username;
	}
}