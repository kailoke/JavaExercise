package a8_RandomAccessFile;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/** RandomAccessFile    任意访问文件"内部"，可读可写
 * > 1.extends java.lang.Object implements DataInput / DataOutput 接口
 * > 2.RandomAccessFile 既可以做输入流，也可做输出流↑[可读可写]
 * > 3.创建实例时需指定 access mode 访问模式
 *      > r     readOnly
 *      > rw    readWrite
 *      > rwd   readWrite，同步文件内容的更新
 *      > rws   readWrite，同步文件内容和元数据的根据
 * > 4.作为输出流时，直接修改文件内的内容
 *      > 若写出文件不存在，则创建
 *      > 若写出文件存在，从文件内部`文件记录指针`开始覆盖写入，而不是直接覆盖文件
 * > 5.文件记录指针
 *      > long getFilePointer() : 获取文件记录指针位置
 *      > void seek(long pos)   : 移动文件记录指针位置
 */


public class A1_RandomAccessFile {
    private String path = "src/a8_RandomAccessFile/";

    @Test
    public void copy(){
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
            System.out.println("RandomAccessFile 完成");
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
    // raf.write(Byte[])覆盖写入
    public void override(){
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(path + "hello.txt","rw");
            raf.seek(3);
            raf.write("zzz".getBytes());    // 覆盖写入

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

    // 插入文本：1.缓存插入位置之后的所有内容 2.在插入位置写入内容 3.续写缓存内容
    @Test
    public void insert(){
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(path + "hello.txt","rw");
            // 1.缓存
            raf.seek(3);
            StringBuilder sb = new StringBuilder();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = raf.read(buffer)) != -1){
               sb.append(new String(buffer,0,len));
            }
            // 2.插入
            raf.seek(3);
            raf.write("zzz".getBytes());
            // 3.续写
            raf.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}