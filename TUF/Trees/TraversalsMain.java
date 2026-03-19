import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TraversalsMain {

    static class Pair {
        TreeNode node;
        int state;

        Pair(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

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


        List<List<Integer>> traversals = allTraversalsSingleStack(root);

        System.out.println("PreOrder: " + traversals.get(0));
        System.out.println("InOrder: " + traversals.get(1));
        System.out.println("PostOrder: " + traversals.get(2));
        
    }

    // PreOrder + InOrder + PostOrder in one traversal using one stack.
    // TC: O(n), each node is handled in constant work across three states.
    // SC: O(n), stack can grow to O(h) and output lists store all n values.
    // Auxiliary SC (excluding output): O(h), worst O(n), balanced O(log n).
    // One pass with one stack: state 1=pre, 2=in, 3=post.
    static List<List<Integer>> allTraversalsSingleStack(TreeNode root) {

        List<Integer> preOrder = new ArrayList<>();
        List<Integer> inOrder = new ArrayList<>();
        List<Integer> postOrder = new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>();
        result.add(preOrder);
        result.add(inOrder);
        result.add(postOrder);

        if (root == null) {
            return result;
        }

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));

        while (!stack.isEmpty()) {
            Pair current = stack.pop();

            if (current.state == 1) {
                preOrder.add(current.node.data);
                current.state = 2;
                stack.push(current);

                if (current.node.left != null) {
                    stack.push(new Pair(current.node.left, 1));
                }
            }
            else if (current.state == 2) {
                inOrder.add(current.node.data);
                current.state = 3;
                stack.push(current);

                if (current.node.right != null) {
                    stack.push(new Pair(current.node.right, 1));
                }
            }
            else {
                postOrder.add(current.node.data);
            }
        }

        return result;
    }
    
}
