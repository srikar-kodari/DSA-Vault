import java.util.ArrayList;
import java.util.List;

public class SumCombination3 {
    public static void main(String[] args) {

        List<List<Integer>> ans = findCombinations3(3, 9);
        System.out.println("Result is: " + ans);
        
    }

    static List<List<Integer>> findCombinations3(int k, int n) {

        List<List<Integer>> result = new ArrayList<>();

        int[] arr = {1,2,3,4,5,6,7,8,9};

        sumCombination3(0, arr, k, n, new ArrayList<>(), result);

        return result;
    }

    // TC: O(C(9, k) * k) - generates C(9,k) combinations, each takes O(k) to copy
    // SC: O(k) recursion stack depth + O(C(9, k) * k) for result storage
    static void sumCombination3(int index, int[] arr, int k, int targetSum, List<Integer> list, List<List<Integer>> result) {

        if(list.size() == k) {
            if(targetSum == 0) {
                result.add(new ArrayList<>(list));
            }
            return;
        }

        for(int i = index; i < arr.length; i++) {

            if(arr[i] > targetSum) break;

            list.add(arr[i]);
            sumCombination3(i+1, arr, k, targetSum - arr[i], list, result);
            list.removeLast();
        }
    }

}
