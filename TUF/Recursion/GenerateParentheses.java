import java.util.*;

public class GenerateParentheses {

    public static void main(String[] args) {

        List<String> ans = generateParentheses(3);
        
        for(String s : ans) {
            System.out.println(s);
        }
        // System.out.println(ans);
    }

    public static List<String> generateParentheses(int n) {

        List<String> result = new ArrayList<>();

        genParentheses("", 0, 0, n, result);

        return result;
    }

    public static void genParentheses(String curr, int open, int close, int n, List<String> result) {

        // Base case: length reached
        if (curr.length() == 2*n) {
            result.add(curr);
            return;
        }

        if(open < n) genParentheses(curr + "(", open + 1, close, n, result);
        if(close < open) genParentheses(curr + ")", open, close + 1, n, result);
    }

}
