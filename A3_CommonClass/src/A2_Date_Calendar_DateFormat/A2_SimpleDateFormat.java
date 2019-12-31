package A2_Date_Calendar_DateFormat;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** SimpleDateFormat 不与语言环境有关的方式
 * 一、作用：格式化和解析Date
 * 二、格式化 format
 *  > SimpleDateFormat():默认的模式
 *  > public SimpleDateFormat(String pattern):根据pattern格式创建一个格式化对象
 *      > 该对象调用 public String format(Date date) : 格式化时间对象 Date
 * 三、解析 parse
 *  > 格式化对象调用 public Date parse(String source) : 将source字符串文本解析为 时间对象 Date
 */
public class A2_SimpleDateFormat {

    @Test
    // 格式化 与 解析
    public void testSimpleDateFormat() {
        // 默认格式
        SimpleDateFormat sdfDefault = new SimpleDateFormat();
        // 自定义格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        // 1.格式化为 String
        Date date = new Date();
        String strFormat = sdfDefault.format(date);
        System.out.println("sdf default format ：" + strFormat);
        System.out.println("sdf custom format : " + sdf.format(date));

        // 2.解析为 Date对象
        String str1 = "2019/11/21 下午8:36";
        try {
            Date date1 = sdfDefault.parse(str1);
            System.out.println("sdf default parse : " + date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String str2 = "2019-11-21 21:32";
        try {
            Date date2 = sdf.parse(str2);
            System.out.println("sdf custom parse : " + date2.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
