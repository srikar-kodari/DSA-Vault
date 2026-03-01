public class BinaryToOne {
    public static void main(String[] args) {

        // System.out.println("Result is: " + numStepsBitwise("1111110011101010110011100100101110010100101110111010111110110010"));
        System.out.println("Result is: " + numStepsBitwise("1111"));
        

    }

    static int numStepsBitwise(String s) {

        if (s.length() == 1) return 0;

        int steps = 0;
        int carry = 0;

        for (int i = s.length() - 1; i > 0; i--) {
            int bit = s.charAt(i) - '0';

            int effective = bit + carry;
            if (effective == 1) {
                steps += 2;
                carry = 1;
            } else {
                steps += 1;
            }
        }

        return steps + carry;
    }

    static int numSteps(String s) {     // Only handles small input values

        Long n = Long.valueOf(s,2);

        if(n == 1) return 0;

        int count = 0;
        while (n != 1) {
            if(n % 2 == 0) n = n / 2;
            else n += 1;
            count++;
        }

        return count;
    }
}
