package a5_GetSpecified;
import a4_ClassStructures.Person;

import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/** 指定参数列表的`泛型`构造器
 * 一、获得Class类实例
 * 二、获得Class实例的Constructor<T>类实例，Constructor<T>泛型构造器，指明其所构造的实例类型
 *  > Constructor<T> Class[].getConstructor(Class<?>... argsType)
 *  > Constructor<T> Class[].getDeclaredConstructor(Class<?>... argsType)
 * 三、禁用Method实例访问权限检查 : Method[].setAccessible(true)
 * 四、操作Object实例调用方法 : Object Method[].invoke(Object Invoker,Object[] args)
 */
public class A2_Constructor {
    @Test
    // 一、运行时类的·指定构造方法·
    public void testConstructor() throws Exception {
        Class<Person> personClass = Person.class;
        // 1.获取指定·泛型·构造器，指明参数列表 Class<?>...paraType
        Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Person person = declaredConstructor.newInstance("刘备");

        System.out.println(person);
    }

    @Test
    // 二、JDK9.0后推荐使用 Class.getDeclaredConstructor().newInstance() 获得空参实例
    public void testNonConstructor() throws Exception {
        Class<Person> personClass = Person.class;
        Method show2 = personClass.getDeclaredMethod("show2");
        show2.setAccessible(true);
        // 空参实例 : Class[].getDeclaredConstructor().newInstance()
        show2.invoke(personClass.getDeclaredConstructor().newInstance());
    }
}
