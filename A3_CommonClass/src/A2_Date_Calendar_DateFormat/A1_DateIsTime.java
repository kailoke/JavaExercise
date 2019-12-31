package A2_Date_Calendar_DateFormat;

import java.util.Date;

/** TimeStamp : 毫秒数通常被称为 时间戳，唯一
 * 一、java.lang.System :: public static long currentTimeMillis()  now()-1970年1月1日0时0分0秒毫秒时间差
 * 二、java.util.Date : Date对象
 *  > 构造器: Date():无参构造器获取本地时间   Date(long date)指定毫秒数的Date对象
 *  > 方法： getTime():获得毫秒数   toString():String： dow mon dd hh:mm:ss zzz yyyy
 *  > Date类的API与语言环境有关，不易于国际化，大部分已被废弃
 *
 *
 */


public class A1_DateIsTime {
    public static void main(String[] args) {
        // 1.System类中的 currentTimeMillis()
        long systemTime = System.currentTimeMillis();
        System.out.println("java.lang.System.currentTimeMillis() : " + systemTime);

        // 2.Util构造器 Date() /  Date(long millis) 指定毫秒数的对象
        System.out.println("\njava.util.Date类:");
        Date date1 = new Date();
        Date date2 = new Date(1574335898000L);
        System.out.println("Date obj :" + date1);          // toString --> 星期 %m %D %H:%M%s 时区 %Y
        System.out.println("new Date(long) : " + date2);
        System.out.println("Date.getTime() : " + date2.getTime());// 获取Date对象的时间戳

        // 3.java.sql.date
        System.out.println("\njava.sql.date:");
        java.sql.Date date3 = new java.sql.Date(1574336127370L);
        System.out.println(date3);  // toString() --> %Y-%M-%D

        // 父类引用指向子类对象才能强转为子类引用
        // java.util.Date to java.sql.Date
        java.sql.Date date4 = new java.sql.Date(date1.getTime());
        System.out.println(date4);

    }
}
