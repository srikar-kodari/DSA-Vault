public class ImpStackLL {

    ImpNodeLL top = null;
    int size = 0;

    void push(int x) {

        ImpNodeLL temp = new ImpNodeLL(x);
        temp.next = top;
        top = temp;
        size++;
    }

    int pop() {

        if(top == null) {
            System.out.println("Empty Stack..");
            return -1;
        }

        ImpNodeLL temp = top;
        top = top.next;

        int popEle = temp.data;
        size--;
        return popEle;
    }

    int peek() {
        if(top == null) {
            System.out.println("Empty Stack..");
            return -1;
        }
        return top.data;
    }

    int size() {
        return size;
    }

    public static void main(String[] args) {
        ImpStackLL stack = new ImpStackLL();

        System.out.println(stack.pop());
        System.out.println(stack.peek());

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println(stack.peek());
        System.out.println(stack.size());

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.size());

        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
    
}
