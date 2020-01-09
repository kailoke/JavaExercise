package A6_java.math;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * > 超过int\long表示范围的整数：BigInteger 可以表示不可变的任意精度的整数
 *      > BigInteger提供所有java基本整数操作符的对应物，提供java.lang.Math 的所有相关方法
 *        add(+)\subtract(-)\multiply(*)\divide(/)\remainder(%)
 *      > BigInteger还提供以下运算：模算术、GCD算术、质数测试、素数生成、位操作···
 * > 商业计算中要求数字精度高：BigDecimal支持不可变的、任意精度的有符号十进制"定点数"
 *      > add(+)\subtract(-)\multiply(*)\divide(/)\remainder(%)
 *      > BigDecimal的(计算方法)需要指定 精度(位数 或 四舍五入规则)[除不尽的情况]
 */


public class BigIntegerBigDecimal {
    public static void main(String[] args) {
        BigInteger bigI = new BigInteger("12433241123");
        BigDecimal bd = new BigDecimal("12435.351");
        BigDecimal bd2 = new BigDecimal("11");
        System.out.println("bigI : " + bigI);
        // divide 需要指定 rounding mode
//        System.out.println(bd.divide(bd2));
        System.out.println(bd.divide(bd2, BigDecimal.ROUND_HALF_UP));
        System.out.println(bd.divide(bd2, 15, BigDecimal.ROUND_HALF_UP));
    }
}
