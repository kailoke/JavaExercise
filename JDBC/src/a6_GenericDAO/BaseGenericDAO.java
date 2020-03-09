package a6_GenericDAO;

import a0_JDBCUtil.JDBCUtil;
import com.sun.istack.internal.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;

/** 泛型DAO<T>
 *  > 1. DAO<T> 让 DB-table 实现类指定自身Bean泛型继承
 *  > 2. DAO<T> 维护Class<T>对象，提供静态块使继承类初始化其为自身Bean类Class<T>对象
 *  > 3. DAO<T> 通用方法中使用成员 Class<T>对象 进行反射
 */

public abstract class BaseGenericDAO<T> {
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

    // Update
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
    // 单行查询
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
    // 多行查询
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
    // 标量查询，泛型方法，调用时根据声明返回值类型强转
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
