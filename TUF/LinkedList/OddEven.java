import java.util.ArrayList;
import java.util.List;

public class OddEven {
    
    public static LLNode oddEvenLL(LLNode head) {

        if(head == null || head.next == null) return head;

        List<Integer> list = new ArrayList<>();

        LLNode temp = head;
        while(temp != null) {
            list.add(temp.data);
            temp = temp.next;
        }

        List<Integer> result = new ArrayList<>();
        int n = list.size();

        for(int i=0; i<n; i+=2) {
            result.add(list.get(i));
        }
        for(int i=1; i<n; i+=2) {
            result.add(list.get(i));
        }

        temp = head;
        int i = 0;
        while(temp != null) {
            temp.data = result.get(i);
            temp = temp.next;
            i++;
        }

        return head;
    }
}
