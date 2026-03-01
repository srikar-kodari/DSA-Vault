import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortLL {
    
    public static LLNode sortList(LLNode head) {

        if(head == null || head.next == null) return head;

        List<Integer> list = new ArrayList<>();

        LLNode temp = head;
        while(temp != null) {
            list.add(temp.data);
            temp = temp.next;
        }

        Collections.sort(list);
        temp = head;
        int i = 0;
        while(temp != null) {
            temp.data = list.get(i);
            temp = temp.next;
            i++;
        }

        return head;
    }

}
