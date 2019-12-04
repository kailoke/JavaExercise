package com.F.a1_threadtest;

/** Thread常用方法
 * 1.   start() 启动当前线程；调用当前线程的run()
 * 2.   run()   子类重写run()，方法体的主进口
 * 3.   currentThread() 静态方法，返回执行当前代码的线程
 * 4.   getName()   获取当前线程的名字
 * 5.   setName()   设置当前线程的名字
 * 6.   yield()     释放当前线程的执行权，但CPU可能再次切换到该线程
 * 7.   jion()      让 线程a.jion() 插入当前线程，当前线程阻塞直到 a线程完成
 * 8.   stop()      deprecated
 * 9.   sleep(long millis)    睡眠 毫秒
 * 10.  isAlive()   判断当前线程是否存活
 */

/** 线程优先级
 * 1. Thread类中定义的常量 ：MAX_PRIORITY = 10;  MIN_PRIORITY = 1; NORM_PRIORITY = 5;
 * 2. getPriority()         获取优先级
 * 3. setPriority(int p)    设置优先级
 *
 */
public class ThreadMethodTest {
}
