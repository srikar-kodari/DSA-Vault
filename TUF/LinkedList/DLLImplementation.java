import java.util.Stack;

public class DLLImplementation {
    public static void main(String[] args) {

        // // Traversal
        // int[] arr = {1,2,3,4};
        // DLLNode head = convertArr2DLL(arr);
        // System.out.println("Forward Iteration is: ");
        // forwardTraversal(head);
        // System.out.println("Backward Traversal is: ");
        // backwardTraversal(head);    // Change return type to back in convertArr2DLL for backward iteration


        // Delete Operations

        // // Delete Head
        // int[] arr = {1,2,3,4};
        // DLLNode head = convertArr2DLL(arr);
        // head = deleteHead(head);
        // System.out.println("Forward Iteration After Deleting Head is: ");
        // forwardTraversal(head);

        // // Delete Tail
        // int[] arr = {1,2,3,4};
        // DLLNode head = convertArr2DLL(arr);
        // head = deleteTail(head);
        // System.out.println("Traversal after deleting tail is: ");
        // forwardTraversal(head);

        // // Delete Kth Node
        // int[] arr = {2,6,4,8,1,7};
        // DLLNode head = convertArr2DLL(arr);
        // head = deleteKthNode(head, 2);
        // System.out.println("Traversal after deleting Kth node is: ");
        // forwardTraversal(head);

        // // Delete Target
        // int[] arr = {2,4,6,1,9,7};
        // DLLNode head = convertArr2DLL(arr);
        // deleteTarget(head.next);    // Constraint: Target != Head   // Directly give a node as input
        // System.out.println("Traversal after deleting Target Node is: ");
        // forwardTraversal(head);


        // Insert Operations

        // Insert Before

        // // Insert Before Head
        // int[] arr = {2,4,6,1,9,7};
        // DLLNode head = convertArr2DLL(arr);
        // head = iBeforeHead(head, 24);
        // System.err.println("Traversal after inserting value before head is: ");
        // forwardTraversal(head);

        // // Insert Before Tail
        // int[] arr = {2,4,6,1,9,7};
        // DLLNode head = convertArr2DLL(arr);
        // head = iBeforeTail(head, 50);
        // System.out.println("Traversal after inserting value before tail is: ");
        // forwardTraversal(head);

        // // Insert Before Kth Node
        // int[] arr = {2,4,6,1,9,7};
        // DLLNode head = convertArr2DLL(arr);
        // head = iBeforeK(head, 25, 6);
        // System.out.println("Traversal after inserting value before Kth element is: ");
        // forwardTraversal(head);

        // // Insert Before Target Node
        // int[] arr = {2,4,6,1,9,7};
        // DLLNode head = convertArr2DLL(arr);
        // iBeforeNode(head.next.next, 24);     // Constraint Target Node != Head
        // System.out.println("Traversal after inserting value before target node is: ");
        // forwardTraversal(head);

        // Insert After

        // // Insert After Head
        // int[] arr = {2,1,6,8,5};
        // DLLNode head = convertArr2DLL(arr);
        // head = iAfterHead(head, 24);
        // System.err.println("Traversal after inserting value after head is: ");
        // forwardTraversal(head);

        // // Insert After Tail
        // int[] arr = {2,1,6,8,5};
        // DLLNode head = convertArr2DLL(arr);
        // head = iAfterTail(head, 24);
        // System.err.println("Traversal after inserting value after tail is: ");
        // forwardTraversal(head);

        // // Insert After Kth Position
        // int[] arr = {2,1,6,8,5};
        // DLLNode head = convertArr2DLL(arr);
        // head = iAfterK(head, 24, 4);
        // System.err.println("Traversal after inserting value after Kth node is: ");
        // forwardTraversal(head);

        // // Insert After Target Node
        // int[] arr = {2,1,6,8,5};
        // DLLNode head = convertArr2DLL(arr);
        // iAfterNode(head.next, 24);
        // System.err.println("Traversal after inserting value after target node is: ");
        // forwardTraversal(head);


        // Reverse DLL

        int[] arr = {2,1,6,8,5};
        DLLNode head = convertArr2DLL(arr);
        // head = reverseDLL(head);     // Brute
        head = reverseDLL2(head);       // Optimal
        System.err.println("Traversal after reversing DLL is: ");
        forwardTraversal(head);



        
    }

    private static DLLNode convertArr2DLL(int[] arr) {    // Convert Array To DLL

        if(arr.length == 0) return null;

        DLLNode head = new DLLNode(arr[0]);
        DLLNode back = head;
        for(int i=1; i<arr.length; i++) {

            DLLNode temp = new DLLNode(arr[i], null, back);

            back.next = temp;
            back = temp;
        }
        
        return head;
    }

    private static void forwardTraversal(DLLNode head) {    // Forward Iteration

        DLLNode temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    private static void backwardTraversal(DLLNode head) {   // Backward Iteration

        DLLNode tail = head;

        while (tail.next != null) {      // Finding out Tail
            tail = tail.next;
        }
        while(tail != null) {       // Backward iteration from Tail
            System.out.print(tail.data + " ");
            tail = tail.back;
        }
        System.out.println();
    }


    // Delete Elements

    private static DLLNode deleteHead(DLLNode head) {

        if(head == null || head.next == null) return null;

        DLLNode prev = head;
        head = head.next;
        
        head.back = null;
        prev.next = null;

        return head;
    }

    private static DLLNode deleteTail(DLLNode head) {

        if(head == null || head.next == null) return null;

        DLLNode tail = head;
        while(tail.next != null) {
            tail = tail.next;
        }
        DLLNode newTail = tail.back;
        newTail.next = null;
        tail.back = null;

        return head;
    }

    private static DLLNode deleteKthNode(DLLNode head, int k) {     // Constraint: 1 <= K <= N. N = Length of List

        if(head == null) {
            System.out.println("Empty List is given as input..");
            return null;
        }

        int count = 0;
        DLLNode temp = head;
        while(temp != null) {

            count++;
            if(count == k) break;
            temp = temp.next;
        }

        if(temp == null) {      // Edge Case: When incorrect K is given, temp value becomes null
            System.out.println("Enter Correct K..");
            return null;
        }

        DLLNode prev = temp.back;    // Added these for Clarity
        DLLNode front = temp.next;

        if(prev == null && front == null) return null;      // Only 1 element in List
        else if(prev == null) return deleteHead(head);      // 1st Element of List
        else if(front == null) return deleteTail(head);     // Last Element of List
        else {
            prev.next = front;
            front.back = prev;

            temp.back = temp.next = null;
        }

        return head;
    }

    private static void deleteTarget(DLLNode target) {  // Constraint: Target != Head

        DLLNode prev = target.back;    // Added these for Clarity
        DLLNode front = target.next;

        if(front == null) {
            prev.next = null;
            target.back = null;

            return;
        }

        prev.next = front;
        front.back = prev;

        target.back = target.next = null;
    }


    // Insert Elements

    // Insert Before

    private static DLLNode iBeforeHead(DLLNode head, int value) {

        if(head == null) return new DLLNode(value);

        DLLNode newHead = new DLLNode(value, head, null);
        head.back = newHead;

        return newHead;
    }

    private static DLLNode iBeforeTail(DLLNode head, int value) {

        if(head == null) return new DLLNode(value);

        DLLNode tail = head;
        while(tail.next != null) {
            tail = tail.next;
        }

        DLLNode prev = tail.back;

        DLLNode newNode = new DLLNode(value, tail, prev);

        prev.next = newNode;
        tail.back = newNode;

        return head;
    }

    private static DLLNode iBeforeK(DLLNode head, int value, int k) {

        if(k == 1) return iBeforeHead(head, value);

        DLLNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            if(count == k) break;
            temp = temp.next;
        }

        DLLNode prev = temp.back;
        DLLNode newNode = new DLLNode(value, temp, prev);

        prev.next = newNode;
        temp.back = newNode;

        return head;
    }

    private static void iBeforeNode(DLLNode target, int value) {    // Constraint: Target Node != Head

        DLLNode prev = target.back;

        DLLNode newNode = new DLLNode(value, target, prev);

        prev.next = newNode;
        target.back = newNode;
    }

    // Insert After

    private static DLLNode iAfterHead(DLLNode head, int value) {

        if(head.next == null) {
            DLLNode newNode = new DLLNode(value, null, head);
            head.next = newNode;
            return head;
        }

        DLLNode newNode = new DLLNode(value, head.next, head);
        head.next.back = newNode;
        head.next = newNode;

        return head;
    }

    private static DLLNode iAfterTail(DLLNode head, int value) {

        if(head.next == null) {
            DLLNode newNode = new DLLNode(value, null, head);
            head.next = newNode;
            return head;
        }

        DLLNode tail = head;
        while(tail.next != null) {
            tail = tail.next;
        }

        DLLNode newNode = new DLLNode(value, null, tail);
        tail.next = newNode;

        return head;
    }

    private static DLLNode iAfterK(DLLNode head, int value, int k) {

        DLLNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            if(count == k) break;
            temp = temp.next;
        }

        if(temp.next == null) return iAfterTail(head, value);

        DLLNode newNode = new DLLNode(value, temp.next, temp);
        temp.next.back = newNode;
        temp.next = newNode;

        return head;
    }

    private static void iAfterNode(DLLNode target, int value) {

        if(target.next == null) {
            DLLNode newNode = new DLLNode(value, null, target);
            target.next = newNode;
            return;
        }

        DLLNode newNode = new DLLNode(value, target.next, target);
        target.next.back = newNode;
        target.next = newNode;
    }


    // Reverse DLL

    private static DLLNode reverseDLL(DLLNode head) {   // Brute Force. TC: O(2N)

        if(head.next == null) return head;

        Stack<Integer> stack = new Stack<>();       // SC: O(N)

        DLLNode temp = head;
        while(temp != null) {   // TC: (N)
            stack.push(temp.data);
            temp = temp.next;
        }

        temp = head;
        while (temp != null) {  // TC: O(N)
            temp.data = stack.pop();
            temp = temp.next;
        }

        return head;
    }

    private static DLLNode reverseDLL2(DLLNode head) {  // Reverse The Links Of DLL. TC: O(N) & SC: O(1)

        if(head == null || head.next == null) return head;

        DLLNode last = null;
        DLLNode current = head;
        while(current != null) {

            last = current.back;            // Swap Links
            current.back = current.next;
            current.next = last;

            current = current.back;
        }

        head = last.back;
        return head;
    }


}


class DLLNode {

    int data;
    DLLNode next;
    DLLNode back;

    DLLNode(int data1, DLLNode next1, DLLNode back1) {
        this.data = data1;
        this.next = next1;
        this.back = back1;
    }
    DLLNode(int data1) {
        this.data = data1;
        this.next = null;
        this.back = null;
    }

    @Override
    public String toString() {

        String s = "Data is: " + data + ", Next is: " + (next != null ? next.data : "null") + ", Previous is: " + (back != null ? back.data : "null" );
        
        return s;
    }
    
}
