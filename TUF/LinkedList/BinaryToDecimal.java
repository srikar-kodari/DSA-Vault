public class BinaryToDecimal {
    
    public static int getDecimal(LLNode head) {

        StringBuilder sb = new StringBuilder();

        LLNode temp = head;
        while (temp != null) {
            sb.append(temp.data);
            temp = temp.next;
        }

        return Integer.parseInt(sb.toString(), 2);
    }

}
