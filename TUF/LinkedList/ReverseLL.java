import java.util.Stack;

public class ReverseLL {
    
    public static LLNode reverseLL(LLNode head) {   // Iterative Approach   // TC: O(2N), SC: O(N)

        if(head == null || head.next == null) return head;

        Stack<Integer> stack = new Stack<>();

        LLNode temp = head;
        while (temp != null) {

            stack.push(temp.data);
            temp = temp.next;
        }

        temp = head;
        while (temp != null) {
            temp.data = stack.pop();
            temp = temp.next;
        }

        return head;
    }

    public static LLNode reverseLL2(LLNode head) {  // Reversing The Links. TC: O(N), SC: O(1)

        if(head == null || head.next == null) return head;

        LLNode prev = null;
        LLNode front = null;
        LLNode temp = head;
        while (temp != null) {
            front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }

        return prev;
    }

    public static LLNode recursiveReverseLL(LLNode head) {  // Recursive Approach. TC: O(N), SC: O(N)

        if(head == null || head.next == null) return head;

        LLNode newHead = recursiveReverseLL(head.next);
        LLNode front = head.next;
        front.next = head;
        head.next = null;

        return newHead;
    }

    // HARD PROBLEM | SHOULD REVISE AGAIN
    public static LLNode reverseGroupK(LLNode head, int k) {    // Constraint: K <= Length of LL

        if(head == null || head.next == null || k == 1) return head;

        LLNode temp = head;
        LLNode prev = null;
        while(temp != null) {
            LLNode kthNode = findKthNode(temp, k);

            if(kthNode == null) {

                if(prev != null) prev.next = temp;
                break;
            }

            LLNode nextNode = kthNode.next;
            kthNode.next = null;

            recursiveReverseLL(temp);

            if(temp == head) {
                head = kthNode;
            }
            else {
                prev.next = kthNode;
            }

            prev = temp;
            temp = nextNode;
        }
        return head;
    }

    public static LLNode findKthNode(LLNode temp, int k) {

        k -= 1;
        while (temp != null && k > 0) {
            temp = temp.next;
            k--;
        }

        return temp;
    }


}
