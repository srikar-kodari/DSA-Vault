public class MergeSortedLists {
    
    public static LLNode mergeTwoLists(LLNode list1, LLNode list2) {

        if(list1 == null) return list2;
        else if(list2 == null) return list1;

        LLNode temp1 = list1;
        LLNode temp2 = list2;

        LLNode dummy = new LLNode(0);
        LLNode tail = dummy;

        while(temp1 != null && temp2 != null) {

            if(temp1.data > temp2.data) {

                tail.next = new LLNode(temp2.data); // We can directly assign temp2. tail.next = temp2
                tail = tail.next;
                temp2 = temp2.next;
            }
            else if(temp1.data < temp2.data) {

                tail.next = new LLNode(temp1.data); // We can directly assign temp1. tail.next = temp1
                tail = tail.next;
                temp1 = temp1.next;
            }
            else {      // We can add the else condition in above if-block only

                tail.next = new LLNode(temp1.data);
                tail.next.next = new LLNode(temp2.data);
                tail = tail.next.next;

                temp1 = temp1.next;
                temp2 = temp2.next;
            }

        }

        // if(temp1 == null && temp2 != null) {
        //     while (temp2 != null) {
        //         tail.next = new LLNode(temp2.data);
        //         tail = tail.next;
        //         temp2 = temp2.next;
        //     }
        // }
        // else if(temp1 != null && temp2 == null) {
        //     while (temp1 != null) {
        //         tail.next = new LLNode(temp1.data);
        //         tail = tail.next;
        //         temp1 = temp1.next;
        //     }
        // }

        if(temp1 == null) tail.next = temp2;
        else tail.next = temp1;

        return dummy.next;
    }

    public static LLNode mergeTwoLists1(LLNode list1, LLNode list2) {   // Simple & Optimised Code

        if(list1 == null) return list2;
        if(list2 == null) return list1;

        LLNode dummy = new LLNode(0);
        LLNode tail = dummy;

        LLNode temp1 = list1;
        LLNode temp2 = list2;

        while(temp1 != null && temp2 != null) {

            if(temp1.data <= temp2.data) {
                tail.next = temp1;
                temp1 = temp1.next;
            }
            else {
                tail.next = temp2;
                temp2 = temp2.next;
            }
            tail = tail.next;
        }

        tail.next = (temp1 != null) ? temp1 : temp2;

        return dummy.next;
    }

}
