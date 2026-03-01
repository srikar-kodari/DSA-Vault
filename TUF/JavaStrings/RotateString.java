public class RotateString {     // To check if on rotating 's' multiple times to get 'goal'
    
    public static boolean rotateString(String s, String goal) { // TC: O(n^2), SC: O(n)

        int k = s.length(); // Max number of rotations

        if(s.length() == 1) {
            if(s.equals(goal)) return true;
            else return false;
        }

        StringBuilder sb = new StringBuilder(s);

        while(k-- > 0) {

            char first = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(first);

            if(sb.toString().equals(goal)) return true;
        }

        return false;
    }

    public static boolean rotateString1(String s, String goal) {    // Most Efficient. Mind Blown

        if(s.length() != goal.length()) return false;   // TC: O(n), SC: O(n)

        return (s + s).contains(goal);
    }
}
