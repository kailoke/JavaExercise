package a2_StreamAPI;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/** Stream<> 函数式编程：描述 oldData-->newData 的映射
 * 一、定义：Stream是数据渠道，用于操作数据源(集合、数组等)锁生成的元素序列
 * 二、特性：
 *  > 1.1 Stream 自己不存储元素，因为其本身面向CPU，通过CPU对数据进行`运算`
 *        Collection集合 是存储`数据`的，其本身面向内存
 *  > 1.2 Stream 不改变源对象，计算后返回一个持有结果的新Stream
 *  > 1.3 Stream 中间操作是延迟的，需要等到[产生结果的]方法的时候才执行
 *
 * 二、Stream操作流程
 *  > 2.1 获取数据源的 Stream，数据源(集合、数组等)
 *  > 2.2 中间操作链，一系列对数据源的处理(filter\\sorted\\map\\reduce...)
 *  > 2.3 终止操作方法，立即执行中间链，并产生结果。已经产生结果的Stream实例不能再被使用
 *      > 惰性求值 : 中间操作不会执行，在终止操作方法时一次性全部处理
 *
 * 三、Stream实例化的4种方式
 *  > 3.1 集合Stream    Collection接口默认方法
 *      > default Stream<E> stream() : 返回顺序流
 *      > default Stream<E> parallelStream() : 并行流
 *  > 3.2 数组Stream    Arrays工具类静态方法 : Arrays.stream(T[] array)
 *      > StreamAPI 为基本数据类型的数组 优化了对应的Stream<T>类
 *        int--> IntStream || long--> LongStream || double--> DoubleStream
 *  > 3.3 Stream 类静态方法，获得实参流
 *      --> Stream.of(T...values)，实际上是调用Arrays.stream(t[] values)
 *  > 3.4 Stream 无限流，Stream静态方法
 *      > public static<T> Stream<T> iterate(final T seed,final UnaryOperator<T> f)
 *          > 按 UnaryOperator<T>.apply() ，以seed为初始值无限迭代。泛型方法：参数和返回值类型一致
 *      > 生成：public static<T> Stream<T> generate(Supplier<T> s)
 *          > 按 Supplier<T>.get() 无限生成，泛型方法：参数和返回值类型一致
 */

public class A1_StreamAPIOverview {
    @Test
    // 一、集合数据源
    public void collectionStream(){
        List<Person> persons = Person.getPersons();
        // 1. default Stream<E> Stream() 返回顺序流
        Stream<Person> stream = persons.stream();

        // 2. default Stream<E> parallelStream() 返回并行流
        Stream<Person> parallelStream = persons.parallelStream();
        // TODO 顺序流和并行流的区别？
    }

    @Test
    // 二、数组数据源
    public void arraysStream(){
        // Arrays :: static <T> Stream<T> stream(T[] array) 数组流
        int[] arr = new int[]{1,2,3,4,5,6,7};
        IntStream arrStream1 = Arrays.stream(arr);

        Person[] personArr = new Person[]{new Person("张辽",14),new Person("曹纯",15)};
        Stream<Person> arrStream2 = Arrays.stream(personArr);
    }

    @Test
    // 三、Stream.of() 静态方法获得流 实参数组
    public void streamOf(){
        // Stream.of(T...values)
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);

        // Stream.of(T t)
        int[] arr = {1,2,3};
        Stream<int[]> arrStream = Stream.of(arr);   // 自动类型推导，将基本数据类型作为一个对象

        // Stream.of(T[] values)
        Integer[] intArr = {1,2,3};
        Stream<Integer> intArrStream = Stream.of(intArr);
    }

    @Test
    // 四、Stream无限流
    public void testInfiniteStream(){
        // 迭代 public static <T> Stream<T> iterate(finale T seed,final UnaryOperator<T> f)
        // 迭代起点 seed, 按迭代函数 UnaryOperator<T>.apply(T,T) 两个参数运算，返回值类型和参数一致
        Stream.iterate(0, integer -> integer + 2).limit(10).forEach( i -> System.out.print(i + "\t"));

        System.out.println();

        // 生成 public static <T> Stream<T> generate(Supplier<T> s)
        // 按 Supplier.get() 无限生成数据
        Stream.generate(Math::random).limit(10).forEach( i -> System.out.print(String.format("%.2f", i) + "\t"));
    }
}
