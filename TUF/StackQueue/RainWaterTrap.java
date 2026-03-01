public class RainWaterTrap {
    public static void main(String[] args) {

        // int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        // int ans = rainWaterTrap(arr);
        // System.out.println(ans + " Units of Water Stored..");

        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        int ans = rainWaterTrapMain(arr);
        System.out.println(ans + " Units of Water Stored..");

    }

    // TC: O(N)
    // SC: O(1)
    static int rainWaterTrapMain(int[] arr) {

        int n = arr.length;
        int leftMax = 0;
        int rightMax = 0;
        int total = 0;
        int left = 0;
        int right = n-1;

        while (left < right) {
            if(arr[left] <= arr[right]) {
                if(leftMax > arr[left]) {
                    total += leftMax - arr[left];
                }
                else {
                    leftMax = arr[left];
                }
                left++;
            }
            else {
                if(rightMax > arr[right]) {
                    total += rightMax -arr[right];
                }
                else {
                    rightMax = arr[right];
                }
                right--;
            }
        }

        return total;
    }

    // TC: O(2N) + O(N)
    // SC: O(2N)
    static int rainWaterTrap(int[] arr) {

        int n = arr.length;
        int[] prefix = prefixMax(arr);  // O(N)
        int[] suffix = suffixMax(arr);  // O(N)

        int ans = 0;
        for(int i=0; i<n; i++) {    // O(N)

            if(arr[i] < prefix[i] && arr[i] < suffix[i]) {
                ans += (Math.min(prefix[i], suffix[i]) - arr[i]);
            }
        }

        return ans;
    }

    // TC: O(N), SC: O(N)
    static int[] prefixMax(int[] arr) {

        int n = arr.length;
        int[] prefix = new int[n];

        int max = arr[0];
        for(int i=0; i<n; i++) {
            max = Math.max(max, arr[i]);
            prefix[i] = max;
        }

        return prefix;
    }

    // TC: O(N), SC: O(N)
    static int[] suffixMax(int[] arr) {

        int n = arr.length;
        int[] suffix = new int[n];

        int max = arr[n-1];
        for(int i=n-1; i>=0; i--) {
            max = Math.max(max, arr[i]);
            suffix[i] = max;
        }

        return suffix;
    }

}
