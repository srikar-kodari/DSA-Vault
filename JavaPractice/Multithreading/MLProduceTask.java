public class MLProduceTask implements Runnable {    // Monitor Lock Example

    MLSharedResource sharedResource;

    MLProduceTask(MLSharedResource resource) {
        this.sharedResource = resource;
    }

    @Override
    public void run() {
        System.out.println("Producer Thread: " + Thread.currentThread().getName());

        try {
            System.out.println("Producer Thread: " + Thread.currentThread().getName() + " is sleeping for 10 seconds..");
            Thread.sleep(10000);
        }
        catch(Exception e) {
            // Handle Exception Here
        }

        sharedResource.addItem();
    }
    
}
