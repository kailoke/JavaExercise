package A2_Annotation;

/** JDK内置三种基本注解
 * > @Override          限定重写父类方法，该注解只能用于方法
 * > @Deprecated        修饰(类、属性、方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择
 * > @SuppressWarnings  抑制编译器警告
 */
public class A2_Basic3Annotations {
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        int a = 10;
    }
    @Deprecated
    public void print(){
        System.out.println("过时的方法");
    }
    @Override
    public String toString() {
        return "重写的toString方法()";
    }
}
