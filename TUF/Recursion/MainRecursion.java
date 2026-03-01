import java.util.Stack;

public class MainRecursion {
    public static void main(String[] args) {

        // // double power = Power.myPower(2, 10);
        // double power = Power.myPowerSafe(2, 10);
        // System.out.println(power);

        // long goodNumbers = GoodNumbers.countGoodNumbers(50);
        // System.out.println(goodNumbers);

        // Stack<Integer> stack = new Stack<>();
        // stack.push(90);
        // stack.push(50);
        // stack.push(40);
        // stack.push(80);
        // stack.push(24);
        // stack.push(10);
        // // stack = StackSort.sortStack(stack);
        // stack = StackSort.sortStack2(stack);
        // System.out.println(stack);

        Stack<Integer> stack = new Stack<>();
        stack.push(90);
        stack.push(50);
        stack.push(40);
        stack.push(80);
        stack.push(24);
        stack.push(10);
        stack = StackReverse.stackReverse(stack);
        System.out.println(stack);

        

        
    }
}
