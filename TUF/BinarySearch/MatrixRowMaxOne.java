public class MatrixRowMaxOne {
    
    public static int maxOne(int[][] matrix, int n, int m) {    // TC: O(n x log2(m))

        int cntMax = 0;
        int index = -1;

        for(int i=0; i<n; i++) {

            int cntOne = m - firstOccurance(matrix[i], 1);  //Since array contains only 0,1's, count = Last Occurance - First Occurance

            if(cntOne > cntMax) {
                cntMax = cntOne;
                index = i;
            }
        }
        return index;
    }

    public static int firstOccurance(int[] arr, int element) {

        int low = 0;
        int high = arr.length - 1;

        int ans = -1;

        while(low <= high) {
            int mid = (low + high)/2;

            if(arr[mid] >= element) {
                if(arr[mid] == element) ans = mid;

                high = mid - 1;
            }
            else low = mid + 1;
        }
        return ans;
    }
}
