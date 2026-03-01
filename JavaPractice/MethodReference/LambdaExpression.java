import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaExpression {
    public static void main(String[] args) {
        
        List<String> names = Arrays.asList("Srikar", "Rama", "Ratnam", "Sandeep");

        System.out.println("Normal Printing: " + names);

        System.out.println("Break..");
        for(String s : names) System.out.println(s);

        System.out.println("Break..");
        Consumer<String> con = new Consumer<String>() { // Consumer is a Functional Interface.
            public void accept(String s) {              // So we can use Lambda Expression.
                System.out.println(s);
            }
        };
        names.forEach(con);

        System.out.println("Break..");
        Consumer<String> con1 = s1 -> System.out.println(s1);   // Remove new & method name
        names.forEach(con1);

        System.out.println("Break..");
        names.forEach(str -> System.out.println(str));
        System.out.println();


        // METHOD REFERENCE
        names.forEach(System.out::println);




    }
}
