public class JumpGame1 {
    public static void main(String[] args) {

        // int[] arr = {3,2,1,0,4};
        int[] arr = {2,3,1,1,4};
        boolean ans = canJump(arr);
        System.out.println(ans);
        
    }

    // TC: O(N), SC: O(1)
    static boolean canJump(int[] nums) {

        int n = nums.length;
        int maxIndex = 0;

        for(int i=0; i<n; i++) {

            if(i > maxIndex) return false;

            maxIndex = Math.max(maxIndex, i + nums[i]);
        }

        return true;
    }

}
