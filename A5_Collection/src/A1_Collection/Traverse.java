package A1_Collection;

import org.junit.Test;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/** Iterator迭代器接口
 * 一、Iterator对象称为迭代器(设计模式的一种)，仅用于遍历Collection集合中的元素
 *  >GOF给迭代器模式的定义为：提供一种方法访问一个容器(container)对象中各个元
 *   素，而又不需暴露该对象的内部细节。迭代器模式，就是为容器而生
 * 二、集合对象每次调用iterator()方法都将得到一个全新的迭代器对象
 *    默认游标都在集合的第一个元素之前。
 * 三、Iterator接口的方法
 * > hasNext()  判断游标后是否有元素
 * > next()     返回游标的下一个元素
 * > remove()   移除游标当前所指元素
 */
public class Traverse {

    @Test
    // 1.Iterator
    public void iterator(){
        Collection coll = new ArrayList();
        coll.add("AA");
        coll.add("BB");
        coll.add(123);
        coll.add(new Date());

        // 1.获得Collection对象的全新Iterator对象
        Iterator iterator = coll.iterator();

        // 2. while + hasNext() + next()
        // 遍历后iterator对象的指针不会重置
        while (iterator.hasNext()){
//            Object o = iterator.next();
            if ("AA".equals(iterator.next())){
                System.out.println("遍历到 \"AA\" 移除");
                iterator.remove();
            }
        }

        // 5.0 新增foreach循环，可用于遍历 集合 和 数组
        for (Object o : coll) {
            System.out.println("foreach 遍历：" + o);
        }
    }

    @Test
    // 2.for 的两种遍历方式
    public void foreach(){
        String[] arr = new String[]{"MM","MM","MM"};
        // 2.1 数组形式
        for (int i = 0; i <arr.length; i++) {
            arr[i] = "GG";
        }
        // 2.2 foreach，底层仍是调用Iterator
        for (String i : arr) {
            i = "MM";
        }
        System.out.println("foreach 不能修改原数组或列表中的元素");
        for (String i : arr) {
            System.out.println(i);
        }
    }
}
