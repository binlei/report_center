package com.jshuabo.reportcenter.server.utils.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: DateUtils
 * @Description: 时间转换工具
 * @author: peng.wu
 * @date: 2014年9月15日 下午10:29:01
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
    // 将excel里面的数字时间转化为准确的时间格式！
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int HOURS_PER_DAY = 24;
    private static final int SECONDS_PER_DAY = (HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE);
    private static final long DAY_MILLISECONDS = SECONDS_PER_DAY * 1000L;

    // 时间格式
    public static final String hms = "HH:mm:ss";
    public static final String ymd = "yyyy-MM-dd";
    public static final String ymd_hm = "yyyy-MM-dd HH:mm";
    public static final String ymd_hms = "yyyy-MM-dd HH:mm:ss";
    public static final String yMd_hms = "yyyy/MM/dd HH:mm:ss";

    private static SimpleDateFormat sdFormat = null;
    
    public static DateFormat dateFormat = null;

    public static void main(String args[]) {
        double date = 41835.3226967593;
        int wholeDays = (int) Math.floor(date);
        int millisecondsInDay = (int) ((date - wholeDays) * DAY_MILLISECONDS + 0.5);
        Calendar calendar = new GregorianCalendar(); // using default time-zone
        setCalendar(calendar, wholeDays, millisecondsInDay, false);
        
        sdFormat =  new SimpleDateFormat("HH:mm:ss");
        System.out.println(sdFormat.format(calendar.getTime()));
        System.out.println(calendar.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        System.out.println(formatter.format(calendar.getTime()));
        
        Date d = ttt(41944.0, hms);
        
        System.out.println(formatter.format(d.getTime()));
    }
    
    public static Date ttt(double date, String format) {
        sdFormat = new SimpleDateFormat(format);
        int wholeDays = (int) Math.floor(date);
        int millisecondsInDay = (int) ((date - wholeDays) * SECONDS_PER_DAY + 0.5);
        Calendar calendar = new GregorianCalendar(); // using default time-zone
        setCalendar(calendar, wholeDays, millisecondsInDay, false);
        return calendar.getTime();
    }

    /**
     * @Title: numberDateFormat
     * @param date 数字时间</> 41835.3226967593
     * @param format 需要转换的时间
     * @return: Date
     * @date: 2014年9月15日 下午10:34:55
     */
    public static Date numberDate2Format(double date, String format) {
        sdFormat = new SimpleDateFormat(format);
        int wholeDays = (int) Math.floor(date);
        int millisecondsInDay = (int) ((date - wholeDays) * DAY_MILLISECONDS + 0.5);
        Calendar calendar = new GregorianCalendar(); // using default time-zone
        setCalendar(calendar, wholeDays, millisecondsInDay, false);
        return calendar.getTime();
    }

    /**
     * @Title: numberDate2FormatString
     * @param date 数字时间</>41835.3226967593
     * @param format 需要转换的格式
     * @return: String 
     * @date: 2014年9月15日 下午10:39:21
     */
    public static String numberDate2FormatString(double date, String format) {
        sdFormat = new SimpleDateFormat(format);
        int wholeDays = (int) Math.floor(date);
        int millisecondsInDay = (int) ((date - wholeDays) * DAY_MILLISECONDS + 0.5);
        Calendar calendar = new GregorianCalendar(); // using default time-zone
        setCalendar(calendar, wholeDays, millisecondsInDay, false);
        return sdFormat.format(calendar.getTime());
    }

    /**
     * @Title: setCalendar
     * @param calendar
     * @param wholeDays
     * @param millisecondsInDay
     * @param use1904windowing
     * @date: 2014年9月15日 下午10:40:57
     */
    public static void setCalendar(Calendar calendar, int wholeDays, int millisecondsInDay,boolean use1904windowing) {
        int startYear = 1900;
        int dayAdjust = -1; // Excel thinks 2/29/1900 is a valid date, which it isn't
        if (use1904windowing) {
            startYear = 1904;
            dayAdjust = 1; // 1904 date windowing uses 1/2/1904 as the first day
        } else if (wholeDays < 61) {
            // Date is prior to 3/1/1900, so adjust because Excel thinks 2/29/1900 exists
            // If Excel date == 2/29/1900, will become 3/1/1900 in Java representation
            dayAdjust = 0;
        }
        calendar.set(startYear, 0, wholeDays + dayAdjust, 0, 0, 0);
        calendar.set(GregorianCalendar.MILLISECOND, millisecondsInDay);
    }
    
    /**
     *  格式化输出日期
     * @param date </br>Date 日期
     * @param format </br>String 格式
     * @return 返回字符型日期
     */
    public static String format(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                dateFormat = new SimpleDateFormat(format);
                result = dateFormat.format(date);
            }
        } catch (Exception e) {
            logger.error("can't convert date :[{}] with error DateUtils.format : [{}]",new Object[]{date,e.getLocalizedMessage()});
        }
        return result;
    }
    
    /**
     * @Title: convert
     * @param date
     * @return: java.sql.Date
     */
    public static java.sql.Date convertSQLDate(String date,String format) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
        try {
            java.util.Date udate = sdf.parse(date);
            java.sql.Date sdate = new java.sql.Date(udate.getTime());
            return sdate;
        } catch (Exception ex) {
            logger.error("can't convert date :[{}] with error DateUtils.convertSQLDate : [{}]",new Object[]{date,ex.getLocalizedMessage()});
            java.util.Date udate = DateUtils.numberDate2Format(Double.parseDouble(date),DateUtils.hms);
            java.sql.Date sdate = new java.sql.Date(udate.getTime());
            return sdate;
        }
    }

}
