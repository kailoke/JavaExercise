package com.F.ThreatPool;
/** 线程池 ExecuteService 接口类,具体实现类 ThreadPoolExecutor
 * 1.创建线程池对象
 * 2.父类引用(ExecuteService) 强转子类对象 (ThreadPoolExecutor)
 * 3.指定执行线程的操作。execute Runnable实现类的对象;submit Callable实现类的对象。
 * 4.关闭连接池
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);

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
        service.submit(new Callable() {

            @Override
            public Object call() throws Exception {
                System.out.println("submmit");
                return null;
            }
        });

        service.shutdown();
    }
}
