package a2_PreparedStatement;

import a0_bean.Customers;
import a0_util.JDBCUtil;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

/*
    查询Customers表
 */
public class QueryCustomers {
    @Test
    public void testOneQuery() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,email,birth from customers where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1,5);

            // 1.执行并返回结果集
            resultSet = ps.executeQuery();
            // 2. ※※ 处理结果集 ※※
            if ( resultSet.next()){ // boolean next(): next有数据则返回 true + 指针下移；否则返回false
                // 获取当前数据的字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                // 2.1 数组封装
                Object[] data = new Object[]{id,name,email,birth};
                // 2.2 对象对缝
                Customers customer = new Customers(id, name, email, birth);
                System.out.println(customer);
            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 3.额外关闭查询资源:resultSet
            JDBCUtil.close(connection,ps,resultSet);
        }
    }

    @Test
    public void testCusQuery() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JDBCUtil.getConnection();

    }
}
