package a12_C3P0_DBCP;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;

public class C3P0Test {
    @Test
    // 方式一：硬编码
    public void testGetConnection() throws Exception {
        // 数据源组合池
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/test?serverTimezone=GMT");
        cpds.setUser("root");
        cpds.setPassword("shaven");
        // 设置数据库连接池的属性 TODO默认最小15个？
        cpds.setInitialPoolSize(10);
        //....

        // 获取连接
        Connection conn1 = cpds.getConnection();
        // test
        ArrayList<Connection> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            System.out.println(i + "成功获取连接");
            list.add(cpds.getConnection());
        }
        list.forEach(System.out::println);

        // 销毁连接池，服务器端并不会进行此操作
        DataSources.destroy(cpds);
    }

    @Test
    // 方式二：XML配置文件
    public void testProperties(){
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");


    }
}
