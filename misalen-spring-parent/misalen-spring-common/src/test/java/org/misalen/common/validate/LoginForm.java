package org.misalen.common.validate;

import org.misalen.common.validate.annotation.Email;
import org.misalen.common.validate.annotation.Length;
import org.misalen.common.validate.annotation.MobilePhone;
import org.misalen.common.validate.annotation.Required;

public class LoginForm {

	@MobilePhone
	private String mobile;

	@Required
	private String password;

	@Email
	private String email;

	@Length(length = 18)
	private String idCard;

	/**
	 * 获取mobile
	 * 
	 * @return mobile mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置mobile
	 * 
	 * @param mobile
	 *            mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取password
	 * 
	 * @return password password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置password
	 * 
	 * @param password
	 *            password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取email
	 * 
	 * @return email email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置email
	 * 
	 * @param email
	 *            email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取idCard
	 * 
	 * @return idCard idCard
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * 设置idCard
	 * 
	 * @param idCard
	 *            idCard
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

}
