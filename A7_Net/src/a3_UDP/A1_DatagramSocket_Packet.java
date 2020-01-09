package a3_UDP;

/**
 * 一、DatagramSocket 类的常用方法
 *  > public DatagramSocket(int port)创建数据报套接字并将其绑定(监听)到本地主机上的指定端口。
 *      套接字将被绑定到通配符地址，IP 地址由内核来选择
 *  > public DatagramSocket(int port,InetAddress addr)创建数据报套接字，将其绑定到指定的本地地址。
 *      本地端口必须在 0 到 65535 之间（包括两者）。如果 IP 地址为 0.0.0.0，套接字将被绑定到通配符地址，IP 地址由内核选择
 *  > public void send(DatagramPacket p)从此套接字发送数据报包p
 *  > public void receive(DatagramPacket p)从此套接字接收数据报包至p中，阻塞方法，直到成功接收数据报包
 *  > public void close()关闭此数据报套接字
 *
 * 二、DatagramPacket 类的常用方法
 *  > public DatagramPacket(byte[] buf,int length)构造 DatagramPacket，用来接收长度为 length 的数据包
 *      length 参数必须小于等于 buf.length
 *  > public DatagramPacket(byte[] buf,int length,InetAddress address,int port)构造数据报包
 *      用来将长度为 length 的包发送到指定主机上的指定端口号
 *  > public InetAddress getAddress()返回某台机器的 IP 地址，此数据报将要发往该机器或者是从该机器接收到的
 *  > public int getPort()返回某台远程主机的端口号，此数据报将要发往该主机或者是从该主机接收到的
 *  > public byte[] getData()返回数据缓冲区。接收到的或将要发送的数据从缓冲区中的偏移量 offset 处开始，持续 length 长度
 *  > public int getLength()返回将要发送或接收到的数据的长度
 */

public class A1_DatagramSocket_Packet {
    public static void main(String[] args) {
    }
}
