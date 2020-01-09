package a7_ObjectIOStream;

import java.io.*;

/**
 * 序列化测试类
 */

public class T_Person implements Serializable {
    public static final long serialVersionUID = 3315L;

    private String name;
    private int age;
    private Account acct;

    public T_Person() {
    }
    public T_Person(String name, int age) {
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
