package A5_System;

/** java.lang.System 系统类，提供系统级的很多属性和控制方法。
 *  > 1. 该类的构造器private，无法实例化。其内部的成员变量和方法都是static的。
 *  > 2. Field :: in\out\err 三个成员变量，分别代表标注输入流(键盘)\标准输出流(显示器)\标准错误输出流(显示器)
 *
 *  方法：
 *  > native long currentTimeMillis() : GMT毫秒数
 *  > void exit(int status) : 退出程序，在图新界面编程中实现程序的退出功能
 *  > void gc() : 请求JVM进行GC
 *  > String getProperty(String key) : 获得java系统\OS系统中属性名为 key 的 value
 */


public class SystemTest {
    public static void main(String[] args) {
        System.out.println("user.name:" + System.getProperty("user.name"));
        System.out.println("user.home:" + System.getProperty("user.home"));
        System.out.println("user.dir:" + System.getProperty("user.dir"));
        System.out.println("java.version:" + System.getProperty("java.version"));
        System.out.println("os.version:" + System.getProperty("os.version"));
        System.out.println("os.home" + System.getProperty("os.home"));

        System.exit(0);
    }
}
