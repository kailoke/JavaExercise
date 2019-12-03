package a2_MethodReference;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

/** MethodReference
 * 1.方法引用，本质上是Lambda表达式
 * 2.当lambda重写抽象方法的方法体已经有实现方法，则可以使用方法引用
 * 3.MethodReference方法 必须 与函数式接口抽象方法的 “形参列表+返回值类型” 相同
 */
public class MethodReferenceTest {

    @Test
    public void testMR(){
        // 1.对象 :: 实例方法
        Consumer<String> consumer = System.out :: println;
        consumer.accept("object.invoke:" + "Hello World");

        // 2.类 :: 静态方法
        BinaryOperator<Integer> binaryOperator = Integer::compareTo;
        Integer apply = binaryOperator.apply(1, 2);
        System.out.println("class.invoke:" + apply);

        // 3.类 :: 实例方法 ( 实际是 对象 :: 实例方法，默认this = 参数1;用operator Override理解)
        Comparator<String> comparator1 = (s1,s2) -> s1.compareTo(s2);
        int compare = comparator1.compare("a", "a");
        System.out.println("Lambda:" + compare);

        Comparator<String> comparator2 = String::compareToIgnoreCase;
        System.out.println("Method Reference:" + comparator2.compare("a","A"));
    }
}
