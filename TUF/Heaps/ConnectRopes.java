import java.util.PriorityQueue;

public class ConnectRopes {
    public static void main(String[] args) {

        // int[] arr = {1,2,3,4,5};
        int[] arr = {4,3,2,6};
        int minCost = minCostRopes(arr);
        System.out.println(minCost);
        
    }

    static int minCostRopes(int[] arr) {

        if (arr == null || arr.length <= 1) return 0;

        int totalCost = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int rope : arr) minHeap.add(rope);

        while (minHeap.size() >= 2) {
            
            int sum = minHeap.poll() + minHeap.poll();

            totalCost += sum;

            minHeap.add(sum);
        }

        return totalCost;
    }

}
