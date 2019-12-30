package a3_ConstructorReference;
import a4_getDetail.Person;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/** 构造器引用
 * 一、原理同MethodReference，参数+返回值（一致）
 * 二、格式:    类名 :: new
 * 三、因为BiFunction只有二元参数，实际使用应该会受到此限制(若使用内置函数式接口)
 */

public class ConstructorReference {
    @Test
    // 默认构造器
    public void testNoArg(){
        Supplier<Person> supplier = Person::new;
        System.out.println(supplier.get());
    }

    @Test
    // 有参构造器
    public void testArg(){
        // 同样有访问权限
        Function<String,Person> function = Person::new;
        System.out.println(function.apply("刘备"));
    }

    @Test
    public void testArgs(){
        BiFunction<String,Integer,Person> biFunction = Person::new;
        System.out.println(biFunction.apply("张飞",15));
    }
}
