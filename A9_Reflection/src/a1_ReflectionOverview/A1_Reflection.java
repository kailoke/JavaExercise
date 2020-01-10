package a1_ReflectionOverview;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** Reflection 反射，`动态语言`的关键
 * 一、作用：反射允许程序在执行期间借助Reflection API 取得任何类的内部信息，并能直接操作任意对象的内部属性或方法
 * 二、原理：ClassLoader加载类后，在MethodArea创建了Class类型的实例(一个类只有一个Class实例)
 * 三、流程： 某类实例 ---> getClass() ---> 指向Heap中代表MethodArea中的Class类实例
 *      ---> 获得Class类实例的各结构实例，将类对象作为此Class类结构实例的参数应用
 *
 * 四、反射机制的功能应用
 *   在运行时判断任意一个对象所属的类
 *   在运行时构造任意一个类的对象         java.lang.reflect.Constructor
 *   在运行时判断任意一个类所具有的成员变量和方法 java.lang.reflect.Field | java.lang.reflect.Method
 *   在运行时获取泛型信息
 *   在运行时调用任意一个对象的成员变量和方法
 *   在运行时处理注解
 *   生成动态代理
 */

public class A1_Reflection {
    @Test
    // 一、NoReflection
    public void  testNoReflection(){
        // 1.实例化对象
        Person p1 = new Person("Tom", 12);
        // 2.通过对象，调用其内部属性、方法
        p1.age = 10;
        p1.show();
        System.out.println(p1);
        // 3.在Person类外部，不能通过Person类对象调用其内部private结构。
    }

    @Test
    // 二、withReflection
    public void testReflection() throws Exception {
        // 获取运行时类
        Class<Person> clas = Person.class;
        // 1.通过反射获得指定类参数的 构造器
        Constructor<Person> constructor = clas.getConstructor(String.class, int.class);
        Object obj = constructor.newInstance("Tom", 12);

        System.out.println("obj: " + obj);
        Person p = (Person)obj;
        System.out.println("(Person)obj: " + p);

        // 2.通过反射，调用对象指定的属性、方法
        Field age = clas.getDeclaredField("age");
        age.set(p,10);
        System.out.println("p field.set(): " + p);
        // 调用方法
        Method method = clas.getDeclaredMethod("show");
        method.invoke(p);

        System.out.println("**************************************");

        // 3.通过反射，调用对象private结构
        Constructor<Person> cons1 = clas.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p2 = cons1.newInstance("Bob");
        System.out.println("p2 private constructor: " + p2);

        Field name = clas.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p2,"Jerry");
        System.out.println("p private field:" + p2);

        Method privateMethod = clas.getDeclaredMethod("showNation", String.class);
        privateMethod.setAccessible(true);
        String str = (String)privateMethod.invoke(p2,"中国");
        System.out.println("return :" + str);
    }
}
