import java.util.Stack;

public class StackReverse {
    
    public static Stack<Integer> stackReverse(Stack<Integer> stack) {

        if(stack.isEmpty()) return stack;

        int x = stack.pop();

        stackReverse(stack);

        insertAtBottom(x, stack);

        return stack;
    }

    private static void insertAtBottom(int element, Stack<Integer> stack) {

        if(stack.isEmpty()) {
            stack.push(element);
            return;
        }

        int temp = stack.pop();

        insertAtBottom(element, stack);

        stack.push(temp);
    }

}
