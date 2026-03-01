import java.util.HashSet;
import java.util.Set;

public class IntersectionNode {
    
    public static LLNode getIntersectionNode(LLNode headA, LLNode headB) {

        Set<LLNode> set1 = new HashSet<>();

        LLNode temp1 = headA;
        while (temp1 != null) {
            set1.add(temp1);
            temp1 = temp1.next;
        }

        LLNode temp2 = headB;
        while(temp2 != null) {

            if(set1.contains(temp2)) return temp2;
            temp2 = temp2.next;
        }

        return null;
    }

}
