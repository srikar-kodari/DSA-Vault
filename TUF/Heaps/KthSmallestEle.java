import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestEle {
    public static void main(String[] args) {

        int[] arr = {7,10,4,3,20,15};

        int small = kthSmallestEle(arr, 2);
        System.out.println(small);

        int large = kthLargestEle(arr, 2);
        System.out.println(large);

        int[] kSmallest = kSmallestElements(arr, 3);
        System.out.println(Arrays.toString(kSmallest));

        int[] kLargest = kLargestElements(arr, 3);
        System.out.println(Arrays.toString(kLargest));

    }

    // TC: O(n log k), SC: O(k)
    static int kthSmallestEle(int[] arr, int k) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int n : arr) {

            maxHeap.add(n);
            if (maxHeap.size() > k) maxHeap.poll();
        }

        return maxHeap.peek();
    }

    static int kthLargestEle(int[] arr, int k) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int n : arr) {

            minHeap.add(n);
            if (minHeap.size() > k) minHeap.poll();
        }

        return minHeap.peek();
    }

    static int[] kSmallestElements(int[] arr, int k) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int n : arr) {

            maxHeap.add(n);
            if (maxHeap.size() > k) maxHeap.poll();
        }

        return maxHeap.stream().mapToInt(Integer::intValue).toArray();
    }

    static int[] kLargestElements(int[] arr, int k) {
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int n : arr) {

            minHeap.add(n);
            if (minHeap.size() > k) minHeap.poll();
        }

        return minHeap.stream().mapToInt(Integer::intValue).toArray();
    }

}
