package a5_IOStreamRW;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/** 转换流
 *  FileInputStream 字节流 ---> InputStreamReader 字符流
 *  FileOutputStream 字节流---> OutputStreamWriter 字符流
 *
 * > Charset
 *  ANSI American National Standard Institution: 一般指当前系统的默认编码集
 *  Unicode 字符集 仅指明唯一的文字编码
 *  Unicode 编码集 UTF-8 UTF-16等的统称
 */
public class IOStreamRWTest {
    @Test
    public void test(){
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            FileInputStream fis = new FileInputStream("src/a5_IOStreamRW/hello_gbk.txt");
            FileOutputStream fos = new FileOutputStream("src/a5_IOStreamRW/hello_gbk.txt");

            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            osw = new OutputStreamWriter(fos,"GBK");

            char[] cbuf = new char[1024];
            int len;
            while ((len = isr.read(cbuf)) != -1){
                osw.write(cbuf,0,len);
            }
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
