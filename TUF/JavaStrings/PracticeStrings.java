public class PracticeStrings {
    public static void main(String[] args) {

        // String a = "Srikar";
        // String b = "Srikar";
        // String c = new String("Srikar");

        // System.out.println(a == b);
        // System.out.println(a == c);
        // System.out.println(a.equals(c));

        // System.out.println(a.charAt(0));

        // int[] arr = {1,2,3};
        // System.out.println(Arrays.toString(arr));

        // Integer num = new Integer(24);
        // System.out.println(num.toString());

        // System.out.printf("Pi value: %.4f%n", Math.PI); // Placeholders in java. %n -> New Line
        // System.out.printf("I am %s and I am %s%n", "Srikar", "Cool"); // String value after formatting

        // System.out.println((char) ('a' + 1));
        // System.out.println("a" + 1);

        // System.out.println(a.concat(c));

        // String Builder
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<26; i++) {
            char ch = (char) ('a' + i);
            sb.append(ch);
        }
        System.out.println(sb.toString());
        
    }
}
