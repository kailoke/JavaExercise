package A3_Set;

/** Set "集合"接口 : 无序、不可重复
 * 一、继承树
 *  |----HashSet        作为Set接口的主要实现类   线程不安全   可以存储null值   底层"数组+链表"
 *      |____LinkedHashSet  HashSet的子类，遍历其内部数据时可以按照添加的顺序遍历
 *  |____SortedSet
 *      |----TreeSet    二叉树--->红黑树 存储数据； 放入的数据必须是同类对象，可以按照属性排序
 *
 * 二、Set extends Collection，没有提供额外的方法
 */

public class A1_Set {
    public static void main(String[] args) {
    }
}
