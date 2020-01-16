package a4_Transaction;

import org.junit.Test;

import a0_JDBCUtil.JDBCUtil;
import java.sql.*;

/** Transaction 事务
 *  > 1.1 DDL && DCL：一旦执行，都会自动提交
 *  > 1.2 DML：默认自动提交，SetAutoCommit=false 关闭自动提交
 *  > 1.3 关闭连接 && 会话异常退出 提交事务
 */

public class TransactionTest {
    @Test
    // 模拟事务
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
            // 回滚数据
            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // 恢复连接的 DML事务 状态，连接池中不会关闭连接
            try {
                if (connection != null)
                    connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JDBCUtil.close(connection,null);
        }
    }
    // 带连接参数的 DML
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
            JDBCUtil.close(null,ps);
        }
    }
}
