package a3_UDP;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

public class UDPTest {
    @Test
    public void sender() throws IOException {
        // Datagram socket实例
        DatagramSocket socket = new DatagramSocket();

        // 封装数据
        String str = "UDP东风快递";
        byte[] data = str.getBytes();
        DatagramPacket packet = new DatagramPacket(data,0,data.length,InetAddress.getByName("localhost"),32123);
        // 发送数据
        socket.send(packet);

        socket.close();
    }

    @Test
    public void receiver() throws IOException {
        // socket实例
        DatagramSocket socket = new DatagramSocket(32123);
        System.out.println("Socket实例");

        // 封装数据包
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
        System.out.println("封装数据包");

        // 接收数据
        socket.receive(packet);
        System.out.println("接收完成");

        System.out.println(new String(packet.getData(),0,packet.getLength()));
        socket.close();
    }
}
