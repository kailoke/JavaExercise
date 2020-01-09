package a2_Synchronization;

/**
 * 多线程同步：extends Thread类的方式
 * 1. 共享数据 : 因有多个Thread线程体类对象，将共享数据static 作为类变量共享
 * 2. monitor:
 *      > (this) monitor 对应线程对象会有多个，不能用于同步
 *      > 使用Class.class 类对象进行同步，因类只加载一次，每个类对象在内存中唯一
 */
public class A2_SellTicketsExtends {
    public static void main(String[] args) {
        // extends 多线程，会有多个线程体对象
        // 使用 Class.class 进行某 线程体 下多个 线程对象间(共享数据)的同步
        A2Seller s1 = new A2Seller("窗口1");
        A2Seller s2 = new A2Seller("窗口2");
        A2Seller s3 = new A2Seller("窗口3");

        s1.start();
        s2.start();
        s3.start();
    }
}

class A2Seller extends Thread{
    private static int ticket = 100;    // 对象不唯一，声明为static 类变量

    // 调用Thread构造器，赋予子类线程对象名称
    public A2Seller(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true){
            synchronized(A2Seller.class){   // Class class = Seller2.class
                                            // Class.class 类只加载一次，所以类对象唯一
                if (ticket > 0 ){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票：" + ticket);
                    ticket--;
                    yield();
                }else{
                    break;
                }
            }
        }
    }
}
