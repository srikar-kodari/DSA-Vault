public class LargestOdd {

    public static String largestOdd(String num) {

        for(int i = num.length()-1; i >= 0; i--) {
            int digit = num.charAt(i) - '0';    // Optimal for computing DIGIT
            //int digit = Integer.valueOf(num.charAt(i));

            if(digit % 2 != 0) return num.substring(0, i+1);
        }

        return "";
    }

    public static String largestOddNumber(String num) { // Works only for small numbers

        long n = Long.valueOf(num);

        while(n > 0) {
            if(n % 2 != 0) return Long.toString(n);
            n /= 10;
        }

        return "";
    }
}
