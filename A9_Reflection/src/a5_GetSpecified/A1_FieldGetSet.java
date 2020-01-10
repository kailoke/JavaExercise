package a5_GetSpecified;

import a4_ClassStructures.Person;
import org.junit.Test;

import java.lang.reflect.Field;

/** 调用(Get\Set)指定属性
 * 一、获得Class类实例
 * 二、获得Class实例的Field类实例 : Field Class[].getField(String filedName)
 *                                Field Class[].getDeclaredField(String filedName)
 * 三、禁用Field实例访问权限检查 : Filed[].setAccessible(true)
 * 四、操作Object实例修改或获取属性
 *  > 设置Object属性 : void Field[].set(Object obj,Object value)
 *  > 获取Object属性 : Object Field[].get(Object obj)
 */

public class A1_FieldGetSet {
    @Test
    // 运行时类中的·指定属性· getField(String name)
    public void testField() throws Exception {
        Class<Person> personClass = Person.class;
        // 1. 创建运行时类的对象
        Person person = personClass.newInstance();

        // 2.1 获取运行时类继承树public属性
        Field id = personClass.getField("id");  // public
        // 2.2 获取运行时类的声明属性
        Field age = personClass.getDeclaredField("age");
        age.setAccessible(true);    // 访问控制符的权限: set AND get

        // 3.1 设置运行时类的属性
        id.set(person,1001);
        age.set(person,15);

        // 3.2 获取运行时类的属性
        int pID = (int)id.get(person);
        int pAge = (int) age.get(person);

        System.out.println("Field[id].get(Person): " + pID);
        System.out.println("Field[age].get(Person): " + pAge);
    }
}
