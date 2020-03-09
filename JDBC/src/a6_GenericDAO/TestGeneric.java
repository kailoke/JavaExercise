package a6_GenericDAO;

import org.junit.Test;
import a0_Bean.Customers;
import a0_JDBCUtil.JDBCUtil;
import java.sql.Connection;
import java.sql.Date;

/** DAO的使用，完全依赖DB-table的实现类对象
 *  > 1. 创建DB-table的实现类对象
 *  > 2. 创建ORM对象
 *  > 3. DB-table!使用连接、对象进行方法调用
 */

public class TestGeneric {
    @Test
    // 插入测试
    public void insert() {
        CustomersDAOImpl dao = new CustomersDAOImpl();

        Connection conn = JDBCUtil.getConnection();

        Customers cust = new Customers("于大飞", "Dafei@F.com", Date.valueOf("2010-9-10"));

        int res = dao.insert(conn,cust);

        JDBCUtil.close(conn,null);
    }
}
