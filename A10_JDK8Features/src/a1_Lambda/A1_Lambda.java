package a1_Lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

/** Lambda表达式 : 匿名对象
 * 理解：匿名实现类的简洁写法,可以理解为是一段可以传递的代码(将代码传递到抽象方法中)
 * 一、作用：Java中Lambda表达式的本质是作为`函数式接口`的实例，并重写虚方法 (依附于 函数式接口)
 *
 * 二、格式：(抽象方法的形参列表) "->" {重写抽象方法的方法体;};
 *                              ↑ lambda箭头操作符
 * 三、Lambda表达式语法
 *  > " -> " : Lambda操作符 箭头操作符，它将Lambda分为两个部分
 *  > 左侧: Lambda表示式的形参列表
 *      > 无参数 : ()
 *      > 有一个参数，则可省略 () : o1 -> {}
 *      > 参数类型可以省略，因为可右编译器推断，称为`类型推断`(虚方法声明、引用类型声明)
 *  > 右侧：Lambda体，虚方法的具体实现
 *      > Lambda有返回值，直接返回
 *      > lambda体应使用 {} 包裹Statements;
 *      > 只有一条执行语句则省略 return + {}
 */

public class A1_Lambda {
    @Test
    // 匿名内部类(实现类) -> lambda表达式
    public void anonymousType() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("run run run");
            }
        };

        Runnable runnable1 = () -> System.out.println("run run run");
    }

    @Test
    // lambda表达式作为 方法参数
    public void lambdaAsArgs() {
        TreeSet<String> treeSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        TreeSet<String> treeSet1 = new TreeSet<>(
                ((o1, o2) -> o1.compareTo(o2))  // (String::compareTo)
        );
    }
}
