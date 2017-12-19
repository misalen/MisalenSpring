package org.misalen.core.wxpay.model;

import java.util.HashMap;

abstract class WechatPayParam extends HashMap<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final void validation() throws Exception {
		for (String entry : mandatory()) {
			String value=get(entry);
			if (value==null||value.equals("")) {
				throw new Exception(entry + "字段不能为空");
			}
		}
	}

	abstract String[] mandatory();
}
