import java.util.Arrays;

public class ImpQueueArray {

    int size = 10;  // Max Size = 4
    int[] queue = new int[size];

    int currentSize = 0;
    int start = -1;
    int end = -1;

    void push(int x) {

        if(currentSize == size) {
            System.out.println("Queue is full. Can't insert element..");
            return;
        }

        if(currentSize == 0) start = end = 0;
        else end = (end + 1) % size;

        queue[end] = x;
        currentSize++;
    }

    int pop() {

        if(currentSize == 0) {
            System.out.println("Empty Queue..");
            return -1;
        }

        int ele = queue[start];
        queue[start] = 0;

        if(currentSize == 1) start = end = -1;
        else start = (start + 1) % size;

        currentSize--;
        return ele;
    }

    int peek() {

        if(currentSize == 0) {
            System.out.println("Empty Queue..");
            return -1;
        }

        return queue[start];
    }

    int size() {
        return currentSize;
    }

    public static void main(String[] args) {
        
        ImpQueueArray q = new ImpQueueArray();
        q.push(10);
        q.push(20);
        q.push(30);
        q.push(40);

        System.out.println(Arrays.toString(q.queue));
        System.out.println(q.pop());
        System.out.println(q.peek());
        System.out.println(Arrays.toString(q.queue));
        System.out.println(q.size());

    }

}
