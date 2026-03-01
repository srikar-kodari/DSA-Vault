public class SimpleRecursion {
    public static void main(String[] args) {

        // printOneToN(5);
        // printNTo1(5, 5);
        // parameterisedSum(5, 0);
        // System.out.println(functionalSum(5));
        // reverseString("TONY", 0);
        // System.out.println(reverseString1("STARK", 0));
        System.out.println(reverseStringEfficient("TONY STARK"));
        
    }

    public static void printOneToN(int n) {

        if(n < 1) return;

        System.out.println(n);  // N to 1
        printOneToN(n-1);
        System.out.println(n);  // 1 to N - BT
    }

    public static void printNTo1(int n, int i) {

        if(i < 1) return;

        printNTo1(n, i-1);
        System.out.println(n + 1 - i);  // N to 1 - BT
    }

    public static void parameterisedSum(int i, int sum) {

        if(i < 1) {
            System.out.println(sum);
            return;
        }

        parameterisedSum(i-1, sum+i);
    }

    public static int functionalSum(int n) {

        if(n == 0) return 0;

        return n + functionalSum(n-1);
    }

    public static void reverseString(String s, int i) {

        if(i == s.length()) return;

        reverseString(s, i+1);

        System.out.println(s.charAt(i));
    }

    public static String reverseString1(String s, int i) {

        if(i == s.length()) return "";

        return reverseString1(s, i+1) + s.charAt(i);
    }

    public static String reverseStringEfficient(String s) {
        if (s == null) return null;
    
        char[] chars = s.toCharArray();
        reverseInPlace(chars, 0, chars.length - 1);
        return new String(chars);
    }
    private static void reverseInPlace(char[] chars, int left, int right) {
        if (left >= right) return;
    
        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;
    
        reverseInPlace(chars, left + 1, right - 1);
    }

}
