package A4_Map;
import org.junit.Test;

import A3_Set.T_Person;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/** TreeMap : 使key-value按照key处于有序状态
 *  > 1. 底层是"红黑树"
 *  > 2. 只能放入相同类key类对象的entry；添加时即按key排序
 *  > 3. 相当判定: compareTo(o)  or compare(o,o)
 *  > 4. key自然排序
 *  > 5. key定制排序
 */


public class A4_TreeMap {

    @Test
    public void treeMap(){
        TreeMap treeMap = new TreeMap();
        treeMap.put(new T_Person("刘备",19),19);
        treeMap.put(new T_Person("刘备",18),18);
        treeMap.put(new T_Person("曹操",20),20);
        treeMap.put(new T_Person("张飞",19),19);
        treeMap.put(new T_Person("关羽",15),15);

        Set entrySet = treeMap.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()){
            System.out.println(((Map.Entry) iterator.next()).getKey());
        }
    }
}
