package a2_TCP;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 客户端发送文件至服务器，服务器保存到本地，服务器返回"发送成功"给客户端，并关闭连接
 *  > OutputStream传输结束后 socket.shutdownOutputStream() 作为 接收方InputStream.read()的终止信号: -1
 */
public class A3_TCPGetMsg {
    private String path = "src/a2_TCP/";

    @Test
    public void client() throws IOException {

        Socket socket = new Socket(InetAddress.getByName("localhost"),32123);
//        Thread.sleep(10000);
        OutputStream os = socket.getOutputStream();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path + "屏幕截图.jpg"));

        byte[] buffer = new byte[1024];
        int len;
        while ((len = bis.read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        // InputStream.read() 是个阻塞方法，需要发送方手动终止Socket.shutdownOutput() 以通知接收方读取结束;
        socket.shutdownOutput();
        System.out.println("客户端文件已发送");

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
    public void server() throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(32123);
        Socket socket = ss.accept();
        System.out.println("建立连接来自:" + socket.getInetAddress().getHostAddress());

        InputStream is = socket.getInputStream();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + "屏幕截图_accept2.jpg"));

        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1){
            bos.write(buffer,0,len);
        }

        // 发送回执
        OutputStream os = socket.getOutputStream();
        os.write("图片".getBytes());
        os.write("传输".getBytes());
        os.write("成功".getBytes());
        // InputStream.read() 是个阻塞方法，需要发送方手动终止Socket.shutdownOutput() 以通知接收方读取结束;
        socket.shutdownOutput();
        System.out.println("服务器已发送回执");

//        os.close();
//        bos.close();
//        is.close();
//        socket.close();
//        ss.close();
        Thread.sleep(10000);
    }
}
