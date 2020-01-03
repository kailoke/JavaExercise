package A3_Set;

import java.util.Objects;

public class T_Person implements Comparable{
    private String name;
    private int age;

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

    public T_Person() {
    }
    public T_Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        System.out.println("compareTo invoke");
        if (o instanceof T_Person){
            if (this.age == ((T_Person) o).age){
                return this.name.compareTo(((T_Person)o).name);
            }
            return Integer.compare(this.age,((T_Person)o).age);
        }
        throw new RuntimeException("数据类型错误");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        T_Person TPerson = (T_Person) o;
        return age == TPerson.age &&
                name.equals(TPerson.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
