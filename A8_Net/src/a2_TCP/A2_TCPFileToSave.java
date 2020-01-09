package a2_TCP;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 客户端发送文件给服务器，服务器将文件保存至本地
 * > 客户端 读取本地文件(bis) 发送(os)
 * > 服务器 接收文件(is) 保存至本地(bos)
 */
public class A2_TCPFileToSave {
    private String path = "src/a2_TCP/";

    @Test
    public void client() throws IOException {
        
        Socket socket = new Socket(InetAddress.getByName("localhost"),32123);
        // 获取输出流
        OutputStream os = socket.getOutputStream();
        // bis读取本地文件
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path + "屏幕截图.jpg"));

        byte[] buffer = new byte[1024];
        int len;
        while ((len = bis.read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        System.out.println("文件已发送");

        bis.close();
        socket.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocket ss = new ServerSocket(32123);
        Socket socket = ss.accept();
        System.out.println("接收文件来自:" + socket.getInetAddress().getHostAddress());

        // 获取输入流
        InputStream is = socket.getInputStream();
        // bos写出到本地
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + "屏幕截图_accept1.jpg"));

        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1){
            bos.write(buffer,0,len);
        }

        bos.close();
        socket.close();
        ss.close();
    }
}
