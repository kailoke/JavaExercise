package a1_Thread;

/** 线程的生命周期 : Thread::private enum State
 * 新建：NEW   Thread或其子类对象被声明+创建时
 * 就绪：RUNNABLE  新建状态的线程 start()后，进入线程队列对待CPU时间片
 * 运行：?
 * 阻塞：
 *      BLOCK   : waiting for a monitor lock
 *      WAITING : (Object.wait() || Thread.join || LockSupport.park) with no timeOut
 *          Waiting for another lock to ``notify`` this <- Object.wait()
 *          Waiting for another thread to be `terminated` <- Thread.join()
 *      TIMED_WAITING : (Thread.sleep() || Object.wait() || Thread.join || LockSupport.park) with timeOut
 * 死亡：TERMINATED    run()结束，执行stop()，未处理的Error/Exception
 */


public class A4_ThreadLifeCycle {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println(thread);
    }
}
