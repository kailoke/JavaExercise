package com.F.communication;

/** 线程通信方法,存在于Object类中，为了让任何类作为同步监视器都拥有下列三个方法
 *  >wait()
 *  >notify()
 *  >notifyall()
 *
 * 1.通信方法必须存于在"同步代码块"或"同步方法"中
 * 2.通信方法的调用者必须和 synchronized() 对象一致
 *
 */

public class NumberTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }
}

class Number implements Runnable{
    private int num = 1;
    @Override
    public void run() {
        while (true) {
            synchronized(this) {

                notify();   // 唤醒被wait()的优先级最高线程

                if (num <= 100) {
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + num);
                    num++;

                    // 调用该wait方法的线程进入阻塞状态
                    // 并释放当前线程拥有的所有锁
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}
