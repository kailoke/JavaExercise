package a7_ObjectIOStream;

import org.junit.Test;

import java.io.*;

/** 对象流 将java对象(或data数据流)序列化成与平台无关的二进制数据，从而持久化或网络传输。另一节点获得此数据可以反序列化成对象
 *  ObjectOutputStream  ObjectInputStream
 *
 */
public class ObjectIOStream {
    private String path = "src/a7_ObjectIOStream/";

    @Test
    // 序列化
    public void testObjOutputStream(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(path + "ObjectIOStream.dat"));
            oos.writeObject(new String("我爱北京天安门"));
            oos.flush();

            oos.writeObject(new Person("张飞",20));
            oos.flush();

            System.out.println("序列化完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    // 反序列化
    public void testObjInputStream(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(path + "ObjectIOStream.dat"));
            Object obj = ois.readObject();
            Person p = (Person)ois.readObject();
            System.out.println(obj);
            System.out.println(p);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
