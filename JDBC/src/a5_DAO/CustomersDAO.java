package a5_DAO;

import a0_Bean.Customers;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 规范DB Customers表的常用操作：使用对象封装数据
 */

public interface CustomersDAO {
    // 添加cust对象到数据库中
    int insert(Connection conn, Customers cust);

    // 根据id 删除数据
    int deleteById(Connection conn,int id);

    // 根据内存对象，更新数据库中的数据
    int update(Connection conn,Customers cust);

    // 根据id获得单个customer 对象
    Customers getCustomerById(Connection conn,int id);

    // 查询所有记录构成的集合
    List<Customers> getAll(Connection conn);

    // 获得个数
    long getCount(Connection conn);

    Date getMaxBirth(Connection conn);
}
