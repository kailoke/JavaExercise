package a4_getDetail;

import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class a1_FieldTest {
    @Test
    // 属性
    public void testField(){
        Class aClass = Person.class;
        // getFields()  获取运行时类及其父类中所有public的属性
        Field[] fields = aClass.getFields();
        for ( Field f: fields) {
            System.out.println(f);
        }

        System.out.println("****************************");

        // getDeclaredFields()  获取运行时类中声明的所有属性
        Field[] declaredFields = aClass.getDeclaredFields();
        for ( Field f: declaredFields) {
            System.out.println(f);
        }
    }

    @Test
    // 权限修饰符 数据类型 变量名
    public void testHead(){
        Class aClass = Person.class;

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field f:declaredFields) {
            // 1.Modifier 类
            int modifiers = f.getModifiers();
            System.out.print(modifiers + ":");
            System.out.print(Modifier.toString(modifiers) + "\t");

            // 2.type()
            Class<?> type = f.getType();
            System.out.print(type.getName() + "\t");

            // 3.变量名
            String name = f.getName();
            System.out.println(name);
        }
    }
}
