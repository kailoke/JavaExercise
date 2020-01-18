package a4_TransactionIsolation;

import org.junit.Test;

import a0_JDBCUtil.JDBCUtil;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

/** Transaction 事务，在一个连接中执行的多条语句
 *  > ACID
 *  > 1.1 DDL && DCL：一旦执行，都会自动提交
 *  > 1.2 DML：默认自动提交，SetAutoCommit=false 关闭自动提交
 *  > 1.3 关闭连接 && 会话异常退出 提交事务
 *        底层DQL/DML执行逻辑`不要关闭连接`，让上层调用者选择连接关闭时机
 */

public class A1_TransactionSQL {
    @Test
    // 模拟事务：AA 给 BB 转账200的过程中程序发生异常终止
    public void simulateTransaction(){
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            // 1.取消连接的DML自动提交功能
            connection.setAutoCommit(false);
            // 2.1 执行事务单元1
            String sql_out = "update user_table set balance = balance - 200 where user = ?";
            modify(connection,sql_out,"AA");

//            System.out.println(10 / 0);   // 模拟异常

            // 2.2 执行事务单元2
            String sql_in = "update user_table set balance = balance + 200 where user = ?";
            modify(connection,sql_in,"BB");

            // 3.提交事务
            connection.commit();
            System.out.println("转账成功");
        } catch (Exception e) {
            // else：回滚数据
            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // finally：恢复连接的 自动提交 状态，连接池中不会关闭连接
            try {
                if (connection != null)
                    connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 事务成功提交或成功回滚后，执行连接关闭
            JDBCUtil.close(connection,null);
        }
    }

    // 一、连接作为参数的 泛型查询
    public static<T> ArrayList<T> query(Connection c, Class<T> clazz, String sql, Object ... args) {
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
            // 不关闭连接
            JDBCUtil.close(null,ps,resultSet);
        }
        return null;
    }

    // 二、连接作为参数的 参数列表DML
    public static void modify(Connection c, String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            // 1.Precompile
            ps = c.prepareStatement(sql);
            // 2.SetValue
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }
            // 3. 执行更新
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 不关闭连接
            JDBCUtil.close(null,ps);
        }
    }
}
