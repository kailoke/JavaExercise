package class_File;


import org.junit.Test;

import java.io.File;

/** File类  << java.io
 * 一、File的对象，代表一个文件或一个文件目录
 * 二、路径
 *  > 相对路径:相对于所属module的路径
 *  > 绝对路径
 *
 * 三、构造器    实例化对象
 *  > File(pathname)
 *  > File(Parent path,Child path)
 *  > File(File, Child path)
 *
 */
public class FileTest {

    @Test
    // 构造器
    public void getFileObj(){
        // public static final separator
        // File.separator

        // 构造器1
        File file1 = new File("hello.txt");
        File file2 = new File("F:\\Projects\\JavaExcercise\\IO\\hello.txt");

        // 构造器2
        File file3 = new File("F:\\Projects\\JavaExcercise","IO");
        System.out.println(file3);  //F:\Projects\JavaExcercise\IO

        // 构造器3
        File file4 = new File(file3,"hello.txt");

    }

    @Test
    // 获取方法测试
    public void testGet(){
        File file1 = new File("hello.txt");
        File file2 = new File("F:\\Projects\\JavaExcercise\\IO\\hello.txt");

        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(file1.lastModified());
    }
}
