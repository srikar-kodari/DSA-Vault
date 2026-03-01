public class RotatedSorted {

    public static int rotatedSorted1(int[] arr, int n, int target) {

        int low = 0;
        int high = n-1;

        while (low <= high) {
            int mid = (low+high)/2;

            if (arr[mid] == target) return mid;

            //Left Sorted
            if(arr[low] <= arr[mid]) {

                if(arr[low] <= target && target <= arr[mid]) high = mid - 1;
                else low = mid + 1;
            }

            //Right Sorted
            else {

                if(arr[mid] <= target && target <= arr[high]) low = mid + 1;
                else high = mid - 1;
            }
            
        }
        return -1;
    }

    public static boolean rotatedSorted2(int[] arr, int n, int target) {    //Contains Duplicates

        int low = 0;
        int high = n-1;

        while (low <= high) {
            int mid = (low+high)/2;

            if (arr[mid] == target) return true;

            if(arr[low] == arr[mid] && arr[mid] == arr[high]) {     //Condition to check duplicates and trim down search space
                low++;
                high--;
                continue;
            }

            //Left Sorted
            if(arr[low] <= arr[mid]) {

                if(arr[low] <= target && target <= arr[mid]) high = mid - 1;
                else low = mid + 1;
            }
            //Right Sorted
            else {

                if(arr[mid] <= target && target <= arr[high]) low = mid + 1;
                else high = mid - 1;
            }
            
        }
        return false;
    }

    public static int minNumRotated(int[] arr, int n) {

        int low = 0;
        int high = n-1;
        int minNum = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low+high)/2;

            //Left Sorted
            if(arr[low] <= arr[mid]) {
                minNum = Math.min(arr[low], minNum);
                low = mid + 1;
            }

            //Right Sorted
            if(arr[mid] <= arr[high]) {     //else
                minNum = Math.min(arr[mid], minNum);
                high = mid - 1;
            }
        }
        return minNum;

        //To get the number of times an array is rotated, we have to calculate the index of min element.
        //For that, instead of using Math.min use if loop and update the index of min element.
    }

}
