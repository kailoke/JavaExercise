package A3_JDK8_java.time;

import org.junit.Test;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/** DateTimeFormatter类
 * 一、格式化方法：
 *  > 1. 预定义的标准格式。如：IOS_LOCAL_DATE_TIME;ISO_LOCAL_DATE;SIO_LOCAL_TIME
 *  > 2. 本地化相关的格式。如：ofLocalizedDateTime(FormatStyle.LONG)
 *  > 3. 自定义的格式。    如：ofPattern("yyyy-MM-dd hh:mm:ss")
 * 二、方法：
 *  > ofPattern(String pattern) 静态方法，返回指定字符串格式的 DateTimeFormatter
 *  > format(TemporalAccessor t)    格式化一个日期、时间，返回字符串
 *  > parse(CharSequence text)      解析字符串序列为 日期、时间
 */
public class A3_DateTimeFormatter {

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
