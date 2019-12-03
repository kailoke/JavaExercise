package a4_getDetail;

import org.junit.Test;

import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/** 构造器不是方法
 */

public class a2_MethodTest {
    @Test
    public void testMethod(){
        Class<Person> aClass = Person.class;
        
        // getMethods() 获取运行时类及其父类所有声明为public的方法
        Method[] methods = aClass.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }

        System.out.println("**********************");

        // getDeclaredMethods() 获取运行时类中声明的所有方法
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method m : declaredMethods) {
            System.out.println(m);
        }
    }

    @Test
    /*
    @Xxxx
    权限修饰符   返回值类型   方法名(参数类型1 形参名1,...) throws XxxException
     */
    public void testHead(){
        Class<Person> aClass = Person.class;

        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method m : declaredMethods) {
            // 1.获取注解   注解声明周期RUNTIME的才能获取
            Annotation[] annotations = m.getAnnotations();
            for ( Annotation anno: annotations) {
                System.out.println(anno);
            }

            // 2.权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + "\t");

            // 3.返回值类型
            System.out.print("ReturnType:" + m.getReturnType() + "\t");

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
                    if (i==exceptionTypes.length-1){
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
