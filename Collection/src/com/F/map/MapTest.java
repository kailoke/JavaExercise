package com.F.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/** Map接口 存储双列数据，key-value 键值对数据 (since 1.2)
 *  |----HashMap:(since 1.2) 作为Map的主要实现类
 *    > 线程不安全，效率高
 *    > 可存储null的Key和Value
 *      |----LinkedHashMap:(since 1.4)
 *        > 遍历时可以按照添加顺序遍历，内部增加双向链表指针
 *        > 适用于频繁的遍历操作，执行效率优于HashMap
 *
 *  |----TreeMap
 *    > 添加时按照 比较器[key作为关键值] 排序
 *    > 底层使用共黑树
 *
 *  |----Hashtable:(since 1.0)作为Map的古老实现类
 *    > 线程安全，效率低
 *    > 不可存储null的Key和Value
 *      |----Properties: 处理配置文件；key和value都是String
 *
 * 二、Map结构理解
 *  > Map中的key: 无序的、不可重复的;  ↓使用Set存储所有的key
 *      ---key自定义类必须重写equals()和hashCode()
 *  > Map中的value:无序的、可重复的;   ↓使用Collection存储所有的value
 *      ---value自定义类必须重写 equals() ---> remove(),contains(),retainAll()
 *  > 键值对：key-value构成一个Entry对象
 *      ----Map中的Entry：无序的，不可重复的； 使用Set存储所有的Entry
 *
 * 三、Map常用方法
 * 增：Object put(Object key,Object value);   void putAll(Map m)
 * 删：Object remove(Object key) 返回移除键值对的value; void clear()
 * 改(Hash相关的改动产生系列影响，更适合删除后添加)
 * 查：Object get(Object key):获取key的value;    boolean containsKey(Object key);    boolean containsValue(Object value)
 *     boolean equals(Object o[Map m])
 * 长度：int size()    boolean isEmpty()
 * 遍历：
 *  > Set keySet(): 返回Map实例所有key构成的Set集合
 *  > Collection values():返回Map实例所有值构成的Collection
 *  > Set entrySet():返回Map实例所有entry构成的Set
 *
 */
public class MapTest {

    // 测试 null 键值
    @Test
    public void testNull(){
        Map map = new HashMap();
        map.put(null,null); // HashMap可以put null键值

        Map table = new Hashtable();
        table.put(null,null);   //throw NullPointerException
    }

}
