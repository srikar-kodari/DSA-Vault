import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {     // Design Problem

    private final PriorityQueue<Integer> maxHeap;
    private final PriorityQueue<Integer> minHeap;

    public MedianFinder() {

        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {

        if (maxHeap.isEmpty() || num <= maxHeap.peek()) maxHeap.add(num);
        else minHeap.add(num);

        if (maxHeap.size() > minHeap.size() + 1) minHeap.add(maxHeap.poll());
        else if (minHeap.size() > maxHeap.size()) maxHeap.add(minHeap.poll());
    }

    public double findMedian() {

        if (maxHeap.isEmpty()) return 0.0;

        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }

        return maxHeap.peek();
    }

    public static void main(String[] args) {
        
        MedianFinder obj = new MedianFinder();

        obj.addNum(1);
        System.out.println(obj.findMedian()); // 1.0

        obj.addNum(2);
        System.out.println(obj.findMedian()); // 1.5

        obj.addNum(3);
        System.out.println(obj.findMedian()); // 2.0

        obj.addNum(4);
        System.out.println(obj.findMedian()); // 2.5
    }

}
