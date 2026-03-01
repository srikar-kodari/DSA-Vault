import java.util.HashMap;
import java.util.Map;

public class HasCycleLL {
    
    public static boolean hasCycle(LLNode head) {

        if(head == null || head.next == null) return false;

        Map<LLNode, Integer> map = new HashMap<>();     // Map or Set for O(1) TC.

        LLNode temp = head;
        while(temp != null) {
            if(map.containsKey(temp)) return true;

            else {
                map.put(temp, 1);
                temp = temp.next;
            }
        }
        return false;
    }

    public static boolean hasCycle2(LLNode head) {  // Tortoise & Hare Approach (Fast & Slow Pointers)

        if(head == null || head.next == null) return false;

        LLNode fast = head; // The 2 pointers will definitely meet if there is a cycle.
        LLNode slow = head;

        while(fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if(fast == slow) return true;
        }

        return false;
    }


    public static LLNode cycleBeginPoint(LLNode head) {     // Starting Node in a Loop of a LL

        if(head == null || head.next == null) return null;

        Map<LLNode, Integer> map = new HashMap<>();     // Map or Set for O(1) TC.

        LLNode temp = head;
        while(temp != null) {
            if(map.containsKey(temp)) return temp;

            else {
                map.put(temp, 1);
                temp = temp.next;
            }
        }

        return null;
    }

    public static LLNode cycleBeginPoint2(LLNode head) {     // Starting Node in a Loop of a LL

        if(head == null || head.next == null) return null;   // Tortoise & Hare Approach | Floyd's Cycle Detection algorithm

        LLNode fast = head; // Refer FloydsCycleDetection.md File for Algorithm Intuition
        LLNode slow = head;

        while(fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if(fast == slow) {      // Step 2: After finding that a loop exists

                slow = head;
                while(slow != fast) {

                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;    // Can return fast also, as slow == fast
            }
        }

        return null;
    }


    public static LLNode convertToCycle(LLNode head, int pos) {

        if(pos == -1) return head;

        LLNode cycle = null;
        LLNode temp = head;
        int count = 0;

        // Step 1: find node at position pos
        while (temp != null) {

            if(count == pos) cycle = temp;

            if(temp.next == null) break;    // tail found

            temp = temp.next;
            count++;
        }

        // If pos was invalid, do nothing
        if(cycle == null) return head;

        // Step 2: connect tail to cycleNode
        temp.next = cycle;
        return head;
    }
    
}
