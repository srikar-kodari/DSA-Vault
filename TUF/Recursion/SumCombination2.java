import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumCombination2 {
    public static void main(String[] args) {

        int[] arr = {10,1,2,7,6,1,5};
        // int[] arr = {10,1,2,7,6,5};
        // int[] arr = {2,5,2,1,2};
        List<List<Integer>> ans = findCombinations2(arr, 8);
        
        for(List<Integer> l1 : ans) System.out.println(l1);

    }

    // TC: O(2^n) - exponential, SC: O(target/minVal) - recursion stack + list
    static List<List<Integer>> findCombinations2(int[] arr, int target) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(arr);
        combinationSum2(0, arr, target, result, new ArrayList<>()); // Must Pass Sorted Array

        return result;
    }

    // TC: O(2^n) - exponential with duplicates handled, SC: O(target/minVal) - recursion stack depth + list
    static void combinationSum2(int index, int[] arr, int targetSum, List<List<Integer>> ans, List<Integer> list) {

        if(targetSum == 0) {

            ans.add(new ArrayList<>(list));
            return;
        }

        for(int i = index; i < arr.length; i++) {

            if(i > index && arr[i] == arr[i-1]) continue;   // If it's a repeated element, then continue
            if(arr[i] > targetSum) break;

            list.add(arr[i]);
            combinationSum2(i+1, arr, targetSum-arr[i], ans, list);
            list.removeLast();
        }
    }

}
