import java.util.PriorityQueue;
import java.util.Arrays;

public class RelativeRanks {
    public static void main(String[] args) {

        int[] score = {10, 3, 8, 9, 4};
        String[] ans = findRelativeRanks(score);
        System.out.println(Arrays.toString(ans));

    }

    static String[] findRelativeRanks(int[] score) {

        int n = score.length;
        String[] result = new String[n];

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
            (a,b) -> Integer.compare(score[b], score[a])
        );

        for (int i = 0; i < n; i++) maxHeap.add(i);

        int rank = 1;

        while (!maxHeap.isEmpty()) {
            int index = maxHeap.poll();

            if (rank == 1) result[index] = "Gold Medal";
            else if (rank == 2) result[index] = "Silver Medal";
            else if (rank == 3) result[index] = "Bronze Medal";
            else result[index] = String.valueOf(rank);

            rank++;
        }

        return result;
    }
}
