package A3_Set;

import org.junit.Test;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/** LinkedHashSet extends HashSet
 * > 拥有HashSet的特性同时，使用双向链表维护元素的次数，使得元素可以按`插入顺序`遍历
 *      > 每个元素增加 Node<E> previous \ Node<E> Next 指针
 *  > LinkedHashSet插入性能略低于 HashSet
 *  > LinkedHashSet迭代访问全部元素时有很好的性能
 */
public class A3_LinkedHashSet {

    @Test
    public void linkedHashSet(){
        Set set = new LinkedHashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("BB");
        set.add(129);
        System.out.println("added 6,LinkedHashSet.size() : " + set.size());

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
