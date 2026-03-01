public class MiddleOfLL {
    public static LLNode middleOfLL(LLNode head) {  // Two Traversals

        if(head == null || head.next == null) return head;

        LLNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        temp = head;
        int cnt = 0;
        while (temp != null) {
            cnt++;
            if(cnt == count/2) {
                break;
            }
            temp = temp.next;
        }
        
        return temp.next;
    }

    public static LLNode middleOfLL2(LLNode head) {     // Tortoise & Hare Approach - Slow & Fast Pointers
        
        if(head == null || head.next == null) return head;

        LLNode a = head;
        LLNode b = head;

        while(b != null && b.next != null) {

            a = a.next;
            b = b.next.next;
        }

        return a;
    }

}
