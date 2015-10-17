package org.smile.microcampus.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ben on 2015/10/16.
 * 用来将时间转化为时间戳的格式
 */
public class TimeFormat {

    public static String format(String format, String time) {
        String result = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = df.parse(time);
            SimpleDateFormat df1 = new SimpleDateFormat(format);
            result = df1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
