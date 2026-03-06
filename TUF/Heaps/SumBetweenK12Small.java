import java.util.Arrays;
import java.util.PriorityQueue;

public class SumBetweenK12Small {
    public static void main(String[] args) {
        
        // int[] arr = {1,3,12,5,15,11};
        // int ans = sumK1K2Brute(arr, 3, 6);
        // System.out.println(ans);

        int[] arr = {1,3,12,5,15,11};
        int ans = sumK1K2(arr, 3, 6);
        System.out.println(ans);

    }

    static int sumK1K2(int[] arr, int k1, int k2) {

        int first = kthSmallest(arr, k1);
        int second = kthSmallest(arr, k2);

        int sum = 0;
        for (int val : arr) {
            if (val > first && val < second) sum += val;
        }

        return sum;
    }

    // TC: O(n log k), SC: O(k)
    static int kthSmallest(int[] arr, int k) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for (int val : arr) {
            maxHeap.add(val);
            if (maxHeap.size() > k) maxHeap.poll();
        }

        return maxHeap.peek();
    }

    // TC: O(n log n)
    // SC: O(1) auxiliary (ignoring sort implementation stack usage)
    static int sumK1K2Brute(int[] arr, int k1, int k2) {

        Arrays.sort(arr);

        int sum = 0;
        for(int i = k1; i < k2 - 1; i++) sum += arr[i];

        return sum;
    }

}
