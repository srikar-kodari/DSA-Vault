public class Ex1Main {
    public static void main(String[] args) {

        Ex1SharedResource resource = new Ex1SharedResource();
        
        System.out.println("Main Thread is Started: " + Thread.currentThread().getName());

        Thread t1 = new Thread(() -> {

            System.out.println("Thread1 is calling produce method..");
            resource.produce();
        });

        Thread t2 = new Thread(() -> {

            try {
                Thread.sleep(4000);
            }
            catch(Exception e) {
                // Handle Exception
            }

            System.out.println("Thread2 is calling produce method..");
            resource.produce();
        });

        t1.setPriority(1);
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();

        // System.out.println("Thread1 is Suspended..");
        // t1.suspend();    // Deprecated Method
        // t1.resume();     // Deprecated Method

        try {
            System.out.println("Main Thread is waiting for Thread1, Thread2 to finish..");
            t1.join();
            t2.join();
        }
        catch(Exception e) {
            // Handle Exception
        }

        System.out.println("Main Thread Execution is Finished..");

    }
}
