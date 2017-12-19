package org.misalen.api.forms;

import org.misalen.common.annotations.Enumeration;
import org.misalen.common.annotations.ModelComment;
import org.misalen.common.validate.annotation.MobilePhone;
import org.misalen.common.validate.annotation.Required;

public class LoginForm extends BasePageForm{

	@MobilePhone
	@ModelComment(value = "手机号")
	private String mobile;

	@ModelComment(value = "密码")
	@Required
	private String password;
	
	@Required
	@ModelComment(value = "设备唯一标识")
	@Enumeration("test")
	private String imei;
	
	@Required
	@ModelComment(value = "设备唯一标识")
	private LoginForm2 loginForm2;

	/**  
	 * 获取loginForm2  
	 * @return loginForm2 loginForm2  
	 */
	public LoginForm2 getLoginForm2() {
		return loginForm2;
	}
	

	/**  
	 * 设置loginForm2  
	 * @param loginForm2 loginForm2  
	 */
	public void setLoginForm2(LoginForm2 loginForm2) {
		this.loginForm2 = loginForm2;
	}
	

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
