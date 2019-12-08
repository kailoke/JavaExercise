package a0_util;


import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

/*
    操作数据库工具类
 */
public class JDBCUtil {
    // 获取连接
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Connection connection = null;

        Properties SQLinfo = new Properties();
        SQLinfo.load(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties")));
        String url = SQLinfo.getProperty("url");
        String driverClass = SQLinfo.getProperty("driverClass");
        String user = SQLinfo.getProperty("user");
        String password = SQLinfo.getProperty("password");
        Class.forName(driverClass);
        connection = DriverManager.getConnection(url, user, password);

        System.out.println("数据库连接完成");
        return connection;
    }

    // 资源关闭
    public static void close(Connection c, Statement p){
        if (c != null){
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (p != null){
            try {
                p.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据库断开连接...");
    }

    public static void close(Connection c, Statement p,ResultSet r) {
        close(c,p);
        if ( r != null){
            try {
                r.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
