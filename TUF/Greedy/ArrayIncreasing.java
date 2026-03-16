public class ArrayIncreasing {
    public static void main(String[] args) {
        
        int[] arr = {1,5,2,4,1};
        int ans = minOperations(arr);
        System.out.println(ans);

    }

    static int minOperations(int[] nums) {

        int n = nums.length;

        if(n == 1) return 0;

        int count = 0;
        for(int i=1; i<n; i++) {

            if(nums[i] > nums[i-1]) continue;

            count += nums[i-1] + 1 - nums[i];
            nums[i] = nums[i-1] + 1;
        }

        return count;
    }
    
}
