public class Ex1SharedResource {
    
    boolean isAvailable = false;

    public synchronized void produce() {

        System.out.println("Lock Acquired by: " + Thread.currentThread().getName());
        isAvailable = true;

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            // Handle Exception
        }

        System.out.println("Lock Released by: " + Thread.currentThread().getName());

    }
    
}
