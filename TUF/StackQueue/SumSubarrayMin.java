import java.util.Arrays;
import java.util.Stack;

public class SumSubarrayMin {
    public static void main(String[] args) {

        // int[] arr = {3,1,2,4};
        // int ans = sumSubarrayMin(arr);
        // System.out.println("Sum of Subarray Min is: " + ans);

        // int[] arr = {3,1,2,4};
        // int ans = sumSubarrayMinBrute(arr);
        // System.out.println("Sum of Subarray Min is: " + ans);

        int[] arr = {1,2,3,1,2,1,1,2,4,5,6,7,8,1,1,1};
        int[] prevSmaller = prevSmallerIndex(arr);
        int[] nextSmaller = nextSmallerIndex(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(prevSmaller));
        System.out.println(Arrays.toString(nextSmaller));
        int ans = sumSubarrayMin(arr);
        System.out.println("Sum of Subarray Min is: " + ans);
        
    }

    static int sumSubarrayMin(int[] arr) {      // Handles Array with Duplicate Values

        int mod = 1_000_000_007;
        int n = arr.length;
        
        int[] prevSmall = prevSmallerIndex(arr);
        int[] nextSmall = nextSmallerIndex(arr);

        long sum = 0;

        for(int i=0; i<n; i++) {

            long left = i - prevSmall[i];
            long right = nextSmall[i] - i;

            long totalWays = left * right;

            long totalSum = arr[i] * totalWays;

            sum = (sum + totalSum) % mod;
        }

        return (int) sum;
    }

    // TC: O(2N)
    // SC: O(N) + O(N)
    static int[] prevSmallerIndex(int[] nums) {     // If stack is empty (Ele not present), adds -1

        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] pSE = new int[n];

        for(int i=0; i<n; i++) {

            while(!stack.isEmpty() && nums[stack.peek()] > nums[i]) {   // Strictly Greater - Condition to Remove Duplicate Count. Vice versa with nextSmallerIndex().
                stack.pop();
            }

            pSE[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(i);
        }

        return pSE;
    }

    // TC: O(2N)
    // SC: O(N) + O(N)
    static int[] nextSmallerIndex(int[] nums) {     // If stack is empty (Ele not present), adds n

        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] nSE = new int[n];

        for(int i=n-1; i>=0; i--) {

            while(!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {  // One should be >= and other should be >, to handle Duplicate Count
                stack.pop();
            }

            nSE[i] = stack.isEmpty() ? n : stack.peek();

            stack.push(i);
        }

        return nSE;
    }
    

    static int sumSubarrayMinBrute(int[] arr) {     // Brute: Only works for small inputs

        int mod = 1_000_000_007;
        int n = arr.length;

        long ans = 0;
        for(int i=0; i<n; i++) {

            int min = arr[i];
            for(int j=i; j<n; j++) {

                min = Math.min(min, arr[j]);
                ans = (ans + min) % mod;
            }
        }

        return (int) ans;
    }

}
