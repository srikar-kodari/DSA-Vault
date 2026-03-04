public class MaxConsecutiveOnes {

    // Intuition: Longest Subarray With Atmost K 0's

    public static void main(String[] args) {

        int[] arr = {1,1,1,0,0,0,1,1,1,1,0};
        int ans = maxConsecutiveOnesOptimal(arr, 2);
        System.out.println(ans);

        // int[] arr = {1,1,1,0,0,0,1,1,1,1,0};
        // int ans = maxConsecutiveOnes(arr, 2);
        // System.out.println(ans);

        // int[] arr = {1,1,1,0,0,1,1};
        // int ans = maxConsecutiveOnesBrute(arr, 2);
        // System.out.println(ans);
        
    }

    // TC: O(N), SC: O(1)
    static int maxConsecutiveOnesOptimal(int[] arr, int k) {

        int n = arr.length;

        int left = 0, right = 0, zeros = 0, len = 0, maxLen = 0;

        while (right < n) {

            if(arr[right] == 0) zeros++;

            if(zeros > k) {
                if(arr[left] == 0) zeros--;
                left++;
            }
            
            if(zeros <= k) {
                len = right - left + 1;
                maxLen = Math.max(maxLen, len);
            }

            right++;
        }

        return maxLen;
    }

    // TC: O(2N), SC: O(1)
    static int maxConsecutiveOnes(int[] arr, int k) {

        int n = arr.length;

        int left = 0, right = 0, zeros = 0, len = 0, maxLen = 0;

        while (right < n) {

            if(arr[right] == 0) zeros++;

            while (zeros > k) {
                if(arr[left] == 0) zeros--;
                left++;
            }

            if(zeros <= k) {
                len = right - left + 1;
                maxLen = Math.max(maxLen, len);
            }

            right++;
        }

        return maxLen;
    }

    // TC: O(N^2), SC: O(1)
    static int maxConsecutiveOnesBrute(int[] arr, int k) {

        int n = arr.length;

        int maxLen = 0;
        for(int i=0; i<n; i++) {

            int len = 0;
            int zeros = 0;
            for(int j=i; j<n; j++) {

                if(arr[j] == 0) zeros++;

                if(zeros <= k) {
                    len = j - i + 1;
                    maxLen = Math.max(maxLen, len);
                }
                else break;
            }
        }

        return maxLen;
    }

}
