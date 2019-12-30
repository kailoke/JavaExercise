package A1_ClassStructures;

/** == 和 equals() 区别
 *  一、== 操作符
 *      > 基础数据类型：比较赋值
 *      > 引用数据类型：比较引用地址值
 *  二、equals() : 从Object根父类继承的方法
 *      > 默认使用 == 判断两个对象的地址是否相等
 *      > 部分java核心类重写此方法判断引用指向的对象内某些属性是否相同
 *      > 使用上也一般重写自定义类的equals()，比较对象内部某些属性
 */


public class A5_EqualsMethodVsEqualSigns {
    public static void main(String[] args) {
        String str1 = "hel";                // 常量池
        String str2 = "lo";

        String str3 = str1 + str2;          // 变量运算-->内存：需要在内存中进行
        String str4 = "hel" + "lo";         // 常量池
        String str5 = "hello";              // 常量池

        System.out.println("Field == String:" + (str3 == str4));
        System.out.println("string+ == String:" + (str4 == str5));

        String str6 = new String(str4);     // 构造器-->内存
        System.out.println("Constructor == String:" + (str6 == str5));
        System.out.println("Constructor == Field:" + (str6 == str3));

        String str7 = str3.intern();    // 返回字符串的常量池引用
        String str8 = str5.intern();    // 若常量池中无此字符串则在常量池中创建字符串，并返回字符串的常量池引用
        System.out.println("Field.intern():" + (str5 == str7));
        System.out.println("String.intern():" + (str5 == str8));
    }
}
