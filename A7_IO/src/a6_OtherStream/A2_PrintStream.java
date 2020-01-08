package a6_OtherStream;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/** 打印流 : 将`基本数据类型`的数据格式转换为`字符串`输出
 * 一、PrintStream(System.out实例) \ PrintWriter
 *  > 提供了一系列重载的print() \ println()，用于多种数据输出
 *  > PrintStream和PrintWriter的输出不会抛出IOException异常
 *  > PrintStream和PrintWriter有自动flush功能
 *  > PrintStream 打印的所有字符都使用平台的默认字符编码转换为字节。
 *    在需要写入字符而不是写入字节的情况下，应该使用 PrintWriter 类。
 */

public class A2_PrintStream {
    @Test
    // PrintStream
    public void printStream(){
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream("src/a6_OtherStream/PrintStream.txt");
            // 创建打印输出流,设置为自动刷新模式(写入换行符或字节 '\n' 时都会刷新输出缓冲区)
            ps = new PrintStream(fos,true);
            // 把标准输出流(控制台输出)改成输出至文件
            System.setOut(ps);
            for (int i = 0; i < 255 ; i++) {
                // 输出实参至 流中
                System.out.print((char)i);
                // 换行
                if (i % 50 ==0){
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } finally{
            if(ps!=null) {
                ps.close();
            }
        }
    }
}
