package A3_Set;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;
/** TreeSet : 使集合元素处于排序(SortedSet)状态，有序：查询速度快
 *  > 1. 底层是"红黑树"数据结构，2-3树的简单实现
 *  > 2. 要进行对象排序，则该对象的类必须实现 Comparable
 *      > implements Comparable and @Override compareTo(Object obj)
 *  > 3. 因为只能有相同类的对象才能比较大小，所以TreeSet中不能添加不同类对象
 *  > 4. TreeSet集合判断两个对象是否相等的标准：两个对象 compare(Object obj)方法比较返回值
 *  > 5. 自然排序：调用对象的compareTo()方法获得对象值后，默认升序排序
 *  > 6. 定制排序：构造TreeSet时传入比较器，则相等标准变为 Comparator 的 compare() 方法
 *      > new Comparator(){@Override compare(Obj,Obj)} )
 */
public class A4_TreeSet {
    @Test
    // 1. TreeSet只能加入相同类的对象
    public void sorted(){
        TreeSet<Integer> set = new TreeSet<>();
        set.add(123);
        set.add(456);
//        set.add("AA");  当有不同类型对象时，TreeSet会强制转型，导致 throw ClassCastException
        set.add(44);
        set.add(1);
        // 基本数据类型，默认升序排序
        for (Object i : set) {
            System.out.println(i);
        }
    }

    // 2.自定义类型
    @Test
    public void comparable(){
        // 2.1 增加时按 compareTo() 年龄优先，名字次级优先
        TreeSet<T_Person> set = new TreeSet<>();
        set.add(new T_Person("曹操",20));
        set.add(new T_Person("刘备",18));
        set.add(new T_Person("张飞",19));
        set.add(new T_Person("项羽",0));
        set.add(new T_Person("刘备",19)); // 按名字和年龄排序，则不重复，可以加入
        set.add(new T_Person("关羽",15));

        for (Object p : set) {
            System.out.println(p);
        }
    }

    // 3.Comparator
    @Test
    public void comparator(){
        // 在TreeSet构造器中传入 Comparator 实例
        TreeSet<T_Person> set = new TreeSet<>(new Comparator<T_Person>() {
            @Override
            public int compare(T_Person o1, T_Person o2) {
                return -Integer.compare(o1.getAge(),o2.getAge());    // 降序
            }
        });

        set.add(new T_Person("刘备",19));
        set.add(new T_Person("项羽",0));
        set.add(new T_Person("刘备",18));
        set.add(new T_Person("曹操",20));
        set.add(new T_Person("张飞",19)); // compare == 0 无法加入
        set.add(new T_Person("关羽",15));

        for (Object p : set) {
            System.out.println(p);
        }
    }
}
