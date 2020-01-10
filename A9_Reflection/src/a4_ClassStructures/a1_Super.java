package a4_ClassStructures;

import org.junit.Test;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/** 获得运行时类的父类结构
 * > 1. getSuperClass() : 获得运行时类的父类，没有泛型参数
 * > 2. getGenericSuperClass() : 获得运行时类的泛型父类，拥有泛型参数
 *
 * 接口：
 *  Class.getInterfaces() : 运行类实现的接口
 *  Class.getSuperClass.getInterfaces() : 运行时类父类实现的接口
 */
public class a1_Super {
    @Test
    // 一、获得运行时类的父类Class，获得运行时类的泛型父类Class
    public void getSuperClass(){
        Class<Person> aClass = Person.class;

        // 1.获取运行时类的父类，没有泛型参数
        Class<? super Person> superclass = aClass.getSuperclass();
        System.out.println("superClass : " + superclass);

        // 2.获取运行时类泛型父类，拥有泛型参数
        Type genericSuperclass = aClass.getGenericSuperclass();
        System.out.println("genericSuperClass : " + genericSuperclass);

        // 3.获取运行时泛型父类的泛型参数
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println(actualTypeArgument);
        }
    }

    @Test
    // 二、获得运行时类及其父类实现的接口
    public void getSuperInterface(){
        Class<Person> personClass = Person.class;

        // 1.获取运行时类实现的接口
        Class<?>[] interfaces = personClass.getInterfaces();
        for (Class c : interfaces) {
            System.out.println(c);
        }
        System.out.println("*****************");

        // 2.获取运行时类父类实现的接口....
        Class<?>[] interfaces1 = personClass.getSuperclass().getInterfaces();
        for (Class c : interfaces1) {
            System.out.println(c);
        }
    }
}
