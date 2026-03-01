public class InsertGCD {
    
    public static LLNode insertGCDNode(LLNode head) {

        if(head.next == null) return head;

        LLNode temp = head;
        LLNode front = null;
        while (temp.next != null) {

            int gcd = findGCD(temp.data, temp.next.data);

            front = temp.next;
            temp.next = new LLNode(gcd, front);
            temp = front;
        }

        return head;
    }

    public static int findGCD(int a, int b) {

        while(a != 0 && b != 0) {

            if(a > b) a = a % b;
            else b = b % a;
        }

        if(a == 0) return b;
        else return a;
    }

}
