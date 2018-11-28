package com.chen.test.test;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by chenxianglin on 2018/3/15.
 * Class note:
 */

public class BirthTest {

    public static void main(String[] args){

    }

    public static String getBirthDay(long birthTime, long nTime) {
        //this is a terrible parameter
        //是否为出生前
        boolean gestation = false;
        if (birthTime > nTime) {
            gestation = true;
            long temp = birthTime;
            birthTime = nTime;
            nTime = temp;
        }

        //收集生日
        String birth = "";
        //某个日子的年月日
        Date n = new Date(nTime);
        int nDay = n.getDate();
        int nMonth = n.getMonth();
        int nYear = n.getYear();

        //生日的年月日
        Date b = new Date(birthTime);
        int bDay = b.getDate();
        int bMonth = b.getMonth();
        int bYear = b.getYear();


        //一年的天数
        int yearDay = 365;
        if (isRunYear(bYear)) {
            yearDay = 366;
        }

        //生日月的天数，计算是否满月
        int mothDay = 31;
        if (bMonth == 2) {
            if (isRunYear(bYear)) {
                mothDay = 29;
            } else {
                mothDay = 28;
            }
        } else if (bMonth == 4 || bMonth == 6 || bMonth == 9 || bMonth == 11) {
            mothDay = 30;
        }


//        int day = (int) ((nTime - birthTime) / 24 * 3600000);

        //满岁：当前年大于出生年，且当前月大于出生月，或当前月等于且出生月当前日大于等于出生日，否则月份为：当前年-出生年-1
        int year = nYear-bYear;
        if(!(year>0&&nMonth>=bMonth&&nDay>=bDay)){
            year--;
        }
//        if (nYear > bYear) {
//            if (nMonth > bMonth || (nMonth == bMonth && nDay >= bDay)) {
//                year = nYear - bYear;
//            } else {
//                year = nYear - bYear - 1;
//            }
//        }
        if (year > 0) {
            if (!gestation) {
                birth += (year + "岁");
            } else {
                return "宝宝出生" + year + "年前";
            }
        }

        //满月：当前月大于出生月；且当前日大于等于出生日，否则月份为：当前月-出生月-1
        int month = 0;
        if (nMonth > bMonth) {
            if (nDay >= bDay) {
                month = nMonth - bMonth;
            } else {
                month = nMonth - bMonth - 1;
            }
        }
        if (nYear > bYear && month < 0) {
            month += 12;
        }
        if (month > 0) {
            if (!gestation) {
                birth += (month + "个月");
            } else {
                return "宝宝出生" + month + "个月前";
            }
        }

        int day = 0;
        if (nDay >= bDay) {
            if (nYear > bYear || (nYear == bYear && nMonth >= bMonth)) {
                day += (nDay - bDay);
                if (!gestation) {
                    day += 1;
                }
            }
        } else {
            if (nMonth > bMonth || nYear > bYear) {
                day += (mothDay - bDay + nDay);
                if (!gestation) {
                    day += 1;
                }
            }
        }

        if (day > 0) {
            if (!gestation) {
                birth += (day + "天");
            } else {
                return "宝宝出生" + day + "天前";
            }
        }

        return birth;
    }

    public static boolean isRunYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
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

    public static final DateFormat FORMATOR_YMD_2 = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    public static void test() {
        List<String> t = new ArrayList<>();
        t.add("19900101");
        t.add("19910115");
        t.add("19920131");
        t.add("19930201");
        t.add("19940215");
        t.add("19950228");
        t.add("19960229");
        t.add("19971101");
        t.add("19981115");
        t.add("19991130");
        t.add("20001201");
        t.add("20011215");
        t.add("20021231");

        List<String> n = new ArrayList<>();
        n.add("19900101");
        n.add("19910115");
        n.add("19920131");
        n.add("19930201");
        n.add("19940215");
        n.add("19950228");
        n.add("19960229");
        n.add("19971101");
        n.add("19981115");
        n.add("19991130");
        n.add("20001201");
        n.add("20011215");
        n.add("20020715");
        n.add("20021215");
        n.add("20021231");

        for (int i = 0; i < t.size(); i++) {
            for (int j = 0; j < n.size(); j++) {
                long t1 = getDateLong(t.get(i), FORMATOR_YMD_2);
                long n1 = getDateLong(n.get(j), FORMATOR_YMD_2);
                String birth = getBirthDay(t1, n1);
                Log.d("test", "出生=" + t.get(i) + "==截止==" + n.get(j) + "=birth=" + birth);
            }
        }
    }
}
