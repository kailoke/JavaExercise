package A3_JDK8_java.time;

import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/** LocalDate   LocalTime   LocalDateTime
 *  一、本质：
 *      > `它们的实例是不可变的对象`，修改方法均返回新对象
 *      > 基于ISO-8601日历系统(公历)的日期、时间、日期和时间，面向人类的时间模型。
 *      > 它们提供了简单的本地日期或时间，不包含 当前的时间信息 && 时区相关信息
 *  二、方法：
 *  > 1. now() / * now(ZoneId zone) 静态方法，根据当前当前时间创建对象/指定时区的对象
 *  > 2. of(Year,Month,Day...)      静态方法，根据指定日期/时间创建对象
 *  > 3. getDayOfMonth()/getDayOfYear() 获得月份天数(1-31) / 获得年份天数(1-366)
 *  > 4. getDayOfWeek() 获得星期，返回 DayOfWeek 枚举值
 *  > 5. getMonth()     获得月份，返回 Month 枚举值
 *  > 6. getMonthValue() / getYear()        获得月份(1-12) / 获得年份
 *  > 7. getHour()/getMinute()/getSecond()  当前对应的 小时、分钟、秒
 *  > 8. withDayOfMonth()/withDayOfYear()/withMonth()/withYear()
 *       修改当前对象，并返回新对象
 *  > 9. plusDays()/plusWeeks()/plusMonths()/plusYears()/plusHours()
 *       向当前对象添加，并返回新对象
 *  >10. minusDays()/minusWeeks()/minusMonths()/minusYears()/minusHours()
 *       从当前对象减去，并返回新对象
 */
public class A1_LocalDateTime {
    @Test
    public void createDateTime() {
        // 1 类.now() 获取当前 日期/时间/日期时间    私有化构造器，由静态方法产生对象
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime1 = LocalDateTime.now();

        System.out.println("LocalDate.now():" + date);
        System.out.println("LocalTime.now():" + time);
        System.out.println("LocalDateTime.now():" + dateTime1);

        // 2 类.of() 指定时间，没有偏移量  私有化构造器，由静态方法产生对象
        LocalDateTime dateTime2 = LocalDateTime.of(2018,8,18,12,30);
        System.out.println("LocalDateTime.of():" + dateTime2);

        // 3 getXxx()   获取对象属性
        System.out.println("LocalDateTime.getDayOfWee() : " + dateTime1.getDayOfWeek());    // 星期几
        System.out.println("LocalDateTime.getDayOfMonth() : " + dateTime1.getDayOfMonth());
        System.out.println("LocalDateTime.getMonth() : " + dateTime1.getMonth());        // 几月
        System.out.println("LocalDateTime.getMonthValue() : " + dateTime1.getMonthValue());   // 第几月
        System.out.println("LocalDateTime.getHour() : " + dateTime1.getHour());

        // 4 with() 设置对象属性 <--- 体现不可变性，只能产生新对象
        LocalDate date1 = date.withDayOfMonth(10).withMonth(5).withYear(2030);
        System.out.println("LocalDate.with(): " + date1);

        // 5 plus()/minus 修改对象属性
        LocalDateTime dateTime3 = dateTime1.plusDays(20);
        System.out.println("LocalDateTime.plusDays() : " + dateTime3);
    }
}
