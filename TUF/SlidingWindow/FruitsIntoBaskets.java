import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FruitsIntoBaskets {

    // Longest Subarray With Atmost 2 Types Of Fruits

    public static void main(String[] args) {

        // int[] fruits = {1,0,1,1,2,2,1};
        // int ans = totalFruits(fruits, 2);
        // System.out.println(ans);

        int[] fruits = {1,0,1,1,2,2,1};
        int ans = totalFruitsOptimal(fruits, 2);
        System.out.println(ans);

        // int[] fruits = {1,0,1,1,2,2};
        // int ans = totalFruitsBrute(fruits);
        // System.out.println(ans);
        
    }

    static int totalFruitsOptimal(int[] fruits, int k) {    // k -> Distinct Fruits You Can Put In Basket

        int n = fruits.length;
        int left = 0, right = 0, maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (right < n) {
            
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);

            if(map.size() > k) {

                map.put(fruits[left], map.get(fruits[left]) - 1);

                if(map.get(fruits[left]) == 0) map.remove(fruits[left]);

                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);

            right++;
        }

        return maxLen;
    }

    // TC: O(2N), (SC: O(3) -> When k=2 -> Map Size)
    static int totalFruits(int[] fruits, int k) {   // k -> Distinct Fruits You Can Put In Basket

        int n = fruits.length;
        int left = 0, right = 0, maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (right < n) {
            
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);

            if(map.size() > k) {    // k = 2, in our problem
                while (map.size() > k) {
                    map.put(fruits[left], map.get(fruits[left]) - 1);

                    if(map.get(fruits[left]) == 0) map.remove(fruits[left]);

                    left++;
                }
            }

            if(map.size() <= k) {

                maxLen = Math.max(maxLen, right - left + 1);
            }

            right++;
        }

        return maxLen;
    }

    // TC: O(N^2), SC: O(3) -> Set Size
    static int totalFruitsBrute(int[] fruits) {

        int n = fruits.length;
        int maxLen = 0;

        for(int i=0; i<n; i++) {

            Set<Integer> set = new HashSet<>();
            for(int j=i; j<n; j++) {

                set.add(fruits[j]);

                if(set.size() <= 2) maxLen = Math.max(maxLen, j-i+1);
                else break;
            }
        }

        return maxLen;
    }

}
