package a4_ClassStructures;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/** 运行时类的方法
 *  > 1. Method[] getMethods() : 获得Class对象类或接口的 public 方法[]
 *  > 2. Method[] getDeclaredMethods() : 获得Class对象类或接口声明的 所有 方法[]
 *
 * 一、Method类常用方法
 *   public int getModifiers() : int modifiers
 *   public Class<?> getReturnType() ： Class<?>[] returnType
 *   public Class<?>[] getParameterTypes() ： Class<?>[] parameterTypes
 *   public Class<?>[] getExceptionTypes() ： Class<?>[] exceptionTypes
 *   public Annotation[] getAnnotations() : annotation[] (Retention="RUNTIME")
 *   public Annotation[] getDeclaredAnnotations() : annotation[] (Retention="RUNTIME")
 *
 * 二、方法结构：
 *  > 1. @Annotation   annotation[]
 *  > 2. 权限修饰符     int modifiers
 *  > 3. 返回值类型     Class<?> type
 *  > 4. 方法名         string
 *  > 5. 参数列表      Class<?> parameterTypes
 *  > 6. 异常类型      Class<?> exception
 */

public class a4_MethodStructure {
    @Test
    // 一、获得运行时类的 Method[] 的两种方式
    public void getMethods(){
        Class<Person> aClass = Person.class;
        
        // 1. getMethods() 获取运行时类及其父类所有声明为public的方法
        Method[] methods = aClass.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }

        System.out.println("**********************");

        // 2.getDeclaredMethods() 获取运行时类中声明的所有方法
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method m : declaredMethods) {
            System.out.println(m);
        }
    }

    @Test
    public void getMethodDetails(){
        Class<Person> aClass = Person.class;
        Method[] declaredMethods = aClass.getDeclaredMethods();

        for (Method m : declaredMethods) {
            // 1.获取注解   注解声明周期RUNTIME的才能获取
            Annotation[] annotations = m.getAnnotations();
            for ( Annotation anno: annotations) {
                System.out.println(anno);
            }

            // 2.权限修饰符
            int modifiers = m.getModifiers();
            System.out.print(modifiers + "\t");


            // 3.返回值类型
            Class<?> returnType = m.getReturnType();
            System.out.print(returnType + "\t");

            // 4.方法名
            System.out.print(m.getName());

            // 5.形参列表
            System.out.print("(");
            Class<?>[] parameterTypes = m.getParameterTypes();
            if(parameterTypes.length != 0){
                for ( Class param: parameterTypes) {
                    System.out.print(param.getName() + " args,");
                }
            }
            System.out.print(") ");

            // 6.异常
            Class<?>[] exceptionTypes = m.getExceptionTypes();
            if (exceptionTypes.length != 0){
                System.out.print("throws ");
                for (int i = 0; i < exceptionTypes.length;i++){
                    if (i == exceptionTypes.length-1){
                        System.out.println(exceptionTypes[i].getName());
                        break;
                    }
                    System.out.print(exceptionTypes[i].getName() + ",");
                }
            }
            System.out.println();
        }
    }
}
