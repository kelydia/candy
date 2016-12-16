package com.cjgod.candy.common.util;

/**
 * Created by lichunjiang on 2016/12/16.
 */

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {
    private static final Logger LOG = LoggerFactory.getLogger(DateFormatUtil.class);
    /**
     * 自增数
     */
    protected static long seq = 0L;

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYY_MM_DD_HH_MM_SS = "";
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String YYYY_MM_DD_HH_MM_SS_SSS000 = "yyyy-MM-dd HH:mm:ss.SSS000";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYMMDD = "yyMMdd";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String HHMMSS = "HHmmss";
    public static final String YYMMDDHHMMSSSSS = "yyMMddHHmmssSSS";
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String YYMMDDHHMM = "yyMMddHHmm";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String HHMMSSSSS = "HHmmssSSS";
    public static final String MM_DD_HH_MM = "MM-dd HH:mm";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String DD_HH_MM = "dd HH:mm";

    public static String stringToString(String date, String sourPattern, String destPattern) throws Exception {
        return dateToString(stringToDate(date, sourPattern), destPattern);
    }

    public static String dateToString(Date date, String pattern) throws Exception {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static Date stringToDate(String date, String pattern) throws Exception {
        DateFormat df = new SimpleDateFormat(pattern);
        df.setLenient(false);
        return df.parse(date);
    }


    /**
     * String类型转Date，转换失败返回NULL
     *
     * @param date    日期
     * @param pattern pattern
     * @return Date
     */
    public static Date stringToDate2(String date, String pattern) {
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            df.setLenient(false);
            return df.parse(date);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Date类型转String，转换失败返回空字符串
     *
     * @param date    日期
     * @param pattern pattern
     * @return string字符串
     */
    public static String dateToString2(Date date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static Date getYyyyMmDdOfDate(Date date) throws Exception {
        return stringToDate(dateToString(date, YYYY_MM_DD), YYYY_MM_DD);
    }

    public static Date getTimeOfDate(Date date) throws Exception {
        DateFormat df = new SimpleDateFormat(HH_MM_SS);
        return df.parse(df.format(date));
    }

    public static Date now() {
        return new Date();
    }

    public static String sequenceNo() {
        try {
            return dateToString(now(), YYMMDDHHMMSSSSS) + "." + (seq++);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 根据时间格式获取当前的时间，获取失败则返回空字符串。
     *
     * @param pattern 格式为参照@see com.rongqiangu.weather.common.util.DateFormatUtil 常量
     * @return 指定格式的时间
     */
    public static String getCurrentDateTime(String pattern) {
        String currentDateTime = "";
        try {
            currentDateTime = dateToString(new Date(), pattern);
        } catch (Exception e) {
            if (LOG.isErrorEnabled()) {
                LOG.error("日期转型失败:{}", e.getCause());
            }
        }
        return currentDateTime;
    }

    /**
     * 获取当月开始时间
     *
     * @param pattern 格式化样式
     * @return 格式化后的日期字符串
     */
    public static String getCurrentMonthStartTime(String pattern) {
        return formatDate(new Date(), pattern, true, 0, 0, 0, 0);
    }

    /**
     * 获取当月开始时间
     *
     * @return 格式化后的日期字符串
     */
    public static String getCurrentMonthStartTime() {
        return formatDate(new Date(), YYYYMMDDHHMMSSSSS, true, 0, 0, 0, 0);
    }

    /**
     * 获取当月结束时间
     *
     * @param pattern 格式化样式
     * @return 格式化后的日期字符串
     */
    public static String getCurrentMonthEndTime(String pattern) {
        return formatDate(new Date(), pattern, false, 23, 59, 59, 999);
    }

    /**
     * 获取当月结束时间
     *
     * @return 格式化后的日期字符串
     */
    public static String getCurrentMonthEndTime() {
        return formatDate(new Date(), YYYYMMDDHHMMSSSSS, false, 23, 59, 59, 999);
    }

    /**
     * 获取指定时间的月份开始时间
     *
     * @param date    指定时间
     * @param pattern 模式
     * @return 格式化后的日期字符串
     */
    public static String getMonthStartDateTime(Date date, String pattern) {
        return formatDate(date, pattern, true, 0, 0, 0, 0);
    }

    /**
     * 获取指定时间的月份结束时间
     *
     * @param date    指定月份
     * @param pattern 模式
     * @return 格式化后的日期字符串
     */
    public static String getMonthEndDateTime(Date date, String pattern) {
        return formatDate(date, pattern, false, 23, 59, 59, 999);
    }

    /**
     * 获取指定月份的第一天获取最后一天的时间
     * isFirstDate=true:yyyy-MM-01 00:00:00.000
     * isFirstDate=false:yyyy-MM-31(30) 23:59:59.999
     *
     * @param date        需要格式的日期
     * @param pattern     模式，yyyy-MM-dd HH:mm:ss等
     * @param isFirstDay  是否第一天，true每月第一天，false每月最后一天
     * @param hour        指定小时，0-23
     * @param minute      指定分钟，0-59
     * @param second      指定秒，0-59
     * @param millisecond 指定毫秒，0-999
     * @return 格式化后的日期字符串
     */
    private static String formatDate(Date date, String pattern, boolean isFirstDay, int hour, int minute, int second,
                                     int millisecond) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (isFirstDay) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return format.format(calendar.getTime());
    }

    /**
     * 根据pattern获取当日开始时间
     *
     * @param pattern 模式，yyyy-MM-dd HH:mm:ss等
     * @return 当日开始时间
     */
    public static String getTodayStartTime(String pattern) {
        return formatDate(new Date(), pattern, 0, 0, 0, 0);
    }

    /**
     * 获取当日开始时间
     *
     * @return 当日开始时间
     */
    public static String getTodayStartTime() {
        return formatDate(new Date(), YYYYMMDDHHMMSSSSS, 0, 0, 0, 0);
    }

    /**
     * 根据pattern获取当日结束时间
     *
     * @param pattern 模式，yyyy-MM-dd HH:mm:ss等
     * @return 当日结束时间
     */
    public static String getTodayEndTime(String pattern) {
        return formatDate(new Date(), pattern, 23, 59, 59, 999);
    }

    /**
     * 获取当日结束时间
     *
     * @return 当日结束时间
     */
    public static String getTodayEndTime() {
        return formatDate(new Date(), YYYYMMDDHHMMSSSSS, 23, 59, 59, 999);
    }

    /**
     * 获取日期的月初开始时间
     *
     * @return
     */
    public static String getMonthStartTime(Date date) {
        return formatDate(date, YYYYMMDDHHMMSSSSS, true, 0, 0, 0, 0);
    }

    /**
     * 获取日期当日的开始时间
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getStartTime(Date date, String pattern) {
        return formatDate(date, pattern, 0, 0, 0, 0);
    }

    /**
     * 获取日期当日的结束时间
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getEndTime(Date date, String pattern) {
        return formatDate(date, pattern, 23, 59, 59, 999);
    }

    /**
     * 按指定格式，格式化日期
     *
     * @param date        需要格式的日期
     * @param pattern     模式，yyyy-MM-dd HH:mm:ss等
     * @param hour        指定小时，0-23
     * @param minute      指定分钟，0-59
     * @param second      指定秒，0-59
     * @param millisecond 指定毫秒，0-999
     * @return 格式化后的日期字符串
     */
    private static String formatDate(Date date, String pattern, int hour, int minute, int second, int millisecond) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return format.format(calendar.getTime());
    }

    /**
     * 获取Cron类型的时间
     *
     * @param date 需要转换的日期
     * @return 转换为Cron类型的日期字符串
     */
    public static String getCron(Date date) {
        return new SimpleDateFormat("ss mm HH dd MM ? yyyy").format(date);
    }

    /**
     * 增加时间
     *
     * @param date   日期
     * @param field  the calendar field. Calendar.MONTH，Calendar.HOUR_OF_DAY等 @link Calendar
     * @param amount the amount of date or time to be added to the field.
     * @return 增加后的日期
     */
    public static Date add(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static String getWeekOfDate(String time) {
        if (StringUtils.isEmpty(time))
            return "";
        Date date = null;
        String formatDate = null;
        try {
            date = DateFormatUtil.stringToDate(time, DateFormatUtil.YYYYMMDDHHMMSSSSS);
            formatDate = DateFormatUtil.dateToString(date, DateFormatUtil.YYYY_MM_DD);
        } catch (Exception e) {
        }
        String[] weekDaysName = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return formatDate + "(" + weekDaysName[intWeek] + ")";
    }


    /**
     * 判断开始时间和截止时间是否符合彩种规则
     *
     * @param time1  比较的日期
     * @param time2  被比较的日期
     * @param format 格式
     * @return (true :time1<time2) (false :time1>time2)
     */
    public static int compareTimes(String time1, String time2, String format) {
        Date DateTime1 = null;
        Date DateTime2 = null;
        try {
            DateTime1 = stringToDate(time1, format);
            DateTime2 = stringToDate(time2, format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DateTime1.compareTo(DateTime2);
    }


}
