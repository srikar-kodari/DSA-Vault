import java.util.Arrays;

public class BSDemo {   //Time Complexity: log2(n), n -> Size of Array

    //Contains code for BS demo, lower bound, upper bound, floor and ceil
    
    public static int bsDemoIterative(int[] arr, int n, int target) {

        int low = 0;
        int high = n-1;

        while(low <= high) {
            int mid = (low+high)/2;

            if(arr[mid] == target) return mid;
            else if(target > arr[mid]) low = mid+1;
            else high = mid-1;
        }

        return -1;  //Time Complexity: log2(n)
    }

    public static int bsDemoRecursive(int[] arr, int low, int high, int target) {

        if(low > high) return -1;

        int mid = (low+high)/2;

        if(arr[mid] == target) return mid;
        else if(target > arr[mid]) return bsDemoRecursive(arr, mid+1, high, target);
        else return bsDemoRecursive(arr, low, mid-1, target);
    }

    public static int lowerBound(int[] arr, int n, int target) {    //Smallest index such that arr[index] >= target

        int low = 0;
        int high = n-1;
        int lowerBound = n;

        while(low <= high) {

            int mid = (low+high)/2;

            if(arr[mid] >= target) {
                lowerBound = mid;
                high = mid-1;
            }
            else if(target > arr[mid]) low = mid+1;  //else
        }
        return lowerBound;
    }

    public static int lBound(int[] arr, int target) {   //Simple
        int lB = Arrays.binarySearch(arr, target);
        int lBound = (lB >= 0) ? lB : -lB-1;    //lB returns -(insert position)-1

        return lBound;
    }

    public static int uBound(int[] arr, int target) {   //Simple
        int uB = Arrays.binarySearch(arr, target);
        int uBound = (uB >= 0) ? uB+1 : -uB-1;

        return uBound;
    }

    public static int upperBound(int[] arr, int n, int target) {    //Smallest index such that arr[index] > target

        int low = 0;
        int high = n-1;
        int upperBound = n;

        while(low <= high) {

            int mid = (low+high)/2;

            if(arr[mid] > target) {
                upperBound = mid;
                high = mid-1;
            }
            else low = mid+1;
        }
        return upperBound;
    }

    public static int ceil(int[] arr, int target) {     //Ceil == Lower Bound
        int c = Arrays.binarySearch(arr, target);
        int ceil = (c >= 0) ? c : -c-1;    //lB returns -(insert position)-1

        if(ceil > arr.length-1) return -1;

        return arr[ceil];
    }

    public static int floor(int[] arr, int target) {
        int f = Arrays.binarySearch(arr, target);
        int floor = (f >= 0) ? f : -f-1-1;    //lB returns -(insert position)-1

        if(floor < 0) return -1;

        return arr[floor];
    }

}
