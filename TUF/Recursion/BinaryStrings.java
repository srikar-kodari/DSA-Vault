import java.util.*;

public class BinaryStrings {    // Generate Binary Strings Without Consecutive 1's

    static List<String> result = new ArrayList<>();

    public static void main(String[] args) {

        int n = 5;
        generate(n, "");

        System.out.println(result);
    }

    public static void generate(int n, String curr) {

        // Base case: length reached
        if (curr.length() == n) {
            result.add(curr);
            return;
        }

        // Always add '0'
        generate(n, curr + "0");

        // Add '1' only if previous char is not '1'
        if (curr.length() == 0 || curr.charAt(curr.length() - 1) != '1') {
            generate(n, curr + "1");
        }
    }

}
