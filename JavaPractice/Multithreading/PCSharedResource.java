import java.util.LinkedList;
import java.util.Queue;

public class PCSharedResource {
    
    private Queue<Integer> sharedBuffer;
    private int bufferSize;

    public PCSharedResource(int bufferSize) {
        sharedBuffer = new LinkedList<>();
        this.bufferSize = bufferSize;
    }

    public synchronized void produce(int item) throws Exception {

        // If buffer is full, wait for the consumer to consume items
        while (sharedBuffer.size() == bufferSize) {
            System.out.println("Buffer is full, Producer is waiting for Consumer..");
            wait();
        }

        sharedBuffer.add(item);
        System.out.println("Produced: " + item);
        // Notify the consumer that there are items to consume now
        notify();
    }

    public synchronized int consume() throws Exception {

        // If buffer is empty, wait for the producer to produce items
        while (sharedBuffer.isEmpty()) {
            System.out.println("Buffer is empty, Consumer is waiting for Producer..");
            wait();
        }

        int item = sharedBuffer.poll();
        System.out.println("Consumed: " + item);
        // Notify the producer that there is space in buffer now
        notify();

        return item;
    }
    
}
