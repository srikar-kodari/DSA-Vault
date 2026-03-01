public class MaxNestParentheses {
    
    public static int maxDepth(String s) {

        int count = 0;
        int maxCount = 0;

        char[] arr = s.toCharArray();

        for(int i = 0; i<arr.length; i++) {

            if(arr[i] == '(') {
                count ++;
                maxCount = Math.max(maxCount, count);
            }
            else if(arr[i] == ')') {
                count--;
            }
        }

        return maxCount;
    }
}
