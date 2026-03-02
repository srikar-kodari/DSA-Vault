package locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class FairLockExample {

    private static List<String> runScenario(boolean fair) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(fair);
        CountDownLatch startGate = new CountDownLatch(1);
        List<String> acquisitionOrder = new ArrayList<>();
        Object orderMutex = new Object();

        Runnable task = () -> {
            try {
                startGate.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            for (int i = 0; i < 2; i++) {
                lock.lock();
                try {
                    String event = Thread.currentThread().getName() + " acquired lock";
                    synchronized (orderMutex) {
                        acquisitionOrder.add(event);
                    }
                    System.out.println((fair ? "FAIR" : "NON_FAIR") + " -> " + event);
                    sleep(60);
                } finally {
                    lock.unlock();
                }
                sleep(20);
            }
        };

        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(task, "t" + (i + 1));
            threads[i].start();
        }

        startGate.countDown();

        for (Thread thread : threads) {
            thread.join();
        }

        return acquisitionOrder;
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> fairOrder = runScenario(true);
        System.out.println("---------------------------------");
        List<String> nonFairOrder = runScenario(false);

        System.out.println("\nFAIR acquisition order:");
        fairOrder.forEach(System.out::println);

        System.out.println("\nNON-FAIR acquisition order:");
        nonFairOrder.forEach(System.out::println);
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
