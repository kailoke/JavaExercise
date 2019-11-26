package com.F.set;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;


/** LinkedHashSet 仅遍历时按添加顺序遍历
 * 一、LinkedHastSet作为HashSet的子类
 * 二、增加元素时，对每个元素增加 Node previous\Node Next 链表结构
 *  > 对于频繁的遍历操作，LinkedHashSet效率高于HashSet
 */
public class LinkedHashSetTest {

    @Test
    public void testLinkedHashSet(){
        Set set = new LinkedHashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("BB");
        set.add(129);


        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
