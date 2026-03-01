import java.util.Arrays;

public class SingleNumber {
    public static void main(String[] args) {

        // int[] arr = {1,2,3,2,1,3,4};
        // int ans = singleNumber1(arr);
        // System.out.println("Single Number is: " + ans);


        // int[] arr = {1,1,1,2,2,2,4};
        // int ans = singleNumber2(arr);
        // System.out.println("Single Number is: " + ans);

        // // int[] arr = {1,1,1,2,2,2,4};
        // // int[] arr = {1,2,2,2,4,4,4};
        // int[] arr = {1,1,1,2,2,2,3,4,4,4};
        // Arrays.sort(arr);
        // int ans = singleNumber21(arr);  // Must Pass Sorted Array
        // System.out.println("Single Number is: " + ans);

        // int[] arr = {1,1,1,2,3,3,3,4,4,4};
        // int ans = singleNumber22(arr);
        // System.out.println("Single Number is: " + ans);


        int[] arr = {1,1,2,2,4,3,4,5,5,6,7,7,8,8};
        int[] ans = singleNumber3(arr);
        System.out.println(Arrays.toString(ans));


    }

    // TC: O(n) - single pass through array
    // SC: O(1) - constant extra space
    static int singleNumber1(int[] nums) {

        int ans = 0;
        for(int i=0; i<nums.length; i++) {

            ans ^= nums[i];
        }

        return ans;
    }


    // TC: O(n x 32)
    // SC: O(1)
    static int singleNumber2(int[] nums) {

        int n = nums.length;

        int ans = 0;
        for(int bitIndex=0; bitIndex<=31; bitIndex++) {

            int count = 0;
            for(int i=0; i<n; i++) {

                if((nums[i] & (1 << bitIndex)) != 0) {
                    count++;
                }
            }
            if(count % 3 == 1) {
                ans = ans | (1 << bitIndex);
            }
        }

        return ans;
    }

    // TC: Sorting: nlog(n)
    // TC: nlogn + n/3
    // SC: O(1)
    static int singleNumber21(int[] nums) {     // Must Pass Sorted Array
        int n = nums.length;

        for(int i=1; i<n; i+=3) {

            if((nums[i] ^ nums[i-1]) == 0) continue;
            else return nums[i-1];
        }

        return nums[n-1];
    }

    // TC: O(n)
    // SC: O(1)
    static int singleNumber22(int[] nums) {     // To Revise

        int n = nums.length;
        int ones = 0;
        int twos = 0;

        for(int i=0; i<n; i++) {

            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }

        return ones;
    }


    // TC: O(2n)
    // SC: O(1)
    static int[] singleNumber3(int[] nums) {

        int n = nums.length;

        long xorAll = 0;
        for(int i=0; i<n; i++) xorAll ^= nums[i];

        long rightMost = (xorAll & xorAll-1) ^ xorAll;
        int b1 = 0;
        int b2 = 0;

        for(int i=0; i<n; i++) {

            if((nums[i] & rightMost) != 0) b1 ^= nums[i];
            else b2 ^= nums[i];
        }

        return new int[] {b1,b2};
    }

}
