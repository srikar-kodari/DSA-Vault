import java.util.ArrayList;
import java.util.List;

public class PowerSet {     // Print All Subsets
    public static void main(String[] args) {

        int[] arr = {1,2,3};
        List<List<Integer>> ans = powerSet(arr);

        for (List<Integer> list : ans) {
            System.out.println(list);
        }
        
    }

    // TC: O(2^n * n) - 2^n subsets, n bits checked per subset
    // SC: O(2^n * n) - storing all subsets
    static List<List<Integer>> powerSet(int[] arr) {

        List<List<Integer>> result = new ArrayList<>();

        int n = arr.length;
        int subSets = 1 << n;   // 2^n -> (1 << n)

        for(int i=0; i < subSets; i++) {

            List<Integer> list = new ArrayList<>();
            for(int j=0; j<n; j++) {

                if((i & (1 << j)) != 0) list.add(arr[j]);
            }

            result.add(list);
        }

        return result;
    }

}
