import java.util.ArrayList;
import java.util.List;

public class CombinationsPhone {    // To Revise
    public static void main(String[] args) {
        String digits = "234";
        System.out.println(letterCombinations(digits));
    }

    public static List<String> letterCombinations(String digits) {

        String[] phone = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        List<String> result = new ArrayList<>();

        backtrack(0, digits, new StringBuilder(), result, phone);
        return result;
    }

    private static void backtrack(int index, String digits, StringBuilder curr, List<String> result, String[] phone) {
        
        if (index == digits.length()) {
            result.add(curr.toString());
            return;
        }

        String letters = phone[digits.charAt(index) - '0'];

        for (int i = 0; i < letters.length(); i++) {

            curr.append(letters.charAt(i));
            backtrack(index + 1, digits, curr, result, phone);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
    
}
