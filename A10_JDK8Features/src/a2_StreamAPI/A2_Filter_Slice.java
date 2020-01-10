package a2_StreamAPI;

import org.junit.Test;

import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Stream;

/** Stream链操作：筛选与切片，数据过滤 && 头尾切片
 *  > filter(Predicate p) : 接收Lambda，从Stream排序false的元素
 *  > distinct() : 根据流元素的 hashCode() && equals() 去除重复元素
 *  > limit(long maxSize) : 从头开始截断流，使前缀元素个数 <= maxSize  保留头
 *  > skip(long n) : 从头开始截断流，抛弃前n个元素，返回新的Stream      保留尾
 */

public class A2_Filter_Slice {
    @Test
    public void filterAndSlice(){
        List<Person> persons = Person.getPersons(); // size = 6
        for (Person person : persons) {
            System.out.println(person);
        }
        System.out.println();

        // 1.filter(Predicate p)    Predicate实现类.test(T t)
        Stream<Person> stream1 = persons.stream();  // size = 4
        stream1.filter( p -> p.getAge() >= 18).forEach( o -> System.out.println("filter:" + o.toString()));
        System.out.println("********** filter end **********\n");

        // 2.limit(long maxSize) 指定流中处理的最大个数
        Stream<Person> stream2 = persons.stream();
        stream2.limit(3).forEach( o -> System.out.println("limit : " + o));
        System.out.println("********* limit end ***********\n");

        // 3.skip(n)，返回一个跳过n个元素的新Stream；若不足则返回空Stream
        Stream<Person> stream3 = persons.stream();
        Stream<Person> skip = stream3.skip(3);
        skip.forEach(o -> System.out.println("skip : " + o));
        System.out.println("********* skip end ***********\n");

        // 4.distinct()去重，根据hashCode() && equals() 去除重复元素
        persons.add(new Person("刘备",15));   // size = 7
        for (Person person : persons) {
            System.out.println(person);
        }
        System.out.println();
        Stream<Person> stream4 = persons.stream();  // size = 6
        stream4.distinct().forEach( o -> System.out.println("distinct:" + o));
    }
}
