import java.util.Arrays;

public class MinBouquets {  //Min days to make M bouquets
    
    public static int minDays(int[] bloomDay, int m, int k) {

        if(bloomDay.length < (m * k)) return -1;

        int low = Arrays.stream(bloomDay).min().getAsInt();
        int high = Arrays.stream(bloomDay).max().getAsInt();

        int ans = high; // Edge Case: ans = -1;

        while(low <= high) {
            int mid = (low + high)/2;

            if(isPossible(bloomDay, mid, m, k)) {
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;

        }
        return ans; //low is also the answer
    }

    public static boolean isPossible(int[] bloomDay, int mid, int m, int k) {
        int count = 0;
        int finalCount = 0;

        for(int n : bloomDay) {
            if(n <= mid) count++;
            else {
                finalCount += count/k;
                count = 0;
            }
        }
        finalCount += count/k;

        if(finalCount >= m) return true;
        else return false;
    }

}
