public class CriticalPoints {

    public static int[] nodesBetweenCriticalPoints(LLNode head) {

        if (head == null || head.next == null || head.next.next == null) {
            return new int[] {-1, -1};
        }

        LLNode prev = head;
        LLNode curr = head.next;
        int position = 2;

        int firstCritical = -1;
        int lastCritical = -1;
        int minDistance = Integer.MAX_VALUE;

        while (curr.next != null) {

            boolean isCritical =
                (curr.data > prev.data && curr.data > curr.next.data) ||
                (curr.data < prev.data && curr.data < curr.next.data);

            if (isCritical) {
                if (firstCritical == -1) {
                    firstCritical = position;
                }
                
                else {
                    minDistance = Math.min(minDistance, position - lastCritical);
                }

                lastCritical = position;
            }

            prev = curr;
            curr = curr.next;
            position++;
        }

        if (firstCritical == -1 || firstCritical == lastCritical) {
            return new int[] {-1, -1};
        }

        return new int[] {minDistance, lastCritical - firstCritical};
    }

}
