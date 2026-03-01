import java.util.Stack;

public class BalancedParentheses {
    public static void main(String[] args) {
        System.out.println(isBalancedParentheses("()[]{}"));
    }
    
    static boolean isBalancedParentheses(String s) {    // Input String contains only - []{}()

        Stack<Character> stack = new Stack<>();

        for(int i=0; i<s.length(); i++) {

            char charAtI = s.charAt(i);
            if(charAtI == '{' || charAtI == '[' || charAtI == '(') stack.push(charAtI);
            else {
                if(stack.isEmpty()) return false;

                char top = stack.pop();
                if((charAtI == ')' && top != '(') ||
                   (charAtI == '}' && top != '{') ||
                   (charAtI == ']' && top != '['))
                   {
                        return false;
                   }
                
            }
        }
        return stack.isEmpty();
    }
}
