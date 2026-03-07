import java.util.PriorityQueue;

public class Z {
    public static void main(String[] args) {

        // PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // minHeap.add(10);
        // minHeap.add(40);
        // minHeap.add(20);
        // minHeap.add(30);

        // PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // maxHeap.add(10);
        // maxHeap.add(40);
        // maxHeap.add(20);
        // maxHeap.add(30);

        // System.out.println(minHeap);
        // System.out.println(maxHeap);

        // minHeap.poll();
        // maxHeap.poll();

        // System.out.println(minHeap.peek());
        // System.out.println(maxHeap.peek());

        // System.out.println(minHeap);
        // System.out.println(maxHeap);


        // int[] arr = {6,5,3,2,8,10,9};
        // PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        // for (int n : arr) {
        //     pQueue.add(n);
        // }
        // System.out.println(pQueue);


        // // Min-heap by first element (a[0])
        // PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        // pq.add(new int[] {20, 100});
        // pq.add(new int[] {10, 3});
        // pq.add(new int[] {5, 10});
        // pq.add(new int[] {15, 3});
        // pq.add(new int[] {1, 3});

        // while (!pq.isEmpty()) {
        //     int[] cur = pq.poll();
        //     System.out.println(cur[0] + ", " + cur[1]);
        // }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(1);
        pq.add(2);
        pq.add(3);
        pq.add(25);
        System.out.println(pq.size());
        System.out.println(pq);

    }
}
