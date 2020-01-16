package a1_Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/** 线程的创建方式三：实现Callable<V>接口
 *  > 1. class Xxx implements Callable，@Override V call()
 *  > 2. new Thread(new FutureTask(Callable call)) 创建Thread线程体对象，调用线程体对象的 start()
 *  > 3. 返回值 是 FutureTask<V>!.get()
 *
 * interface Callable :: V call throws Exception()
 *  > 有返回值，并且支持泛型返回值，由FutureTask实例保存
 *  > call()可以抛出异常
 *  > 需要借助 Class FutureTask<V> implements RunnableFuture Which implements Runnable,Future
 *          > 所以 FutureTask是Runnable接口的实现类
 */


public class A3_ImplementsCallable {
    public static void main(String[] args) {

        FutureTask<Object> futureTask = new FutureTask<>(new Call());
        new Thread(futureTask).start();

        try {
            Object o = futureTask.get() ;
            System.out.println("sum = " + o);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class Call implements Callable<Object>{

    @Override
    // V call()，默认Callable<Object>
    public Object call() throws Exception{
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
