package a12_Map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MapTest {
    @Test
    public void testMap(){
        List list = Arrays.asList("aa", "bb", "cc", "dd");
        ArrayList aList = new ArrayList(list);

        List list1 = Arrays.asList("ee", "gg");
        ArrayList aList1 = new ArrayList(list1);
        aList.add(aList1);
        System.out.println("AList:" + aList);   // 生成完整的ArrayList
        System.out.println("************");

        // 1.map(Function f) 按f将流中 元素 分别映射成 新元素 的流
        Stream<String> stream1 = aList1.stream();   // aList报错，ArrayList ClassCastException String
        stream1.map(String::toUpperCase).forEach( s -> System.out.println("Map:" + s));

        // 2.flatMap(Function f)    按f将流中 元素 分别映射成新流， 之后合流
        Stream<String> stream2 = aList.stream();
//        stream2.flatMap(s -> s.toUpperCase()).forEach( s -> System.out.println("Map:" + s));
    }
}
