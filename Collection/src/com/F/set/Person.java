package com.F.set;

public class Person implements Comparable{
    private String name;
    private int age = 60;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    @Override
    public int compareTo(Object o) {
        System.out.println("compareTo");
        if (o instanceof Person){
            return Integer.compare(this.age,((Person)o).age);
        }
        throw new RuntimeException("数据类型错误");
    }
}
