public class Ex1Daemon {
    public static void main(String[] args) {

        Ex1SharedResource resource = new Ex1SharedResource();

        System.out.println("Main Method Started..");

        Thread t1 = new Thread(() -> {

            System.out.println("Thread1 is calling produce method..");
            resource.produce();
        });

        // Daemon thread definition:
        // A daemon thread is a background/support thread.
        // The JVM does not wait for daemon threads to finish.
        // Once all user (non-daemon) threads complete, daemon threads are terminated.
        // Examples: Auto Save, Logging, Garbage Collector
        t1.setDaemon(true);
        t1.start();

        System.out.println("Main Thread Execution is Finished..");
        
    }
}
