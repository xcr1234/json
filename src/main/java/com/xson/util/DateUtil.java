package com.xson.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String format(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        return simpleDateFormat.format(date);
    }
    public static String format(Date date,String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}
