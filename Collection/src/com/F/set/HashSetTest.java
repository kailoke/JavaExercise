package com.F.set;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * HashSet-----------↓↓↓↓   本质上是HashMap
 * 二、无序性 != 随机性
 *  > 内部相对有序  底层数组不按 增加顺序 添加对象<--- Hash值计算后排列
 * 三、不可重复性
 *  > Object中的hashCode() 随机取值 Set中的对象需要重写 equals()和hashCode() [保持一致性，属性均在两个方法中使用]
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
public class HashSetTest {

    // 1.HashSet 测试
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
