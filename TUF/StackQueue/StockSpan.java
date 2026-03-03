import java.util.Arrays;
import java.util.Stack;

public class StockSpan {
    
    Stack<int[]> stack;
    int index;

    public StockSpan() {
        stack = new Stack<>();
        index = -1;
    }

    // TC (per next call, amortized): O(1), TC (per next call, worst-case): O(m)
    // TC (for m next calls): O(m), SC (total storage): O(m), SC (extra): O(1)
    public int next(int price) {

        index += 1;

        while ((!stack.isEmpty()) && stack.peek()[0] <= price) {
            stack.pop();
        }

        int ans = index - ((stack.isEmpty()) ? -1 : stack.peek()[1]);

        stack.push(new int[] {price, index});

        return ans;
    }

    public static void main(String[] args) {

        StockSpan obj = new StockSpan();

        obj.next(10);
        obj.next(20);
        obj.next(30);
        obj.next(5);

        System.out.println(Arrays.deepToString(obj.stack.toArray()));
        
    }

}
