public class FLOccurence {
    
    public static int[] flOccurence(int[] arr, int target) {    //First and Last occurence of target in the array

        // Calculate lower bound and (upper bound - 1) for first and last occurence. Used simple compute (3 loops) for UB (Last Occurence)

        int firstOccurence = firstOccurence(arr, arr.length, target);

        if(firstOccurence == -1) return new int[] {-1,-1};

        int lastOccurence = lastOccurence(arr, arr.length, target);

        return new int[] {firstOccurence, lastOccurence};

        //Count no of occurences of an element
        //if(firstOccurence == -1) return 0;
        //else return lastOccurence - firstOccurence + 1;
    }

    public static int firstOccurence(int[] arr, int n, int target) {    //Lower Bound

        int low = 0;
        int high = n-1;
        int firstOccurence = -1;

        while(low <= high) {
            int mid = (low+high)/2;

            if(arr[mid] >= target) {
                firstOccurence = mid;
                high = mid-1;
            }
            else low = mid+1;
        }

        if(firstOccurence != -1 && arr[firstOccurence] == target) return firstOccurence;    //Condition
        else return -1;
    }

    public static int lastOccurence(int[] arr, int n, int target) {     //Used simple Binary Search with 3 loops

        int low = 0;
        int high = n-1;
        int lastOccurence = -1;

        while(low <= high) {
            int mid = (low+high)/2;

            if(arr[mid] == target) {
                lastOccurence = mid;
                low = mid+1;
            }
            else if(arr[mid] < target) low = mid+1;
            else high = mid-1;
        }
        return lastOccurence;   //NOTE: Use 3 loops: if, if else, else for simple compute. For firstOccurence also.
    }

}