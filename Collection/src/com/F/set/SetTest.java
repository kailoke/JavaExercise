package com.F.set;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/** Set接口 无序、不可重复           "集合"
 * |----HashSet     作为Set接口的主要实现类   线程不安全   可以存储null值   底层数组+链表
 *   |----LinkedHashSet HashSet的子类，遍历其内部数据时可以按照添加的顺序遍历
 * |----TreeSet     二叉树--->红黑树 存储数据； 放入的数据必须是同类对象，可以按照属性排序
 *
 * 一、Set中没有新增方法，因其内部没有索引
 *
 * 二、无序性 != 随机性
 *  > 内部相对有序  底层数组不按 增加顺序 添加对象<--- Hash值
 * 三、不可重复性
 *  > 增加的元素按照equals()判断时，不能返回true.
 *
 * 四、add()过程    以HashSet为例
 *  > 默认构造长度16
 *  > Hash值 = 类的方法，使用对象的属性计算
 *  > Hash值按照某种算法得出位置
 *  > 位置上无元素则直接添加
 *  > 位置上有其他元素(或链表形式的元素)，则比较添加对象和链表的hash值
 *      > Hash值不同使用链表添加对象（JDK7从上添加，JDK8从下添加）
 *      > Hash值相同则调用添加对象的equals方法逐一对比  true则不添加 false添加
 */
public class SetTest {
    @Test
    public void testHashSet(){
        Set set = new HashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("BB");
        set.add(129);


        for (Object o : set
             ) {
            System.out.println(o);
        }
    }
}
