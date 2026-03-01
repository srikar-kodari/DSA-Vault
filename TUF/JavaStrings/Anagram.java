import java.util.Arrays;

public class Anagram {
    
    public static boolean isAnagram(String s, String t) {   // Simple

        if(s.length() != t.length()) return false;

        char[] a = s.toCharArray();
        char[] b = t.toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);

        return Arrays.equals(a, b);
    }

    public static boolean isAnagram1(String s, String t) {  // Efficient. TC: O(n)

        if(s.length() != t.length()) return false;

        int[] freq = new int[26];   // Constraint: s and t consists only lowercase english letters

        for(int i=0; i<s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        for(int x : freq) if(x != 0) return false;

        return true;
    }
}
