public class SimpleProblems {
    public static void main(String[] args) {

        // SimpleProblems.swap();

        // SimpleProblems.ithBitCheck(13, 2);
        // SimpleProblems.ithBitCheck(13, 1);

        // SimpleProblems.ithBitSet(9, 2);

        // SimpleProblems.ithBitClear(13, 2);

        // SimpleProblems.ithBitToggle(13, 2);

        // SimpleProblems.removeLastSetBit(30);

        // SimpleProblems.isPowerOf2(1024);

        SimpleProblems.countSetBits(13);
        SimpleProblems.countSetBits1(13);

    }

    static void swap() {

        int a = 5;
        int b = 10;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("A is: " + a + ", B is: " + b);
    }

    static void ithBitCheck(int n, int i) {

        // Left Shift
        boolean isSet = (n & (1 << i)) != 0;
        System.out.println(isSet ? "Set" : "Not Set");

        // Right Shift
        boolean isSet2 = (1 & (n >> i)) == 0;
        System.out.println(isSet2 ? "Not Set" : "Set");
    }

    static void ithBitSet(int n, int i) {

        System.out.println((n | (1 << i)));
    }

    static void ithBitClear(int n, int i) {

        System.out.println(n & ~(1 << i));
    }

    static void ithBitToggle(int n, int i) {

        System.out.println(n ^ (1 << i));
    }

    static void removeLastSetBit(int n) {   // Right Most Set Bit

        System.out.println(n & n-1);
    }

    static void isPowerOf2(int n) {

        if((n & n-1) == 0) System.out.println("True");
        else System.out.println("False");
    }

    static void countSetBits(int n) {

        int count = 0;
        while (n != 0) {

            count += n & 1;     // if(n % 2 == 1) count++;
            n = n >> 1;         // n = n / 2;
        }
        System.out.println(count);
    }

    static void countSetBits1(int n) {

        int count = 0;
        while (n != 0) {

            n = n & (n-1);
            count++;
        }
        System.out.println(count);
    }

}
