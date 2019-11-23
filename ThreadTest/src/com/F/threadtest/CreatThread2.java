package com.F.threadtest;

/** 线程的创建：实现Runnable接口
 * 1.实现Runnable接口
 * 2.重写run方法
 * 3.调用Thread构造器传入实现类的对象，创建Thread类对象
 * 4.通过Thread类的对象.start()
 *
 *  ~~创建两种线程的方式
 *  1.继承Thread的局限性:Java单继承，implements可以多实现
 *  2.实现接口的实现类对象，堆中的该对象在其运行中的线程自动共享
 *
 *  Thread 也 implements Runnable，即任何线程类都需要重写 run()方法
 *
 */
public class CreatThread2 {
    public static void main(String[] args) {
        new Thread(new CreateThread2()).start();
        new Thread(new CreateThread2()).start();    // 根据对象可直接启动线程

    }
}

class CreateThread2 implements Runnable{

    @Override
    public void run() {
        System.out.println("实现Runnable的线程");
    }
}
