package locks;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {

    private static final class Point {
        private final StampedLock lock = new StampedLock();
        private double x;
        private double y;

        public void move(double deltaX, double deltaY) {
            long stamp = lock.writeLock();
            try {
                x += deltaX;
                y += deltaY;
                System.out.println(Thread.currentThread().getName() + " moved point to (" + x + ", " + y + ")");
                sleep(120);
            } finally {
                lock.unlockWrite(stamp);
            }
        }

        public double distanceFromOrigin() {
            long stamp = lock.tryOptimisticRead();
            double currentX = x;
            double currentY = y;
            sleep(40);

            if (!lock.validate(stamp)) {
                System.out.println(Thread.currentThread().getName() + " optimistic read failed, falling back to READ lock");
                stamp = lock.readLock();
                try {
                    currentX = x;
                    currentY = y;
                } finally {
                    lock.unlockRead(stamp);
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " optimistic read succeeded");
            }

            return Math.hypot(currentX, currentY);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Point point = new Point();

        Thread writer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                point.move(1.0, 1.5);
                sleep(60);
            }
        }, "writer");

        Runnable readerTask = () -> {
            for (int i = 0; i < 6; i++) {
                double distance = point.distanceFromOrigin();
                System.out.println(Thread.currentThread().getName() + " distance=" + String.format("%.2f", distance));
                sleep(70);
            }
        };

        Thread reader1 = new Thread(readerTask, "reader-1");
        Thread reader2 = new Thread(readerTask, "reader-2");

        writer.start();
        reader1.start();
        reader2.start();

        writer.join();
        reader1.join();
        reader2.join();

        System.out.println("StampedLock example finished");
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
