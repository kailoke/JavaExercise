package a1_Thread;

/** Thread其他
 * 一、 Constructor:
 *  > 1. Thread()
 *  > 2. Thread(String name)
 *  > 3. Thread(Runnable target)
 *  > 4. Thread(Runnable target,String name)
 *
 * 二、 Method:
 * > 1. void start() 启动当前线程；调用当前线程的run()
 * > 2. void run()   子类重写run()，方法体的主进口
 * > 3. static Thread currentThread() 返回当前线程；Thread子类=this，常用于主线程和Runnable实现类
 * > 4. getName()   获取当前线程的名字
 * > 5. setName()   设置当前线程的名字
 * > 6. yield()     释放当前线程的执行权，但CPU可能再次切换到该线程
 * > 7. join()      让 线程a.join() 插入当前线程，当前线程阻塞直到 a线程执行完毕
 * > 8. stop()      deprecated
 * > 9. sleep(long millis)  阻塞当前线程一定时间
 * > 10. isAlive()   判断当前线程是否存活
 *
 * 三、线程优先级
 * > 1. 同优先级线程组成 FIFO 队列(先进先出)
 * > 2. 低优先级线程只是获得调度概率低，并非一定比低优先级队列后执行
 * > 3. 优先级相关的属性和方法：
 *      > Thread类中定义的常量 ：MAX_PRIORITY = 10;  MIN_PRIORITY = 1; NORM_PRIORITY = 5;
 *      > getPriority()         获取优先级
 *      > setPriority(int p)    设置优先级
 *
 * 四、线程分类
 * > 1. 守护线程: Daemon，服务用户进程，例如GC。当JVM中仅剩守护线程，当前JVM退出
 * > 2. 用户线程: 线程启动前，线程体对象.setDaemon(true) 可以称为守护线程
 */


public class A0_Thread {
    public static void main(String[] args) {
    }
}
