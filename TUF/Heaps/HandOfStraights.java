import java.util.HashMap;
import java.util.PriorityQueue;

public class HandOfStraights {
    public static void main(String[] args) {
        
        int[] arr = {1,2,3,6,2,3,4,7,8};
        boolean ans = isNStraightHand(arr, 3);
        System.out.println(ans);

    }

    static boolean isNStraightHand(int[] hand, int groupSize) {

        if(hand.length % groupSize != 0) return false;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int n : hand) map.put(n, map.getOrDefault(n, 0) + 1);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(map.keySet());

        while (!minHeap.isEmpty()) {
            
            int start = minHeap.peek();

            for(int i = start; i < start + groupSize; i++) {

                if(!map.containsKey(i)) return false;

                map.put(i, map.get(i)-1);

                if(map.get(i) == 0) {
                    if(i != minHeap.peek()) return false;

                    minHeap.poll();
                }
            }
        }

        return true;
    }

}
