public class BalancedBT {
    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(8);
        root.right.left.right = new TreeNode(9);

        boolean result = isBalancedBT(root);
        System.out.println(result);

        boolean ans = isBalancedBTOptimized(root);
        System.out.println(ans);

    }

    static boolean isBalancedBT(TreeNode root) {

        if(root == null) return true;

        int leftHeight = MaxDepthBT.maxDepth(root.left);
        int rightHeight = MaxDepthBT.maxDepth(root.right);

        if(Math.abs(rightHeight - leftHeight) > 1) return false;

        boolean left = isBalancedBT(root.left);
        boolean right = isBalancedBT(root.right);

        if(!left || !right) return false;

        return true;
    }

    // Optimized Balanced BT check
    // TC: O(n), each node is visited once.
    // SC: O(h), recursion stack (worst O(n), balanced O(log n)).
    static boolean isBalancedBTOptimized(TreeNode root) {
        return heightOrUnbalanced(root) != -1;
    }

    // Returns subtree height if balanced, else -1 as a fail signal.
    static int heightOrUnbalanced(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = heightOrUnbalanced(root.left);
        if (leftHeight == -1) return -1;

        int rightHeight = heightOrUnbalanced(root.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        return 1 + Math.max(leftHeight, rightHeight);
    }
    
}
