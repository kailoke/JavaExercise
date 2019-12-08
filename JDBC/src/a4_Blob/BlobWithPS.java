package a4_Blob;

import a0_bean.Customers;
import a0_util.JDBCUtil;
import org.junit.Test;

import java.io.*;
import java.sql.*;


public class BlobWithPS {
    @Test
    // 修改blob字段
    public void testInsert() throws Exception {
        Connection connection = JDBCUtil.getConnection();
        String sql ="insert into customers values (null,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setObject(1,"刘备");
        ps.setObject(2,"LB@nb.com");
        ps.setObject(3,"2012-3-15");
        FileInputStream fis = new FileInputStream("./src/a4_Blob/test.jpg");
        ps.setBlob(4, fis);
        ps.execute();

        JDBCUtil.close(connection,ps);
    }

    @Test
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
