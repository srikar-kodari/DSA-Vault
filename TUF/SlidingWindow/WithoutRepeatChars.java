import java.util.Arrays;

public class WithoutRepeatChars {
    public static void main(String[] args) {

        // String s = "cadbzabcd";
        String s = "abcabcddab";
        int ans = longestSubstring(s);
        System.out.println(ans);

        // String s = "cadbzabcd";
        // int ans = longestSubstringBrute(s);
        // System.out.println(ans);
        
    }

    // TC: O(N), SC: O(256)
    static int longestSubstring(String s) {

        int[] hash = new int[256];
        Arrays.fill(hash, -1);      // for(int i=0; i<256; i++) hash[i] = -1;

        int n = s.length();
        int left = 0;   int right = 0;  int maxLen = 0;

        while(right < n) {
            
            // Current character has appeared before somewhere in the string
            if(hash[s.charAt(right)] != -1) {

                // Move left only if duplicate is inside window; indices < left are outside and must not move left backward
                if(hash[s.charAt(right)] >= left) {
                    left = hash[s.charAt(right)] + 1;
                }
                
            }

            int len = right - left + 1;
            maxLen = Math.max(maxLen, len);

            hash[s.charAt(right)] = right;
            right++;
        }

        return maxLen;
    }

    // TC: O(N^2), SC: O(256)
    static int longestSubstringBrute(String s) {

        int n = s.length();

        int maxLen = 0;

        for(int i=0; i<n; i++) {

            int len = 0;
            int[] hash = new int[256];
            for(int j=i; j<n; j++) {

                if(hash[s.charAt(j)] == 1) break;

                len = j - i + 1;

                maxLen = Math.max(maxLen, len);

                hash[s.charAt(j)] = 1;
            }
        }

        return maxLen;
    }

}
