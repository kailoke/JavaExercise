package a1_Lambda;

import org.junit.Test;

import java.util.function.*;

/** 函数式接口 : FunctionalInterfaces
 * 一、定义：只有一个抽象方法的接口，称为 函数式接口
 *    // @FunctionalInterface 检查它是否是函数式接口
 * 二、作用：通过Lambda表达式来创建该接口的匿名对象 <- 也可以普通方法实现匿名类的对象
 * 三、java.util.function 包下定义了JDK8 函数式接口
 *  > Consumer<T> || BiConsumer<T,U>
 *      > void accept(T) || void accept(T,U)
 *  > Supplier<T>
 *      > T get()
 *  > Function<T,R> || BiFunction<T,U,R> || UnaryOperator<T> || BinaryOperator<T>
 *      > R apply(T)||    R apply(T,U)   ||  T apply(T)      || T apply(T,T)
 *  > Predicate<T>        || BiPredicate<T,U>
 *      > boolean test(T) || boolean test(T,U)
 */

public class A2_FunctionalInterface {
    @Test
    // 一、消费型 Consumer<T> || BiConsumer<T,U>
    public void testConsumer(){
        // 1. void accept(T t)
        Consumer<Integer> consumer1 = i -> System.out.println(i);
        consumer1.accept(10);
        // 2.方法引用
        Consumer<String> consumer2 = System.out :: println;
        consumer2.accept("Hello World");
    }

    @Test
    // 二、供给型 supplier<T>
    public void testSupplier(){
        // T get()
        Supplier<Double> supplier = () -> Math.PI;
        System.out.println(supplier.get());
    }

    @Test
    // 三、函数型 Function    R BiFunction<T,U,R> (T t,U u)
    // T UnaryOperator<T>(T t)  T BinaryOperator<T> (T t1,T t2)
    public void testFunction(){
        // R apply(T t)
        Function<Double,String> function = d -> String.valueOf(Math.round(d));
        String apply1 = function.apply(12.3);
        String apply2 = function.apply(12.5);
        System.out.println(apply1);
        System.out.println(apply2);
    }

    @Test
    // 四、断定型 Predicate     BiPredicate(T,U)
    public void testPredicate(){
        // boolean test(T t)
        Predicate<String> predicate = String::isEmpty;
        System.out.println(predicate.test(""));
        System.out.println(predicate.test("1"));
    }

    @Test
    public void testWithLambda(){
        // 实参实现 Lambda表达式
        buyWater(500, i -> System.out.println("买了一瓶龙泉水：" + i));
    }
    // 函数式接口作为参数 传递
    private void buyWater(int water, Consumer<Integer> con){
        // 接口执行抽象方法
        con.accept(water);
    }
}
