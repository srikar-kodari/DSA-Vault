public class MaxOddBinary {
    public static void main(String[] args) {
        
        // String s = "1010";
        String s = "010";
        String ans = maxOddBinary(s);
        System.out.println(ans);

    }

    static String maxOddBinary(String s) {

        int n = s.length();
        int count1 = (int) s.chars().filter(c -> c == '1').count();

        StringBuilder sb = new StringBuilder();

        sb.append("1".repeat(count1-1))
            .append("0".repeat(n-count1))
            .append("1");

        return sb.toString();
    }

}
