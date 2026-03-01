import java.util.Stack;

public class StackReverse1 {

    public static void main(String[] args) {
        
        Stack<Integer> st = new Stack<>();
        st.push(1);
        st.push(2);
        st.push(3);

        System.out.println("Original stack: " + st);
        reverseStack(st);
        System.out.println("Reversed stack: " + st);
    }

    static void reverseStack(Stack<Integer> st) {
        if (st.isEmpty()) return;

        int top = st.pop();
        System.out.println("Popped: " + top);

        reverseStack(st);

        insertAtBottom(st, top);
    }

    static void insertAtBottom(Stack<Integer> st, int val) {
        if (st.isEmpty()) {
            st.push(val);
            System.out.println("Inserted at bottom: " + val);
            return;
        }

        int temp = st.pop();
        insertAtBottom(st, val);
        st.push(temp);
    }

}
