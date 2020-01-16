package A4_Map;

import java.util.Hashtable;

/** HashTable since JDK1.0
 * 一、相同点：
 *  > 都实现了 Map<K,V>接口，HashTable继承自Dictionary类
 *  > HashTable实现原理和HashMap相同，功能相同，底层都是使用哈希表结构。
 *  > HashTable 也是无序、不可重复的键值对，判断相等的标准一致
 * 二、区别：
 *  > 不同于HashMap，HashTable是线程安全的;hashMap是hashTable的轻量级实现
 *  > HashTable不允许使用 Null 作为 key 和 value
 */


public class A5_HashTable {
    public static void main(String[] args) {
        Hashtable<String, String> hashTable = new Hashtable<>();
    }
}
