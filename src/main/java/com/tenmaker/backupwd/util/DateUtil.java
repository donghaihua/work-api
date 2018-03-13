package com.tenmaker.backupwd.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
	/** A working calendar. */
	// private static final Calendar CALENDAR = Calendar.getInstance();

	/**
	 * 给定日期加减年数
	 */
	public static Date changeYear(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);// 给定日期
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + offset);// 让日期加1
		// calendar.set(Calendar.DAY_OF_YEAR,
		// (calendar.get(Calendar.DAY_OF_YEAR) + offset));
		// System.out.println(calendar.get(Calendar.DATE));//加1之后的日期
		return calendar.getTime();
	}

	/**
	 * 给定日期加减月数
	 */
	public static Date changeMonth(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);// 给定日期
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + offset);// 让日期加1
		// calendar.set(Calendar.DAY_OF_YEAR,
		// (calendar.get(Calendar.DAY_OF_YEAR) + offset));
		// System.out.println(calendar.get(Calendar.DATE));//加1之后的日期
		return calendar.getTime();
	}

	/**
	 * 给定日期加减天数
	 */
	public static Date changeDay(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);// 给定日期
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)
				+ offset);// 让日期加1
		// calendar.set(Calendar.DAY_OF_YEAR,
		// (calendar.get(Calendar.DAY_OF_YEAR) + offset));
		// System.out.println(calendar.get(Calendar.DATE));//加1之后的日期
		return calendar.getTime();
	}

	/**
	 * 给定日期加减小时数
	 */
	public static Date changeHour(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);// 给定日期
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)
				+ offset);
		return calendar.getTime();
	}

	/**
	 * 给定日期加减分钟数
	 */
	public static Date changeMinute(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);// 给定日期
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + offset);
		return calendar.getTime();
	}

	public static Date convertStrToDate(String aStr) {// aStr形如"2010-11-11T00:00:00"
														// 或
														// "2010-11-11 00:00:00"
		if (StringUtil.isEmpty(aStr))
			return null;
		if (aStr.contains("T")) {
			aStr = aStr.substring(0, 10);
		}
		// try {
		// return DateUtils.parseDate(aStr.toString(), new String[]
		// {"yyyy-MM-dd"});//"yyyy-MM-dd HH:mm:ss.SSS",
		// "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm", "yyyy-MM-dd"
		// } catch (ParseException e) {
		// System.out.println("DateUtil.convertStrToDate转换字符串到util.Date型失败，失败后默认返回null");
		// e.printStackTrace();
		// }
		// return null;
		return strToDate(aStr);
	}

	public static Date convertStrToDateTime(String aStr) {// aStr形如"2010-11-11T00:00:00"
															// 或
															// "2010-11-11 00:00:00"
		if (StringUtil.isEmpty(aStr))
			return null;
		if (aStr.contains("T")) {
			aStr = aStr.replaceFirst("T", " ");
		}
		return strToDateTime(aStr);
	}

	/**
	 * 去除日期中的时间，处理后形如"2008-08-08 00:00:00"
	 *
	 * @param aDate
	 * @return
	 */
	public static Date formatDateByRemoveTime(Date aDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(aDate);
		// ParsePosition pos = new ParsePosition(8); //设置时区
		try {
			aDate = formatter.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aDate;
	}

	/**
	 * 根据指定格式对日期进行格式化
	 *
	 * @param aDate
	 * @return
	 */
	public static String formatByAppointString(Date aDate, String appointFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(appointFormat);
		String strDate = formatter.format(aDate);

		return strDate;
	}

	/**
	 * 根据指定格式对日期进行格式化并返回Date
	 *
	 * @param aDate
	 * @return
	 */
	public static Date formatByAppoint(Date aDate, String appointFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(appointFormat);
		String strDate = formatter.format(aDate);
		Date s = null;
		try {
			s = formatter.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;

	}

	/**
	 * 获得给定Date的年份数字
	 *
	 * @param date
	 * @return
	 */
	public static int getYearNum(Date date) {
		Calendar CALENDAR = Calendar.getInstance();
		CALENDAR.setTime(date);
		return CALENDAR.get(Calendar.YEAR);
	}

	/**
	 * 获得给定Date的月份数字
	 *
	 * @param date
	 * @return
	 */
	public static int getMonthNum(Date date) {
		Calendar CALENDAR = Calendar.getInstance();
		CALENDAR.setTime(date);
		return CALENDAR.get(Calendar.MONTH) + 1;
	}

	/**
	 * 判断日期为星期几,星期天是1
	 *
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date) {
		// 首先定义一个calendar，必须使用getInstance()进行实例化

		Calendar aCalendar = Calendar.getInstance();
		// 里面野可以直接插入date类型
		aCalendar.setTime(date);
		// 计算此日期是一周中的哪一天

		int day_of_week = aCalendar.get(Calendar.DAY_OF_WEEK);
		// int x = day_of_week<=1 ? day_of_week-1 : day_of_week+6;//以星期一为1
		// int x = day_of_week>=1 ? day_of_week-1 : day_of_week+6;//以星期天为0
		return day_of_week;
	}

	public static String getChineseWeekDay(int i) {
		switch (i) {
		case 1:
			return "星期日";
		case 2:
			return "星期一";
		case 3:
			return "星期二";
		case 4:
			return "星期三";
		case 5:
			return "星期四";
		case 6:
			return "星期五";
		case 7:
			return "星期六";
		default:
			return "";
		}
	}

	/**
	 * 得到一个给定年份的月份的最大天数
	 *
	 * @param yearNum
	 * @param monthNum
	 * @return
	 */
	public static int getMaxDayNumOfMonth(int yearNum, int monthNum) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, yearNum);
		calendar.set(Calendar.MONTH, monthNum - 1);// 注意:Calendar对象默认一月为0
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);// 本月份的天数
		return maxDay;
	}

	/**
	 * Creates a date.
	 *
	 * @param yyyy
	 *            the year.
	 * @param month
	 *            the month (1 - 12).
	 * @param day
	 *            the day.
	 * @return a date.
	 */
	public static synchronized Date createDate(final int yyyy, final int month,
			final int day) {
		Calendar CALENDAR = Calendar.getInstance();
		CALENDAR.clear();
		CALENDAR.set(yyyy, month - 1, day);
		return CALENDAR.getTime();
	}

	/**
	 * Creates a date.
	 *
	 * @param yyyy
	 *            the year.
	 * @param month
	 *            the month (1 - 12).
	 * @param day
	 *            the day.
	 * @param hour
	 *            the hour.
	 * @param min
	 *            the minute.
	 * @return a date.
	 */
	public static synchronized Date createDate(final int yyyy, final int month,
			final int day, final int hour, final int min) {
		Calendar CALENDAR = Calendar.getInstance();
		CALENDAR.clear();
		CALENDAR.set(yyyy, month - 1, day, hour, min);
		return CALENDAR.getTime();

	}

	public static String formatDateString1() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日   HH时mm分ss秒");
		String strDate = formatter.format(currentTime);
		return strDate;
	}

	public static String formatDateString2() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		String strDate = formatter.format(currentTime);
		return strDate;
	}

	public static String formatDateString3() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd   HH:mm:ss");
		String strDate = formatter.format(currentTime);
		return strDate;
	}

	public static String formatDateString4() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(currentTime);
		return strDate;
	}

	public static String formatDateString5() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = formatter.format(currentTime);
		return strDate;
	}

	public static String formatDateString6() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
		String strDate = formatter.format(currentTime);
		return strDate;
	}

	public static String formatDateString7() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(currentTime);
		return strDate;
	}

	public static String formatDateString8() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String strDate = formatter.format(currentTime);
		return strDate;
	}

	/*------------------------------------------------------------------------

	public static Date formatDate1() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH时mm分ss秒");
		String strDate = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8); //设置时区   
		Date currentTime_2 = formatter.parse(strDate, pos);
		return currentTime_2;
	}

	public static Date formatDate2() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		String strDate = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8); //设置时区   
		Date currentTime_2 = formatter.parse(strDate, pos);
		return currentTime_2;
	}

	public static Date formatDate3() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
		String strDate = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8); //设置时区   
		Date currentTime_2 = formatter.parse(strDate, pos);
		return currentTime_2;
	}

	public static Date formatDate4() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8); //设置时区   
		Date currentTime_2 = formatter.parse(strDate, pos);
		return currentTime_2;
	}

	public static Date formatDate5() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8); //设置时区   
		Date currentTime_2 = formatter.parse(strDate, pos);
		return currentTime_2;
	}

	public static Date formatDate6() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd   HH:mm");
		String strDate = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8); //设置时区   
		Date currentTime_2 = formatter.parse(strDate, pos);
		return currentTime_2;
	}

	public static Date formatDate7() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8); //设置时区   
		Date currentTime_2 = formatter.parse(strDate, pos);
		return currentTime_2;
	}

	public static Date formatDate8() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String strDate = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8); //设置时区   
		Date currentTime_2 = formatter.parse(strDate, pos);
		return currentTime_2;
	}*/

	/**
	 * 把时间字符串转换成Date变量.
	 */
	public static Date strToDate(String strDate) {
		if (StringUtil.isNotEmpty(strDate)) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ParsePosition pos = new ParsePosition(0);
			Date strtodate = formatter.parse(strDate, pos);
			return strtodate;
		} else {
			return null;
		}
	}

	/**
	 * 把时间字符串转换成Date变量.
	 */
	public static Date strToDateTime(String strDate) {

		if (StringUtil.isNotEmpty(strDate)) {
			strDate = strDate.replace('T', ' ');
			if (strDate.length() == 16) {
				strDate = strDate + ":00";
			}
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			Date strtodate = formatter.parse(strDate, pos);
			return strtodate;
		} else {
			return null;
		}
	}

	/**
	 * 把时间变量转换成String.
	 */
	public static String dateTimeToStr(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * @author lzp
	 */
	public static String dateToStr(Date date) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			return sf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 得到当前的时间.
	 */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}

	/**
	 * 得到一个时间变量的秒数.
	 */
	public static long getSecond(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate.getTime();
	}

	/**
	 * 设置一个时间，返回它的秒数.
	 */
	public static long getDateLongTime(int year, int month, int day) {
		Calendar myCalendar = Calendar.getInstance();
		myCalendar.set(year, month - 1, day);
		return myCalendar.getTime().getTime();
	}

	/**
	 * 返回距当天day天的date变量.
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 34 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	// public static void main(String args[]) {
	// System.out.println(formatDateString8());
	// System.out.println(getDateLongTime(2004, 12, 23));
	// System.out.println(getLastDate(2));
	// System.out.println(getNow());
	// }

	/**
	 * 返回本周一至周日的字符串，如今天为2012-07-10, 返回型如：2012-07-09~2012-07-15
	 */
	public static String getThisWeekStr() {
		Calendar CALENDAR = Calendar.getInstance();
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		int dayOfWeek = CALENDAR.get(Calendar.DAY_OF_WEEK);
		// System.out.println(CALENDAR.SUNDAY);//SUNDAY=1
		StringBuffer sb = new StringBuffer("");

		// 得到本周一
		CALENDAR.set(Calendar.DATE, CALENDAR.get(Calendar.DATE) + 2 - dayOfWeek);
		d = CALENDAR.getTime();
		sb.append(sft.format(d));

		sb.append("~");

		// 得到本周日
		CALENDAR.set(Calendar.DATE, CALENDAR.get(Calendar.DATE) + 6);
		d = CALENDAR.getTime();
		sb.append(sft.format(d));

		return sb.toString();
	}

	/**
	 * 返回下周一至下周日的字符串，如今天为2012-07-10, 返回型如：2012-07-16~2012-07-22
	 */
	public static String getNextWeekStr() {
		Calendar CALENDAR = Calendar.getInstance();
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		int dayOfWeek = CALENDAR.get(Calendar.DAY_OF_WEEK);
		StringBuffer sb1 = new StringBuffer("");

		// 得到下周一
		CALENDAR.set(Calendar.DATE, CALENDAR.get(Calendar.DATE) + 9 - dayOfWeek);
		d = CALENDAR.getTime();
		sb1.append(sft.format(d));

		sb1.append("~");

		// 得到下周日
		CALENDAR.set(Calendar.DATE, CALENDAR.get(Calendar.DATE) + 6);
		d = CALENDAR.getTime();
		sb1.append(sft.format(d));

		return sb1.toString();
	}

	/**
	 * 返回下下周一至下周日的字符串，如今天为2012-07-10, 返回型如：2012-07-23~2012-07-29
	 */
	public static String getAfterNextWeekStr() {
		Calendar CALENDAR = Calendar.getInstance();
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		int dayOfWeek = CALENDAR.get(Calendar.DAY_OF_WEEK);

		StringBuffer sb1 = new StringBuffer("");

		// 得到下下周一
		CALENDAR.set(Calendar.DATE, CALENDAR.get(Calendar.DATE) + 16
				- dayOfWeek);
		d = CALENDAR.getTime();
		sb1.append(sft.format(d));

		sb1.append("~");

		// 得到下下周日
		CALENDAR.set(Calendar.DATE, CALENDAR.get(Calendar.DATE) + 6);
		d = CALENDAR.getTime();
		sb1.append(sft.format(d));

		return sb1.toString();
	}

	/**
	 * 获取相对本月开始的半年时间，比如上个月开始，下个月开始 -1代表上个月，1代表下个月
	 *
	 * @return
	 */
	public static Map getHalfYearFromGivenMonth(int param) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, param);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		Date beginMonth = calendar.getTime();

		// 本来应该+5，现在因为要求最后一个月的最后一天，所以加一个月，然后时间设置为第一天，再减去一天，就是最后一个月的最后一天了
		int temp = calendar.get(Calendar.MONTH) + 6;
		if (temp > 12) {
			// 加以年，并且设置月份为1月
			calendar.add(Calendar.YEAR, 1);
			calendar.set(Calendar.MONTH, 0);
			//
			calendar.set(Calendar.MONTH, temp - 12);// 月份从0开始
		} else {
			calendar.set(Calendar.MONTH, temp);// 月份从0开始
		}

		calendar.set(Calendar.DATE, 1); // 设置为该月第一天
		calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
		Date endMonth = calendar.getTime();
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(0, df.format(beginMonth));
		map.put(1, df.format(endMonth));
		// System.out.println(df.format(beginMonth));
		// System.out.println(df.format(endMonth));
		return map;
	}

}
