package a2_TCP;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/** 客户端发送文件至服务器，服务器保存到本地
 *  返回"发送成功"给客户端，并关闭连接
 *  传输结束后 socket.shutdownOutputStream() 作为 InputStream.read()的终止信号
 */
public class TCPExercise2 {
    String path = "src/a2_TCP/";

    @Test
    public void client() throws IOException, InterruptedException {

        Socket socket = new Socket(InetAddress.getByName("localhost"),32123);
        Thread.sleep(10000);
        OutputStream os = socket.getOutputStream();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path + "屏幕截图.jpg"));

        byte[] buffer = new byte[1024];
        int len;
        while ((len = bis.read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        socket.shutdownOutput();
        System.out.println("文件已发送");

        // 接收服务器回执
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] iBuffer = new byte[2];
        int iLen;
        while ((iLen = is.read(iBuffer)) != -1){
            baos.write(iBuffer,0,iLen);
        }
        System.out.println(baos.toString());

//        baos.close();
//        is.close();
//        bis.close();
//        os.close();
//        socket.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocket ss = new ServerSocket(32123);
        System.out.println("ss实例");
        Socket socket = ss.accept();
        System.out.println("socket实例");
        InputStream is = socket.getInputStream();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + "屏幕截图_copy2.jpg"));

        byte[] buffer = new byte[1024];
        int len;
        // InputStream.read() 是个阻塞方法，需要手动终止
        while ((len = is.read(buffer)) != -1){
            bos.write(buffer,0,len);
        }
        System.out.println("接收文件来自:" + socket.getInetAddress().getHostAddress());

        // 发送回执
        OutputStream os = socket.getOutputStream();
        os.write("图片".getBytes());
        os.write("传输".getBytes());
        os.write("成功".getBytes());
        socket.shutdownOutput();
        System.out.println("已发送回执");

//        os.close();
//        bos.close();
//        is.close();
//        socket.close();
//        ss.close();
    }
}
