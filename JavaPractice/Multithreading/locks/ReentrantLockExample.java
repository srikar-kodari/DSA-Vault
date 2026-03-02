package locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    private static final class ReentrantCounter {
        private final ReentrantLock lock = new ReentrantLock();
        private int value = 0;

        public void incrementWithReentrancy() {
            lock.lock();
            try {
                value++;
                System.out.println(Thread.currentThread().getName() + " entered outer method, value=" + value);
                incrementInner();
            } finally {
                lock.unlock();
            }
        }

        private void incrementInner() {
            lock.lock();
            try {
                value++;
                System.out.println(Thread.currentThread().getName() + " re-entered inner method, holdCount=" + lock.getHoldCount() + ", value=" + value);
            } finally {
                lock.unlock();
            }
        }

        public int getValue() {
            lock.lock();
            try {
                return value;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantCounter counter = new ReentrantCounter();
        ReentrantLock gate = new ReentrantLock();

        Thread holder = new Thread(() -> {
            gate.lock();
            try {
                System.out.println("holder acquired gate lock and holds it for 1 second");
                sleep(1000);
            } finally {
                gate.unlock();
                System.out.println("holder released gate lock");
            }
        }, "holder");

        Thread timedTry = new Thread(() -> {
            try {
                System.out.println("timedTry attempting tryLock with 300ms timeout");
                if (gate.tryLock(300, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("timedTry acquired gate lock");
                    } finally {
                        gate.unlock();
                    }
                } else {
                    System.out.println("timedTry timed out and continued without blocking forever");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "timedTry");

        Thread interruptible = new Thread(() -> {
            try {
                System.out.println("interruptible waiting with lockInterruptibly");
                gate.lockInterruptibly();
                try {
                    System.out.println("interruptible acquired gate lock");
                } finally {
                    gate.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println("interruptible got interrupted while waiting");
                Thread.currentThread().interrupt();
            }
        }, "interruptible");

        Thread worker1 = new Thread(counter::incrementWithReentrancy, "worker-1");
        Thread worker2 = new Thread(counter::incrementWithReentrancy, "worker-2");

        holder.start();
        sleep(100);
        timedTry.start();
        interruptible.start();
        sleep(400);
        interruptible.interrupt();

        worker1.start();
        worker2.start();

        holder.join();
        timedTry.join();
        interruptible.join();
        worker1.join();
        worker2.join();

        System.out.println("Final counter value=" + counter.getValue());
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
