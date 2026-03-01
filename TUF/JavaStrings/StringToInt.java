public class StringToInt {      // ATOI

    public static int atoi(String s) {     // GPT

        // Skip leading whitespace
        s = s.stripLeading();
        if(s.isEmpty()) return 0;

        int i = 0;
        int n = s.length();  // Update n AFTER stripping, as length reduces after removing leading spaces
        int sign = 1;
        long result = 0;

        // Skip leading whitespace                  // Can use this while loop also to skip the leading spaces
        // while(i < n && s.charAt(i) == ' ') {
        //     i++;
        // }

        // Check for sign
        if(i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        // Build result from digits
        while(i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');
            
            // Handle overflow
            if(result > Integer.MAX_VALUE) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            
            i++;
        }

        return (int)(result * sign);
    }
}
