package com.sszh.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 *
 * @author CSDN:seesun2012
 * @version 1.1
 * @CreateDate 2019年06月12日
 */
public class DateUtils extends CommonUtils {

    //1970年的日期字符串
    private static final String STATIC_DATE_1970 = "1970-01-01 ";
    // 默认显示日期的格式
    public static final String DATE_FORMAT = "yyyy-MM-dd";


    /**
     * 根据时分秒字符串获取时间戳
     *
     * @param timeStr 格式如：08:08:08
     * @return 返回一个1970年的日期
     * @throws ParseException
     */
    public static Long getHhMmSsTime(String timeStr) throws ParseException {
        return getHhMmSsDate(timeStr).getTime();
    }

    //返回日期格式
    public static Date getHhMmSsDate(String timeStr) throws ParseException {
        timeStr = STATIC_DATE_1970 + timeStr.trim();
        SimpleDateFormat SDF_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return SDF_HH_MM_SS.parse(timeStr);
    }


    /**
     * 获取今天的日期，格式如：Tue May 28 00:00:00 GMT+08:00 2019           2019-05-28 00:00:00
     *
     * @param pattern 日期格式，不传入则默认：yyyy-MM-dd
     * @return Date -   默认返回今天的日期为日期格式
     */
    public static Date getTodayDate(String pattern) throws ParseException {
        if (null == pattern || "".equals(pattern.trim())) pattern = DATE_FORMAT;
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat tsdf = new SimpleDateFormat(pattern);
        tsdf.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(sdf.format(now.getTime()));
    }

    //返回字符串
    public static String getTodayStr() {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(now.getTime()));
    }

    //不带参数返回今天日期
    public static Date getTodayDate() throws ParseException {
        return getTodayDate(null);
    }


    /**
     * 获取若干天之前/之后的日期
     *
     * @param days -    间隔天数
     */
    public static String getInternalTimeByDay(Integer days) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        now.add(Calendar.DATE, days);
        return (sdf.format(now.getTime()));
    }


    /**
     * 根据给定日期Date返回周几
     *
     * @param date
     * @return int 1~7 周一~周日
     */
    public static int dayForWeek(Date date) {
        String dateStr = date.toString().substring(0, 3).toLowerCase();
        if ("mon".equals(dateStr)) {
            return 1;
        } else if ("tue".equals(dateStr)) {
            return 2;
        } else if ("wed".equals(dateStr)) {
            return 3;
        } else if ("thu".equals(dateStr)) {
            return 4;
        } else if ("fri".equals(dateStr)) {
            return 5;
        } else if ("sat".equals(dateStr)) {
            return 6;
        } else if ("sun".equals(dateStr)) {
            return 7;
        }
        return -1;
    }


    /**
     * 获取某天的凌晨时间
     *
     * @param date
     * @return
     */
    public static Date getFirstDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


}
