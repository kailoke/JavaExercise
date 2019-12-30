package com.F.datetimeTest;

import java.util.Date;

/*  timestamp
毫秒数通常被称为 时间戳，唯一
 */
/*
java.util.Date类
    |--- java.sql.Date

 */
public class TimeAndDate {
    public static void main(String[] args) {
        // 1.System类中的 currentTimeMillis()
        long time = System.currentTimeMillis();
        System.out.println(time);

        // 2.Util构造器 Date() /  Date(long millis) 指定毫秒数的对象
        System.out.println("java.util.Date类:");
        Date date1 = new Date();
        System.out.println(date1);      // toString --> 星期 %m %D %H:%M%s 时区 %Y
        System.out.println(date1.getTime());    // 获取Date对象的时间戳

        Date date2 = new Date(1574335898378L);
        System.out.println(date2);

        // 3.java.sql.date
        System.out.println("java.util.date:");
        java.sql.Date date3 = new java.sql.Date(1574336127370L);
        System.out.println(date3);  // toString() --> %Y-%M-%D

        // 父类引用指向子类对象才能强转为子类引用
        // java.util.Date to java.sql.Date
        java.sql.Date date4 = new java.sql.Date(date1.getTime());
        System.out.println(date4);

    }
}
