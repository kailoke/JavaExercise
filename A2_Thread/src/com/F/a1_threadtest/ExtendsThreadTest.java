package com.F.a1_threadtest;

/** 线程的创建 :继承Thread类
 * 1.创建一个继承于Thread类的子类
 * 2.重写Thread类的run方法  -->将此线程执行的操作声明在run()中
 * 3.创建一个Thread子类对象
 * 4.通过对象调用start()方法
 *  > 每个线程有一个 ThreadState 通过此值判断状态
 */
public class ExtendsThreadTest {
    public static void main(String[] args) {
        primeThread pt = new primeThread();
        pt.start(); // 线程开始执行；JVM调用 this 线程的 Run()方法
//        pt.run();   // 不会开启新线程，当做普通类处理
        for (int i = 0; i < 100; i++) {
            if (i % 2 !=0){
                System.out.println("main\t" + i);
            }
        }

        primeThread pt2 = new primeThread();
        pt2.start();
    }

    // 匿名类的匿名对象
    public void startTwo(){
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 ==0){
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 !=0){
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        }.start();
    }
}

class primeThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 ==0){
                System.out.println("prime\t" + i);
            }
        }
    }
}
