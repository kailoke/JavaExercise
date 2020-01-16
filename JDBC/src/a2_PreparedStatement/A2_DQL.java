package a2_PreparedStatement;

import org.junit.Test;

import a0_Bean.Customers;
import a0_JDBCUtil.JDBCUtil;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/** ResultSet ： 执行查询后返回结果集
 * *ResultSet内部遍历 (iterator)，RS的索引和SQL一致，从1开始
 * 一、获得结果集对象：PreparedStatement.executeQuery():ResultSet
 *      > 结果集元数据：ResultMetaData = ResultSet!.getMetaData()
 * 二、移动ResultSet游标：使用ResultSet.next() 判断下一个index是否有对象，有则下移Pointer
 * 三、获取游标所指ResultSet的类型对象的成员属性：ResultSet.getXxx(index):Xxx
 *      > 结果集列数：int column = ResultSetMetaData.getColumnCount()
 *      > 结果集列的字段标签：String label = ResultSetMetaData.getColumnLabel(index)
 */

public class A2_DQL {
    @Test
    // 一、单个结果的 sql查询 语句
    public void queryCustomers() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select `id`,`name`,`email`,`birth` from `customers` where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1,5);

            // 1.执行查询，获得结果集 ResultSet对象
            resultSet = ps.executeQuery();
            // ※※ 处理结果集 ※※
            // 2.ResultSet.next()判断下一个index并移 pointer
            if ( resultSet.next() ){
                // 2.1 获取当前pointer所指的ResultSet对象的成员属性
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                // 2.1 将获得的属性，数组封装
                Object[] data = new Object[]{id,name,email,birth};
                System.out.println("fill Object[]：" + Arrays.toString(data));
                // 2.2 将获得的属性，封装成对象：对象的属性和值构成KV易阅读
                Customers customer = new Customers(id, name, email, birth);
                System.out.println("new Customers()：" + customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close outer resource instance of ResultSet
            JDBCUtil.close(connection,ps,resultSet);
        }
    }

    @Test
    public void customersQuery(){
        String sql = "select id,name,email,birth from customers where id <= ?";
        ArrayList<Customers> list1 = customersQuery(sql,10);
        list1.forEach(System.out::println);
    }
    // 二、多个结果的 sql查询 语句
    // > 获取ResultSetMetaData，使用反射获得运行时类的成员类，设置反射实例的属性为Pointer对象成员属性
    //   将每个单行封装为一个JAVA对象，整个结果集构成 该对象数组
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
            // ps.getMetaData:RSMD结果集元数据，描述结果集的数据
            ResultSetMetaData metaData = ps.getMetaData();
            ArrayList<Customers> list = new ArrayList<>();

            while (resultSet.next()){
                // 反射创建实例
                Customers temp = Customers.class.getDeclaredConstructor().newInstance();
                // 1.获得RS列数，即字段数量 getColumnCount()
                int columnCount = metaData.getColumnCount();
                // 循环处理每个列的字段
                for(int i = 0; i < columnCount; i++){
                    // 2.获得RS pointer对象的index列字段标签 getColumnLabel(index)
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    // 3.反射指定字段，设置实例的成员值
                    Field field = Customers.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(temp,resultSet.getObject(i + 1));
                }
                // 添加实例到列表
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
