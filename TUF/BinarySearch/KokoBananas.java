import java.util.Arrays;

public class KokoBananas {
    
    public static int kokoBananas(int[] piles, int h) {

        int low = 1;
        int high = Arrays.stream(piles).max().getAsInt();   //Max element in array

        int ans  = Integer.MAX_VALUE;
        
        while(low <= high) {
            int mid = (low + high)/2;

            int time = 0;
            for(int n : piles) {
                time += Math.ceil((double) n / (double) mid);   //ENSURE THE DIVISON TO BE DOUBLE
            }

            if(time <= h) {

                ans = Math.min(ans, mid);
                high = mid - 1;
            }
            else low = mid + 1;
        }
        //System.out.println("Low is: " + low);
        return ans; //Answer is also = low
    }
}
