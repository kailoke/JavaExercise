package a2_StreamAPI;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/** Stream终止操作：匹配与查找
 * > allMatch(Predicate p) : 检查所有元素是否匹配函数条件
 * > anyMatch(Predicate p) : 检查是否有任一元素匹配函数条件
 * > noneMatch(Predicate p) : 检查是否没有元素匹配函数条件
 * > findFirst() : 返回流中第一个元素，Option<T>类对象
 * > findAny() ：返回流中任一元素，Option<T>类对象
 *
 * > count() : 返回流的元素总数
 * > max(Comparator c) : 返回流中比较器比较后的最后一个
 * > min(Comparator c) : 返回流中比较器比较后的第一个
 * > forEach(Consumer c) : 内部迭代，对流的每个元素进行Consumer函数
 *      > 外部迭代：Collection接口需要用户迭代: iterator()
 */

public class A5_Match_Find {
    @Test
    // 一、匹配与查找
    public void matchFind(){
        List<Person> list = Person.getPersons();

        // 1. boolean allMatch(Predicate p) : 是否所有元素匹配断言条件
        boolean allMatch = list.stream().allMatch(person -> person.getAge() > 10);
        System.out.println("1.allMatch(allAge > 10): " + allMatch);

        // 2. boolean anyMatch(Predicate p) : 是否任一元素匹配断言条件
        boolean anyMatch = list.stream().anyMatch(p -> p.getAge() > 22);
        System.out.println("2.anyMatch(anyAge > 22): " + anyMatch);

        // 3. boolean noneMatch(Predicate p) : 是否没有元素匹配断言条件
        boolean noneMatch = list.stream().noneMatch(p -> p.getName().startsWith("赵"));
        System.out.println("3.noneMatch(noneName startsWith 赵): " + noneMatch);

        // 4. Optional<T> findFirst()
        Optional<Person> findFirst = list.stream().findFirst();
        System.out.println("4.findFirst(): " + findFirst);

        // 5. Optional<T> findAny()
        Optional<Person> findAny = list.stream().findAny();
        System.out.println("5.stream.findAny(): " + findAny);   // 顺序流始终取第一个
        // TODO 串行流始终固定且非最后,why
        System.out.println("5.parallelStream.findAny(): " + list.parallelStream().findAny());
    }

    @Test
    // 二、流结果
    public void result(){
        List<Person> list = Person.getPersons();

        // 1. forEach(Consumer c)
        list.stream().skip(0).forEach(System.out::println);

        // 2. long count()
        long count = list.stream().filter(i -> i.getAge()>20).count();
        System.out.println("1.count(age > 20): " + count);

        // 3. Optional<T> max(Comparator com) : 最后一个
        Optional<Person> max = list.stream().max(Comparator.comparingInt(Person::getAge));
        System.out.println("2.max(age): " + max);

        // 4. Optional<T> min(Comparator com) : 第一个
        Optional<Person> min = list.stream().min(((o1, o2) -> -Integer.compare(o1.getAge(), o2.getAge())));
        System.out.println("3.min(-age): " + min);
    }
}
