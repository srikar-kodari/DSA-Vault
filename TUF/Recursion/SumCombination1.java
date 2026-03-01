import java.util.ArrayList;
import java.util.List;

public class SumCombination1 {
    
    public static void main(String[] args) {

        // int[] arr = {1,2,3,5,6};
        // int[] arr = {2,3,6,7};
        int[] arr = {1,2,3};
        List<List<Integer>> ans = findCombinations(arr, 4);
        // System.out.println(ans);
        for(List<Integer> a : ans) System.out.println(a);

    }
    
    // TC: O(2^n * (target/minVal)) ~ exponential in n, SC: O(target/minVal) - recursion stack + list
    static List<List<Integer>> findCombinations(int[] arr, int target) {

        List<List<Integer>> result = new ArrayList<>();
        combinationSum(0, arr, target, result, new ArrayList<>());

        return result;
    }

    // TC: O(2^n * (target/minVal)) ~ exponential in n, SC: O(target/minVal) - recursion stack + list
    static void combinationSum(int index, int[] arr, int targetSum, List<List<Integer>> ans, List<Integer> list) {

        if(index == arr.length) {

            if(targetSum == 0) {

                ans.add(new ArrayList<>(list));
            }
            return;
        }

        if(arr[index] <= targetSum) {

            list.add(arr[index]);
            combinationSum(index, arr, targetSum-arr[index], ans, list);
            list.removeLast();
        }

        combinationSum(index+1, arr, targetSum, ans, list);
    }

}
