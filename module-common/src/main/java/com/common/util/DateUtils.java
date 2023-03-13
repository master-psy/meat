package com.common.util;

import lombok.extern.log4j.Log4j2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Calendar.DAY_OF_MONTH;

/**
 * @author maoyuchun
 * @version 创建时间：2016年6月28日 下午2:38:03
 */
@Log4j2
public class DateUtils {

    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 格式化时间 返回字符串 格式 "yyyy-MM-dd HH:mm:ss"
     *
     * @param seconds
     * @param format
     * @return
     */
    public static String date2String(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);

    }

    public static String date2StringSpot(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        return format.format(date);

    }

    /**
     * 日期转年月日格式字符串
     *
     * @param date 日期
     * @return 年月日格式字符串
     */
    public static String date2StringDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    public static String date2StringyyyyMMdd(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String timestamp2String(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (timestamp == 0)
            return "";
        return format.format(new Date(timestamp));
    }

    public static String timestamp2String(String timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(Long.parseLong(timestamp)));
    }

    public static String timestamp2StringSpot(String timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        return format.format(new Date(Long.parseLong(timestamp)));
    }

    public static Date strToDate(String dateStr) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.parse(dateStr);
        } catch (ParseException e) {
            log.error("异常:", e);
            return null;
        }
    }

    /**
     * yyyyMMdd转yyyy-MM-dd HH:mm:ss
     *
     * @param yearMonthDayStr
     * @return
     */
    public static Date yearMonthDayToDate(String yearMonthDayStr) {
        List<String> stringList = Arrays.asList(yearMonthDayStr.split(""));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            if (i == 3) {
                stringList.set(3, stringList.get(3) + "-");
            }
            if (i == 5) {
                stringList.set(5, stringList.get(5) + "-");
            }
            stringBuilder.append(stringList.get(i));
        }
        return changeStrToDateyyyyMMdd(stringBuilder.toString());
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds   精确到秒的字符串
     * @param formatStr
     * @return
     */
    public static Date timeStamp2Date(long seconds) {
        if (seconds == 0) {
            return null;
        }
        return new Date(seconds);
    }

    /**
     * 毫秒格式时间戳转年月日格式字符串
     *
     * @param millSeconds 毫秒格式时间戳
     * @return 年月日格式字符串
     */
    public static String timeStamp2StringDate(long millSeconds) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(new Date(millSeconds));
    }

    /**
     * 毫秒格式时间戳转年月日格式字符串
     *
     * @param millSeconds 毫秒格式时间戳
     * @return 年月日格式字符串
     */
    public static String timeStamp2StringDateYYMMDD(long millSeconds) {
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        return format.format(new Date(millSeconds));
    }

    public static String timeStamp2StringDateYYYYMM(long millSeconds) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(new Date(millSeconds));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date   字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return String.valueOf(format.parse(date_str).getTime());
        } catch (Exception e) {
            log.error("异常:", e);
        }
        return "";
    }

    /**
     * yyyy-MM-dd HH:mm:ss 格式时间转换成 13位时间戳 1000000000000
     *
     * @param date_str
     * @return
     */
    public static long dateStr2TimeStamp(String date_str) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.parse(date_str).getTime();
        } catch (Exception e) {
            log.error("异常:", e);
        }
        return 0l;
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return
     */
    public static String timeStamp() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr
     * @return
     */
    public static Date changeStrToDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateParse = null;
        try {
            dateParse = sdf.parse(dateStr);
        } catch (ParseException e) {
            log.error("异常:", e);
        }
        return dateParse;
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr
     * @return
     */
    public static Date changeStrToDateyyyyMMdd(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateParse = null;
        try {
            dateParse = sdf.parse(dateStr);
        } catch (ParseException e) {
            log.error("异常:", e);
        }
        return dateParse;
    }

    /**
     * 判断时间是否当月
     *
     * @param time
     * @return
     */
    public static boolean isThisMonth(long time) {
        String pattern = "yyyy-MM";
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);
        String now = sdf.format(new Date());
        if (param.equals(now))
            return true;
        return false;
    }

    /**
     * 获取当月的第一天,不带时分秒
     */
    public static String getStartDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar instance = Calendar.getInstance();
        instance.set(DAY_OF_MONTH, 1);
        Date time = instance.getTime();
        String startDate = format.format(time);
        return startDate;
    }

    /**
     * 获取当前时间,不带时分秒
     */

    public static String getEndDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long currentTimeMillis = System.currentTimeMillis();
        String endDate = format.format(new Date(currentTimeMillis));
        return endDate;
    }

    /**
     * 获取当月的第一天,带时分秒
     */
    public static String getStartDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar instance = Calendar.getInstance();
        instance.set(DAY_OF_MONTH, 1);
        //将小时至0
        instance.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        instance.set(Calendar.MINUTE, 0);
        //将秒至0
        instance.set(Calendar.SECOND, 0);
        //将毫秒至0
        instance.set(Calendar.MILLISECOND, 0);
        //获得当前月第一天
        Date time = instance.getTime();
        String startDate = format.format(time);
        return startDate;
    }

    /**
     * 获取当前时间,带时分秒
     */

    public static String getEndDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTimeMillis = System.currentTimeMillis();
        String endDate = format.format(new Date(currentTimeMillis));
        return endDate;
    }

    /**
     * 获取前一天日期yyyy-MM-dd
     *
     * @return
     */
    public static String getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return yesterday;
    }

    /**
     * 获取前一天日期yyyyMMdd
     */
    public static String dateToYearMonthDayStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
        return yesterday;
    }

    public static String dateToYearMonthDayStr2() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return yesterday;
    }

    /**
     * 获取当天日期yyyyMMdd
     */
    public static String getCurrentDayYearMonthDayStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        String today = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
        return today;
    }

    /**
     * 获取当月第一时刻的时间戳
     *
     * @return
     */
    public static long getTimestampOfFirstDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    public static long getTimestampOfLastDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(DAY_OF_MONTH, c.getActualMaximum(DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 59);
        return c.getTimeInMillis();
    }

    /**
     * 获取今天是周几
     * 周一:1  周二:2  周三:3  周四:4  周五:5  周六:6  周日:7
     *
     * @return
     */
    public static int getDayOfWeek() {
        Calendar c = Calendar.getInstance();
        int i = c.get(Calendar.DAY_OF_WEEK);
        if (i == 1) {
            return 7;
        } else {
            return i - 1;
        }
    }

    /**
     * 获取当前日期 yyyyMMdd
     *
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    /**
     * 获取某个日期前多少天
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getDaysBefore(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(DAY_OF_MONTH, -days);
        return calendar.getTime();
    }

    /**
     * 获取solr时间
     *
     * @param date
     * @return
     */
    public static String getSolrDate(Date date) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        sdf2.setTimeZone(TimeZone.getTimeZone("UTC"));
        String result = sdf1.format(date) + "T" + sdf2.format(date) + "Z";
        return result;
    }

    /**
     * 获取N年以后的日期
     *
     * @param startDate 开始时间节点
     * @param years     年数
     * @return
     */
    public static long getAfterYearDate(Date startDate, int years) {
        Calendar calendar = Calendar.getInstance();
        if (null != startDate) {
            calendar.setTime(startDate);
        }
        calendar.add(Calendar.YEAR, years);
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String dateTime = format.format(date);
//		System.out.println(dateTime);
        return date.getTime();
    }

    /**
     * 计算两个时间戳相隔多少天
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int equation(long startTime, long endTime) {
        startTime = DateUtils.dateStr2TimeStamp(DateUtils.timestamp2String(startTime));
        endTime = DateUtils.dateStr2TimeStamp(DateUtils.timestamp2String(endTime));
        int newL = (int) ((endTime - startTime) / (1000 * 3600 * 24));
        return newL;

    }

    /**
     * 计算两个时间戳相隔多少小时
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int equation2(long startTime, long endTime) {
        startTime = DateUtils.dateStr2TimeStamp(DateUtils.timestamp2String(startTime));
        endTime = DateUtils.dateStr2TimeStamp(DateUtils.timestamp2String(endTime));
        int hours = (int) ((endTime - startTime) / (1000 * 3600));
        return hours;

    }

    /**
     * start
     * 本周开始时间戳 - 以星期一为本周的第一天
     */
    public static String getWeekStartTime() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day == 0) {
            day = 7;
        }
        cal.add(Calendar.DATE, -day + 1);
        Date date = cal.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf1.format(date);
        return startTime + " 00:00:00";
    }

    /**
     * end
     * 本周结束时间戳 - 以星期一为本周的第一天
     */
    public static String getWeekEndTime() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day == 0) {
            day = 7;
        }
        cal.add(Calendar.DATE, -day + 7);
        Date date = cal.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String endTime = sdf1.format(date);
        return endTime + " 23:59:59";
    }

    /**
     * 获取过去N月日期
     *
     * @param num
     * @return
     */
    public static String getLastMonth(int num) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -num);
        Date m = cal.getTime();
        return format.format(m);
    }

    public static long getFisrtDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    public static long getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTimeInMillis();
    }

    /**
     * 获取一天中剩余的时间（秒数）
     */
    public static Integer getRemainSecondsOneDay(Date currentDate) {
        Calendar midnight = Calendar.getInstance();
        midnight.setTime(currentDate);
        midnight.add(midnight.DAY_OF_MONTH, 1);
        midnight.set(midnight.HOUR_OF_DAY, 0);
        midnight.set(midnight.MINUTE, 0);
        midnight.set(midnight.SECOND, 0);
        midnight.set(midnight.MILLISECOND, 0);
        Integer seconds = (int) ((midnight.getTime().getTime() - currentDate.getTime()) / 1000);
        return seconds;
    }

    /**
     * 获取两个时间戳之间的日期，格式[2020-01-18, 2020-01-19]
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getDayLists(long startTime, long endTime) {
        List<String> days = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date start = new Date(startTime);
        Date end = new Date(endTime);
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        tempEnd.add(Calendar.DATE, +1);
        while (tempStart.before(tempEnd)) {
            days.add(dateFormat.format(tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return days;
    }

    /**
     * 获取当天的开始时间戳
     *
     * @param date_str
     * @return
     */
    public static long getStartMillis(String date_str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(date_str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    /**
     * 获取当天的结束时间戳
     *
     * @param date_str
     * @return
     */
    public static long getEndMillis(String date_str) {
        return getStartMillis(date_str) + 24 * 60 * 60 * 1000 - 1;
    }

    public static void main(String[] args) {
        String s = timeStamp2StringDateYYMMDD(1641347696439L);
        System.out.println(s);

        long l = dateStr2TimeStamp("2022-02-26 00:00:00");
        System.out.println(l);
    }

}
