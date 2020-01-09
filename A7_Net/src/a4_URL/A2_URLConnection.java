package a4_URL;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/** URLConnection : 针对HTTP协议
 * 一、URLConnection 连接实例 == URL的方法 openStream()
 *
 * 二、通过URLConnection对象获取的输入流和输出流，即可以与现有的CGI程序进行交互
 * > CGI : Common Gateway Interface : 公共网关接口
 * public Object getContent( ) throws IOException
 * public int getContentLength( )
 * public String getContentType( )
 * public long getDate( )
 * public long getLastModified( )
 *
 * public InputStream getInputStream( )throws IOException
 * public OutputStream getOutputStream( )throws IOException
 *
 * 三、URL下载流程
 *  1.实例化URL对象  2.获取URL连接协议对象(连接对象建立连接)  3.获取连接对象的输入流
 */


public class A2_URLConnection {
    @Test
    public void urlConnection() throws IOException {
        URL url = new URL("https://www.sina.com.cn/");
        URLConnection conn = url.openConnection( );

        long date = conn.getDate();
        System.out.println("conn.getDate(): " + date);

        Object content = conn.getContent();
        System.out.println(content);

        // 获取网页内容
        InputStream inputStream = conn.getInputStream();
        byte[] buff = new byte[1024];
        ByteArrayOutputStream bais = new ByteArrayOutputStream();
        int len;
        while ((len = inputStream.read(buff)) != -1) {
            bais.write(buff,0,len);
        }
        System.out.println(bais.toString());
    }
}
