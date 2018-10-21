package com.merit.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
	public final static String DEF_DATE_FORMAT = "yyyy/MM/dd";

	public final static String DEF_TIME_FORMAT = "kk:mm";

	public final static String DEF_DATE_TIME = "yyyy/MM/dd HH:mm";

	public final static String ORA_DATE_FORMAT = "yyyy-MM-dd";

	public final static String ORA_TIME_FORMAT = "HH24:MI";

	public final static String ORA_DATE_TIME = "YYYY-MM-DD HH24:MI";

	public final static String ORA_DATE_TIME_FORMAT = "yyyyMMddHHMM";

	public final static String SQL_DATE_TIME_FORMAT_LIMIT = "yyyy-MM-dd HH:mm:ss";

	public final static String FILE_INDENTIFY_FORMAT = "yyyyMMddHHmmss";

	public final static String SQL_TIME_FORMAT = "HH:mm:ss";

	/**
	 * Get Long object from default format string
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Long strToLong(String dateStr) {
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat(DEF_DATE_FORMAT);
			ParsePosition pos = new ParsePosition(0);
			Date tempDate = formatDate.parse(dateStr, pos);
			return new Long(tempDate.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 方法 getBetweenDays 得到两个日期的间隔天�?
	 * 
	 * @param endDate
	 *            时间字符�?1，如�?2001-07-17 11:00:00
	 * @param startDate
	 *            时间字符�?2，如�?2001-12-31 11:00:00
	 * @return long 间隔天数(long)
	 */
	public static long getBetweenDays(String endDate, String startDate) {
		if (endDate == null || startDate == null)
			return -1;
		if (endDate.length() < 10 || startDate.length() < 10)
			return -1;
		int startYear = 2001, startMonth = 12, startDay = 31;
		int endYear = 2001, endMonth = 12, endDay = 31;

		try {
			startYear = Integer.parseInt(endDate.substring(0, 4));
			startMonth = Integer.parseInt(endDate.substring(5, 7));
			startDay = Integer.parseInt(endDate.substring(8, 10));
			endYear = Integer.parseInt(startDate.substring(0, 4));
			endMonth = Integer.parseInt(startDate.substring(5, 7));
			endDay = Integer.parseInt(startDate.substring(8, 10));
		} catch (NumberFormatException e) {
		} catch (Exception e) {
		}

		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		startCalendar.set(startYear, startMonth - 1, startDay, 1, 0, 0);
		endCalendar.set(endYear, endMonth - 1, endDay, 0, 0, 0);
		Date endDateTemp = startCalendar.getTime();
		Date startDateTemp = endCalendar.getTime();
		long startTime = endDateTemp.getTime();
		long endTime = startDateTemp.getTime();
		long times = startTime - endTime;
		// long days = Math.abs(times / ( 1000 *
		// 3600 * 24
		// ));
		long days = times / (1000 * 3600 * 24);
		return days;
	}

	/**
	 * 方法 getAfterDate 得到与指定日期相差指定天数的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2001-07-17 11:00:00
	 * @param dayCount
	 *            向前或向后的天数，向后为正数，向前为负�??
	 * @return 处理后的日期字符�?
	 */
	public static String getAfterDate(String baseDate, int dayCount) {
		int year = Integer.parseInt(baseDate.substring(0, 4));
		int month = Integer.parseInt(baseDate.substring(5, 7));
		int date = Integer.parseInt(baseDate.substring(8, 10));

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date);
		calendar.add(Calendar.DATE, dayCount);
		Date _date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(_date);

		return dateString;
	}

	/**
	 * <p>
	 * 过去
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: DateUtil.java,v 0.1 Oct 16, 2010 1:16:23 PM Jack Exp $
	 */
	public static Date getAfterHours(Date _date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		calendar.add(Calendar.HOUR, hours);
		Date _date1 = calendar.getTime();
		return _date1;
	}

	public static Date getAfterMinuts(String baseDate, int minus) {
		int year = Integer.parseInt(baseDate.substring(0, 4));
		int month = Integer.parseInt(baseDate.substring(5, 7));
		int date = Integer.parseInt(baseDate.substring(8, 10));
		int hour = Integer.parseInt(baseDate.substring(11, 13));
		int minuts = Integer.parseInt(baseDate.substring(14, 16));
		int second = Integer.parseInt(baseDate.substring(17, 19));

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date, hour, minuts, second);
		calendar.add(Calendar.MINUTE, minus);
		Date _date = calendar.getTime();
		return _date;
	}

	public static String parse(String baseDate) {

		String dateString = baseDate;
		baseDate = baseDate.trim().toLowerCase();
		try {
			if (baseDate.length() == 11) {
				dateString = baseDate.substring(7);
				if (baseDate.substring(3, 6).equals("jan"))
					dateString += "-01-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("feb"))
					dateString += "-02-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("mar"))
					dateString += "-03-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("apr"))
					dateString += "-04-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("may"))
					dateString += "-05-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("jun"))
					dateString += "-06-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("jul"))
					dateString += "-07-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("aug"))
					dateString += "-08-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("sep"))
					dateString += "-09-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("oct"))
					dateString += "-10-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("nov"))
					dateString += "-11-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("dec"))
					dateString += "-12-" + baseDate.substring(0, 2);
				else
					dateString = baseDate;
			} else if (baseDate.length() > 11) {
				dateString = baseDate.substring(7, 11);
				if (baseDate.substring(3, 6).equals("jan"))
					dateString += "-01-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("feb"))
					dateString += "-02-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("mar"))
					dateString += "-03-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("apr"))
					dateString += "-04-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("may"))
					dateString += "-05-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("jun"))
					dateString += "-06-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("jul"))
					dateString += "-07-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("aug"))
					dateString += "-08-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("sep"))
					dateString += "-09-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("oct"))
					dateString += "-10-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("nov"))
					dateString += "-11-" + baseDate.substring(0, 2);
				else if (baseDate.substring(3, 6).equals("dec"))
					dateString += "-12-" + baseDate.substring(0, 2);
				else
					dateString = baseDate;
				if (!dateString.equals(baseDate)) {
					dateString += baseDate.substring(11);
				}
			}
		} catch (Exception ex) {
			dateString = baseDate;
		}

		return dateString;
	}

	/**
	 * 方法 getBeforeDate 得到与指定日期之前相差指定天数的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2001-07-17 11:00:00
	 * @param dayCount
	 *            向前的天�?
	 * @return 相减后的日期字符�?
	 */
	public static String getBeforeDate(String baseDate, int dayCount) {
		return getAfterDate(baseDate, -dayCount);
	}

	/**
	 * 方法 getAfterMonth 得到与指定日期相差指定月数的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2001-07-17 11:00:00
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负�??
	 * @return 相加后的日期字符�?
	 */
	public static String getAfterMonth(String baseDate, int monthCount) {
		int year = Integer.parseInt(baseDate.substring(0, 4));
		int month = Integer.parseInt(baseDate.substring(5, 7));
		int date = Integer.parseInt(baseDate.substring(8, 10));

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date);
		calendar.add(Calendar.MONTH, monthCount);
		Date _date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(_date);

		return dateString;
	}

	/**
	 * 方法 getBeforeMonth 得到与指定日期之前相差指定月数的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2001-07-17 11:00:00
	 * @param monthCount
	 *            向前的月�?
	 * @return 相减后的日期字符�?
	 */
	public static String getBeforeMonth(String baseDate, int monthCount) {
		return getAfterMonth(baseDate, -monthCount);
	}

	/**
	 * 方法 getEndDate 得到与指定日期相差指定月数并减去�?天的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2001-07-17 11:00:00
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负�??
	 * @return 相加后的日期字符�?
	 */
	public static String getEndDate(String baseDate, int monthCount) {
		// int year =
		// Integer.parseInt(baseDate.substring(0,4));
		// int month =
		// Integer.parseInt(baseDate.substring(5,7));
		int day = Integer.parseInt(baseDate.substring(8, 10));

		String endDate = getAfterMonth(baseDate, monthCount);

		// int endYear =
		// Integer.parseInt(endDate.substring(0,4));
		// int endMonth =
		// Integer.parseInt(endDate.substring(5,7));
		int endDay = Integer.parseInt(endDate.substring(8, 10));

		// 说明日期没变
		if (endDay == day) {
			// 月数为正则为减一�?
			if (monthCount > 0) {
				endDate = getAfterDate(endDate, -1);
			} else {
				endDate = getAfterDate(endDate, 1);
			}
		} else { // 日期已变
			if (monthCount < 0) {
				endDate = getAfterDate(endDate, 1);
			}
		}

		return endDate;
	}

	/**
	 * 方法 getDate 得到Date型指定日�?
	 * 
	 * @param baseDate
	 *            时间字符串，如：2001-07-17 11:00:00
	 * @return Date 得到Date型指定日�?
	 */
	public static Date getDate(String baseDate) {
		if (baseDate == null || baseDate.length() == 0)
			return null;
		int year = Integer.parseInt(baseDate.substring(0, 4));
		int month = Integer.parseInt(baseDate.substring(5, 7));
		int date = Integer.parseInt(baseDate.substring(8, 10));
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (baseDate.length() >= 13)
			hour = Integer.parseInt(baseDate.substring(11, 13));
		if (baseDate.length() >= 16)
			minute = Integer.parseInt(baseDate.substring(14, 16));
		if (baseDate.length() >= 19)
			second = Integer.parseInt(baseDate.substring(17, 19));

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date, hour, minute, second);
		Date _date = calendar.getTime();

		return _date;
	}

	/**
	 * 方法 getDate 得到Date型指定日�?
	 * 
	 * @param baseDate
	 *            时间字符串，如：20010717
	 * @return Date 得到Date型指定日�?
	 */
	public static Date getDateSmall(String baseDate) {
		if (baseDate == null || baseDate.length() == 0)
			return null;
		int year = Integer.parseInt(baseDate.substring(0, 4));
		int month = Integer.parseInt(baseDate.substring(4, 6));
		int date = Integer.parseInt(baseDate.substring(6));
		int hour = 0;
		int minute = 0;
		int second = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date, hour, minute, second);
		Date _date = calendar.getTime();

		return _date;
	}

	public static java.sql.Date getSqlDate(String baseDate) {
		if (baseDate == null || baseDate.length() == 0)
			return null;
		Date date = getDate(baseDate);
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 方法 getDateString 得到指定样式的年月日
	 * 
	 * @param year
	 *            �?
	 * @param month
	 *            �?
	 * @param date
	 *            �?
	 * @param patternString
	 *            格式。如：yyyy-MM-dd HH:mm:ss EEE
	 * @return 格式化后的字符串
	 */
	public static String getDateString(int year, int month, int date, String patternString) {
		String dateString = "";
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date);
		Date showDate = calendar.getTime();
		dateString = formatter.format(showDate);
		return dateString;
	}

	/**
	 * 方法 getDateString 得到指定样式的年月日, 对于非法数据返回当前日期样式
	 * 
	 * @param year
	 *            �?
	 * @param month
	 *            �?
	 * @param date
	 *            �?
	 * @param patternString
	 *            格式。如：yyyy-MM-dd HH:mm:ss EEE
	 * @return 格式化后的字符串
	 */
	public static String getDateString(String year, String month, String date, String patternString) {
		return getDateString(year, month, date, patternString, true);
	}

	public static String getDateString(String year, String month, String date, String patternString, boolean hasDefault) {
		String dateString = "";
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		if (hasDefault)
			dateString = formatter.format(new Date());
		try {
			int y = Integer.parseInt(year);
			int m = Integer.parseInt(month);
			int d = Integer.parseInt(date);
			dateString = getDateString(y, m, d, patternString);
		} catch (Exception e) {
		}

		return dateString;
	}

	public static String getDateString(String date, String patternString) {
		if (date == null)
			return "";
		if (date.length() < 10)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(patternString, Locale.ENGLISH);
		return formatter.format(getDate(date));
	}

	/**
	 * Convert a date object to String with specified format. See API document
	 * of java.text.SimpleDateFormat for detail description of format pattern.
	 * Return empty string if parameter date is null.
	 * 
	 * @param _date
	 * @param patternString
	 * @return String
	 */
	public static String getDateString(Date _date, String patternString) {
		String dateString = "";
		if (_date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(patternString);
			dateString = formatter.format(_date);
		}
		return dateString;
	}

	public static String getBeforeMonth(int monthCount) {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, monthCount);
		Date _date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		return formatter.format(_date);
	}

	public static String getHoursOfDay(String day) {
		String st = "";
		try {
			st = Float.parseFloat(day) * 24 + "";
		} catch (NumberFormatException ex) {
		}
		return st;
	}

	public static String getDayOfHours(String hours) {
		String st = "";
		try {
			st = (Float.parseFloat(hours) / 24.0) + "";
		} catch (NumberFormatException ex) {
		}
		return st;

	}

	/**
	 * 方法 getAfterDate 得到与指定日期相差指定天数的日期
	 * 
	 * @param patternString
	 *            时间字符串，如：2001-07-17 11:00:00
	 * @param dayCount
	 *            向前或向后的天数，向后为正数，向前为负�??
	 * @return String 处理后的日期字符�?
	 */
	public static String getAfterToday(int dayCount, String patternString) {

		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.DATE, dayCount);
		Date _date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		String dateString = formatter.format(_date);

		return dateString;
	}

	/**
	 * 将string类型转换为date类型
	 * 
	 * @param dateStr
	 *            2007-10-10
	 * @return
	 * @throws Exception
	 */
	public static Date getDateByString(String dateStr) {
		try {
			return getDate(dateStr, "yyyy-MM-dd");
		} catch (Exception e) {
			return null;
		}
	}

	public static String getCurrentDate(String patten) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(patten);
		String date = formatter.format(new Date(System.currentTimeMillis()));
		return date;
	}

	public static String getDateStr(String patten) {
		SimpleDateFormat formatter = new SimpleDateFormat(patten);
		String date = "";
		date = formatter.format(new Date(System.currentTimeMillis()));
		return date;
	}

	/**
	 * 将字符日期型数据按照指定的格式解析成日期对象
	 * 
	 * @param dateStr
	 *            2007-10-10 / 2007/10/10
	 * @param patten
	 *            yyyy-MM-dd / yyyy/MM/dd
	 * @return
	 */
	public static Date getDate(String dateStr, String patten) {
		SimpleDateFormat formatter = new SimpleDateFormat(patten);
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	public static boolean isDateStr(String dateStr, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			formatter.parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 获得格式化日期之后的 long数据�?
	 * 
	 * @param dateStr
	 * @param patten
	 * @return
	 * @throws Exception
	 */
	public static long getDateOfLong(String dateStr, String patten) throws Exception {
		return getDate(dateStr, patten).getTime();
	}

	/**
	 * 获得格式化日期之后的 String数据�?
	 * 
	 * @param dateLong
	 * @param patten
	 * @return
	 * @throws Exception
	 */
	public static String getDateOfString(Long dateLong, String patten) throws Exception {
		if (dateLong != null) {
			return (new SimpleDateFormat(patten).format(new Date(dateLong.longValue()))).toString();
		}
		return "";
	}

	/**
	 * 将string的日期类型转换为long类型
	 * 
	 * @param dateStr
	 *            2007-10-10
	 * @return
	 * @throws Exception
	 */
	public static long getDateOfLong(String dateStr) throws Exception {
		Date dt = getDateByString(dateStr);
		return dt.getTime();
	}

	/**
	 * 获得long型当前日�?
	 * 
	 * @return
	 * @throws Exception
	 */
	public static long getCurrenTimeOfLong() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime().getTime();
	}

	/**
	 * Convert java.util.Date object to java.sql.Date object.
	 * 
	 * @param date
	 * @return Date
	 */
	public static java.sql.Date UtilDateToSQLDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 验证输入的文本信息日期是否合�?
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date isDate(String dateStr) {
		String date_format_1 = "yyyy/MM/dd";
		String date_format_2 = "yyyy-MM-dd";
		String date_format_3 = "yyyyMMdd";
		String date_format_4 = "yyyy.MM.dd";
		String[] date_format = { date_format_1, date_format_2, date_format_3, date_format_4 };
		for (int i = 0; i < date_format.length; i++) {
			try {
				SimpleDateFormat formatDate = new SimpleDateFormat(date_format[i]);
				formatDate.setLenient(false);
				ParsePosition pos = new ParsePosition(0);
				Date tempDate = formatDate.parse(dateStr, pos);
				tempDate.getTime();
				return tempDate;
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 返回calendar ，传入date
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: DateUtil.java,v 0.1 Jul 2, 2008 12:36:59 PM Jack Exp $
	 */
	public static Calendar getCalendar(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal;
	}

	/**
	 * <p>
	 * 返回时分秒的long类型
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: DateUtil.java,v 0.1 Jul 2, 2008 12:36:39 PM Jack Exp $
	 */
	public static Long getHMSLong(Calendar c) {
		int ap = c.get(Calendar.AM_PM);
		int h = c.get(Calendar.HOUR);
		if (ap == 1)
			h = h + 12;
		int m = c.get(Calendar.MINUTE);
		int s = c.get(Calendar.SECOND);
		return (long) (s + m * 60 + h * 60 * 60);
	}

	public static boolean compare(String hm) throws Exception {
		Date dat = getDate(getDateString(new Date(), "yyyyMMdd") + " " + hm, "yyyyMMdd HH:mm");
		if (new Date().getTime() > dat.getTime()) {
			return true;
		} else {
			return false;
		}
	}

	public static Date getDateFromStr(String baseDate) {
		if (baseDate == null || baseDate.length() == 0)
			return null;
		int year = Integer.parseInt(baseDate.substring(0, 4));
		int month = Integer.parseInt(baseDate.substring(4, 6));
		int date = Integer.parseInt(baseDate.substring(6, 8));
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (baseDate.length() >= 10)
			hour = Integer.parseInt(baseDate.substring(8, 10));
		if (baseDate.length() >= 12)
			minute = Integer.parseInt(baseDate.substring(10, 12));
		if (baseDate.length() >= 14)
			second = Integer.parseInt(baseDate.substring(12, 14));

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date, hour, minute, second);
		Date _date = calendar.getTime();

		return _date;
	}

	/**
	 * <p>
	 * 返回 d1-d2
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: DateUtil.java,v 0.1 Feb 11, 2011 12:42:44 PM Jack Exp $
	 */
	@SuppressWarnings("deprecation")
	public static int getBetweenMinutes(Date d1, Date d2) {
		long bw = 0;
		int cs = 1;
		long minus = 0;
		if (d1.getTime() >= d2.getTime())
			bw = d1.getTime() - d2.getTime();
		else {
			bw = d2.getTime() - d1.getTime();
			cs = -1;
		}
		minus = bw / (60 * 1000);
		return new Long(minus * cs).intValue();

	}

	/**
	 * <p>
	 * 相隔多少分钟，年月日时分秒
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: DateUtil.java,v 0.1 2015-10-15 下午10:08:09 Jack Exp $
	 */
	public static int getBetweenMinutesYMDHMS(String ymdhms1, String ymdhms2) {
		long bw = 0;
		int cs = 1;
		long minus = 0;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse(ymdhms1);
			Date d2 = df.parse(ymdhms2);
			bw = d1.getTime() - d2.getTime();
			minus = bw / (60 * 1000);
		} catch (Exception e) {
			minus = 0;
		}
		return new Long(minus * cs).intValue();
	}

	/**
	 * <p>
	 * 返回当前时间是本周的第几天，星期日为1，星期六为7，依次类推
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: DateUtil.java,v 0.1 May 28, 2011 4:46:15 PM Jack Exp $
	 */
	public static int getDayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * <p>
	 * 传入时间，返回星期几
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: DateUtil.java,v 0.1 May 28, 2011 4:47:46 PM Jack Exp $
	 */
	public static int getDayOfWeek(Date _date) {
		Calendar calendar = getCalendar(_date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * <p>
	 * 传入时间，获得当月的天数
	 * </p>
	 * 
	 * @author Jack Zhou
	 * @version $Id: DateUtil.java,v 0.1 2011-12-28 下午02:03:25 Jack Exp $
	 */
	public static int getDayOfMonth(Date _date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(_date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static void main(String[] args) throws Exception {
		Date date = new Date();
		 String s1 = "2015-10-10 11:11:11";
	       String s2 = "2015-10-16 23:23:23";
	       System.out.println(DateUtil.getBetweenMinutesYMDHMS(s2, s1));
//		Format formatter = new SimpleDateFormat("MM/dd/yy hh:mm");
//		String s = formatter.format(date);
//		System.out.println(s);
//
//		System.out.println("------" + DateUtil.getDayOfWeek());
//		String curr = DateUtil.getCurrentDate("yyyy-MM-dd");
//		System.out.println(curr);
//		String bf = DateUtil.getBeforeDate(curr, 4);
//		System.out.println(bf);
//		String ed = DateUtil.getAfterDate(curr, 2);
//		System.out.println(ed);

	}

}
