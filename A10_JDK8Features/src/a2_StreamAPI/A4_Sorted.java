package a2_StreamAPI;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/** Stream链操作：排序 sorted()
 * > sorted() ：产生一个自然排序后的新流
 * > sorted(Comparator conn) : 产生一个比较器排序的新流
 */

public class A4_Sorted {
    @Test
    public void sorted(){
        List<Integer> list = Arrays.asList(12, 43, 87, 0, -98, 7);
        // 1.sorted() 自然排序，Integer implements Comparable
        list.stream().sorted().forEach( integer -> System.out.println("sorted() :" + integer));
        System.out.println();

        // 2.sorted(Comparator com) 比较器排序 提供Comparator匿名类对象
        List<Person> persons = Person.getPersons();
        persons.stream().sorted((p1,p2) -> -Integer.compare(p1.getAge(),p2.getAge()))
                .forEach( person -> System.out.println("sorted(Comparator)" + person));
    }
}
