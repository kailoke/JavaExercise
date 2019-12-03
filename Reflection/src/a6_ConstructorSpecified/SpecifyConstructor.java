package a6_ConstructorSpecified;
import a4_getDetail.Person;

import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
获取指定参数列表的构造器
 */
public class SpecifyConstructor {
    @Test
    public void testConstructor() throws Exception {
        Class<Person> personClass = Person.class;
        // 1.获取指定构造器，指明参数列表
        Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Person person = declaredConstructor.newInstance("刘备");

        System.out.println(person);
    }

    @Test
    // JDK9.0后推荐使用 Class.getDeclaredConstructor().newInstance()
    public void testNonConstructor() throws Exception {
        Class<Person> personClass = Person.class;
        Method show2 = personClass.getDeclaredMethod("show2");
        show2.setAccessible(true);
        show2.invoke(personClass.getDeclaredConstructor().newInstance());
    }
}
