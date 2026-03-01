import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumSubset2 {
    public static void main(String[] args) {

        int[] arr = {1,2,2};
        List<List<Integer>> ans = sumSubset2(arr);

        for (List<Integer> list : ans) System.out.println(list);

    }

    static List<List<Integer>> sumSubset2(int[] arr) {

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);

        subsetSum2(0, arr, new ArrayList<>(), result);

        return result;
    }

    static void subsetSum2(int index, int[] arr, List<Integer> list, List<List<Integer>> result) {

        result.add(new ArrayList<>(list));

        for(int i = index; i < arr.length; i++) {

            if(i != index && arr[i] == arr[i-1]) continue;   // If it's a repeated element, then continue

            list.add(arr[i]);
            subsetSum2(i+1, arr, list, result);
            list.removeLast();
        }
    }
    
}
