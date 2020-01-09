package a7_ObjectIOStream;

import org.junit.Test;

import java.io.*;

/** 对象流 : 用于存储和读取基本数据类型数据或对象的处理流。
 * 一、对象流 implements Serializable
 *  > 将java对象(或data数据流)序列化成与平台无关的二进制数据，从而持久化或网络传输
 *    另一节点获得此二进制流，就可以反序列化成内存对象
 *  > 序列化：ObjectOutputStream类保存基本类型数据或对象的机制
 *  > 反序列化：ObjectInputStream类读取基本类型数据或对象的机制
 *  > 不能序列化 static / transient 修饰的变量
 *
 * 二、方法
 * > writeObject()
 * > Object readObject()    实际使用需要强转，还是类似DataIOStream
 *
 * 三、序列化要求
 *  > 1.实现接口  Serializable[空标识接口]/ Externalizable
 *  > 2.声明 全局常量(long) 序列版本号 [标识]
 *      > 序列化版本标识符 : public static final long serialVersionUID，固定类的序列化版本
 *      > 若未显示指定，则jvm根据类的内部细节自动生成一个值：结果is--若类的实例变量改变，则serialVersionUID也改变
 *  > 3.类内部属性必须是全可序列化的
 *      > 所有基本数据类型可以序列化
 *      > 使其他引用类型均可序列化即可
 *  > 4.不能序列化static transient修饰的属性
 */


public class ObjectIOStream {
    private String path = "src/a7_ObjectIOStream/";

    @Test
    // 序列化
    public void testObjOutputStream(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(path + "ObjectIOStream.dat"));
            oos.writeObject("我爱北京天安门");
            oos.flush();    // 测试不必要，建议还是加上

            oos.writeObject(new T_Person("张飞",20));
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
            T_Person p = (T_Person)ois.readObject();
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
