public class ToSolveSumSubarrayMin {
    public static void main(String[] args) {

        int[] arr = {3,1,2,4};
        int ans = sumSubarrayMin(arr);
        System.out.println("Sum of Subarray Min is: " + ans);


        
    }

    static int sumSubarrayMin(int[] arr) {

        int mod = 1_000_000_007;
        long total = 0;
        int n = arr.length;
        int[] nSE = NextGreaterEle.nextSmallerEle(arr);
        int[] pSE = NextGreaterEle.prevSmallerEle(arr);

        for(int i=0; i<n; i++) {

            long left = i - pSE[i];
            long right = nSE[i] - i;

            total = (total + ((right * left) % mod) * arr[i]) % mod;
        }

        return (int) total;
    }

}
