package A1_StringRelevant;

import org.junit.Test;

/**
 * String : `字面量`，对字符串内容进行任意修改的方法()都是返回新的String实例
 *  > 1.JAVA中所有的字面值("abc")均是String的实例
 *  > 2.public final class String implements java.io.serializable,Comparable<String>,CharSequence
 *  > 3.内部是 final char value[]，不可变的字符序列
 *  > 4.字符串是常量，创建之后均不可改变
 *      > 使用双引号括起来存放在MethodArea，目的为了共享常量
 *      > new String(“str”) 从MethodArea拷贝str字面量到 堆中的String 实例
 *      > new String(char[]) 从堆中拷贝字符数组
 *
 * 二、字符串的拼接
 *  > 常量 + 常量 ： 依然在常量池中，且常量池中不会相同的常量
 *  > 拼接中只要有一个变量(声明方式)，结果就在堆中
 *  > String.intern() 返回String对象在常量池中的引用地址；若无->则在常量池创建字面值返回其地址
 */
public class A1_String {
    @Test
    // 字符串创建的本质
    public void creatString() {
        String str = "hello";

        // 本质上是 this.value = new char[0];
        String s1 = new String();

        /*
        String s2 = new String(String original);
        // 本质上是 this.value = original.value;
        // original 来自于 MethodArea常量池

        String s3 = new String(char[] a);
        String s4 = new String(char[] a,int startIndex,int count);
        // 本质上是 this.value = Arrays.copyOf(value, value.length);
         */
    }

    @Test
    // 字符串存储位置 : MethodArea || Heap
    public void test() {
        String str1 = "hel";                // 常量池
        String str2 = "lo";

        String str3 = str1 + str2;          // 变量运算-->内存：需要在内存中进行
        String str4 = "hel" + "lo";         // 常量池
        String str5 = "hello";              // 常量池

        System.out.println("two args +ed == full const:" + (str3 == str4));   // false
        System.out.println("two const +ed == full const:" + (str4 == str5));  // true

        String str6 = new String(str4);     // 构造器-->拷贝字面量至Heap对象
        System.out.println("Constructor == full const: " + (str6 == str5));   // false
        System.out.println("Constructor == two args +ed: " + (str6 == str3)); // false

        String str7 = str3.intern();    // 返回字符串的常量池引用
        String str8 = str5.intern();    // 若常量池中无此字符串则在常量池中创建字符串，并返回字符串的常量池引用
        System.out.println("const == Heap.intern():" + (str5 == str7));  // true
        System.out.println("const == const.intern():" + (str5 == str8)); // true
    }

    @Test
    public void StringExercise() {
        String s1 = "javaEE";
        String s2 = "javaEE";
        String s3 = new String("javaEE");
        String s4 = new String("javaEE");
        System.out.println(s1 == s2);   // true
        System.out.println(s1 == s3);   // false
        System.out.println(s1 == s4);   // false
        System.out.println(s3 == s4);   // false
    }
    @Test
    public void StringPractice() {
        T_Person p1 = new T_Person();
        p1.name = "atguigu";
        T_Person p2 = new T_Person();
        p2.name = "atguigu";
        System.out.println(p1.name .equals(p2.name));  // true
        System.out.println(p1.name == p2.name);         // true
        System.out.println(p1.name == "atguigu");       // true

        String s1 = new String("bcde");
        String s2 = new String("bcde");
        System.out.println(s1==s2);                     // false
    }
}
