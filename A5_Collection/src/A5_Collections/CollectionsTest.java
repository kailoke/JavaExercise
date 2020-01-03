package A5_Collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** Collections：操作Set\List\Map等集合的工具类
 * 一、Collections工具类提供了一系列静态的方法对集合元素进行排序、查询和修改等操作，
 *     还提供了对集合对象设置不可变、对集合对象实现同步控制等方法。
 *
 * 二、排序：
 * > reverser(List) 反转List中的元素顺序
 * > shuffle(List)  使List集合元素随机排序
 * > sort(List)     自然排序
 * > sort(List, Comparator) 定制排序
 * > swap(List, i, j)   交换集合中的 i\j 索引元素值
 *
 * 三、查找
 * > Object max(Collection) \ min(Collection) 根据自然排序，返回集合中的最值
 * > Object max(Collection, Comparator) \ min(Collection, Comparator) 根据定制排序，返回集合中的最值
 * > int frequency(Collection,Object)   返回集合中特定元素出现的次数
 *
 * 四、替换
 * > void copy(List dest,List src)  src内容复制到dest | 提前保证集合容量，否则IndexOutOfBoundsException
 * > boolean replaceAll(List list,Object old,Object new): 旧值改新
 *
 * 五、同步控制，将参数集合包装成线程同步的集合
 * > synchronizedCollection(Collection)
 * > synchronizedList(List)
 * > synchronizedSet(Set)
 * > synchronizedMap(Map)
 * > synchronizedSortedSet(SortedSet)
 * > synchronizedSortedMap(SortedMap)
 */


public class CollectionsTest {
    public static void main(String[] args) {
        List<Integer> list =new ArrayList<>();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(-97);
        list.add(0);
//        List<Integer> dest = new ArrayList<>(5);    // 内部数组指向改变了，但未修改 size
        List<Object> dest = Arrays.asList(new Object[list.size()]); // Object空数组，则修改了size
        System.out.println("dest.size() : " + dest.size());

        java.util.Collections.copy(dest,list);
        System.out.println(dest);
    }
}
