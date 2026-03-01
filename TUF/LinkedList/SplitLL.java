public class SplitLL {
    
    public static LLNode[] splitList(LLNode head, int k) {

        if(head == null) return new LLNode[k];   // Should return null values

        LLNode[] result = new LLNode[k];

        LLNode temp = head;
        int counter = 0;
        while(temp != null) {
            counter++;
            temp = temp.next;
        }

        int noOfElements = counter / k;
        int remaining = counter % k;

        temp = head;
        for(int i=0; i<k; i++) {

            result[i] = temp;

            int size = noOfElements + (remaining-- > 0 ? 1 : 0);

            for(int j=1; j < size && temp != null; j++) {

                temp = temp.next;
            }

            if(temp != null) {

                LLNode next = temp.next;
                temp.next = null;
                temp = next;
            }
        }

        return result;
    }

    public static LLNode[] splitList1(LLNode head, int k) {     // Same code using While Loop
        
        if (head == null) return new LLNode[k];

        LLNode[] result = new LLNode[k];

        LLNode temp = head;
        int counter = 0;
        while (temp != null) {
            counter++;
            temp = temp.next;
        }

        int noOfElements = counter / k;
        int remaining = counter % k;

        temp = head;
        int i = 0;
        while (i < k) {

            result[i] = temp;

            int size = noOfElements + (remaining-- > 0 ? 1 : 0);
            int j = 1;
            while (j < size && temp != null) {
                temp = temp.next;
                j++;
            }

            if (temp != null) {
                LLNode next = temp.next;
                temp.next = null;
                temp = next;
            }

            i++;
        }

        return result;
    }

}
