package org.misalen.common.utils;

import java.util.List;

/**
 * 
 * list工具类
 * 
 * @author guochao
 *
 */
public class ListUtil {

	public static boolean isNullOrEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}

}
