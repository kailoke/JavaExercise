package com.F.map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/** Properties extends HashTable
 * > 常用来处理配置文件. 读取\存储
 * > key和value都是String类型
 */
public class PropertiesTest {
        public static void main(String[] args) {
            Properties pros = new Properties();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream("jdbc.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                assert fis != null;
                pros.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(pros.getProperty("name1"));
            System.out.println(pros.getProperty("password"));

            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
