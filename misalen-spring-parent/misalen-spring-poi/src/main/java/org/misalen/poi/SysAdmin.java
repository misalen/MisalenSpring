package org.misalen.poi;

import java.util.Date;

import org.misalen.common.annotations.Enumeration;
import org.misalen.common.annotations.ModelComment;

@ModelComment("管理员信息")
public class SysAdmin {

	@ModelComment("用户名")
	private String username;

	@ModelComment("昵称")
	private String nickname;

	@ModelComment("登录密码")
	private String loginPwd;

	@ModelComment("出生日期")
	private Date birthday;
	
	@Enumeration("gender")
	@ModelComment("性别 ")
	private String gender;

	/**  
	 * 获取birthday  
	 * @return birthday birthday  
	 */
	public Date getBirthday() {
		return birthday;
	}
	

	/**  
	 * 设置birthday  
	 * @param birthday birthday  
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	

	/**  
	 * 获取gender  
	 * @return gender gender  
	 */
	public String getGender() {
		return gender;
	}
	

	/**  
	 * 设置gender  
	 * @param gender gender  
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	

	/**
	 * 获取username
	 * 
	 * @return username username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置username
	 * 
	 * @param username
	 *            username
	 */
	public void setUsername(String username) {
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
	 * 获取loginPwd
	 * 
	 * @return loginPwd loginPwd
	 */
	public String getLoginPwd() {
		return loginPwd;
	}

	/**
	 * 设置loginPwd
	 * 
	 * @param loginPwd
	 *            loginPwd
	 */
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

}
