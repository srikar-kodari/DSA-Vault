import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ImpStackUsingQueue {

    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    void push(int x) {
        q2.offer(x);

        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }

        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    int pop() {
        if (q1.isEmpty()) {
            System.out.println("Empty Stack..");
            return -1;
        }
        return q1.poll();
    }

    int peek() {
        if (q1.isEmpty()) {
            System.out.println("Empty Stack..");
            return -1;
        }
        return q1.peek();
    }

    int size() {
        return q1.size();
    }

    public static void main(String[] args) {
        ImpStackUsingQueue stack = new ImpStackUsingQueue();

        System.out.println("Stack Using Queue:");
        System.out.println(stack.pop());
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println(stack.peek());
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        ImpQueueUsingStack queue = new ImpQueueUsingStack();

        System.out.println("Queue Using Stack:");
        System.out.println(queue.pop());
        queue.push(10);
        queue.push(20);
        queue.push(30);
        System.out.println(queue.peek());
        System.out.println(queue.size());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }
}

class ImpQueueUsingStack {

    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    void push(int x) {
        s1.push(x);
    }

    int pop() {
        if (s1.isEmpty() && s2.isEmpty()) {
            System.out.println("Empty Queue..");
            return -1;
        }

        moveIfNeeded();
        return s2.pop();
    }

    int peek() {
        if (s1.isEmpty() && s2.isEmpty()) {
            System.out.println("Empty Queue..");
            return -1;
        }

        moveIfNeeded();
        return s2.peek();
    }

    int size() {
        return s1.size() + s2.size();
    }

    void moveIfNeeded() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
    }
}
