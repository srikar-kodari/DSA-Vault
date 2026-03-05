public class BinarySubarraySum {
    public static void main(String[] args) {

        int[] arr = {1,0,1,0,1};
        // int[] arr = {0,0,0,0,0};
        int goal = 2;
        int ans = totalSubarraysWithSum(arr, goal)
                    - totalSubarraysWithSum(arr, goal-1);
        System.out.println(ans);
        
    }

    // This Method Computes Count Of Subarrays <= goal
    static int totalSubarraysWithSum(int[] nums, int goal) {

        if(goal < 0) return 0;

        int n = nums.length;
        int left = 0, right = 0, sum = 0, count = 0;

        while (right < n) {
            
            sum += nums[right];

            while (sum > goal) {
                sum = sum - nums[left];
                left++;
            }

            count = count + (right - left + 1);
            right++;
        }

        return count;
    }

    static int totalSubarraysWithSumBrute(int[] nums, int goal) {

        int n = nums.length;
        int count = 0;

        for(int i=0; i<n; i++) {

            int sum = 0;
            for(int j=i; j<n; j++) {

                sum += nums[i];
                if(sum == goal) count++;
                if(sum > goal) break;
            }
        }

        return count;
    }

}
