import java.util.Arrays;

public class ImpStackArray {

    int top = -1;
    int[] stack = new int[10];  // Max Size = 10

    void push(int x) {

        if(top >= stack.length - 1) {
            System.out.println("Stack Size is Full..");
            return;
        }
        top = top + 1;
        stack[top] = x;
    }

    int pop() {

        if(top == -1) {
            System.out.println("Empty Stack..");
            return -1;
        }

        int temp = stack[top];
        stack[top] = 0;
        top = top - 1;
        return temp;
    }

    int peek() {

        if(top == -1) {
            System.out.println("Empty Stack..");
            return -1;
        }
        return stack[top];
    }

    int size() {

        return top + 1;
    }

    public static void main(String[] args) {
        
        ImpStackArray st = new ImpStackArray();
        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);

        System.out.println(Arrays.toString(st.stack));
        System.out.println(st.peek());
        System.out.println(st.size());

        st.pop();
        System.out.println(Arrays.toString(st.stack));
        System.out.println(st.peek());

    }

}
