package locks;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    private static final class ConnectionPool {
        private final Semaphore permits;

        ConnectionPool(int maxConnections, boolean fair) {
            this.permits = new Semaphore(maxConnections, fair);
        }

        public void useConnection(String workerName) {
            try {
                System.out.println(workerName + " waiting for permit, available=" + permits.availablePermits());
                permits.acquire();
                System.out.println(workerName + " acquired permit, available=" + permits.availablePermits());
                Thread.sleep(250);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                permits.release();
                System.out.println(workerName + " released permit, available=" + permits.availablePermits());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConnectionPool pool = new ConnectionPool(3, true);

        Thread[] workers = new Thread[8];
        for (int i = 0; i < workers.length; i++) {
            String name = "worker-" + (i + 1);
            workers[i] = new Thread(() -> pool.useConnection(Thread.currentThread().getName()), name);
            workers[i].start();
        }

        for (Thread worker : workers) {
            worker.join();
        }

        System.out.println("Semaphore example finished");
    }
}
