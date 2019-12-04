package a10_StreamAPI;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/** Stream 对数据的运算
 * 一、特性：
 *  > 1.1 Stream 不会存储元素，因为其本身是对数据进行运算
 *  > 1.2 Stream 不改变源对象，返回一个持有结果的新Stream
 *  > 1.3 Stream 中间操作是延迟的，需要等到结果方法的时候才执行
 * 二、执行流程
 *  > 2.1 Stream 的实例化
 *  > 2.2 Stream 中间操作链，一系列对数据源的处理(filter\\sorted\\map\\reduce...)
 *  > 2.3 Stream 执行终止操作->执行中间链。产生的结果(Stream实例)不能再被使用
 * 三、实例化：4种方式
 *  > 3.1 集合    Collection两个默认方法
 *  > 3.2 数组    Arrays.stream(array) <-- Arrays类静态方法
 *      > StreamAPI 为基本数据类型的数组 优化了特定类
 *      > int--> IntStream  ||  long-->LongStream   ||  double-->DoubleStream
 *  > 3.3 Stream类静态方法-->Stream.of()
 *        ↑ 实际上是调用Arrays.stream(Values)
 *  > 3.4 Stream类静态方法--> Stream.iterate(seed,UnaryOperator) || Stream.generate(Supplier s)
 */
public class StreamAPITest {

    @Test
    // 1.集合实例化
    public void testCollection(){
        List<Person> persons = Person.getPersons();

        /*  1.1 集合默认方法
         Collection:: default Stream<E> Stream() 返回顺序流
         */
        Stream<Person> stream = persons.stream();

        /*  1.2 集合默认方法
        Collection:: default Stream<E> parallelStream() 返回并行流
         */
        Stream<Person> parallelStream = persons.parallelStream();
    }

    @Test
    // 数组实例化
    public void testArrays(){
        // Arrays:: static <T> Stream<T> stream(T[] array)   返回流
        int[] arr = new int[]{1,2,3,4,5,6,7};
        IntStream stream = Arrays.stream(arr);


        Person[] personArr = new Person[]{new Person("张辽",14),new Person("曹纯",15)};
        Stream<Person> personStream = Arrays.stream(personArr);
    }

    @Test
    // Stream静态方法实例化
    public void testStreamOf(){
        int[] arr = {1,2,3};    // 类型推导
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);

    }

    @Test
    // Stream 创建无限流
    public void testInfiniteStream(){
        // 迭代
        // public static <T> Stream<T> iterate(finale T seed,final UnaryOperator<T> f)
        Stream.iterate(0, integer -> integer + 2).limit(10).forEach(System.out::println);

        // 生成
        // public static <T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
