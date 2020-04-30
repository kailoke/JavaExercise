package a2_Synchronization;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock类实现的Lock，"显示"定义同步锁对象实现同步
 * 一、ReentrantLock 显示锁
 *  > java.util.concurrent.locks.Lock接口是控制多个线程对共享资源进行访问的工具
 *  > 每次只能有一个线程对Lock对象加锁，线程开始访问共享资源前必须先获得Lock对象
 *  > 必须搭配 lock.lock() ; try {} finally {lock.unlock()} 当线程出现异常时对锁进行释放
 *
 * 二、synchronized和ReentrantLock对比
 * > 1.显示锁 VS 隐式锁
 *      > synchronized 是隐式锁，进作用域自动加锁，出作用域自动释放锁
 *      > ReentrantLock是显示锁，手动开启 lock.lock()    手动关闭 lock.unlock()
 * > 2.作用域大小
 *      > synchronized 代码块锁和方法锁
 *      > ReentrantLock 代码块锁
 * > 3.性能与效率 : Lock的性能更好(JDK类具有更好的扩展性)
 *      > synchronized依靠jvm实现
 * > 4.锁的公平性
 *      > synchronized 只能是非公平锁
 *      > ReentrantLock可以指定公平锁或非公平锁
 * > 5.线程唤醒：
 *      > synchronized 唤醒一个随机线程 || 唤醒全部线程
 *      > ReentrantLock可以分组唤醒
 */

public class A3_ReentrantLockTest {
    public static void main(String[] args) {

    }
}

class B {
    // 1.定义锁
    private ReentrantLock lock = new ReentrantLock();
    void m() {
        // 2.上锁
        lock.lock();
        try {
            // 需要线程安全的代码
            System.out.println("ReentrantLock Test");
        }finally {
            // 3.finally 进行锁释放
            lock.unlock();
        }
    }
}
