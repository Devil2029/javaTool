package com.tool.as.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
/**
 * @description:日期工具类
 * @author lp
 */
public class DateUtil {
	
	private static final Logger log = Logger.getLogger(DateUtil.class);
	
	

	public final static SimpleDateFormat sdFormat1 = new SimpleDateFormat(
			"yyyy-MM-dd");
	public final static SimpleDateFormat sdFormat2 = new SimpleDateFormat(
			"yyyyMMdd");
	public final static SimpleDateFormat sdFormat3 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public final static SimpleDateFormat sdFormat4 = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	
	/**
	 * 把时间戳 转为 Date日期
	 * @param timestamp  时间戳
	 * @return date 日期
	 */
	public static Date timestamp2DateTime(Long timestamp) {
		Date date =null;
		if (timestamp!=null) {
			date =new Date();
			date.setTime(timestamp);
			return date;
		}
	    return date;
	}

	/**
	 * 把 Date日期 转为 时间戳
	 * @param dateTime   日期
	 * @return long型的 时间戳
	 */
	public static Long dateTime2timestamp(Date dateTime) {
		Long longDate = null;
		if (dateTime != null) {
			return dateTime.getTime();
		}
		return longDate;
	}
		
	/** 把  date日期  转为     字符串日期
	 *  传入 date类型日期，返回 字符串 日期格式
	 * 
	 * @param 日期格式   type; 
	 *             1:yyyy-MM-dd HH:mm:ss ,
	 *             2:yyyy-MM-dd
	 * @param date
	 *            date类型日期
	 * @return 字符串 日期格式
	 */
	public static String date2String(int type, Date date) {
		String format = "";
		if (type == 1) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else if (type == 2) {
			format = "yyyy-MM-dd";
		}else if (type == 3) {
			format = "yyyyMMddHHmmss";
		}else if (type == 4) {
			format = "yyyyMMdd";
		}else if (type == 5) {
			format = "yyyy-MM-dd HH";
		}else if (type == 6) {
			format = "yyyy-MM";
		}else if (type==7) {
			format = "yyyyMMddHHmmssSSS";
		}else if (type==8) {
			format = "yyMMddHHmmss";
		}else if (type==9) {
			format = "yyyy/MM/dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	
	/** 把 字符串格式 日期   转为    date 日期
	 * @param str 字符串日期
	 * @param type 为:
	 *  1:yyyy-MM-dd HH:mm:ss 
	 *  2:yyyy-MM-dd
	 *  3:yyyy-MM-dd HH:mm
	 *  
	 * @return date 日期
	 */
	public static Date string2date(String dateStr,int type){
		String dateFormat="";  
		if (type==1) {
			dateFormat="yyyy-MM-dd HH:mm:ss";
		}else if(type==2){
			dateFormat="yyyy-MM-dd";
		}else if (type==3) {
			dateFormat="yyyy-MM-dd HH:mm";
		}else if (type==4) {
			dateFormat="yyyyMMddHHmmss";
		}else if (type==5) {
			dateFormat="yyyy-MM";
		}
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		Date date=null;
			try {
				date=format.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
	}
	
	
	/**   
	 * 判断 在 一个时间范围内 是否有效，如：在24小时内有效，或者 1分钟内有效
	 * @param timestamp 时间戳
	 * @param type 1：小时 ，2：分钟
	 * @param expireTime   过期时间（数字），如：1、2、3、4……
	 * @return  true:时间在这个范围内（没过期），false:超过了这个范围（已过期）
	 */
	public static Boolean isInvalidScope(Long timestamp,int type,String expireTime){
		Boolean bool=true;
		if (timestamp==null) {
			return bool;
		}
		Long expireDate =null;
		//如果是 小时，如 24小时
		if (type==1) { 
			//得到过期 日期
			expireDate =Long.valueOf(expireTime)*60*60*1000+timestamp;
		}
		//如果是分钟，如 10分钟
		else if (type==2) {
			//得到过期 日期
			expireDate =Long.valueOf(expireTime)*60*1000+timestamp;
		}
		//说明 已过期了，当前时间 已超过了 过期时间
		if (System.currentTimeMillis()>expireDate) {
			bool=false;
		}
		return bool;
	}
	
	 /**
	  * 获取前d天的日期时间
	  * @param d  前d天的天数
	  * @return 字符串类型的日期
	  */
	public static String getBeforeDate(int d) {
		Date dNow = new Date(); // 当前时间
		Date dBefore = new Date();

		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, d); // 设置为前一天
		dBefore = calendar.getTime(); // 得到前一天的时间

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		String result = sdf.format(dBefore); // 格式化前n天
		return result;
	}
	
	/**
	  * 获取前d天的日期时间
	  * @param d  前d天的天数
	  * @return 字符串类型的日期
	  */
	public static String getBeforeTime(int d) {
		Date dNow = new Date(); // 当前时间
		Date dBefore = new Date();

		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, d); // 设置为前一天
		dBefore = calendar.getTime(); // 得到前一天的时间

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
		String result = sdf.format(dBefore); // 格式化前n天
		return result;
	}
	
	 /**
	  * 获取前d天的日期时间
	  * @param d  前d天的天数
	  * @return 字符串类型的日期
	  */
	public static String getBeforeDate(int d,Date date) {
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(date);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, d); // 设置为前一天
		dBefore = calendar.getTime(); // 得到前一天的时间

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		String result = sdf.format(dBefore); // 格式化前n天
		return result;
	}
	
	
	 /** 返回指定日期的上个月份
	  * @param d  月份
	  * @return 字符串类型的日期，年月
	  */
	public static String getAfterMonth(int d,Date dNow) {
		//Date dNow = new Date(); // 当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		//calendar.add(Calendar.DAY_OF_MONTH, d); // 设置为前一天
		calendar.add(Calendar.MONTH, d); 
		dBefore = calendar.getTime(); // 得到前一天的时间

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); // 设置时间格式
		String result = sdf.format(dBefore); // 格式化前n天
		return result;
	}
	
	/**
	 * 2个日期是字符串的比较
	 * @param date1  日期1
	 * @param date2  日期2
	 * @param type  类型
	 * @return   1：date1大于date2, -1:date1小于date2, 0:date1等于date2
	 */
	public static int compareDateBy2Str(String date1, String date2, int type) {
		SimpleDateFormat df = null;
		if (type == 0) {
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else if (type == 1) {
			df = new SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 2个日期是日期类型的比较
	 * @param date1  日期1
	 * @param date2  日期2
	 * @param type  类型
	 * @return   1：date1大于date2, -1:date1小于date2, 0:date1等于date2
	 */
	public static int compareDateBy2Date(Date date1, Date date2) {
		try {
			Date dt1 = date1;
			Date dt2 = date2;
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	
	public static int getWeekOfMonth(String dateString){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(dateString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
			return weekOfMonth;
		} catch (Exception e) {
			log.error("获取月份中的周期异常"+e.getMessage(), e);
		}
        return -100;
	}
	
	public static String formatStr(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	
	public static String format(Date date, SimpleDateFormat sdf) {
		return sdf.format(date);
	}
	
	public static String getyyyyMMddHHmmssNow() {
		return sdFormat4.format(new Date());
	}

	public static String getNow() {
		return sdFormat3.format(new Date());
	}

	public static String getDate(Date date) {
		return sdFormat1.format(date);
	}

	public static String getDateForInt(long i) {
		if (i > 0) {
			return sdFormat3.format(new Date(i * 1000L));
		} else {
			return "";
		}
	}

	public static Date parse(String date) {
		try {
			return sdFormat2.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date parse(String date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static int getWeekOfDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return w == 0 ? 7 : w;
	}

	/**
	 * 获得 当前 或者参数时间是今年的第几周
	 * 
	 * @param dt
	 * @return
	 */
	public static int getWeekIndex(Date dt) {
		if (dt == null) {
			dt = new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		long time = 0;
		try {
			// 今年 1月1号 0点
			time = sdf.parse(sdf.format(dt)).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (time == 0) {
			return -1;
		}
		// 从今年1月1号到现在过去的时间
		long lapsed = System.currentTimeMillis() - time
				+ getWeekOfDate(new Date(time)) * 86400000;

		// 这个星期已经过去的时间
		long remainder = lapsed % (86400000 * 7);

		return (int) ((lapsed - remainder) / (86400000 * 7) + (remainder == 0 ? 0
				: 1));
	}

	/**
	 * 获得 榜单批次号
	 * 
	 * @param time
	 *            如果time为空 则返回当期批次号 如果是今年第一周 则检查去年的最后一周
	 * @return
	 */
	public static int getBatchid(long time) {
		// 本周是今年的第几周
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		int index = DateUtil.getWeekIndex(time == 0 ? null : new Date(time));
		int year = Integer.valueOf(sdf.format(time == 0 ? new Date()
				: new Date(time)));
		// 如果是第一周 则检查去年的最后一周
		if (index == 1) {
			index = DateUtil.getWeekIndex(time == 0 ? null : new Date(time
					- (8640000 * 7)));
			year -= 1;
		}
		return year * 100 + index;
	}

	
	public static Date addTime(Date date, int addTime, int unit) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(unit, addTime);
		return cal.getTime();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args)  throws Exception{
		//System.out.println(MD5Utils.string2MD5(MD5Utils.string2MD5("111111")));
		
/*		String dateString = "2017-05-08";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(dateString);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		System.out.println(weekOfMonth);*/
		
		
//		 String[] weeks = { "日", "一", "二", "三", "四", "五", "六" };
//		 Calendar c = Calendar.getInstance();
//		  int week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
//		  int day = c.get(Calendar.DAY_OF_WEEK);//获致是本周的第几天地, 1代表星期天...7代表星期六
//		  System.out.println("今天是本月的第" + week + "周");
//		  System.out.println("今天是星期" + weeks[day-1]);
		
		//System.out.println(getAfterMonth(1, new Date()));
		
		System.out.println(getBeforeTime(-1));
		
	}
	
}
