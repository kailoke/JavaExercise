package a5_ThreadPool;

import java.util.concurrent.*;

/** 线程池 : 管理线程的创建、运行、销毁
 *
 * 一、ExecuteService：真正的线程池接口，常见子类：ThreadPoolExecutor
 *  > void execute(Runnable command) : 执行任务/命令，一般执行Runnable
 *  > <T> Future<T> submit(Callable<T> task)：执行任务，泛型返回值，一般执行Callable
 *  > void shutdown()：关闭连接池
 *
 * 二、Executors：工具类、线程池的工厂类。用于创建并返回不同类型的线程池(ThreadPool)
 *  > Executors.newCachedThreadPool()：可创建新线程的池
 *  > Executors.newFixedThreadPool(int num)：可重用的固定线程数的池
 *  > Executors.newSingleThreadPool()：只有一个线程的池
 *  > Executors.newScheduledThreadPool(int num)：有计划任务的池(给定延迟后运行命令 || 周期执行)
 *
 * 三、线程池的使用：
 * 1.通过Executors工厂类，创建线程池对象
 * 2.ExecutorService对象`获取线程`装载`任务`并启动线程
 *      > execute( Runnable )实现类的对象
 *      > submit ( Callable )实现类的对象
 * 3.关闭连接池
 *
 * 父类引用(ExecuteService) 强转子类对象 (ThreadPoolExecutor)
 */


public class A2_Executors {
    public static void main(String[] args) {
        // 1.通过Executors工厂类获得线程池对象
        ExecutorService service = java.util.concurrent.Executors.newFixedThreadPool(10);
        // 2.1 Executors.execute(Runnable run)
        service.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(i);
                    try {
                        System.out.println("Runnable 睡眠中");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // 2.1 Executors.submit(Callable<T> call)
        service.submit(new Callable<Object>() {
            @Override
            public Object call() {
                System.out.println("submit");
                return null;
            }
        });
        // 3.关闭线程池
        service.shutdown();
    }
}
