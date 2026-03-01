import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterEle {   // Monotonic Stack Introduction

    public static void main(String[] args) {

        // // int[] arr = {4,12,5,3,1,2,5,3,1,2,4,6};
        // // int[] arr = {1,3,2,4};
        // int[] arr = {6,8,0,1,3};
        // int[] result = nextGreater(arr);
        // System.out.println(Arrays.toString(arr));
        // System.out.println(Arrays.toString(result));

        // int[] arr1 = {4,1,2};
        // int[] arr2 = {1,3,4,2};
        // int[] result = nextGreaterEle1(arr1, arr2);
        // System.out.println(Arrays.toString(result));

        // // int[] arr = {1,2,1};
        // int[] arr = {1,2,3,4,3};
        // int[] ans = nextGreaterEle2(arr);
        // System.out.println(Arrays.toString(arr));
        // System.out.println(Arrays.toString(ans));

        // // int[] arr = {4,5,2,10,8,1};
        // int[] arr = {5,7,9,6,7,4,5,1,3,7};
        // int[] result = prevSmallerEle(arr);
        // System.out.println(Arrays.toString(arr));
        // System.out.println(Arrays.toString(result));

        int[] arr = {2,1,3,1,4,2,3,1,5};
        int[] result = prevGreaterEle(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(result));

    }

    // TC: O(2N)
    // SC: O(N) + O(N)
    static int[] nextGreater(int[] arr) {

        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] nGE = new int[n];

        for(int i=n-1; i>=0; i--) {
            while (!stack.isEmpty() && arr[i] >= stack.peek()) {
                stack.pop();
            }

            nGE[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(arr[i]);
        }

        return nGE;
    }

    static int[] nextGreaterEle1(int[] nums1, int[] nums2) {

        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        int n2 = nums2.length;
        int n1 = nums1.length;
        int[] result = new int[n1];

        for(int i=n2-1; i>=0; i--) {
            while (!stack.isEmpty() && nums2[i] >= stack.peek()) {
                stack.pop();
            }

            if(stack.isEmpty()) map.put(nums2[i], -1);
            else map.put(nums2[i], stack.peek());

            stack.push(nums2[i]);
        }

        for(int i=0; i<n1; i++) result[i] = map.get(nums1[i]);

        return result;
    }

    // Circular Array
    static int[] nextGreaterEle2(int[] nums) {

        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] nGE = new int[n];

        for(int i = 2*n-1; i >= 0; i--) {

            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }

            if(i < n) {
                nGE[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(nums[i % n]);
        }

        return nGE;
    }

    // TC: O(2N)
    // SC: O(N) + O(N)
    static int[] nextSmallerEle(int[] nums) {

        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] nSE = new int[n];

        for(int i=n-1; i>=0; i--) {

            while(!stack.isEmpty() && stack.peek() >= nums[i]) {
                stack.pop();
            }

            nSE[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(nums[i]);
        }

        return nSE;
    }

    // TC: O(2N)
    // SC: O(N) + O(N)
    static int[] prevSmallerEle(int[] nums) {

        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] pSE = new int[n];

        for(int i=0; i<n; i++) {

            while(!stack.isEmpty() && stack.peek() >= nums[i]) {
                stack.pop();
            }

            pSE[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(nums[i]);
        }

        return pSE;
    }

    static int[] prevGreaterEle(int[] nums) {

        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] pGE = new int[n];

        for(int i=0; i<n; i++) {

            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                stack.pop();
            }

            pGE[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(nums[i]);
        }

        return pGE;
    }

}
