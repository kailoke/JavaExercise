package A2_Date_Calendar_DateFormat;

import java.util.Calendar;
import java.util.Date;

/** Abstract Calendar(抽象基类) extends TimeZone
 * 一、作用： 完成日期字段之间的相互操作功能
 *           可以和Date相互转换：Date getTime() || setTime(Date)
 *           JDK1.1，引入后基本替代了Date的大多数方法
 * 二、获取Calendar实例：  依靠其子类GregorianCalendar提供API
 *  > 1. Calendar.getInstance() 获得其子类 GregorianCalendar()的对象
 *  > 2. 调用其子类 GregorianCalendar()构造器
 *  > 3. Calendar实例是系统时间的抽象表示，通过 get(int field)来取得日期字段
 *          YEAR、MONTH、DAY_OF_WEEK、HOUR_OF_DAY、MINUTE、SECOND
 * 三、常用方法
 *  > public void get(int field)    : 获得指定的日期字段
 *  > public void set(int field,int value)  设定日期字段值
 *  > public void add(int field,int amount) 增加日期字段值
 *  > public final Date getTime()   : 获得Date对象
 *  > public final void setTime(Date date)  : 右Date对象修改内部日期
 *
 ** 获取月份：一月 == 0,十二月 == 11
 ** 获取星期：周日 == 1,周六 == 7
 */


public class A3_Calendar {
    public static void main(String[] args) {
        // Calendar实例化  Calendar.getInstance() || GregorianCalendar()
        Calendar calendar = Calendar.getInstance();
        System.out.println("Calendar.getInstance() : " + calendar.getClass());

        // 常用方法
        // 1.getTime() Calendar ---> Date
        Date date = calendar.getTime();
        System.out.println("Date of Calendar.getTime(): " + date);

        // 2.setTime() Date ---> Calendar
        date = new Date();
        calendar.setTime(date);
        System.out.println("day_of_year of setTime(Date):" + calendar.get(Calendar.DAY_OF_YEAR));

        // 3.get(int field)
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        System.out.println("day of year_get():" + calendar.get(Calendar.DAY_OF_YEAR));

        // 4.set()    同步修改GregorianCalendar类对象内所有属性
        calendar.set(Calendar.DAY_OF_YEAR,100);
        System.out.println("day of year_set(year):" + calendar.get(Calendar.DAY_OF_YEAR));
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("day of month_set(year):" + days);

        // 5.add()
        calendar.add(Calendar.DAY_OF_MONTH,-5);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("day of month_add(day):" + days);
        System.out.println("day of year_add(day):" + calendar.get(Calendar.DAY_OF_YEAR));
    }
}
