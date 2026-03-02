package locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class PessimisticLockExample {

    private static final class Inventory {
        private final ReentrantLock lock = new ReentrantLock();
        private int stock;

        Inventory(int initialStock) {
            this.stock = initialStock;
        }

        public boolean purchaseWithPessimisticLock(int quantity) {
            try {
                if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " could not get lock in time");
                    return false;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }

            try {
                System.out.println(Thread.currentThread().getName() + " acquired lock for purchase");
                sleep(120);
                if (stock >= quantity) {
                    stock -= quantity;
                    System.out.println(Thread.currentThread().getName() + " purchase success, stock=" + stock);
                    return true;
                }
                System.out.println(Thread.currentThread().getName() + " purchase failed, stock=" + stock);
                return false;
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " released lock");
            }
        }

        public int getStock() {
            lock.lock();
            try {
                return stock;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Inventory inventory = new Inventory(10);

        Runnable buyerTask = () -> {
            for (int i = 0; i < 2; i++) {
                inventory.purchaseWithPessimisticLock(3);
                sleep(50);
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

        System.out.println("Pessimistic locking final stock=" + inventory.getStock());
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
