package com.F.set;

/** Set接口 无序、不可重复           "集合"
 * |----HashSet     作为Set接口的主要实现类   线程不安全   可以存储null值   "底层数组+链表"
 *   |----LinkedHashSet HashSet的子类，遍历其内部数据时可以按照添加的顺序遍历
 * |----TreeSet     二叉树--->红黑树 存储数据； 放入的数据必须是同类对象，可以按照属性排序
 *
 * Set中没有新增方法，因其内部没有索引
 */

public class SetTest {
    public static void main(String[] args) {
    }
}
