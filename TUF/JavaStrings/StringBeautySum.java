public class StringBeautySum {

    public static int finalBeautySum(String s) {    // O(n^3)

        int sum = 0;

        int len = s.length();
        for(int i=0; i<len; i++) {
            for(int j=i+3; j<=len; j++) {  // j-i >= 3 for minimum substring length

                String t = s.substring(i, j);

                sum += singleBeautySum(t);
            }
        }

        return sum;
    }

    public static int singleBeautySum(String s) {

        int[] arr = new int[26]; // s only contains lowercase letters

        for(int i=0; i<s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int freq : arr) {
            if(freq > 0) {
                min = Math.min(min, freq);
                max = Math.max(max, freq);
            }
        }
        sum += max - min;

        return sum;
    }

    public static int finalBeautySum1(String s) {    // O(n^2)  // GPT
        int sum = 0;
        int len = s.length();
        
        for(int i = 0; i < len; i++) {
            int[] freq = new int[26];  // Reset frequency for each starting position
            
            for(int j = i; j < len; j++) {
                freq[s.charAt(j) - 'a']++;  // Increment frequency
                
                if(j - i >= 2) {  // Only process substrings of length >= 3
                    sum += getBeautySum(freq);
                }
            }
        }
        
        return sum;
    }

    public static int getBeautySum(int[] freq) {    // GPT
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for(int f : freq) {
            if(f > 0) {
                min = Math.min(min, f);
                max = Math.max(max, f);
            }
        }
        
        return (min == Integer.MAX_VALUE) ? 0 : (max - min);
    }

}
