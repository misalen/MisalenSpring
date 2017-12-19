package org.misalen.core.wxpay.config;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.misalen.core.wxpay.sdk.IWechatPayDomain;
import org.misalen.core.wxpay.sdk.WechatPay;
import org.misalen.core.wxpay.sdk.WechatPayConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WechatPayAutoConfig extends WechatPayConfig {

	@Value("${misalen.wxpay.appId:}")
	private String appId;

	@Value("${misalen.wxpay.mchId:}")
	private String mchId;

	@Value("${misalen.wxpay.key:}")
	private String key;

	@Value("${misalen.wxpay.useSandbox:false}")
	private Boolean useSandbox;

	@Value("${misalen.wxpay.certPath:}")
	private String certPath;

	private byte[] certData;

	@Bean
	public WechatPay wechatPay() throws Exception {
		if (appId.equals("")) {
			return null;
		}
		if (certPath.equals("")) {
			File file = new File(certPath);
			InputStream certStream = new FileInputStream(file);
			this.certData = new byte[(int) file.length()];
			certStream.read(this.certData);
			certStream.close();
		}
		return new WechatPay(this);
	}

	@Override
	public String getAppID() {
		return appId;
	}

	@Override
	public String getMchID() {
		return mchId;
	}

	@Override
	public String getKey() {
		return key;
	}

	public InputStream getCertStream() {
		if (this.certData == null) {
			throw new RuntimeException("配置正确的misalen.wxpay.certPath");
		}
		ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
		return certBis;
	}

	@Override
	public IWechatPayDomain getWXPayDomain() {
		return WechatPayAutoDomain.instance();
	}

	@Override
	public boolean useSandbox() {
		return useSandbox;
	}

}
