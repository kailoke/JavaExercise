package a3_NewInstance;

import a1_ReflectionOverview.Person;
import org.junit.Test;
import java.util.Random;

/** 通过Class对象创建运行时类的对象
 *  > 类必须有一个无参数的构造器
 *  > 无参构造器的访问权限可以访问
 *
 * 方法：
 *  > Class.newInstance since="9"废弃，推荐通过Constructor实例化对象
 */
public class A1_NewInstance {

    // 1. Class类实例直接调用 @Deprecated newInstance()，使用空参构造器
    @Test
    public void testNewInstance() throws IllegalAccessException, InstantiationException {
        Class<Person> clas = Person.class;
        // 返回Object o，涉及 无空参构造器 及 无访问权限 问题
        Person p = clas.newInstance();
        System.out.println(p);
    }

    @Test
    // 2.反射的动态性体现：将类名作为参数，动态生成类对象
    public void test(){
        int num = new Random().nextInt(3);// 0,1,2
        String classPath = "";
        switch (num){
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                classPath = "java.lang.String";
                break;
            case 2:
                classPath = "a1_ReflectionTest.Person";
                break;
        }
        System.out.println(classPath);
        try {
            Object instance = getInstance(classPath);
            System.out.println(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据类名动态创建实例
     * @param classPath 类名
     * @return  类实例
     * @throws Exception 统一抛出
     */
    private Object getInstance(String classPath) throws Exception {
        Class<?> aClass = Class.forName(classPath);
        return aClass.newInstance();
    }
}
