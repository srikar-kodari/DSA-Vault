public class AddTwoNumbers {
    
    // TC: O(n + m), SC: O(log(n+m)) - converts to long, limited to 19 digits
    public static LLNode addTwoNumbers(LLNode list1, LLNode list2) {    // Not working for large numbers

        LLNode temp = list1;
        long num1 = 0;
        long place = 1;
        while (temp != null) {

            num1 += temp.data * place;
            place *= 10;

            temp = temp.next;
        }

        temp = list2;
        long num2 = 0;
        place = 1;
        while (temp != null) {
            
            num2 += temp.data * place;
            place *= 10;

            temp = temp.next;
        }

        long num = num1 + num2;

        if (num == 0) return new LLNode(0);
        
        LLNode dummy = new LLNode(0);
        temp = dummy;
        while (num > 0) {

            long digit = num % 10;

            temp.next = new LLNode((int) digit);

            num = num / 10;
            temp = temp.next;
        }

        return dummy.next;
    }

    // TC: O(n + m), SC: O(n + m) - uses StringBuilder, limited to 19 digits
    public static LLNode addTwoNumbers1(LLNode list1, LLNode list2) {

        StringBuilder s1 = new StringBuilder();     // Not working for large numbers
        StringBuilder s2 = new StringBuilder();

        LLNode temp1 = list1;
        LLNode temp2 = list2;
        while (temp1 != null || temp2 != null) {

            if(temp1 != null) {
                s1.append(temp1.data);
                temp1 = temp1.next;
            }
            if(temp2 != null) {
                s2.append(temp2.data);
                temp2 = temp2.next;
            }

        }

        s1.reverse();
        s2.reverse();

        long result = Long.parseLong(s1.toString()) + Long.parseLong(s2.toString());

        String s = new StringBuilder(String.valueOf(result)).reverse().toString();

        LLNode dummy = new LLNode(0);
        LLNode temp = dummy;
        for(char c : s.toCharArray()) {

            temp.next = new LLNode(c - '0');    // c-'0' -> Integer.parseInt(String.valueOf(c));

            temp = temp.next;
        }

        return dummy.next;
    }



    // TC: O(max(n, m)), SC: O(max(n, m)) - digit-wise addition, unlimited precision
    public static LLNode addTwoNumbersOptimal(LLNode list1, LLNode list2) {     // GPT
        LLNode dummy = new LLNode(0);
        LLNode tail = dummy;
        int carry = 0;

        while (list1 != null || list2 != null || carry != 0) {
            int sum = carry;
            if (list1 != null) {
                sum += list1.data;
                list1 = list1.next;
            }
            if (list2 != null) {
                sum += list2.data;
                list2 = list2.next;
            }

            carry = sum / 10;
            tail.next = new LLNode(sum % 10);
            tail = tail.next;
        }

        return dummy.next;
    }

}
