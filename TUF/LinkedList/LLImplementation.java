public class LLImplementation {
    public static void main(String[] args) {

        // // Basic Code
        // int[] arr = {2,10,6,4};
        // LLNode n4 = new LLNode(arr[3]);
        // LLNode n3 = new LLNode(arr[2], n4);
        // LLNode n2 = new LLNode(arr[1], n3);
        // LLNode n1 = new LLNode(arr[0], n2);
        // System.out.println(n1);
        // System.out.println(n2);
        // System.out.println(n3);
        // System.out.println(n4);


        // // Convert Array To LinkedList
        // int[] arr = {4,8,6,2};
        // LLNode head = convertArr2LL(arr);
        // System.out.println("Head value is: " + head);


        // // Traverse LinkedList  // TC: O(n)
        // int[] arr = {4,8,6,2};
        // LLNode head = convertArr2LL(arr);
        // LLNode temp = head;
        // int length = 0; // Length of LL
        // while(temp != null) {
        //     System.out.print(temp.data + " ");
        //     temp = temp.next;
        //     length++;
        // }
        // System.out.println();
        // System.out.println("Length of LL is: " + length);   // TC: O(n)


        // // Search for element in LL
        // int[] arr = {4,8,6,2};
        // LLNode head = convertArr2LL(arr);
        // System.out.println("Is element present in LL: " + checkIfPresent(head, 4));


        // Delete Operations

        // //Delete Head
        // int[] arr = {4,8,6,2,10};
        // LLNode head = convertArr2LL(arr);
        // LLNode headDelete = deleteHead(head);
        // System.out.print("New LL after deleting head is: ");
        // traverseLL(headDelete);

        // // Delete Tail
        // int[] arr = {4,8,6,2,10};
        // //int[] arr = {1,2};
        // LLNode head = convertArr2LL(arr);
        // LLNode tailDelete = deleteTail(head);
        // System.out.print("New LL after deleting tail is: ");
        // traverseLL(tailDelete);

        // // Delete Kth Node
        // int[] arr = {4,8,6,2,10};
        // LLNode head = convertArr2LL(arr);
        // LLNode deleteK = deleteKthNode(head, 3);
        // System.out.println("New LL after deleting Kth node is: ");
        // traverseLL(deleteK);

        // // Delete Target
        // int[] arr = {4,8,6,2,10};
        // LLNode head = convertArr2LL(arr);
        // LLNode targetDelete = deleteTarget(head, 2);
        // System.out.println("New LL after deleting target is: ");
        // traverseLL(targetDelete);


        // Insert Operations

        // // Insert at Head
        // int[] arr = {4,8,6,2,10};
        // //int[] arr = {};
        // LLNode head = convertArr2LL(arr);
        // LLNode newHead = insertHead(head, 24);
        // System.out.println("New LL after inserting value at head is: ");
        // traverseLL(newHead);

        // // Insert at Head - Simple Code
        // int[] arr = {4,8,6,2,10};
        // LLNode head = convertArr2LL(arr);
        // head = new LLNode(24, head);    // Simply modify the head value
        // System.out.println("New LL after inserting value at head is: ");
        // traverseLL(head);

        // // Insert at Tail
        // int[] arr = {4,8,6,2,10};
        // LLNode head = convertArr2LL(arr);
        // head = insertTail(head, 24);
        // System.out.println("New LL after inserting value at tail is: ");
        // traverseLL(head);

        // // Insert at Kth position
        // int[] arr = {4,8,6,2,10};
        // LLNode head = convertArr2LL(arr);
        // head = insertKthNode(head, 7, 99);
        // System.err.println("New LL after inserting value at Kth position is: ");
        // traverseLL(head);

        // Insert element before X
        int[] arr = {4,8,6,2,10};
        LLNode head = convertArr2LL(arr);
        head = insertBexforeX(head, 4, 24);
        System.out.println("New LL after inserting element before X is: ");
        traverseLL(head);



        
    }

    public static LLNode convertArr2LL(int[] arr) {    // Convert Array To LinkedList

        if(arr.length == 0) return null;

        LLNode head = new LLNode(arr[0]);
        LLNode mover = head;

        for(int i=1; i<arr.length; i++) {
            LLNode temp = new LLNode(arr[i]);
            mover.next = temp;
            mover = temp;
        }
        // System.out.println(head);
        // System.out.println(mover);  // Tail after complete iteration
        return head;
    }

    public static void traverseLL(LLNode head) {

        LLNode temp = head;
        
        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();

    }

    public static String llToString(LLNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        LLNode temp = head;
        while (temp != null) {
            sb.append(temp.data);
            temp = temp.next;
            if (temp != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static int checkIfPresent(LLNode head, int target) {    // TC: O(n)
        LLNode temp = head;
        while (temp != null) {
            if(temp.data == target) return 1;
            temp = temp.next;
        }
        return -1;
    }


    // Delete Elements

    private static LLNode deleteHead(LLNode head) { // TC: O(1)
        if(head == null) return head;

        head = head.next;
        return head;
    }

    private static LLNode deleteTail(LLNode head) {     // TC: O(n)
        if(head == null || head.next == null) return null;

        LLNode temp = head;
        while(temp.next.next != null) {

            temp = temp.next;
        }
        temp.next = null;

        return head;
    }

    private static LLNode deleteKthNode(LLNode head, int k) {   // Delete element at Kth position

        if(head == null) return null;   // TC: O(k)

        if(k == 1) {
            LLNode temp = head;
            head = head.next;

            return head;
        }

        int count = 0;
        LLNode temp = head;
        LLNode prev = null;
        while(temp != null) {

            count++;
            if(count == k) {

                prev.next = prev.next.next;
                break;
            }
            prev = temp;
            temp = temp.next;
        }

        return head;
    }

    private static LLNode deleteTarget(LLNode head, int target) {

        if(head == null) return head;   // || return null;

        if(head.data == target) {   // This if-block is not needed
            LLNode temp = head;
            head = head.next;
            return head;
        }

        LLNode temp = head;
        LLNode prev = null;
        while (temp != null) {

            if(temp.data == target) {
                prev.next = prev.next.next;
                break;
            }
            prev = temp;
            temp = temp.next;
        }

        return head;
    }


    // Insert Elements

    private static LLNode insertHead(LLNode head, int value) {

        if(head == null) return new LLNode(value);

        LLNode newHead = new LLNode(value, head);
        head = newHead;

        return head;
    }

    private static LLNode insertTail(LLNode head, int value) {

        if(head == null) return new LLNode(value);

        LLNode temp = head;
        while(temp.next != null) {

            temp = temp.next;
        }
        temp.next = new LLNode(value);

        return head;
    }

    private static LLNode insertKthNode(LLNode head, int k, int value) {   // Constraint: k -> (1, n+1)

        if(head == null) {
            if(k == 1) return new LLNode(value);
            else {
                System.out.println("GIVE CORRECT INPUT..");
                return head;
            }
        }

        else if(k == 1) {
            LLNode temp = new LLNode(value, head);

            return temp;
        }

        int count = 0;
        LLNode temp = head;
        while(temp != null) {

            count++;
            if(count == k-1) {
                
                LLNode element = new LLNode(value);
                
                element.next = temp.next;
                temp.next = element;

                break;
            }
            temp = temp.next;
        }

        return head;
    }

    private static LLNode insertBexforeX(LLNode head, int x, int element) {   // Insert element before X

        if(head == null) return null;

        if(head.data == x) {
            LLNode temp = new LLNode(element, head);
            return temp;
        }

        LLNode temp = head;
        while(temp.next != null) {

            if(temp.next.data == x) {

                LLNode result = new LLNode(element, temp.next);
                temp.next = result;
                break;
            }
            temp = temp.next;
        }

        return head;
    }

}


class LLNode {

    int data;
    LLNode next;

    LLNode(int data1, LLNode next1) {
        this.data = data1;
        this.next = next1;
    }
    LLNode(int data1) {
        this.data = data1;
        this.next = null;
    }

    @Override
    public String toString() {

        String s = "Data is: " + data + ", Next is: " + (next != null ? next.data : "null");
        
        return s;
    }
    
}
