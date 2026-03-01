public class SquareRoot {
    
    public static int squareRoot(int n) {

        int low = 1;
        int high = n;

        int result = 1;

        while(low <= high) {
            int mid = (low + high)/2;

            if(mid * mid <= n) {
                result = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }
        return result;  //Can also return high
    }

    public static int nthRoot(int num, int n) {

        int low = 1;
        int high = num;

        while(low <= high) {
            int mid = (low + high) / 2;

            if((int) Math.pow(mid,n) == num) return mid;

            if(Math.pow(mid,n) < num) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}
