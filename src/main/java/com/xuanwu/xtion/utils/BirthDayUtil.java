package com.xuanwu.xtion.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: BirthDayUtil
 * @Author <a href="jiji@javawind.com">Xuefang.Xu</a>
 * @Date 2013-09-23
 * @Version 1.0.0
 */
public class BirthDayUtil {

	/**
	 * 判断日期是否合法
	 * 
	 * @param dateStr
	 * @return
	 */
	public static boolean validate(String dateStr) {
		Pattern p = Pattern.compile("\\d{4}+[-]\\d{1,2}+[-]\\d{1,2}+");
		Matcher m = p.matcher(dateStr);
		if (!m.matches()) {
			return false;
		}

		String[] array = dateStr.split("-");
		int year = Integer.valueOf(array[0]);
		int month = Integer.valueOf(array[1]);
		int day = Integer.valueOf(array[2]);

		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		if (year < y - 150 || month > y) {// 年龄不超过150岁
			return false;
		}

		if (month < 1 || month > 12) {
			return false;
		}

		int[] mDays = new int[] { 0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31,
				30, 31 };
		if (isLeapYear(year)) {
			mDays[2] = 29;
		} else {
			mDays[2] = 28;
		}
		if (day < 1 || day > mDays[month]) {
			return false;
		}
		return true;
	}

	/**
	 * 是否是闰年
	 * 
	 * @param year
	 * @return
	 */
	private static boolean isLeapYear(int year) {
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
	}
	
	/***
	 * 将毫秒数转换成日期格式字符串
	 * @param dateFormat
	 * @param millSec
	 * @return
	 */
	public static String transferLongToDate(String dateFormat,Long millSec){
	     SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	     Date date= new Date(millSec);
	     return sdf.format(date);
	 }
}
