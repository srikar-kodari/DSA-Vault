import java.util.Stack;

public class StackSort {
    
    // TC: O(n^2), SC: O(n) (recursion stack)
    public static Stack<Integer> sortStack(Stack<Integer> stack) {

        if(stack.isEmpty()) return stack;

        int x = stack.pop();

        sortStack(stack);

        insertSorted(x, stack);

        return stack;
    }

    // TC: O(n) for a single insert, SC: O(n) (recursion stack)
    public static void insertSorted(int x, Stack<Integer> stack) {

        if(stack.isEmpty() || stack.peek() <= x) {
            stack.push(x);
            return;
        }

        int temp = stack.pop();

        insertSorted(x, stack);

        stack.push(temp);
    }

    
    // TC: O(n^2), SC: O(n) (extra stack)
    public static Stack<Integer> sortStack2(Stack<Integer> stack) {   // GPT

        Stack<Integer> tempStack = new Stack<>();

        while (!stack.isEmpty()) {
            int x = stack.pop();

            while (!tempStack.isEmpty() && tempStack.peek() > x) {
                stack.push(tempStack.pop());
            }

            tempStack.push(x);
        }

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

        return stack;
    }

}
