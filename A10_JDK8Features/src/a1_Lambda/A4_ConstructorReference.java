package a1_Lambda;

import org.junit.Test;

import a4_ClassStructures.Person;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/** 构造器引用
 * 一、原理同MethodReference，“形参列表+返回值类型” 必须一致
 * 二、格式     类名 :: new，抽象方法的返回值类型即返回的 实例类型
 * 三、构造器引用实例调用对应抽象方法，获得类型实例
 *
 * 四、使用上因函数式接口形参数量有限，能使用的构造器形参长度受到限制
 *  > Supplier<T>:空参数    Function<T,R>单参数    BiFunction<T,U,R>双参数
 */

public class A4_ConstructorReference {
    @Test
    // 一、空参构造器引用
    public void testNoArg(){
        Supplier<Person> supplier = Person::new;
        System.out.println(supplier.get());
    }

    @Test
    // 二、单参 || 双不同参 构造器引用
    // UnaryOperator && BinaryOperator 可能能作为链表类使用
    public void testArg(){
        // public Person(String name)
        Function<String,Person> function = Person::new;
        System.out.println(function.apply("刘备"));

        // public Person(String name,int age)
        BiFunction<String,Integer,Person> biFunction = Person::new;
        System.out.println(biFunction.apply("张飞",15));
    }
}
