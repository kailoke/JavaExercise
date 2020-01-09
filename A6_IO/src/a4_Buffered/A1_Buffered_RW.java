package a4_Buffered;

import org.junit.Test;
import java.io.*;

public class A1_Buffered_RW {
    @Test
    public void copy() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            // 创建缓冲流对象：它是处理流，是对节点流的包装
            br = new BufferedReader(new FileReader("src/a4_Buffered/hello_src.txt"));
            bw = new BufferedWriter(new FileWriter("src" + File.separator + "a4_Buffered/hello_dest.txt"));
            String str;
            while ((str = br.readLine()) != null) { // 一次读取字符文本文件的一行字符，ignore LF
                bw.write(str);  // 一次写入一行字符串(ignored LF)
                bw.newLine();   // 写入行分隔符
            }
            bw.flush(); // 刷新缓冲区，缓冲流关闭前会自动刷新缓冲区
            System.out.println("BufferedReader/BufferedWriter 复制完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭IO流对象
            try {
                if (bw != null) {
                    bw.close(); // 关闭缓冲流时,会自动关闭它所包装的底层节点流
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
