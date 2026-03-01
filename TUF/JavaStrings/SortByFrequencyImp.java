import java.util.HashMap;
import java.util.Map;

public class SortByFrequencyImp {   // Sort input string by character frequency

    public static String frequencySort(String s) {

        int[] freqArray = new int[62];  // Index: 0-25 -> 'a-z', 26-51 -> 'A-Z', 52-61 -> 0-9

        for(char c : s.toCharArray()) {     // Frequency Count

            if(Character.isLowerCase(c)) freqArray[c - 'a']++;              // a-z
            else if(Character.isUpperCase(c)) freqArray[c - 'A' + 26]++;    // A-Z
            else freqArray[c - '0' + 52]++;                                 // 0-9
        }

        Map<Character, Integer> map = new HashMap<>();

        for(int i=0; i<62; i++) {   // Putting <Char, Frequency> in Map
            if(freqArray[i] == 0) continue;

            if(i >= 0 && i <= 25) map.put((char) ('a' + i), freqArray[i]);              // a-z
            else if(i >= 26 && i <= 51) map.put((char) ('A' + i - 26), freqArray[i]);   // A-Z
            else map.put((char) ('0' + i - 52), freqArray[i]);                          // 0-9
        }

        StringBuilder result = new StringBuilder(); // The below step sorts map based on VALUES() and computes the RESULT

        map.entrySet().stream()
                      .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                      .forEach(e -> result.append(String.valueOf(e.getKey()).repeat(e.getValue())));


        return result.toString();
    }

    public static String frequencySort1(String s) {     // Eliminates unnecessary code
        Map<Character, Integer> map = new HashMap<>();
    
        // Count frequencies directly in map
        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
    
        // Sort and build result
        StringBuilder result = new StringBuilder();

        map.entrySet().stream()
                      .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                      .forEach(e -> result.append(String.valueOf(e.getKey()).repeat(e.getValue())));
    

        return result.toString();
    }

}
