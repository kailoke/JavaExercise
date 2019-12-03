package a3_ConstructorReference;
import a4_getDetail.Person;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/** 构造器引用
 * 原理同MethodReference，省略 参数+返回值（一致）
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
