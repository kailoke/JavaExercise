package a2_Synchronization;

import static java.lang.Thread.yield;

/** 多线程同步
 * 一、同步方式：
 *  1.synchronized(同步监视器[monitor]){ 共享的变量数据相关代码 }
 *      > 所有线程必须全部使用同一个对象锁
 *        > this锁适用于implements(线程体对象唯一，实例变量同样唯一)
 *        > 类.class锁适用于 extends(线程体对象多个，共享数据需要声明为static 类变量
 *      > 使被同步的代码变为单线程处理，效率变低
 *  2. synchronized 声明方法，成为同步方法
 *
 * 二、锁的释放：
 *  1.同步内容执行完毕
 *  2.同步内容被主动终止 break;return
 *  3.同步内容出现未处理的 Error/Exception
 *  4.同步代码执行 Object.wait()
 *
 * 三、不会释放锁的操作:
 *  1.Thread.sleep()    Thread.yield() 暂定当前线程不会释放锁
 *  2.当前线程执行时，其他线程调用该 线程对象.suspend() 将当前线程挂起
 *      > 避免使用suspend()和resume()来控制线程
 */


public class A1_SelleTicketsImplements {
    public static void main(String[] args) {
        // Runnable实现类对象
        A1Seller a1Seller = new A1Seller();
        // Thread装载Runnable实现类对象
        Thread t1 = new Thread(a1Seller,"窗口1");
        Thread t2 = new Thread(a1Seller,"窗口2");
        Thread t3 = new Thread(a1Seller,"窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class A1Seller implements Runnable{
    private final Object ojb = new Object();    // 自定义的monitor 需要 final
    private int ticket = 100;   // 对象唯一，则不需要声明为 static

    @Override
    public void run() {
        while (true){
            // synchronized(同步监视器:锁，任何类的对象){共享变 量相关代码块}
            synchronized(ojb){
//            synchronized(Seller.class){
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
