import java.util.ArrayList;
import java.util.List;

public class PowerSet {
    
    public static void main(String[] args) {

        String s = "abc";
        generateSubsequences(s);
    }

    static void generateSubsequences(String s) {    // Non Sorted Output

        List<String> ans = new ArrayList<>();

        int n = s.length();
        genSubsequences(0, n, s, ans);

        // return ans;
    }

    static void genSubsequences(int index, int n, String s, List<String> result) {

        if(index == n) {
            for(String str : result) System.out.print(str + " ");
            System.out.println();
            return;
        }

        result.add(String.valueOf(s.charAt(index)));
        genSubsequences(index+1, n, s, result);

        result.removeLast();
        genSubsequences(index+1, n, s, result);
    }

}
