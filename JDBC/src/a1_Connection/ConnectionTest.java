package a1_Connection;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {
    @Test
    // 1.通过第三方驱动实例获得连接对象
    public void testConnection1() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();

        //            主协议:子协议
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT";
        //
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","shaven");

        Connection connect = driver.connect(url, info);

        System.out.println(connect);
    }

    @Test
    // 2.通过反射获得第三方驱动类实例，获得更好的可移植性
    public void testConnection2() throws Exception {
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","shaven");

        // 反射获得驱动类实例
        Class<?> clazz = Class.forName("com.mysql.cj.jdbc.Driver");

        Driver driver = (Driver) clazz.getDeclaredConstructor().newInstance();
        Connection connect = driver.connect(url, info);

        System.out.println(connect);
    }

    @Test
    //使用DriverManager，注册driver get连接实例
    public void testConnection3() throws Exception {
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","shaven");

        // 反射获得驱动类实例
        Class<?> clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.getDeclaredConstructor().newInstance();
        // 注册驱动
        DriverManager.registerDriver(driver);
        // 获得连接对象
        Connection connection = DriverManager.getConnection(url, info);

        System.out.println(connection);
    }

    @Test
    // 反射时mysqlDriver类加载时静态注册自身实例
    public void testConnection4() throws Exception{
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","shaven");

        // 类加载时，mysqlDriver静态代码块调用DriverManager注册自类实例
        Class.forName("com.mysql.cj.jdbc.Driver");  // services 包含驱动类地址
        Connection connection = DriverManager.getConnection(url,info);

        System.out.println(connection);
    }

    @Test
    // 封装信息至配置文件
    // > 1.实现 数据和代码 的解耦
    // > 2.修改配置文件避免源码重新打包
    public void testConnection5() throws Exception {
        // 读取配置文件
        // 类加载器 默认路径为 module/src/
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driverClass = properties.getProperty("driverClass");

        // 注册并并获得连接实例
        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url,user,password);

        System.out.println(connection);
    }
}
