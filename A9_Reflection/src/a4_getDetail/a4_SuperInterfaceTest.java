package a4_getDetail;

import org.junit.Test;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class a4_SuperInterfaceTest {
    @Test
    public void testSuper(){
        Class<Person> aClass = Person.class;

        // 1.获取运行时类的父类
        Class<? super Person> superclass = aClass.getSuperclass();
        System.out.println(superclass);

        // 2.获取运行时类泛型父类
        Type genericSuperclass = aClass.getGenericSuperclass();
        System.out.println(genericSuperclass);

        // 3.获取运行时泛型父类泛型参数
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println(actualTypeArguments[0].getTypeName());
    }

    @Test
    public void testInterface(){
        Class<Person> personClass = Person.class;

        // 1.获取运行时类接口
        Class<?>[] interfaces = personClass.getInterfaces();
        for (Class c : interfaces) {
            System.out.println(c);
        }
        System.out.println("*****************");
        // 2.获取运行时类父类接口....
        Class<?>[] interfaces1 = personClass.getSuperclass().getInterfaces();
        for (Class c : interfaces1) {
            System.out.println(c);
        }
    }
}
