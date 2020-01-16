package A6_java.math;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 小数练习
 */
public class DecimalExercise {
    @Test
    // 一、保留两位小数
    public void retention2() {
        double d = 12.346;

        // 1. 字符串格式化:String.format (自动四舍五入)
        String str1 = String.format("%.1f",d);
        String str2 = String.format("%.2f",d);
        System.out.println(Double.parseDouble(str1));
        System.out.println(Double.parseDouble(str2));

        // 2. 针对包装类的字符串格式 (自动四舍五入)
        String str3 = new DecimalFormat("#.0").format(d);
        String str4 = new DecimalFormat("#.00").format(d);
        System.out.println(str3);
        System.out.println(str4);

        // 3. 定点数的方法
        BigDecimal bigDecimal = new BigDecimal(d);
        BigDecimal bigDecimal1 = bigDecimal.setScale(1,BigDecimal.ROUND_HALF_UP);
        BigDecimal bigDecimal2 = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal1);
        System.out.println(bigDecimal2);
    }

    @Test
    // 获取小数点前两位
    public void test(){
        double d = 123.45;
        int i = (int)d % 100;
        System.out.println(i);
    }
}
