package A2_List;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/** List接口 ---  "动态数组"
 *  |----ArrayList  作为List的主要实现类(since 1.2)  线程不安全，效率高   底层是Object[] elementDate，随机访问get和set快
 *  |----LinkedList 同ArrayList一起实现(since 1.2)  线程不安全，效率高   底层是双向链表，频繁插入、删除快
 *  |----Vector     作为List的古老实现类(since 1.0) 线程安全，效率低     底层是Object[] elementDate
 *  相同点：均实现了List接口，存储数据的特点相同:元素有序、可重复
 *          ArrayList和Vector集合中的每个元素都有其对应的顺序索引
 *
 * 一、ArrayList常用方法
 *  > 从Collection继承的方法 + ArrayList集合增加通过索引来操作集合元素的方法
 *
 * 二、ArrayList源码分析
 *  |JDK 7.0 : 饿汉式，initialCapacity(default) == 10
 *      > ArrayList list = new ArrayList() 默认构造器capacity = 10
 *      > add() 时，判断目标size当前list是否满足，不满足则进行1.5 * size 扩容， 并Arrays.copyOf(old)
 *      > 实际开发中建议使用 ArrayList list = new ArrayList(int capacity) 提前定义好容量
 *        或使用 public ensureCapacity(int min)一次性进行数组扩容，减少递增式再扩容的次数
 *
 *  |JDK 8.0 : 懒汉式，initialCapacity(default) == 0，add()时扩容 == 10
 *      > ArrayList list = new ArrayList() 默认构造器 Object[] elementDate = {},并没有创建内存对象
 *      > add()时，判断elementDate.length == 0 || size ? 进行扩容逻辑(默认构造器，1.5倍)
 *
 *  |Vector
 *  > 线程安全，公开方法均是 同步方法，效率较低
 *  > 默认构造器 capacity = 10 , 默认扩容为 2 * size
 *  > 多一个属性 capacityIncrement，可通过构造器指定Vector对象每次扩容空间大小。指定后扩容为 size + cI
 *  > 子类 Stack extends Vector
 *
 * 三、遍历方式 : List.Iterator   foreach   for数组形式
 */
public class A1_List_ArrayList {

    @Test
    // interview question
    public void testListRemove() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        updateList(list);
        System.out.println(list);   // [1,2]
    }
    private static void updateList(List list) {
        list.remove(2);
    }
}