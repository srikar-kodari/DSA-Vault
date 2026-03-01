public class MergeZeroNodes {
    
    public static LLNode mergeZeroNodes(LLNode head) {

        LLNode temp = head.next;
        LLNode dummy = new LLNode(0);
        LLNode tail = dummy;
        int sum = 0;

        while (temp != null) {

            if (temp.data == 0) {
                tail.next = new LLNode(sum);
                tail = tail.next;
                sum = 0;
            }
            
            else sum += temp.data;

            temp = temp.next;
        }

        return dummy.next;
    }

}
