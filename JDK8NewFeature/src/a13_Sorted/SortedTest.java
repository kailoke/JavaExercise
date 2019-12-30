package a13_Sorted;

import a10_StreamAPI.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SortedTest {
    @Test
    public void testSorted(){
        List<Integer> list = Arrays.asList(12, 43, 87, 0, -98, 7);
        // sorted() 自然排序    因为Integer implements Comparable
        list.stream().sorted().forEach( integer -> System.out.println("sorted() :" + integer));

        // sorted(Comparator com) 定制排序 类内实现CompareTo 或者 提供Comparator
        List<Person> persons = Person.getPersons();
        persons.stream().sorted((p1,p2) -> Integer.compare(p1.getAge(),p2.getAge()))
                .forEach( person -> System.out.println("sorted(Comparator)" + person));
    }
}
