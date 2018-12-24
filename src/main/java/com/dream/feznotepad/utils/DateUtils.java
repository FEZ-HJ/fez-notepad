package com.dream.feznotepad.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by H.J
 * 2018/12/5
 */
public class DateUtils {

    /**
     * 日期转为yyyy-MM-dd HH:mm:ss字符串
     */
    public static String dateToStrLong(Date time){
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        return formatter.format(time);
    }

    /**
     * 日期转为yyyy-MM-dd字符串
     */
    public static String dateToStrshort(Date time){
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat ("yyyy-MM-dd");
        return formatter.format(time);
    }

    /**
     * 获取HH:mm类型的时间
     * @param time
     * @return
     */
    public static String dateToStrTime(Date time){
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat ("HH:mm");
        return formatter.format(time);
    }

    /**
     * 返回当前日期的yyyy-MM-dd的格式
     * @return yyyy-MM-dd
     */
    public static Date getNowDate(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        return formatter.parse(dateString, pos);
    }

    /**
     * 将2018-11-22转化为日期格式
     * @param strDate 2018-11-22
     */
    public static Date strToDateShort(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 将2018-11-22 11:22:00转化为日期格式
     * @param strDate 2018-11-22 11:22:00格式
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 计算两个2018-11-22 11:22:00字符串格式的日期的时间差
     * @param begin 2018-11-22 11:22:00
     * @param end 2018-11-22 11:22:00
     * @return 分钟数
     */
    public static long getDuration(String begin,String end){
        Date d1 = DateUtils.strToDateLong(begin);
        Date d2 = DateUtils.strToDateLong(end);
        return (d2.getTime() - d1.getTime())/1000/60;
    }

}
