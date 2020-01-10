package a2_ClassClass;

import org.junit.Test;
import a1_ReflectionOverview.Person;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 一、类的加载过程，当程序主动使用某个类(的结构)时，该类还未加载，则通过如下三个步骤来对该类进行初始化
 *  > 1.类的加载(Load)  需要类的加载器参与
 *      > 类的class文件加载到内存中，并将这些静态数据转换成方法区的运行时数据结构
 *      > 后为之在"堆"中创建一个该类的java.lang.Class对象，此对象作为方法区中类数据的访问入口(引用地址)
 *  > 2.类的链接(Link)  将类的二进制数据合并到JRE中
 *      > 验证        确保加载的类信息符合JVM规范
 *      > 准备        为(static)变量分配内存并设置变量默认初始值，分配至 MethodArea
 *      > 解析(链接？) 虚拟机常量池内的符号引用(变量名)替换为直接引用(地址)的过程。
 *  > 3.类的初始化(Initialize)   JVM负责对类进行初始化
 *      > 执行类构造器<clinit>()方法的过程
 *      > 类构造器<clinit>()方法是编译器自动收集类中所有变量的赋值动作和静态代码块中的语句合并产生
 *      > 类构造器是构造类信息的，不是构造该类对象的构造器
 *      > 虚拟机会保证一个类的<clinit>()方法在多线程环境中被正确加锁和同步
 *
 * 二、类的初始化
 *  类的主动引用（一定会发生类的初始化）
 *   当虚拟机启动，先初始化main方法所在的类
 *   new一个类的对象
 *   调用类的静态成员（除了final常量）和静态方法
 *   使用java.lang.reflect包的方法对类进行反射调用
 *   当初始化一个类，如果其父类没有被初始化，则先会初始化它的父类
 *  类的被动引用（不会发生类的初始化）
 *   当访问一个静态域时，只有真正声明这个域的类才会被初始化
 *       当通过子类引用父类的静态变量，不会导致子类初始化
 *   通过数组定义类引用，不会触发此类的初始化
 *   引用常量不会触发此类的初始化（常量在链接阶段就存入调用类的常量池中了）
 *
 * 三、类的加载器分类
 * > 1. Bootstrap ClassLoader 引导类加载器: C++编写，JVM自带的类加载器，负责JAVA平台核心库的加载。该加载器无法直接获取
 * > 2. Platform ClassLoader 平台类加载: 负责加载jre/lib/ext目录下的jar包或-D java.ext.dirs 指定目录下的jar包
 * > 3. System ClassLoader 系统类加载器: 负责java -classpath 或 -D java.class.path 下类与jar包的加载，最常用
 * > 4. 自定义类的加载器
 */

public class A2_ClassLoader {
    static {
        System.out.println("main所在的类");
    }

    // 一、类的初始化时机测试
    public static void main(String[] args) {
         // 主动引用：一定会导致A和Father的初始化
//        Son a = new Son();
//        System.out.println(Son.m);
//        try {
//            Class.forName("a2_ClassClass.Son");    // ClassLoader根目录当前module/src
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        // 被动引用
        Son[] array = new Son[5];   // 不会导致Son和Father的初始化
        System.out.println();
        System.out.println("Son.b : " + Son.b);  // 引用其父类属性，只会初始化Father
        System.out.println();
        System.out.println("Son.M : " + Son.M);  // 引用常量池，不会导致Son和Father的初始化
    }

    @Test
    // 二、ClassLoader分类 :
    public void classLoaderClassify(){
        // 1.自定义类使用AppClassLoader进行加载
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("System ClassLoader:" + systemClassLoader);
        ClassLoader classLoaderX = Person.class.getClassLoader();
        System.out.println("系统类加载器 == 自定义类.getClassLoader(): " + (systemClassLoader == classLoaderX));

        // 2.调用系统类加载器.getParent()：获取平台类加载器PlatformClassLoader
        ClassLoader extensionClassLoader = systemClassLoader.getParent();
        System.out.println("Platform ClassLoader: " + extensionClassLoader);

        // 3.1 Bootstrap ClassLoader 负责加载java的核心类库，无法被获取
        // 调用Extension ClassLoader的getParent()，获取null
        ClassLoader bootstrapClassLoader = extensionClassLoader.getParent();
        System.out.println("Bootstrap ClassLoader: " + bootstrapClassLoader);   // null

        // 3.2 引导类加载器，负责加载Java平台核心库
        ClassLoader stringClassLoader = Object.class.getClassLoader();  // String int[] Class
        System.out.println("Core.class.getClassLoader(): " + stringClassLoader);  // null
    }

    @Test
    // 三、ClassLoader.getResourceAsStream()读取src下的配置文件
    public void testProperties() throws IOException {
        Properties properties = new Properties();

        // 方式一  JunitTest路径相对于module;main相对于Project
        FileInputStream fis = new FileInputStream("src/test.properties");
//        properties.load(fis);

        // 方式二 ClassLoader对象.getResourceAsStream(String path)
        // 类加载器路径相对于 module/src
        ClassLoader classLoader = A2_ClassLoader.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("test.properties");
        assert is != null;
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        System.out.println("user=" + user + ",password=" + password);
    }
}

class Father {
    static int b = 2;
    static {
        System.out.println("父类被加载");
    } }
class Son extends Father {
    static {
        System.out.println("子类被加载");
        m = 300;
    }
    static int m = 100;
    static final int M = 1;
}
