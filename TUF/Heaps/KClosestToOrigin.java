import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class KClosestToOrigin {
    public static void main(String[] args) {

        int[][] points = {{3,3},{5,-1},{-2,4}};
        int[][] ans = kClosest(points, 2);
        System.out.println(Arrays.deepToString(ans));

        int[][] ansOptimized = kClosestOptimized(points, 2);
        System.out.println(Arrays.deepToString(ansOptimized));
        
    }

    // TC: O(n log k)
    // SC: O(n + k) auxiliary, plus O(k) output
    static int[][] kClosest(int[][] points, int k) {

        int n = points.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            map.put(i, (points[i][0]*points[i][0] + points[i][1]*points[i][1]));
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
            (a,b) -> Integer.compare(map.get(b), map.get(a))
        );

        for(int key : map.keySet()) {

            maxHeap.add(key);
            
            if(maxHeap.size() > k) maxHeap.poll();
        }

        int[][] ans = new int[k][2];

        int i = 0;
        while (!maxHeap.isEmpty()) {
            int index = maxHeap.poll();

            ans[i++] = points[index];
        }

        return ans;
    }

    // TC: O(n log k)
    // SC: O(k) auxiliary, plus O(k) output
    static int[][] kClosestOptimized(int[][] points, int k) {

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(
                (b[0] * b[0]) + (b[1] * b[1]),
                (a[0] * a[0]) + (a[1] * a[1])
            )
        );

        for (int[] point : points) {
            maxHeap.add(point);

            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] ans = new int[k][2];
        int i = 0;

        while (!maxHeap.isEmpty()) {
            ans[i++] = maxHeap.poll();
        }

        return ans;
    }

}
