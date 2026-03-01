## Lambda Expressions and Method References in Java

### 1. Functional Interfaces (Foundation)

- **Functional interface**: An interface with **exactly one abstract method**.  
  Examples (from `java.util.function`):
  - **`Runnable`**: `void run()`
  - **`Consumer<T>`**: `void accept(T t)`
  - **`Supplier<T>`**: `T get()`
  - **`Function<T,R>`**: `R apply(T t)`
  - **`Predicate<T>`**: `boolean test(T t)`

Custom functional interface:

```java
@FunctionalInterface
interface MyCalculator {
    int operate(int a, int b);
}
```

All lambda expressions and method references are assigned to some functional interface.

---

### 2. Lambda Expressions

#### 2.1 Syntax

General forms:

```java
(parameters) -> expression
(parameters) -> { statements; }
```

Examples:

```java
// No parameters, no return value
Runnable r = () -> System.out.println("Hello from lambda");

// One parameter, inferred type
Consumer<String> printer = s -> System.out.println(s);

// Multiple parameters, block body with return
MyCalculator add = (a, b) -> {
    int sum = a + b;
    return sum;
};

// Multiple parameters, single expression (no braces, no return keyword)
MyCalculator multiply = (a, b) -> a * b;
```

**Type inference**: parameter types can usually be omitted because the compiler infers them from the target functional interface.

#### 2.2 Using Lambdas with Collections

```java
import java.util.*;

public class LambdaCollectionExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("Srikar", "John", "Alice", "Bob"));

        // Sort using lambda (Comparator)
        names.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        System.out.println(names);

        // forEach with lambda
        names.forEach(name -> System.out.println("Name: " + name));

        // Remove if a condition holds
        names.removeIf(name -> name.startsWith("J"));
        System.out.println(names);
    }
}
```

#### 2.3 Using Lambdas with Streams

```java
import java.util.List;

public class StreamLambdaDemo {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        int sumOfSquaresOfEven =
            numbers.stream()
                   .filter(n -> n % 2 == 0)       // Predicate<Integer>
                   .map(n -> n * n)              // Function<Integer, Integer>
                   .reduce(0, (a, b) -> a + b);  // BinaryOperator<Integer>

        System.out.println(sumOfSquaresOfEven);
    }
}
```

#### 2.4 Variable Capture and "Effectively Final"

Lambdas can use variables from the enclosing scope, but they must be **final or effectively final** (not modified after assignment).

```java
public class CaptureExample {
    public static void main(String[] args) {
        int base = 10; // effectively final (never changed)

        MyCalculator adder = (a, b) -> a + b + base;

        System.out.println(adder.operate(3, 4)); // 17

        // base++; // ERROR if uncommented: variable used in lambda should be final or effectively final
    }
}
```

#### 2.5 `this` in Lambdas

Inside a lambda, `this` refers to the **enclosing instance**, not the lambda itself (unlike anonymous inner classes where `this` refers to the anonymous class).

```java
public class ThisExample {
    private String name = "Outer";

    public void demo() {
        Runnable r = () -> {
            System.out.println(this.name); // refers to ThisExample.this
        };
        r.run();
    }
}
```

---

### 3. Method References

A **method reference** is a shorthand for a lambda that only calls an existing method.

General forms:

1. **Static method**: `ClassName::staticMethod`
2. **Instance method of a specific object**: `instanceRef::instanceMethod`
3. **Instance method of an arbitrary object of a given type**: `ClassName::instanceMethod`
4. **Constructor reference**: `ClassName::new`

The compiler chooses the correct overload by matching the functional interface method signature (parameter types and return type) to a compatible method.

---

### 3.1 Static Method Reference

Instead of:

```java
Function<String, Integer> parse = s -> Integer.parseInt(s);
```

Write:

```java
Function<String, Integer> parse = Integer::parseInt;
```

Example with streams:

```java
import java.util.List;

public class StaticMethodRefExample {
    public static void main(String[] args) {
        List<String> numbers = List.of("1", "2", "3");

        int sum = numbers.stream()
                         .map(Integer::parseInt) // static method reference
                         .reduce(0, Integer::sum); // static method reference

        System.out.println(sum);
    }
}
```

---

### 3.2 Instance Method of a Particular Object

```java
import java.util.function.Consumer;

public class InstanceMethodRefExample {
    public static void main(String[] args) {
        Printer printer = new Printer();

        // Lambda
        Consumer<String> c1 = msg -> printer.print(msg);

        // Method reference
        Consumer<String> c2 = printer::print;

        c2.accept("Hello via method reference");
    }
}

class Printer {
    void print(String msg) {
        System.out.println("Printer: " + msg);
    }
}
```

---

### 3.3 Instance Method of an Arbitrary Object of a Given Type

Form: `ClassName::instanceMethod`. The **first parameter** of the functional interface becomes the target object (`this`).

Example: sort strings ignoring case:

```java
import java.util.*;

public class ArbitraryInstanceMethodRef {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("Srikar", "John", "Alice", "Bob"));

        // Lambda
        names.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

        // Method reference (instance method of arbitrary String)
        names.sort(String::compareToIgnoreCase);

        names.forEach(System.out::println);
    }
}
```

Here, `String::compareToIgnoreCase` matches `Comparator<String>`’s `int compare(String a, String b)`  
and corresponds to `a.compareToIgnoreCase(b)` under the hood.

---

### 3.4 Constructor References

Form: `ClassName::new`. Used when the functional interface method is supposed to **create and return a new object**.

Simple example:

```java
import java.util.function.Supplier;

public class ConstructorRefExample {
    public static void main(String[] args) {
        // Lambda
        Supplier<Person> s1 = () -> new Person();

        // Constructor reference
        Supplier<Person> s2 = Person::new;

        Person p = s2.get();
        System.out.println(p);
    }
}

class Person {
    private String name;

    public Person() {
        this.name = "Default";
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "'}";
    }
}
```

With parameters:

```java
import java.util.function.Function;

public class ConstructorRefWithArgs {
    public static void main(String[] args) {
        // Functional interface: R apply(T t)
        Function<String, Person> personCreator = Person::new;

        Person p = personCreator.apply("Srikar");
        System.out.println(p);
    }
}
```

---

### 4. Lambdas vs Method References

- **Lambda**
  - More flexible.
  - Can have multiple statements, local variables, conditions, loops, etc.
  - Example:  
    ```java
    list.forEach(s -> {
        String upper = s.toUpperCase();
        System.out.println(upper);
    });
    ```

- **Method reference**
  - Shorter and more readable **when** you are just forwarding parameters to an existing method.
  - Example:  
    ```java
    list.forEach(System.out::println);
    ```

**Rule of thumb**:  
Use a **method reference** when the lambda body is just a call to an existing method.  
Use a **lambda** when you need additional logic.

---

### 5. Custom Functional Interface Example (Both Lambda and Method Reference)

```java
@FunctionalInterface
interface StringOperation {
    String apply(String s);
}

public class CustomFunctionalExample {

    public static String toUpper(String s) {
        return s.toUpperCase();
    }

    public String addBrackets(String s) {
        return "[" + s + "]";
    }

    public static void main(String[] args) {
        // Lambda
        StringOperation op1 = s -> s.trim();

        // Static method reference
        StringOperation op2 = CustomFunctionalExample::toUpper;

        CustomFunctionalExample obj = new CustomFunctionalExample();

        // Instance method reference on a particular object
        StringOperation op3 = obj::addBrackets;

        String input = "  hello  ";

        System.out.println(op1.apply(input));   // "hello"
        System.out.println(op2.apply(input));   // "  HELLO  "
        System.out.println(op3.apply("world")); // "[world]"
    }
}
```

---

### 6. Common Points and Pitfalls

- **Functional interface restriction**: exactly one abstract method (but can have default and static methods).
- **Effectively final variables**: variables captured from the enclosing scope inside a lambda must be final or effectively final.
- **`this` in lambda**: refers to the enclosing class instance, not a separate anonymous object.
- **Overloading**: multiple overloaded methods taking different functional interfaces can confuse type inference; sometimes you need an explicit cast.

These notes and examples should give you a solid reference for lambda expressions and method references in Java.

