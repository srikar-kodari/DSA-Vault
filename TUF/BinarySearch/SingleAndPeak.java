public class SingleAndPeak {
    
    public static int singleElement(int[] arr, int n) {     //Single element in a sorted array

        if(n == 1) return arr[0];
        if(arr[0] != arr[1]) return arr[0];
        if(arr[n-1] != arr[n-2]) return arr[n-1];

        int low = 1;
        int high = n-2;

        while (low <= high) {
            int mid = (low+high)/2;

            if(arr[mid] != arr[mid+1] && arr[mid] != arr[mid-1]) return arr[mid];

            if((mid%2 == 0 && arr[mid] == arr[mid+1]) || (mid%2 == 1 && arr[mid] == arr[mid-1])) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public static int peakElement(int[] arr, int n) {   //Peak Element: Element which is greater than its neighbors

        if(n == 1) return 0;
        if(arr[0] > arr[1]) return 0;
        if(arr[n-1] > arr[n-2]) return n-1;

        int low = 1;
        int high = n-2;

        while(low <= high) {
            int mid = (low+high)/2;

            if(arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1]) return mid;

            if(arr[mid] > arr[mid-1] && arr[mid] < arr[mid+1]) low = mid + 1;
            else if(arr[mid] < arr[mid-1] && arr[mid] > arr[mid+1]) high = mid - 1;
            else low = mid + 1; // or high = mid - 1. This condition occurs when arr[mid-1] > arr[mid] < arr[mid+1]

        }
        return -1;
    }
}
