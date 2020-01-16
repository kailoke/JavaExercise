package a12_ConnectionPool.a1_C3P0_DBCP;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPTest {
    @Test
    // 方式一：硬编码
    public void testGetConnection() throws SQLException {
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost/test?serverTimezone=GMT");
        source.setUsername("root");
        source.setPassword("shaven");

        source.setInitialSize(10);
        source.setMaxActive(10);

        Connection conn = source.getConnection();
        System.out.println(conn);
    }

    @Test
    public void testXML() throws Exception {
        Properties prop = new Properties();
        prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream("dpcp.properties"));
        DataSource source = BasicDataSourceFactory.createDataSource(prop);

        Connection connection = source.getConnection();
        System.out.println(connection);
    }
}
