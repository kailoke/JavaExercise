package com.F.datetimeTest;

import com.F.annotation.MyAnnotation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
    SimpleDateFormat JDK 8.0前
    对日期Date类的格式化和解析
1.格式化 Date--->String
2.解析    String--->Date
    Calendar 抽象基类
        > getInstance 获得其子类 GregorianCalendar()的对象
    一月是<0>；周日是<1>
 */
public class DateFormatCalendar {
    public static void main(String[] args) {
//        testSimpleDateFormat();
//        exercise();
        CalendarTest();
    }

    // Calendar
    public static void CalendarTest(){
        // 1.Calendar实例化  Calendar.getInstance() GregorianCalendar()
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getClass());

        // 2.常用方法
        // get()
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        System.out.println("day of year_get():" + calendar.get(Calendar.DAY_OF_YEAR));

        // set()    同步修改GregorianCalendar类对象内所有属性
        calendar.set(Calendar.DAY_OF_YEAR,100);
        System.out.println("day of year_set(year):" + calendar.get(Calendar.DAY_OF_YEAR));
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("day of month_set(year):" + days);

        // add()
        calendar.add(Calendar.DAY_OF_MONTH,-5);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("day of month_add(day):" + days);
        System.out.println("day of year_add(day):" + calendar.get(Calendar.DAY_OF_YEAR));

        // getTime() Calendar ---> Date
        Date date = calendar.getTime();
        System.out.println("date: " + date);

        // setTime() Date ---> Calendar
        date = new Date();
        calendar.setTime(date);
        System.out.println("day of set(Date):" + calendar.get(Calendar.DAY_OF_YEAR));

    }

    // SimpleDateFormat 带格式的构造器
    public static void exercise(){
        String str = "2020-09-08";
        SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.sql.Date date = new java.sql.Date(dateF.parse(str).getTime());
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    // SimpleDateFormat 构造器
    public static void testSimpleDateFormat() {
            // SimpleDateFormat 实例化:使用pattern构造器，按pattern格式化或解析
            SimpleDateFormat sdf = new SimpleDateFormat();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date();
            System.out.println("date getTime():" + date.getTime());

            String str2 = "2019-11-21 21:32";
            Date date2 = null;
            try {
                date2 = sdf1.parse(str2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assert date2 != null;
            System.out.println(date2.getTime());


            // 1.格式化为 String
            String str = sdf.format(date);
            System.out.println(str);

            System.out.println(sdf1.format(date));

            // 2.解析为对象
            String str1 = "2019/11/21 下午8:36";
            try {
                Date date1 = sdf.parse(str1);
                System.out.println(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
    }

}
