public class DivideTwoNumbers {
    public static void main(String[] args) {

        // int ans = divide2NumbersBrute(22, 3);
        // System.out.println("Answer is: " + ans);

        int ans = divide2Numbers(-2147483648, -1);
        System.out.println("Answer is: " + ans);

    }

    static int divide2NumbersBrute(int dividend, int divisor) {   // Brute
         int sum = 0;
         int count = 0;

         while (sum + divisor <= dividend) {
            count++;
            sum += divisor;
         }
         return count;
    }

    static int divide2Numbers(int dividend, int divisor) {

        if(dividend == divisor) return 1;

        boolean sign = true;
        if(dividend >= 0 && divisor < 0) sign = false;
        else if(dividend < 0 && divisor > 0) sign = false;

        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);

        long quotient = 0;
        while(n >= d) {

            int count = 0;
            while (n >= (d << (count+1))) {
                count++;
            }
            quotient += (1L << count);
            n -= (d << count);
        }

        if(quotient == (1L << 31) && sign) return Integer.MAX_VALUE;
        if(quotient == (1L << 31) && !sign) return Integer.MIN_VALUE;

        return (int) (sign ? quotient : -quotient);
    }

}
