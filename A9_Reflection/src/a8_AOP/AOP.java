package a8_AOP;

import a6_StaticProxy.Singer;
import a6_StaticProxy.A2_Star;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class AOP {
    public static void main(String[] args) {
        Human proxyInstance = (Human) ProxyMan.getProxyInstance(new SuperMan());
        System.out.println(proxyInstance.getBelief());  // println最后发生，结果已在回调中获取
        System.out.println();
        proxyInstance.eat("汉堡");    // 回调中println()

        System.out.println();   // console segmentation

        A2_Star ProxyStar = (A2_Star) ProxyMan.getProxyInstance(new Singer("张飞",22));
        ProxyStar.sing("Better Man");
    }
}

// 一、通过原始对象、通用方法类对象 创建 有回调逻辑的 代理调用处理器
class ProxyInvocationHandler implements InvocationHandler{
    // 1. 通用类的对象
    private HumanUtil humanUtil = new HumanUtil();

    // 2. 回调类的对象
    private Object obj;
    ProxyInvocationHandler(Object obj) {
        this.obj = obj;
    }
    // 3. 重写invoke逻辑： 通用类对象方法 + 回调类对象方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 回调函数 之前 的通用处理
        humanUtil.method1();
        // 使回调函数动态调用
        Object object = method.invoke(obj,args);
        // 回调函数 之后 的通用处理
        humanUtil.method2();
        return object;  // 返回指定的回调函数结果
    }
}

// 二、Proxy代理类实例
class ProxyMan{
    static Object getProxyInstance(Object obj){ // obj被代理类对象
        ProxyInvocationHandler handler = new ProxyInvocationHandler(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),handler);
    }
}


// 通用方法，在InvocationHandler 回调函数 之前\之后可插入
class HumanUtil{

    void method1(){
        System.out.println("===========Human原始对象通用方法1===========");
    }

    void method2(){
        System.out.println("===========Human原始对象通用方法2===========");
    }
}

// 接口
interface Human{
    String getBelief();
    void eat(String food);
}

// 原始类
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