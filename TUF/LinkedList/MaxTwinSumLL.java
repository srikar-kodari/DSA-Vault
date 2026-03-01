import java.util.LinkedList;
import java.util.List;

public class MaxTwinSumLL {
    
    // TC: O(n), SC: O(n)
    public static int maxTwinSum(LLNode head) {

        List<Integer> list = new LinkedList<>();

        LLNode temp = head;
        while (temp != null) {
            list.add(temp.data);
            temp = temp.next;
        }

        int maxTwin = Integer.MIN_VALUE;

        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            // maxTwin = Math.max(maxTwin, (list.get(i) + list.get(j)));    // Getting TLE because list.get(i) -> O(N)
            maxTwin = Math.max(maxTwin, (list.getFirst() + list.getLast()));
            list.removeFirst();
            list.removeLast();
            i++;
            j--;
        }

        return maxTwin;
    }


    // TC: O(n), SC: O(1)
    public static int maxTwinSumOptimal(LLNode head) {  // GPT
        if (head == null || head.next == null) {
            return 0;
        }

        LLNode slow = head;
        LLNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        LLNode secondHalf = reverse(slow);

        int maxTwin = Integer.MIN_VALUE;
        LLNode p1 = head;
        LLNode p2 = secondHalf;
        while (p2 != null) {
            maxTwin = Math.max(maxTwin, p1.data + p2.data);
            p1 = p1.next;
            p2 = p2.next;
        }

        reverse(secondHalf);
        return maxTwin;
    }

    // TC: O(n), SC: O(1)
    private static LLNode reverse(LLNode head) {
        LLNode prev = null;
        LLNode curr = head;
        while (curr != null) {
            LLNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

}
