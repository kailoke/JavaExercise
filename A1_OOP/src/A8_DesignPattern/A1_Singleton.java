package A8_DesignPattern;

/**
 * 单例模式：使某类的对象在内存中仅有一个，全局唯一
 */

// LazyLoad
public class A1_Singleton {
    // 1.私有类变量，全局唯一
    private static A1_Singleton singleton;
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
