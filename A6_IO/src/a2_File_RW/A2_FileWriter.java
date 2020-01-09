package a2_File_RW;

import org.junit.Test;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;

/** Writer节点流实现类 : FileWriter
 * 1. 创建流对象，将已存在的文件(对象)加载进流
 *  > FileWriter fw = new FileWriter(new File("pathname"))
 * 2. 将数据写入
 *  > fw.write("content")
 * 3. 关闭外部资源
 *  > fw.close()
 *
 *  * "写"操作将自动创建创建不存在的 文件 或 路径
 *  * 所有的 "写" 操作均是覆盖原文件从新写入
 */


public class A2_FileWriter {
    @Test
    // 1.写入
    public void fileWriter(){
        File file = new File("src\\a2_File_RW\\hello_src.txt");

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write("hello world1\n");
            fw.write("hello world2\n");
            fw.write("hello world3\n");

            System.out.println("FileWriter 写入完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null)
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 2.文件的复制 = 读入->写出
    @Test
    public void testReaderAndWriter(){
        File parent = new File("src\\a2_File_RW");
        File srcFile = new File(parent,"hello_src.txt");
        File destFile = new File(parent,"hello_dest.txt");

        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            char[] buff = new char[5];
            int len;

            while ((len = fr.read(buff)) != -1){
                System.out.print("读取长度 : " + len);
                System.out.println("\t读取内容 : " + Arrays.toString(buff));
                fw.write(buff,0,len);
            }

            System.out.println("FileReader/FilerWriter 复制文件完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null)
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fr != null)
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}