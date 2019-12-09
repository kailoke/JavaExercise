package a11_DAOWithGeneric;

import a0_bean.Customers;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/*
    具体实现Customers表的方法
 */
public class CustomersDAOImpl extends BaseDAO<Customers> implements CustomersDao {
    @Override
    public int insert(Connection conn, @NotNull Customers cust) {
        String sql = "insert into customers(name,email,birth) values (?,?,?)";
        return update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth());
    }

    @Override
    public int deleteById(Connection conn, int id) {
        String sql = "delete from customers where id = ?";
        return update(conn, sql, id);
    }

    @Override
    public int update(Connection conn, @NotNull Customers cust) {
        String sql = "update customers set name=?,email=?,birth=? where id=?";
        return update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getId());
    }

    @Override
    public Customers getCustomerById(Connection conn, int id) {
        String sql = "select id,name,email,birth from customers where id = ?";
        return queryInstance(conn, sql, id);
    }

    @Override
    public List<Customers> getAll(Connection conn) {
        String sql = "select id,name,email,birth from customers";
        return queryForList(conn, sql);
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
