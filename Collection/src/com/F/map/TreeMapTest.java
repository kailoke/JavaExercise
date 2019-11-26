package com.F.map;
import org.junit.Test;

import com.F.set.Person;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/** TreeMap
 *  > 1. 底层是"红黑树"
 *  > 2. 只能放入相同类key类对象的entry；添加时即按key排序
 *  > 3. key类自然排序 或者
 *         > if compareTo = 0 即此元素被认为已经存在；TreeSet中不使用equals判断同
 *  > 4. key类定制排序方法( new Comparator(){@override...})
 *         > compare()方法
 */


public class TreeMapTest {

    @Test
    public void test(){
        TreeMap treeMap = new TreeMap();
        treeMap.put(new Person("刘备",19),19);
        treeMap.put(new Person("刘备",18),18);
        treeMap.put(new Person("曹操",20),20);
        treeMap.put(new Person("张飞",19),19);
        treeMap.put(new Person("关羽",15),15);

        Set entrySet = treeMap.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()){
            System.out.println(((Map.Entry) iterator.next()).getKey());
        }
    }
}
