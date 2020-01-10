package a2_StreamAPI;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/** Stream终止操作：收集，将流中元素返回为一个指定类型集合实例
 * 一、唯一方法：collect(Collector c)
 * 二、Collectors工具类的提供众多静态方法，方便创建常见Collector实例(List,Set,Map)
 *    Collectors工具类还提供 类似Stream的`操作链`及`终止方法`，得到Optional<T> 或 T 实例
 *  > List<T> toList()      : 流中元素收集到List
 *  > Set<T> toSet()        ：流中元素收集到Set
 *  > Collection<T> toCollection()  : 流中元素收集到Collection
 *  > Long counting()       : 计算流中元素的个数     为啥不直接用Stream.count()?
 *  > Integer summingInt()  : 对流中元素的整数属性求和
 *  > Double averagingInt() : 计算流中元素Integer属性的平均值
 *  > IntSummaryStatistics summarizingInt() : 收集流中Integer属性的统计值。如：平均值
 */

public class A7_Collect {
    @Test
    public void collect(){
        List<Person> persons = Person.getPersons();

        // 得到过滤后自然排序的List<Person>
        List<Person> list = persons.stream().filter(person -> person.getAge() >= 20)
                .sorted(Comparator.comparingInt(Person::getAge)).collect(Collectors.toList());
        System.out.println(list);

        Set<Person> collect = persons.stream().limit(4).collect(Collectors.toSet());
        collect.forEach(System.out::println);
    }
}
