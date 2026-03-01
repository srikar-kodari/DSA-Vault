public class XorRange {
    public static void main(String[] args) {

        // int ans = xorRange(4);
        // System.out.println("XOR Range of 1 To N is: " + ans);

        // int ans = xorRange1(4);
        // System.out.println("XOR Range of 1 to N is: " + ans);

        int ans = xorRange2(5, 8);
        System.out.println("XOR Range of A to B is: " + ans);

    }

    // TC: O(n) - iterates from 1 to n
    // SC: O(1) - constant extra space
    static int xorRange(int n) {    // XOR Range of 1 to N

        int ans = 0;
        for(int i=1; i<=n; i++) ans ^= i;

        return ans;
    }

    // TC: O(1) - constant time (modulo and comparison)
    // SC: O(1) - constant extra space
    static int xorRange1(int n) {   // XOR Range of 1 to N

        if(n % 4 == 1) return 1;
        else if(n % 4 == 2) return n+1;
        else if(n % 4 == 3) return 0;
        else return n;
    }

    // TC: O(1) - calls xorRange1 twice (constant time)
    // SC: O(1) - constant extra space
    static int xorRange2(int start, int last) {     // XOR Range of start to last
        
        int xor1 = xorRange1(start-1);
        int xor2 = xorRange1(last);

        return xor1 ^ xor2;
    }

}
