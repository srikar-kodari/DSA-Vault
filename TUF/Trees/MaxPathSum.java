public class MaxPathSum {
    public static void main(String[] args) {

        TreeNode root = new TreeNode(-10);

        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int ans = maxPathSum(root);
        System.out.println("Answer is: " + ans);

    }

    static int maxPathSum(TreeNode root) {

        int[] maxSum = new int[] { Integer.MIN_VALUE };
        maxPathSumUtil(root, maxSum);
        return maxSum[0];
    }

    static int maxPathSumUtil(TreeNode root, int[] maxSum) {

        if (root == null) {
            return 0;
        }

        int left = Math.max(0, maxPathSumUtil(root.left, maxSum));
        int right = Math.max(0, maxPathSumUtil(root.right, maxSum));

        maxSum[0] = Math.max(maxSum[0], root.data + left + right);

        return root.data + Math.max(left, right);
    }
    
}
