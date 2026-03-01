public class RemoveNthNode {
    
    public static LLNode removeNthFromEnd(LLNode head, int n) {

        LLNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        int k = count - n;

        if(k == 0) return head.next;

        int cnt = 0;
        temp = head;
        while (temp != null) {
            cnt++;
            if(cnt == k) {
                temp.next = temp.next.next;
                return head;
            }
            temp = temp.next;
        }

        return head;
    }

    public static LLNode removeNthFromEnd1(LLNode head, int n) {    // GPT

        LLNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        int k = count - n;
        LLNode dummy = new LLNode(0);
        dummy.next = head;
        temp = dummy;
        for (int i = 0; i < k; i++) {
            if (temp == null) {
                return head;
            }
            temp = temp.next;
        }

        if (temp != null && temp.next != null) {
            LLNode toDelete = temp.next;
            temp.next = toDelete.next;
            toDelete.next = null;
        }

        return dummy.next;
    }

    public static LLNode removeNthFromEnd2(LLNode head, int n) {    // two-pointer

        LLNode dummy = new LLNode(0);
        dummy.next = head;
        LLNode fast = dummy;
        LLNode slow = dummy;

        // Move fast n steps ahead so the gap between fast and slow is n.
        for (int i = 0; i < n; i++) {
            if (fast.next == null) {
                return head;
            }
            fast = fast.next;
        }

        // Advance both pointers; slow will stop just before the target node.
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        LLNode toDelete = slow.next;
        if (toDelete != null) {
            slow.next = toDelete.next;
            toDelete.next = null;
        }

        return dummy.next;
    }
}
