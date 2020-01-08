package a6_OtherStream;

import org.junit.Test;

import java.io.*;

/** 数据流(处理流) ---> 持久化内存中的`基本数据类型`和`String`的数据
 * DataInputStream / DataOutputStream 套接 InputStream 和 OutputStream
 *  > 操作基本数据类型和String数据
 *  > 读取或写出基本数据类型的变量或字符串
 *  > 使用数据流读取数据文件时，需要按写入的顺序依次读取
 *
 *  boolean readBoolean()       byte readByte()
 *  char readChar()             float readFloat()
 *  double readDouble()         short readShort()
 *  long readLong()             int readInt()
 *  String readUTF()            void readFully(byte[] b)
 *  * 上述方法read相应改为write即为写出方法
*/


public class A3_DataIOStream {
    @Test
    // 写出基本数据
    public void dataOutputStream() throws IOException {
        // 处理流(节点流(文件对象))
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/a6_OtherStream/DataIOStream.txt"));
        dos.writeUTF("刘备");
        dos.flush();
        dos.writeInt(23);
//        dos.flush();
        dos.writeBoolean(true);
//        dos.flush();
        dos.close();
    }

    @Test
    // 读取基本数据
    public void dataInputStream() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("src/a6_OtherStream/DataIOStream.txt"));
        // 必须按照写入顺序读取....
        System.out.println(dis.readUTF());
        System.out.println(dis.readInt());
        System.out.println(dis.readBoolean());

        dis.close();
    }
}
