import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class SlidingWindowMax {
    public static void main(String[] args) {

        // int[] arr = {1,3,-1,-3,5,3,6,7};
        // int[] ans = maxSlidingWindowBrute(arr, 3);
        // System.out.println(Arrays.toString(ans));

        int[] arr = {1,3,-1,-3,5,3,6,7};
        int[] ans = maxSlidingWindow(arr, 3);
        System.out.println(Arrays.toString(ans));
        
    }

    static int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;
        
        List<Integer> ans = new ArrayList<>();

        Deque<Integer> deque = new ArrayDeque<>();

        for(int i=0; i<n; i++) {

            if(!deque.isEmpty() && deque.getFirst() <= i-k) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && nums[deque.getLast()] <= nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);

            if(i >= k-1) ans.add(nums[deque.getFirst()]);
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    static int[] maxSlidingWindowBrute(int[] nums, int k) {

        int n = nums.length;

        List<Integer> list = new ArrayList<>();

        for(int i=0; i <= n-k; i++) {

            int maxEle = nums[i];
            for(int j=i; j <= i+k-1; j++) {

                maxEle = Math.max(maxEle, nums[j]);
            }
            list.add(maxEle);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

}
