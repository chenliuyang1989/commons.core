package com.lumi.aiot.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils
{

    public static final String ENG_DATE_FROMAT = "EEE, d MMM yyyy HH:mm:ss z";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY = "yyyy";
    public static final String MM = "MM";
    public static final String DD = "dd";

    private static final int[] DAY_OF_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static boolean isValidDate(int year, int month, int day)
    {

        if (month < 1 || month > 12 || year < 1900 || day < 1 || day > 31)
        {
            return false;
        }

        if (day > getMaxDayOfMon(year, month))
        {
            return false;
        }
        return true;
    }

    public static int getMaxDayOfMon(int year, int month)
    {

        if (month != 2)
        {
            return DAY_OF_MONTH[month - 1];
        } else if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0)
        {
            return 29;//闰二月
        } else
        {
            return 28;
        }
    }

    /**
     * 日期减法 date1-date2 相差天数
     *
     * @param date1String
     * @param date2String
     * @param pattern     格式化
     * @return
     * @throws ParseException
     */
    public static long minusDay(String date1String, String date2String, String pattern) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date1 = format.parse(date1String);
        Date date2 = format.parse(date2String);
        long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 获取当前日期
     *
     * @param pattern
     * @return
     */
    public static String getNowDateString(String pattern)
    {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date());
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static String date2String(Date date, String formatStr)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        String strDate = sdf.format(date);
        return strDate;
    }
}
