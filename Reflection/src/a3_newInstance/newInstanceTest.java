package a3_newInstance;

import a1_ReflectionTest.Person;
import org.junit.Test;
import java.util.Random;

/** 通过反射创建运行时类的对象
 *
 *
 */
public class newInstanceTest {

    // 1. Class类实例直接调用 @Deprecated newInstance()，使用空参构造器
    @Test
    public void testNewInstance() throws IllegalAccessException, InstantiationException {
        Class<Person> clas =Person.class;
        // 返回Object o，涉及 无空参构造器 及 无访问权限 问题
        Person p = clas.newInstance();
        System.out.println(p);
    }

    @Test
    // 反射的动态性
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
    /*
    创建全类名类的对象
     */
    private Object getInstance(String classPath) throws Exception {
        Class<?> aClass = Class.forName(classPath);
        return aClass.newInstance();
    }
}
