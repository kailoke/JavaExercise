package a11_DAOWithGeneric;

import a0_bean.Customers;
import a0_util.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;

public class TestGeneric {
    CustomersDAOImpl dao = new CustomersDAOImpl();
    @Test
    public void insert() {
        Connection conn = JDBCUtil.getConnection();
        Customers cust = new Customers("于大飞", "Dafei@F.com", Date.valueOf("2010-9-10"));
        int res = dao.insert(conn,cust);
        JDBCUtil.close(conn,null);
        System.out.println("添加成功");
    }
}
