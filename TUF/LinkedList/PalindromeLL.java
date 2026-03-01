import java.util.Stack;

public class PalindromeLL {

    public static boolean isPalindromeLL(LLNode head) {

        if(head.next == null) return true;

        Stack<Integer> stack = new Stack<>();

        LLNode temp = head;
        while(temp != null) {
            stack.push(temp.data);
            temp = temp.next;
        }

        temp = head;
        while (temp != null) {
            int check = stack.pop();

            if(temp.data != check) return false;
            temp = temp.next;
        }

        return true;
    }

}