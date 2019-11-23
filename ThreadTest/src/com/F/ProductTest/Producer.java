package com.F.ProductTest;

public class Producer extends Thread{
    private Clerk clerk;

    public Producer(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            clerk.receive();

            try {
                Thread.sleep(260);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
