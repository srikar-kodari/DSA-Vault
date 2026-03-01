public class Operators {
    public static void main(String[] args) {

        // // AND
        // int n = 13 & 7;
        // System.out.println(n);

        // // OR
        // int k = 13 | 7;
        // System.out.println(k);

        // // XOR
        // int s = 13 ^ 7;
        // System.out.println(s);

        // // Right Shift  (x >> k = x / 2^k)
        // int x = 13 >> 1;
        // // int x = 13 >> 2;
        // System.out.println(x);

        // // Left Shift   (x << k = x * 2^k)
        // int x = 13 << 1;
        // // int x = Integer.MAX_VALUE;
        // System.out.println(x);

        // // NOT
        // int x = ~ -6;
        // System.out.println(x);

        // Complement methods
        complementDemo();

    }

    public static void complementDemo() {
        System.out.println("\n=== 1's Complement & 2's Complement ===");
        System.out.println();
        
        int num = 5;
        
        // 1's Complement: Flip all bits (NOT operation)
        System.out.println("Number: " + num);
        System.out.println("Binary: " + Integer.toBinaryString(num));
        
        int onesComplement = ~num;
        System.out.println("1's Complement (~" + num + "): " + onesComplement);
        System.out.println("Binary: " + Integer.toBinaryString(onesComplement));
        
        System.out.println();
        
        // 2's Complement: 1's Complement + 1 (or just negate the number)
        int twosComplement = ~num + 1;
        System.out.println("2's Complement (~" + num + " + 1): " + twosComplement);
        System.out.println("Binary: " + Integer.toBinaryString(twosComplement));
        
        System.out.println();
        System.out.println("Alternative (negate): -" + num + " = " + (-num));
        
        // Another example
        System.out.println("\n--- Another Example ---\n");
        int num2 = -5;
        System.out.println("Number: " + num2);
        System.out.println("1's Complement (~" + num2 + "): " + ~num2);
        System.out.println("2's Complement (~" + num2 + " + 1): " + (~num2 + 1));
    }
    
}
