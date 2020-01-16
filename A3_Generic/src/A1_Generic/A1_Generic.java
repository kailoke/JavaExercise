package A1_Generic;

import org.junit.Test;
import java.util.ArrayList;

/** 泛型 : JDK5.0新增特性 <参数化类型 parameterized Type>
 * 一、思想：将元素的类型设计为参数，这个类型参数叫做泛型
 * 二、定义：允许在定义`类``接口`时通过一个标识表示类中某个`属性的类型`或者是某个方法的`返回值`及`参数类型`。
 *          这个类型参数将在实例化对象时确定。
 *    * 泛型可以解决元素存储的安全性问题，不再需要类型转换，使代码更加简洁、健壮；编译时检查
 *    * 泛型的核心思想：将集合中的内容限制为一个特定数据类型
 *
 *  在集合中使用泛型
 *  > 1.集合框架中的全部 接口和类 在JDK1.5都修改为 <泛型结构>
 *      从而可以在声明集合变量、创建集合对象时传入类型实参
 *  > 2.在实例化集合类时，可以指明集合中元素的类型，该集合将只能保存在 泛型 元素
 *
 * 三、JAVA泛型标记：
 * > E  Element 集合类，存放元素(类)
 * > T  Type    Java类(S、 U、 V - 2nd/3rd/4th types)
 * > K  Key     键(类)
 * > V  Value   值(类)
 * > N  Number  仅数值类型
 * > ?  Wildcard通配符，任意类型，通常搭配 extends || super 指定泛型的下限或者泛型的上限
 */

public class A1_Generic {
    @Test
    // 1.没有泛型
    public void noGeneric(){
        // 需求：存放成绩
        ArrayList list = new ArrayList();
        list.add(78);
        list.add(96);
        list.add(88);
        list.add(92);
        list.add("Tom");  // 1.无编译检查，可以放入；但之后强转会 throw new ClassCastException

        for (Object o : list) {
            // 2.因为没有指定类型，所以需要进行类型强转，可能引发 ClassCastException
            int score = (Integer)o;
            System.out.println(score);
        }
    }

    // 2.使用泛型
    @Test
    public void testGeneric(){
        ArrayList<Integer> list = new ArrayList<>();
        // 需求：存放成绩
        list.add(78);
        list.add(88);
        list.add(96);
        list.add(92);
//        list.add("Tom");  // 1.编译检查类型不通过

        for (int o : list) {
            // 2.因为已经指定类型，则不需要强转
            System.out.println(o);
        }
    }
}
