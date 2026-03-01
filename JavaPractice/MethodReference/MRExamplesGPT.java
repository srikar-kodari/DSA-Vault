import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

// Custom method reference examples: beginner to advanced
public class MRExamplesGPT {

    public static void main(String[] args) {
        System.out.println("Beginner: static method reference");
        beginnerStaticMethodReference();

        System.out.println("\nBeginner: instance method reference on a specific object");
        beginnerInstanceMethodOnObject();

        System.out.println("\nIntermediate: instance method of arbitrary object type");
        intermediateInstanceMethodOfType();

        System.out.println("\nIntermediate: constructor references (no-arg and arg)");
        intermediateConstructorReferences();

        System.out.println("\nIntermediate: custom functional interface with method refs");
        intermediateCustomFunctionalInterface();

        System.out.println("\nAdvanced: combining method references with streams");
        advancedStreamsWithMethodReferences();

        System.out.println("\nAdvanced: factory/registry pattern with method references");
        advancedFactoryWithMethodReferences();
    }

    // ========= BEGINNER EXAMPLES =========

    // Static method reference: ClassName::staticMethod
    private static void beginnerStaticMethodReference() {
        // Lambda form:
        // Consumer<String> printer = s -> MRExamplesGPT.printUpper(s);

        // Method reference form:
        Consumer<String> printer = MRExamplesGPT::printUpper;

        printer.accept("hello");
        printer.accept("method references");

        // Output:
        // HELLO
        // METHOD REFERENCES
    }

    private static void printUpper(String input) {
        System.out.println(input.toUpperCase());
    }

    // Instance method reference on a specific object: instanceRef::instanceMethod
    private static void beginnerInstanceMethodOnObject() {
        MessagePrinter printer = new MessagePrinter();

        // Lambda form:
        // Runnable r = () -> printer.printGreeting();

        // Method reference:
        Runnable r = printer::printGreeting;

        r.run();

        // Output:
        // Hello from MessagePrinter
    }

    // ========= INTERMEDIATE EXAMPLES =========

    // Instance method of arbitrary object of a given type: ClassName::instanceMethod
    private static void intermediateInstanceMethodOfType() {
        List<String> names = new ArrayList<>(Arrays.asList("srikar", "john", "alice", "bob"));

        // Sort ignoring case using method reference
        names.sort(String::compareToIgnoreCase); // Comparator<String> via method reference

        // Print each name using System.out::println
        names.forEach(System.out::println);

        // Possible output:
        // alice
        // bob
        // john
        // srikar
    }

    // Constructor references
    private static void intermediateConstructorReferences() {
        // No-arg constructor reference
        Supplier<Person> defaultPersonSupplier = Person::new;
        Person p1 = defaultPersonSupplier.get();
        System.out.println(p1);

        // Constructor with arguments using Function and BiFunction
        Function<String, Person> namedPersonCreator = Person::new;
        Person p2 = namedPersonCreator.apply("Srikar");
        System.out.println(p2);

        BiFunction<String, Integer, Employee> employeeCreator = Employee::new;
        Employee e1 = employeeCreator.apply("Alice", 1001);
        System.out.println(e1);

        // Possible output:
        // Person{name='Default'}
        // Person{name='Srikar'}
        // Employee{name='Alice', id=1001}
    }

    // Custom functional interface + method references
    private static void intermediateCustomFunctionalInterface() {
        // Lambda form
        StringOperation trimOp = s -> s.trim();

        // Static method reference
        StringOperation upperOp = MRExamplesGPT::toUpper;

        // Instance method reference on a specific object
        SurroundOperation surroundOp = new SurroundOperationImpl("[", "]")::surround;

        String input = "  hello  ";

        System.out.println("Trimmed: '" + trimOp.apply(input) + "'");
        System.out.println("Upper:   '" + upperOp.apply(input) + "'");
        System.out.println("Wrapped: '" + surroundOp.apply("world") + "'");

        // Output:
        // Trimmed: 'hello'
        // Upper:   '  HELLO  '
        // Wrapped: '[world]'
    }

    private static String toUpper(String s) {
        return s.toUpperCase();
    }

    // ========= ADVANCED EXAMPLES =========

    // Method references with streams and composed operations
    private static void advancedStreamsWithMethodReferences() {
        List<String> words = Arrays.asList("java", "method", "reference", "lambda");

        List<Integer> result =
                words.stream()
                     .map(String::trim)           // instance method of arbitrary type
                     .map(String::toUpperCase)    // instance method of arbitrary type
                     .map(String::length)         // instance method of arbitrary type
                     .sorted(Comparator.reverseOrder()) // static method / natural order helper
                     .toList();

        System.out.println(result);

        // Possible output:
        // [9, 8, 6, 4]
    }

    // Simple factory/registry-like pattern with constructor references
    private static void advancedFactoryWithMethodReferences() {
        // We can store constructor references (or other method refs) in a list and call them later
        List<Supplier<Shape>> shapeSuppliers = Arrays.asList(
                Circle::new,   // calls new Circle()
                Square::new,   // calls new Square()
                Triangle::new  // calls new Triangle()
        );

        List<Shape> shapes = shapeSuppliers.stream()
                                           .map(Supplier::get)   // call each constructor
                                           .toList();

        // Now use another method reference to call a method on each element
        shapes.forEach(Shape::draw);

        // Possible output:
        // Drawing Circle
        // Drawing Square
        // Drawing Triangle
    }
}

// ===== Helper types for examples =====

// For beginner instance method example
class MessagePrinter {
    void printGreeting() {
        System.out.println("Hello from MessagePrinter");
    }
}

// For constructor reference examples
class Person {
    private final String name;

    Person() {
        this.name = "Default";
    }

    Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "'}";
    }
}

class Employee {
    private final String name;
    private final int id;

    Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', id=" + id + "}";
    }
}

// Custom functional interfaces
@FunctionalInterface
interface StringOperation {
    String apply(String s);
}

@FunctionalInterface
interface SurroundOperation {
    String apply(String s);
}

class SurroundOperationImpl {
    private final String prefix;
    private final String suffix;

    SurroundOperationImpl(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    String surround(String s) {
        return prefix + s + suffix;
    }
}

class SurroundOperationAlternative {
    private final String left;
    private final String right;

    SurroundOperationAlternative(String left, String right) {
        this.left = left;
        this.right = right;
    }

    String surround(String s) {
        return left + s + right;
    }
}

class SurroundOperationHolder {
    private final String left;
    private final String right;

    SurroundOperationHolder(String left, String right) {
        this.left = left;
        this.right = right;
    }

    String surround(String s) {
        return left + s + right;
    }
}

class SurroundOperationWrapper {
    private final String prefix;
    private final String suffix;

    SurroundOperationWrapper(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    String surround(String s) {
        return prefix + s + suffix;
    }
}

class SurroundOperationService {
    private final String prefix;
    private final String suffix;

    SurroundOperationService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    String surround(String s) {
        return prefix + s + suffix;
    }
}

// Shapes for factory example
interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Square");
    }
}

class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Triangle");
    }
}
