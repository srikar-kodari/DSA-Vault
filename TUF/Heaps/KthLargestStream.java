import java.util.PriorityQueue;

public class KthLargestStream {     // Design Problem

    private final int k;
    private final PriorityQueue<Integer> minHeap;

    public KthLargestStream(int k, int[] nums) {

        this.k = k;
        this.minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.add(num);

            if (minHeap.size() > k) minHeap.poll();
        }
    }

    public int add(int val) {

        minHeap.add(val);
        if (minHeap.size() > k) minHeap.poll();

        return minHeap.peek();
    }

    public static void main(String[] args) {
        KthLargestStream obj = new KthLargestStream(3, new int[] {4, 5, 8, 2});

        System.out.println(obj.add(3));   // 4
        System.out.println(obj.add(5));   // 5
        System.out.println(obj.add(10));  // 5
        System.out.println(obj.add(9));   // 8
        System.out.println(obj.add(4));   // 8

    }
}
