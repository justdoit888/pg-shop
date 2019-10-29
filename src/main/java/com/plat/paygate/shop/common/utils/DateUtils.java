package com.plat.paygate.shop.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @ClassName: DateUtils
 * @author: zhjs
 * @createDate: 2019/10/29 下午3:31
 * @JDK: 1.8
 * @Desc: 时间工具类
 */
public class DateUtils {


    /**
     * 获取之前或者之后的时间
     * @param amount 和现在相差几天
     * @param time   时分秒
     * @return
     */
    public static String getSpecialTime(int amount,String time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -7);
        String result = format.format(c.getTime()) + " " + time;
        return result;
    }
}
