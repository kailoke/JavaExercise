package a7_DynamicProxy;

import a6_StaticProxy.Singer;
import a6_StaticProxy.A2_Star;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** 动态代理 Proxy类
 * 一、思想：客户通过代理类来调用其它对象的方法，并且是在程序运行时根据需要(原始对象)动态创建目标类的代理对象
 *          接口声明的所有方法都被转移到代理 调用处理器 集中处理，更加灵活和统一处理众多的方法
 *
 * 二、Proxy
 *  > Proxy:代理的操作类，是所有动态代理类的父类
 *  > 通过此类为一个或多个接口动态地生成实现类
 *
 * 三、创建 原始类对象 代理调用处理器
 *  new InvocationHandler() { @Override Object invoke(Object obj,Method name,Object[] args) }
 * 四、根据 原始类加载器、原始类实现接口 创建其动态代理类的静态方法   Proxy.
 *  > static Class<?> getProxyClass(ClassLoader loader,Class<?>... interfaces) 创建一个动态代理类所对应的Class对象
 * 五、根据 原始类加载器、原始类实现接口、原始类对象代理调用处理器，创建其动态代理对象的静态方法  Proxy[].
 *  > static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h)
 *     直接创建一个InvocationHandler接口实现类实例(动态代理对象)
 */

public class DynamicProxyTest {
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

// 一、通过原始对象生成 代理调用处理器，集中处理原始对象的方法
class ProxyInvocationHandler implements InvocationHandler{
    // 1.原始对象声明
    private Object obj;
    // 2.构造原始对象的 代理调用处理器对象
    ProxyInvocationHandler(Object obj) {
        this.obj = obj;
    }
    // 3.重写invoke()，使用原始类的反射Method类.invoke(原始对象,参数)动态调用方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(obj,args);
    }
}

// 二、通过原始类对象ProxyInvocationHandler，创建代理类的对象
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