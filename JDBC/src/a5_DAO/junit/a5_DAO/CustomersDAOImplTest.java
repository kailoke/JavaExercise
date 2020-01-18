package a5_DAO;

import a0_Bean.Customers;
import a0_JDBCUtil.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;

public class CustomersDAOImplTest {
    CustomersDAOImpl dao = new CustomersDAOImpl();
    @Test
    public void insert() {
        Connection conn = JDBCUtil.getConnection();
        Customers cust = new Customers("于小飞", "xiaofei@f.com", Date.valueOf("2010-10-10"));
        int res = dao.insert(conn,cust);
        JDBCUtil.close(conn,null);
        System.out.println("添加成功");
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getCustomerById() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getCount() {
    }

    @Test
    public void getMaxBirth() {
    }
}