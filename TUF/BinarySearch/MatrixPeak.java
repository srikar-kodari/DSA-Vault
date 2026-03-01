public class MatrixPeak {   //Similar to finding PEAK ELEMENT in an array
    
    public static int[] findPeak(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        int low = 0;
        int high = m-1;

        while(low <= high) {
            int mid = (low + high)/2;

            int rowIndex = maxRowIndex(matrix, n, m, mid);

            int left = mid-1 >= 0 ? matrix[rowIndex][mid-1] : -1;
            int right = mid+1 < m ? matrix[rowIndex][mid+1] : -1;

            if(matrix[rowIndex][mid] > left && matrix[rowIndex][mid] > right) return new int[] {rowIndex,mid};

            else if(matrix[rowIndex][mid] > right) high = mid - 1;
            else low = mid + 1;
        }
        return new int[] {-1,-1};
    }

    public static int maxRowIndex(int[][] matrix, int n, int m, int column) {

        int maxValue = -1;
        int rowIndex = -1;

        for(int i=0; i<n; i++) {
            if(matrix[i][column] > maxValue) {
                maxValue = matrix[i][column];
                rowIndex = i;
            }
        }
        return rowIndex;
    }
}
