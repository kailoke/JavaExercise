package a16_MatchFind;

import a10_StreamAPI.Person;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class MatchFindTest {
    @Test
    public void testAllMatch(){
        List<Person> list = Person.getPersons();

        // 1. boolean allMatch(Predicate p) : 是否所有元素匹配断言条件
        boolean allMatch = list.stream().allMatch(person -> person.getAge() > 10);
        System.out.println("1.allMatch():" + allMatch);

        // 2. boolean anyMatch(Predicate p) : 是否任一元素匹配断言条件
        boolean anyMatch = list.stream().anyMatch(p -> p.getAge() > 22);
        System.out.println("2.anyMatch():" + anyMatch);

        // 3. boolean noneMatch(Predicate p) : 是否没有元素匹配断言条件
        boolean noneMatch = list.stream().noneMatch(p -> p.getName().startsWith("赵"));
        System.out.println("3.noneMatch():" + noneMatch);

        // 4. Optional<T> findFirst()
        Optional<Person> findFirst = list.stream().findFirst();
        System.out.println("findFirst():" + findFirst);

        // 5. Optional<T> findAny() TODO ??? findAny()结果始终不变
        Optional<Person> findAny = list.stream().findAny();
        System.out.println("findAny():" + findAny);
        System.out.println("findAny():" + list.parallelStream().findAny());
    }

    @Test
    public void testFind(){
        List<Person> list = Person.getPersons();

        // 6. long count()
        long count = list.stream().count();
        System.out.println("count():" + count);

        // 7. Optional<T> max(Comparator com)

        // 8. Optional<T> min(Comparator com)

        // 9. forEach(Consumer c)
    }


}
