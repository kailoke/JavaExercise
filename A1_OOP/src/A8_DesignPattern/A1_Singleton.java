package A8_DesignPattern;

/** 单例模式：使某类的对象在内存中仅有一个，全局唯一
 * 一、lazyLoad:
 *  > 1.synchronized 双重判断
 *  > 2.volatile 放置指令重排
 *
 * 二、eagerLoad:
 *  > 静态属性 = new Xxx()，通过ClassLoader完成初始化(synchronized load())
 *  > 静态内部类，内部类内部使用eagerLoad 静态属性 = new Xxx()；
 *      > 当调用外部类的get()时才会加载内部类的加载，能起到一定的lazyLoad作用
 *      > 会被反射破坏此单例，(反射调用构造器，在构造器内判断单例是否存在)
 *          # 枚举类不支持反射访问构造器，也是借助ClassLoader完成静态初始化
 */

// LazyLoad
public class A1_Singleton {
    // 1.私有类变量，全局唯一
    private static volatile A1_Singleton singleton;
    // 2.私有构造器
    private A1_Singleton(){}
    // 3.公共的类方法
    public static A1_Singleton getInstance() {
        if (singleton == null){
            synchronized (A1_Singleton.class) {
                if (singleton == null) {
                    singleton = new A1_Singleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        A1_Singleton instance1 = A1_Singleton.getInstance();
        A1_Singleton instance2 = A1_Singleton.getInstance();
        System.out.println(instance1 == instance2);
    }
}
