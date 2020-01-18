package a7_ConnectionPool;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

public class DruidTest {
    @Test
    public void getDruid() throws Exception {
        Properties prop = new Properties();
        prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties"));
        DataSource source = DruidDataSourceFactory.createDataSource(prop);

        Connection conn = source.getConnection();
        System.out.println(conn);

    }
}
