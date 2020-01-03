package A1_Collection;

import org.junit.Test;
import java.util.*;

/** Collection 静态内存数据结构
 * 一、java集合框架(容器)
 *  |----Collection大接口：单列数据，存储一个一个的对象
 *      |____List子接口(继承):存储有序、可重复的数据                <动态数组></动态数组>
 *          |----实现类：ArrayList
 *                      LinkedList
 *                      Vector
 *      |____Set子接口(继承):存储无序、不可重复的数据               <集合></集合>
 *          |----实现类：HashSet ____LinkedHashSet(继承)
 *                      SortedSet---TreeSet(实现)
 *      |____Queue子接口(继承)
 *
 *  |----Map接口：双列数据，存储具有映射关系“Key-value”对的数据    <键值对></键值对>
 *      |----实现类：HashMap ____LinkedHashMap(继承)
 *                  Hashtable___Properties(继承)
 *                  TreeSorted--TreeMap(实现)
 * 二、泛型
 *  > JDK5.0前，java集合会丢失容器中所有对象的数据类型
 *  > JDK5.0新增泛型，使得java集合可以记住容器中对象的数据类型
 */


public class CollectionFrame {

    @Test
    // 集合接口方法
    public void collectionMethod1(){
        Collection coll = new ArrayList();

        // 1.add(Object o) 添加
        coll.add("AA");
        coll.add("BB");
        coll.add(123);
        coll.add("AA");
        coll.add(new Date());

        // 1.addAll(Collection c) 添加集合
        Collection collAll = new ArrayList();
        collAll.addAll(coll);
        // 等价于 Collection collAll = new ArrayList(coll);

        // 2.size() 获取有效元素的个数
        System.out.println("2   coll.size() : " + coll.size());
        System.out.println("2   collAll.size() : " + collAll.size());

        // 3.clear() 清除容器内数据，不清除容器对象
        collAll.clear();
        System.out.println("3   collAll.clear()....");

        // 4.isEmpty
        System.out.println("4   coll.isEmpty() : " + coll.isEmpty());
        System.out.println("4   collAll.isEmpty() : " + collAll.isEmpty());

        // 5.contains(Object o)  调用o的equals()和内部所有元素对比
        System.out.println("5   coll.contains(123): " + coll.contains(123));

        // 6.remove(Object o)   调用o的equals() 找到第一个对象后remove
        collAll.addAll(coll);
        System.out.println("6   coll.remove(\"CC\") :" + coll.remove("CC"));
        System.out.println("6   coll.remove(\"AA\") :" + coll.remove("BB"));
//        System.out.println("6   coll.remove(\"AA\") :" + coll.remove("AA"));  // 移除AA，下containsAll则均true

        // 5.containsAll(Collection c) !调用者是否包含形参的全部元(不判断个数，只equals())
        System.out.println("5   coll.containsAll(collAll) : " + coll.containsAll(collAll));
        System.out.println("5   collAll.containsAll(coll) : " + collAll.containsAll(coll));

        // 6.removeALL(Collection c)    调用者移除与形参的交集，保留差集
        collAll.removeAll(coll);    // 差集为空，因"AA"两个。实际调用equals()判断并集
        System.out.println("6   collALL.removeAll(coll).size() : " + collAll.size());
        System.out.println("6   after collAll.removeALL,coll.size() : " + coll.size());

        // 7.retainAll(Collection c)    调用者与形参的交集，移除差集
        collAll.add("AA");
        collAll.add("CC");
        collAll.retainAll(coll);
        System.out.println("7   collALL.retainAll(coll).size() : " + collAll.size());

        // 8.equals(Object o) 判断两个集合是否相同，根据集合是否有序判断
        System.out.println("8   coll.equals(collAll) : " + coll.equals(collAll));

        // 9.toArray()  将集合转成对象数组Object[]，注意数组类型
        Object[] oa = coll.toArray();
        for (Object o: oa) {
            System.out.println("9   coll.toArray() : " + o);
        }

        // 10.hashCode  获取集合对象的hash值
        System.out.println("10  coll.hashCode() :" + coll.hashCode());

        // 11.Arrays.asList()，返回Arrays内部类ArrayList的固定长度List集合，既不是ArrayList实例，也不是Vector实例
        // 基本类型数组是对象，会整体作为对象存入List中
        List coll2 = Arrays.asList(new boolean[]{true, true});
        System.out.println("11  Arrays.asList.getClass() : " + coll2.getClass());   // java.util.Arrays$ArrayList
        System.out.println("11  Arrays.asList().size(数组) : " + coll2.size());
        // 包装类数组是对象数组，List会将对象数组中的对象逐一添加
        coll2 = Arrays.asList(new Boolean[]{true, true});
        // 等价于 Arrays.asList(true, true);
        System.out.println("11  Arrays.asList().size(对象数字) : " + coll2.size());
    }
}
