package a2_Class;

import org.junit.Test;
import a1_ReflectionTest.Person;

/** java.lang.Class类
 * 一、运行程序：
 *  > 1. 编译：源码 javac.ext xxx.java ---> xxx.class 字节码文件
 *  > 2. 加载：java.exe xxx.class 解释运行程序 : 将类的字节码文件加载到内存中的过程
 * 二、运行时类   获取
 *  > 内存中类称为运行时类，此运行时类作为Class类的实例
 *  > Class类的实例即对应一个运行时类
 *  > 运行时类只有一个，可在内存中缓存一段时间，直到被GC回收(Class对象)
 * 三、哪些类型可以作为Class对象
 *  > class: 外部类，成员（内部类，静态内部类），局部内部类，匿名内部类
 *  > interface:接口                            Comparable.class
 *  > []:数组，同元素类型同维度即同一个Class      int[][].class
 *  > enum:枚举类                               ElementType.class
 *  > annotation:注解                           Override.class
 *  > primitive type:基本数据类型                int.class
 *  > void                                      void.class
 *  > Class                                     Class.class
 * 四、类的加载过程
 *  > 1.类的加载(Load)  类的加载器
 *      > 类的class文件加载到内存中，并将这些静态数据转换成方法区的运行时数据结构
 *      > 后为之在"堆"中创建一个java.lang.Class对象，此对象作为方法区中类数据的访问入口
 *  > 2.类的链接(Link)  将类的二进制数据合并到JRE中
 *      > 验证        确保加载的类信息符合JVM规范
 *      > 准备        为(static)变量分配内存并设置变量默认初始值
 *      > 解析(链接？)   虚拟机常量池内的符号引用(变量名)替换为直接引用(地址)的过程。
 *  > 3.类的初始化(Initialize)   JVM负责对类进行初始化
 *      > 类构造器<clinit>()收集类中所有变量的赋值动作和静态代码块中的语句合并
 */
public class ClassTest {
    @Test
    // ""获取""Class类实例的四种方法
    public void testClass() throws ClassNotFoundException {
        // 方式一：运行时类的属性: .class
        Class<Person> clas1 = Person.class;
        System.out.println(clas1.toString());

        // 方式二：运行时类的对象
        Person p2 = new Person();
        Class clas2 = p2.getClass();
        System.out.println(p2.getClass());
        System.out.println(clas2);

        // 方式三：Class静态方法：forName(String className)
        Class clas3 = Class.forName("a1_ReflectionTest.Person");
        System.out.println(clas3);

        System.out.println("clas1 == clas2 :" + (clas1 == clas2));
        System.out.println("clas1 == clas3 :" + (clas1 == clas3));

        // 方式四：类的加载器 ClassLoader
        ClassLoader classLoader = ClassTest.class.getClassLoader();
        Class clas4 = classLoader.loadClass("a1_ReflectionTest.Person");
        System.out.println("clas1 == clas4 :" + (clas1 == clas4));
    }
}
