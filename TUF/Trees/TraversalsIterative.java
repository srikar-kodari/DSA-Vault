import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class TraversalsIterative {      // Uses Stack
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


        // Iterative Traversals

        List<Integer> preOrder = iterativePreOrder(root);
        System.out.println("PreOrder: " + preOrder);

        List<Integer> inOrder = iterativeInOrder(root);
        System.out.println("InOrder: " + inOrder);

        List<Integer> inOrderOptimized = iterativeInOrderOptimized(root);
        System.out.println("InOrder-Optimized: " + inOrderOptimized);

        List<Integer> postOrder = iterativePostOrder(root);
        System.out.println("PostOrder: " + postOrder);

        List<Integer> postOrder1 = iterativePostOrder1(root);
        System.out.println("PostOrder-1: " + postOrder1);
        
    }

    // PreOrder: Root - Left - Right
    // TC: O(n), visits every node exactly once.
    // SC: O(h), stack holds nodes along current path (worst O(n), balanced O(log n)).
    static List<Integer> iterativePreOrder(TreeNode root) {     // Root - Left - Right

        if(root == null) return new ArrayList<>();

        List<Integer> preOrder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            preOrder.add(root.data);
            if(root.right != null) stack.push(root.right);
            if(root.left != null) stack.push(root.left);
        }

        return preOrder;
    }

    // InOrder: Left - Root - Right
    // TC: O(n), visits every node exactly once.
    // SC: O(h), stack holds nodes along current path (worst O(n), balanced O(log n)).
    static List<Integer> iterativeInOrder(TreeNode root) {      // Left - Root - Right

        if(root == null) return new ArrayList<>();

        List<Integer> inOrder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;
        while (true) {

            if(node != null) {
                stack.push(node);
                node = node.left;
            }
            else {
                if(stack.isEmpty()) break;

                node = stack.pop();
                inOrder.add(node.data);
                node = node.right;
            }
        }

        return inOrder;
    }

    // InOrder (Optimized): Left - Root - Right
    // TC: O(n), visits every node exactly once.
    // SC: O(h), stack holds nodes along current path (worst O(n), balanced O(log n)).
    static List<Integer> iterativeInOrderOptimized(TreeNode root) {

        List<Integer> inOrder = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {

            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            inOrder.add(curr.data);
            curr = curr.right;
        }

        return inOrder;
    }

    // PostOrder: Left - Right - Root (2 stacks)
    // TC: O(n), each node is pushed/popped a constant number of times.
    // SC: O(n), two stacks together can store all nodes in the worst case.
    static List<Integer> iterativePostOrder(TreeNode root) {    // Left - Right - Root

        if(root == null) return new ArrayList<>();

        Stack<TreeNode> stack1 = new Stack<>();     // Uses 2 Stacks
        Stack<TreeNode> stack2 = new Stack<>();
        List<Integer> postOrder = new ArrayList<>();

        stack1.push(root);
        while (!stack1.isEmpty()) {
            root = stack1.pop();
            stack2.add(root);
            if(root.left != null) stack1.push(root.left);
            if(root.right != null) stack1.push(root.right);
        }

        while (!stack2.isEmpty()) {
            postOrder.add(stack2.pop().data);
        }

        return postOrder;
    }

    // PostOrder: Left - Right - Root (1 stack)
    // TC: O(n), each node is processed exactly once.
    // SC: O(h), one stack tracks the path (worst O(n), balanced O(log n)).
    static List<Integer> iterativePostOrder1(TreeNode root) {   // Uses 1 Stack

        if(root == null) return new ArrayList<>();

        List<Integer> postOrder1 = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            
            if (current != null) {
                stack.push(current);
                current = current.left;
            }
            else {
                TreeNode temp = stack.peek().right;
                if(temp == null) {

                    temp = stack.peek();
                    stack.pop();
                    postOrder1.add(temp.data);

                    while (!stack.isEmpty() && temp == stack.peek().right) {
                        temp = stack.peek();
                        stack.pop();
                        postOrder1.add(temp.data);
                    }
                }
                else {
                    current = temp;
                }
            }
        }

        return postOrder1;
    }

}
