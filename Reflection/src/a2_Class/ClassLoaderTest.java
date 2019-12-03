package a2_Class;

import org.junit.Test;
import a1_ReflectionTest.Person;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClassLoaderTest {
    @Test
    public void testCL(){
        // 自定义类，使用System ClassLoader进行加载
        ClassLoader classLoader1 = ClassLoaderTest.class.getClassLoader();
        System.out.println("System ClassLoader:" + classLoader1);
        ClassLoader classLoaderx = Person.class.getClassLoader();
        System.out.println("测试系统类加载器:" + (classLoader1 == classLoaderx));

        // 调用系统类加载器的getParent()：获取扩展类加载器
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println("Extension ClassLoader:" + classLoader2);

        // Bootstrap ClassLoader 负责加载java的核心类库，无法被获取
        // 调用Extension ClassLoader的getParent()，无法获取引导类加载器
        ClassLoader classLoader3 = classLoader2.getParent();
        System.out.println(classLoader3);   // null

        // 引导类加载器
        ClassLoader classLoader4 = String.class.getClassLoader();
        System.out.println(classLoader4);   // null
    }

    // 读取配置文件
    @Test
    public void testProperties() throws IOException {
        Properties properties = new Properties();

        // 方式一  JunitTest路径相对于module;main相对于Project
        FileInputStream fis = new FileInputStream("src/test.properties");

        // 方式二 ClassLoader对象.getResourceAsStream(String path)
        // 路径相对于 module/src
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("test.properties");

//        properties.load(fis);
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        System.out.println("user=" + user + ",password=" + password);

    }
}
