package a6_StaticProxy;

/**
 * 原始对象
 */
public class Singer implements A2_Star {
    private String name;
    private int age;

    public Singer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void sing(String name) {
        System.out.println(this.name + "唱歌：" + name);
    }

    @Override
    public String toString() {
        return "Singer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
