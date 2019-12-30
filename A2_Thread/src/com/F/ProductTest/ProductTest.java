package com.F.ProductTest;

public class ProductTest {


    public static void main(String[] args) {
        Clerk clerk1 = new Clerk();

        Producer producer = new Producer(clerk1);
        Producer producer2 = new Producer(clerk1);
        Customer customer = new Customer(clerk1);

        producer.setName("生产者");
        producer2.setName("生产者2");
        customer.setName("消费者");

        producer.start();
        producer2.start();
        customer.start();
    }
}
