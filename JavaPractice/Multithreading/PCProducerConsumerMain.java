public class PCProducerConsumerMain {   // Implementation of Producer Consumer Problem
    public static void main(String[] args) {
        
        PCSharedResource sharedBuffer = new PCSharedResource(4);

        // Creating Threads using Lambda Expression

        Thread producerThread = new Thread(() -> {
            try {
                for(int i=1; i<=10; i++) {
                    sharedBuffer.produce(i);
                }
            }
            catch(Exception e) {
                // Handle Exception
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                for(int i=1; i<=10; i++) {
                    sharedBuffer.consume();
                }
            }
            catch(Exception e) {
                // Handle Exception
            }
        });

        consumerThread.start();
        producerThread.start();

    }
    
}
