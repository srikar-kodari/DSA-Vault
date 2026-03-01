public class MinBitFlips {
    public static void main(String[] args) {

        int ans = minBitFlips(10, 7);
        System.out.println("Min Bit Flips is: " + ans);
        
    }

    // TC: O(k) where k is the number of set bits (max 32 for int, so O(1))
    // SC: O(1) - constant extra space
    static int minBitFlips(int start, int target) {

        int ans = start ^ target;

        int count = 0;
        while (ans != 0) {
            
            ans = ans & (ans-1);
            count++;
        }

        return count;
    }
}
