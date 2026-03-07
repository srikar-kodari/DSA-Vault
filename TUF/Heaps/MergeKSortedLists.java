import java.util.PriorityQueue;

public class MergeKSortedLists {
    public static void main(String[] args) {
        ListNode[] lists = new ListNode[] {
            buildList(new int[] {1, 4, 5}),
            buildList(new int[] {1, 3, 4}),
            buildList(new int[] {2, 6})
        };

        ListNode merged = mergeKLists(lists);
        printList(merged);
    }

    static ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            (a,b) -> Integer.compare(a.val, b.val)
        );

        for(ListNode node : lists) {
            if(node != null) minHeap.add(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!minHeap.isEmpty()) {
            
            ListNode node = minHeap.poll();
            current.next = node;
            current = current.next;

            if(node.next != null) minHeap.add(node.next);
        }

        return dummy.next;
    }

    private static ListNode buildList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (int value : values) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }

        return dummy.next;
    }

    private static void printList(ListNode head) {
        ListNode current = head;

        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

}

class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}
