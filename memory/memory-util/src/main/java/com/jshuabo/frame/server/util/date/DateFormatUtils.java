/**
* Copyright©2014 www.jshuabo.com. all rights reserved.
*
* @Title: DateUtil.java
* @Prject: memory-util
* @Package: com.jshuabo.frame.server.util.date
* @author: lianghe.yuan
* @date: Mar 19, 2014 10:38:29 PM
* @version: v1.0
* @Description: 
*/
package com.jshuabo.frame.server.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: DateFormatUtils
 * @Description: 
 * @author: lianghe.yuan
 * @date: Mar 19, 2014 10:38:29 PM
 */
public class DateFormatUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(DateFormatUtils.class);
    
    public static final String ymd = "yyyy-MM-dd";
    public static final String ymd_hm = "yyyy-MM-dd HH:mm";
    public static final String ymd_hms = "yyyy-MM-dd HH:mm:ss";
    
    private static ThreadLocal<Map<String, DateFormat>> dateFormat = new ThreadLocal<Map<String, DateFormat>>();
    

    
    /**
     * 获得8位的日期 yyyyMMdd
     * 
     * @return
     */
    public static String getChar8(Date date) {
        return DateFormatUtils.format(date, ymd);
    }

    /**
     * 获得14位的日期 yyyyMMddHHmmss
     * 
     * @return
     */
    public static String getChar17(Date date) {
        return DateFormatUtils.format(date, "yyyyMMddHHmmssSSS");
    }

    /**
     * 获得14位的日期 yyyyMMddHHmmss
     * 
     * @return
     */
    public static String getChar14(Date date) {
        return DateFormatUtils.format(date, "yyyyMMddHHmmss");
    }

    /**
     * 获得12位的日期 yyyyMMddHHmm
     * 
     * @return
     */
    public static String getChar12(Date date) {
        return DateFormatUtils.format(date, "yyyyMMddHHmm");
    }
    /**
     * 获得8位的日期 yyyyMMdd
     * 
     * @return
     */
    public static String getChar8() {
        return DateFormatUtils.format(new Date(), "yyyyMMdd");
    }

    /**
     * 获得14位的日期 yyyyMMddHHmmss
     * 
     * @return
     */
    public static String getChar17() {
        return DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
    }

    /**
     * 获得14位的日期 yyyyMMddHHmmss
     * 
     * @return
     */
    public static String getChar14() {
        return DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 获得12位的日期 yyyyMMddHHmm
     * 
     * @return
     */
    public static String getChar12() {
        return DateFormatUtils.format(new Date(), "yyyyMMddHHmm");
    }

    /**
     * 格式化14位的日期
     * 
     * @param char14
     * @return
     */
    public static String formatChar14(String char14) {
        if (char14 == null || char14.length() == 0) return char14;
        return char14.substring(0, 4) + "-" + char14.substring(4, 6) + "-" + char14.substring(6, 8)
                + " " + char14.substring(8, 10) + ":" + char14.substring(10, 12) + ":"
                + char14.substring(12, 14) + " ";
    }

    /**
     * 格式化8位的日期
     * 
     * @param char8
     * @return
     */
    public static String formatChar8(String char8) {
        if (char8 == null || char8.length() == 0) return char8;
        return char8.substring(0, 4) + "-" + char8.substring(4, 6) + "-" + char8.substring(6, 8)
                + " ";

    }

    public static String formatDataBaseChar8(String char8) {
        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd ");
        String time = null;
        try {
            time = DateFormatUtils.format(formate.parse(char8), "yyyyMMdd");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            logger.error("formatDataBaseChar8 can't formate [{}], error with to [{}]",new Object[]{char8,e.getLocalizedMessage()});
        }
        return time;
    }

    public static String formatDataBaseChar12(String char8) {
        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = null;
        try {
            time = DateFormatUtils.format(formate.parse(char8), "yyyyMMddHHmm");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            logger.error("formatDataBaseChar12 can't formate [{}], error with to [{}]",new Object[]{char8,e.getLocalizedMessage()});
        }
        return time;
    }

    /**
     * 返回当天所在的年份
     * 
     * @return yyyy
     */
    public static String getCurrentYear() {
        return getChar8().substring(0, 4);
    }

    /**
     * 返回当天所在的年月
     * 
     * @return yyyyMM
     */
    public static String getCurrentYearMonth() {
        return getChar8().substring(0, 6);
    }

    /**
     * 返回当天所在的月份
     * 
     * @return mm
     */
    public static String getCurrentMonth() {
        return getChar8().substring(4, 6);
    }

    /**
     * 返回指定时间所在月份
     * 
     * @param date
     * @return
     */
    public static String getAssignMonth(String date) {
        if (date != null && date.length() >= 6) return date.substring(4, 6);

        return null;
    }

    /**
     * 根据指定规则格式化日期
     * 
     * @param date
     * @param formatter
     * @return
     */
    public static String formatDate(String date, String formatter) {
        SimpleDateFormat myFormatter = null;
        Date da = null;
        if (date.length() < 15)
            myFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
        else
            myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            da = myFormatter.parse(date);
        } catch (Exception e) {
            logger.error("formatDate can't formate data: [{}] to [{}], error with to [{}]",new Object[]{date,formatter,e.getLocalizedMessage()});
        }
        return DateFormatUtils.format(da, formatter);
    }

    /**
     * @Title: parse
     * @Description: 日期字符串 装日期
     * @param dateStr
     * @param formatter
     * @return: Date
     */
    public static Date parse(String dateStr, String formatter) {
        SimpleDateFormat format = new SimpleDateFormat(formatter);
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            logger.error("parse can't formate string data: [{}] to [{}], error with to [{}]",new Object[]{dateStr,formatter,e.getLocalizedMessage()});
        }
        return null;
    }

    /**
     * 将当前日期向后滚动n天
     * 
     * @param dateNow
     * @param rollDate
     * @return
     */
    public static String rollDate(String dateNow, int rollDate) {
        String dateReturn = "";
        if (dateNow == null || dateNow.trim().length() < 8) return dateReturn;

        dateNow = dateNow.trim();
        Calendar cal = Calendar.getInstance();
        int nYear = Integer.parseInt(dateNow.substring(0, 4));
        int nMonth = Integer.parseInt(dateNow.substring(4, 6));
        int nDate = Integer.parseInt(dateNow.substring(6, 8));
        cal.set(nYear, nMonth - 1, nDate);
        cal.add(Calendar.DATE, rollDate);
        String strYear = String.valueOf(cal.get(Calendar.YEAR));
        String strMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
        String strDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        strMonth = (strMonth.length() == 1) ? "0" + strMonth : strMonth;
        strDay = (strDay.length() == 1) ? "0" + strDay : strDay;
        dateReturn = strYear + strMonth + strDay;
        return dateReturn;
    }

    /**
     * 获取月份的最后一天
     * 
     * @param year
     * @param month
     * @return
     */
    public static int lastDay(Integer year, int month) {

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static DateFormat getDateFormat(String pattern) {
        Map<String, DateFormat> dfMap = dateFormat.get();
        if (dfMap == null) {
            dfMap = new HashMap<String, DateFormat>();
            dateFormat.set(dfMap);
        }
        if (StringUtils.isEmpty(pattern)) throw new IllegalArgumentException("date format patter must not be null or empty!");

        DateFormat df = dfMap.get(pattern);
        if (df == null) {
            df = new SimpleDateFormat(pattern);
            dfMap.put(pattern, df);
        }
        return dfMap.get(pattern);
    }
    
    public static String format(Date date, String pattern) {
        if(date == null) return "";
        DateFormat df = getDateFormat(pattern);
        return df.format(date);
    }
    
//    public static Date parse(String source, String pattern) {
//        DateFormat df = getDateFormat(pattern);
//        try {
//            return df.parse(source);
//        } catch (ParseException e) {
//            logger.error("can't parse source: [{}] to date! catched ParseException:\n\t{}", new Object[]{source, e.getLocalizedMessage()});
//        } catch (Exception e) {
//            logger.error("can't parse source: [{}] to date! catched Exception:\n\t{}", new Object[]{source, e.getLocalizedMessage()});
//        }
//        return null;
//    }

}
