package a9_AOP;

import a8_StaticProxy.Singer;
import a8_StaticProxy.Star;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** 动态代理
 * 一、根据加载到内存中的被代理类，创建代理类对象
 *
 * 二、代理类对象动态调用被代理类中的同名方法
 *
 */

public class AOPTest {
    public static void main(String[] args) {
        Human proxyInstance = (Human) ProxyMan.getProxyInstance(new SuperMan());
        System.out.println(proxyInstance.getBelief());
        proxyInstance.eat("汉堡");

        System.out.println("*********************");

        Singer singer = new Singer("刘备",20);
        Star ProxyStar = (Star) ProxyMan.getProxyInstance(singer);
        ProxyStar.sing();
    }
}

// 一、创建代理类的对象(通过被代理类对象)
class ProxyMan{
    static Object getProxyInstance(Object obj){ // obj被代理类对象
        ProxyInvocationHandler handler = new ProxyInvocationHandler(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),handler);
    }
}

// 二、多态调用方法(通过被代理类对象)
class ProxyInvocationHandler implements InvocationHandler{
    private Object obj;
    ProxyInvocationHandler(Object obj) {
        this.obj = obj;
    }

    private HumanUtil humanUtil = new HumanUtil();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        humanUtil.method1();

        Object object = method.invoke(obj,args);

        humanUtil.method2();

        return object;
    }
}

interface Human{
    String getBelief();
    void eat(String food);
}

class SuperMan implements Human {

    @Override
    public String getBelief() {
        return "信仰超人!";
    }

    @Override
    public void eat(String food) {
        System.out.println("超人喜欢吃" + food);
    }
}

class HumanUtil{
    public void method1(){
        System.out.println("===========通用方法1===========");
    }
    public void method2(){
        System.out.println("===========通用方法2===========");
    }
}