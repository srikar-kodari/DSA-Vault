public class MLConsumeTask implements Runnable {    // Monitor Lock Example

    MLSharedResource sharedResource;

    MLConsumeTask(MLSharedResource resource) {
        this.sharedResource = resource;
    }

    @Override
    public void run() {
        System.out.println("Consumer Thread: " + Thread.currentThread().getName());
        sharedResource.consumeItem();
    }
    
}
