import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jan26 {    // LC 1200: Minimum Absolute Difference

    public static List<List<Integer>> minAbsDifference(int[] arr) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(arr);

        int len = arr.length;

        int min = Integer.MAX_VALUE;

        for(int i=1; i<len; i++) {  // Finding min difference

            min = Math.min(min, arr[i]-arr[i-1]);
        }

        for(int i=0; i<len-1; i++) {    // TC: O(n)

            if(arr[i+1] - arr[i] == min) result.add(Arrays.asList(arr[i], arr[i+1]));
        }

        return result;
    }
}
