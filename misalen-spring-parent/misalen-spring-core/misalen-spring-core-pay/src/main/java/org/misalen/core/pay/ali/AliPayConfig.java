package org.misalen.core.pay.ali;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.egzosn.pay.ali.api.AliPayConfigStorage;
import com.egzosn.pay.ali.api.AliPayService;

@Component
@ConfigurationProperties(prefix = "misalen.pay.alipay")
public class AliPayConfig {
	/** 应用id */
	private String appId;
	/** 商户签约拿到的pid,partner_id的简称，合作伙伴身份等同于 partner */
	private String pid;
	/** 商户收款账号 */
	private String seller;
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

	public AliPayConfigStorage getAliPayConfigStorage() {
		AliPayConfigStorage aliPayConfigStorage = new AliPayConfigStorage();
		aliPayConfigStorage.setPid(pid);
		aliPayConfigStorage.setAppId(appId);
		aliPayConfigStorage.setKeyPublic(keyPublic);
		aliPayConfigStorage.setKeyPrivate(keyPrivate);
		aliPayConfigStorage.setNotifyUrl(notifyUrl);
		aliPayConfigStorage.setReturnUrl(returnUrl);
		aliPayConfigStorage.setSignType(signType);
		aliPayConfigStorage.setSeller(seller);
		aliPayConfigStorage.setInputCharset(inputCharset);
		return aliPayConfigStorage;
	}

	/**
	 * 使用文档地址：https://github.com/egzosn/pay-java-parent/tree/master/pay-java-ali
	 * 
	 * @return
	 */
	@Bean
	public AliPayService getAliPayService() {
		return new AliPayService(getAliPayConfigStorage());
	}

}
