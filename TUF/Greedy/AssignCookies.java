import java.util.Arrays;

public class AssignCookies {
    public static void main(String[] args) {

        int[] g = {1,5,3,3,4};
        int[] s = {4,2,1,2,1,3};
        int ans = assignCookies(g, s);
        System.out.println(ans);
        
    }

    // TC: O(n log n + m log m), where n = g.length and m = s.length.
    // SC: O(1) auxiliary space (excluding sorting implementation internals).
    static int assignCookies(int[] g, int[] s) {

        if(s.length == 0 || g.length == 0) return 0;

        Arrays.sort(g);
        Arrays.sort(s);

        int gLen = g.length, sLen = s.length;
        int gP = 0, sP = 0, count = 0;

        while (gP < gLen && sP < sLen) {

            if(s[sP] >= g[gP]) {
                count++;
                sP++;
                gP++;
            }
            else sP++;
        }

        return count;
    }
    
}
