package a3_ExerciseExamStu;

import a0_util.JDBCUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public class CommonUtil {
    // 通用查询
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

    // 通用增删改
    public static int update(String sql, Object... args) {
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
            return i;
        } catch ( SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection,ps);
        }
        return 0;
    }
}
