public class NiceSubarrays {

    // Similar To BinarySubarraySum Problem. Imagine Even, Odd as 0,1.

    public static void main(String[] args) {
        
        // int[] arr = {1,1,2,1,1};
        // int ans = numberOfNiceSubarraysBrute(arr, 3);
        // System.out.println(ans);

        int[] arr = {1,1,2,1,1};
        int k = 3;
        int ans = numberOfNiceSubarrays(arr, k)
                    - numberOfNiceSubarrays(arr, k-1);
        System.out.println(ans);

    }

    // This Method Computes Count Of Subarrays <= K. Refer BinarySubarraySum Problem
    static int numberOfNiceSubarrays(int[] nums, int k) {

        if(k < 0) return 0;

        int n = nums.length;
        int left = 0, right = 0, sum = 0, count = 0;

        while (right < n) {
            
            sum += nums[right] % 2;

            while (sum > k) {
                sum = sum - nums[left] % 2;
                left++;
            }

            count = count + (right - left + 1);
            right++;
        }

        return count;
    }

    static int numberOfNiceSubarraysBrute(int[] nums, int k) {

        int n = nums.length;
        int total = 0;

        for(int i=0; i<n; i++) {

            int count = 0;
            for(int j=i; j<n; j++) {

                if(nums[j] % 2 == 1) count++;
                if(count == k) total++;

                if(total > k) break;
            }
        }

        return total;
    }

}
