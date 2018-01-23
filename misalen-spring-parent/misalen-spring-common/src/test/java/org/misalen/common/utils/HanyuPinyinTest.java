package org.misalen.common.utils;

import junit.framework.TestCase;

public class HanyuPinyinTest extends TestCase {

	public void testApp() {
		String pinyin = HanyuPinyinHelper.toHanyuPinyin("名称,模板生成用到");
		System.out.println(pinyin);
	}

}
