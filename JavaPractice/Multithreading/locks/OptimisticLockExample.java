package locks;

import java.util.concurrent.atomic.AtomicInteger;

public class OptimisticLockExample {

    private static final class Inventory {
        private final AtomicInteger version = new AtomicInteger(0);
        private int stock;

        Inventory(int initialStock) {
            this.stock = initialStock;
        }

        public boolean purchaseOptimistically(int quantity) {
            for (int attempt = 1; attempt <= 5; attempt++) {
                int observedVersion = version.get();
                int observedStock = stock;

                if (observedStock < quantity) {
                    System.out.println(Thread.currentThread().getName() + " insufficient stock=" + observedStock);
                    return false;
                }

                sleep(50);

                synchronized (this) {
                    if (version.get() == observedVersion && stock >= quantity) {
                        stock -= quantity;
                        int newVersion = version.incrementAndGet();
                        System.out.println(Thread.currentThread().getName() + " purchase success, stock=" + stock + ", version=" + newVersion);
                        return true;
                    }
                }

                System.out.println(Thread.currentThread().getName() + " conflict on attempt " + attempt + ", retrying...");
                sleep(30);
            }

            System.out.println(Thread.currentThread().getName() + " failed after retries due to repeated conflicts");
            return false;
        }

        public synchronized int getStock() {
            return stock;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Inventory inventory = new Inventory(12);

        Runnable buyerTask = () -> {
            for (int i = 0; i < 3; i++) {
                inventory.purchaseOptimistically(2);
                sleep(40);
            }
        };

        Thread buyer1 = new Thread(buyerTask, "buyer-1");
        Thread buyer2 = new Thread(buyerTask, "buyer-2");
        Thread buyer3 = new Thread(buyerTask, "buyer-3");

        buyer1.start();
        buyer2.start();
        buyer3.start();

        buyer1.join();
        buyer2.join();
        buyer3.join();

        System.out.println("Optimistic locking final stock=" + inventory.getStock());
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
