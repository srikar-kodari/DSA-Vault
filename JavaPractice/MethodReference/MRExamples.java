import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MRExamples { // Method Reference Operator Examples
    public static void main(String[] args) {
        
        // List<String> names = List.of("Ram", "Sita", "Arjun");
        // names.forEach(System.out::println);    //names.forEach(s -> System.out.println(s));

        // List<String> words = List.of("java", "spring", "docker");
        // // words.forEach(s -> System.out.println(s.toUpperCase()));
        // words.stream().map(String::toUpperCase).forEach(System.out::println);

        // List<String> items = Arrays.asList("pen", "notebook", "book");
        // // Collections.sort(items, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        // // System.out.println(items);
        // // items.sort((s1,s2) -> Integer.compare(s1.length(), s2.length()));
        // // System.out.println(items);
        // items.sort(Comparator.comparingInt(String::length));    // REVISE
        // System.out.println(items);

        // List<String> nums = Arrays.asList("10", "20", "30");
        // // nums.forEach(s -> System.out.println(Integer.valueOf(s)));
        // // nums.stream().map(Integer::valueOf).forEach(System.out::println);
        // System.out.println(nums);
        // List<Integer> intList = nums.stream().map(Integer::parseInt).collect(Collectors.toList());
        // System.out.println(intList);

        List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'z');
        //chars.forEach(s -> System.out.println(Integer.valueOf(s)));
        List<Integer> intList = chars.stream().map(Integer::valueOf).collect(Collectors.toList());
        System.out.println(intList);







    }
}
