package a6_otherStream;

import org.junit.Test;
import java.io.*;

/**
 * 1.标准输入、输出流
 *     InputStream System.in   标准输入流，默认从键盘输入
 *     PrintStream System.out  标准输出流，默认从控制台输出
 *     System 类 setIn()/setOut() 修改 in/out 指向
 * 2.打印流
 *     PrintStream \ PrintWriter
 *     提供了一系列重载的print() \ println()
 * 3.数据流 ---> 持久化内存中的数据
 *     DataInputStream  DataOutputStream
 *     操作基本数据类型和String数据
 *      > 读取或写出基本数据类型的变量或字符串
 */
public class otherSteamTest {
    String path = "src/a6_otherStream/";
    public static void main(String[] args) {
        BufferedReader br = null;
        InputStreamReader isr;

        try {
            isr = new InputStreamReader(System.in);
            String data = null;
            char[] cbuf = new char[1024];
            int len;

            while (true){
                System.out.print("输入:");
                try {
//                    data = br.readLine();
                    len = isr.read(cbuf);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                assert data != null;
//                if (data.equalsIgnoreCase("e") || data.equalsIgnoreCase("exit")){
//                    System.out.println("程序结束");
//                    break;
//                }
//                System.out.println("输出:" + data);
                System.out.println(cbuf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // PrintStream
    @Test
    public void testPrintStream(){
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(path + "PrintStream.txt");
            ps = new PrintStream(fos,true);
            System.setOut(ps);
            for (int i = 0; i <255 ; i++) {
                System.out.print((char)i);
                if (i%50 ==0){
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } finally{
            if(ps!=null)
                ps.close();
        }
    }

    // 数据流  写入的数据直接使用文本软件打开会显示乱码
    // 为了将内存中的数据持久化
    @Test
    public void testDataIStream() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(path + "DataIOStream.txt"));
        dos.writeUTF("刘备");
        dos.flush();
        dos.writeInt(23);
//        dos.flush();
        dos.writeBoolean(true);
//        dos.flush();
        dos.close();
    }

    @Test
    public void testDataOStream() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(path + "DataIOStream.txt"));
        // 必须按照写入顺序读取....
        System.out.println(dis.readUTF());
        System.out.println(dis.readInt());
        System.out.println(dis.readBoolean());

        dis.close();
    }

}
