package a17_reduce;

import a10_StreamAPI.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/*
    归约
 */
public class reduceTest {
    @Test
    public void testReduce(){
        // 1. T reduce(T identity,BinaryOperator) : identity 归约起始值
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer reduce = list.stream().reduce(1, (Integer::sum));
        System.out.println("reduce():" + reduce);

        // 2. Optional<T> reduce(BinaryOperator) : 类似包装类？
        List<Person> persons = Person.getPersons();
        Optional<Integer> reduce1 = persons.stream().map(Person::getAge).reduce(Integer::sum);
        System.out.println("reduce():" + reduce1);
    }
}
