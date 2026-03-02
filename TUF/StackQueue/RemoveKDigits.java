import java.util.Stack;

public class RemoveKDigits {    // Greedy & Monotonic Stack Problem
    public static void main(String[] args) {

        // String ans = removeKDigits("1432219", 3);
        String ans = removeKDigits("10200", 1);
        System.out.println(ans);
        
    }

    // TC: O(N), SC: O(N)
    static String removeKDigits(String n, int k) {

        int len = n.length();

        if(len == k) return "0";

        Stack<Character> stack = new Stack<>();
        for(int i=0; i<len; i++) {
            while (!stack.isEmpty() && k > 0 && (stack.peek() - '0') > (n.charAt(i) - '0')) {
                stack.pop();
                k--;
            }

            stack.push(n.charAt(i));
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        if(stack.isEmpty()) return "0";

        StringBuilder ans = new StringBuilder();

        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        while (ans.length() != 0 && ans.charAt(ans.length()-1) == '0') {
            ans.deleteCharAt(ans.length() - 1);
        }

        return (ans.length() == 0) ? "0" : ans.reverse().toString();
    }

}
