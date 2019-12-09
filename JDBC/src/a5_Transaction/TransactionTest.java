package a5_Transaction;

import a0_util.JDBCUtil;
import org.junit.Test;

import java.sql.*;

/** Transaction
 * 一、自动提交
 *  > 1.1 DDL：一旦执行，都会自动提交
 *  > 1.2 DML：默认自动提交，SetAutoCommit=false 关闭自动提交
 *  > 1.3 默认关闭连接时，自动提交
 */
public class TransactionTest {
    // 传入连接，仅执行语句
    public static int txModify(Connection c,String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            // 1.Precompile
            ps = c.prepareStatement(sql);
            // 2.SetValue
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }
            // 3. 执行
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps);
        }
        return 0;
    }

    @Test
    public void testTxModify(){
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            // 取消连接的DML自动提交功能
            connection.setAutoCommit(false);

            String sql_out = "update user_table set balance = balance - 200 where user = ?";
            txModify(connection,sql_out,"AA");

//            System.out.println(10 / 0);

            String sql_in = "update user_table set balance = balance + 200 where user = ?";
            txModify(connection,sql_in,"BB");

            System.out.println("转账成功");
            connection.commit();
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
            // 恢复AutoCommit默认状态，连接池中不会关闭连接
            try {
                if (connection != null)
                    connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JDBCUtil.close(connection,null);
        }
    }
}
