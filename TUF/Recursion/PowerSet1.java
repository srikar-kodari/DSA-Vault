import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PowerSet1 {
    
    public static void main(String[] args) {

        String s = "abc";
        // generateSubsequences(s);
        powerSet(s);
    }

    static void generateSubsequences(String s) {    // Gives Output In Sorted Order

        List<String> ans = new ArrayList<>();

        int n = s.length();
        genSubsequences(0, n, s, new ArrayList<>(), ans);

        Collections.sort(ans);
        for(String str : ans) {
            System.out.print(str + " ");
            System.out.println();
        }
    }

    static void genSubsequences(int index, int n, String s, List<String> current, List<String> ans) {

        if(index == n) {
            ans.add(String.join("", current));
            return;
        }

        current.add(String.valueOf(s.charAt(index)));
        genSubsequences(index+1, n, s, current, ans);

        current.removeLast();
        genSubsequences(index+1, n, s, current, ans);
    }


    static void powerSet(String s) {    // To Revise

        int n = s.length();

        for(int i=0; i < (1 << n); i++) {   // (1 << n) -> 2^n  | Left Shift Bits

            String sub = "";
            for(int j=0; j<n; j++) {

                if((i & (1 << j)) != 0) {

                    sub += s.charAt(j);
                }
            }
            System.out.println(sub);
        }
    }

}
