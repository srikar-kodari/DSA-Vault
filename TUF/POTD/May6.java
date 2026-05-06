public class May6 {
    public static void main(String[] args) {
        char[][] matrix = {
                {'#', '.', '*', '.'},
                {'#', '#', '*', '.'}
        };

        System.out.println("Input matrix:");
        printMatrix(matrix);

        char[][] rotated = rotateTheMatrix(matrix);

        System.out.println("\nRotated matrix:");
        printMatrix(rotated);
    }

    // LeetCode - 1861. Rotating The Box
    public static char[][] rotateTheMatrix(char[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        for(int i=0; i<m; i++) {

            int pointer = n-1;
            for(int j=n-1; j>=0; j--) {

                if(matrix[i][j] == '*') pointer = j-1;

                else if(matrix[i][j] == '#') {
                    char temp = matrix[i][j];
                    matrix[i][j] = matrix[i][pointer];
                    matrix[i][pointer] = temp;

                    pointer--;
                }
            }
        }

        char[][] result = new char[n][m];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                result[j][m-1-i] = matrix[i][j];
            }
        }

        return result;
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
