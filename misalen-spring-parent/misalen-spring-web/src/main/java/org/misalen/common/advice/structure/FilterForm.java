package org.misalen.common.advice.structure;

import org.misalen.common.validate.annotation.Length;
import org.misalen.common.validate.annotation.Required;

/**
 * imei 设备IMEI号
 * 
 * macCode 设备类型(Android/iOS 固定值)
 *
 * deviceInfo 设备型号
 *
 * osVersion 系统版本
 *
 * macTime 系统时间(时间戳) 13位
 *
 * version 客户端版本
 *
 * userid 用户id
 * 
 * nonceStr 随机数 十位
 *
 * sign 签名 String
 * 
 * @author DO·VIS
 *
 */
public class FilterForm {

	@Required
	private String imei;
	@Required
	private String macCode;
	@Length(length = 13)
	private Long macTime;
	@Length(length = 10)
	private String nonceStr;
	@Length(length = 32)
	private String sign;
	
	private String deviceInfo;
	private String osVersion;
	private String version;

	/**  
	 * 获取imei  
	 * @return imei imei  
	 */
	public String getImei() {
		return imei;
	}
	
	/**  
	 * 设置imei  
	 * @param imei imei  
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}
	
	/**  
	 * 获取macCode  
	 * @return macCode macCode  
	 */
	public String getMacCode() {
		return macCode;
	}
	
	/**  
	 * 设置macCode  
	 * @param macCode macCode  
	 */
	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}
	
	/**  
	 * 获取macTime  
	 * @return macTime macTime  
	 */
	public Long getMacTime() {
		return macTime;
	}
	
	/**  
	 * 设置macTime  
	 * @param macTime macTime  
	 */
	public void setMacTime(Long macTime) {
		this.macTime = macTime;
	}
	
	/**  
	 * 获取nonceStr  
	 * @return nonceStr nonceStr  
	 */
	public String getNonceStr() {
		return nonceStr;
	}
	
	/**  
	 * 设置nonceStr  
	 * @param nonceStr nonceStr  
	 */
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	
	/**  
	 * 获取deviceInfo  
	 * @return deviceInfo deviceInfo  
	 */
	public String getDeviceInfo() {
		return deviceInfo;
	}
	
	/**  
	 * 设置deviceInfo  
	 * @param deviceInfo deviceInfo  
	 */
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	
	/**  
	 * 获取osVersion  
	 * @return osVersion osVersion  
	 */
	public String getOsVersion() {
		return osVersion;
	}
	
	/**  
	 * 设置osVersion  
	 * @param osVersion osVersion  
	 */
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	
	/**  
	 * 获取version  
	 * @return version version  
	 */
	public String getVersion() {
		return version;
	}
	
	/**  
	 * 设置version  
	 * @param version version  
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**  
	 * 获取sign  
	 * @return sign sign  
	 */
	public String getSign() {
		return sign;
	}
	
	/**  
	 * 设置sign  
	 * @param sign sign  
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FilterForm [imei=" + imei + ", macCode=" + macCode + ", macTime=" + macTime + ", nonceStr=" + nonceStr
				+ ", deviceInfo=" + deviceInfo + ", osVersion=" + osVersion + ", version=" + version
				+ "]";
	}

}
