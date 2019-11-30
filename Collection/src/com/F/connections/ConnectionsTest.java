package com.F.connections;

import com.F.set.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** Collections：操作Collection\Map的工具类
 *
 * reverser(list)
 * shuffle(list)
 * sort(list)   自然排序
 * sort(list, Comparator)   定制排序
 * swap(list,index i,index j)
 *
 * Object max(Collection) \ min(Collection)
 * Object max(Collection, Comparator) \ min(Collection, Comparator)
 * int frequency(Collection,Object)
 * void copy(List dest,List src)  src内容复制到===>dest | IndexOutOfBoundsException
 * boolean replaceAll(List list,Object old,Object new): 旧值改新
 *
 * synchronizedXxxx(Xxxx):返回线程安全的容器
 *
 */
public class ConnectionsTest {
    public static void main(String[] args) {
        List<Integer> list =new ArrayList<>();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(-97);
        list.add(0);
//        List<Integer> dest = new ArrayList<>(list.size());
        List<Object> dest = Arrays.asList(new Object[list.size()]);
        System.out.println(dest);   // ↑ Object类空数组

        Collections.copy(dest,list);
        System.out.println(dest);
    }
}
