package a13_Druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DruidPool {
    private static DataSource SOURCE = null;

    static {
        try {
            Properties properties = new Properties();
            properties.load(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties")));
            SOURCE = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnetion() throws SQLException {
        return SOURCE.getConnection();
    }
}
