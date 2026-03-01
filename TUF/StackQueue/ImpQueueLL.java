public class ImpQueueLL {
    
    int size = 0;
    ImpNodeLL start = null;
    ImpNodeLL end = null;

    void push(int x) {

        ImpNodeLL temp = new ImpNodeLL(x);

        if(start == null) {
            start = temp;
            end = temp;
            size++;
            return;
        }

        end.next = temp;
        end = temp;
        size++;
    }

    int pop() {

        if(start == null) {
            end = null;
            System.out.println("Empty Queue..");
            return -1;
        }
        ImpNodeLL temp = start;
        start = start.next;
        size--;
        return temp.data;
    }

    int peek() {

        if(start == null) {
            System.out.println("Empty Queue..");
            return -1;
        }
        return start.data;
    }

    int size() {
        return size;
    }

    public static void main(String[] args) {

        ImpQueueLL queue = new ImpQueueLL();

        System.out.println(queue.pop());
        System.out.println(queue.peek());

        queue.push(10);
        queue.push(20);
        queue.push(30);

        System.out.println(queue.peek());
        System.out.println(queue.size());

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.peek());
        System.out.println(queue.size());

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        
    }

}
