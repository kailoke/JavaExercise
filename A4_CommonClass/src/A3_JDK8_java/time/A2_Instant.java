package A3_JDK8_java.time;

import org.junit.Test;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/** Instant 时间线上的一个瞬时点，时间戳，不需要任何上下文信息
 * 一、本质：
 *  > 面向机器的连续时间点模型，至1970年开始，以秒为单位。
 *  > 默认UTC时区(东半球)；中国处于西半球，需要在此基础上 偏移ZoneOffset.ofHours(8)
 * 二、方法：
 *  > now()     静态方法，返回默认UTC时区的Instant类的对象
 *  > ofEpochMilli(Long epochMilli) 静态方法，返回在 1970-01-01 00:00:00
 *                                           基础上加上指定毫秒数之后的Instant类的对象
 *  > atOffset(ZoneOffset offset) 结合即时的偏移来创建一个 OffsetDateTime
 *  > toEpochMilli()    返回毫秒数的时间戳
 */
public class A2_Instant {
    @Test
    public void creatInstant() {
        // 1.UTC 日期时间 Instant.now()
        Instant instant = Instant.now();
        System.out.println(instant);

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
}
