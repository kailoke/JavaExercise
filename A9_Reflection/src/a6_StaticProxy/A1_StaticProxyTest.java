package a6_StaticProxy;

public class A1_StaticProxyTest {
    public static void main(String[] args) {
        A2_Star star = new Manager(new Singer("关羽",18));
        star.sing("三国演义");
    }
}
