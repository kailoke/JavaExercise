package a2_PreparedStatement;

import org.junit.Test;

import a0_Bean.Customers;
import a0_Bean.Order;
import a0_JDBCUtil.JDBCUtil;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

/**
 * 运行时类作为泛型参数，调用者传入Class信息
 */

public class A3_GenericQuery {
    @Test
    public void testGenericQuery(){
        String sql = "select id,name,email,birth from customers where id <= ?";
        ArrayList<Customers> customers = genericQuery(Customers.class, sql, 10);
        customers.forEach(System.out::println);

        System.out.println("*****************************");

        sql = "select order_id orderId,order_name orderName,order_date orderDate from `order`";
        ArrayList<Order> orders = genericQuery(Order.class, sql);
        orders.forEach(System.out::println);
    }

    // 泛型方法 : 运行时类作为泛型参数，返回该泛型的数组
    private <T> ArrayList<T> genericQuery(Class<T> clazz, String sql, Object ... args) {
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
                // 泛型类的实例
                T temp = clazz.getDeclaredConstructor().newInstance();

                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i+1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(temp,resultSet.getObject(i+1));
                }
                list.add(temp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection,ps,resultSet);
        }
        return null;
    }
}
