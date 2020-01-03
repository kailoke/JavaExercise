package A1_Generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/** 泛型方法
 * 一、格式 : 声明泛型参数后，在形参列表使用该参数
 *  > [访问权限] <泛型> 返回类型 方法名([泛型 参数名]) 抛出异常
 *
 * 二、泛型方法，可以声明为静态的.泛型参数是在调用方法时确定的，并非在实例化类时确定。
 */

public class A3_GenericMethod {
    public static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
        c.addAll(Arrays.asList(a));
    }

    public static void main(String[] args) {
        Object[] ao = new Object[100];
        Collection<Object> co = new ArrayList<>();
        fromArrayToCollection(ao, co);

        String[] sa = new String[20];
        Collection<String> cs = new ArrayList<>();
        fromArrayToCollection(sa, cs);
        fromArrayToCollection(sa, co);  // T是Object类型，sa是String类型，可以赋值成功。

        Collection<Double> cd = new ArrayList<>();
//         fromArrayToCollection(sa, cd);   // T是Double类，但sa是String类型，编译错误。
    }
}
