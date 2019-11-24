package com.F.connection;

import org.junit.Test;

import java.util.*;

/**
 * 一、集合框架(容器)
 *  |----Collection接口：单列数据，存储一个一个的对象
 *      |----List接口(继承):存储有序、可重复的数据                <动态数组></动态数组>
 *          |----实现类：ArrayList  LinkedList  Vector
 *      |----Set接口(继承):存储无序、不可重复的数据               <集合></集合>
 *          |----实现类：HashSet    LinkedHashSet   TreeSet
 *
 *  |----Map接口：双列数据，存储具有映射关系“Key-value”对的数据    <键值对></键值对>
 *      |----实现类：HashMap    LinkedHashMap   TreeMap Hashtable Properties
 *
 * 二、Collection接口的中的方法,如下
 *
 */
public class ConnectionTest {

    @Test
    public void test1(){
        Collection coll = new ArrayList();

        // 1.add(Object 0)
        coll.add("AA");
        coll.add("BB");
        coll.add(123);
        coll.add(new Date());

        // 2.size()
        System.out.println(coll.size());

        // 3.addAll(Collection c)
        Collection coll1 = new ArrayList();
        coll1.addAll(coll);
        System.out.println("size() :" + coll1.size());

        // 5.clear() 清楚数据，不清除对象
//        coll1.clear();

        // 4.isEmpty
        System.out.println("isEmpty:" + coll1.isEmpty());

        // 6.contains(Object o)  调用o的equals()和内部所有元素有序逐一对比
        System.out.println("contains: " + coll.contains("AA"));

        // 7.containsAll(Collection c) 调用c所有对象的equals和容器内所有元素有序逐一对比


        // 8.remove(Object o)   调用o的equals() 找到到对象后remove
        System.out.println("remove() :" + coll.remove("CC"));

        // 9.removeALL(Collection c) 调用者 移除与coll1交集

        // 10.retainAll(Collection c) 返回boolean，保留调用者元素为两者交集

        // 11.equals(Object o) 判断两个集合是否相同，根据集合是否有序判断

        // 12.hashCode  返回对象的hash值
        System.out.println("hashCode() :" + coll.hashCode());

        // 13.toArray() 集合--->数组    // 注意数组类型
        Object[] ts = coll1.toArray();
        for (Object o: ts
             ) {
            System.out.println(o);
        }
        // Arrays.asList()，返回值是List对象 数组--->集合  !!基本类型数组会整体作为一个对象存入!!
        List coll2 = Arrays.asList(new Boolean[]{true,true});
        System.out.println("Arrays.asList(): " + coll2.size());

        // 14.iterator()  返回Iterator接口的实例，用于遍历List元素
    }
}
