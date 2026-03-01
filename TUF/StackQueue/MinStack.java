import java.util.Stack;

class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    // TC: O(1), SC: O(1)
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    // TC: O(1), SC: O(1)
    public void push(int val) {
        stack.push(val);

        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    // TC: O(1), SC: O(1)
    public void pop() {
        if (stack.pop().equals(minStack.peek())) {
            minStack.pop();
        }
    }

    // TC: O(1), SC: O(1)
    public int top() {
        return stack.peek();
    }

    // TC: O(1), SC: O(1)
    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        
        MinStack obj = new MinStack();
        obj.push(10);
        obj.push(20);
        obj.push(30);
        obj.push(40);
        System.out.println(obj.top());
        obj.pop();
        System.out.println(obj.top());
        System.out.println(obj.getMin());
        System.out.println(obj.stack);
        System.out.println(obj.minStack);
    }
}
