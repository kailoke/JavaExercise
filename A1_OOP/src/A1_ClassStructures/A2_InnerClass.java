package A1_ClassStructures;

/** InnerClass
 * 一、静态内部类
 *
 *
 * 二、实例内部类
 *
 *
 *
 * 三、匿名内部类
 *
 *
 *
 * 四、局部内部类
 *
 *
 */
public class A2_InnerClass {
    public static void main(String[] args) {
        // 非静态内部类
        OutClass out = new OutClass();
        OutClass.InnerA inner = out.new InnerA();
        OutClass.InnerA inner2 = new OutClass().new InnerA();
        inner.methodA1();
        System.out.println("**************");
        inner2.methodA2();

        // 静态内部类
        OutClass.InnerB innerB = new OutClass.InnerB();
    }
}

class OutClass{
    String name;
    int age;


    // 局部内部类
    public void method(){
        final int[] num = {1};
        System.out.println("OutClass method");

        // 只能非静态
        class InnerC{
            public void methodC(){
                System.out.println(num);
            }
        }

        InnerC c = new InnerC();
        c.methodC();
    }

    // 静态内部类
    static class InnerB{

    }

    // 非静态成员内部类
    class InnerA{
        String name;
        int age;

        public void methodA1(){
            System.out.println("InnerClass MethodA1");
            OutClass.this.name = "外部名";
        }
        public void methodA2(){
            System.out.println("InnerClass methodA2");
            OutClass.this.method();
        }
    }
}