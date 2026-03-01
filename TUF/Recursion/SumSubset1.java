import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SumSubset1 {
    public static void main(String[] args) {

        // int[] arr = {5,2,1};
        int[] arr = {3,1,2};
        List<Integer> ans = sumSubset1(arr);

        // Sorting 2^n subset sums: TC O(2^n log(2^n)) = O(n * 2^n), SC O(log(2^n)) = O(n)
        Collections.sort(ans);
        
        System.out.println("Result is: " + ans);

    }

    static List<Integer> sumSubset1(int[] arr) {

        List<Integer> result = new ArrayList<>();
        int n = arr.length;

        subsetSum1(0, 0, arr, n, result);
        
        return result;
    }

    // TC (subset generation): O(2^n)
    // SC (auxiliary): O(n) recursion stack
    // SC (including output list): O(2^n)
    static void subsetSum1(int index, int sum, int[] arr, int n, List<Integer> result) {

        if(index == n) {
            result.add(sum);
            return;
        }

        // Pick
        sum += arr[index];
        subsetSum1(index+1, sum, arr, n, result);

        // Do Not Pick
        sum -= arr[index];
        subsetSum1(index+1, sum, arr, n, result);
    }

}
