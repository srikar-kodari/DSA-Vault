package locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    private static final class ConfigStore {
        private final ReadWriteLock lock = new ReentrantReadWriteLock();
        private final Map<String, String> data = new HashMap<>();

        public void put(String key, String value) {
            lock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + " acquired WRITE lock");
                data.put(key, value);
                sleep(200);
            } finally {
                System.out.println(Thread.currentThread().getName() + " released WRITE lock");
                lock.writeLock().unlock();
            }
        }

        public String get(String key) {
            lock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + " acquired READ lock");
                sleep(120);
                return data.get(key);
            } finally {
                System.out.println(Thread.currentThread().getName() + " released READ lock");
                lock.readLock().unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConfigStore store = new ConfigStore();
        store.put("featureFlag", "OFF");

        Thread writer = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                store.put("featureFlag", "STATE-" + i);
            }
        }, "writer");

        Runnable readerTask = () -> {
            for (int i = 0; i < 4; i++) {
                String value = store.get("featureFlag");
                System.out.println(Thread.currentThread().getName() + " read value=" + value);
                sleep(80);
            }
        };

        Thread reader1 = new Thread(readerTask, "reader-1");
        Thread reader2 = new Thread(readerTask, "reader-2");
        Thread reader3 = new Thread(readerTask, "reader-3");

        reader1.start();
        reader2.start();
        reader3.start();
        sleep(100);
        writer.start();

        reader1.join();
        reader2.join();
        reader3.join();
        writer.join();

        System.out.println("ReadWriteLock example finished");
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
