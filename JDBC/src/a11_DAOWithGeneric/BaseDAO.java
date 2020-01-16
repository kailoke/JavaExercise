package a11_DAOWithGeneric;

import a0_JDBCUtil.JDBCUtil;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;

/** BaseDAO : Data Access Object
 *  封针对数据库的通用操作，让数据表类继承获得方法
 */
public abstract class BaseDAO<T> {
    private Class<T> clazz = null;
    // 对象使用方法,this指针指向调用对象
    {
        // 获得带泛型的父类
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        // 参数化获得get方法
        ParameterizedType paraType = (ParameterizedType) genericSuperclass;
        // 获得泛型参数(类)列表
        Type[] types = paraType.getActualTypeArguments();
        clazz = (Class<T>) types[0];
    }
    // 增删改
    public int update(Connection c,String sql, Object... args) {
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
    // 单行
    public T queryInstance(Connection c, String sql, Object ... args) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = c.prepareStatement(sql);
            for (int i = 0;i < args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            resultSet = ps.executeQuery();
            ResultSetMetaData metaData = ps.getMetaData();

            if (resultSet.next()){
                T t = clazz.getDeclaredConstructor().newInstance();

                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i+1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,resultSet.getObject(i+1));
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,resultSet);
        }
        return null;
    }
    // 多行
    public ArrayList<T> queryForList(Connection c, String sql, Object ... args) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = c.prepareStatement(sql);
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
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,resultSet);
        }
        return null;
    }
    // scalar query
    public <E> E getValue(@NotNull Connection c, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            if (rs.next()){
                return (E)rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,rs);
        }
        return null;
    }
}
