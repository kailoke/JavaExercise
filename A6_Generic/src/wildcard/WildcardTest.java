package wildcard;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class WildcardTest {

    // 1.泛型参数具有继承性的，被用作其他类的泛型参数时，其他类的实例化对象不具有子父类关系
    // T<A> T<B> 不具有子父类关系
    // A<T> B<T> 具有子父类关系
    @Test
    public void GenericExtendsTest(){
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

    // 通配符可匹配任意泛型
    public void traverse(List<?> list){
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()){
            // 根父类Object接收任意对象
            Object obj = iterator.next();
        }
    }
    @Test
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

    // 有限制条件的通配符
    @Test
    public void wildcardLimited(){
        // extends <=
        // super >=
        // extends接口，实现了接口的实现类
        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;
        List<? extends Comparable> list3 = null;
    }

}
