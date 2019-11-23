package com.F.annotation;

/** JDK内置三种基本注解
 * > @Override
 * > @Deprecated
 * > @SuppressWarnings
 *
 */


public class AnnotationTest {
    public static void main(String[] args) {

    }
}

@MyAnnotation("hi")
class Person{
    private String name;
    private int age;


    // 8.1  JDK8以前的重复注解写法: 对该Annotation创建数组类，之后填入数组
    @MyAnnotations({@MyAnnotation("1"),@MyAnnotation("1")})
    public Person() {
    }
    // 8.2
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void walk(){
        System.out.println("人在走路");
    }
}

class Student extends Person{
    public Student() {
    }

    public Student(String name, int age) {
        super(name, age);
    }

    @Override

    public void walk() {
        System.out.println("学生在走路");
    }
}

class Generic<@MyAnnotation("T") T>{
    public void show(){
    }
}