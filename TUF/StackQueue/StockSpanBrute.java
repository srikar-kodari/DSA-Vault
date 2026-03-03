import java.util.ArrayList;
import java.util.List;

public class StockSpanBrute {
    
    List<Integer> list;

    public StockSpanBrute() {

        list = new ArrayList<>();
    }

    // TC (per next call, worst-case): O(n)
    // TC (for m next calls): O(m^2), SC (total storage): O(m), SC (extra): O(1)
    public int next(int val) {

        list.add(val);
        int count = 1;
        for(int i = list.size()-2; i>=0; i--) {

            if(list.get(i) <= val) count++;
            else break;
        }

        return count;
    }

    public static void main(String[] args) {

        StockSpanBrute obj = new StockSpanBrute();

        System.out.println(obj.next(10));
        System.out.println(obj.next(20));
        System.out.println(obj.next(5));

        System.out.println(obj.list);
        
    }
}
