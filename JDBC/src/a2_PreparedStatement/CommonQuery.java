package a2_PreparedStatement;

import a0_bean.Customers;
import a0_bean.Order;
import a0_util.JDBCUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public class CommonQuery {
    // 泛型方法 : 泛型作为函数参数列表参数
    private <T> ArrayList<T> commonQuery(Class<T> clazz, String sql, Object ... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0;i < args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            resultSet = ps.executeQuery();
            ResultSetMetaData metaData = ps.getMetaData();
            ArrayList<T> list = new ArrayList<>();

            while (resultSet.next()){
                T t = clazz.getDeclaredConstructor().newInstance();

                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i+1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,resultSet.getObject(i+1));
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection,ps,resultSet);
        }
        return null;
    }

    @Test
    public void testCommonQuery(){
        String sql = "select id,name,email,birth from customers where id <= ?";
        ArrayList<Customers> customers = commonQuery(Customers.class, sql, 10);
        customers.forEach(System.out::println);

        System.out.println("*****************************");

        sql = "select order_id orderId,order_name orderName,order_date orderDate from `order`";
        ArrayList<Order> orders = commonQuery(Order.class, sql);
        orders.forEach(System.out::println);
    }
}
