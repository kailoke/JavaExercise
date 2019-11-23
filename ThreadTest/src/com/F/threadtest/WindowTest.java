package com.F.threadtest;

/*
三个窗口卖100张票，将票定义为类
 */
public class WindowTest {
    public static void main(String[] args) {
        Seller seller = new Seller();
        Thread t1 = new Thread(seller);
        Thread t2 = new Thread(seller);
        Thread t3 = new Thread(seller);

        t1.setName("窗口<1>");
        t2.setName("窗口<2>");
        t3.setName("窗口<3>");

        t1.start();
        t2.start();
        t3.start();
    }
}

// 类.....
class Seller implements Runnable{
    private int TICKET = 100;   // 对象的属性

    @Override
    public void run() {
        while (true){
            if(TICKET > 0){
                System.out.println(Thread.currentThread().getName() + "卖票：" + TICKET);
                TICKET--;
            }else {
                break;
            }
        }

    }
}