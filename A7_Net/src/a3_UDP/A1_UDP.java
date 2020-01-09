package a3_UDP;

import org.junit.Test;
import java.io.IOException;
import java.net.*;

/** UDP 用户数据报协议 User Datagram Protocol
 * 一、UDP
 *  > 无连接：将数据(Data+长度)、源(发送端IP+Port)、目的地(接收端IP+Port)封装成数据包，不需要建立连接
 *  > 不可靠：发送不管对方是否准备好，接收方收到也不确认，故是不可靠的
 *  > 封装数据报，每个大小限制<=64K
 *  > 效率高：发送数据结束时无需释放资源，开销小，速度快
 *
 * 二、基于DatagramSocket 和 DatagramPacket 的UDP编程
 *  > UDP数据报通过数据报套接字 DatagramSocket 发送和接收，系统不保证UDP数据报一定能够安全送到目的地，也不能确定什么时候可以抵达
 *  > DatagramPacket 对象封装了UDP数据报，在数据报中包含了发送端的IP地址和端口号以及接收端的IP地址和端口号
 *
 * 三、UDP通信流程
 *  > 1. 建立DatagramSocket   (发送端 || 接收端(指定监听port))
 *  > 2. 建立数据包DatagramPacket    (待发送的 || 待接收的缓存)
 *  > 3. 调用Socket的      (发送方法 || 接收方法)
 *  > 4. 关闭Socket
 */


public class A1_UDP {
    @Test
    // 1.发送端
    public void sender() throws IOException {
        // DatagramSocket实例
        DatagramSocket socket = new DatagramSocket();

        // 封装数据
        String str = "UDP东风快递";
        byte[] data = str.getBytes();
        DatagramPacket packet = new DatagramPacket(data,data.length,InetAddress.getByName("localhost"),32123);
        // 发送数据
        socket.send(packet);
        socket.send(packet);
        System.out.println("发送端已发送数据报包");

        socket.close();
    }

    @Test
    public void receiver() throws IOException {
        // DatagramSocket实例，指定监听端口
        DatagramSocket socket = new DatagramSocket(32123);
        System.out.println("1.DatagramSocket实例创建...");

        // 封装数据包
        byte[] buffer1 = new byte[1024];
        byte[] buffer2 = new byte[1024];
        DatagramPacket packet1 = new DatagramPacket(buffer1,buffer1.length);
        DatagramPacket packet2 = new DatagramPacket(buffer2,buffer2.length);
        System.out.println("2.封装空数据包待接收");

        // 接收数据
        socket.receive(packet1);
        socket.receive(packet2);
        System.out.println("3.空数据宝接收数据完成");

        System.out.println("4.接收数据报包内容:" + new String(packet1.getData(),0,packet1.getLength()));
        System.out.println("4.接收数据报包内容:" + new String(packet2.getData(),0,packet2.getLength()));
        socket.close();
    }
}
