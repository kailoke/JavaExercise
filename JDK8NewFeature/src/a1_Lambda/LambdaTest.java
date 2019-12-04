package a1_Lambda;

import org.junit.Test;

import java.util.function.*;

/** Lambda表达式   匿名实现类的简洁写法- -?
 * 一、格式：(抽象方法的形参列表) -> {重写抽象方法的方法体;};
 *                              ↑ lambda操作符 箭头操作符 (C/C++取值运算符)
 * 二、Java中lambda表达式的本质是作为函数式接口的实例，并重写虚方法
 * 三、语法总结
 *  > 形参列表:lambda形参列表参数类型可以省略(类型推断)；lambda形参列表只有一个参数，则可省略()
 *  > 方法体：lambda体应使用{}包裹多条语句；只有一条执行语句则省略 return + {}
 * 四、Java内置 java.util.function 包内函数式接口
 *  > Consumer  BiConsumer
 *  > Supplier
 *  > Function  BiFunction UnaryFunction BinaryFunction
 *  > Predicate BiPredicate
 */
public class LambdaTest {
    @Test
    // Consumer 消费型接口   BiConsumer(T,U)
    public void testConsumer(){
        Consumer<Integer> consumer1 = i -> System.out.println(i);
        consumer1.accept(10);

        Consumer<String> consumer2 = System.out :: println;
        consumer2.accept("Hello World");
    }

    @Test
    //Supplier  供给型接口
    public void testSupplier(){
        Supplier<Double> supplier = () -> Math.PI;
        System.out.println(supplier.get());
    }

    @Test
    // Function 函数型接口       R BiFunction<T,U,R> (T t,U u)
    // T UnaryOperator<T>(T t)  T BinaryOperator<T> (T t1,T t2)
    public void testFunction(){
        Function<Double,String> function = d -> String.valueOf(Math.round(d));
        System.out.println(function.apply(12.3));
        System.out.println(function.apply(12.5));
    }

    @Test
    // Predicate 断定型    BiPredicate(T,U)
    public void testPredicate(){
        Predicate<String> predicate = String::isEmpty;
        System.out.println(predicate.test(""));
    }

    @Test
    public void testWithLambda(){
        buyWater(500, i -> System.out.println("买了一瓶龙泉水：" + i));
    }

    private void buyWater(int water, Consumer<Integer> con){
        con.accept(water);
    }
}
