package a1_Lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

/** MethodReference 方法引用
 * 一、本质：替换Lambda表达式，当Lambda体已经有实现的方法，则可以使用方法引用
 * 二、要求：被引用方法的 “形参列表+返回值类型” 必须与函数式接口`抽象方法`的相同
 * 三、格式：方法引用操作符 " :: " 将类(或对象)与方法名分割
 *  > 对象 :: 实例方法 || 类 :: 实例方法 || 类 :: 静态方法
 * 四、方法引用实例调用对应抽象方法
 */

public class A3_MethodReference {

    @Test
    public void testMR(){
        // 1.对象 :: 实例方法
        Consumer<String> consumer = System.out :: println;
        consumer.accept("object.invoke:" + "Hello World");

        // 2.类 :: 实例方法 (实际是 对象 :: 实例方法，this == 第一个参数，方法实参 == 第二个参数)
        BinaryOperator<Integer> binaryOperator = Integer::compareTo;
        Integer apply = binaryOperator.apply(1, 2);
        System.out.println("class.invoke:" + apply);

        Comparator<String> comparator = String::compareToIgnoreCase;
        System.out.println("Method Reference:" + comparator.compare("a","A"));

        // 3.类 :: 静态方法
    }
}
