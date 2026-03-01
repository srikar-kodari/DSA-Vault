public class NumberConversions {
    
    // TC: O(log n) - number of bits in n
    // SC: O(log n) - StringBuilder stores log n characters
    static String convert2Binary(int n) {

        StringBuilder ans = new StringBuilder();
        
        while(n != 0) {

            ans.append(n%2);
            n = n/2;
        }
        ans.reverse();

        return ans.toString();
    }

    // TC: O(n) - where n is the length of string s
    // SC: O(1) - constant extra space
    static int convert2Decimal(String s) {

        int len = s.length();

        int decimal = 0;
        for(int i = len-1; i>=0; i--) {

            decimal += Integer.parseInt(String.valueOf(s.charAt(i))) * (int) Math.pow(2, (len-1)-i);
        }

        return decimal;
    }

    static int convert2Decimal1(String s) {     // Optimal

        int len = s.length();

        int decimal = 0;
        int power = 1;
        for(int i = len-1; i>=0; i--) {

            if(s.charAt(i) == '1') {
                decimal += power;
            }
            power *= 2;
        }

        return decimal;
    }

}
