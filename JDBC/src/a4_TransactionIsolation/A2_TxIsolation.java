package a4_TransactionIsolation;

import org.apache.commons.dbutils.DbUtils;
import org.junit.Test;

import a0_Bean.User;
import a0_JDBCUtil.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;

/**
 * 模拟两个会话同时进行时，不同数据库TransactionLevel的结果
 * MySQL默认transactionLevel: RepeatableRead
 */

public class A2_TxIsolation {
    @Test
    // 查询会话
    public void transactionQuery() throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
//        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        System.out.println("Query transaction isolation: " + connection.getTransactionIsolation());
        String sql = "select user,password,balance from user_table where user = ?";

        ArrayList<User> cc = A1_TransactionSQL.query(connection, User.class, sql, "CC");    // 2000
        cc.forEach(System.out::println);
        DbUtils.close(connection);
    }

    @Test
    // 修改会话
    public void transactionModify() throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        System.out.println("Modify transaction isolation: " + connection.getTransactionIsolation());
        connection.setAutoCommit(false);
        String sql = "update user_table set balance = ? where user = ?";
        A1_TransactionSQL.modify(connection,sql,5000,"CC");

        // 线程休眠10秒，结束后不提交
        System.out.println("修改会话进入休眠");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DbUtils.close(connection);  // TODO 不是说连接关闭时自动提交吗？此处update语句未执行 = 5000
        System.out.println("修改会话连接关闭...");
    }
}
