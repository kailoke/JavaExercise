package com.F.set;

import java.util.Objects;

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
            if (Integer.compare(this.age,((Person)o).age) == 0){
                return this.name.compareTo(((Person)o).name);
            }
            return Integer.compare(this.age,((Person)o).age);
        }
        throw new RuntimeException("数据类型错误");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
