package com.F.map;

/** HashMap 底层实现
 * 一、new HashMap()  JDK7.0
 *  >1.实例化默认构造长度=DEFAULT_INITIAL_CAPACITY(16):Entry<K,V>[] table
 *  >2.key类的Hash值 = 类的方法，使用对象的属性计算,之后使用Hash值按照某种算法得出位置
 *  >3.此位置上为空元素则直接添加
 *  >4.此位置上有其他元素(或链表形式的元素)，则比较添加对象key和链表中元素的key的hash值
 *     > Hash值不同使用链表添加对象（JDK7从上添加，JDK8从下添加）
 *     > Hash值相同则调用添加对象的equals方法逐一对比
 *          > false = equals() key-value添加成功
 *          > true = equals() key的value被替换!!!
 *  > HashMap底层:数组+链表
 *  > 扩容：默认扩容为原来容量的2倍，并将原有数据复制到新容器中。
 *      > size >= TREEIFY_THRESHOLD = CAPACITY*loadFactor 进行扩容
 *
 * 二、HashMap    JDK8.0
 *  >1.new HashMap():底层不创建长度=16的数组
 *  >2.底层数组是 Node[]，而非Entry[]
 *  >3.首次调用put()，底层创建长度=16的数组
 *  >4.HashMap底层:数组+链表+红黑树
 *      > 当数组某个索引位置上的元素以 链表形式存在数据个数 > TREEIFY_THRESHOLD(8) && 当前数组长度 > MIN_TREEIFY_CAPACITY(64)
 *          ↑此索引位置上的所有数据改为使用红黑树存储。                                 ↑ 否则扩容
 */

import org.junit.Test;

public class HashMapTest {
    @Test
    public void test(){

    }
}
