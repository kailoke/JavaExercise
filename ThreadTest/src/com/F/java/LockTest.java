package com.F.java;

import java.util.concurrent.locks.ReentrantLock;

/** 解决线程安全问题的方式三：Lock  ReentrantLock
 *  Lock 方式：手动锁定，手动解锁
 *  synchronized 方式： 自动锁定，自动解锁
 */

public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Window implements Runnable{
    private int ticket = 100;

    // 1.实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            try {   // try的必要性：不在乎内部代码是否完整执行，均执行finally解锁
                lock.lock();
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }finally {
                lock.unlock();
            }
        }
    }
}


