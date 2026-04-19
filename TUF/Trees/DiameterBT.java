public class DiameterBT {
    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.right.left = new TreeNode(4);
        root.right.left.left = new TreeNode(5);
        root.right.left.left.left = new TreeNode(9);

        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(7);
        root.right.right.right.right = new TreeNode(8);

        /*
         * Tree structure:
         *         1
         *        / \
         *       2   3
         *          / \
         *         4   6
         *        /     \
         *       5       7
         *      /         \
         *     9           8
         */

        int maxDiameter = diameterBT(root);
        System.out.println("Answer is: " + maxDiameter);

    }

    static int diameterBT(TreeNode root) {

        int[] max = new int[1]; // max[0] stores the best diameter in edges
        diameterLogic(root, max);

        return max[0];
    }

    private static int diameterLogic(TreeNode node, int[] max) {

        if(node == null) return 0;

        int lh = diameterLogic(node.left, max);
        int rh = diameterLogic(node.right, max);

        max[0] = Math.max(max[0], lh + rh);

        return 1 + Math.max(lh, rh);
    }
    
}
