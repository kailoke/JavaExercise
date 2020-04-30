package a7_DynamicProxy;

import a6_StaticProxy.Singer;
import a6_StaticProxy.A2_Star;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** JDK动态代理，只提供接口的代理，不支持类的代理
 * 一、思想：用户通过代理类来调用其它对象的方法，并且是在程序运行时根据需要(原始对象)动态创建目标类的代理对象
 *          接口声明的所有方法都被转移到代理 调用处理器 集中处理，更加灵活和统一处理众多的方法
 *
 * 二、java.lang.reflect.Proxy
 *  > 所有动态代理类的父类，用于生成接口的代理类或代理对象
 *  > public static Class<?> getProxyClass(ClassLoader loader,Class<?>... interfaces)
 *    * 用于生成代理的Class对象(类加载对象)
 *  > public static Object newProxyInstance(ClassLoader loader,CLass<?>... interfaces,InvocationHandler h)
 *    * 用于生成代理对象
 *      # loader:Classloader对象，加载 动态生成的代理类。
 *      # interfaces:需要代理的接口(目标对象的接口:obj.getClass().getInterfaces())
 *      # h:InvocationHandler实例
 *
 * 三、InvocationHandler：完成动态代理的逻辑执行(invoke())
 *  > 接口，需要自定义实现
 *  > new InvocationHandler() { @Override Object invoke(Object proxy,Method method,Object[] args) }
 *      > invoke：代理对象调用代理方法，会回调invoke()
 *      # proxy:  已经创建的代理对象引用，一般不会使用
 *      # method: 正在被调用的方法对象
 *      # args:   正在被调用的方法的参数
 *
 */
public class DynamicProxy {
    public static void main(String[] args) {
        // 根据原始对象，动态创建其所有实现接口的代理类对象
        Human proxyInstance = (Human) ProxyMan.getProxyInstance(new SuperMan());
        System.out.println(proxyInstance.getBelief());
        proxyInstance.eat("汉堡");

        System.out.println();   // console segmentation

        A2_Star ProxyStar = (A2_Star) ProxyMan.getProxyInstance(new Singer("刘备",20));
        ProxyStar.sing("Shape of You");
    }
}

// 1、通过原始对象生成 代理调用处理器，集中处理原始对象的方法
class ProxyInvocationHandler implements InvocationHandler{
    // 1.原始对象声明
    private Object obj;
    // 2.构造原始对象的 代理调用处理器对象
    ProxyInvocationHandler(Object obj) {
        this.obj = obj;
    }
    // 3.重写invoke():使用原始类的反射Method类(正在被调用).invoke(原始对象,参数)调用目标对象的方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(obj,args);
    }
}

// 2、通过原始类对象ProxyInvocationHandler，创建代理类的对象
class ProxyMan{
    static Object getProxyInstance(Object obj){ // obj被代理类对象
        ProxyInvocationHandler handler = new ProxyInvocationHandler(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),handler);
    }
}

// 接口
interface Human{
    String getBelief();
    void eat(String food);
}

// 原始类
class SuperMan implements Human{

    @Override
    public String getBelief() {
        return "信仰超人!";
    }

    @Override
    public void eat(String food) {
        System.out.println("超人喜欢吃" + food);
    }
}