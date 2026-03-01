import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Feb1 {

    public static int minimumCost(int[] nums) {

        int result = nums[0];

        List<Integer> list = new ArrayList<>();

        for(int i=1; i<nums.length; i++) list.add(nums[i]);

        Collections.sort(list);
        System.out.println(list);
        result += list.get(0) + list.get(1);

        return result;
    }
    
}
