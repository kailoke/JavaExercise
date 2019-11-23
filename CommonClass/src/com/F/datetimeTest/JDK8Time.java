package com.F.datetimeTest;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class JDK8Time {

    // 一、LocalDate,LocalTime,LocalDateTime
    @Test
    public void testLocal3(){

        // 1 类.now() 获取当前 日期/时间/日期时间    私有化构造器，由静态方法产生对象
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println("类.now():" + date);
        System.out.println("类.now():" + time);
        System.out.println("类.now():" + dateTime);

        // 2 类.of() 指定时间，没有偏移量  私有化构造器，由静态方法产生对象
        LocalDateTime dateTime1 = LocalDateTime.of(2018,8,18,12,30);
        System.out.println("类.of():" + dateTime1);

        // 3 getXxx()   获取对象属性
        System.out.println(dateTime.getDayOfWeek());    // 星期几
        System.out.println(dateTime.getDayOfMonth());
        System.out.println(dateTime.getMonth());        // 几月
        System.out.println(dateTime.getMonthValue());   // 第几月
        System.out.println(dateTime.getHour());

        // 4 with() 设置对象属性 <--- 体现不可变性，只能产生新对象
        LocalDate date1 = date.withDayOfMonth(10).withMonth(5).withYear(2030);
        System.out.println("对象.with(): " + date1);

        // 5 plus()/minus 修改对象属性
        LocalDateTime dateTime2 = dateTime.plusDays(20);
        System.out.println(dateTime2);
    }

    // 二、Instant
    @Test
    public void testInstant(){
        // 1.UTC 日期时间 Instant.now()
        Instant instant = Instant.now();
        System.out.println(instant);

        // 东半球我们UST 需要+8
        // 2.设置偏移日期时间 OffsetDateTime对象   instant.atOffset
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        // 3.获取时间戳
        long millis = instant.toEpochMilli();
        System.out.println(millis);

        // 4.由时间戳创建Instant实例
        Instant instant1 = Instant.ofEpochMilli(millis);
        System.out.println(instant1);
    }

    // 三、DateTimeFormatter
    @Test
    public void testDateTimeFormatter(){
        // 方式一：预定义的标准格式。 ISO_LOCAL_DATE_TIME;IOS_LOCAL_DATE;IOS_LOCAL_TIME
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDatetime = LocalDateTime.now(); //2019-11-22T11:49:52.669228
        // 格式化
        String localDateString = dateTimeFormatter.format(localDatetime);
        System.out.println(localDateString);
        // 解析
        TemporalAccessor parse = dateTimeFormatter.parse("2019-11-22T11:47:27.8598213");
        System.out.println(parse);

        // 方式二：预定义的本地化相关的格式
        // pattern 预定义为 FormatStyle枚举类中的对象
        // ofLocalizedDateTime(FormatStyle.SHORT)

        // 方式三： 自定义的格式 ofPattern("yyyy-MM-dd hh:mm:ss")
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        // 格式化
        String definedStr = dtFormatter.format(localDatetime);
        System.out.println("自定义的DateTimeFormatter格式化：" + definedStr);
        // 解析


    }
}
