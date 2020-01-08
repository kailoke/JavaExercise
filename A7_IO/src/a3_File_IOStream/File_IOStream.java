package a3_File_IOStream;

import org.junit.Test;
import java.io.*;

/** 字节流
 *  > 非文本文件
 *      > mp3 jpg doc excel ...
 *  > 文本文件(因为编码问题，可能乱码)
 *      > txt .java .c .cpp
 */


public class File_IOStream {
    @Test
    // 字节流输出英文ok，输出中文(Unicode双字节)则出现乱码
    public void testFileInputStream(){
        File file = new File("src/a3_File_IOStream/hello_chinese.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[5];
            int len;
            while ((len = fis.read(buffer)) != -1){
                System.out.println(new String(buffer,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 复制字节流文件
    @Test
    public void copy() {
        File scrFile = new File("src/a3_File_IOStream/屏幕截图.jpg");
        File destFile = new File("src/a3_File_IOStream/屏幕截图_dest.jpg");

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(scrFile);
            fos = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024]; // 1K
            int len;
            int count = 0;
            while ((len = fis.read(buffer)) != -1){
                count++;
                fos.write(buffer,0,len);
            }
            System.out.println("完成图片复制，读取次数：" + count); // 325次，~= 325K
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
