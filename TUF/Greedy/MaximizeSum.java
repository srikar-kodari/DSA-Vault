import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class MaximizeSum {
    public static void main(String[] args) {

        // int[] arr = {84,93,100,77,93};
        // int[] ans = maxKDistinct(arr, 3);
        int[] arr = {1,1,1,2,2,2};
        int[] ans = maxKDistinct(arr, 6);
        System.out.println(Arrays.toString(ans));
        
    }

    // TC: O(n log d + m), SC: O(d + m)
    // n = nums.length, d = distinct count, m = min(k, d)
    static int[] maxKDistinct(int[] nums, int k) {

        TreeSet<Integer> set = new TreeSet<>();
        ArrayList<Integer> list = new ArrayList<>();

        for(int z : nums) set.add(z);

        while(k-- > 0 && !set.isEmpty()) {

            list.add(set.removeLast());
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // TC: O(n log d + m), SC: O(d + m)
    // Same asymptotic complexity as maxKDistinct, but with lower constant overhead.
    static int[] maxKDistinctOptimized(int[] nums, int k) {

        TreeSet<Integer> set = new TreeSet<>();
        for (int z : nums) set.add(z);

        int limit = Math.min(k, set.size());
        int[] ans = new int[limit];

        var it = set.descendingIterator();
        for (int i = 0; i < limit; i++) {
            ans[i] = it.next();
        }

        return ans;
    }
}
