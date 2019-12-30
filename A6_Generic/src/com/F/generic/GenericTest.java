package com.F.generic;

import org.junit.Test;

import java.util.ArrayList;

/** 泛型 JDK5.0新增特性
 * 一、在集合中使用泛型
 *  > 1.集合接口或集合类在JDK5.0是都修改为带泛型结构
 *  > 2.泛型类型 必须是 类
 *  > 3.在实例化集合类时，可以指明具体的泛型类型
 *  > 4.指明泛型的集合类或接口中的泛型均会替换为指定类型
 * 二、需求泛型的类实例化时若未指定，则默认类型为java.lang.Object
 *
 * 三、异常类不能声明为异常类:catch 时若不是异常类不能处理
 * 四、泛型方法 <E> List<E> method(E e)；声明时使用<E>关键词声明是泛型方法，不是类名
 *      > 调用时确定泛型，可以声明为 static
 */


public class GenericTest {
    // 没有泛型
    @Test
    public void testNoGeneric(){
        ArrayList list = new ArrayList();
        // 需求：存放成绩
        list.add(78);
        list.add(96);
        list.add(88);
        list.add(92);
        // 1.类型不安全，无编译检查
//        list.add("Tom");

        for (Object o : list) {
            // 2.因为没有指定类型，所以需要进行类型强转，则引发 ClassCastException
            int score = (Integer)o;
            System.out.println(score);
        }
    }

    // 使用泛型
    @Test
    public void testGeneric(){
        ArrayList<Integer> list = new ArrayList<>();
        // 需求：存放成绩
        list.add(78);
        list.add(88);
        list.add(96);
        list.add(92);
        // 1.编译检查类型不通过
//        list.add("Tom");

        for (int o : list) {
            // 2.因为已经指定类型，则不需要强转
            System.out.println(o);
        }
    }
}
