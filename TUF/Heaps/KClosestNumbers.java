import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class KClosestNumbers {
    public static void main(String[] args) {

        // int[] arr = {1,2,3,4,5};
        // List<Integer> ans = kClosestNumbers(arr, 3, 4);
        // System.out.println(ans);

        // int[] arr = {1,1,2,3,4,5};
        // List<Integer> ans = kClosestNumbers(arr, -1, 4);
        // System.out.println(ans);

        int[] arr = {5,6,7,8,9};
        List<Integer> list = kClosestNumbers1(arr, 7, 3);
        System.out.println(list);

    }

    static List<Integer> kClosestNumbers(int[] arr, int ele, int k) {

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? Integer.compare(b[0], a[0]) : Integer.compare(b[1], a[1])
        );

        int n = arr.length;
        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0; i<n; i++) {

            maxHeap.add(new int[] {Math.abs(ele - arr[i]), arr[i]});

            if(maxHeap.size() > k) maxHeap.poll();
        }

        while (!maxHeap.isEmpty()) {
            list.add(maxHeap.poll()[1]);
        }

        Collections.sort(list);
        return list;
    }

    // Just Changed The Comparator
    static List<Integer> kClosestNumbers1(int[] arr, int ele, int k) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
            (a,b) -> {
                int d1 = Math.abs(a - ele);
                int d2 = Math.abs(b - ele);

                return (d1 != d2) ? Integer.compare(d2, d1) : Integer.compare(b, a);
            });

        int n = arr.length;
        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0; i<n; i++) {

            maxHeap.add(arr[i]);

            if(maxHeap.size() > k) maxHeap.poll();
        }

        while (!maxHeap.isEmpty()) list.add(maxHeap.poll());

        Collections.sort(list);
        return list;
    }

}
