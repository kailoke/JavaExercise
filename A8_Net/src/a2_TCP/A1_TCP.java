package a2_TCP;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/** TCP/IP 参考模型
 * 一、本质；传输控制协议(TCP)和网络互联协议(IP)组成的一组协议，包括多个具有不同功能且互为关联的协议
 *   IP(Internet Protocol)协议是网络层的主要协议，支持网间互连的数据通信。
 *   TCP/IP协议模型从更实用的角度出发，形成了高效的四层体系结构，即
 *      应用层、传输层、网络层(IP层)、物理+数据链路层
 *
 * 二、TCP 传输控制协议 Transmission Control Protocol   实现面向连接的会话
 *  > TCP协议进行通信的两个应用进程：客户端、服务端
 *  > 使用TCP协议前，先建立TCP连接，形成传输数据通道
 *  > 连接可靠：三次握手，四次挥手，点对点通信
 *  > 连接中可大数据量传输
 *  > 传输完毕需要释放连接，效率低
 *
 * 三、基于Socket的TCP编程
 * <1> 客户端
 *  > 1.创建Socket：Socket(InetAddress addr,int port)，客户端自动向服务器发起连接
 *      > 此实例对象的创建过程就是客户端向服务器发出套接字连接请求，若成功则创建Socket对象，否则抛出异常
 *  > 2.获取Socket的OutputStream/InputStream [传输byte]
 *  > 3.OutputStream输出流发送数据 / InputStream输入流读取服务器放入线路的信息(非自我线路放入的信息)
 *  > 4.关闭Socket实例，断开客户端到服务器的连接，释放线路
 *
 * <2> 服务器
 *  > 1.创建ServerSocket实例(int port)，监听指定端口的客户端请求
 *  > 2.调用ServerSocket实例.accept()，接受客户端的连接请求，返回Socket实例
 *  > 3.获取Socket实例的输入流\输出流 [传输byte]
 *  > 4.读取输入流的数据 \ 向输出流发送数据
 *  > 5.关闭ServerSocket && Socket实例
 */
public class A1_TCP {

    @Test
    // 1.简单客户端模拟
    public void client(){
        Socket socket = null;
        OutputStream os = null;
        try {
            InetAddress inet = InetAddress.getByName("localhost");
            // 1.创建Socket实例，客户端自动向服务器发起连接
            socket = new Socket(inet,8889);
            // 2.获取输出流
            os = socket.getOutputStream();
            // 3.发送数据
            os.write("你好我是客户端1\n".getBytes());
            os.write("你好我是客户端2\n".getBytes());
            os.write("你好我是客户端3\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流 关闭套接字
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
    // 2.简单服务器模拟
    public void server(){
        ServerSocket ss = null;
        Socket socket = null;
        try {
            ss = new ServerSocket(8889);
            // 阻塞方法，直到获取连接
            socket = ss.accept();
            System.out.println("收到数据来自：" + socket.getInetAddress().getHostAddress());

            InputStream is = socket.getInputStream();
            byte[] buffer = new byte[10];
            int len;
//            // 一、String类将字节按字符串存储则改变了其原意
//            StringBuilder sb = new StringBuilder(50);
//            while ((len = is.read(buffer)) != -1){
//                sb.append(Arrays.toString(buffer));
//            }
//            System.out.println(sb.length()); // 每个字符的字节都成为了字符:356
//            System.out.println("sb.toString:\n" + sb.toString());

            // 二、ByteArrayOutput，内部维护 byte[] 供存储数据
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = is.read(buffer)) != -1){
                // 将缓存数据写入baos内部数组
                baos.write(buffer,0,len);
            }
            System.out.println("baos.toString:\n" + baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
