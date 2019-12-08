package a2_PreparedStatement;

import a0_bean.Customers;
import a0_util.JDBCUtil;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class QueryCustomers {
    @Test
    // 指定sql 语句查询Customers表
    public void testQuery() {
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
                System.out.println("Object:" + Arrays.toString(data));
                // 2.2 对象对缝
                Customers customer = new Customers(id, name, email, birth);
                System.out.println("customers:" + customer);
            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 3.额外关闭查询资源:resultSet
            JDBCUtil.close(connection,ps,resultSet);
        }
    }

    // 通用sql语句查询 Customers
    private ArrayList<Customers> customersQuery(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i = 0; i < args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            resultSet = ps.executeQuery();
            ResultSetMetaData metaData = ps.getMetaData();
            ArrayList<Customers> list = new ArrayList<>();

            while (resultSet.next()){
                // 反射创建实例
                Customers t = Customers.class.getDeclaredConstructor().newInstance();
                // 获得RS列数
                int columnCount = metaData.getColumnCount();
                for(int i = 0 ; i <columnCount;i++){
                    // 获得列 字段名
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    // 反射 设置实例值
                    Field field = Customers.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,resultSet.getObject(i + 1));
                }
                // 添加实例到列表
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
    public void testCustomersQuery(){
        String sql = "select id,name,email,birth from customers where id <= ?";
        ArrayList<Customers> list1 = customersQuery(sql,10);
        list1.forEach(System.out::println);
    }
}
