import java.util.ArrayList;
import java.util.List;

public class SumSubsequence {
    
    public static void main(String[] args) {

        int[] arr = {1,1,2,3,5,7};
        sumSubseq(arr, 5);

    }

    static void sumSubseq(int[] arr, int targetSum) {

        int n = arr.length;
        List<Integer> l1 = new ArrayList<>();

        sumSubsequence(0, l1, 0, targetSum, arr, n);    // Print All Subsequences

        // sumSubsequence1(0, l1, 0, targetSum, arr, n);   // Print Only 1 Subsequence

        int count = sumSubsequenceCount(0, 0, targetSum, arr, n);
        System.out.println("Total No Of Subsequences Are: " + count);
    }

    // TC: O(2^n) - explores all 2^n subsequences, SC: O(n) - recursion stack + list
    static void sumSubsequence(int index, List<Integer> list, int sum, int targetSum, int[] arr, int n) {   // Print All Subsequences

        if(index == n) {

            if(sum == targetSum) {

                System.out.println(list);
            }
            return;
        }

        list.add(arr[index]);
        sum += arr[index];

        // Pick
        sumSubsequence(index+1, list, sum, targetSum, arr, n);

        sum -= arr[index];
        list.removeLast();

        // Not Pick
        sumSubsequence(index+1, list, sum, targetSum, arr, n);
    }

    // TC: O(2^n) - explores all subsequences but returns early, SC: O(n) - recursion stack depth
    static boolean sumSubsequence1(int index, List<Integer> list, int sum, int targetSum, int[] arr, int n) {   // Print Only 1 Subsequence

        if(index == n) {

            // Condition Satisfied
            if(sum == targetSum) {

                System.out.println(list);
                return true;
            }
            // Condition Not Satisfied
            return false;
        }

        list.add(arr[index]);
        sum += arr[index];

        // Pick
        if(sumSubsequence1(index+1, list, sum, targetSum, arr, n) == true) return true;

        sum -= arr[index];
        list.removeLast();

        // Not Pick
        if(sumSubsequence1(index+1, list, sum, targetSum, arr, n)) return true;

        return false;
    }

    // TC: O(2^n) - explores all subsequences with pruning, SC: O(n) - recursion stack depth
    static int sumSubsequenceCount(int index, int sum, int targetSum, int[] arr, int n) {   // Count No Of Subsequences

        if(sum > targetSum) return 0;   // If array contains only positive intergers
        
        if(index == n) {

            // Condition Satisfied
            if(sum == targetSum) {

                return 1;
            }
            // Condition Not Satisfied
            return 0;
        }

        sum += arr[index];

        // Pick
        int left = sumSubsequenceCount(index+1, sum, targetSum, arr, n);

        sum -= arr[index];

        // Not Pick
        int right = sumSubsequenceCount(index+1, sum, targetSum, arr, n);

        return right + left;
    }

}
