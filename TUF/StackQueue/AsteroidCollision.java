import java.util.ArrayList;
import java.util.Arrays;

public class AsteroidCollision {    // I LIKED THIS PROBLEM
    public static void main(String[] args) {

        int[] asteroid = {4,7,1,1,2,-3,-7,17,15,-16};
        int[] ans = asteroidCollision(asteroid);
        System.out.println(Arrays.toString(ans));

    }

    static int[] asteroidCollision(int[] arr) {

        int n = arr.length;
        ArrayList<Integer> stack = new ArrayList<>();    // Functions as Stack

        for(int i=0; i<n; i++) {

            if(arr[i] > 0) stack.add(arr[i]);

            else {
                while (!stack.isEmpty() && stack.getLast() > 0 && stack.getLast() < Math.abs(arr[i])) {
                    
                    stack.removeLast();
                }

                if(!stack.isEmpty() && stack.getLast() == Math.abs(arr[i])) {
                    stack.removeLast();
                }

                else if(stack.isEmpty() || stack.getLast() < 0) {
                    stack.add(arr[i]);
                }

            }
        }

        return stack.stream().mapToInt(Integer::intValue).toArray();
    }

}
