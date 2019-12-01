package a2_TCP;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一、客户端
 *  > 1.创建Socket实例，指明PID
 *  > 2.获取Socket实例输出流，用以输出数据
 *  > 3.输出流写出数据
 *
 * 二、服务器
 *  > 1.创建ServerSocket实例，指明本服务Port
 *  > 2.调用ServerSocket实例.accept()，接收来自客户端的Socket
 *  > 3.获取Socket实例的输入流
 *  > 4.读取输入流的数据
 */
public class TCPTest {

    @Test
    public void client(){
        Socket socket = null;
        OutputStream os = null;
        try {
            InetAddress inet = InetAddress.getByName("localhost");
            socket = new Socket(inet,8889);

            os = socket.getOutputStream();
            os.write("你好我是客户端".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void server(){
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            ss = new ServerSocket(8889);
            socket = ss.accept();
            is = socket.getInputStream();

            byte[] buffer = new byte[1024];
            int len;

//        while ((len = is.read(buffer)) != -1){
//            String str = new String(buffer,0,len);
//        }
//        System.out.println(str);

            baos = new ByteArrayOutputStream();
            while ((len = is.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }
            System.out.println(baos.toString());
            System.out.println("收到数据来自：" + socket.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket != null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ss != null)
                    ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
