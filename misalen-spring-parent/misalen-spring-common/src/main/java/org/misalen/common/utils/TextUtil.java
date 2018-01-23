package org.misalen.common.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author guochao
 *
 */
public class TextUtil {

	/**
	 * 判断 ""/null
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isNullOrEmpty(String text) {
		return text == null || text.isEmpty();
	}

	public static boolean isDateString(String datevalue) {
		return isDateString(datevalue, "yyyy-MM-dd");
	}

	public static boolean isDateString(String datevalue, String dateFormat) {
		if (isNullOrEmpty(datevalue)) {
			return false;
		}
		try {
			SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
			java.util.Date dd = fmt.parse(datevalue);
			if (datevalue.equals(fmt.format(dd))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * md5 32位
	 * 
	 * @param s
	 * @return
	 */
	public static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * md5 16位
	 * 
	 * @param s
	 * @return
	 */
	public static String MD5bit16(String s) {
		String md5bit32 = MD5(s);
		return md5bit32.substring(8, 24);
	}

	public static String getNonceNum(int count) {
		StringBuffer str = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			str.append(random.nextInt(10));
		}
		return str.toString();
	}

	public static String arrayToString(List<String> strings) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strings.size(); i++) {
			sb.append(strings.get(i));
			if (i < strings.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	/**
	 * 随机字符串
	 * 
	 * @param count
	 *            长度
	 * @return
	 */
	public static String getNonceStr(int count) {
		char[] radixDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
				'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int length = radixDigits.length;
		StringBuffer nonceStr = new StringBuffer();
		for (int i = 0; i < count; i++) {
			Random random = new Random();
			nonceStr.append(radixDigits[random.nextInt(length)]);
		}
		return nonceStr.toString();
	}

	/**
	 * 首字母转小写
	 * 
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}

	/**
	 * 首字母转大写
	 * 
	 * @param s
	 * @return
	 */
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}

	/**
	 * 转换为下划线
	 * 
	 * @param camelCaseName
	 * @return
	 */
	public static String underscoreName(String camelCaseName) {
		StringBuilder result = new StringBuilder();
		if (camelCaseName != null && camelCaseName.length() > 0) {
			result.append(camelCaseName.substring(0, 1).toLowerCase());
			for (int i = 1; i < camelCaseName.length(); i++) {
				char ch = camelCaseName.charAt(i);
				if (Character.isUpperCase(ch)) {
					result.append("_");
					result.append(Character.toLowerCase(ch));
				} else {
					result.append(ch);
				}
			}
		}
		return result.toString();
	}

	/**
	 * 转换为驼峰
	 * 
	 * @param underscoreName
	 * @return
	 */
	public static String camelCaseName(String underscoreName) {
		StringBuilder result = new StringBuilder();
		if (underscoreName != null && underscoreName.length() > 0) {
			boolean flag = false;
			for (int i = 0; i < underscoreName.length(); i++) {
				char ch = underscoreName.charAt(i);
				if ("_".charAt(0) == ch) {
					flag = true;
				} else {
					if (flag) {
						result.append(Character.toUpperCase(ch));
						flag = false;
					} else {
						result.append(ch);
					}
				}
			}
		}
		return result.toString();
	}

	public static String join(String[] strings) {
		if (strings != null) {
			StringBuffer sb = new StringBuffer();
			for (String string : strings) {
				sb.append(string);
				sb.append(",");
			}
			return sb.substring(0, sb.length() - 1);
		}
		return null;
	}
}
