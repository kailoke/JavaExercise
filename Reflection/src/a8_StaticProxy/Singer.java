package a8_StaticProxy;

public class Singer implements Star{
    private String name;
    private int age;

    public Singer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void sing() {
        System.out.println(this.name + "歌手唱歌");
    }

    @Override
    public String toString() {
        return "Singer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
