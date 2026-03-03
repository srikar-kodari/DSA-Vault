public class CelebrityProblem {
    public static void main(String[] args) {

        // int[][] matrix = {{0,1,1,0}, {0,0,0,0}, {0,1,0,0}, {1,1,0,0}};
        // int ans = celebrityBrute(matrix);
        // System.out.println("Celebrity is: " + ans);

        int[][] matrix = {{0,1,1,0}, {0,0,0,0}, {0,1,0,0}, {1,1,0,0}};
        int ans = celebrity(matrix);
        System.out.println("Celebrity is: " + ans);

    }

    // TC: O(2N)
    // SC: O(1)
    static int celebrity(int[][] matrix) {

        int n = matrix.length;

        int top = 0;
        int down = n-1;

        while (top < down) {
            
            if(matrix[top][down] == 1) top++;
            else if(matrix[down][top] == 1) down--;
            else {
                top++;
                down--;
            }
        }

        if(top > down) return -1;

        for(int i=0; i<n; i++) {
            if(i == top) continue;

            if(!(matrix[top][i] == 0 && matrix[i][top] == 1)) return -1;
        }

        return top;
    }

    // TC: O(N X N) + O(N)
    // SC: O(2N)
    static int celebrityBrute(int[][] matrix) {

        int n = matrix.length;

        // knowMe should be n-1.
        // iKnow should be 0.

        int[] knowMe = new int[n];
        int[] iKnow = new int[n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {

                if(i==j) continue;

                if(matrix[i][j] == 1) {
                    knowMe[j]++;
                    iKnow[i]++;
                }
            }
        }

        for(int i=0; i<n; i++) {
            if(knowMe[i] == n-1 && iKnow[i] == 0) return i;
        }

        return -1;
    }

}
