public class MaxCardsPoints {
    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,1};
        int ans = maxCardsPoints(arr, 3);
        System.out.println(ans);
        
    }

    // TC: O(2K), SC: O(1)
    // You Can Pick Only From Front / Back
    static int maxCardsPoints(int[] arr, int k) {

        int n = arr.length;

        if(k == n) {
            int sum = 0;
            for(int i=0; i<n; i++) sum += arr[i];
            return sum;
        }

        int leftSum = 0;    int rightSum = 0;   int rightIndex = n-1;

        for(int i=0; i<k; i++) leftSum += arr[i];

        int maxSum = leftSum;

        for(int i=k-1; i>=0; i--) {

            leftSum = leftSum - arr[i];
            rightSum = rightSum + arr[rightIndex];
            rightIndex--;

            maxSum = Math.max(maxSum, leftSum + rightSum);
        }

        return maxSum;
    }

}
