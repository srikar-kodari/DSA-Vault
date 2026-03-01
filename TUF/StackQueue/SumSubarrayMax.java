import java.util.Arrays;
import java.util.Stack;

public class SumSubarrayMax {
    public static void main(String[] args) {

        // int[] arr = {3,1,2,4};
        // int ans = sumSubarrayMax(arr);
        // System.out.println("Sum of Subarray Max is: " + ans);

        int[] arr = {1,2,3,1,2,1,1,2,4,5,6,7,8,1,1,1};
        int[] prevGreater = prevGreaterIndex(arr);
        int[] nextGreater = nextGreaterIndex(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(prevGreater));
        System.out.println(Arrays.toString(nextGreater));
        int ans = sumSubarrayMax(arr);
        System.out.println("Sum of Subarray Max is: " + ans);

    }

    static int sumSubarrayMax(int[] arr) {      // Handles Array with Duplicate Values

        int mod = 1_000_000_007;
        int n = arr.length;

        int[] prevGreat = prevGreaterIndex(arr);
        int[] nextGreat = nextGreaterIndex(arr);

        long sum = 0;

        for(int i=0; i<n; i++) {

            long left = i - prevGreat[i];
            long right = nextGreat[i] - i;

            long totalWays = left * right;

            long totalSum = arr[i] * totalWays;

            sum = (sum + totalSum) % mod;
        }

        return (int) sum;
    }

    // TC: O(2N)
    // SC: O(N) + O(N)
    static int[] prevGreaterIndex(int[] nums) {     // If stack is empty (Ele not present), adds -1

        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] pGE = new int[n];

        for(int i=0; i<n; i++) {

            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]) {   // Strictly Smaller - Condition to Remove Duplicate Count. Vice versa with nextGreaterIndex().
                stack.pop();
            }

            pGE[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(i);
        }

        return pGE;
    }

    // TC: O(2N)
    // SC: O(N) + O(N)
    static int[] nextGreaterIndex(int[] nums) {     // If stack is empty (Ele not present), adds n

        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] nGE = new int[n];

        for(int i=n-1; i>=0; i--) {

            while(!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {  // One should be <= and other should be <, to handle Duplicate Count
                stack.pop();
            }

            nGE[i] = stack.isEmpty() ? n : stack.peek();

            stack.push(i);
        }

        return nGE;
    }

}
