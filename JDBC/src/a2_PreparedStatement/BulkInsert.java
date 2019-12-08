package a2_PreparedStatement;

import a0_util.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
    PreparedStatement 实现高效批量插入
 */
public class BulkInsert {
    @Test
    // 批量插入2000条： 每次写数据都进行了交互
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
        System.out.println("花费时间：" + (end-start));
    }

    @Test
    // 批量插入2000条： Batch 减少交互次数
    public void testBatchInsert() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into goods values (null,?)";
        PreparedStatement ps = null;
        long start = System.currentTimeMillis();
        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < 20000; i++) {
                ps.setObject(1,"name_" + (i+1));

                // Batch: addBatch \ executeBatch \ clearBatch
                ps.addBatch();
                if ((i+1) % 500 == 0 ){  //注意末尾
                    ps.executeBatch();
                    ps.clearBatch();
                }

                System.out.println("插入 :" + i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(connection,ps);
        }
        long end = System.currentTimeMillis();
        System.out.println("花费时间：" + (end-start));
    }
}
