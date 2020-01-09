package a1_Inet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/** 网络编程
 * 一、定位 主机+应用
 *  > Socket == IP + Port  Socket网络套接字
 *  > IP地址：IPV4 4个字节组成，无符号~=42亿个    IPV6 16个字节，写成8个无符号整数，每个整数4个十六进制位
 *  > Port : 0 ~ 1023 公认端口  1024~49151 注册端口 49152~65535 动态/私有端口
 *
 * 二、传输数据 : TCP/IP 模型，TCP/IP 协议
 *  > 应用层
 *  > 传输层   Segment
 *  > 网络层   Packet
 *  > 数据链路层 Frame
 *  > 物理层   Bits
 *
 * 三、Socket
 *  > 网络通信其实就是Socket间的通信
 *  > Socket允许程序把网络连接当成一个流，数据在两个Socket间通过IO传输
 *  > 一般主动发起通信的应用程序属客户端，等待通信请求的为服务端
 *
 * 四、Socket分类
 *  > 流套接字（stream socket）：使用TCP提供可依赖的字节流服务 Socket
 *  > 数据报套接字（datagram socket）：使用UDP提供“尽力而为”的数据报服务:DatagramSocket + DatagramPacket
 */

public class A1_Socket {
    public static void main(String[] args) throws IOException {
        // 1. public Socket(InetAddress address,int port)
        Socket socket1 = new Socket(InetAddress.getByName("www.baidu.com"), 3306);
        // 2. public Socket(String host,int port)
        Socket socket2 = new Socket("www.baidu.com", 3306);
        // 3. 接收网络消息 : public InputStream getInputStream()返回此套接字的输入流
        InputStream inputStream = socket1.getInputStream();
        // 4. 发送网络消息 : public OutputStream getOutputStream()返回此套接字的输出流
        OutputStream outputStream = socket2.getOutputStream();

        socket1.close();    // 关闭Socket自动关闭 I/O Stream
        socket2.close();
    }
}
