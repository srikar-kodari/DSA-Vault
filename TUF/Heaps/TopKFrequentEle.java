import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TopKFrequentEle {
    public static void main(String[] args) {

        int[] arr = {1,1,1,2,3,4,4,5,5,5};
        int[] ans = topKFrequent(arr, 3);
        System.out.println(Arrays.toString(ans));
        
    }

    static int[] topKFrequent(int[] nums, int k) {

        int n = nums.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a,b) -> Integer.compare(map.get(a), map.get(b))
        );

        for(int key : map.keySet()) {

            minHeap.add(key);
            if(minHeap.size() > k) minHeap.poll();            
        }

        return minHeap.stream().mapToInt(Integer::intValue).toArray();
    }

}
