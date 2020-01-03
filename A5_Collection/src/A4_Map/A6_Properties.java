package A4_Map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/** Properties extends HashTable
 * > key 和 value 都是String类型，常用来处理配置(属性)文件
 * > 读取数据：getProperty(String key)
 * > 存入数据：setProperty(String key,String value)
 */
public class A6_Properties {
        public static void main(String[] args) {
            // 1.获得文件流
            FileInputStream fis = null;
            try {
                fis = new FileInputStream("jdbc.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // 2.读取文件流
            Properties pros = new Properties();
            try {
                assert fis != null;
                pros.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(pros.getProperty("name"));
            System.out.println(pros.getProperty("name11"));
            System.out.println(pros.getProperty("password"));

            // 3.关闭文件流
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
