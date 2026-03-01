# Complete Guide to Java Collections Framework

## Table of Contents
1. Introduction to Java Collections Framework
2. Collection Interface - Core Methods
3. List Interface and Implementations
4. Set Interface and Implementations
5. Queue Interface and Implementations
6. Map Interface and Implementations
7. Utility Classes: Collections and Arrays
8. Comparable vs Comparator
9. Iterators: Iterator vs ListIterator
10. Concurrent Collections
11. Special Collections: EnumSet and EnumMap
12. Fail-Fast vs Fail-Safe Iterators

---

## 1. Introduction to Java Collections Framework

The Java Collections Framework (JCF) is a unified architecture for representing and manipulating collections of objects. It provides interfaces, implementations, and algorithms for efficient data management.

### Core Hierarchy

```
Collection (Interface)
├── List (ordered, allows duplicates)
│   ├── ArrayList
│   ├── LinkedList
│   ├── Vector (legacy, synchronized)
│   └── Stack (extends Vector)
├── Set (unordered, no duplicates)
│   ├── HashSet
│   ├── LinkedHashSet
│   ├── TreeSet
│   └── EnumSet
└── Queue (FIFO/FIFO ordering)
    ├── PriorityQueue
    ├── Deque (Double Ended Queue)
    │   └── ArrayDeque
    └── LinkedList (implements both List and Deque)

Map (Separate Hierarchy, key-value pairs)
├── HashMap
├── LinkedHashMap
├── TreeMap
├── EnumMap
├── WeakHashMap
├── ConcurrentHashMap
└── Hashtable (legacy, synchronized)
```

### Key Benefits

- **Unified API**: Consistent methods across different collection types
- **Resizable Collections**: Automatic memory management
- **Algorithm Support**: Sorting, searching, shuffling built-in
- **Thread-Safe Options**: Synchronized and concurrent collections
- **Performance Optimized**: Different implementations for different use cases

---

## 2. Collection Interface - Core Methods

The `Collection` interface is the root of the collection hierarchy. It defines common operations that all collections should support.

### Core Methods

```java
// Method signatures in Collection interface
public interface Collection<E> {
    // Modification Operations
    boolean add(E e);
    boolean remove(Object o);
    boolean addAll(Collection<? extends E> c);
    boolean removeAll(Collection<?> c);
    boolean retainAll(Collection<?> c);
    void clear();
    
    // Query Operations
    int size();
    boolean isEmpty();
    boolean contains(Object o);
    boolean containsAll(Collection<?> c);
    
    // Iterator
    Iterator<E> iterator();
    
    // Conversion
    Object[] toArray();
    <T> T[] toArray(T[] a);
    
    // Comparison and hashing
    boolean equals(Object o);
    int hashCode();
}
```

### Common Collection Methods with Examples

```java
import java.util.*;

public class CollectionMethodsExample {
    public static void main(String[] args) {
        // Adding elements
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        System.out.println("After adding: " + fruits); 
        // Output: [Apple, Banana, Orange]
        
        // Checking size and emptiness
        System.out.println("Size: " + fruits.size()); // Output: 3
        System.out.println("Is empty: " + fruits.isEmpty()); // Output: false
        
        // Checking containment
        System.out.println("Contains 'Banana': " + fruits.contains("Banana")); // Output: true
        
        // Removing elements
        fruits.remove("Banana");
        System.out.println("After removing Banana: " + fruits); 
        // Output: [Apple, Orange]
        
        // Adding multiple elements (addAll)
        List<String> moreFruits = Arrays.asList("Mango", "Papaya");
        fruits.addAll(moreFruits);
        System.out.println("After addAll: " + fruits); 
        // Output: [Apple, Orange, Mango, Papaya]
        
        // Clear all elements
        fruits.clear();
        System.out.println("After clear: " + fruits); 
        // Output: []
        System.out.println("Is empty: " + fruits.isEmpty()); // Output: true
    }
}
```

---

## 3. List Interface and Implementations

The `List` interface represents an ordered collection (sequence) where duplicates are allowed. Elements are accessed by index.

### 3.1 ArrayList

**Purpose**: Resizable array implementation. Best for frequent access and minimal insertion/deletion in the middle.

**Internal Implementation**: 
- Backed by a dynamic array `Object[]`
- Grows by ~50% when capacity is exceeded
- Maintains insertion order

**Time Complexities**:
```
Access by index:     O(1) - direct array access
Search (indexOf):    O(n) - linear scan
Add at end:          O(1) amortized
Add at position:     O(n) - requires shifting
Remove from end:     O(1)
Remove from middle:  O(n) - requires shifting
```

**Code Example**:

```java
import java.util.*;

public class ArrayListExample {
    public static void main(String[] args) {
        // Creating ArrayList
        List<Integer> numbers = new ArrayList<>();
        
        // Adding elements
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);
        
        // Accessing elements
        System.out.println("First element: " + numbers.get(0)); // Output: 10
        System.out.println("Element at index 2: " + numbers.get(2)); // Output: 30
        
        // Finding index
        System.out.println("Index of 30: " + numbers.indexOf(30)); // Output: 2
        
        // Adding at specific position (shifts elements)
        numbers.add(2, 25);
        System.out.println("After adding 25 at index 2: " + numbers); 
        // Output: [10, 20, 25, 30, 40, 50]
        
        // Removing element
        numbers.remove(Integer.valueOf(25)); // Removes first occurrence
        System.out.println("After removing 25: " + numbers); 
        // Output: [10, 20, 30, 40, 50]
        
        // Removing by index
        numbers.remove(1); // Removes element at index 1 (20)
        System.out.println("After removing index 1: " + numbers); 
        // Output: [10, 30, 40, 50]
        
        // Updating element
        numbers.set(0, 15);
        System.out.println("After set(0, 15): " + numbers); 
        // Output: [15, 30, 40, 50]
        
        // Sublist operations
        List<Integer> subList = numbers.subList(1, 3);
        System.out.println("Sublist from index 1 to 3: " + subList); 
        // Output: [30, 40]
        
        // Iteration
        System.out.println("Iterating:");
        for (Integer num : numbers) {
            System.out.print(num + " "); // Output: 15 30 40 50
        }
        System.out.println();
        
        // Using ListIterator for bidirectional iteration
        ListIterator<Integer> iterator = numbers.listIterator();
        System.out.println("Forward iteration:");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " "); // Output: 15 30 40 50
        }
        System.out.println();
        
        System.out.println("Backward iteration:");
        while (iterator.hasPrevious()) {
            System.out.print(iterator.previous() + " "); // Output: 50 40 30 15
        }
    }
}
```

### 3.2 LinkedList

**Purpose**: Doubly-linked list implementation. Best for frequent insertion/deletion at beginning or end, or when iterator operations dominate.

**Internal Implementation**:
- Doubly linked nodes: `Node { E value, Node prev, Node next }`
- Each element maintains references to previous and next nodes
- Can be used as List or Deque

**Time Complexities**:
```
Access by index:     O(n) - traversal required
Search (indexOf):    O(n) - linear scan
Add at beginning:    O(1) - direct link
Add at end:          O(1) - direct link
Add at position:     O(n) - traversal then link
Remove from head:    O(1) - direct unlink
Remove from tail:    O(1) - direct unlink
Remove from middle:  O(n) - traversal then unlink
```

**Code Example**:

```java
import java.util.*;

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<String> names = new LinkedList<>();
        
        // Adding elements
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        System.out.println("Initial list: " + names); 
        // Output: [Alice, Bob, Charlie]
        
        // Add at specific positions
        names.addFirst("Zara"); // Add at beginning
        System.out.println("After addFirst('Zara'): " + names); 
        // Output: [Zara, Alice, Bob, Charlie]
        
        names.addLast("David"); // Add at end
        System.out.println("After addLast('David'): " + names); 
        // Output: [Zara, Alice, Bob, Charlie, David]
        
        names.add(2, "Eve");
        System.out.println("After add(2, 'Eve'): " + names); 
        // Output: [Zara, Alice, Eve, Bob, Charlie, David]
        
        // Accessing elements
        System.out.println("getFirst(): " + names.getFirst()); // Output: Zara
        System.out.println("getLast(): " + names.getLast()); // Output: David
        System.out.println("get(2): " + names.get(2)); // Output: Eve
        
        // Removing elements
        names.removeFirst();
        System.out.println("After removeFirst(): " + names); 
        // Output: [Alice, Eve, Bob, Charlie, David]
        
        names.removeLast();
        System.out.println("After removeLast(): " + names); 
        // Output: [Alice, Eve, Bob, Charlie]
        
        // Using LinkedList as Deque
        Deque<String> deque = new LinkedList<>();
        deque.push("X"); // Add to front
        deque.push("Y");
        deque.offer("Z"); // Add to rear
        System.out.println("Deque after push and offer: " + deque); 
        // Output: [Y, X, Z]
        
        System.out.println("pop(): " + deque.pop()); // Remove and return front: Y
        System.out.println("After pop: " + deque); 
        // Output: [X, Z]
        
        System.out.println("poll(): " + deque.poll()); // Remove and return front: X
        System.out.println("After poll: " + deque); 
        // Output: [Z]
    }
}
```

### 3.3 Vector (Legacy)

**Purpose**: Synchronized version of ArrayList. Obsolete - use ArrayList with Collections.synchronizedList() or CopyOnWriteArrayList.

**Characteristics**:
- Thread-safe (all methods synchronized)
- Legacy class from Java 1.0
- Performance overhead due to synchronization

**Code Example**:

```java
import java.util.*;

public class VectorExample {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        
        // Adding elements
        vector.add(1);
        vector.add(2);
        vector.add(3);
        vector.add(4);
        vector.add(5);
        System.out.println("Vector: " + vector); 
        // Output: [1, 2, 3, 4, 5]
        
        // All operations are synchronized
        System.out.println("Size: " + vector.size()); // Output: 5
        System.out.println("Capacity: " + vector.capacity()); // Output: 10
        
        // Vector-specific methods
        vector.insertElementAt(99, 2);
        System.out.println("After insertElementAt(99, 2): " + vector); 
        // Output: [1, 2, 99, 3, 4, 5]
        
        vector.removeElementAt(2);
        System.out.println("After removeElementAt(2): " + vector); 
        // Output: [1, 2, 3, 4, 5]
        
        // Not recommended - use ArrayList instead
    }
}
```

### 3.4 Stack

**Purpose**: LIFO (Last In First Out) data structure. Extends Vector.

**Key Methods**:
- `push(E item)` - adds element to top
- `pop()` - removes and returns top element
- `peek()` - returns top element without removing
- `search(Object o)` - returns position from top

**Code Example**:

```java
import java.util.*;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        
        // Push elements
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        System.out.println("Stack after pushes: " + stack); 
        // Output: [10, 20, 30, 40]
        
        // Peek (view top without removing)
        System.out.println("Peek: " + stack.peek()); // Output: 40
        System.out.println("Stack after peek: " + stack); 
        // Output: [10, 20, 30, 40] (unchanged)
        
        // Pop (remove and return top)
        System.out.println("Pop: " + stack.pop()); // Output: 40
        System.out.println("Stack after pop: " + stack); 
        // Output: [10, 20, 30]
        
        // Search from top (returns 1-based index)
        System.out.println("Search for 20: " + stack.search(20)); // Output: 2
        System.out.println("Search for 10: " + stack.search(10)); // Output: 3
        System.out.println("Search for 100: " + stack.search(100)); // Output: -1
        
        // Check if empty
        System.out.println("Is empty: " + stack.isEmpty()); // Output: false
        
        // Practical example: Balanced parentheses
        String expression = "(){}[]";
        System.out.println("Is balanced: " + isBalanced(expression)); // Output: true
    }
    
    static boolean isBalanced(String expr) {
        Stack<Character> stack = new Stack<>();
        for (char c : expr.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') || 
                    (c == '}' && top != '{') || 
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
```

---

## 4. Set Interface and Implementations

The `Set` interface represents an unordered collection with no duplicate elements. It's based on the mathematical set concept.

### 4.1 HashSet

**Purpose**: Unordered set with no duplicates. Best for general-purpose use when order doesn't matter.

**Internal Implementation**:
- Backed by HashMap (stores elements as keys, dummy value)
- Hash table with separate chaining for collisions
- Average O(1) operations, O(n) worst case

**Time Complexities**:
```
Add:      O(1) average, O(n) worst
Remove:   O(1) average, O(n) worst
Contains: O(1) average, O(n) worst
Size:     O(1)
```

**Code Example**:

```java
import java.util.*;

public class HashSetExample {
    public static void main(String[] args) {
        Set<String> colors = new HashSet<>();
        
        // Adding elements
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Yellow");
        System.out.println("Colors: " + colors); 
        // Output: [Blue, Red, Yellow, Green] (order may vary)
        
        // Attempting to add duplicate
        boolean added = colors.add("Red");
        System.out.println("Added 'Red': " + added); // Output: false
        System.out.println("Colors after adding duplicate: " + colors); 
        // Output: [Blue, Red, Yellow, Green] (unchanged)
        
        // Checking containment
        System.out.println("Contains 'Red': " + colors.contains("Red")); // Output: true
        System.out.println("Contains 'Purple': " + colors.contains("Purple")); // Output: false
        
        // Removing elements
        colors.remove("Green");
        System.out.println("After removing 'Green': " + colors); 
        // Output: [Blue, Red, Yellow]
        
        // Iteration
        System.out.println("Iterating through set:");
        for (String color : colors) {
            System.out.print(color + " "); // Order is unpredictable
        }
        System.out.println();
        
        // Set operations
        Set<String> moreColors = new HashSet<>(Arrays.asList("Blue", "Purple", "Orange"));
        
        // Union (addAll)
        Set<String> union = new HashSet<>(colors);
        union.addAll(moreColors);
        System.out.println("Union: " + union); 
        // Output: [Blue, Red, Yellow, Purple, Orange]
        
        // Intersection (retainAll)
        Set<String> intersection = new HashSet<>(colors);
        intersection.retainAll(moreColors);
        System.out.println("Intersection: " + intersection); // Output: [Blue]
        
        // Difference (removeAll)
        Set<String> difference = new HashSet<>(colors);
        difference.removeAll(moreColors);
        System.out.println("Difference (colors - moreColors): " + difference); 
        // Output: [Red, Yellow]
    }
}
```

### 4.2 LinkedHashSet

**Purpose**: Maintains insertion order while providing set semantics. Best when you need set functionality with predictable iteration order.

**Internal Implementation**:
- Backed by LinkedHashMap
- Doubly linked list maintains insertion order
- Slightly slower than HashSet due to link maintenance

**Time Complexities**:
```
Add:      O(1) average
Remove:   O(1) average
Contains: O(1) average
Iteration: O(n) in insertion order
```

**Code Example**:

```java
import java.util.*;

public class LinkedHashSetExample {
    public static void main(String[] args) {
        Set<String> linkedSet = new LinkedHashSet<>();
        
        // Adding elements - insertion order is preserved
        linkedSet.add("Java");
        linkedSet.add("Python");
        linkedSet.add("C++");
        linkedSet.add("Go");
        linkedSet.add("Rust");
        System.out.println("LinkedHashSet: " + linkedSet); 
        // Output: [Java, Python, C++, Go, Rust] (insertion order)
        
        // Attempting to add duplicate
        linkedSet.add("Java");
        System.out.println("After adding 'Java' again: " + linkedSet); 
        // Output: [Java, Python, C++, Go, Rust] (unchanged, insertion order maintained)
        
        // Iteration maintains insertion order
        System.out.println("Iterating (maintains order):");
        for (String lang : linkedSet) {
            System.out.print(lang + " "); 
            // Output: Java Python C++ Go Rust
        }
        System.out.println();
        
        // Removing and reinserting changes order
        linkedSet.remove("Python");
        System.out.println("After removing Python: " + linkedSet); 
        // Output: [Java, C++, Go, Rust]
        
        linkedSet.add("Python");
        System.out.println("After re-adding Python: " + linkedSet); 
        // Output: [Java, C++, Go, Rust, Python] (added at end)
    }
}
```

### 4.3 TreeSet

**Purpose**: Sorted set based on natural ordering or custom comparator. Best when you need elements in sorted order.

**Internal Implementation**:
- Backed by TreeMap (Red-Black Tree)
- Self-balancing binary search tree
- Maintains sorted order (natural or custom)

**Time Complexities**:
```
Add:      O(log n)
Remove:   O(log n)
Contains: O(log n)
Iteration: O(n) in sorted order
```

**Code Example**:

```java
import java.util.*;

public class TreeSetExample {
    public static void main(String[] args) {
        // Natural ordering (ascending)
        Set<Integer> numbers = new TreeSet<>();
        numbers.add(50);
        numbers.add(20);
        numbers.add(80);
        numbers.add(10);
        numbers.add(60);
        System.out.println("TreeSet (natural order): " + numbers); 
        // Output: [10, 20, 50, 60, 80] (sorted)
        
        // Adding duplicate
        numbers.add(50);
        System.out.println("After adding 50 again: " + numbers); 
        // Output: [10, 20, 50, 60, 80] (unchanged)
        
        // Custom comparator (reverse order)
        Set<Integer> reverseNumbers = new TreeSet<>(Collections.reverseOrder());
        reverseNumbers.addAll(numbers);
        System.out.println("TreeSet (reverse order): " + reverseNumbers); 
        // Output: [80, 60, 50, 20, 10]
        
        // Navigation methods
        TreeSet<Integer> treeSet = new TreeSet<>(numbers);
        System.out.println("First element: " + treeSet.first()); // Output: 10
        System.out.println("Last element: " + treeSet.last()); // Output: 80
        System.out.println("Lower than 50: " + treeSet.lower(50)); // Output: 20
        System.out.println("Higher than 50: " + treeSet.higher(50)); // Output: 60
        System.out.println("Floor of 55: " + treeSet.floor(55)); // Output: 50
        System.out.println("Ceiling of 55: " + treeSet.ceiling(55)); // Output: 60
        
        // Range views
        Set<Integer> headSet = treeSet.headSet(50); // Elements < 50
        System.out.println("Elements < 50: " + headSet); // Output: [10, 20]
        
        Set<Integer> tailSet = treeSet.tailSet(50); // Elements >= 50
        System.out.println("Elements >= 50: " + tailSet); // Output: [50, 60, 80]
        
        Set<Integer> subSet = treeSet.subSet(20, 60); // Elements >= 20 and < 60
        System.out.println("Elements [20, 60): " + subSet); // Output: [20, 50]
        
        // Custom objects with TreeSet
        Set<Person> people = new TreeSet<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));
        System.out.println("People (sorted by age): " + people);
    }
}

class Person implements Comparable<Person> {
    String name;
    int age;
    
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }
    
    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}
```

### 4.4 EnumSet

**Purpose**: Optimized set for enum types. Extremely efficient (uses bit vectors internally).

**Internal Implementation**:
- Uses bit vector (long[] array)
- One bit per enum constant
- Extremely fast and memory efficient

**Code Example**:

```java
import java.util.*;

public class EnumSetExample {
    enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }
    
    public static void main(String[] args) {
        // Creating EnumSet with specific elements
        EnumSet<Day> weekdays = EnumSet.of(
            Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, 
            Day.THURSDAY, Day.FRIDAY
        );
        System.out.println("Weekdays: " + weekdays);
        // Output: [MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY]
        
        // Creating all elements
        EnumSet<Day> allDays = EnumSet.allOf(Day.class);
        System.out.println("All days: " + allDays);
        // Output: [MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY]
        
        // Creating range
        EnumSet<Day> midWeek = EnumSet.range(Day.TUESDAY, Day.THURSDAY);
        System.out.println("Mid-week days: " + midWeek);
        // Output: [TUESDAY, WEDNESDAY, THURSDAY]
        
        // Creating complement
        EnumSet<Day> weekend = EnumSet.complementOf(weekdays);
        System.out.println("Weekend: " + weekend);
        // Output: [SATURDAY, SUNDAY]
        
        // Set operations
        EnumSet<Day> temp = EnumSet.copyOf(weekdays);
        temp.retainAll(midWeek);
        System.out.println("Intersection (weekdays ∩ midWeek): " + temp);
        // Output: [TUESDAY, WEDNESDAY, THURSDAY]
    }
}
```

---

## 5. Queue Interface and Implementations

The `Queue` interface represents a collection designed for holding elements prior to processing, typically in FIFO (First In First Out) order.

### Queue Methods

```java
// Queue-specific methods
boolean add(E e);           // Add, throw exception if fails
boolean offer(E e);         // Add, return false if fails

E remove();                 // Remove, throw exception if empty
E poll();                   // Remove, return null if empty

E element();                // View head, throw exception if empty
E peek();                   // View head, return null if empty
```

### 5.1 PriorityQueue

**Purpose**: Queue where elements are ordered by priority rather than insertion order. Uses a min-heap by default.

**Internal Implementation**:
- Binary min-heap (array-based)
- Complete binary tree stored in array
- Parent at index i, children at 2i+1 and 2i+2

**Time Complexities**:
```
Offer (add):  O(log n)
Poll (remove): O(log n)
Peek:         O(1)
Remove:       O(n)
```

**Code Example**:

```java
import java.util.*;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // Min-heap (default)
        Queue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(50);
        minHeap.offer(30);
        minHeap.offer(70);
        minHeap.offer(20);
        minHeap.offer(40);
        
        System.out.println("MinHeap: " + minHeap);
        System.out.println("Peek (min): " + minHeap.peek()); // Output: 20
        
        System.out.println("Polling from min-heap:");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " "); 
            // Output: 20 30 40 50 70 (sorted order)
        }
        System.out.println();
        
        // Max-heap (using custom comparator)
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.offer(50);
        maxHeap.offer(30);
        maxHeap.offer(70);
        maxHeap.offer(20);
        maxHeap.offer(40);
        
        System.out.println("MaxHeap: " + maxHeap);
        System.out.println("Peek (max): " + maxHeap.peek()); // Output: 70
        
        System.out.println("Polling from max-heap:");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " "); 
            // Output: 70 50 40 30 20 (reverse sorted)
        }
        System.out.println();
        
        // Custom objects with priority
        Queue<Task> taskQueue = new PriorityQueue<>();
        taskQueue.offer(new Task("Email", 3));
        taskQueue.offer(new Task("Bug Fix", 1));
        taskQueue.offer(new Task("Documentation", 2));
        taskQueue.offer(new Task("Code Review", 1));
        
        System.out.println("Processing tasks by priority:");
        while (!taskQueue.isEmpty()) {
            System.out.println(taskQueue.poll());
        }
    }
}

class Task implements Comparable<Task> {
    String name;
    int priority; // Lower value = higher priority
    
    Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
    
    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }
    
    @Override
    public String toString() {
        return "[" + name + " | Priority: " + priority + "]";
    }
}
```

### 5.2 Deque (Double Ended Queue)

**Purpose**: Queue allowing insertion/removal at both ends. Can be used as both Queue and Stack.

**Key Methods**:
```java
// Add operations
void addFirst(E e);    // Throw exception if fails
void addLast(E e);
boolean offerFirst(E e); // Return false if fails
boolean offerLast(E e);

// Remove operations
E removeFirst();       // Throw exception if empty
E removeLast();
E pollFirst();         // Return null if empty
E pollLast();

// Access operations
E getFirst();          // Throw exception if empty
E getLast();
E peekFirst();         // Return null if empty
E peekLast();

// Stack-like operations
void push(E e);        // Add to front
E pop();               // Remove from front
```

### 5.3 ArrayDeque

**Purpose**: Resizable array implementation of Deque. Efficient for both Queue and Stack operations.

**Internal Implementation**:
- Circular resizable array
- Two pointers (head and tail) for efficient operations at both ends
- No capacity restrictions

**Time Complexities**:
```
AddFirst/AddLast:     O(1) amortized
RemoveFirst/RemoveLast: O(1)
GetFirst/GetLast:     O(1)
```

**Code Example**:

```java
import java.util.*;

public class ArrayDequeExample {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();
        
        // Adding elements
        deque.addFirst("First");
        deque.addLast("Last");
        deque.add("Middle"); // Default addLast
        System.out.println("Deque: " + deque); 
        // Output: [First, Middle, Last]
        
        // Accessing elements
        System.out.println("getFirst(): " + deque.getFirst()); // Output: First
        System.out.println("getLast(): " + deque.getLast()); // Output: Last
        System.out.println("peekFirst(): " + deque.peekFirst()); // Output: First
        System.out.println("peekLast(): " + deque.peekLast()); // Output: Last
        
        // Removing elements
        System.out.println("removeFirst(): " + deque.removeFirst()); // Output: First
        System.out.println("After removeFirst: " + deque); 
        // Output: [Middle, Last]
        
        System.out.println("removeLast(): " + deque.removeLast()); // Output: Last
        System.out.println("After removeLast: " + deque); 
        // Output: [Middle]
        
        // Using as Stack (LIFO)
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack after pushes: " + stack); 
        // Output: [3, 2, 1]
        
        System.out.println("pop(): " + stack.pop()); // Output: 3
        System.out.println("pop(): " + stack.pop()); // Output: 2
        System.out.println("Remaining: " + stack); // Output: [1]
        
        // Using as Queue (FIFO)
        Deque<String> queue = new ArrayDeque<>();
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");
        System.out.println("Queue: " + queue); 
        // Output: [First, Second, Third]
        
        System.out.println("poll(): " + queue.poll()); // Output: First
        System.out.println("poll(): " + queue.poll()); // Output: Second
        System.out.println("Remaining: " + queue); // Output: [Third]
    }
}
```

---

## 6. Map Interface and Implementations

The `Map` interface represents a collection of key-value pairs where each key maps to exactly one value.

### Core Map Methods

```java
public interface Map<K, V> {
    // Basic operations
    V put(K key, V value);
    V get(Object key);
    V remove(Object key);
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    
    // View operations
    Set<K> keySet();
    Collection<V> values();
    Set<Entry<K, V>> entrySet();
    
    // Bulk operations
    void putAll(Map<? extends K, ? extends V> m);
    void clear();
    
    // Query operations
    int size();
    boolean isEmpty();
}
```

### 6.1 HashMap

**Purpose**: General-purpose map implementation with no ordering. Best for most use cases requiring key-value mapping.

**Internal Implementation**:
- Hash table with separate chaining
- Array of buckets (Entry nodes)
- Uses hashCode() and equals() for key lookup
- Load factor (default 0.75) triggers resizing

**Time Complexities**:
```
Get:      O(1) average, O(n) worst
Put:      O(1) average, O(n) worst
Remove:   O(1) average, O(n) worst
Iteration: O(n + capacity)
```

**Code Example**:

```java
import java.util.*;

public class HashMapExample {
    public static void main(String[] args) {
        // Basic operations
        Map<String, Integer> ages = new HashMap<>();
        
        // Putting values
        ages.put("Alice", 30);
        ages.put("Bob", 25);
        ages.put("Charlie", 35);
        ages.put("David", 28);
        System.out.println("HashMap: " + ages); 
        // Output: {Alice=30, Bob=25, Charlie=35, David=28} (order varies)
        
        // Getting values
        System.out.println("Alice's age: " + ages.get("Alice")); // Output: 30
        System.out.println("Frank's age: " + ages.get("Frank")); // Output: null
        
        // Getting with default value
        System.out.println("Frank's age (default 0): " + ages.getOrDefault("Frank", 0)); 
        // Output: 0
        
        // Updating value
        ages.put("Alice", 31);
        System.out.println("After updating Alice's age: " + ages.get("Alice")); 
        // Output: 31
        
        // Checking containment
        System.out.println("Contains 'Bob': " + ages.containsKey("Bob")); // Output: true
        System.out.println("Contains age 30: " + ages.containsValue(30)); // Output: false
        System.out.println("Contains age 35: " + ages.containsValue(35)); // Output: true
        
        // Removing entries
        ages.remove("David");
        System.out.println("After removing David: " + ages); 
        // Output: {Alice=31, Bob=25, Charlie=35}
        
        // Iteration through keySet
        System.out.println("Keys:");
        for (String name : ages.keySet()) {
            System.out.print(name + " "); // Output: Alice Bob Charlie (order varies)
        }
        System.out.println();
        
        // Iteration through values
        System.out.println("Values:");
        for (Integer age : ages.values()) {
            System.out.print(age + " "); // Output: 31 25 35 (order varies)
        }
        System.out.println();
        
        // Iteration through entrySet (most efficient)
        System.out.println("Entries:");
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
            // Output: Alice -> 31, Bob -> 25, Charlie -> 35 (order varies)
        }
        
        // putIfAbsent
        ages.putIfAbsent("Frank", 40);
        System.out.println("After putIfAbsent Frank: " + ages); 
        // Output includes Frank=40
        
        ages.putIfAbsent("Alice", 50); // Won't replace
        System.out.println("After putIfAbsent Alice: " + ages.get("Alice")); 
        // Output: 31 (unchanged)
        
        // compute and merge operations
        ages.compute("Alice", (k, v) -> v == null ? 32 : v + 1);
        System.out.println("After compute Alice: " + ages.get("Alice")); 
        // Output: 32
        
        ages.merge("George", 29, Integer::sum); // Add new entry
        System.out.println("After merge George: " + ages.get("George")); 
        // Output: 29
    }
}
```

### 6.2 LinkedHashMap

**Purpose**: HashMap that maintains insertion order (or access order). Predictable iteration order.

**Internal Implementation**:
- HashMap + doubly linked list
- Maintains insertion order by default
- Can maintain access order (LRU - Least Recently Used)

**Time Complexities**:
```
Get:      O(1) average
Put:      O(1) average
Remove:   O(1) average
Iteration: O(n) in insertion/access order
```

**Code Example**:

```java
import java.util.*;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        // Insertion order
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("Apple", 100);
        linkedMap.put("Banana", 50);
        linkedMap.put("Orange", 75);
        linkedMap.put("Mango", 120);
        System.out.println("LinkedHashMap (insertion order): " + linkedMap);
        // Output: {Apple=100, Banana=50, Orange=75, Mango=120}
        
        // Iteration maintains insertion order
        System.out.println("Iterating (insertion order):");
        for (Map.Entry<String, Integer> entry : linkedMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        // Access order LinkedHashMap (LRU cache implementation)
        Map<String, Integer> lruMap = new LinkedHashMap<String, Integer>(16, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return size() > 3; // Keep only 3 most recently used
            }
        };
        
        lruMap.put("Item1", 1);
        lruMap.put("Item2", 2);
        lruMap.put("Item3", 3);
        System.out.println("LRU Map (size 3): " + lruMap);
        // Output: {Item1=1, Item2=2, Item3=3}
        
        lruMap.get("Item1"); // Access Item1
        System.out.println("After accessing Item1: " + lruMap);
        // Output: {Item2=2, Item3=3, Item1=1} (Item1 moved to end)
        
        lruMap.put("Item4", 4); // Add new item (Item2 removed as eldest)
        System.out.println("After adding Item4: " + lruMap);
        // Output: {Item3=3, Item1=1, Item4=4} (Item2 removed)
    }
}
```

### 6.3 TreeMap

**Purpose**: Sorted map based on natural key ordering or custom comparator.

**Internal Implementation**:
- Red-Black Tree (self-balancing BST)
- Keys stored in sorted order
- Natural ordering or custom Comparator

**Time Complexities**:
```
Get:      O(log n)
Put:      O(log n)
Remove:   O(log n)
Iteration: O(n) in sorted order
```

**Code Example**:

```java
import java.util.*;

public class TreeMapExample {
    public static void main(String[] args) {
        // Natural ordering (ascending)
        Map<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(50, "Fifty");
        treeMap.put(20, "Twenty");
        treeMap.put(80, "Eighty");
        treeMap.put(10, "Ten");
        treeMap.put(60, "Sixty");
        System.out.println("TreeMap (sorted): " + treeMap);
        // Output: {10=Ten, 20=Twenty, 50=Fifty, 60=Sixty, 80=Eighty}
        
        // Iteration maintains sorted order
        System.out.println("Iterating (sorted order):");
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        // Navigation methods
        TreeMap<Integer, String> navMap = new TreeMap<>(treeMap);
        System.out.println("First key: " + navMap.firstKey()); // Output: 10
        System.out.println("Last key: " + navMap.lastKey()); // Output: 80
        System.out.println("Lower key than 50: " + navMap.lowerKey(50)); // Output: 20
        System.out.println("Higher key than 50: " + navMap.higherKey(50)); // Output: 60
        System.out.println("Floor key of 55: " + navMap.floorKey(55)); // Output: 50
        System.out.println("Ceiling key of 55: " + navMap.ceilingKey(55)); // Output: 60
        
        // Range views
        System.out.println("Head map (keys < 50): " + navMap.headMap(50));
        // Output: {10=Ten, 20=Twenty}
        
        System.out.println("Tail map (keys >= 50): " + navMap.tailMap(50));
        // Output: {50=Fifty, 60=Sixty, 80=Eighty}
        
        System.out.println("Sub map ([20, 60)): " + navMap.subMap(20, 60));
        // Output: {20=Twenty, 50=Fifty}
        
        // Custom comparator (reverse order)
        Map<Integer, String> reverseMap = new TreeMap<>(Collections.reverseOrder());
        reverseMap.put(50, "Fifty");
        reverseMap.put(20, "Twenty");
        reverseMap.put(80, "Eighty");
        reverseMap.put(10, "Ten");
        System.out.println("TreeMap (reverse order): " + reverseMap);
        // Output: {80=Eighty, 60=Sixty, 50=Fifty, 20=Twenty, 10=Ten}
    }
}
```

### 6.4 EnumMap

**Purpose**: Optimized map for enum keys. Extremely efficient (array-backed).

**Internal Implementation**:
- Array-backed (direct index using enum ordinal)
- No hashing required
- Extremely fast and memory efficient

**Code Example**:

```java
import java.util.*;

public class EnumMapExample {
    enum Season { SPRING, SUMMER, FALL, WINTER }
    
    public static void main(String[] args) {
        // Creating EnumMap
        Map<Season, String> seasonMap = new EnumMap<>(Season.class);
        
        // Putting values
        seasonMap.put(Season.SPRING, "March-May");
        seasonMap.put(Season.SUMMER, "June-August");
        seasonMap.put(Season.FALL, "September-November");
        seasonMap.put(Season.WINTER, "December-February");
        
        System.out.println("EnumMap: " + seasonMap);
        // Output: {SPRING=March-May, SUMMER=June-August, FALL=September-November, WINTER=December-February}
        
        // Accessing values
        System.out.println("Summer months: " + seasonMap.get(Season.SUMMER)); 
        // Output: June-August
        
        // Iteration maintains enum order
        System.out.println("All seasons:");
        for (Map.Entry<Season, String> entry : seasonMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        
        // EnumMap provides all entries even if not explicitly put (null values)
        Map<Season, Integer> temperatureMap = new EnumMap<>(Season.class);
        temperatureMap.put(Season.SUMMER, 35);
        temperatureMap.put(Season.WINTER, 15);
        System.out.println("Temperature map: " + temperatureMap);
        System.out.println("Spring temp: " + temperatureMap.get(Season.SPRING)); // null
    }
}
```

### 6.5 ConcurrentHashMap

**Purpose**: Thread-safe map without locking entire table. Multiple threads can read/write concurrently.

**Internal Implementation**:
- Segmented hash table (bucket array divided into segments)
- Each segment has its own lock
- Multiple threads can modify different segments simultaneously

**Code Example**:

```java
import java.util.*;
import java.util.concurrent.*;

public class ConcurrentHashMapExample {
    public static void main(String[] args) throws InterruptedException {
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        
        // Basic operations (thread-safe)
        concurrentMap.put("Task1", 100);
        concurrentMap.put("Task2", 200);
        concurrentMap.put("Task3", 300);
        
        System.out.println("Initial map: " + concurrentMap);
        
        // Concurrent read/write operations
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Reading: " + concurrentMap.get("Task1"));
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });
        
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                concurrentMap.put("Task1", 100 + i);
                System.out.println("Updated Task1 to " + (100 + i));
                try { Thread.sleep(150); } catch (InterruptedException e) {}
            }
        });
        
        reader.start();
        writer.start();
        reader.join();
        writer.join();
        
        System.out.println("Final value: " + concurrentMap.get("Task1"));
        
        // putIfAbsent (atomic operation)
        Integer previous = concurrentMap.putIfAbsent("Task4", 400);
        System.out.println("putIfAbsent Task4: " + previous); // null
        
        previous = concurrentMap.putIfAbsent("Task1", 500);
        System.out.println("putIfAbsent Task1 (exists): " + previous); // Some value
        
        // replace (atomic operation)
        boolean replaced = concurrentMap.replace("Task2", 200, 250);
        System.out.println("Replaced Task2: " + replaced); // true
        System.out.println("Task2 value: " + concurrentMap.get("Task2")); // 250
    }
}
```

---

## 7. Utility Classes: Collections and Arrays

### 7.1 Collections Utility Class

The `Collections` class provides static utility methods for operating on collections.

**Key Methods**:

```java
import java.util.*;

public class CollectionsUtilityExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(50, 20, 80, 10, 60, 30));
        
        // Sorting
        Collections.sort(numbers); // Natural order
        System.out.println("After sort: " + numbers); 
        // Output: [10, 20, 30, 50, 60, 80]
        
        // Sorting with custom comparator
        Collections.sort(numbers, Collections.reverseOrder());
        System.out.println("After reverse sort: " + numbers); 
        // Output: [80, 60, 50, 30, 20, 10]
        
        // Searching (requires sorted list)
        Collections.sort(numbers);
        int index = Collections.binarySearch(numbers, 50);
        System.out.println("Index of 50: " + index); // Output: 4
        
        // Reversing
        Collections.reverse(numbers);
        System.out.println("After reverse: " + numbers); 
        // Output: [80, 60, 50, 30, 20, 10]
        
        // Shuffling (randomizing)
        Collections.shuffle(numbers);
        System.out.println("After shuffle: " + numbers); 
        // Output: randomized order
        
        // Finding min/max
        System.out.println("Min: " + Collections.min(numbers)); 
        System.out.println("Max: " + Collections.max(numbers)); 
        
        // Filling
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        Collections.fill(list, "X");
        System.out.println("After fill with X: " + list); 
        // Output: [X, X, X, X, X]
        
        // Frequency (count occurrences)
        List<String> items = new ArrayList<>(Arrays.asList("A", "B", "A", "C", "A"));
        int count = Collections.frequency(items, "A");
        System.out.println("Frequency of A: " + count); // Output: 3
        
        // Disjoint (checks if collections have no common elements)
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(3, 4, 5);
        System.out.println("list1 and list2 disjoint: " + Collections.disjoint(list1, list2)); 
        // Output: true
        System.out.println("list1 and list3 disjoint: " + Collections.disjoint(list1, list3)); 
        // Output: false
        
        // Creating immutable collections
        List<String> immutableList = Collections.unmodifiableList(
            new ArrayList<>(Arrays.asList("a", "b", "c"))
        );
        // immutableList.add("d"); // Throws UnsupportedOperationException
        
        Set<String> immutableSet = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList("x", "y", "z"))
        );
        
        Map<String, Integer> immutableMap = Collections.unmodifiableMap(
            new HashMap<String, Integer>() {{
                put("key1", 1);
                put("key2", 2);
            }}
        );
        
        // Creating synchronized collections
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
        
        // Rotate
        List<String> letters = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));
        Collections.rotate(letters, 2); // Rotate right by 2
        System.out.println("After rotate by 2: " + letters); 
        // Output: [D, E, A, B, C]
        
        // Swap
        List<String> words = new ArrayList<>(Arrays.asList("One", "Two", "Three", "Four"));
        Collections.swap(words, 1, 3);
        System.out.println("After swap(1, 3): " + words); 
        // Output: [One, Four, Three, Two]
        
        // Copy
        List<String> source = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> destination = new ArrayList<>(Arrays.asList("X", "Y", "Z", "W"));
        Collections.copy(destination, source);
        System.out.println("After copy: " + destination); 
        // Output: [A, B, C, W]
    }
}
```

### 7.2 Arrays Utility Class

The `Arrays` class provides static methods for working with arrays.

**Code Example**:

```java
import java.util.*;

public class ArraysUtilityExample {
    public static void main(String[] args) {
        // Creating arrays
        int[] arr = {50, 20, 80, 10, 60, 30};
        
        // Printing array
        System.out.println("Array: " + Arrays.toString(arr));
        // Output: [50, 20, 80, 10, 60, 30]
        
        // Sorting
        Arrays.sort(arr);
        System.out.println("After sort: " + Arrays.toString(arr)); 
        // Output: [10, 20, 30, 50, 60, 80]
        
        // Binary search (requires sorted array)
        int index = Arrays.binarySearch(arr, 50);
        System.out.println("Index of 50: " + index); // Output: 3
        
        // Filling
        int[] filled = new int[5];
        Arrays.fill(filled, 99);
        System.out.println("After fill with 99: " + Arrays.toString(filled)); 
        // Output: [99, 99, 99, 99, 99]
        
        // Fill range
        int[] rangeArray = new int[5];
        Arrays.fill(rangeArray, 1, 4, 77); // Fill indices 1-3
        System.out.println("After fill range [1,4): " + Arrays.toString(rangeArray)); 
        // Output: [0, 77, 77, 77, 0]
        
        // Copying arrays
        int[] original = {1, 2, 3, 4, 5};
        int[] copy = Arrays.copyOf(original, 3);
        System.out.println("copyOf(original, 3): " + Arrays.toString(copy)); 
        // Output: [1, 2, 3]
        
        int[] copyRange = Arrays.copyOfRange(original, 1, 4);
        System.out.println("copyOfRange(1, 4): " + Arrays.toString(copyRange)); 
        // Output: [2, 3, 4]
        
        // Comparing arrays
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = {1, 2, 4};
        System.out.println("Arrays.equals(arr1, arr2): " + Arrays.equals(arr1, arr2)); 
        // Output: true
        System.out.println("Arrays.equals(arr1, arr3): " + Arrays.equals(arr1, arr3)); 
        // Output: false
        
        // Deep comparison (for multidimensional arrays)
        int[][] matrix1 = {{1, 2}, {3, 4}};
        int[][] matrix2 = {{1, 2}, {3, 4}};
        System.out.println("Arrays.deepEquals: " + Arrays.deepEquals(matrix1, matrix2)); 
        // Output: true
        
        // Converting to List
        String[] fruits = {"Apple", "Banana", "Orange"};
        List<String> list = Arrays.asList(fruits);
        System.out.println("Arrays.asList: " + list); 
        // Output: [Apple, Banana, Orange]
        // Note: This is a fixed-size list backed by array
        
        // Sort with custom comparator
        String[] words = {"Zebra", "Apple", "Mango", "Banana"};
        Arrays.sort(words);
        System.out.println("After sort (natural): " + Arrays.toString(words)); 
        // Output: [Apple, Banana, Mango, Zebra]
        
        Arrays.sort(words, Collections.reverseOrder());
        System.out.println("After reverse sort: " + Arrays.toString(words)); 
        // Output: [Zebra, Mango, Banana, Apple]
        
        // Parallel sort (faster for large arrays)
        int[] largeArray = new int[1000000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = (int) (Math.random() * 1000000);
        }
        Arrays.parallelSort(largeArray);
        System.out.println("Parallel sort completed"); 
        
        // Stream from array
        long sum = Arrays.stream(largeArray).limit(5).sum();
        System.out.println("Sum of first 5 elements: " + sum);
    }
}
```

---

## 8. Comparable vs Comparator

### Comparable Interface

Comparable is used for **natural ordering** (single comparison strategy).

```java
// Comparable interface - natural ordering
public interface Comparable<T> {
    int compareTo(T other);
}

// Returns:
// negative - this < other
// zero - this == other
// positive - this > other
```

### Comparator Interface

Comparator is used for **custom ordering** (multiple comparison strategies).

```java
// Comparator interface - custom ordering
public interface Comparator<T> {
    int compare(T o1, T o2);
    // Returns: negative, zero, or positive
}
```

**Detailed Example**:

```java
import java.util.*;

// Using Comparable for natural ordering
class Student implements Comparable<Student> {
    int rollNo;
    String name;
    double gpa;
    
    Student(int rollNo, String name, double gpa) {
        this.rollNo = rollNo;
        this.name = name;
        this.gpa = gpa;
    }
    
    // Natural ordering by roll number
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.rollNo, other.rollNo);
    }
    
    @Override
    public String toString() {
        return "Student(" + rollNo + ", " + name + ", " + gpa + ")";
    }
}

public class ComparableComparatorExample {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(103, "Charlie", 3.8));
        students.add(new Student(101, "Alice", 3.9));
        students.add(new Student(102, "Bob", 3.7));
        students.add(new Student(105, "Eve", 3.6));
        students.add(new Student(104, "David", 3.85));
        
        System.out.println("Original list: " + students);
        
        // Using Comparable natural ordering (by roll number)
        Collections.sort(students);
        System.out.println("After sorting by roll number (Comparable): " + students);
        // Output: Student(101, Alice, 3.9), Student(102, Bob, 3.7), ...
        
        // Using Comparator for custom ordering (by name)
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.name.compareTo(s2.name);
            }
        });
        System.out.println("After sorting by name (Comparator): " + students);
        // Output: Alice, Bob, Charlie, David, Eve
        
        // Using Comparator lambda (Java 8+)
        Collections.sort(students, (s1, s2) -> Double.compare(s2.gpa, s1.gpa)); // Reverse GPA
        System.out.println("After sorting by GPA descending: " + students);
        // Output: Alice(3.9), Charlie(3.8), David(3.85), Bob(3.7), Eve(3.6)
        
        // Using Comparator.comparing (Java 8+)
        students.sort(Comparator.comparingInt(s -> s.rollNo));
        System.out.println("Using Comparator.comparing (roll no): " + students);
        
        // Chaining multiple comparators
        students.sort(
            Comparator.comparingDouble((Student s) -> s.gpa).reversed()
                      .thenComparingInt(s -> s.rollNo)
        );
        System.out.println("Sorted by GPA (desc) then roll number: " + students);
        
        // Find student with highest GPA
        Student topStudent = Collections.max(students, 
            Comparator.comparingDouble(s -> s.gpa));
        System.out.println("Top student by GPA: " + topStudent);
        
        // Find student with lowest GPA
        Student lowestStudent = Collections.min(students, 
            Comparator.comparingDouble(s -> s.gpa));
        System.out.println("Lowest student by GPA: " + lowestStudent);
    }
}
```

---

## 9. Iterators: Iterator vs ListIterator

### Iterator Interface

Bidirectional iteration over any collection. **Forward only**.

```java
public interface Iterator<E> {
    boolean hasNext();
    E next();
    void remove();
    void forEachRemaining(Consumer<? super E> action); // Java 8+
}
```

### ListIterator Interface

Bidirectional iteration over list. **Forward and backward**.

```java
public interface ListIterator<E> extends Iterator<E> {
    // Iterator methods (inherited)
    boolean hasNext();
    E next();
    void remove();
    
    // ListIterator specific methods
    boolean hasPrevious();
    E previous();
    int nextIndex();
    int previousIndex();
    void set(E e);              // Update current element
    void add(E e);              // Insert element at current position
}
```

**Detailed Example**:

```java
import java.util.*;

public class IteratorExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(
            Arrays.asList("A", "B", "C", "D", "E")
        );
        
        // Using Iterator (works with any Collection)
        System.out.println("Using Iterator (forward only):");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.print(element + " "); // Output: A B C D E
            // Safe removal during iteration
            if (element.equals("C")) {
                iterator.remove();
            }
        }
        System.out.println();
        System.out.println("After removal via Iterator: " + list); 
        // Output: [A, B, D, E]
        
        // Using ListIterator (bidirectional)
        System.out.println("\nUsing ListIterator (bidirectional):");
        list = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
        ListIterator<String> listIterator = list.listIterator();
        
        // Forward iteration
        System.out.println("Forward iteration:");
        while (listIterator.hasNext()) {
            System.out.println("Index: " + listIterator.nextIndex() + 
                             ", Element: " + listIterator.next());
            // Output: Index 0, Element 1; Index 1, Element 2; ...
        }
        
        // Backward iteration (from end to beginning)
        System.out.println("\nBackward iteration (from current position):");
        while (listIterator.hasPrevious()) {
            System.out.println("Index: " + listIterator.previousIndex() + 
                             ", Element: " + listIterator.previous());
            // Output: Index 4, Element 5; Index 3, Element 4; ...
        }
        
        // Using set() to modify during iteration
        System.out.println("\nUsing set() to modify during iteration:");
        listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String element = listIterator.next();
            if (element.equals("3")) {
                listIterator.set("THREE"); // Replace current element
            }
        }
        System.out.println("After modification: " + list); 
        // Output: [1, 2, THREE, 4, 5]
        
        // Using add() to insert during iteration
        System.out.println("\nUsing add() to insert during iteration:");
        list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        listIterator = list.listIterator();
        listIterator.next(); // Move to first element
        listIterator.add("A1"); // Insert after "A"
        System.out.println("After add: " + list); 
        // Output: [A, A1, B, C]
        
        // Comparing with for-each loop
        System.out.println("\nFor-each loop (iterator internally):");
        for (String element : list) {
            System.out.print(element + " "); // Output: A A1 B C
        }
        System.out.println();
        
        // forEachRemaining (Java 8+)
        System.out.println("\nUsing forEachRemaining:");
        iterator = list.iterator();
        iterator.forEachRemaining(e -> System.out.print(e + " ")); 
        // Output: A A1 B C
    }
}
```

---

## 10. Concurrent Collections

### Overview

Concurrent collections are designed for multi-threaded environments.

| Collection | Thread-Safe | Locking Strategy | Performance |
|---|---|---|---|
| Collections.synchronizedList() | Yes | Entire list locked | Slow |
| CopyOnWriteArrayList | Yes | Copy-on-write | Good for reads |
| Collections.synchronizedMap() | Yes | Entire map locked | Slow |
| ConcurrentHashMap | Yes | Segmented locking | Very fast |
| CopyOnWriteArraySet | Yes | Copy-on-write | Good for reads |
| ConcurrentSkipListMap | Yes | Skip list | Fast |

**Code Example**:

```java
import java.util.*;
import java.util.concurrent.*;

public class ConcurrentCollectionsExample {
    public static void main(String[] args) throws InterruptedException {
        // CopyOnWriteArrayList - efficient reads
        List<String> copyList = new CopyOnWriteArrayList<>();
        copyList.add("Item1");
        copyList.add("Item2");
        copyList.add("Item3");
        
        // Iterator is safe from ConcurrentModificationException
        Iterator<String> it = copyList.iterator();
        copyList.add("Item4"); // Modification during iteration
        // Iterator still works on original copy
        while (it.hasNext()) {
            System.out.println(it.next()); // Item1, Item2, Item3 (Item4 not seen)
        }
        
        // ConcurrentHashMap - segmented locking
        Map<String, Integer> concMap = new ConcurrentHashMap<>();
        concMap.put("Key1", 1);
        concMap.put("Key2", 2);
        concMap.put("Key3", 3);
        
        // Atomic operations
        concMap.putIfAbsent("Key1", 100); // No effect
        concMap.replace("Key2", 2, 20); // Conditional replace
        
        System.out.println("ConcurrentHashMap: " + concMap);
        
        // ConcurrentSkipListMap - sorted concurrent map
        Map<Integer, String> skipMap = new ConcurrentSkipListMap<>();
        skipMap.put(50, "Fifty");
        skipMap.put(20, "Twenty");
        skipMap.put(80, "Eighty");
        skipMap.put(10, "Ten");
        
        System.out.println("ConcurrentSkipListMap (sorted): " + skipMap);
    }
}
```

---

## 11. Special Collections: EnumSet and EnumMap

Already covered in sections 4.4 and 6.4 - these are highly optimized for enum types.

---

## 12. Fail-Fast vs Fail-Safe Iterators

### Fail-Fast Iterators

Throw `ConcurrentModificationException` if collection is modified during iteration.

**Fail-Fast Collections**: ArrayList, LinkedList, HashMap, HashSet, TreeMap, TreeSet, Vector

```java
List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
Iterator<String> it = list.iterator();

while (it.hasNext()) {
    String element = it.next();
    if (element.equals("B")) {
        list.add("E"); // Throws ConcurrentModificationException!
    }
}
```

### Fail-Safe Iterators

Do NOT throw exception on concurrent modification. Iterate over copy.

**Fail-Safe Collections**: CopyOnWriteArrayList, CopyOnWriteArraySet, ConcurrentHashMap

```java
List<String> list = new CopyOnWriteArrayList<>(Arrays.asList("A", "B", "C", "D"));
Iterator<String> it = list.iterator();

while (it.hasNext()) {
    String element = it.next();
    if (element.equals("B")) {
        list.add("E"); // No exception! But E won't be in this iteration
    }
}
```

---

## Time Complexity Summary

| Operation | ArrayList | LinkedList | HashSet | TreeSet | HashMap | TreeMap |
|---|---|---|---|---|---|---|
| Add | O(1)* | O(1) | O(1)* | O(log n) | O(1)* | O(log n) |
| Remove | O(n) | O(1) | O(1)* | O(log n) | O(1)* | O(log n) |
| Get | O(1) | O(n) | N/A | N/A | O(1)* | O(log n) |
| Contains | O(n) | O(n) | O(1)* | O(log n) | O(1)* | O(log n) |
| Iteration | O(n) | O(n) | O(n) | O(n) | O(n) | O(n) |

*Average case; worst case O(n)

---

## Best Practices

1. **Choose the Right Collection**
   - Need ordered? → ArrayList (fast access) or LinkedList (fast modification at ends)
   - Need set semantics? → HashSet (fast) or TreeSet (sorted)
   - Need key-value pairs? → HashMap (fast) or TreeMap (sorted keys)
   - Need queue/stack? → ArrayDeque, PriorityQueue

2. **Initialization Size**
   - Set ArrayList initial capacity if size is known
   - Reduces resizing overhead

3. **Thread Safety**
   - Use concurrent collections in multi-threaded environments
   - Avoid Collections.synchronized* - use concurrent classes instead

4. **Iteration**
   - Use for-each loop when only reading
   - Use Iterator when removing elements
   - Avoid modification during iteration (use fail-safe if necessary)

5. **Performance**
   - Profile your code
   - Consider memory vs. speed trade-offs
   - Use parallel streams for bulk operations on large collections

---

## Conclusion

The Java Collections Framework provides a comprehensive set of data structures for solving various problems. Understanding the characteristics, time complexities, and use cases of each collection type is crucial for writing efficient and maintainable Java code. Choose collections based on your specific requirements for access patterns, ordering, and thread-safety.