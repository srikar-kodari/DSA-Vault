import java.util.ArrayList;

public class StreamsPractice {

    public static void streamsPractice() {

        // ArrayList<Integer> list = new ArrayList<>();
        // list.add(10);
        // list.add(2);
        // list.add(3);
        // list.add(44);
        // list.add(25);
        // list.add(16);
        // list.add(2);

        // Stream<Integer> st = list.stream().filter(n -> n > 3)
        //                                   .peek(n -> System.out.println("After Filter: " + n))
        //                                   .map(n -> -1*n)
        //                                   .peek(n -> System.out.println("After Map: " + n))
        //                                   .sorted()
        //                                   .peek(n -> System.out.println("After Sorting: " + n));

        // st.count();


        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);
        list.add(70);
        list.add(80);
        list.add(90);
        list.add(100);

        // Sequential Stream
        long sequentialStartTime = System.currentTimeMillis();
        list.stream().map(n -> n*n).forEach(n -> System.out.println(n));
        System.out.println("Sequential Time Taken: " + (System.currentTimeMillis() - sequentialStartTime));

        // Parallel Stream
        long parallelStartTime = System.currentTimeMillis();
        list.parallelStream().map(n -> n*n).forEach(n -> System.out.println(n));
        System.out.println("Parallel Time Taken: " + (System.currentTimeMillis() - parallelStartTime));


        






    }
    
}
