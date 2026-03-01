import java.util.Arrays;

public class RotateImage {

    public static void main(String[] args) {
        
        // int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        // rotate(matrix);
        // rotate1(matrix);
        
        int[][] matrix2 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] result = rotateRecursive(matrix2);
        System.out.println(Arrays.deepToString(result));
    }

    public static void rotate(int[][] matrix) {

        int len = matrix.length;
        int[][] result = new int[len][len];

        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {

                result[j][len-1-i] = matrix[i][j];
            }
        }

        System.out.println(Arrays.deepToString(result));
    }

    public static void rotate1(int[][] matrix) {    // Without Modifying Input

        int len = matrix.length;
        int[][] copy = new int[len][len];

        for(int i=0; i<len; i++) {
            copy[i] = Arrays.copyOf(matrix[i], len);
        }

        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {

                matrix[j][len-1-i] = copy[i][j];
            }
        }

        System.out.println(Arrays.deepToString(matrix));
    }

    // Recursive solution
    public static int[][] rotateRecursive(int[][] matrix) {
        int len = matrix.length;
        int[][] result = new int[len][len];
        rotateHelper(matrix, result, 0, 0, len);
        return result;
    }

    private static void rotateHelper(int[][] matrix, int[][] result, int i, int j, int len) {
        // Base case: processed all elements
        if (i >= len) {
            return;
        }
        
        // Process current cell: result[j][len-1-i] = matrix[i][j]
        result[j][len - 1 - i] = matrix[i][j];
        
        // Move to next column
        if (j + 1 < len) {
            rotateHelper(matrix, result, i, j + 1, len);
        } else {
            // Move to next row, reset column to 0
            rotateHelper(matrix, result, i + 1, 0, len);
        }
    }
}
