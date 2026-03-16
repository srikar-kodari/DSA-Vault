public class MaxIncreaseCitySky {
    public static void main(String[] args) {

        int[][] matrix = {{3,0,8,4},{2,4,5,7},{9,2,6,3},{0,3,1,0}};
        int ans = maxIncreaseSkyline(matrix);
        System.out.println(ans);
        
    }

    static int maxIncreaseSkyline(int[][] grid) {

        int n = grid.length;

        int[] rowMax = new int[n];
        int[] colMax = new int[n];

        // Row Max
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                rowMax[i] = Math.max(rowMax[i], grid[i][j]);
            }
            // rowMax[i] = Arrays.stream(grid[i]).max().orElse(Integer.MIN_VALUE);
        }

        // Col Max
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                colMax[j] = Math.max(colMax[j], grid[i][j]);
            }
        }

        int totalIncrease = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int allowedHeight = Math.min(rowMax[i], colMax[j]);
                totalIncrease += allowedHeight - grid[i][j];
            }
        }

        return totalIncrease;
    }

}
