public class Ex2Main {
    public static void main(String[] args) {

        Ex2SharedResource resource = new Ex2SharedResource();
        
        System.out.println("Main Thread is Started: " + Thread.currentThread().getName());

        Thread t1 = new Thread(() -> {

            System.out.println("Thread1 is calling produce method..");
            resource.produce();
        }, "Thread-0");

        Thread t2 = new Thread(() -> {

            try {
                Thread.sleep(1000);
            }
            catch(Exception e) {
                // Handle Exception
            }

            System.out.println("Thread2 is calling produce method..");
            resource.produce();
        }, "Thread-1");

        t1.start();
        t2.start();

        resource.waitUntilT1EnteredProduce();

        try {
                Thread.sleep(3000);
            }
            catch(Exception e) {
                // Handle Exception
            }

        System.out.println("Thread1 is Suspended..");
        resource.requestSuspendT1();

        System.out.println("Main Thread Execution is Finished..");

    }
}
