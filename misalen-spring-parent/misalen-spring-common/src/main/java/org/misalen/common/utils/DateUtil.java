package org.misalen.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author guochao
 *
 */
public class DateUtil {

	/**
	 * 日期转string
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 日期转string
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date, String string) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(string);
		return sdf.format(date);
	}

	/**
	 * string转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseString(String date) {
		return parseString(date, "yyyy-MM-dd");

	}

	/**
	 * string转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseString(String date, String string) {
		SimpleDateFormat sdf = new SimpleDateFormat(string);
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 计算相差多少分钟
	 * 
	 * @param dateA
	 * @param dateB
	 * @return
	 */
	public static long diff(Date dateA, Date dateB) {
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long diff = dateA.getTime() - dateB.getTime();
		long day = diff / nd;// 计算差多少天
		long min = diff % nd % nh / nm + day * 24 * 60;
		return min;
	}

	/**
	 * 
	 */
	public static Date getToday() {
		return getDate(0);
	}

	public static Date getDate(int day) {
		return add(new Date(), day);
	}

	public static Date add(Date dateA, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateA);
		calendar.add(Calendar.DATE, day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getWeek(int week) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day + week * 7);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getMonth(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

}
