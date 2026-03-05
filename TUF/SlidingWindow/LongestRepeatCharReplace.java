public class LongestRepeatCharReplace {
    public static void main(String[] args) {

        int ans = charReplacement("AABABBA", 2);
        System.out.println(ans);

        // int ans = charReplacementBrute("AABABBA", 2);
        // System.out.println(ans);
        
    }

    // TC: O(2N) X 26, SC: O(26)
    static int charReplacement(String s, int k) {

        int n = s.length();
        int left = 0, right = 0, maxFreq = 0, maxLen = 0;
        int[] hash = new int[26];

        while (right < n) {

            hash[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, hash[s.charAt(right) - 'A']);

            while ((right - left + 1) - maxFreq > k) {

                hash[s.charAt(left) - 'A']--;

                // Update Max Frequency
                maxFreq = 0;
                for(int i=0; i<26; i++) maxFreq = Math.max(maxFreq, hash[i]);
                
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }

    // TC: O(N^2), SC: O(26)
    static int charReplacementBrute(String s, int k) {

        int n = s.length();
        int changes = 0;
        int maxLen = 0;

        for(int i=0; i<n; i++) {

            int maxFreq = 0;
            int[] hash = new int[26];
            for(int j=i; j<n; j++) {

                hash[s.charAt(j) - 'A']++;
                maxFreq = Math.max(maxFreq, hash[s.charAt(j) - 'A']);

                changes = (j - i + 1) - maxFreq;

                if(changes <= k) maxLen = Math.max(maxLen, j - i + 1);
                else break;
            }
        }

        return maxLen;
    }

}
