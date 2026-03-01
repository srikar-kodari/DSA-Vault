public class MLMain {
    public static void main(String[] args) {
        
        System.out.println("Main Method Starts..");

        MLSharedResource sharedResource = new MLSharedResource();

        // Producer Thread
        Thread producerThread = new Thread(new MLProduceTask(sharedResource));

        // Consumer Thread
        Thread consumerThread = new Thread(new MLConsumeTask(sharedResource));

        // Thread is in Runnable State
        // consumerThread.start();
        producerThread.start();
        consumerThread.start();

        System.out.println("Main Method Ends..");

    }
}
