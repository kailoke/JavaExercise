package a3_Communication;

/** 锁通信
 * 线程通信方法仅能声明在Object类中：为了让任何对象都能作为"同步监视器"
 *  > Object.wait()         monitor.wait() 令当前线程Block，释放锁；直到被唤醒
 *  > Object.notify()       随机唤醒一个优先级最高的处于Block状态的锁
 *  > Object.notifyAll()    唤醒全部处于 Block 状态的锁，结束他们的Block状态 --> Runnable
 *  !! 必须使用 锁对象 执行通信方法，否则throw IllegalThreadStateException
 *
 * 1.通信方法的调用者必须和 synchronized(monitor) 对象一致
 * 2.通信方法必须存于在"同步代码块"或"同步方法"中
 * 3.重新被唤醒的线程将从断点处继续执行代码
 */

public class CommunicationTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number,"线程1");
        Thread t2 = new Thread(number,"线程2");

        t1.start();
        t2.start();
    }
}

// 实现两个线程交替打印1-100
class Number implements Runnable{
    private int num = 1;
    private final Object lock = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized(lock) {
                lock.notify();   // 唤醒被wait()的优先级最高线程
                if (num <= 100) {
                    // Object.wait() ：当前线程释放锁，并进入阻塞状态
                    // 前置wait()，A线程==100挂起并释放锁后，B线程进入能打印出101
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":" + num);
                    num++;
                    try {
                        Thread.sleep(40);
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
