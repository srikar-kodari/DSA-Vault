import java.util.Arrays;

public class LLMain {
    public static void main(String[] args) {

        // int[] arr = {1,2,3,4,5};
        // LLNode head = LLImplementation.convertArr2LL(arr);
        // //head = MiddleOfLL.middleOfLL(head);
        // head = MiddleOfLL.middleOfLL2(head);
        // LLImplementation.traverseLL(head);

        // int[] arr = {1,2,3,4,5,6,7,8,9,10,11};
        // LLNode head = LLImplementation.convertArr2LL(arr);
        // // head = ReverseLL.reverseLL(head);
        // // head = ReverseLL.reverseLL2(head);
        // // head = ReverseLL.recursiveReverseLL(head);
        // head = ReverseLL.reverseGroupK(head, 10);    // Reverse Nodes in K Group Size
        // LLImplementation.traverseLL(head);

        // int[] arr = {1,2,3,2,1};
        // LLNode head = LLImplementation.convertArr2LL(arr);
        // boolean result = PalindromeLL.isPalindromeLL(head);
        // System.out.println("Is Boolean: " + result);

        // int[] arr = {7,9,1,2,3,2,1};
        // LLNode head = LLImplementation.convertArr2LL(arr);
        // head = SortLL.sortList(head);
        // LLImplementation.traverseLL(head);

        // int[] arr = {1,2,3,4,5,6};
        // LLNode head = LLImplementation.convertArr2LL(arr);
        // head = OddEven.oddEvenLL(head);
        // LLImplementation.traverseLL(head);

        // int[] arr = {1,2,3,4,5,6};
        // LLNode head = LLImplementation.convertArr2LL(arr);
        // head = HasCycleLL.convertToCycle(head, 4);
        // System.out.println(HasCycleLL.hasCycle(head));
        // System.out.println(HasCycleLL.hasCycle2(head));

        // int[] arr = {18,6,10,3};
        // LLNode head = LLImplementation.convertArr2LL(arr);
        // head = InsertGCD.insertGCDNode(head);
        // LLImplementation.traverseLL(head);

        // int[] arr = {0,3,1,0,4,5,2,0};
        // LLNode head = LLImplementation.convertArr2LL(arr);
        // head = MergeZeroNodes.mergeZeroNodes(head);
        // LLImplementation.traverseLL(head);

        // int[] arr = {0,1,1};
        // LLNode head = LLImplementation.convertArr2LL(arr);
        // int result = BinaryToDecimal.getDecimal(head);
        // System.out.println("Binary To Decimal is: " + result);

        // int[] arr1 = {1,2,4};
        // int[] arr2 = {1,3,4};
        // LLNode list1 = LLImplementation.convertArr2LL(arr1);
        // LLNode list2 = LLImplementation.convertArr2LL(arr2);
        // // LLNode head = MergeSortedLists.mergeTwoLists(list1, list2);
        // // LLImplementation.traverseLL(head);
        // LLNode head1 = MergeSortedLists.mergeTwoLists1(list1, list2);
        // LLImplementation.traverseLL(head1);

        // int[] arr1 = {0,1,2,3,4,5,6};
        // int[] arr2 = {100,200,300};
        // // int[] arr1 = {10,1,13,6,9,5};
        // // int[] arr2 = {100,200,300};
        // LLNode list1 = LLImplementation.convertArr2LL(arr1);
        // LLNode list2 = LLImplementation.convertArr2LL(arr2);
        // // LLNode head = MergeInBetweenLL.mergeInBetweenLL(list1, 2, 5, list2);
        // // LLNode head = MergeInBetweenLL.mergeInBetweenLLOptimized(list1, 2, 5, list2);
        // LLNode head = MergeInBetweenLL.mergeInBetweenLL2(list1, 2, 5, list2);
        // LLImplementation.traverseLL(head);

        // int[] arr = {4,2,2,3};
        // LLNode head = LLImplementation.convertArr2LL(arr);
        // int result = MaxTwinSumLL.maxTwinSum(head);
        // System.out.println("Max Twin Sum Is: " + result);

        // int[] arr = {1,2,3,4,5,6,7,8,9,10};
        // LLNode head = LLImplementation.convertArr2LL(arr);
        // head = null;
        // LLNode[] result = SplitLL.splitList(head, 3);
        // String[] printable = new String[result.length];
        // for (int i = 0; i < result.length; i++) {
        //     printable[i] = LLImplementation.llToString(result[i]);
        // }
        // System.out.println("Result is: " + Arrays.toString(printable));

        // int[] arr = {1};
        // int[] arr1 = {1,2,3,4,54,1,3,4,1,1,2};
        // LLNode head = LLImplementation.convertArr2LL(arr1);
        // head = DeleteArrayNodes.deleteArray(head, arr);
        // LLImplementation.traverseLL(head);

        // int[] arr = {1,2,3,4,5,6,7,8,9};
        // LLNode head = LLImplementation.convertArr2LL(arr);
        // head = RemoveNthNode.removeNthFromEnd(head, 3);
        // LLImplementation.traverseLL(head);

        // // int[] arr1 = {2,4,3};
        // // int[] arr2 = {5,6,4};
        // // int[] arr1 = {9,9,9,9,9,9,9};
        // // int[] arr2 = {9,9,9,9};
        // // int[] arr1 = {9};
        // // int[] arr2 = {1,9,9,9,9,9,9,9,9,9};
        // int[] arr1 = {5,6,4};
        // int[] arr2 = {3};
        // LLNode l1 = LLImplementation.convertArr2LL(arr1);
        // LLNode l2 = LLImplementation.convertArr2LL(arr2);
        // // LLNode head = AddTwoNumbers.addTwoNumbers(l1, l2);
        // LLNode head = AddTwoNumbers.addTwoNumbers1(l1, l2);
        // LLImplementation.traverseLL(head);

        int[] arr = {5,3,1,2,5,1,2};
        LLNode head = LLImplementation.convertArr2LL(arr);
        int[] result = CriticalPoints.nodesBetweenCriticalPoints(head);
        System.out.println("Result is: " + Arrays.toString(result));

        


        








        



    }
}
