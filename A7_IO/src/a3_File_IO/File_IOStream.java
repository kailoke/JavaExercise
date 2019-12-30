package a3_File_IO;

import org.junit.Test;

import java.io.*;

// 字节流(非文本文件 + 文本文件(可能乱码))
public class File_IOStream {
    @Test
    public void testFileInputStream(){
        File file = new File("src/a3_File_IO/hello_chinese.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[5];
            int len;
            while ((len = fis.read(buffer)) != -1){
                System.out.println(new String(buffer,0,len));
            }
            System.out.println("输出完毕");
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

    // 复制图片
    @Test
    public void testCopy() {
        File scrFile = new File("src/a3_File_IO/屏幕截图.jpg");
        File destFile = new File("src/a3_File_IO/屏幕截图_dest.jpg");

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(scrFile);
            fos = new FileOutputStream(destFile);
            // 1K
            byte[] buffer = new byte[1024];
            int len;
            int count = 0;
            while ((len = fis.read(buffer)) != -1){
                count++;
                fos.write(buffer,0,len);
            }
            System.out.println("完成图片复制，读取次数：" + count);
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
