package com.F.Exercise;

/** 实现Runnable的方式开启多线程
 *  解决线程安全问题
 * 1.synchronized(同步监视器){共享的变量数据相关代码}
 *  > 所有线程必须全部使用同一个对象锁, 当前对象this适用于implements
 *  > 使被同步的代码变为单线程处理，效率变低
 *
 *
 */

import static java.lang.Thread.sleep;
import static java.lang.Thread.yield;

public class WindowSellerTest {
    public static void main(String[] args) {
        Seller seller = new Seller();

        Thread t1 = new Thread(seller);
        Thread t2 = new Thread(seller);
        Thread t3 = new Thread(seller);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Seller implements Runnable{
    Object ojb = new Object();
    private int ticket = 100;
    @Override
    public void run() {
        while (true){
            // synchronized(同步监视器:锁，任何类的对象){共享变 量相关代码块}
//            synchronized(ojb){
            synchronized(Seller.class){
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
