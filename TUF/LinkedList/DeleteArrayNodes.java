import java.util.HashSet;
import java.util.Set;

public class DeleteArrayNodes {

    public static LLNode deleteArray(LLNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        LLNode dummy = new LLNode(0);
        LLNode tail = dummy;
        LLNode temp = head;
        while (temp != null) {
            if (!set.contains(temp.data)) {
                tail.next = temp;
                tail = tail.next;
            }
            temp = temp.next;
        }

        if (tail != null) {
            tail.next = null;
        }

        return dummy.next;
    }

}
