package a8_StaticProxy;

public class StaticProxyTest {
    public static void main(String[] args) {
        Star star = new Manager(new Singer("关羽",18));
        star.sing();
    }
}
