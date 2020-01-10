package a1_Lambda;

import org.junit.Test;
import java.util.Arrays;
import java.util.function.Function;

/** 数组引用
 * 一、原理同MethodReference，“形参列表+返回值类型” 必须一致
 * 二、格式     type[]::new ( 将type[] 当做类构造器使用)
 * 三、数组引用实例调用对应抽象方法，获得数组实例
 *
 *  *同构造器引用，感觉实用价值不大，不如直接 new...；重复的获取数组实例||重复获取对象实例?也不如new...
 */
public class A5_ArrayReference {
    @Test
    public void arrayReference(){
        // Lambda表达式
        Function<Integer,String[]> function1 = length -> new String[length];
        String[] apply1 = function1.apply(5);
        System.out.println("Lambda: " + Arrays.toString(apply1));

        // 数组引用
        Function<Integer,String[]> function2 = String[]::new;
        String[] apply2 = function2.apply(5);
        System.out.println("数组引用：" + Arrays.toString(apply2));
    }
}
