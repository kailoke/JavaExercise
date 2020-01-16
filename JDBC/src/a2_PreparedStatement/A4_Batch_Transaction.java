package a2_PreparedStatement;

import org.junit.Test;

import a0_JDBCUtil.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/** Batch批量插入
 * 一、PreparedStatement：addBatch() / executeBatch() / clearBatch()
 * 二、目的：减少程序与客户端的交互次数
 * 三、方法： Batch + Transaction(connection.setAutoCommit(false))
 */

public class A4_Batch_Transaction {
    @Test
    // 一、插入2000条： 每次写数据(一个连接)都与数据库进行了交互
    public void testInsert() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into goods values (null,?)";
        PreparedStatement ps = null;
        long start = System.currentTimeMillis();
        try {
            for (int i = 0; i < 20000; i++) {
                ps = connection.prepareStatement(sql);
                ps.setObject(1,"name_" + i+1);
                ps.execute();
                System.out.println("插入 :" + i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(connection,ps);
        }
        long end = System.currentTimeMillis();
        System.out.println("basic Insert花费时间：" + (end-start));
    }

    @Test
    // 二、批处理 插入50万条：使用PreStMt.addBatch()，批处理减少和数据库的交互次数 35199
    public void testBatchInsert() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into goods values (null,?)";
        PreparedStatement ps = null;
        long start = System.currentTimeMillis();
        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < 500000; i++) {
                ps.setObject(1,"name_" + (i+1));

                // SQL加入Batch
                ps.addBatch();
                if ((i+1) % 500 == 0 ){  // Batch中有500条ps则执行批处理，注意行是否被正确包含
                    ps.executeBatch();
                    ps.clearBatch();     // 清空Batch内存数据
                }
                System.out.println("插入 :" + i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(connection,ps);
        }
        long end = System.currentTimeMillis();
        System.out.println("Batch Insert花费时间：" + (end-start));
    }

    @Test
    // 三、事务+批处理 插入50万条数据：批处理完成后一次性提交至数据库服务器 13071
    public void testBatchInsertWithTransaction() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into goods values (null,?)";
        PreparedStatement ps = null;
        long start = System.currentTimeMillis();
        try {
            // 1.关闭DML自动提交
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(sql);
            // 2.构建批处理
            for (int i = 0; i < 500000; i++) {
                ps.setObject(1,"name_" + (i+1));
                ps.addBatch();
                if ((i+1) % 500 == 0 ){  //注意末尾
                    ps.executeBatch();
                    ps.clearBatch();
                }
                System.out.println("插入 :" + i);
            }
            // 3.提交事务
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(connection,ps);
        }
        long end = System.currentTimeMillis();
        System.out.println("花费时间：" + (end-start));
    }
}
