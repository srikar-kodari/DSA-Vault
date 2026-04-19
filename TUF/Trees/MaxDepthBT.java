public class MaxDepthBT {
    public static void main(String[] args) {

        // Sample non-perfect tree with 4 levels.
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(8);
        root.right.left.right = new TreeNode(9);

        int depth = maxDepth(root);
        System.out.println("Max Depth: " + depth);

    }

    static int maxDepth(TreeNode root) {

        if(root == null) return 0;

        int lh = maxDepth(root.left);
        int rh = maxDepth(root.right);

        return 1 + Math.max(lh, rh);
    }

}
