package a2_File_RW;

import org.junit.Test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/** File节点流实现类: FileReader
 * 1. 创建流对象，将已存在的文件(对象)加载进流
 *  > FileReader fr = new FileReader(new File("pathname"))
 * 2. 创建临时数组缓存读取的数据
 *  > char[] buff = new char[1024]  // 2k，可存放1024个字符
 * 3. 读取字符到临时数组
 *  > fr.read(buff)，返回int
 * 4. 关闭外部资源
 *  > fr.close()
 *
 *  * "读"操作的文件对象若不存在则抛出FileNotFoundException
 */


public class A1_FileReader {
    @Test
    // 1.读入一个字符
    public void testFileReader(){
        // 1.实例化File类对象
        File file = new File("src/a2_File_RW/hello.txt");
        // 2.实例化节点流(文件流)
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            // 3.流的方法read()，返回读取的字符长度，若seek达到文件末尾，则返回-1
            int data;
            while ((data = fr.read()) != -1){
                System.out.println((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.流的关闭
            try {
                if (fr != null)
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    // 2.读入字符到内存数组
    public void testFileReaderWithCharArray(){
        File file = new File("src/a2_File_RW/hello.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            char[] buff = new char[5];
            int len;
            while ( (len = fr.read(buff)) != -1){
                // new String(char[] arr,int offset,int len)
                System.out.println(new String(buff, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null)
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}