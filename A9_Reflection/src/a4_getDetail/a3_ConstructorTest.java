package a4_getDetail;

import org.junit.Test;

import java.lang.reflect.Constructor;

public class a3_ConstructorTest {
    @Test
    public void testConstructor(){
        Class<Person> aClass = Person.class;
        // getConstructors() 获取""运行时类""中声明为public的构造器
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor c :
                constructors) {
            System.out.println(c);
        }

        System.out.println("****************");

        // getDeclaredConstructors() 获取运行时类中声明的所有构造器
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        for (Constructor c :
                declaredConstructors) {
            System.out.println(c);
        }
    }
}
