package a18_collect;

import a10_StreamAPI.Person;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
1.collect(Collector.toList())
2.collect(Collector.toSet())
3.collect(Collector.toCollection())
 */
public class collectTest {
    @Test
    // collect(Collector c) Collectors工具类(实用类)调用静态方法返回Collector接口实例
    public void testCollect(){
        List<Person> persons = Person.getPersons();
        List<Person> list = persons.stream().filter(person -> person.getAge() > 18)
                .sorted(Comparator.comparingInt(Person::getAge)).collect(Collectors.toList());
        list.forEach(System.out::println);
    }


}
