import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public static void main(String[] args) {

        // int[] arr = {2,7,4,1,8,1};
        int[] arr = {2,2};
        int ans = lastStoneWeight(arr);
        System.out.println(ans);
        
    }

    static int lastStoneWeight(int[] stones) {

        if(stones.length == 1) return stones[0];

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int n : stones) maxHeap.add(n);

        while (maxHeap.size() >= 2) {
            
            int y = maxHeap.poll();
            int x = maxHeap.poll();

            if(x == y) continue;
            else maxHeap.add(y-x);
        }

        return (maxHeap.peek() != null) ? maxHeap.peek() : 0;
    }

}
