package a6_StaticProxy;

/**
 * 代理类，静态代理原始类Singer对象,且本代理类仅为一个接口服务
 * 控制对这个对象的访问
 * 代理类和目标对象的类在编译期间确定，不利于程序扩展
 */

public class Manager implements A2_Star {
    // 被代理对象声明
    private Singer singer;

    // 构造\修改Manager代理对象
    Manager(Singer singer) {
        this.singer = singer;
    }
    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public void sing(String name) {
        System.out.println("经理人准备工作...");
        singer.sing(name);  // 代理类决定 是否及何时 调用原始对象的方法
        System.out.println("经理人收钱...");
    }
}
