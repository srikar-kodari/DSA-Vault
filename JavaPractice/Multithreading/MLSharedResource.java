public class MLSharedResource {     // Monitor Lock Example

    boolean itemAvailable = false;

    // synchronized - puts the monitor lock
    public synchronized void addItem() {

        itemAvailable = true;
        System.out.println("Producer Item added by: " + Thread.currentThread().getName() + " and invoking / notifying all threads which are waiting..");
        notifyAll();
    }

    public synchronized void consumeItem() {
        System.out.println("ConsumeItem method invoked by: " + Thread.currentThread().getName());

        // Using while loop to avoid "spurious wake-up", sometimes because of system noise
        while (!itemAvailable) {
            
            try {
                System.out.println("Consumer Thread: " + Thread.currentThread().getName() + " is waiting now..");
                wait();     // Thread goes to non runnable state // It releases the monitor lock
            }
            catch(Exception e) {
                // Handle Exception
                System.out.println("Exception is handled..");
            }
        }

        System.out.println("Item consumed by: " +Thread.currentThread().getName());
        itemAvailable = false;
    }

}
