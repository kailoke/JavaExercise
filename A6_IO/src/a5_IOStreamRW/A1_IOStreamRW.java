package a5_IOStreamRW;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/** 转换流 : 字节流转换为字符流(仍然是节点流)，实现字符编码集转换(编码+解码)
 * > 字节流中的数据都是字符时，将字节流转换成字符流提高读写效率
 *  new InputStreamReader(InputStream, CharSet) : InputStream ---> Reader
 *  new OutputStreamWriter(OutputStream, CharSet) : OutputStream ---> Writer
 *  * 可指定字符流的编码集，默认CharSet = ANSI
 *
 * > CharsetName 字符集
 *  ANSI American National Standard Institution: 一般指当前系统的默认编码集
 *  Unicode 字符集 仅指明唯一的文字编码
 *  Unicode 编码集 UTF-8 UTF-16等的统称
 */


public class A1_IOStreamRW {
    @Test
    public void copy(){
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            FileInputStream fis = new FileInputStream("src/a5_IOStreamRW/hello_src.txt");
            FileOutputStream fos = new FileOutputStream("src/a5_IOStreamRW/hello_gbk.txt");

            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            osw = new OutputStreamWriter(fos,"GBK");    // 按GBK编码输出，则必须以GBK编码读取

            char[] buff = new char[1024];
            int len;
            while ((len = isr.read(buff)) != -1){
                osw.write(buff,0,len);  // 字符写出
            }
            System.out.println("InputStreamReader/OutputStreamWriter 复制完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null)
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (osw != null)
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
