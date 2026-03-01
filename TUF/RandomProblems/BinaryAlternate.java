public class BinaryAlternate {
    public static void main(String[] args) {

        System.out.println("Result is: " + hasAlternatingBits(7));
        
    }

    static boolean hasAlternatingBits(int n) {

        String s = Integer.toBinaryString(n);
        int len = s.length();

        if(len == 1) return true;

        for(int i=1; i<len; i++) {

            if(s.charAt(i) == s.charAt(i-1)) return false;
        }
        return true;
    }
}
