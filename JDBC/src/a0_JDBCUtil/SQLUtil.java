package a0_JDBCUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

/** SQL通用操作
 * 调用者只需要传入(运行时类Class实例 SQL语句 实参)
 */
public class SQLUtil {
    // 泛型查询，没有Connection参数
    public static<T> ArrayList<T> query(Class<T> clazz, String sql, Object ... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0;i < args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            resultSet = ps.executeQuery();
            ResultSetMetaData metaData = ps.getMetaData();
            ArrayList<T> list = new ArrayList<>();

            while (resultSet.next()){
                // 泛型类实例
                T t = clazz.getDeclaredConstructor().newInstance();
                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i+1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,resultSet.getObject(i+1));
                }
                System.out.println("查询成功");
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection,ps,resultSet);
        }
        return null;
    }

    // 可变参数的DML，没有Connection参数
    public static int update(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }
            // 返回affected rows
            int i = ps.executeUpdate();
            System.out.println("> affected rows：" + i);
            return i;
        } catch ( SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection,ps);
        }
        return 0;
    }
}
