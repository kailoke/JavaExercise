package a8_Apache_DBUtils;

import a0_Bean.Customers;
import a7_ConnectionPool.DruidPool;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers .*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/*
    Apache封装的开源JDBC工具类库，封装对数据库的增删改差操作
 */
public class Testdbutils {
    @Test
    // 增删改
    public void testUpdate() {
        Connection connection = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection = DruidPool.getConnetion();
            String sql1 = "insert into customers(name,email,birth) values(?,?,?)";
            runner.update(connection,sql1,"刘翔","Lx@lx.com","1992-01-01");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtils.close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    // 查询记录
    // ResultSetHandler接口，子接口实现各类resultSet返回类型
    public void testQuery() {
        QueryRunner runner = new QueryRunner();
        Connection connection = null;
        try {
            connection = DruidPool.getConnetion();

            // BeanHandler
            String sql1 = "select id,name,email,birth from customers where id = ?";
            BeanHandler<Customers> handler1 = new BeanHandler<>(Customers.class);
            Customers query1 = runner.query(connection, sql1, handler1, 13);
            System.out.println("query_bean : " + query1);

            // BeanListHandler
            String sql2 = "select id,name,email,birth from customers where id >= ?";
            BeanListHandler<Customers> handler2 = new BeanListHandler<>(Customers.class);
            List<Customers> query2 = runner.query(connection, sql2, handler2, 13);
            query2.forEach( (i) -> System.out.println("query_list : " + i.toString()));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeQuietly(connection);
        }
    }

    @Test
    // MapHandler \ MapListHandler
    public void testQuery2() {
        QueryRunner runner = new QueryRunner();
        Connection connection;
        try {
            connection = DruidPool.getConnetion();

            // MapHandler
            String sql1 = "select id,name,email,birth from customers where id = ?";
            MapHandler handler1 = new MapHandler();
            Map<String, Object> query1 = runner.query(connection, sql1, handler1, 13);
            System.out.println("map_bean : " + query1);

            // MapListHandler
            String sql2 = "select id,name,email,birth from customers where id >= ?";
            MapListHandler handler2 = new MapListHandler();
            List<Map<String, Object>> query2 = runner.query(connection, sql2, handler2, 13);
            query2.forEach( (i) -> System.out.println("map_list : " + i.toString()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    // scalar query : implements ResultSetHandler<Object>
    public void testScalar(){
        QueryRunner runner = new QueryRunner();
        Connection connection;
        try {
            connection = DruidPool.getConnetion();

            String sql1 = "select count(*) from customers where id >= ?";
            ScalarHandler handler1 = new ScalarHandler();
            // ScalarHandler().handle()使用Object 指向 实际字段类
            // 所以可以将Object转为实际字段类
            Long query1 = (Long) runner.query(connection, sql1, handler1, 13);
            System.out.println("Scalar : " + query1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
