package a1_DriverManager;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 一、Java中的数据存储技术
 *  > JDBC 直接访问数据库
 *  > JDO : Java Data Object 持久对象
 *  > 第三方 O/R 工具，如Hibernate/mybatis等
 *  * JDBC是java访问数据库的基石，JDO/Hibernate等更好地封装了JDBC
 *
 * 二、JDBC
 * > 1.开发人员使用 面向应用的API：interfaces in (java.sql,javax.sql)
 * > 2.开发商(DBMS)使用 面向数据库的Driver API，实现这套接口的集合称为JDBC驱动
 *
 * 三、JDBC Process Overview
 * > Oracle,MySQL 使用纯java驱动，为工程导入对应的DBMS驱动(Build Path)
 * > 1. 加载并注册驱动程序
 * > 2. 创建Connection对象
 * > 3. 创建Statement对象
 * > 4. 执行StatementSQL语句
 *      > QUERY : 使用ResultSet对象 --> 关闭ResultSet对象
 *      > DML : {获取 || 没有}返回值
 * > 5. 关闭Statement对象，关闭Connection对象
 *
 * 四、`加载并注册`JDBC驱动底层
 * > 1. ClassLoader加载jar包类：Class.forName("Driver Path");
 * > 2. DriverManger驱动程序管理类：DriverManager.registerDriver(Class DiverPath)
 * > 3. 通常不显示调用上述方法，因Driver接口的实现类都包含了static{invoke DriverManager.registerDriver()}
 *      > 即驱动类在加载时完成自身实例的注册
 */

public class ConnectSQL {
    @Test
    // final : Properties封装数据库连接信息
    // > 数据和代码 解耦，当数据库信息变动时，仅修改配置文件避免源码重新打包
    public void finalConnection() throws Exception {
        // 1.读取配置文件，ClassLoader根路径：module/src/
        InputStream is = ConnectSQL.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        assert is != null;
        properties.load(is);

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        // 2.加载并注册驱动
        String driverClass = properties.getProperty("driverClass");
        Class.forName(driverClass);

        // 3.DriverManager创建连接实例
        Connection connection = DriverManager.getConnection(url,user,password);

        System.out.println(connection);
        // 关闭连接 流对象
        connection.close();
        is.close();
    }

    @Test
    // 一、第三方驱动类的驱动实例，驱动创建连接对象
    public void testConnection1() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();

        //JDBC URL：标识一个被注册的驱动程序，DM通过URL选择对应的JDBC驱动从而建立连接
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT";
                    //主协议:子协议:定位数据库 ip:port/db?params(k=v)
        // 数据访问权限(Properties) : -u -p
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","shaven");

        // Driver类实例创建连接对象
        Connection connect = driver.connect(url, info);

        System.out.println(connect);
    }

    @Test
    // 二、反射获得第三方驱动Class类实例，不出现第三方API调用具有更好的可移植性
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
    // 三、使用DriverManager，注册驱动registerDriver()，创建连接对象getConnection()
    public void testConnection3() throws Exception {
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","shaven");

        Class<?> clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.getDeclaredConstructor().newInstance();

        // 1.DriverManager注册驱动
        DriverManager.registerDriver(driver);
        // 2.DriverManager创建连接对象
        Connection connection = DriverManager.getConnection(url, info);

        System.out.println(connection);
    }

    @Test
    // 四、MySQLDriver类加载时,static{}调用DiverManager.registerDiver()注册自身实例
    public void testConnection4() throws Exception{
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","shaven");

        // 1.MySQLDriver类静态代码向DriverManager注册
        //  * META-INF/services/java.sql.Driver 包含驱动类地址:不是所有的DBMS都默认调用，不能省略
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2.DriverManager创建连接对象
        Connection connection = DriverManager.getConnection(url,info);

        System.out.println(connection);
    }
}
