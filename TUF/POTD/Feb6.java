import java.util.Arrays;

public class Feb6 {

    public static int minRemoval(int[] nums, int k) {

        int n = nums.length;
        if (n <= 1) return 0;

        Arrays.sort(nums);

        int maxLen = 1;
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j < n && (long) nums[j] <= (long) k * nums[i]) {
                j++;
            }
            maxLen = Math.max(maxLen, j - i);
        }

        return n - maxLen;
    }

}
