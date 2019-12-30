package a3_Communication.ProductTest;

public class Clerk {
    private int product = 0;

    public synchronized void receive(){
        if (product < 20) {
            product++;
            System.out.println(Thread.currentThread().getName() + "生产产品：" + product);
            notify();
        }else {
            System.out.println("产品已满，"+ Thread.currentThread().getName() + "等待中···");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consume(){
        if (product > 0) {
            System.out.println(Thread.currentThread().getName() + "消费产品" + product);
            --product;
            notify();
        }else {
            System.out.println("无产品，"+ Thread.currentThread().getName() + "等待中···");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
