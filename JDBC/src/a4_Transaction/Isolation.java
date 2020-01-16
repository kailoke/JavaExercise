package a4_Transaction;

import org.junit.Test;

import a0_Bean.User;
import a0_JDBCUtil.JDBCUtil;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public class Isolation {
    @Test
    public void testTxQuery() throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "select user,password,balance from user_table where user = ?";
        ArrayList<User> cc = txQuery(connection, User.class, sql, "CC");
        // 不提交
        cc.forEach(System.out::println);
    }
    // 一、将连接作为参数传递：泛型方法(Connection,Class<T>,SQL,Objects[])
    public static<T> ArrayList<T> txQuery(Connection c,Class<T> clazz, String sql, Object ... args) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = c.prepareStatement(sql);
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
            JDBCUtil.close(null,ps,resultSet);
        }
        return null;
    }


    @Test
    public void testTxModify() throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "update user_table set balance = ? where user = ?";
        TransactionTest.modify(connection,sql,5000,"CC");

        // 当前线程休眠，不提交
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("修改结束");
    }
}
