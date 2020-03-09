package a5_DAO;

import a0_Bean.Customers;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 具体实现DB Customers表的方法：获取对象属性进行自定义操作
 */

public class CustomersDAOImpl extends BaseDAO implements CustomersDAO {
    @Override
    public int insert(Connection conn, Customers cust) {
        String sql = "insert into customers(name,email,birth) values (?,?,?)";
        return update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth());
    }

    @Override
    public int deleteById(Connection conn, int id) {
        String sql = "delete from customers where id = ?";
        return update(conn, sql, id);
    }

    @Override
    public int update(Connection conn, Customers cust) {
        String sql = "update customers set name=?,email=?,birth=? where id=?";
        return update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getId());
    }

    @Override
    public Customers getCustomerById(Connection conn, int id) {
        String sql = "select id,name,email,birth from customers where id = ?";
        return queryInstance(conn, Customers.class, sql, id);
    }

    @Override
    public List<Customers> getAll(Connection conn) {
        String sql = "select id,name,email,birth from customers";
        return queryForList(conn, Customers.class, sql);
    }

    @Override
    public long getCount(Connection conn) {
        String sql = "select count(*) from customers";
        return getValue(conn, sql);
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        String sql = "select max(birth) from customers";
        return getValue(conn, sql);
    }
}
