package com.sitexa.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class DateUtils {
	final static Logger			logger							= LoggerFactory.getLogger(DateUtils.class);

	public static final String	TIME_FORMAT_SHORT				= "yyyyMMddHHmmss";
	public static final String	TIME_FORMAT_YMD					= "yyyy/MM/dd HH:mm:ss";
	public static final String	TIME_FORMAT_NORMAL				= "yyyy-MM-dd HH:mm:ss";
	public static final String	TIME_FORMAT_ENGLISH				= "MM/dd/yyyy HH:mm:ss";
	public static final String	TIME_FORMAT_CHINA				= "yyyy年MM月dd日 HH时mm分ss秒";
	public static final String	TIME_FORMAT_CHINA_S				= "yyyy年M月d日 H时m分s秒";
	public static final String	TIME_FORMAT_SHORT_S				= "HH:mm:ss";

	public static final String	DATE_FORMAT_SHORT				= "yyyyMMdd";
	public static final String	DATE_FORMAT_NORMAL				= "yyyy-MM-dd";
	public static final String	DATE_FORMAT_ENGLISH				= "MM/dd/yyyy";
	public static final String	DATE_FORMAT_CHINA				= "yyyy年MM月dd日";
	public static final String	DATE_FORMAT_CHINA_YEAR_MONTH	= "yyyy年MM月";
	public static final String	MONTH_FORMAT					= "yyyyMM";
	public static final String	YEAR_MONTH_FORMAT				= "yyyy-MM";
	public static final String	DATE_FORMAT_MINUTE				= "yyyyMMddHHmm";
	public static final String	MONTH_DAY_FORMAT				= "MM-dd";
	
	/**
	 * yyyy-MM-dd HH:mm:ss:SSS
	 */
	public final static String FORMAT_1 = "yyyy-MM-dd HH:mm:ss:SSS";

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public final static String FORMAT_2 = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 将yyyy-MM-dd HH:mm:ss 转换为距离 格林威治时间1970-01-01 00:00:00 毫秒 转换失败则返回-1
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static long string2Time(String strDate, String format) {
		Date date = string2Date(strDate, format);
		if (date == null) {
			return -1;
		} else {
			return date.getTime();
		}
	}

	public static long string2Time(String strDate) {
		return string2Time(strDate, FORMAT_2);
	}

	public static Date string2Date(String strDate) {
		return string2Date(strDate, FORMAT_2);
	}

	public static Date string2Date(String strDate, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String date2String(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static String date2String(Date date) {
		return date2String(date, FORMAT_2);
	}

	public static String time2String(long lTime, String format) {
		Date date = new Date(lTime);
		return date2String(date, format);
	}

	public static String time2String(long lTime) {
		return time2String(lTime, FORMAT_2);
	}

	public static String getNow(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	public static String getNow() {
		return getNow(FORMAT_2);
	}

	/**
	 * 得到时间字符串,格式为 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            待格式化日期
	 * @return 返回格式化后的时间字符串
	 * @since 0.1
	 */
	public static String getTimeNormalString(Date date) {
		DateFormat fmt = new SimpleDateFormat(TIME_FORMAT_NORMAL);
		return fmt.format(date);
	}

	/**
	 * 得到时间字符串,根据使用的格式模板
	 * 
	 * @param date
	 *            待格式化日期
	 * @param pattern
	 *            格式化模板
	 * @return 返回格式化后的时间字符串
	 */
	public static String getTimeString(Date date, String pattern) {
		DateFormat fmt = new SimpleDateFormat(pattern);
		return fmt.format(date);
	}

	/**
	 * Description:TODO 获取当前时间字符串,格式为 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String 返回格式化后的时间字符串 添加人: huwenhu
	 */
	public static String getNowTimeNormalString() {
		Date date = new Date();
		return getTimeNormalString(date);
	}

	/**
	 * 获取本月的年月日
	 * 
	 * @param toFormat
	 *            日期格式
	 * @return
	 */
	public static String getDate(String toFormat) {
		SimpleDateFormat format = new SimpleDateFormat(toFormat);
		String time = format.format(new Date());
		return time;
	}

	public static Date parseDate(String strDate, String pattern) {
		DateFormat fmt = new SimpleDateFormat(pattern);
		try {
			return fmt.parse(strDate);
		} catch (ParseException e) {
			logger.warn(e.getMessage(), e);
			throw new IllegalArgumentException("Date or Time String is invalid.");
		}
	}

	/**
	 * 把日期字符串转换为日期类型
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return 日期
	 * @since 0.1
	 */
	public static Date convertAsDate(String dateStr) {
		if (dateStr == null || "".equals(dateStr)) {
			return null;
		}
		DateFormat fmt = null;
		if (dateStr.matches("\\d{14}")) {
			fmt = new SimpleDateFormat(TIME_FORMAT_SHORT);
		} else if (dateStr.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
			fmt = new SimpleDateFormat(TIME_FORMAT_NORMAL);
		} else if (dateStr.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
			fmt = new SimpleDateFormat(TIME_FORMAT_ENGLISH);
		} else if (dateStr.matches("\\d{4}年\\d{1,2}月\\d{1,2}日 \\d{1,2}时\\d{1,2}分\\d{1,2}秒")) {
			fmt = new SimpleDateFormat(TIME_FORMAT_CHINA);
		} else if (dateStr.matches("\\d{8}")) {
			fmt = new SimpleDateFormat(DATE_FORMAT_SHORT);
		} else if (dateStr.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
			fmt = new SimpleDateFormat(DATE_FORMAT_NORMAL);
		} else if (dateStr.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
			fmt = new SimpleDateFormat(DATE_FORMAT_ENGLISH);
		} else if (dateStr.matches("\\d{4}年\\d{1,2}月\\d{1,2}日")) {
			fmt = new SimpleDateFormat(DATE_FORMAT_CHINA);
		} else if (dateStr.matches("\\d{4}\\d{1,2}\\d{1,2}\\d{1,2}\\d{1,2}")) {
			fmt = new SimpleDateFormat(DATE_FORMAT_MINUTE);
		} else if (dateStr.matches("\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
			fmt = new SimpleDateFormat(TIME_FORMAT_SHORT_S);
		}
		try {
			return fmt.parse(dateStr);
		} catch (ParseException e) {
			logger.warn(e.getMessage(), e);
			throw new IllegalArgumentException("Date or Time String is invalid.");
		}
	}

	public static Date addDay(Date date, int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	public static Date addMinute(Date date, int minute) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	public static Date addSecond(Date date, int second) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}

	public static Date addHour(Date date, int hour) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hour);
		return calendar.getTime();
	}

	public static Date add(Date date,int day,  int hour,int minute,int second) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		calendar.add(Calendar.MINUTE, minute);
		calendar.add(Calendar.HOUR, hour);
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}
	
	public static Date add(Date date,int day,  int hour) {
		return add( date , day ,   hour , 0 , 0);
	}
	
	public static Date add(Date date,int day,  int hour , int minute) {
		return add( date , day ,   hour , minute , 0);
	}
	
	
	public static String delayScheduleDateBySecond(Date dtNow, int second) {
		return getTimeNormalString(addSecond(dtNow, second));
	}

	public static String delayScheduleDateByMinute(Date dtNow, int minute) {
		return getTimeNormalString(addMinute(dtNow, minute));
	}

	public static String delayScheduleDateByHour(Date dtNow, int hour) {
		return getTimeNormalString(addHour(dtNow, hour));
	}
	
	
	

	
	public static final DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Pattern pattern = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})$");
	public static Date parseDate(String strDate) {
		try {
			return fmt.parse(strDate);
		} catch (ParseException e) {
			logger.warn(e.getMessage(), e);
			throw new IllegalArgumentException("Date or Time String is invalid.");
		}
	}
	
	//是否为可运行时间
	public static boolean isRunTime(String dtDisableStart,String dtDisableEnd){
		//日期类型匹配
		if(pattern.matcher(dtDisableStart).matches() && pattern.matcher(dtDisableEnd).matches()){
			long currentTimes=System.currentTimeMillis();
			long startTims=parseDate(dtDisableStart).getTime();
			long endTims=parseDate(dtDisableEnd).getTime();
			if(currentTimes>startTims && endTims>currentTimes){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}	
	
	public static long[] getDatePoor(Date endDate, Date nowDate) {
		long type=1; 
		
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    if(diff<0){
	    	type=-1;
	    }
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    // long sec = diff % nd % nh % nm / ns;
	    long[] tims={type,Math.abs(day),Math.abs(hour),Math.abs(min)};
	    logger.info("(type:"+type+")  --->   "+Math.abs(day) + "天" + Math.abs(hour) + "小时" + Math.abs(min) + "分钟");
	    return tims;
	}	
}
