package a3_Blob;

import org.junit.Test;

import a0_Bean.Customers;
import a0_JDBCUtil.JDBCUtil;
import java.io.*;
import java.sql.*;

/** BLOB 二进制大对象 Binary Large Object
 * 一、二进制上传至数据库
 *  > ps.setBlob(index, InputStream is)
 *  > ps.execute
 *
 * 二、二进制流下载至本地
 *  > Blob对象：RS.getBlob()
 *  > blob输入流：Blob.getBinaryStream : InputStream
 *  > 从blob输入流读取 二进制数据 写出至 输出流(OutputStream)
 */

public class BLOBInPS {
    @Test
    // 一、向数据库上传BLOB类型数据
    public void testInsert() throws Exception {
        Connection connection = JDBCUtil.getConnection();
        String sql ="insert into customers values (null,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setObject(1,"刘备");
        ps.setObject(2,"LB@nb.com");
        ps.setObject(3,"2012-3-15");
        // Blob流从外部读取，PrepareStatement自动获取输入流
        FileInputStream fis = new FileInputStream("./src/a4_Blob/test.jpg");
        ps.setBlob(4, fis);
        ps.execute();
        // SQL执行时，将blob输入流存储到数据库中
        JDBCUtil.close(connection,ps);
    }

    @Test
    // 二、从数据库获取Blob类型数据，并保存到本地
    public void testQuery() {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String sql = "select id,name,email,birth,photo from customers where id = ?";
        InputStream is = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,19);
            resultSet = ps.executeQuery();
            if (resultSet.next()){
                // resultSet.getXxx(String ColumnLabel)
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Date date = resultSet.getDate("birth");
                Customers customers = new Customers(id, name, email, date);
                System.out.println(customers);

                // 获得Blob输入流
                Blob photo = resultSet.getBlob("photo");
                is = photo.getBinaryStream();
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(new FileOutputStream("./src/a4_Blob/download.jpg"));
                int len;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    System.out.println(len);
                    bos.write(buffer,0,len);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JDBCUtil.close(connection,ps,resultSet);
    }
}
