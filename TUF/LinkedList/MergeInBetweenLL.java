public class MergeInBetweenLL {
    
    public static LLNode mergeInBetweenLL(LLNode list1, int a, int b, LLNode list2) {
        // TC: O(n + m) | Traversals: list1 ~3x, list2 ~1x | SC: O(1)

        LLNode dummy = list1;   // My Code. It's Not Good
        int cnt = 0;
        while(dummy != null) {

            if(cnt == b) {
                dummy = dummy.next;
                break;
            }

            cnt++;
            dummy = dummy.next;
        }

        LLNode temp = list1;
        int count = 1;
        while(temp != null) {

            if(count == a) {
                temp.next = list2;
                break;
            }

            count++;
            temp = temp.next;
        }

        LLNode temp1 = list1;
        while (temp1 != null) {
            
            if(temp1.next == null) {
                temp1.next = dummy;
                break;
            }

            temp1 = temp1.next;
        }

        return list1;
    }

    public static LLNode mergeInBetweenLL2(LLNode list1, int a, int b, LLNode list2) {  // GPT
        // TC: O(n + m) | Traversals: list1 ~1x, list2 ~1x | SC: O(1)
        // 0-based: remove nodes in [a, b] from list1 and insert list2

        LLNode prevA = null;
        LLNode afterB = null;
        LLNode curr = list1;
        int index = 0;

        while(curr != null) {
            if(index == a - 1) {
                prevA = curr;
            }
            if(index == b) {
                afterB = curr.next;
                break;
            }
            curr = curr.next;
            index++;
        }

        LLNode tail2 = list2;
        while(tail2.next != null) {
            tail2 = tail2.next;
        }

        prevA.next = list2;
        tail2.next = afterB;

        return list1;
    }

    public static LLNode mergeInBetweenLLOptimized(LLNode list1, int a, int b, LLNode list2) {  // GPT
        // TC: O(n + m) | Traversals: list1 ~2x (bounded), list2 ~1x | SC: O(1)
        // Constraints: 3 <= list1.length <= 10^4, 1 <= a <= b < list1.length - 1, list2.length >= 1

        LLNode prevA = list1;
        for(int i = 0; i < a - 1; i++) {
            prevA = prevA.next;
        }

        LLNode curr = prevA.next;
        for(int i = a; i < b; i++) {
            curr = curr.next;
        }
        LLNode afterB = curr.next;

        LLNode tail2 = list2;
        while(tail2.next != null) {
            tail2 = tail2.next;
        }

        prevA.next = list2;
        tail2.next = afterB;

        return list1;
    }

}
