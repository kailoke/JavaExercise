package a5_GetSpecified;
import a4_ClassStructures.Person;

import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** 调用指定方法
 * 一、获得Class类实例
 * 二、获得Class实例的Method类实例 : Method Class[].getMethod(String methodName,Class<?>... argsType)
 *                                 Method Class[].getDeclaredMethod(String methodName,Class<?>... argsType)
 * 三、禁用Method实例访问权限检查 : Method[].setAccessible(true)
 * 四、操作Object实例调用方法 : Object Method[].invoke(Object Invoker,Object[] args)
 * 五、
 *  > 1. Object 对应原方法的返回值，若原方法无返回值，此时返回null
 *  > 2. 若原方法若为静态方法，此时形参Object obj可为null
 *  > 3. 若原方法形参列表为空，则Object[] args为null
 */

public class A3_MethodInvoke {
    @Test
    // 运行时类中的·指定方法·
    public void testMethod() throws Exception {
        Class<Person> personClass = Person.class;

        // 1.获取运行时类的非静态方法
        Method show = personClass.getDeclaredMethod("show", String.class);
        show.setAccessible(true);
        Person person = personClass.newInstance();  // Object invoker
        String str = (String) show.invoke(person,"CHN");
        System.out.println(str);

        // 2.获取运行时类的指定静态方法
        Method show2 = personClass.getDeclaredMethod("show2");
        show2.setAccessible(true);
        //  static方法 null Object调用
        show2.invoke(null);
    }
}
