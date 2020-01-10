package a4_ClassStructures;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

@MyAnnotation(value = "hi123")
public class Person extends Creature<String>
                    implements Comparable<String>,MyInterface
{
    private String name;
    int age;
    public int id;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    @MyAnnotation
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @MyAnnotation
    private String show(String nation) throws FileNotFoundException, FileAlreadyExistsException {
        System.out.println("我的国籍是：" + nation);
        return nation;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    @Override
    public void info() {
        System.out.println("Person info()");
    }

    private static void show2(){
        System.out.println("我是show2");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
