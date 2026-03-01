import java.util.Arrays;

public class BSMain {
    public static void main(String[] args) {
        
        System.out.println("Practicing Binary Search..");

        // int[] arr = {2,4,5,6,12,12,16,19,24,24,24,40};    //For Demo Problems

        // int result = BSDemo.bsDemoIterative(arr, arr.length, 24);
        // System.out.println("Iterative Result is: " + result);

        // int res = BSDemo.bsDemoRecursive(arr, 0, arr.length-1, 24);
        // System.out.println("Recursive Result is: " + res);

        // System.out.println("Inbuilt Method Result is: " + Arrays.binarySearch(arr, 4));

        // int lowerBound = BSDemo.lowerBound(arr, arr.length, 18);
        // System.out.println("Lower bound is: " + lowerBound);
        // int lBound = BSDemo.lBound(arr, 12);
        // System.out.println("Simple lower bound is: " + lBound);

        // int upperBound = BSDemo.upperBound(arr, arr.length, 45);
        // System.out.println("Upper bound is: " + upperBound);
        // int uBound = BSDemo.uBound(arr, 2);
        // System.out.println("Simple upper bound is: " + uBound);

        //System.out.println("Ceil is: " + BSDemo.ceil(arr, 100));
        //System.out.println("Floor is: " + BSDemo.floor(arr, 39));

        //System.out.println("First & Last Occurence is: " + Arrays.toString(FLOccurence.flOccurence(arr, 2424)));

        // int[] arr = {6,7,8,0,2,3,4,5};    //For Rotated Sorted Problems
        // //int[] arr = {6,7,8,4,5,6,6,6};  //Contains Duplicates
        // System.out.println("Target of rotated sorted array is: " + RotatedSorted.rotatedSorted1(arr, arr.length, 4));
        // System.out.println("Target of rotated duplicates is: " + RotatedSorted.rotatedSorted2(arr, arr.length, 5));
        // System.out.println("Min element in rotated sorted arrays is: " + RotatedSorted.minNumRotated(arr, arr.length));

        // int[] arr = {1,1,2,2,3,3,4,4,5,5,6,6,7};
        // System.out.println("Single element in rotated sorted array is: " + SingleAndPeak.singleElement(arr, arr.length));
        //int[] arr = {1,2,3,4,2,6,7,8,2,1};
        //System.out.println("Peak element is: " + SingleAndPeak.peakElement(arr, arr.length));

        //System.out.println("Square root of N is: " + SquareRoot.squareRoot(26));
        //System.out.println("Nth root of Number is: " + SquareRoot.nthRoot(125, 3));

        //int[] arr = {3,6,7,11};
        //int[] arr = {30,11,23,4,20};
        //System.out.println("Speed of eating bananas is: " + KokoBananas.kokoBananas(arr, 8));

        //int[] arr = {1,10,3,10,2};
        //int[] arr = {7,7,7,7,7,7,7};
        //System.out.println("Min no of days to make M bouquets is: " + MinBouquets.minDays(arr, 2, 3));

        //int[] arr = {0,0,1,1};
        //System.out.println(MatrixRowMaxOne.firstOccurance(arr, 1));
        //int[][] matrix = {{0,1,1},{1,1,1},{0,1,1},{0,1,1}};
        //System.out.println("Matrix Row which has max 1's is: " + MatrixRowMaxOne.maxOne(matrix, 4, 3));

        //int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        //System.out.println("Element search in 2D Matrix is: " + SearchInMatrix.searchMatrix1(matrix, 2));
        //System.out.println("Optimized element search is: " + SearchInMatrix.searchMatrix2(matrix, 4));
        //int[][] matrix = {{-5,2,4},{-3,3,6}};   //For searchMatrix3, every row & column is sorted in increasing order
        //System.out.println("searchMatrix3 is: " + Arrays.toString(SearchInMatrix.searchMatrix3(matrix, 3)));

        int[][] matrix = {{1,4},{3,2}};
        System.out.println("Index of peak element in matrix is: " + Arrays.toString(MatrixPeak.findPeak(matrix)));

    }
}
