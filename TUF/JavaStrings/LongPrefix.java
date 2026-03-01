import java.util.Arrays;

public class LongPrefix {
    
    public static String longestCommonPrefix(String[] str) {    // Longest Common Prefix - Basic

        Arrays.sort(str);

        StringBuilder result = new StringBuilder();

        String s1 = str[0];
        String s2 = str[str.length-1];

        int min = Math.min(s1.length(), s2.length());

        for(int i=0; i<min; i++) {

            if(s1.charAt(i) == s2.charAt(i)) {
                result.append(s1.charAt(i));
            }
            else return result.toString();
        }

        return result.toString();   // || s1 || s2 - Because, complete word is stored
    }

    public static String longPrefix(String[] str) {     // Returns substring - More efficient

        if(str.length == 1) return str[0];

        Arrays.sort(str);

        String s1 = str[0];
        String s2 = str[str.length-1];

        int min = Math.min(s1.length(), s2.length());

        for(int i=0; i<min; i++) {

            if(s1.charAt(i) != s2.charAt(i)) return s1.substring(0, i);
        }

        return s1;
    }
}
