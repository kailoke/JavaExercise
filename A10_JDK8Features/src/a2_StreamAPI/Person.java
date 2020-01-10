package a2_StreamAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    static List<Person> getPersons(){
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("刘备",15));
        persons.add(new Person("关羽",16));
        persons.add(new Person("张飞",22));
        persons.add(new Person("孙策",19));
        persons.add(new Person("吕布",20));
        persons.add(new Person("马超",18));

        return persons;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
