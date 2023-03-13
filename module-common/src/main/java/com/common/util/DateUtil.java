package com.common.util;


import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public final static String DEFAULT_TIMEZONE = "GMT+8";

    public final static String ISO_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public final static String SHORT_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    public final static String FULL_SEQ_FORMAT = "yyyyMMddHHmmssSSS";

    public final static String SEQ_FORMAT = "yyyyMMddHHmmss";

    public final static String[] MULTI_FORMAT = {DEFAULT_DATE_FORMAT, ISO_DATE_TIME_FORMAT, DATE_TIME_FORMAT, SHORT_TIME_FORMAT, "yyyy-MM"};

    public final static String FORMAT_YYYY = "yyyy";

    public final static String FORMAT_YYYYMM = "yyyyMM";

    public final static String FORMAT_YYYYMMDD = "yyyyMMdd";

    public final static String FORMAT_YYYYMMDDHH = "yyyyMMddHH";

    public static final String FORMAT_YYYYMMDD_SLASH = "yyyy/MM/dd";

    public static final String FORMAT_DDMMYYYY_SLASH = "dd/MM/yyyy";

    public final static SimpleDateFormat DateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
    public final static SimpleDateFormat DateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    public final static SimpleDateFormat TimeFormat = new SimpleDateFormat("HH:mm:ss");

    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return DateTimeFormat.format(date);
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        return DateFormat.format(date);
    }

    public static String formatDate(Date date, String format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }

    public static String seqFormatDate(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(SEQ_FORMAT).format(date);
    }

    public static Date formatStringDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(SEQ_FORMAT);
        Date utilDate = null;
        if (Strings.isBlank(date))
            return null;
        try {
            utilDate = sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
        return utilDate;
    }

    public static Date formatStringDateTime(String date) {
        Date datetime = null;
        SimpleDateFormat sdf = new SimpleDateFormat(ISO_DATE_TIME_FORMAT);
        if (Strings.isBlank(date))
            return null;
        try {
            datetime = sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
        return datetime;
    }


    public static Date parseMultiFormatDate(String date) {
        if (Strings.isBlank(date))
            return null;

        try {
            return DateUtils.parseDate(date, MULTI_FORMAT);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String nowTimeString() {
        return formatDateTime(new Date());
    }

    public static Integer formatDateToInt(Date date, String format) {
        if (date == null) {
            return null;
        }
        return Integer.valueOf(new SimpleDateFormat(format).format(date));
    }

    public static Long formatDateToLong(Date date, String format) {
        if (date == null) {
            return null;
        }
        return Long.valueOf(new SimpleDateFormat(format).format(date));
    }

    public static String formatShortTime(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(SHORT_TIME_FORMAT).format(date);
    }

    public static Date parseDate(String date) {
        return parseTime(date, DateFormat);
    }

    public static Date parseDate(String dateString, String oldDateFormatter) {
        SimpleDateFormat formatter = new SimpleDateFormat(oldDateFormatter);
        formatter.setLenient(false);
        Date newDate = null;
        try {
            newDate = formatter.parse(dateString);
            return newDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return newDate;
        }
    }

    public static String parseDate(String dateString, String oldDateFormatter, String newDateFormatter) {
        SimpleDateFormat formatter = new SimpleDateFormat(oldDateFormatter);
        formatter.setLenient(false);
        Date newDate = null;
        try {
            newDate = formatter.parse(dateString);
            DateFormat formatterNew = new SimpleDateFormat(newDateFormatter);
            String str = formatterNew.format(newDate);
            return str;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseDateTime(String date) {
        return parseTime(date, DateTimeFormat);
    }

    public static Date parseTime(String date, String format) {
        return parseTime(date, new SimpleDateFormat(format));
    }

    public static Date parseTime(String date, SimpleDateFormat format) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        try {
            return format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getHumanDisplayForTimediff(Long diffMillis) {
        if (diffMillis == null) {
            return "";
        }
        long day = diffMillis / (24 * 60 * 60 * 1000);
        long hour = (diffMillis / (60 * 60 * 1000) - day * 24);
        long min = ((diffMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long se = (diffMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day + "D");
        }
        DecimalFormat df = new DecimalFormat("00");
        sb.append(df.format(hour) + ":");
        sb.append(df.format(min) + ":");
        sb.append(df.format(se));
        return sb.toString();
    }

    /**
     * @Title:getDiffDay
     * @Description:获取日期相差天数
     * @param:@param beginDate 字符串类型开始日期
     * @param:@param endDate 字符串类型结束日期
     * @param:@return
     * @return:Long 日期相差天数
     * @author:谢
     * @thorws:
     */
    public static Long getDiffDay(String beginDate, String endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Long checkday = 0l;
        // 开始结束相差天数
        try {
            checkday = (formatter.parse(endDate).getTime() - formatter.parse(beginDate).getTime()) / (1000 * 24 * 60 * 60);
        } catch (ParseException e) {
            checkday = null;
        }
        return checkday;
    }

    /**
     * @Title:getDiffDay
     * @Description:获取日期相差天数
     * @param:@param beginDate Date类型开始日期
     * @param:@param endDate Date类型结束日期
     * @param:@return
     * @return:Long 相差天数
     * @author: 谢
     * @thorws:
     */
    public static Long getDiffDay(Date beginDate, Date endDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strBeginDate = format.format(beginDate);

        String strEndDate = format.format(endDate);
        return getDiffDay(strBeginDate, strEndDate);
    }

    /**
     * @Title:getSpanDays
     * @Description:获取日期间隔天数，向上取整
     * @param:@param beginDate Date类型开始日期
     * @param:@param endDate Date类型结束日期
     * @param:@return
     * @return:long 间隔天数
     * @author:
     * @thorws:
     */
    public static int getSpanDays(Date beginDate, Date endDate, int spanDays) {
        // 开始结束相差天数
        return (int) Math.ceil((double) (endDate.getTime() - beginDate.getTime()) / (1000 * 24 * 60 * 60));
    }

    /**
     * N天之后
     *
     * @param n
     * @param date
     * @return
     */
    public static Date nDaysAfter(Integer n, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + n);
        return cal.getTime();
    }

    /**
     * N天之前
     *
     * @param n
     * @param date
     * @return
     */
    public static Date nDaysAgo(Integer n, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - n);
        return cal.getTime();
    }

    public static Integer getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return Integer.valueOf(formatDate(date, FORMAT_YYYY) + c.get(Calendar.WEEK_OF_YEAR));
    }

    /*
     * return (date1 < date2)
     */
    public static boolean compareDate(Date date1, Date date2) {
        return (date1.getTime() < date2.getTime());
    }

    public static Date addHour(Date date, int addHour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, addHour);
        return cal.getTime();
    }

    public static Date addYear(Date date, int addYear) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, addYear);
        return cal.getTime();
    }

    public static Date addSecond(Date date, int addSecond) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, addSecond);
        return cal.getTime();
    }

    public static Date addDay(Date date, int add) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, add);
        return cal.getTime();
    }

    public static Date addMonth(Date date, int add) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, add);
        return cal.getTime();
    }

    public static Date addMinute(Date date, int add) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, add);
        return cal.getTime();
    }

    /**
     * 获取给定时间的前某些天或后某些天
     *
     * @param date
     * @param addDate
     * @return
     */
    public static String addDate(String date, int addDate) {
        if (Strings.isBlank(date)) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT).parse(date));
        } catch (Exception pe) {
            return null;
        }
        c.add(Calendar.DATE, addDate);
        return DateUtil.formatDate(c.getTime(), DateUtil.DATE_TIME_FORMAT);
    }

    /**
     * 比较两个字符串时间的大小
     *
     * @param starttime
     * @param endtime
     * @param type
     * @return
     */
    public static long compareToTime(String starttime, String endtime, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        long time = 0;
        try {
            Date start = sdf.parse(starttime);
            Date end = sdf.parse(endtime);
            time = start.getTime() - end.getTime();
        } catch (Exception pe) {
            // return;
        }
        return time;
    }

    public static long getDuration(LocalDateTime begin) {
        LocalDateTime end = LocalDateTime.now();
        return Duration.between(begin, end).toMillis();
    }

    /**
     * 获取某天或者某天之后1天的开始时间
     *
     * @param date
     * @param todayFlag
     * @return
     */
    public static Date getStartTimeOfOneDayOrNextDay(Date date, Boolean todayFlag) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if (todayFlag) {
            cal.set(Calendar.MILLISECOND, 0);
        } else {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return new Date(cal.getTimeInMillis());
    }

    public static Date getEndOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return new Date(cal.getTimeInMillis());
    }

    public static Date parseDateByTimeZone(Long createTime, String timeZone, boolean ms) {
        Date date = null;
        try {
            int n = ms ? 1 : 1000;
            java.text.DateFormat dateFormat = new SimpleDateFormat("Z");
            String localTimeZone = dateFormat.format(new Date());
            TimeZone srcTimeZone = TimeZone.getTimeZone("GMT" + localTimeZone);
            TimeZone destTimeZone = TimeZone.getTimeZone(timeZone);
            DateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
            String destDateTime = dateTransformBetweenTimeZone(new Date(createTime * n), formatter, srcTimeZone, destTimeZone);
            date = DateTimeFormat.parse(destDateTime);
        } catch (Exception e) {
            date = null;
        }

        return date;
    }

    private static String dateTransformBetweenTimeZone(Date sourceDate, DateFormat formatter, TimeZone sourceTimeZone, TimeZone targetTimeZone) {
        Long targetTime = sourceDate.getTime() - sourceTimeZone.getRawOffset() + targetTimeZone.getRawOffset();
        return formatter.format(new Date(targetTime));
    }

    public static String getWeek(Date specifiedDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(specifiedDay);
        return String.valueOf(c.get(7));
    }

    public static Integer getDay(Date specifiedDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(specifiedDay);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static Date setDate(Date initDays, int month, Integer day) {
        Calendar c = Calendar.getInstance();
        c.setTime(initDays);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    public static Integer getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH);
    }

    public static Date format(Date date, String format) {
        DateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        String str = formatter.format(date);
        Date formateDate = parseDate(str, format);
        return formateDate;
    }

    /**
     * 获取本月第一天
     */
    public static Date getFirstDayOfMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取本周第一天
     */
    public static Date getFirstDayOfWeek() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static String formatShortTime(Date date, String type) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if ("start".equals(type)) {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            return new SimpleDateFormat(SHORT_TIME_FORMAT).format(c.getTime());
        } else {
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            return new SimpleDateFormat(SHORT_TIME_FORMAT).format(c.getTime());
        }
    }

    public static int getAge(Date birthDay){
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;//当前日期在生日之前，年龄减一
                }
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        } return age;
    }

}
