package A4_Map;

import org.junit.Test;

import java.io.ObjectStreamException;
import java.util.*;

/** Map接口 存储双列数据，key-value 键值对数据 (since 1.2)
 *  |----HashMap:(since 1.2) 作为Map的主要实现类
 *    > 线程不安全，效率高
 *    > 可存储null的Key和Value
 *      |____LinkedHashMap:(since 1.4)
 *        > 遍历时可以按照添加顺序遍历，内部增加双向链表指针
 *        > 适用于频繁的遍历操作，执行效率优于HashMap
 *
 *  |----TreeMap
 *    > 添加时按照 比较器[key作为关键值] 排序
 *    > 底层使用红黑树
 *
 *  |----Hashtable:(since 1.0)作为Map的古老实现类
 *    > 线程安全，效率低
 *    > 不可存储null的Key和Value
 *      |----Properties: 处理配置文件；key和value都是String
 *
 * 二、Map结构理解
 *  > Map中的key: 无序的、不可重复的，使用Set存储所有的key
 *      ---key对象的类必须重写 equals() 和 hashCode()
 *  > Map中的value:无序的、可重复的，使用Collection存储所有的value
 *      ---value自定义类必须重写 equals() ---> remove(),contains(),retainAll()
 *  > 键值对：key-value构成一个Entry类对象，即通过指定的key总能找到唯一的、确定的value
 *      ---Map中的Entry：无序的，不可重复的，使用Set存储所有的Entry
 *
 * 三、Map常用方法
 * 增：Object put(Object key,Object value);   void putAll(Map m)
 * 删：Object remove(Object key) 返回移除键值对的value; void clear()
 * 改：(Hash相关的改动产生系列影响，更适合删除后添加)
 * 查：Object get(Object key) 获取key对应的value
 *     boolean containsKey(Object key)    boolean containsValue(Object value)
 *     boolean equals(Object o) 判断当前map和参数对象obj是否相等
 * 长度：int size()    boolean isEmpty()
 * 遍历：(元视图)
 *  > Set<K> keySet():     返回 Set<K>
 *  > Collection<V> values():   返回 Collection<V>
 *  > Set<Map.Entry<K, V>> entrySet():   返回 Set<Map.Entry<K, V>>
 */


public class A1_Map {

    @Test
    // 测试 put null 键-值
    public void testNull(){
        Map map = new HashMap();
        map.put(null,null); // HashMap可以put null键-值
        map.put(null,1);    // 键相同时，更新键值
        map.put(1,null);

        Map table = new Hashtable();
//        table.put(null,null);   //throw NullPointerException

        System.out.println("map的所有key:");
        Set keys = map.keySet();    // HashSet
        for (Object key : keys) {
            System.out.println(key + "->" + map.get(key));
        }

        System.out.println("map的所有的value：");
        Collection values = map.values();
        Iterator iter = values.iterator();
        while (iter.hasNext()) {
            System.out.println("value:" + iter.next());
        }

        System.out.println("map所有的映射关系：");
        for (Object e: map.entrySet()) {    // iterator返回的是泛型对象
            Map.Entry entry = (Map.Entry) e;    // 映射关系的类型是 Map.Entry类型，Map接口的内部接口
            System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
        }
    }
}
