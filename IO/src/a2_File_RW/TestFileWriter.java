package a2_File_RW;

import org.junit.Test;

import java.io.*;

public class TestFileWriter {
    @Test
    public void testFileWriter(){
        File file = new File("hello_src.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write("hello world1\n");
            fw.write("hello world2\n");
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

    // 读入，并且写出 = 文件的复制
    @Test
    public void testReaderAndWriter(){
        File srcFile = new File("hello_src.txt");
        File destFile = new File("hello_dest.txt");

        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            char[] cbuf = new char[5];
            int len;

            while ((len = fr.read(cbuf)) != -1){
                fw.write(cbuf,0,len);
            }
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
