package a12_C3P0_DBCP;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DBCPPool {
    // 唯一数据库连接池
    private static DataSource SOURCE = null;
    // 初始化连接池，应增入更多配置信息
    static {
        try {
            Properties properties = new Properties();
            properties.load(Objects.requireNonNull(ClassLoader.getSystemClassLoader().
                    getResourceAsStream("dpcp.properties")));
            SOURCE = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return SOURCE.getConnection();
    }
}
