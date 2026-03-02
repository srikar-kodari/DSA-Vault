package locks;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {

    private static final class BoundedBuffer {
        private final Queue<Integer> queue = new ArrayDeque<>();
        private final int capacity;
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition notFull = lock.newCondition();
        private final Condition notEmpty = lock.newCondition();

        BoundedBuffer(int capacity) {
            this.capacity = capacity;
        }

        public void put(int value) throws InterruptedException {
            lock.lock();
            try {
                while (queue.size() == capacity) {
                    System.out.println(Thread.currentThread().getName() + " waiting: buffer full");
                    notFull.await();
                }

                queue.offer(value);
                System.out.println(Thread.currentThread().getName() + " produced " + value + ", size=" + queue.size());
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }

        public int take() throws InterruptedException {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + " waiting: buffer empty");
                    notEmpty.await();
                }

                int value = queue.poll();
                System.out.println(Thread.currentThread().getName() + " consumed " + value + ", size=" + queue.size());
                notFull.signal();
                return value;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer buffer = new BoundedBuffer(3);

        Thread producer1 = new Thread(() -> produce(buffer, 1), "producer-1");
        Thread producer2 = new Thread(() -> produce(buffer, 100), "producer-2");

        Thread consumer1 = new Thread(() -> consume(buffer), "consumer-1");
        Thread consumer2 = new Thread(() -> consume(buffer), "consumer-2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        producer1.join();
        producer2.join();
        consumer1.join();
        consumer2.join();

        System.out.println("Condition example finished");
    }

    private static void produce(BoundedBuffer buffer, int start) {
        for (int i = start; i < start + 6; i++) {
            try {
                buffer.put(i);
                Thread.sleep(70);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private static void consume(BoundedBuffer buffer) {
        for (int i = 0; i < 6; i++) {
            try {
                buffer.take();
                Thread.sleep(140);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
