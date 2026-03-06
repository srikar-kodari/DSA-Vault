import java.util.Arrays;
import java.util.PriorityQueue;

public class SortKSortedArray {
    public static void main(String[] args) {

        int[] arr = {6,5,3,2,8,10,9};
        int[] ans = sortKSorted(arr, 3);
        System.out.println(Arrays.toString(ans));
        
    }

    static int[] sortKSorted(int[] arr, int k) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int n = arr.length;
        int[] ans = new int[n];
        int index = 0;

        for(int i=0; i<n; i++) {

            minHeap.add(arr[i]);

            if(minHeap.size() > k + 1) ans[index++] = minHeap.poll();
        }

        while (!minHeap.isEmpty()) ans[index++] = minHeap.poll();

        return ans;
    }

}
