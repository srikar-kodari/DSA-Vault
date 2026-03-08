import java.util.Arrays;

public class ShortestJobFirst {
    public static void main(String[] args) {

        int[] arr = {4,3,7,1,2};
        int ans = averageTime(arr);
        System.out.println(ans);

    }

    // TC: O(N + Nlog(N)), SC: O(1)
    static int averageTime(int[] arr) {

        int n = arr.length;
        Arrays.sort(arr);

        int timer = 0, waitTime = 0;

        for(int i=0; i<n; i++) {

            waitTime += timer;

            timer += arr[i];
        }

        return waitTime / n;
    }

}
