package a5_FieldMethodSpecified;
import a4_getDetail.Person;

import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 获取指定属性   Field.set(Object,Value)  Field.get(Object)
 * 调用指定方法   Method.invoke(Object,args...)
 *                  > invoke()返回值 = 实际调用方法的返回值；无返回值则=null
 * static Field || Method 可被运行时类直接调用
 */

public class SpecifyFieldMethodTest {
    @Test
    // 调用运行时类中指定的属性
    public void testField() throws Exception {
        Class<Person> personClass = Person.class;
        // 1.创建运行时类的对象
        Person person = personClass.newInstance();

        // 2.1获取运行时类继承树public属性
        Field id = personClass.getField("id");// public
        // 2.2获取运行时类的声明属性
        Field age = personClass.getDeclaredField("age");
        age.setAccessible(true);    // 访问控制符的权限: set AND get

        // 3.设置运行时类的属性
        id.set(person,1001);
        age.set(person,15);

        // 4获取运行时类的属性
        int pID = (int)id.get(person);
        int pAge = (int) age.get(person);

        System.out.println("Field.get(Person):" + pID);
        System.out.println(pAge);
    }

    @Test
    public void testMethod() throws Exception {
        Class<Person> personClass = Person.class;
        Person person = personClass.newInstance();

        // 获取运行时类的指定非静态方法
        // getDeclaredMethod(String methodName, args...) 参数列表
        Method show = personClass.getDeclaredMethod("show", String.class);
        show.setAccessible(true);
        String str = (String) show.invoke(person,"CHN");
        System.out.println(str);

        // 获取运行时类的指定静态方法
        Method show2 = personClass.getDeclaredMethod("show2");
        show2.setAccessible(true);
        // static方法运行时类即可直接调用
        show2.invoke(null);
    }
}
