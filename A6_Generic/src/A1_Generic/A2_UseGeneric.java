package A1_Generic;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

/** 自定义泛型结构
 * 一、泛型声明：例如 interface List<T> 和 class GenTest<K,V>
 *     其中T\K\V不代表值，而是表示类型。
 *     T == Type ; E == Element ; K == Key ; V == Value
 *
 * 二、泛型实例化
 *  > 一定在 类名 后指定类型参数的实参
 *  > 泛型参数只能是 类；基本数据类型使用包装类。(因为泛型实参可能会实例化对象)
 *  > 接口或抽象类不可创建类型类的对象，它们本身也不能实例化对象...
 *  > 带有泛型参数的类或接口实例化时若未指定泛型，则默认类型类似为Object，但不完全等同
 *  > 静态方法中不能使用 类泛型参数，类泛型结构只能在实例结构中使用
 *    类泛型参数是实例化时传入；静态方法只有一份，不能多次实例化
 *  > Exception不能使用泛型
 *  > 不能 new E[]。但可以 E[] e = (E[])new Object[capacity]
 *
 * 三、泛型类的构造器，不需要再声明泛型参数。 这个泛型类的构造器是错误的： public Person<E>(){}
 *
 * 四、父类有泛型，子类可以选择 保留泛型 || 指定泛型类型 || 擦除泛型
 */
public class A2_UseGeneric<T> {
    // 泛型类型定义变量
    private T info;
    // 泛型类型定义方法
    public T getInfo() {
        return info;
    }
    // 泛型类型的构造器
    public A2_UseGeneric(){
    }


    public static void main(String[] args) {

    }
}

class Father<T1, T2> { }

// 子类不保留父类的泛型
// 1)没有类型，擦除
class Son1 extends Father { }   // 等价于class Son extends Father<Object,Object>{ }
// 2)具体类型
class Son2 extends Father<Integer, String> { }

// 子类保留父类的泛型
// 1)全部保留
class Son3<T1, T2> extends Father<T1, T2> { }
// 2)部分保留
class Son4<T2> extends Father<Integer, T2> { }