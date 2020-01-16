package a2_PreparedStatement;

import a0_JDBCUtil.JDBCUtil;
import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

/** 使用PreparedStatement实现对数据表的 CUD
 * PreparedStatement特性：
 *  > Precompiled: 防止SQL语句被非法切割--> 解决SQl注入问题
 *  > Precompiled：执行效率更快。 编译后运行时只填入占位参数(连接传输)，不需要重复解释(不必通过连接传输SQL语句)
 *  > 可存储Blob数据
 *
 * Statement存在 拼串操作 values('" + name +"','"+email+"','"+birth+"') 拼串带来了SQL注入问题
 *  > SQL注入，利用Statement没有检查，用户在输入数据中注入非法的SQL语句或命令
 *  > 不能操作BLOB类型变量
 *  > 批量插入效率低
 *
 * 访问数据库 with PreparedStatement
 *  一、Connection编译SQL语句，返回编译好的PreparedStatement对象
 *  二、PreparedStatement预编译对象 填入参数
 *      > ps.setObject(index,obj) : Object类
 *      > ps.setXxx(index,xxx)    : 明确类型
 *  三、执行SQL
 *      > PreparedStatement!.execute()：执行SQL。成功返回true，否则false
 *      > PreparedStatement!.executeUpdate()：执行更新，返回 affected rows
 *      > PreparedStatement!.executeQuery()：执行查询，返回结果集 ResultSet
 */

public class A1_DML {
    @Test
    // 测试方法
    public void commonDML(){
//        String sql1 = "delete from `customers` where id = ? or id = ? or id = ?";
//        commonModify(sql1,21,22,23);
//
        String sql2 = "Insert into `user` values (null,?,?,?,?)";
        commonModify(sql2, "梁朝伟", "xyz", "Hongkong", "18758585858");
    }

    @Test
    // 一、insert into customers with it's bean
    public void testInsert() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            Properties SQLInfo = new Properties();
            assert is != null;
            SQLInfo.load(is);
            String url = SQLInfo.getProperty("url");
            String driverClass = SQLInfo.getProperty("driverClass");
            String user = SQLInfo.getProperty("user");
            String password = SQLInfo.getProperty("password");
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url,user,password);

            String sql = "INSERT into customers(`name`,email,birth) values (?,?,?)";
            // 1.Connection调用Server||Client预编译SQL语句，返回PreparedStatement实例
            ps = connection.prepareStatement(sql);

            // 2.填充占位符
            ps.setString(1,"张飞");
            ps.setString(2,"zf@zf.com");

            // java.sql.Date.valueOf("yyyy-[m]m-[d]d") only in this pattern
            ps.setDate(3, java.sql.Date.valueOf("0210-11-11"));
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            java.util.Date date = sdf.parse("0210-11-11");
//            ps.setDate(3,new java.sql.Date(date.getTime()));

            // 3.执行sql
            ps.execute();
            System.out.println("Insert() finished...");
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
    }

    @Test
    // 二、封装JDBCUtil，使用专用update
    public void testUpdate() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtil.getConnection();

            String sql = "UPDATE customers set name = ?,email = ?, birth = ? where id = ?";
            // 1.编译sql
            ps = connection.prepareStatement(sql);

            // 2.填参
            ps.setString(1,"刘备");
            ps.setString(2,"LB@lb.com");
            ps.setDate(3, java.sql.Date.valueOf("1910-1-1"));
            ps.setInt(4,20);

            // 3.执行实例
            ps.execute();
            System.out.println("Update finished");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4.释放资源
            JDBCUtil.close(connection,ps);
        }
    }

    // 三、full args[] + ps.executeUpdate() RETURNS affected rows
    private void commonModify(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }

//            ps.execute();
            // > 执行语句后有结果集，则返回 true
            // > 执行语句后无结果集，则返回 false

            // > ps.executeUpdate():返回affected rows
            int i = ps.executeUpdate();
            System.out.println("CommonModify() affected rows：" + i);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection,ps);
        }
    }
}
