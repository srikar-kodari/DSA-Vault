import java.util.Arrays;
import java.util.HashSet;

public class SubstringsContain3Chars {
    public static void main(String[] args) {

        int ans = numberOfSubstrings3Chars("aaacb");
        System.out.println(ans);

        // // int ans = numberOfSubstrings3CharsBrute("abcabc");
        // int ans = numberOfSubstrings3CharsBrute("aaacb");
        // System.out.println(ans);
        
    }

    // TC: O(N), SC: O(1)
    static int numberOfSubstrings3Chars(String s) {

        int n = s.length();
        int count = 0;
        int[] lastSeen = {-1,-1,-1};

        for(int i=0; i<n; i++) {

            lastSeen[s.charAt(i) - 'a'] = i;

            if(lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {

                count += 1 + Arrays.stream(lastSeen).min().getAsInt();  // Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
            }
        }

        return count;
    }

    // TC: O(N^2), SC: O(1)
    static int numberOfSubstrings3CharsBrute(String s) {

        int n = s.length();
        int count = 0;

        for(int i=0; i<n; i++) {

            HashSet<Character> set = new HashSet<>();
            for(int j=i; j<n; j++) {

                set.add(s.charAt(j));

                if(set.size() == 3) count++;
            }
        }

        return count;
    }

}
