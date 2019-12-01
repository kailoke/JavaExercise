package a8_RandomAccess;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/** RandomAccessFile    任意访问文件"内部"
 * > 1.继承于 java.lang.Object，实现了 Input / Output 接口
 * > 2.RandomAccessFile 既可以做输入流，也可做输出流
 * > 3.@NotNull access mode
 *      > r
 *      > rw
 *      > rwd
 *      > rws
 * > 4.作为输出流时，直接修改文件内的内容
 *      > 若写出文件不存在，则创建
 *      > 若写出文件存在，从文件内部头开始覆盖，而不是直接覆盖文件
 * > 5.拥有光标，arf.seek(long seek)
 */
public class RandomAccessTest {
    String path = "src/a8_RandomAccess/";
    @Test
    // 输入输出流测试
    public void testRAF(){

        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            raf1 = new RandomAccessFile(path + "屏幕截图.jpg","r");
            raf2 = new RandomAccessFile(path + "屏幕截图_copy.jpg","rw");


            byte[] buffer = new byte[1024];
            int len;
            while ((len = raf1.read(buffer)) != -1){
                raf2.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf1 != null)
                raf1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (raf2 != null)
                    raf2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 初始化文本
    @Test
    public void createHello(){
        FileWriter fw = null;
        try {
            fw = new FileWriter(path + "hello.txt");
            fw.write("abcdefghijklmn");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    // 文本测试
    public void testRAFTXT(){

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(path + "hello.txt","rw");
            raf.seek(3);
            raf.write("zzz".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf != null)
                    raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 插入文本
    @Test
    public void testInsert(){
        RandomAccessFile rsf = null;
        try {
            rsf = new RandomAccessFile(path + "hello.txt","rw");
            rsf.seek(3);
            StringBuilder sb = new StringBuilder();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = rsf.read(buffer)) != -1){
               sb.append(new String(buffer,0,len));
            }
            rsf.seek(3);
            rsf.write("zzz".getBytes());
            rsf.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (rsf != null) {
                try {
                    rsf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
