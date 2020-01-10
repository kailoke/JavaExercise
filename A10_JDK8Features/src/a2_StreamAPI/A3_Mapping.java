package a2_StreamAPI;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/** Stream链操作：映射，将元素从 old --> new
 * > map(Function<T,R> f) : 流中元素均执行函数，被映射成新的元素
 * > mapToDouble(ToDoubleFunction<T> f) : 每个元素均被函数映射，产生新的DoubleStream
 * > mapToInt(ToIntFunction<T> f) :  每个元素均被函数映射，产生新的IntStream
 * > mapToLong(ToLongFunction<T> f) :  每个元素均被函数映射，产生新的LongStream
 * > flatMap(Function<T,R> f) : 每个元素均被函数映射为另一个泛型流<R>，然后把所有流连接成一个流
 *      > flatMap 会遍历Stream流的Stream流内的元素，再重组为新流 [a,b,[c,d]] -> [a,b,c,d]
 *      > 使用于 流中套流 ： 集合中套集合
 */

public class A3_Mapping {
    @Test
    // 一、映射
    public void mapping(){
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd", "ee", "gg");
        ArrayList<String> aList = new ArrayList<>(list);

        // 1.map(Function<T,R> f) 每个元素均被函数映射为 新元素
        Stream<String> stream1 = aList.stream();   // aList报错，ArrayList ClassCastException String
        stream1.map(String::toUpperCase).forEach( s -> System.out.print("Map:" + s + '\t'));
        System.out.println();   // console segmentation

        // 2.flatMap(Function<T,R> f) 每个元素均被函数映射成另一个泛型流<R>，然后把所有流连接成一个流
        List<Person> persons = Person.getPersons();
        Stream<Person> stream = persons.stream();
        // Stream<Person> --> Stream<String>
        Stream<String> stringStream = stream.map(Person::getName);
        stringStream.filter(name -> name.length() > 1).limit(3).forEach(name -> System.out.println("flatMap: " + name + '\t'));
    }

    @Test
    // 二、理解 Map 和 flatMap
    public void seeAdd() {
        ArrayList list1 = new ArrayList();
        list1.add("aa");
        list1.add("bb");
        list1.add("cc");

        ArrayList list2 = new ArrayList();
        list2.add("ee");
        list2.add("gg");

//        list1.add(list2);   // add不会遍历数组内部 : [aa, bb, cc, [ee, gg]]
        list1.addAll(list2);    // addAll则会遍历数组内部 : list1:[aa, bb, cc, ee, gg]
        System.out.println("list1:" + list1);
    }

    // 返回字符串的字符流
    private static Stream<Character> StringStream(String str) {
        ArrayList<Character> characters = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            characters.add(character);
        }
        return characters.stream();
    }
    @Test
    // 三、 Map Vs flatMap
    public void flatMapVsMap() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd", "ee", "gg");

        // 1.Map : 流中套流，map之后依然是新的 流中套流
        Stream<Stream<Character>> streamStream = list.stream().map(A3_Mapping::StringStream);
        streamStream.forEach(stream -> {
            stream.forEach(System.out::print);
        });
        System.out.println();

        // 2.flatMap : 流中套流，flatMap会遍历内部流的内部
        Stream<Character> characterStream = list.stream().flatMap(A3_Mapping::StringStream);
        characterStream.forEach(System.out::print);
    }
}
