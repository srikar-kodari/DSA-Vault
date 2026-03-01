import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {
    
    public static boolean isIsomorphic(String s, String t) {

        int len = s.length();
        
        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> reverseMap = new HashMap<>();
        
        for(int i = 0; i < len; i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            
            if(!map.containsKey(sChar)) map.put(sChar, tChar);
            else if(map.get(sChar) != tChar) return false;
            
            if(!reverseMap.containsKey(tChar)) reverseMap.put(tChar, sChar);
            else if(reverseMap.get(tChar) != sChar) return false;

        }   // The second if, else-if step ensures one-one mapping. Ex: s = "ab", t = "aa"

        return true;
    }
}
