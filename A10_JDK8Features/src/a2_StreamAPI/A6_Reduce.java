package a2_StreamAPI;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.ToDoubleFunction;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/** Stream终止操作：归约，执行归约的流必须是类的具体成员
 *  > reduce(BinaryOperator b) : 自动从第一个元素开始归约函数，返回Optional<T>
 *  > reduce(T identity,BinaryOperator b) : 从额外的identity开始和流中元素归约函数，返回T类型的结果
 *      > BinaryOperator<T,T,T>，参数及返回值的类型需要全部一致
 *
 *  * Map-Reduce
 */

public class A6_Reduce {
    @Test
    public void reduce(){
        // 1. Optional<T> reduce(BinaryOperator) : 自动从第一个元素开始归约
        List<Person> persons = Person.getPersons();
        Optional<Integer> reduce1 = persons.stream().map(Person::getAge).reduce(Integer::sum);
//        persons.stream().reduce((o1,o2) -> Integer.sum(o1.getAge(),o2.getAge()));
        System.out.println("reduce(BinaryOperator): " + reduce1);

        // 2. T reduce(T identity,BinaryOperator) : 从额外的identity开始和流中元素归约
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        System.out.println("list sum: " + list.stream().mapToInt(i -> i).sum());

        Stream<Integer> stream = list.stream();
        int reduce2 = stream.reduce(0, Integer::sum);
        System.out.println("reduce(1, BinaryOperator): " + reduce2);

        Integer reduce3 = list.stream().reduce(1, Integer::sum);
        System.out.println("reduce(1, BinaryOperator): " + reduce3);

//         Stream<Double> doubleStream = list.stream().map(i -> Double.parseDouble(String.valueOf(i)));
        DoubleStream doubleStream = list.stream().mapToDouble(i -> Double.parseDouble(String.valueOf(i)));
        double reduce4 = doubleStream.reduce(0.0, Double::sum);
        System.out.println("reduce(0.0, BinaryOperator): " + reduce4);
    }
}
