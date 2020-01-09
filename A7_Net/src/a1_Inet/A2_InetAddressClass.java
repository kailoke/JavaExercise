package a1_Inet;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;

/** InetAddress java中使用此类代表IP
 * 一、InetAddress类对象包含网络主机的
 *      > 域名(hostName)  域名解析：通过DNS(Domain Name System)获得域名指向的IP地址
 *      > 主机地址(hostAddress) : IPV4 || IPV6
 * 二、本地回环地址：127.0.0.1   本地主机名:localhost
 * 三、InetAddress工厂方法模式提供实例
 *  > 1.获取本地主机：public static InetAddress getLocalHost()
 *  > 2.获取指定(域名或IP)主机：public static InetAddress getByName(String host)
 *  > 常用：InetAddress.getByName() / InetAddress.getLocalHost() / getHostName() / getHostAddress
 */

public class A2_InetAddressClass {
    @Test
    // InetAddress类 工厂方法获得实例
    public void getInstance() throws IOException {
        java.net.InetAddress inet1 = java.net.InetAddress.getByName("192.168.0.101");
        System.out.println(inet1);

        java.net.InetAddress inet2 = InetAddress.getByName("www.baidu.com");
        System.out.println(inet2);  // hostName/hostAddress
        System.out.println("baiDu.hostAddress: " + inet2.getHostAddress());
        System.out.println("baiDu.isReachable: " + inet2.isReachable(100));

        java.net.InetAddress inet3 = InetAddress.getByName("localhost");
        System.out.println(inet3);

        java.net.InetAddress inet4 = InetAddress.getLocalHost();
        System.out.println(inet4);
    }
}
