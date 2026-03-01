import java.util.HashMap;
import java.util.Map;

public class RomanToInt {
    
    public static int romanToInt(String s) {    // MY CODE

        Map<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;

        for(int i=0; i<s.length(); i++) {

            if(i == s.length()-1) {
                result += map.get(s.charAt(i));
                return result;
            }

            if(s.charAt(i) == 'V' || s.charAt(i) == 'L' || s.charAt(i) == 'D' || s.charAt(i) == 'M') {

                result += map.get(s.charAt(i));
            }

            else if(s.charAt(i) == 'C') {
                if(s.charAt(i+1) == 'D' || s.charAt(i+1) == 'M') {

                    result += map.get(s.charAt(i+1)) - 100;
                    i++;
                }
                else result += map.get(s.charAt(i));
            }

            else if(s.charAt(i) == 'X') {
                if(s.charAt(i+1) == 'L' || s.charAt(i+1) == 'C') {

                    result += map.get(s.charAt(i+1)) - 10;
                    i++;
                }
                else result += map.get(s.charAt(i));
            }

            else if(s.charAt(i) == 'I') {
                if(s.charAt(i+1) == 'V' || s.charAt(i+1) == 'X') {

                    result += map.get(s.charAt(i+1)) - 1;
                    return result;
                }
                result += map.get(s.charAt(i));
            }

        }

        return result;
    }

    public static int romanToInt1(String s) {   // GPT Optimised Code

        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        
        for(int i = 0; i < s.length(); i++) {

            int current = map.get(s.charAt(i));

            int next = (i + 1 < s.length()) ? map.get(s.charAt(i + 1)) : 0;
            
            if(current < next) {
                result += next - current;
                i++; // Skip next character
            }
            
            else result += current;
        }
        
        return result;
    }
}
