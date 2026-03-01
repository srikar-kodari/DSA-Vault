public class SumSubsetXor {
    public static void main(String[] args) {

        int[] arr = {1, 3};
        int ans = subsetXORSum(arr);
        System.out.println("Sum of all Subset XOR totals is: " + ans);
    }

    static int subsetXORSum(int[] nums) {
        
        return xorSum(0, 0, nums);
    }

    static int xorSum(int index, int xor, int[] nums) {
        if (index == nums.length) {
            return xor;
        }

        int take = xorSum(index + 1, xor ^ nums[index], nums);
        int skip = xorSum(index + 1, xor, nums);
        return take + skip;
    }
}
