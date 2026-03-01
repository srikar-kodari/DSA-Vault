public class Power {

    public static double myPower(double x, int n) {     // Brute Force
        
        if (n == 0) return 1.0;
        if (n < 0) return 1.0 / myPower(x, -n);
        if (n == 1) return x;

        return x * myPower(x, n - 1);
    }


    public static double myPowerSafe(double x, int n) {     // Efficient

        if (n < 0) {
            x = 1.0 / x;
            n = -n;
        }

        // return fastPow(x, n);
        return bestPow(x, n);
    }

    private static double fastPow(double x, long n) {
        if (n == 0) return 1.0;
        if (n == 1) return x;

        double half = fastPow(x, n / 2);
        if (n % 2 == 0) return half * half;
        return half * half * x;
    }

    // Binary / Fast Exponentiation
    // Theory: exponentiation by squaring. Keep squaring base and halve exponent; if exponent is odd,
    // multiply one base into the answer. Invariant: ans * x^n == original_x^original_n.
    // If using modulo, reduce after each multiply: (a*b) % MOD == ((a%MOD)*(b%MOD)) % MOD.
    // Time: O(log n), Space: O(1) for iterative, O(log n) for recursion depth.
    private static double bestPow(double x, long n) {
        if (n == 0) return 1.0;
        if (n == 1) return x;

        if(n % 2 == 0) return bestPow(x*x, n/2);    // Even

        return x * bestPow(x, n-1);     // Odd
    }

    // Binary Exponentiation - Striver
    private static long binaryPow(long x, long n) {

        // long m = n;     // Used If Exponent Is Negative

        long ans = 1;

        while(n > 0) {

            if(n % 2 == 1) {
                ans = ans * x;
                n = n - 1;
            }
            else {
                n = n / 2;
                x = x * x;
            }
        }
        // if(m < 0) ans = 1 / ans;    // Must Use Double
        return ans;
    }

    // Binary Exponentiation - Errichto Algorithms
    private static long binaryPowEA1(long x, long n) {

        long ans = 1;

        while(n > 0) {
            if(n % 2 == 1) ans = ans * x;

            x = x * x;
            n = n / 2;
        }

        return ans;
    }

    // Binary Exponentiation - Errichto Algorithms | RECURSION
    private static long binaryPowEA2(long x, long n) {

        if(n == 0) return 1;

        long tmp = binaryPowEA2(x, n/2);

        long ans = tmp * tmp;

        if(n % 2 == 1) ans *= x;

        return ans;
    }


}
