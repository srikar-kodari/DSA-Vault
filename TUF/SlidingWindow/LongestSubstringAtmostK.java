import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringAtmostK {

    // Similar To Fruits Into Baskets Problem

    public static void main(String[] args) {

        int ans = atmostKDistinct("aaabbccd", 2);
        System.out.println(ans);

        // int ans = atmostKDistinctOptimal("aaabbccd", 2);
        // System.out.println(ans);

        // int ans = atmostKDistinctBrute("aaabbccd", 2);
        // System.out.println(ans);

    }

    // TC: O(N)
    static int atmostKDistinctOptimal(String s, int k) {

        int n = s.length();
        int left = 0, right = 0, maxLen = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        while (right < n) {
            
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);

            if(map.size() > k) {

                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);

                if(map.get(s.charAt(left)) == 0) map.remove(s.charAt(left));

                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }

    // TC: O(2N), SC: O(256)
    static int atmostKDistinct(String s, int k) {

        int n = s.length();
        int left = 0, right = 0, maxLen = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        while (right < n) {
            
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);

            while (map.size() > k) {
                
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);

                if(map.get(s.charAt(left)) == 0) map.remove(s.charAt(left));

                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }

    // TC: O(N^2), SC: O(256)
    static int atmostKDistinctBrute(String s, int k) {

        int n = s.length();
        int maxLen = 0;

        for(int i=0; i<n; i++) {

            HashSet<Character> set = new HashSet<>();
            for(int j=i; j<n; j++) {

                set.add(s.charAt(j));

                if(set.size() <= k) maxLen = Math.max(maxLen, j-i+1);
                else break;
            }
        }

        return maxLen;
    }

}
