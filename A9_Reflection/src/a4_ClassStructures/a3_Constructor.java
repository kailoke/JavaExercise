package a4_ClassStructures;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/** 运行时类的构造器 ： 构造器无法继承
 *  > 1.getConstructors() : Class对象类的 public 构造方法[]
 *  > 2.getDeclaredConstructors() : Class对象类的所有 权限 构造方法[]
 *
 *  Constructor类常用方法：
 *  > 1. public int getModifiers() : 获得权限修饰符
 *  > 2. public String getName() : 获得构造方法名称
 *  > 3. public Class<?>[] getParameterTypes() : 获得参数类型
 */

public class a3_Constructor {
    @Test
    // 1.获得Class对象表示类的 public Constructor<?>[]
    public void getConstructors() {
        Class<Person> aClass = Person.class;
        // Class.getConstructors() 获取""运行时类""中声明为public的构造方法
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor c : constructors) {
            System.out.println(c);
        }
    }

    @Test
    // 2.获得Class对象表示类声明的所有 Modifiers Constructor<?>[]
    public void getDeclaredConstructors() {
        Class<Person> aClass = Person.class;
        // getDeclaredConstructors() 获取运行时类中声明的 所有构造方法
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        for (Constructor c : declaredConstructors) {
            System.out.println("Constructor: " + c);
            System.out.println("Modifiers: " + c.getModifiers());
            System.out.println("Name: " + c.getName());
            System.out.println("ParameterTypes: " + Arrays.toString(c.getParameterTypes()));
            System.out.println();
        }
    }
}
