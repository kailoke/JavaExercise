package a3_Communication.ProductTest;

public class Customer extends Thread{
    private Clerk clerk;

    public Customer(Clerk clerk){
        this.clerk = clerk;
    }

    public void run() {
        while (true) {
            clerk.consume();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
