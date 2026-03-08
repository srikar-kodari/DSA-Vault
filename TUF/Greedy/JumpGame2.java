public class JumpGame2 {
    public static void main(String[] args) {

        int[] arr = {2,3,0,1,4};
        int ans = jump(arr);
        int greedyAns = jumpGreedy(arr);
        System.out.println(ans);
        System.out.println(greedyAns);

    }

    // TC: O(N) - single pass over the array
    // SC: O(1) - constant extra space
    static int jumpGreedy(int[] nums) {

        if(nums.length <= 1) return 0;

        int jumps = 0;
        int end = 0;
        int farthest = 0;

        for(int i=0; i<nums.length-1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if(i == end) {
                jumps++;
                end = farthest;
            }
        }

        return jumps;
    }

    
    // Recursion
    static int jump(int[] nums) {

        int ans = countJumps(0, 0, nums);

        return ans;
    }

    // TC: O(2^N) worst case - explores many jump combinations recursively
    // SC: O(N) recursion stack depth in the worst case
    static int countJumps(int index, int jumps, int[] nums) {

        if(index >= nums.length-1) return jumps;

        int mini = Integer.MAX_VALUE;
        for(int i=1; i<=nums[index]; i++) {

            mini = Math.min(mini, countJumps(index+i, jumps+1, nums));
        }

        return mini;
    }

}
