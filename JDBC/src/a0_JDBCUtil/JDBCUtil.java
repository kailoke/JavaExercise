package a0_JDBCUtil;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

/**
 * 数据库通用操作
 * > getConnection() : 返回java.sql.Connection 实例
 * > close() : 关闭SQL资源(Connection || Statement || ResultSet)
 */

public class JDBCUtil {
    // return instance of java.sql.Connection
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Properties SQLinfo = new Properties();
            SQLinfo.load(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties")));
            String url = SQLinfo.getProperty("url");
            String driverClass = SQLinfo.getProperty("driverClass");
            String user = SQLinfo.getProperty("user");
            String password = SQLinfo.getProperty("password");
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("JDBCUtil：db connected");
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // close() overload
    private static void close(Connection c){
        if (c != null){
            try {
                c.close();
                System.out.println("JDBCUtil：db disconnected");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // close() overload
    public static void close(Connection c, Statement p){
        close(c);
        if (p != null){
            try {
                p.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // close() overload
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
