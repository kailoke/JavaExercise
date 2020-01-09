package a11_FilterSplit;

import a10_StreamAPI.Person;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

public class FilterDistinctTest {
    @Test

    public void testFilter(){
        List<Person> persons = Person.getPersons();
        Stream<Person> stream1 = persons.stream();

        // 1.filter(Predicate p)
        // Predicate对象重写过滤方法体
        stream1.filter( p -> p.getAge() >= 18).forEach( o -> System.out.println("filter:" + o.toString()));

        // 2.limit(long maxSize) 指定流中处理的最大个数
        Stream<Person> stream2 = persons.stream();
        stream2.limit(3).forEach(System.out::println);
        System.out.println("********************");

        // 3.skip(n),返回一个跳过n个元素的新Stream；若不足则返回空Stream

        // 4.distinct(),筛选    hashCode() && equals() 去除重复元素
        persons.add(new Person("刘备",15));
        for (Person person : persons) {
            System.out.println(person);
        }
        Stream<Person> stream4 = persons.stream();
        stream4.distinct().forEach( o -> System.out.println("distinct:" + o));
    }
}
