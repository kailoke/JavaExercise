package a1_Thread;

/** 创建线程方式二：实现Runnable接口
 * 1. class Xxx implements Runnable and Override run()
 * 2. new Thread(Runnable target)创建线程体对象，调用线程体对象的 start()
 * 3. 核心是 线程体对象.start() 进行线程创建和run()调用，run()只是一个普通方法
 *
 *  ~~创建两种线程的方式比较
 *  1. Thread 也 implements Runnable，即任何线程类都是重写 Runnable 接口里的 abstract run()
 *  2. 继承Thread重写Thread类的run(); 实现Runnable重写Runnable接口的run()
 *  3. 实现的好处
 *      > 局限性 : Java单继承，implements可以多重实现
 *      > 多个线程可以共享同一个接口实现子类的对象(唯一)，非常适合多个相同线程来处理同一份资源
 */
public class A1_ImplementsRunnable {
    public static void main(String[] args) {
        new Thread(new CreateThread2()).start();    // new Thread(Runnable target) 创建线程体对象
        new Thread(new CreateThread2()).start();    // 线程体对象invoke start()
    }
}

class CreateThread2 implements Runnable{

    @Override
    public void run() {
        System.out.println("实现Runnable的线程");
    }
}
