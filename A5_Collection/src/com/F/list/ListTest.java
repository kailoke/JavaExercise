package com.F.list;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;

/** List接口 ---  "动态数组"
 *      |----ArrayList  作为List的主要实现类(since 1.2) 线程不安全，效率高   底层是Object[] elementDate
 *  *      |----LinkedList 同ArrayList一起实现(since 1.2) 线程不安全，效率高   底层是双向链表,适合频繁插入、删除
 *  *      |----Vector     作为List的古老实现类(since 1.0) 线程安全，效率低     底层是Object[] elementDate
 *
 * 相同点：均实现了List接口，存储数据的特点相同
 *
 * 一、ArrayList源码分析
 *  | JDK 7.0   饿汉式
 *  > ArrayList list = new ArrayList() 默认构造器capacity = 10
 *  > add() 时，判断目标size当前list是否满足，不满足则进行1.5 * size 扩容， 并Arrays.copyOf(old)
 *  > 实际开发中建议使用 ArrayList list = new ArrayList(int capacity) 提前定义好容量
 *  | JDK 8.0   懒汉式
 *  >ArrayList list = new ArrayList() 默认构造器 Object[] elementDate = {},并没有创建内存对象
 *  > add()时，判断elementDate 容量进行扩容逻辑
 *
 * 二、LinkedList源码分析
 *  > LinkedList list = new LinkedList() 声明Node first \ Node last = null
 *  > Node<E> 内部类
 *
 * 三、Vector源码分析
 *  > 默认构造器 capacity =10
 *  > 扩容2倍
 *
 * 四、List常用方法
 *
 * 五、遍历方法  Iterator,foreach,fori
 */
public class ListTest {
    /*
    List常用方法，增加索引方法。因List内部有序且有索引
     */

    //
    @Test
    public void test1(){

    }
}