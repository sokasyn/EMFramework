package com.emin.digit.mobile.android.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Samson on 16/7/13.
 *
 * 日期时间的处理工具
 */
public class DateUtil {

    private static final String DATE_TEMPLATE = "YYYY-MM-dd HH:mm:ss.SSS";

    /*
     * @param 无
     * @return 默认格式模版的当前日期字符串 如 2016-07-18 17:25:51.941
     */
    public static String getCurrentDateString(){
        return getCurrentDateStringWithTemplate(DATE_TEMPLATE);
    }

    /*
     * @param 自定义的日期格式模版字符串 如"YYYY-MM-dd HH:mm:ss"
     * @return 根据自定义日期格式模版生成的当前日期的字符串
     */
    public static String getCurrentDateStringWithTemplate(String dateTemplate){
        Date nowDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateTemplate);
        String dateString = dateFormat.format(nowDate);
        return dateString;
    }
}
