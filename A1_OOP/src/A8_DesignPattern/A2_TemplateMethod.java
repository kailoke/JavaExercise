package A8_DesignPattern;

/** TemplateMethod(Hook)
 * 父类中的一部分实现是确定，一部分实现是不确定
 * 将不确定的部分暴露出去，让子类实现；根据不同的子类实现，调用不同的不确定的方法(也可以是父类对象则为空调用)
 *
 * > 1. 抽象模板类中声明抽象方法，并在模板类中 钩子处 调用模板方法(对象的方法多态性)
 * > 2. 某些子类继承模板类，实现抽象方法；某些子类实现接口模板，实现抽象方法
 * > 3. 父类引用指向(不同的)子类对象，父类调用整体流程方法，则在 钩子处 调用具体子类的实现方法
 */

public abstract class A2_TemplateMethod {
    // 1.暴露不确定的部分，protected让子类均可访问
    protected abstract void hook();
    // 2.public final方法，不让子类重写      private 自动 +final
    public final void templateMethod() {
        System.out.println("模板方法调用1");
        // 父类调用此部分
        this.hook();
        System.out.println("模板方法调用2");
    }

    public static void main(String[] args) {
        A2_TemplateMethod concrete1 = new Concrete1();
        A2_TemplateMethod concrete2 = new Concrete2();

        concrete1.templateMethod();
        System.out.println();   // console segmentation
        concrete2.templateMethod();
    }
}

class Concrete1 extends A2_TemplateMethod{
    @Override
    public void hook() {
        System.out.println("Concrete1方法调用");
    }
}
class Concrete2 extends A2_TemplateMethod{
    @Override
    public void hook() {
        System.out.println("Concrete2方法调用");
    }
}