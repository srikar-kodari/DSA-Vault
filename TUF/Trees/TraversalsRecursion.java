import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TraversalsRecursion {
    public static void main(String[] args) {

        // Sample non-perfect tree with 4 levels.

        // Level 1
        TreeNode root = new TreeNode(1);

        // Level 2
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        // Level 3
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        // Level 4
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(8);
        root.right.left.right = new TreeNode(9);

        /*
         * Tree structure:
         *          1
         *        /   \
         *       2     3
         *      / \   /
         *     4   5 6
         *    / \     \
         *   7   8     9
         */


        // Traversals

        System.out.print("PreOrder: ");
        preOrder(root);

        System.out.println();

        System.out.print("InOrder: ");
        inOrder(root);

        System.out.println();

        System.out.print("PostOrder: ");
        postOrder(root);

        System.out.println();

        System.out.println("BFS");
        List<List<Integer>> bfs = levelOrder(root);
        System.out.println(bfs);

    }

    // PreOrder: Root - Left - Right
    // TC: O(n), visits every node exactly once.
    // SC: O(h), recursion stack where h is tree height (worst O(n), best O(log n) for balanced tree).
    static void preOrder(TreeNode root) {
        
        if(root == null) return;

        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    // InOrder: Left - Root - Right
    // TC: O(n), visits every node exactly once.
    // SC: O(h), recursion stack where h is tree height (worst O(n), best O(log n) for balanced tree).
    static void inOrder(TreeNode root) {

        if(root == null) return;

        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    // PostOrder: Left - Right - Root
    // TC: O(n), visits every node exactly once.
    // SC: O(h), recursion stack where h is tree height (worst O(n), best O(log n) for balanced tree).
    static void postOrder(TreeNode root) {

        if(root == null) return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    // LevelOrder (BFS)
    // TC: O(n), each node is processed exactly once.
    // SC: O(w), queue stores up to tree width w (worst case O(n)).
    static List<List<Integer>> levelOrder(TreeNode root) {  // BFS

        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> wrapList = new ArrayList<>();

        if(root == null) return wrapList;

        queue.offer(root);

        while (!queue.isEmpty()) {

            int levelNum = queue.size();
            List<Integer> subList = new ArrayList<>();

            for(int i=0; i<levelNum; i++) {
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                subList.add(queue.poll().data);
            }
            wrapList.add(subList);
        }

        return wrapList;
    }

}
