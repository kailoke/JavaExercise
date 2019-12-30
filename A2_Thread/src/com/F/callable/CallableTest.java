package com.F.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/** Callable创建线程
 *
 */

// 1.实现Callable接口
class Call implements Callable{
    // 2.重写call() public <V> call() throws Exception
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}


public class CallableTest {
    public static void main(String[] args) {

        FutureTask futureTask = new FutureTask(new Call());
        new Thread(futureTask).start();

        try {
            Object o = futureTask.get() ;
            System.out.println("sum = " + o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
