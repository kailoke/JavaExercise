package a2_ClassClass;

import org.junit.Test;
import a1_ReflectionOverview.Person;

/** java.lang.Class类    本身也是类，描述类的结构的类；Class实例只能由系统建立对象
 * 一、运行程序：
 *  > 1. 编译：源码 javac.ext xxx.java ---> xxx.class 字节码文件
 *  > 2. 加载：java.exe xxx.class 解释运行程序 : ClassLoader将类的字节码文件加载到内存中的过程
 * 二、获取运行时类
 *  > 内存中类称为运行时类，此运行时类作为Class类的实例
 *  > Class类的实例即对应一个运行时类
 *  > 运行时类只有一个，可在内存中缓存一段时间，直到被GC回收(Class对象)
 * 三、哪些类型可以作为Class对象
 *  > class: 外部类，成员（内部类，静态内部类），局部内部类，匿名内部类  Object.class
 *  > interface:接口                            Comparable.class
 *  > enum:枚举类                               ElementType.class
 *  > annotation:注解                           Override.class
 *  > primitive type:基本数据类型                int.class
 *  > []:数组，同元素类型同维度即同一个Class      int[][].class
 *  > void                                      void.class
 *  > Class                                     Class.class
 */

public class A1_ClassClass {
    @Test
    // `获取`Class类实例的四种方法
    public void testClass() throws ClassNotFoundException {
        // 方式一：运行时类的属性: .class
        Class<Person> clas1 = Person.class;
        System.out.println("Class.class: " + clas1.toString());

        // 方式二：运行时类的对象.getClass()
        Person p2 = new Person();
        Class clas2 = p2.getClass();
        System.out.println("Class.getClass(): " + clas2);

        // 方式三：static Class.forName(String className)
        Class clas3 = Class.forName("a1_ReflectionOverview.Person");
        System.out.println("Class.forName(String className): " + clas3);

        System.out.println("clas1 == clas2 :" + (clas1 == clas2));
        System.out.println("clas1 == clas3 :" + (clas1 == clas3));

        // 方式四：类的加载器 Class.class.getClassLoader.loadClass(String className)
        ClassLoader classLoader = Person.class.getClassLoader();
        Class clas4 = classLoader.loadClass("a1_ReflectionOverview.Person");
        System.out.println("clas1 == clas4 :" + (clas1 == clas4));
    }
}
