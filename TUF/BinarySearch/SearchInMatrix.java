import java.util.Arrays;

public class SearchInMatrix {
    // For searchMatrix1 & searchMatrix2, every element in matrix is increasing in order from [0][0]->[n][m]
    // For searchMatrix3, every row & column is sorted in increasing order

    public static boolean searchMatrix1(int[][] matrix, int target) {    //TC: O(n) + log2(m)

        int n = matrix.length;
        int m = matrix[0].length;

        for(int i=0; i<n; i++) {
            if(matrix[i][0] <= target && target <= matrix[i][m-1]) {
                boolean result = Arrays.binarySearch(matrix[i], target) >= 0;   //Can return index or boolean value
                return result;
            }
        }
        return false;
    }

    public static boolean searchMatrix2(int[][] matrix, int target) {   //Assume 2D array as 1D array

        int n = matrix.length;      //TC: log2(n x m)
        int m = matrix[0].length;   //SC: O(1). Did not use any extra space

        int low = 0;
        int high = (n * m) - 1;

        while(low <= high) {
            int mid = (low + high)/2;

            int row = mid / m;      //Compute ROW
            int column = mid % m;   //Compute COLUMN

            if(matrix[row][column] == target) return true;

            else if(matrix[row][column] < target) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }

    //Intuition explained by STRIVER is CRAZY
    //Intuition: The L-Shape (Ex: (0,0)->(0,m)->(n,m)) of matrix is always sorted, and we will make eliminations accordingly
    public static int[] searchMatrix3(int[][] matrix, int target) {

        int n = matrix.length;      //TC: O(n+m)
        int m = matrix[0].length;   //SC: O(1)

        int row = 0;
        int column = m - 1;

        while((row < n) && (column >= 0)) {

            if(matrix[row][column] == target) return new int[] {row,column};

            else if(matrix[row][column] > target) column--;
            else row++;
        }
        return new int[] {-1,-1};   //Can return boolean depending upon question
    }

}
