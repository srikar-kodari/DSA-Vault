import java.util.concurrent.CountDownLatch;

public class Ex2SharedResource {
    
    boolean isAvailable = false;
    volatile boolean suspendT1Requested = false;
    private final CountDownLatch t1EnteredProduce = new CountDownLatch(1);

    public void requestSuspendT1() {
        suspendT1Requested = true;
    }

    public void waitUntilT1EnteredProduce() {
        try {
            t1EnteredProduce.await();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void produce() {

        System.out.println("Lock Acquired by: " + Thread.currentThread().getName());
        isAvailable = true;

        if ("Thread-0".equals(Thread.currentThread().getName())) {
            t1EnteredProduce.countDown();
        }

        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {
            // Handle Exception
        }

        if ("Thread-0".equals(Thread.currentThread().getName()) && suspendT1Requested) {
            System.out.println("Thread-0 is Suspended while holding lock..");
            while (true) {
                try {
                    Thread.sleep(20000);
                }
                catch (InterruptedException e) {
                    // Keep suspended for demo
                }
            }
        }

        System.out.println("Lock Released by: " + Thread.currentThread().getName());

    }
    
}
