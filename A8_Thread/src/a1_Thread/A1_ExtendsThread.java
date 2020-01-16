package a1_Thread;

/** 创建线程方式一 : 继承Thread类，重写run()
 * 1. 创建继承Thread类的线程体子类，重写Thread类 run()
 *      > 将此线程执行的操作声明在run()中
 * 2. new 线程体类()，调用线程体子类对象 start()
 * 3. 核心是 线程体对象.start() 进行线程创建和run()调用，run()只是一个普通方法
 * 4. 一个线程对象只能start()一次，否则抛出IllegalThreadStateException
 *      > 线程状态：Thread实例成员：int ThreadStatus
 */


public class A1_ExtendsThread {
    public static void main(String[] args) {
        primeThread1 pt = new primeThread1();
        pt.start();     // 线程创建，JVM调用 this 线程的 Run()方法
//        pt.run();     // 不会开启新线程，run()是一个普通方法
        for (int i = 0; i < 100; i++) {
            if (i % 2 !=0){
                System.out.println("main\t" + i);
            }
        }

        primeThread1 pt2 = new primeThread1();
        pt2.start();
    }

    // 匿名类的匿名对象
    public void startTwo(){
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 ==0){
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 !=0){
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        }.start();
    }
}

class primeThread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 ==0){
                System.out.println("prime\t" + i);
            }
        }
    }
}
