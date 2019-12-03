package a4_ArrayReference;

import org.junit.Test;
import java.util.Arrays;
import java.util.function.Function;

/** 数组引用    原理同MethodReference，省略 参数+返回值（一致）
 * new Xxx[];
 * 将Xxx[] 当做构造器使用(实际上是初始化initializer？)
 */
public class ArrayReference {
    @Test
    public void testAR(){
        Function<Integer,String[]> function1 = length -> new String[length];
        String[] apply1 = function1.apply(5);
        System.out.println(Arrays.toString(apply1));

        System.out.println("***********************");
        Function<Integer,String[]> function2 = String[]::new;
        String[] apply2 = function2.apply(5);
        System.out.println(Arrays.toString(apply2));
    }
}
