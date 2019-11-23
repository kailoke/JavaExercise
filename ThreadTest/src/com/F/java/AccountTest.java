package com.F.java;

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
    public synchronized void save(int money){
        this.money += money;
        System.out.println(Thread.currentThread().getName() + "账户余额：" + this.money);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Customer implements Runnable{
    private BankAccount acct;

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            this.deposite(1000);
        }
    }
    public Customer(BankAccount acct){
        this.acct = acct;
    }

    public void deposite(int money){
        acct.save(money);
    }
}
