package org.misalen.core.wxpay.model;

public class WechatPayUnifiedParam extends WechatPayParam {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WechatPayUnifiedParam() {
		put("trade_type", "APP");
	}

	/**
	 * 商品描述
	 * 
	 * @param value
	 */
	public void setBody(String value) {
		put("body", value);
	}

	/**
	 * 商户订单号 要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
	 * 
	 * @param value
	 */
	public void setOutTradeNo(String value) {
		put("out_trade_no", value);
	}

	/**
	 * 标价金额 订单总金额，单位为分
	 * 
	 * @param value
	 */
	public void setTotalFee(Integer value) {
		put("total_fee", value.toString());
	}

	/**
	 * 终端IP
	 * 
	 * @param value
	 */
	public void setSpbillCreateIp(String value) {
		put("spbill_create_ip", value);
	}

	/**
	 * 通知地址
	 * 
	 * @param value
	 */
	public void setNotifyUrl(String value) {
		put("notify_url", value);
	}

	/**
	 * 交易类型 取值如下：JSAPI，NATIVE，APP等，说明详见
	 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_2
	 * 
	 * @param value
	 */
	public void setTradeType(String value) {
		put("trade_type", value);
	}

	@Override
	String[] mandatory() {
		return new String[] { "body", "out_trade_no", "total_fee", "spbill_create_ip", "notify_url", "trade_type" };
	}

}
