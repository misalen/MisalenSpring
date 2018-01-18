package org.misalen.core.pay.wechat;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.egzosn.pay.wx.api.WxPayConfigStorage;
import com.egzosn.pay.wx.api.WxPayService;

@Component
@PropertySource(value = { "classpath:*.properties", "classpath:misalen-pay.properties" }, ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "misalen.pay.wechat")
public class WeChatPayConfig {
	/** 应用id */
	private String appid;
	/** 商户号 合作者id */
	private String mchId;
	/** 应用私钥，rsa_private pkcs8格式 生成签名时使用 */
	private String keyPrivate;
	/** 支付平台公钥(签名校验使用) */
	private String keyPublic;
	/** 异步回调地址 */
	private String notifyUrl;
	/** 同步回调地址，支付完成后展示的页面 */
	private String returnUrl;
	/** 签名加密类型 */
	private String signType;
	/** 字符类型 */
	private String inputCharset = "UTF-8";

	/**
	 * 获取应用id
	 * 
	 * @return appid 应用id
	 */
	public String getAppid() {
		return appid;
	}

	/**
	 * 设置应用id
	 * 
	 * @param appid
	 *            应用id
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}

	/**
	 * 获取商户号合作者id
	 * 
	 * @return mchId 商户号合作者id
	 */
	public String getMchId() {
		return mchId;
	}

	/**
	 * 设置商户号合作者id
	 * 
	 * @param mchId
	 *            商户号合作者id
	 */
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	/**
	 * 获取应用私钥，rsa_privatepkcs8格式生成签名时使用
	 * 
	 * @return keyPrivate 应用私钥，rsa_privatepkcs8格式生成签名时使用
	 */
	public String getKeyPrivate() {
		return keyPrivate;
	}

	/**
	 * 设置应用私钥，rsa_privatepkcs8格式生成签名时使用
	 * 
	 * @param keyPrivate
	 *            应用私钥，rsa_privatepkcs8格式生成签名时使用
	 */
	public void setKeyPrivate(String keyPrivate) {
		this.keyPrivate = keyPrivate;
	}

	/**
	 * 获取支付平台公钥(签名校验使用)
	 * 
	 * @return keyPublic 支付平台公钥(签名校验使用)
	 */
	public String getKeyPublic() {
		return keyPublic;
	}

	/**
	 * 设置支付平台公钥(签名校验使用)
	 * 
	 * @param keyPublic
	 *            支付平台公钥(签名校验使用)
	 */
	public void setKeyPublic(String keyPublic) {
		this.keyPublic = keyPublic;
	}

	/**
	 * 获取异步回调地址
	 * 
	 * @return notifyUrl 异步回调地址
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}

	/**
	 * 设置异步回调地址
	 * 
	 * @param notifyUrl
	 *            异步回调地址
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	/**
	 * 获取同步回调地址，支付完成后展示的页面
	 * 
	 * @return returnUrl 同步回调地址，支付完成后展示的页面
	 */
	public String getReturnUrl() {
		return returnUrl;
	}

	/**
	 * 设置同步回调地址，支付完成后展示的页面
	 * 
	 * @param returnUrl
	 *            同步回调地址，支付完成后展示的页面
	 */
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	/**
	 * 获取签名加密类型
	 * 
	 * @return signType 签名加密类型
	 */
	public String getSignType() {
		return signType;
	}

	/**
	 * 设置签名加密类型
	 * 
	 * @param signType
	 *            签名加密类型
	 */
	public void setSignType(String signType) {
		this.signType = signType;
	}

	/**
	 * 获取字符类型
	 * 
	 * @return inputCharset 字符类型
	 */
	public String getInputCharset() {
		return inputCharset;
	}

	/**
	 * 设置字符类型
	 * 
	 * @param inputCharset
	 *            字符类型
	 */
	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}

	public WxPayConfigStorage getWxPayConfigStorage() {
		WxPayConfigStorage wxPayConfigStorage = new WxPayConfigStorage();
		wxPayConfigStorage.setMchId(mchId);
		wxPayConfigStorage.setAppid(appid);
		wxPayConfigStorage.setKeyPublic(keyPublic);
		wxPayConfigStorage.setKeyPrivate(keyPrivate);
		wxPayConfigStorage.setNotifyUrl(notifyUrl);
		wxPayConfigStorage.setReturnUrl(returnUrl);
		wxPayConfigStorage.setSignType(signType);
		wxPayConfigStorage.setInputCharset(inputCharset);
		return wxPayConfigStorage;
	}

	/**
	 * 使用文档地址：https://github.com/egzosn/pay-java-parent/tree/master/pay-java-wx
	 * 
	 * @return
	 */
	@Bean
	public WxPayService getWxPayService() {
		return new WxPayService(getWxPayConfigStorage());
	}

}
