package a2_PreparedStatement;

import a0_util.JDBCUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/** 使用PreparedStatement实现对数据表的 增改删
 * > Precompiled: 防止SQL语句被非法切割--> 解决SQl注入问题
 * > Blob数据
 * > 因为预编译，执行效率更快。 编译后运行时只填入占位参数，不需要重复解释
 */
public class CommonInsertUpdateDeleteTest {
    @Test
    // 添加一条记录
    public void testInsert() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            Properties SQLInfo = new Properties();
            SQLInfo.load(is);
            String url = SQLInfo.getProperty("url");
            String driverClass = SQLInfo.getProperty("driverClass");
            String user = SQLInfo.getProperty("user");
            String password = SQLInfo.getProperty("password");
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url,user,password);

            // 1.预编译sql语句，返回PreparedStatement实例
            String sql = "INSERT into customers(name,email,birth) values (?,?,?)";
            ps = connection.prepareStatement(sql);

            // 2.填充占位符
            ps.setString(1,"张飞");
            ps.setString(2,"zf@zf.com");
            ps.setDate(3, java.sql.Date.valueOf("0210-11-11"));
//             ↑ sql.date.valueOf(String str)只支持 "yyyy-MM-dd"
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = sdf.parse("220-5-3");
//            ps.setDate(3,new java.sql.Date(date.getTime()));
            // 3.执行sql
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4.资源关闭
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Insert finished...");
    }

    @Test
    public void testUpdate() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtil.getConnection();

            // 1.sql 实例
            String sql = "UPDATE customers set name = ?,email = ?, birth = ? where id = ?";
            ps = connection.prepareStatement(sql);
            // 2.填充实例
            ps.setString(1,"刘备");
            ps.setString(2,"LB@lb.com");
            ps.setDate(3, java.sql.Date.valueOf("1910-1-1"));
            ps.setInt(4,20);
            // 3.执行实例
            ps.execute();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 4.释放资源
            JDBCUtil.close(connection,ps);
        }

        System.out.println("Update finished");
    }


    // 通用增删改
    private void commonModify(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }
            // > 执行语句后有结果集，则返回 true
            // > 执行语句后无结果集，则返回 false
//            ps.execute();

            // > 返回affected 行数
            int i = ps.executeUpdate();
            System.out.println("Common Modify successed and affected lines of " + i);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection,ps);
        }
    }
    @Test
    public void testCommon(){
//        String sql1 = "delete from customers where id = ? or id = ? or id = ?";
//        commonModify(sql1,21,22,23);
//
        String sql2 = "Insert into user values (null,?,?,?,?)";
        commonModify(sql2, "梁朝伟", "xyz", "Hongkong", "18758585858");
    }
}
