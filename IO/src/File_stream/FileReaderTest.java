package File_stream;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {
    @Test
    public void testFileReader(){
        // pathname junitTest相较于module；若是main方法，则相较于project
        // 1.实例化File类对象
        File file = new File("hello.txt");
        // 2.实例化节点流(文件流)
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            // 3.流的方法read()，如果达到文件末尾，则返回-1
            // 方式一
            int data;
            while ((data = fr.read()) != -1){
                System.out.print((char)data);
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
}
