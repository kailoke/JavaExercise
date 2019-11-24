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
