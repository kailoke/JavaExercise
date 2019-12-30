package a2_TCP;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/** 客户端发送文件给服务器，服务器将文件保存至本地
 *
 */
public class TCPExercise1 {
    String path = "src/a2_TCP/";

    @Test
    public void client() throws IOException {
        
        Socket socket = new Socket(InetAddress.getByName("localhost"),32123);
        OutputStream os = socket.getOutputStream();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path + "屏幕截图.jpg"));

        byte[] buffer = new byte[1024];
        int len;
        while ((len = bis.read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        System.out.println("文件已发送");

        bis.close();
        os.close();
        socket.close();
    }

    @Test
    public void server() throws IOException {
        System.out.println("服务器启动");
        ServerSocket ss = new ServerSocket(32123);
        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + "屏幕截图_copy1.jpg"));

        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1){
            bos.write(buffer,0,len);
        }
        System.out.println("接收文件来自:" + socket.getInetAddress().getHostAddress());

        bos.close();
        is.close();
        socket.close();
        ss.close();
    }
}
