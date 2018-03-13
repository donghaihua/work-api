package com.tenmaker.backupwd.util;

public class NumberUtil {

	/**
	 * 转潜在对象为Long型
	 */
	public static Long parseLong(Object obj) {
		if (obj != null) {
			if (obj instanceof Long)
				return (Long) obj;
			if (StringUtil.isNotEmpty(obj.toString()))
				return Long.parseLong(obj.toString().trim());
			else
				return null;
		} else {
			return null;
		}
	}

	/**
	 * 转字符串为Long型
	 */
	public static Long parseLongFromStr(String str) {
		if (StringUtil.isNotEmpty(str)) {
			return Long.parseLong(str);
		} else {
			return null;
		}
	}

	/**
	 * 转潜在对象为Integer型
	 */
	public static Integer parseInteger(Object obj) {
		if (obj != null) {
			if (obj instanceof Integer)
				return (Integer) obj;
			if (StringUtil.isNotEmpty(obj.toString()))
				return Integer.parseInt(obj.toString().trim());
			else
				return null;
		} else {
			return null;
		}
	}

	/**
	 * 转字符串为Integer型
	 */
	public static Integer parseIntegerFromStr(String str) {
		try {
			if (StringUtil.isNotEmpty(str)) {
				if (str.contains(".")) {
					str = str.substring(0, str.indexOf("."));
				}
				return Integer.parseInt(str);
			} else {
				return null;
			}
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * 转字符串为Double型
	 */
	public static Double parseDoubleFromStr(String str) {
		if (StringUtil.isNotEmpty(str)) {
			return Double.parseDouble(str.trim());
		} else {
			return null;
		}
	}

	public static String starTelephone(String telephone) {
		if (StringUtil.isEmpty(telephone)) {
			return null;
		}
		return telephone.replaceAll("(?<=\\d{3})\\d(?=\\d{3})", "*");
	}

	public static String starMail(String mail) {
		if (StringUtil.isEmpty(mail)) {
			return null;
		}
		String mail_first = mail.substring(0, mail.lastIndexOf("@"));
		String mail_last = mail.substring(mail_first.length());
		// String[] array_temp =
		// mail_first.split("");//假如mail_first="taobao",那么数组为[,t,a,o,b,a,o],会多出一个字符，所以索引从1开始
		if (mail_first.length() <= 2) {
			return mail;
		}
		if (mail_first.length() == 3) {
			String first = mail_first.substring(0, 2);
			String end = mail_first.substring(2);
			mail_first = first + "***" + end;
		} else if (mail_first.length() > 3) {
			String first = mail_first.substring(0, 2);
			String end = mail_first.substring(mail_first.length() - 2,
					mail_first.length());
			mail_first = first + "***" + end;
		}
		return mail_first + mail_last;
	}

}