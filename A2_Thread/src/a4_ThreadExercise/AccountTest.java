package a4_ThreadExercise;

/** 银行有一个账户
 * 两个储户分别向同一个账户存3000元，每次存 1000 ，分别存3次。
 * 每次存完打印余额。
 */
public class AccountTest {
    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        Customer c1 = new Customer(account);
        Customer c2 = new Customer(account);
        Thread p1 = new Thread(c1);
        Thread p2 = new Thread(c2);

        p1.start();
        p2.start();
    }
}

class BankAccount{
    private int money = 0;
    public synchronized void save(int money) throws InterruptedException {
        this.notifyAll();
        this.money += money;
        System.out.println(Thread.currentThread().getName() + "账户余额：" + this.money);
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if (this.money < 6000){
            this.wait();    // monitor.wait()需要条件，否则最后执行的线程一定会被挂起，其他线程已结束不会notify
        }
    }
}

class Customer implements Runnable{
    private BankAccount acct;

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                this.deposit(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Customer(BankAccount acct){
        this.acct = acct;
    }

    public void deposit(int money) throws InterruptedException {
        acct.save(money);
    }
}
