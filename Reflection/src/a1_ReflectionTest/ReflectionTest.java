package a1_ReflectionTest;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
    @Test
    // 一、NoReflection
    public void  testNoReflection(){
        // 1.实例化对象
        Person p1 = new Person("Tom", 12);
        // 2.通过对象，调用其内部属性、方法
        p1.age = 10;
        p1.show();
        System.out.println(p1);
        // 3!在Person类外部，不能通过Person类对象调用其内部private结构。
    }

    @Test
    // 二、withReflection
    public void testReflection() throws Exception {
        // 获取运行时类
        Class clas = Person.class;
        // 1.通过反射，创建Person类对象
        Constructor constructor = clas.getConstructor(String.class,int.class);
        Object obj = constructor.newInstance("Tom", 12);

        System.out.println("obj:" + obj);
        Person p = (Person)obj;
        System.out.println("p:" + p);

        // 2.通过反射，调用对象指定的属性、方法
        Field age = clas.getDeclaredField("age");
        age.set(p,10);
        System.out.println("p field:" + p);
        // 调用方法
        Method method = clas.getDeclaredMethod("show");
        method.invoke(p);

        System.out.println("**************************************");

        // 3.通过反射，调用对象private结构
        Constructor cons1 = clas.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p2 = (Person)cons1.newInstance("Bob");
        System.out.println("p2 private constructor:" + p2);

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
