package com.F.set;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;
/*
    TreeSet
 > 1. 底层是"红黑树"
 > 2. 只能放入相同类对象，不能添加不同类对象
 > 3. 自然排序 或者
        > if compareTo = 0 即此元素被认为已经存在；TreeSet中不使用equals判断同
 > 4. 定制排序方法( new Comparator(){@override...})
        > compare()方法
 */
public class TreeSetTest {
    @Test
    public void test1(){
        TreeSet<Integer> set = new TreeSet<Integer>();
        set.add(123);
        set.add(456);
//        set.add("AA");  当有不同类型对象时，TreeSet会强制转型，导致 throw ClassCastException
        set.add(44);
        set.add(1);

        for (Object i : set
             ) {
            System.out.println(i);
        }
    }

    // 1.Comparable
    @Test
    public void test2(){
        // 增加时即使用compareTo()进行排序
        TreeSet<Person> set = new TreeSet<>();
        set.add(new Person());
        set.add(new Person("刘备",19));
        set.add(new Person("刘备",18));
        set.add(new Person("曹操",20));
        set.add(new Person("张飞",19));
        set.add(new Person("关羽",15));

        for (Object p :
                set) {
            System.out.println(p);
        }
    }

    // 2.Comparator
    @Test
    public void test3(){
        // 增加时即使用compareTo()进行排序，传入比较器
        // 无比较器则使用自然排序compareTo，否则使用比较器compare()
        TreeSet set = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Person && o2 instanceof Person){
                    Person p1 = (Person)o1;
                    Person p2 = (Person)o2;
                    return p1.getName().compareTo(p2.getName());
                }else {
                    throw new RuntimeException("数据不匹配");
                }
            }
        });
        set.add(new Person("项羽",0));
        set.add(new Person("刘备",19));
        set.add(new Person("刘备",18));
        set.add(new Person("曹操",20));
        set.add(new Person("张飞",19));
        set.add(new Person("关羽",15));

        for (Object p : set) {
            System.out.println(p);
        }
    }
}
