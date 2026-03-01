import java.util.Stack;

public class ImpArithmeticExp {

    // Infix - Prefix - Postfix

    // Infix - (a+b)*(c-d)
    // Prefix - (* + ab - cd)   // Used in programming language - LISP & in Tree Data Structures
    // Postfix - (ab + cd - *)  // Used in stack based calculators

    public static void main(String[] args) {

        String infix = "(a+b*c-d)";
        String postfix = infixToPostfix(infix);
        String prefix = infixToPrefix(infix);
        String infixFromPostfix = postfixToInfix(postfix);
        String infixFromPrefix = prefixToInfix(prefix);
        String prefixFromPostfix = postfixToPrefix(postfix);
        String postfixFromPrefix = prefixToPostfix(prefix);
        System.out.println("Infix: " + infix + " and Postfix: " + postfix);
        System.out.println("Infix: " + infix + " and Prefix: " + prefix);
        System.out.println("Postfix: " + postfix + " and Infix: " + infixFromPostfix);
        System.out.println("Prefix: " + prefix + " and Infix: " + infixFromPrefix);
        System.out.println("Postfix: " + postfix + " and Prefix: " + prefixFromPostfix);
        System.out.println("Prefix: " + prefix + " and Postfix: " + postfixFromPrefix);

        
        

    }

    // TC: O(n), SC: O(n)
    static String infixToPostfix(String s) {

        int i = 0;
        Stack<Character> stack = new Stack<>();
        StringBuilder ans = new StringBuilder();

        while (i < s.length()) {
            
            char charAtI = s.charAt(i);
            if((charAtI >= 'A' && charAtI <= 'Z') ||
               (charAtI >= 'a' && charAtI <= 'z') ||
               (charAtI >= '0' && charAtI <= '9'))
               {
                ans.append(charAtI);
               }

            else if(charAtI == '(') stack.push(charAtI);

            else if(charAtI == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    
                    ans.append(stack.peek());
                    stack.pop();
                }
                stack.pop();
            }

            else {
                while (!stack.isEmpty() && stack.peek() != '(' &&
                        (priority(stack.peek()) > priority(charAtI) ||
                        (priority(stack.peek()) == priority(charAtI) && !isRightAssociative(charAtI)))) {
                    ans.append(stack.peek());
                    stack.pop();
                }
                stack.push(charAtI);
            }
            i++;
        }

        while (!stack.isEmpty()) {
            ans.append(stack.peek());
            stack.pop();
        }

        return ans.toString();
    }

    // TC: O(n), SC: O(n)
    static String infixToPrefix(String s) {

        StringBuilder reversed = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            char charAtI = s.charAt(i);
            if (charAtI == '(') reversed.append(')');
            else if (charAtI == ')') reversed.append('(');
            else reversed.append(charAtI);
        }

        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        int i = 0;
        while (i < reversed.length()) {

            char charAtI = reversed.charAt(i);
            if((charAtI >= 'A' && charAtI <= 'Z') ||
               (charAtI >= 'a' && charAtI <= 'z') ||
               (charAtI >= '0' && charAtI <= '9'))
               {
                postfix.append(charAtI);
               }

            else if(charAtI == '(') stack.push(charAtI);

            else if(charAtI == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {

                    postfix.append(stack.peek());
                    stack.pop();
                }
                stack.pop();
            }

            else {
                while (!stack.isEmpty() && stack.peek() != '(' &&
                        (priority(stack.peek()) > priority(charAtI) ||
                        (priority(stack.peek()) == priority(charAtI) && isRightAssociative(charAtI)))) {
                    postfix.append(stack.peek());
                    stack.pop();
                }
                stack.push(charAtI);
            }
            i++;
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.peek());
            stack.pop();
        }

        return postfix.reverse().toString();
    }

    // TC: O(n), SC: O(n)
    static String postfixToInfix(String s) {

        Stack<String> stack = new Stack<>();
        int i = 0;

        while (i < s.length()) {
            char charAtI = s.charAt(i);

            if((charAtI >= 'A' && charAtI <= 'Z') ||
               (charAtI >= 'a' && charAtI <= 'z') ||
               (charAtI >= '0' && charAtI <= '9')) {
                stack.push(String.valueOf(charAtI));
            }
            else {
                String top1 = stack.pop();
                String top2 = stack.pop();
                stack.push("(" + top2 + charAtI + top1 + ")");
            }

            i++;
        }

        return stack.pop();
    }

    // TC: O(n), SC: O(n)
    static String prefixToInfix(String s) {

        Stack<String> stack = new Stack<>();

        for (int i = s.length() - 1; i >= 0; i--) {
            char charAtI = s.charAt(i);

            if((charAtI >= 'A' && charAtI <= 'Z') ||
               (charAtI >= 'a' && charAtI <= 'z') ||
               (charAtI >= '0' && charAtI <= '9')) {
                stack.push(String.valueOf(charAtI));
            }
            else {
                String top1 = stack.pop();
                String top2 = stack.pop();
                stack.push("(" + top1 + charAtI + top2 + ")");
            }
        }

        return stack.pop();
    }

    // TC: O(n), SC: O(n)
    static String postfixToPrefix(String s) {

        Stack<String> stack = new Stack<>();
        int i = 0;

        while (i < s.length()) {
            char charAtI = s.charAt(i);

            if((charAtI >= 'A' && charAtI <= 'Z') ||
               (charAtI >= 'a' && charAtI <= 'z') ||
               (charAtI >= '0' && charAtI <= '9')) {
                stack.push(String.valueOf(charAtI));
            }
            else {
                String top1 = stack.pop();
                String top2 = stack.pop();
                stack.push(charAtI + top2 + top1);
            }

            i++;
        }

        return stack.pop();
    }

    // TC: O(n), SC: O(n)
    static String prefixToPostfix(String s) {

        Stack<String> stack = new Stack<>();

        for (int i = s.length() - 1; i >= 0; i--) {
            char charAtI = s.charAt(i);

            if((charAtI >= 'A' && charAtI <= 'Z') ||
               (charAtI >= 'a' && charAtI <= 'z') ||
               (charAtI >= '0' && charAtI <= '9')) {
                stack.push(String.valueOf(charAtI));
            }
            else {
                String top1 = stack.pop();
                String top2 = stack.pop();
                stack.push(top1 + top2 + charAtI);
            }
        }

        return stack.pop();
    }

    static int priority(char op) {
        switch (op) {
            case '^': return 3;
            case '*':
            case '/':
            case '%': return 2;
            case '+':
            case '-': return 1;
            default: return -1;
        }
    }

    static boolean isRightAssociative(char op) {
        return op == '^';
    }
}
