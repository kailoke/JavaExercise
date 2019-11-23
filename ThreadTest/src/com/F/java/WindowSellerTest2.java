package com.F.java;

/** 继承Thread类的方式启动多线程
 *  1.synchronized(同步监视器){共享变量相关代码块}
 *      > 注意共享数据的唯一性，this不适用于此方法
 */
public class WindowSellerTest2 {
    public static void main(String[] args) {
        Seller2 s1 = new Seller2();
        Seller2 s2 = new Seller2();
        Seller2 s3 = new Seller2();

        s1.setName("窗口1");
        s2.setName("窗口2");
        s3.setName("窗口3");

        s1.start();
        s2.start();
        s3.start();
    }
}

class Seller2 extends Thread{
    private static Object ojb = new Object();
    private static int ticket = 100;
    @Override
    public void run() {
        while (true){
            // synchronized(同步监视器:锁，任何类的对象){共享变量相关代码块}
//            synchronized(ojb){
            synchronized(Seller2.class){    // 类也是一个对象 Class class = Seller2.class
                                            // 类名.class 只会随代码加载一次，类名对象唯一
                if (ticket > 0 ){
                    // 程序尚未完成操作，内部阻塞或程序耗时出现了线程安全问题
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票：" + ticket);
                    ticket--;
                    yield();
                }else{
                    break;
                }
            }

        }
    }
}
