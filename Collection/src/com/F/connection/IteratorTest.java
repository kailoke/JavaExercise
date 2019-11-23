package com.F.connection;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import javax.tools.ForwardingFileObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/** 集合元素的遍历操作，使用迭代器Iterator接口   迭代器模式
 *
 */
public class IteratorTest {

    @Test
    public void testIterator(){
        Collection coll = new ArrayList();

        coll.add("AA");
        coll.add("BB");
        coll.add(123);
        coll.add(new Date());

        // 1.获得Collection对象的全新Iterator对象
        Iterator iterator = coll.iterator();
        // 2. while + hasNext() + next()
        // 遍历后iterator的指针不会重置
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

        // remove()
        while (iterator.hasNext()){
            Object o = iterator.next();
            if (o.equals("AA")){
                iterator.remove();
            }
        }

        // 5.0 新增foreach循环，可用于遍历 集合 和 数组
        System.out.println("foreach 替代：");
        for (Object o : coll) {
            System.out.println(o);
        }
    }

    @Test
    public void testFor(){
        String[] arr = new String[]{"MM","MM","MM"};
        for (int i = 0; i <arr.length; i++) {
            arr[i] = "GG";
        }

        for (String i : arr
                ) {
            i = "MM";
        }

        for (String i : arr
                ) {
            System.out.println(i);
        }


    }
}
