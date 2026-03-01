public class GoodNumbers {

    static final long MOD = 1_000_000_007L;

    public static long countGoodNumbers(long n) {

        long evenCount = (n + 1) / 2;  // positions 0,2,4...
        long oddCount = n / 2;        // positions 1,3,5...

        long part1 = power(5, evenCount);
        long part2 = power(4, oddCount);

        return (part1 * part2) % MOD;
    }

    // Fast power with modulo (binary exponentiation).
    // Theory: write exponent in binary and keep squaring the base. When a bit is 1 (odd exponent),
    // multiply it into result. Loop invariant: result * base^exponent == original_base^original_exponent (mod MOD).
    // Applying (a*b) % MOD after each multiply is safe because (a*b) % MOD == ((a%MOD)*(b%MOD)) % MOD.
    // Time: O(log exponent), Space: O(1). Assumes exponent >= 0.
    private static long power(long base, long exponent) {

        long result = 1;
        base = base % MOD;

        while (exponent > 0) {

            if (exponent % 2 == 1) {   // if exponent is odd
                result = (result * base) % MOD;
            }

            base = (base * base) % MOD;   // square base
            exponent = exponent / 2;      // reduce exponent
        }

        return result;
    }
    
    

}
