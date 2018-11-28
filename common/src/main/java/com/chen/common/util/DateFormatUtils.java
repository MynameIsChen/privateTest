package com.chen.common.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间格式
 */

public class DateFormatUtils {
    public static final DateFormat FORMATOR_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    public static final DateFormat FORMATOR_YMDHMSS = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.CHINA);
    public static final DateFormat FORMATOR_YMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    public static final DateFormat FORMATOR_YMD = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    public static final DateFormat FORMATOR_HMS = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);

    /**
     * 毫秒值转字符串
     *
     * @param millisecond 毫秒值
     * @return 2017-07-07 08:00:00
     */
    public static String format(long millisecond) {
        return FORMATOR_YMDHMS.format(new Date(millisecond));
    }

    public static long YMDHMSString2long(String string) {
        if (TextUtils.isEmpty(string)) return 0;
        try {
            Date date = FORMATOR_YMDHMS.parse(string);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String long2StringYMD(long millisecond) {
        Date mDate = new Date(millisecond);
        return FORMATOR_YMD.format(mDate);
    }

    public static String YMDHMSString2StringYMD(String string) {
        return long2StringYMD(YMDHMSString2long(string));
    }

    public static String long2StringHMS(long millisecond) {
        Date mDate = new Date(millisecond);
        return FORMATOR_HMS.format(mDate);
    }

    public static String string2TimerStringHMS(String millisecondStr) {
        long millisecond = 0;
        if (!TextUtils.isEmpty(millisecondStr))
            millisecond = Long.valueOf(millisecondStr);
        return long2TimerStringHMS(millisecond);
    }

    @SuppressLint("DefaultLocale")
    public static String long2TimerStringHMS(long millisecond) {
        int minute = (int) (millisecond / 60);
        int hour = 0;
        if (minute >= 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        int second = (int) (millisecond % 60);
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    @SuppressLint("DefaultLocale")
    public static String long2TimerStringHM(long millisecond) {
        int minute = (int) (millisecond / 60);
        int hour = 0;
        if (minute >= 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        return String.format("%02d:%02d", hour, minute);
    }

    public static String currentTime2StringYMDHM() {
        Date mDate = new Date(System.currentTimeMillis());
        return FORMATOR_YMDHM.format(mDate);
    }

    public static String currentTime2StringYMDHMS() {
        Date mDate = new Date(System.currentTimeMillis());
        return FORMATOR_YMDHMS.format(mDate);
    }

    public static String YMDHMSS() {
        Date mDate = new Date(System.currentTimeMillis());
        return FORMATOR_YMDHMSS.format(mDate);
    }

    /**
     * 判断两个时间的间隔
     */
    public static int differentDaysByMillisecond(String dateStr, String dateStr2) {
        if (TextUtils.isEmpty(dateStr) || TextUtils.isEmpty(dateStr2))
            return 1;
        try {
            Date date1 = FORMATOR_YMDHMS.parse(dateStr);
            Date date2 = FORMATOR_YMDHMS.parse(dateStr2);
            int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
            return days + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static String getDayMinuteString(String time) {
        return getDayMinute(getDateLong(time, FORMATOR_YMDHMS));
    }

    public static String getDayMinute(long compareTime) {
        String info;
        String afterSuffix = "%1$s后";
        String beforeSuffix = "%1$s前";

        info = getIntervalTime(compareTime);
        int intervalDay = getIntervalDay(compareTime);
        if (intervalDay == 0) {
            if (System.currentTimeMillis() < compareTime) {
                info = String.format(afterSuffix, String.valueOf(info));
            } else if (System.currentTimeMillis() >= compareTime) {
                info = String.format(beforeSuffix, String.valueOf(info));
            }
        } else if (intervalDay > 0) {
            info = intervalDay + "天";
            info = String.format(afterSuffix, String.valueOf(info));
        } else if (intervalDay < 0) {
            info = Math.abs(intervalDay) + "天";
            info = String.format(beforeSuffix, String.valueOf(info));
        }
        return info;
    }

    public static int getIntervalDay(long compareCt) {
        long nowCt = System.currentTimeMillis();
        String n = getDateStr(nowCt, FORMATOR_YMD);
        String r = getDateStr(compareCt, FORMATOR_YMD);
        nowCt = getDateLong(n, FORMATOR_YMD);
        long comCt = getDateLong(r, FORMATOR_YMD);
        return (int) ((comCt - nowCt) / 86400000);
    }

    /**
     * 获取时间间隔,分钟，小时，天
     */
    private static String getIntervalTime(long time) {
        String info = "1分钟";
        long now = System.currentTimeMillis();
        long t = Math.abs(now - time);
        if (t > 60000) {
            t = t / 60000;
            if (t >= 1 && t < 60) {
                info = t + "分钟";
            } else if (t >= 60) {
                t = t / 60;
                if (t >= 1 && t < 24) {
                    info = t + "小时";
                } else if (t >= 24) {
                    t = t / 24;
                    if (t > 0) {
                        info = t + "天";
                    }
                }
            }
        }
        return info;
    }


    public static String getDateStr(long time, DateFormat dateFormat) {
        return dateFormat.format(new Date(time));
    }

    public static long getDateLong(String dateStr, DateFormat dateFormat) {
        Date date;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException var4) {
            return 0L;
        }

        return date.getTime();
    }
}
