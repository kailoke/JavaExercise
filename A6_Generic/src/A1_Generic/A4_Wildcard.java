package A1_Generic;

import org.junit.Test;
import java.util.Iterator;
import java.util.List;

/** 通配符(Wildcard: ? )
 *  > List<?> 是 List<String>、List<Object>等各种泛型List的父类
 *  > List<?> 包含的都是Object，但同时又因为不知道 List<?> 的元素类型，不能向其中添加对象。
 *  > 泛型必须是一个未知类型的子类，即需要具体类型。 null值例外，它是任意类型的成员。
 *
 *  使用限制：
 *  > 1. 泛型方法不支持 <?>
 *  > 2. <?>不能用在泛型类的声明上
 *  > 3. <?>不能用在泛型类创建对象上
 *
 *  一、有限制的通配符 指定能使用泛型的上限\下限
 *      ? extends A : 使用时指定的类型必须是继承 A，或者实现 A。 即类型 <= A
 *      ? super A : 使用时指定的类型必须是 A 的父类，或 A 的父接口。 即类型 >= A
 *  二、List<?> list 参数类型
 */

public class A4_Wildcard {

    // 1.泛型参数不具有继承性
    // T<A> T<B> 不具有子父类关系
    // A<T> B<T> 具有子父类关系
    @Test
    public void extendedGeneric(){
        Object o1 = null;
        String o2 = null;
        o1 = o2;

        Object[] o3 = null;
        String[] o4 = null;
        o3 = o4;

        List<Object> list1 = null;
        List<String> list2 = null;
        // list1 list2 不具有子父类关系
//        list1 = list2;
        /*
        反证： list1 = list2;
        list.add(123); 与类型list<String>不匹配
         */
    }

    @Test
    // 2.通配符的使用
    public void Wildcard(){
        List<Object> list1 = null;
        List<String> list2 = null;
        // 1.通配符
        traverse(list1);
        traverse(list2);

        List<?> list3 = null;
        list3 = list1;
        // 任何类型均不可applied <?>
//        list3.add("AA");

        // <?> 可读取

    }
    // 通配符可匹配任意泛型
    private void traverse(List<?> list){
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()){
            // 根父类Object接收任意对象
            Object obj = iterator.next();
        }
    }

    // 有类型上/下 限制条件的通配符
    @Test
    public void wildcardLimited(){
        // extends <=
        // super >=
        // extends接口，实现了接口的实现类
        List<? extends T_Person> list1 = null;
        List<? super T_Person> list2 = null;
        List<? extends Comparable> list3 = null;
    }
}