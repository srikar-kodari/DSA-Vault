# Java Streams — Complete DSA Notes

## 1. Introduction

- **Stream** = sequence of elements supporting sequential and parallel aggregate operations.
- **Does not store data** — it operates on a source (collection, array, I/O).
- **Lazy** — intermediate ops are evaluated only when a terminal op is invoked.
- **One-time use** — consumed after one terminal op; need a new stream for another pass.
- **Non-mutating** — operations don’t modify the source (unless the source is mutable and you mutate inside lambdas).

**Two kinds of operations:**
- **Intermediate**: return a stream (e.g. `filter`, `map`) — can be chained.
- **Terminal**: produce a result or side-effect (e.g. `collect`, `forEach`) — ends the pipeline.

---

## 2. Ways to Create Streams

### 2.1 From Collections

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
Stream<Integer> stream = list.stream();           // sequential
Stream<Integer> parallelStream = list.parallelStream();

Set<String> set = Set.of("a", "b", "c");
Stream<String> setStream = set.stream();
// Output: stream contains [1, 2, 3, 4, 5]
//         setStream contains ["a", "b", "c"] (order may vary)
```

### 2.2 From Arrays

```java
int[] arr = {1, 2, 3, 4, 5};
IntStream intStream = Arrays.stream(arr);
IntStream partial = Arrays.stream(arr, 1, 4);  // from index 1 to 3 (exclusive)
// Output: intStream contains [1, 2, 3, 4, 5]
//         partial contains [2, 3, 4]

String[] names = {"Alice", "Bob", "Charlie"};
Stream<String> nameStream = Arrays.stream(names);
// Output: nameStream contains ["Alice", "Bob", "Charlie"]
```

### 2.3 Static factory: `Stream.of(T... values)`

```java
Stream<Integer> s = Stream.of(1, 2, 3, 4, 5);
Stream<String> words = Stream.of("hello", "world");
// Output: s contains [1, 2, 3, 4, 5]
//         words contains ["hello", "world"]

// Single element
Stream<Integer> single = Stream.of(42);
// Output: single contains [42]

// Empty stream
Stream<Object> empty = Stream.empty();
// Output: empty contains []
```

### 2.4 `Stream.iterate(seed, UnaryOperator)` — infinite stream

```java
// 0, 1, 2, 3, ...
Stream<Integer> naturals = Stream.iterate(0, n -> n + 1);
// Output: infinite stream [0, 1, 2, 3, ...] (use limit() to make finite)

// First 10 even numbers: 0, 2, 4, ...
Stream<Integer> evens = Stream.iterate(0, n -> n + 2).limit(10);
// Output: [0, 2, 4, 6, 8, 10, 12, 14, 16, 18]

// With predicate (Java 9+): stop when condition fails
Stream<Integer> limited = Stream.iterate(0, n -> n < 100, n -> n + 1);
// Output: [0, 1, 2, ..., 99] (100 elements)
```

### 2.5 `Stream.generate(Supplier)` — infinite stream

```java
Stream<Double> randoms = Stream.generate(Math::random);
// Output: infinite stream of random doubles [0.0, 1.0)

Stream<Integer> constant = Stream.generate(() -> 1);
// Output: infinite stream [1, 1, 1, ...]

Stream<Integer> firstTen = Stream.generate(() -> 1).limit(10);
// Output: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
```

### 2.6 Primitive streams: `IntStream`, `LongStream`, `DoubleStream`

```java
IntStream.range(0, 10);           // 0..9
// Output: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

IntStream.rangeClosed(1, 10);     // 1..10
// Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

LongStream.range(0, 100);
// Output: [0, 1, 2, ..., 99]

DoubleStream.of(1.0, 2.0, 3.0);
// Output: [1.0, 2.0, 3.0]
```

### 2.7 From `BufferedReader` (lines)

```java
try (Stream<String> lines = Files.lines(Path.of("file.txt"))) {
    lines.forEach(System.out::println);
}
// Output: prints each line of file.txt to console
```

### 2.8 From `Pattern` (regex)

```java
Stream<String> words = Pattern.compile("\\s+").splitAsStream("a b c");
// Output: ["a", "b", "c"]
```

### Quick reference

| Source              | Method / Class                          |
|---------------------|-----------------------------------------|
| Collection          | `collection.stream()`                   |
| Array               | `Arrays.stream(array)`                  |
| Literal values      | `Stream.of(...)`                        |
| Empty               | `Stream.empty()`                        |
| Infinite (sequence) | `Stream.iterate(seed, f)`               |
| Infinite (random)   | `Stream.generate(supplier)`             |
| Range of ints       | `IntStream.range(l, r)` / `rangeClosed`  |

---

## 3. Intermediate Operations

All return a **Stream** (or IntStream etc.), so they can be chained. Execution is **lazy** until a terminal op runs.

### 3.1 `filter(Predicate<T>)`

Keeps only elements that satisfy the predicate.

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
List<Integer> evens = list.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());  // [2, 4, 6]
// Output: evens = [2, 4, 6]

// DSA: filter indices where arr[i] > k
int[] arr = {10, 20, 5, 30, 15};
int k = 12;
int[] above = Arrays.stream(arr).filter(x -> x > k).toArray();
// Output: above = [20, 30, 15]
```

### 3.2 `map(Function<T, R>)`

Transforms each element (1-to-1).

```java
List<String> names = Arrays.asList("alice", "bob", "charlie");
List<String> capped = names.stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());
// Output: capped = ["ALICE", "BOB", "CHARLIE"]

// DSA: square each element
int[] arr = {1, 2, 3, 4};
int[] squares = Arrays.stream(arr).map(x -> x * x).toArray();
// Output: squares = [1, 4, 9, 16]

// map to length
List<Integer> lengths = names.stream().map(String::length).collect(Collectors.toList());
// Output: lengths = [5, 3, 7]
```

### 3.3 `flatMap(Function<T, Stream<R>>)`

Maps each element to a stream and flattens into one stream (1-to-many).

```java
List<List<Integer>> listOfLists = Arrays.asList(
    Arrays.asList(1, 2),
    Arrays.asList(3, 4),
    Arrays.asList(5, 6)
);
List<Integer> flat = listOfLists.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());  // [1, 2, 3, 4, 5, 6]
// Output: flat = [1, 2, 3, 4, 5, 6]

// DSA: flatten list of arrays
List<int[]> arrays = Arrays.asList(new int[]{1, 2}, new int[]{3, 4});
int[] one = arrays.stream().flatMapToInt(Arrays::stream).toArray();
// Output: one = [1, 2, 3, 4]

// Split sentence into words
Stream<String> words = Stream.of("hello world", "java streams")
    .flatMap(s -> Arrays.stream(s.split(" ")));
// Output: words contains ["hello", "world", "java", "streams"]
```

### 3.4 `distinct()`

Removes duplicates (uses `equals()`).

```java
List<Integer> withDupes = Arrays.asList(1, 2, 2, 3, 1, 3);
List<Integer> unique = withDupes.stream().distinct().collect(Collectors.toList());  // [1, 2, 3]
// Output: unique = [1, 2, 3]
```

### 3.5 `sorted()` / `sorted(Comparator<T>)`

Sorts elements. No comparator = natural order (elements must be `Comparable`).

```java
List<Integer> list = Arrays.asList(3, 1, 4, 1, 5);
List<Integer> asc = list.stream().sorted().collect(Collectors.toList());   // [1, 1, 3, 4, 5]
// Output: asc = [1, 1, 3, 4, 5]

List<Integer> desc = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
// Output: desc = [5, 4, 3, 1, 1]

// DSA: sort by absolute value, then by value
List<Integer> byAbs = list.stream()
    .sorted(Comparator.comparingInt(Math::abs).thenComparingInt(i -> i))
    .collect(Collectors.toList());
// Output: byAbs = [1, 1, 3, 4, 5] (same as natural order in this case)
```

### 3.6 `peek(Consumer<T>)`

Performs a side-effect (e.g. debug) without changing elements. Mostly for debugging.

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
list.stream()
    .filter(n -> n > 2)
    .peek(System.out::println)  // prints: 3, 4, 5
    .map(n -> n * 2)
    .collect(Collectors.toList());
// Output: Console prints: 3, 4, 5
//         Result: [6, 8, 10]
```

### 3.7 `limit(long n)`

Takes at most the first `n` elements.

```java
Stream.iterate(0, i -> i + 1).limit(5).forEach(System.out::println);  // 0,1,2,3,4
// Output: Console prints:
//         0
//         1
//         2
//         3
//         4
```

### 3.8 `skip(long n)`

Skips the first `n` elements.

```java
Arrays.asList(1, 2, 3, 4, 5).stream().skip(2).collect(Collectors.toList());  // [3, 4, 5]
// Output: [3, 4, 5]
```

### 3.9 `takeWhile(Predicate<T>)` (Java 9+)

Takes elements **while** the predicate is true; stops at first false.

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 2, 1);
List<Integer> taken = list.stream().takeWhile(n -> n < 4).collect(Collectors.toList());  // [1, 2, 3]
// Output: taken = [1, 2, 3] (stops at first element >= 4)
```

### 3.10 `dropWhile(Predicate<T>)` (Java 9+)

Drops elements **while** the predicate is true; returns the rest.

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 2, 1);
List<Integer> dropped = list.stream().dropWhile(n -> n < 4).collect(Collectors.toList());  // [4, 2, 1]
// Output: dropped = [4, 2, 1] (drops elements until first >= 4)
```

### 3.11 `mapToInt`, `mapToLong`, `mapToDouble`

Map to primitive streams (avoids boxing).

```java
List<String> nums = Arrays.asList("1", "2", "3");
int sum = nums.stream().mapToInt(Integer::parseInt).sum();
// Output: sum = 6
```

### 3.12 `boxed()`

Converts primitive stream to `Stream<Integer>` etc.

```java
List<Integer> list = IntStream.range(0, 5).boxed().collect(Collectors.toList());
// Output: list = [0, 1, 2, 3, 4]
```

### Intermediate ops summary

| Operation   | Purpose                          |
|------------|-----------------------------------|
| `filter`   | Keep elements matching predicate  |
| `map`      | Transform 1-to-1                  |
| `flatMap`  | Transform 1-to-many, flatten      |
| `distinct` | Remove duplicates                 |
| `sorted`   | Sort (natural or comparator)      |
| `peek`     | Side-effect (e.g. debug)          |
| `limit`    | First n elements                  |
| `skip`     | Skip first n elements             |
| `takeWhile`| Take while predicate true (Java 9+) |
| `dropWhile`| Drop while predicate true (Java 9+) |

---

## 4. Terminal Operations

Terminal ops **consume** the stream and produce a result or side-effect. After this, the stream cannot be reused.

### 4.1 `forEach(Consumer<T>)` / `forEachOrdered`

Apply an action to each element. No return value.

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
list.stream().filter(n -> n % 2 == 0).forEach(System.out::println);
// Output: Console prints:
//         2
//         4
// forEachOrdered: use when order matters in parallel streams
```

### 4.2 `collect(Collector)`

Accumulate elements into a collection or other result. Most flexible terminal op.

```java
Stream<String> stream = Stream.of("a", "b", "c");
List<String> list = stream.collect(Collectors.toList());
// Output: list = ["a", "b", "c"]

Stream<Integer> intStream = Stream.of(1, 2, 2, 3);
Set<Integer> set = intStream.collect(Collectors.toSet());
// Output: set = [1, 2, 3] (order may vary)

// To array
String[] arr = Stream.of("a", "b", "c").toArray(String[]::new);
// Output: arr = ["a", "b", "c"]

int[] prim = IntStream.of(1, 2, 3).toArray();
// Output: prim = [1, 2, 3]

// To map: key = identity, value = length
List<String> names = Arrays.asList("alice", "bob", "charlie");
Map<String, Integer> map = names.stream()
    .collect(Collectors.toMap(Function.identity(), String::length));
// Output: map = {"alice": 5, "bob": 3, "charlie": 7}

// Joining strings
String joined = Stream.of("a", "b", "c").collect(Collectors.joining(", "));
// Output: joined = "a, b, c"

// Grouping
Map<Integer, List<String>> byLength = names.stream()
    .collect(Collectors.groupingBy(String::length));
// Output: byLength = {3: ["bob"], 5: ["alice"], 7: ["charlie"]}

// Partitioning (two groups by predicate)
List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
Map<Boolean, List<Integer>> evensOdds = nums.stream()
    .collect(Collectors.partitioningBy(n -> n % 2 == 0));
// Output: evensOdds = {false: [1, 3, 5], true: [2, 4]}
```

### 4.3 `reduce`

Combine elements into one value.

```java
// reduce(identity, BinaryOperator)
int sum = Arrays.asList(1, 2, 3, 4, 5).stream()
    .reduce(0, Integer::sum);
// Output: sum = 15

Stream<Integer> stream = Stream.of(2, 3, 4);
int product = stream.reduce(1, (a, b) -> a * b);
// Output: product = 24

// Optional: no identity
Stream<Integer> stream2 = Stream.of(3, 1, 4, 1, 5);
Optional<Integer> max = stream2.reduce(Integer::max);
// Output: max = Optional[5]

// reduce(identity, accumulator, combiner) — for parallel
Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);
int sumParallel = stream3.parallel().reduce(0, Integer::sum, Integer::sum);
// Output: sumParallel = 15
```

### 4.4 `count()`

Number of elements (long).

```java
List<Integer> list = Arrays.asList(5, 15, 8, 20, 12);
long n = list.stream().filter(x -> x > 10).count();
// Output: n = 3
```

### 4.5 `min(Comparator)` / `max(Comparator)`

Returns `Optional<T>`.

```java
List<Integer> list = Arrays.asList(3, 1, 4, 1, 5);
Optional<Integer> min = list.stream().min(Integer::compareTo);
// Output: min = Optional[1]

Optional<Integer> max = list.stream().max(Integer::compareTo);
// Output: max = Optional[5]

// DSA: min element in array
int[] arr = {3, 1, 4, 1, 5};
OptionalInt minVal = Arrays.stream(arr).min();
// Output: minVal = OptionalInt[1]
```

### 4.6 `anyMatch(Predicate)` / `allMatch` / `noneMatch`

Short-circuit boolean checks.

```java
List<Integer> list = Arrays.asList(1, 3, 5, 2, 7);
boolean hasEven = list.stream().anyMatch(n -> n % 2 == 0);
// Output: hasEven = true

boolean allPositive = list.stream().allMatch(n -> n > 0);
// Output: allPositive = true

boolean noNegative = list.stream().noneMatch(n -> n < 0);
// Output: noNegative = true
```

### 4.7 `findFirst()` / `findAny()`

Return `Optional<T>`. `findFirst` = first in encounter order; `findAny` = any (useful in parallel).

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
Optional<Integer> first = list.stream().filter(n -> n > 3).findFirst();
// Output: first = Optional[4]

Optional<Integer> any = list.stream().parallel().filter(n -> n > 3).findAny();
// Output: any = Optional[4] or Optional[5] (non-deterministic in parallel)
```

### 4.8 `toArray()` / `toArray(IntFunction<A[]>)`

Convert to array.

```java
Stream<String> stream = Stream.of("a", "b", "c");
Object[] objArr = stream.toArray();
// Output: objArr = ["a", "b", "c"]

Stream<String> stream2 = Stream.of("x", "y", "z");
String[] strArr = stream2.toArray(String[]::new);
// Output: strArr = ["x", "y", "z"]

IntStream intStream = IntStream.of(1, 2, 3);
int[] intArr = intStream.toArray();
// Output: intArr = [1, 2, 3]
```

### Terminal ops summary

| Operation     | Return type   | Purpose                    |
|---------------|---------------|----------------------------|
| `forEach`     | void          | Side-effect per element    |
| `collect`     | R             | Accumulate to collection   |
| `reduce`      | T / Optional  | Combine to single value    |
| `count`       | long          | Number of elements         |
| `min` / `max` | Optional<T>   | Min/max by comparator      |
| `anyMatch`    | boolean       | Any element matches        |
| `allMatch`    | boolean       | All match                  |
| `noneMatch`   | boolean       | No element matches         |
| `findFirst`   | Optional<T>   | First element              |
| `findAny`     | Optional<T>   | Any element                |
| `toArray`     | array         | Stream to array            |

---

## 5. DSA-Friendly Examples

### Sum, product, min, max

```java
int[] arr = {3, 1, 4, 1, 5};
int sum = Arrays.stream(arr).sum();
// Output: sum = 14

int min = Arrays.stream(arr).min().orElse(-1);
// Output: min = 1

int max = Arrays.stream(arr).max().orElse(-1);
// Output: max = 5
```

### Count elements satisfying condition

```java
int[] arr = {10, 20, 5, 30, 15};
int k = 12;
long countGreaterThanK = Arrays.stream(arr).filter(x -> x > k).count();
// Output: countGreaterThanK = 3
```

### Check if any / all / none match

```java
int[] arr = {3, 1, 4, -1, 5};
boolean hasNegative = Arrays.stream(arr).anyMatch(x -> x < 0);
// Output: hasNegative = true

boolean allPositive = Arrays.stream(arr).allMatch(x -> x > 0);
// Output: allPositive = false
```

### First element matching predicate

```java
int[] arr = {1, 3, 4, 5, 6};
OptionalInt firstEven = Arrays.stream(arr).filter(x -> x % 2 == 0).findFirst();
// Output: firstEven = OptionalInt[4]

int val = firstEven.orElse(-1);
// Output: val = 4
```

### Sort array (as stream → array)

```java
int[] arr = {3, 1, 4, 1, 5};
int[] sorted = Arrays.stream(arr).sorted().toArray();
// Output: sorted = [1, 1, 3, 4, 5]

Integer[] desc = Arrays.stream(arr).boxed()
    .sorted(Comparator.reverseOrder())
    .toArray(Integer[]::new);
// Output: desc = [5, 4, 3, 1, 1]
```

### Distinct elements

```java
int[] arr = {3, 1, 4, 1, 5, 3};
int[] distinct = Arrays.stream(arr).distinct().toArray();
// Output: distinct = [3, 1, 4, 5]
```

### Map then collect

```java
int[] arr = {1, 2, 3, 4};
List<Integer> doubled = Arrays.stream(arr).map(x -> x * 2).boxed().collect(Collectors.toList());
// Output: doubled = [2, 4, 6, 8]
```

### Flatten 2D array

```java
int[][] matrix = {{1, 2}, {3, 4}, {5, 6}};
int[] flat = Arrays.stream(matrix).flatMapToInt(Arrays::stream).toArray();
// Output: flat = [1, 2, 3, 4, 5, 6]
```

### Group by (e.g. parity)

```java
int[] arr = {1, 2, 3, 4, 5};
Map<Boolean, List<Integer>> byParity = Arrays.stream(arr).boxed()
    .collect(Collectors.partitioningBy(x -> x % 2 == 0));
// Output: byParity = {false: [1, 3, 5], true: [2, 4]}
```

### Reduce: custom aggregation

```java
// GCD of array (conceptual: use reduce with GCD function)
int[] arr = {12, 18, 24};
// Assuming gcd() method exists
int gcd = Arrays.stream(arr).reduce((a, b) -> gcd(a, b)).orElse(1);
// Output: gcd = 6 (GCD of 12, 18, 24)
```

### Take first k, skip first k

```java
int[] arr = {1, 2, 3, 4, 5};
int k = 3;
int[] firstK = Arrays.stream(arr).limit(k).toArray();
// Output: firstK = [1, 2, 3]

int[] afterK = Arrays.stream(arr).skip(k).toArray();
// Output: afterK = [4, 5]
```

---

## 6. Important Points for DSA

1. **Primitive streams** (`IntStream`, etc.) avoid boxing — prefer for `int[]`/`long[]` when you only need numeric ops.
2. **One terminal op per pipeline** — after `count()` or `collect()`, the stream is consumed.
3. **Lazy evaluation** — no work until a terminal op runs; `limit()` can make infinite streams finite.
4. **Order** — `forEach` on parallel stream doesn’t guarantee order; use `forEachOrdered` if needed.
5. **Nulls** — avoid putting `null` in streams; use `Optional` or filter nulls if needed.
6. **Collectors** — `toList()`, `toSet()`, `toMap()`, `groupingBy`, `partitioningBy`, `joining` cover most DSA needs.

---

## 7. Cheat Sheet

```
Create:    list.stream() | Arrays.stream(arr) | Stream.of(1,2,3) | IntStream.range(0,n)
Filter:    filter(pred)
Transform: map(fun) | flatMap(fun -> stream)
Order:     sorted() | sorted(comparator)
Slice:     limit(n) | skip(n) | takeWhile(pred) | dropWhile(pred)
Uniq:      distinct()

Terminal:  collect(toList()) | count() | sum() | min() | max()
           reduce(identity, op) | anyMatch | allMatch | noneMatch
           findFirst() | findAny() | forEach(...) | toArray()
```

This covers stream creation, intermediate and terminal operations with examples, and patterns useful for DSA and coding interviews.
