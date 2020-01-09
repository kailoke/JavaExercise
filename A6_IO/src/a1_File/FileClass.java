package a1_File;


import org.junit.Test;

import java.io.File;

/** File类  ∈ java.io
 * 一、File：文件和路径的抽象，可以操作文件或路径，但不能访问文件内容
 *      > File的对象，代表一个文件或一个目录，这个文件或目录并不一定真实存在
 *
 * 二、作用：作为流的构造器File参数，path的终点可以是路径或文件
 *
 * 三、File构造器
 *  > File(pathname)
 *  > File(Parent path,Child path)
 *  > File(File, Child path)
 *
 * 四、路径分隔符
 *  > 系统级的路径分割符常量  public static final separator
 *  > 多平台建议使用  File.separator
 *
 * 五、常用方法：
 * > 创建路径： boolean mkdir()    boolean mkdirs() 强制创建
 * > 创建文件： boolean createNewFile()
 * > 删： boolean delete()
 * > 重命名： boolean renameTo(File dest)
 *
 * 六、相对路径 pathname
 *  > junitTest 相较于 module
 *  > main()， 相较于project
 */


public class FileClass {
    @Test
    // File构造方法
    public void getFileObj() {
        // 1.File(pathname)
        File file1 = new File("hello.txt");
        System.out.println("file1" + file1);
        File file2 = new File("F:\\Projects\\JavaExercise\\IO\\hello.txt");
        System.out.println("file2" + file2);

        // 2.File(Parent path,Child path)
        File file3 = new File("F:\\Projects\\JavaExercise","IO");
        System.out.println("file3" + file3);  //F:\Projects\JavaExcercise\IO

        // 3.File(File, Child path)
        File file4 = new File(file3,"hello.txt");
        System.out.println("file4" + file4);
    }

    @Test
    // File获取方法 : 当硬盘中存在真实文件或目录时，创建File对象时，各个属性会显示赋值；否则处路径信息外的属性均为默认值
    // 返回值均为 String，操作路径即需要String方法
    public void getMethod() {
        File file1 = new File("hello.txt");
        File file2 = new File("F:\\Projects\\JavaExercise\\IO\\hello.txt");

        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(file2.lastModified());
    }
}
