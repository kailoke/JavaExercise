package a5_ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 一、线程池的组成：
 *  # corePoolSize + maximumPoolSize : 核心线程数 + 总线程数
 *  # keepAliveTime : 非核心线程闲置存活时间
 *  # TimeUnit :
 *  # BlockingQueue ：存储任务(Runnable || Callable)
 *      > 阻塞队列：队列空时 = 获取阻塞，队列满时 = 放入阻塞
 *                add,offer(阻塞),put  remove,poll(阻塞),take
 *  # ThreadFactory :
 *  # RejectedExecutionHandler : 拒绝策略
 *
 * 二、线程池状态
 *  > Running : 能接受新任务 & 处理已添加的任务
 *  > Shutdown: 不接受新任务，继续处理已添加的任务
 *  > Stop :    不接受新任务，不处理已添加的任务，并且中断正在处理的任务
 *  > Tidying:  所有任务已经终止，ctl记录的"任务数量"=0。
 *      > shutdown:阻塞队列空，工作线程数量==0
 *      > stop:工作线程数量==0
 *  > Terminated: 线程池彻底终止
 */
public class A1_ThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 3, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5));
        for (int i = 0; i < 9; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 3; j++) {
                    System.out.println(Thread.currentThread().getName() + "：" + j);
                }
            });
        }

    }

}
