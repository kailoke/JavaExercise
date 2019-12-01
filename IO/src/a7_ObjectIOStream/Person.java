package a7_ObjectIOStream;

import java.io.*;

/** 类可序列化要求
 * > 1.实现接口  Serializable[标识接口]/ Externalizable
 * > 2.声明 全局常量(long) 序列版本号 [标识]
 *      > public static final long serialVersionUID
 *      > 未显示指定，则jvm会运行时根据属性指定一个值
 * > 3.类内部属性必须是全可序列化的
 *      > 所有基本数据类型可以序列化
 *      > 使其他引用类型均可序列化即可
 * > 4.不会序列化static transient修饰的属性
 */
public class Person implements Serializable {
    public static final long serialVersionUID = 3315L;

    private String name;
    private int age;
    private Account acct;

    public Person() {
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Account implements Serializable{
    private static final long serialVersionUID = 57433352143L;
    private int balance;
}
