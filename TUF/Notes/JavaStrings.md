# Java Strings – DSA Notes

## 1. String Basics

*   **Definition**: A `String` in Java is an object representing a sequence of characters. It is backed by a `char[]` (or `byte[]` in newer JDKs).
*   **Immutability**: Once created, the string object cannot be changed.
    *   **Why**: Security (network connections, database URLs), Thread-safety, String Constant Pool caching, HashCode caching.
    *   **Impact**: Methods like `toUpperCase()` or `substring()` do not modify the original; they return **new** instances.
*   **String vs `char[]`**:
    *   `String`: Immutable, overhead of object header, has rich method API.
    *   `char[]`: Mutable, primitive array, less memory, faster for low-level parsing.
*   **Comparison**:

| Feature | String | StringBuilder | StringBuffer |
| :--- | :--- | :--- | :--- |
| **Mutability** | **Immutable** | **Mutable** | **Mutable** |
| **Thread Safety** | Yes (Implicit) | No | Yes (Synchronized) |
| **Performance** | Slow (concatenation loops) | **Fast** (not synched) | Slower than Builder |
| **Use Case** | Constants, Keys, Literals | Loops, Mutable text | Multithreaded text gen |

## 2. String Memory & Comparison

*   **String Constant Pool (SCP)**: Special heap area. Stores unique string literals.
    *   `String s1 = "Hi";` and `String s2 = "Hi";` point to the **same** SCP object.
    *   `String s3 = new String("Hi");` creates a **new** object in Heap (skips SCP check for reference).
*   **`==` vs `.equals()`**:
    *   `==`: Checks **Reference Equality** (Do they point to the same memory address?).
    *   `.equals()`: Checks **Content Equality** (Do they contain the same characters?).
*   **`intern()`**:
    *   Returns the reference from the SCP. If string not in SCP, adds it and returns reference.
    *   **Why**: To deduplicate strings and save memory.

## 3. Important String Methods

| Method | Purpose | Syntax | Example |
| :--- | :--- | :--- | :--- |
| `length()` | Count chars | `int length()` | `"abc".length(); // 3` |
| `charAt()` | Get char at index | `char charAt(int i)` | `"abc".charAt(0); // 'a'` |
| `substring()` | Slices string | `String substring(int s, int e)` | `"code".substring(1,3); // "od"` |
| `indexOf()` | First index of val | `int indexOf(String s)` | `"hello".indexOf("l"); // 2` |
| `lastIndexOf()`| Last index of val | `int lastIndexOf(String s)` | `"hello".lastIndexOf("l"); // 3` |
| `equals()` | Value check | `boolean equals(Object o)` | `"a".equals("A"); // false` |
| `equalsIgnoreCase()` | Case-insensitive | `boolean equalsIgnoreCase(String s)` | `"a".equalsIgnoreCase("A"); // true` |
| `compareTo()` | Lexical diff | `int compareTo(String s)` | `"a".compareTo("c"); // -2` |
| `contains()` | Contains seq | `boolean contains(CharSequence s)`| `"Hi".contains("i"); // true` |
| `startsWith()` | Prefix check | `boolean startsWith(String p)` | `"Hi".startsWith("H"); // true` |
| `endsWith()` | Suffix check | `boolean endsWith(String s)` | `"Hi".endsWith("i"); // true` |
| `split()` | Regex Split | `String[] split(String regex)` | `"a,b".split(","); // ["a","b"]` |
| `replace()` | Replace chars | `String replace(old, new)` | `"ox".replace('x','k'); // "ok"` |
| `replaceAll()` | Regex Replace | `String replaceAll(regex, repl)` | `"1a2".replaceAll("\\d", "#"); // "#a#"` |
| `trim()` | Remove outer space | `String trim()` | `" a ".trim(); // "a"` |
| `strip()` | Unicode trim (Java 11+) | `String strip()` | `"\u2000a ".strip(); // "a"` |
| `toLowerCase()` | To Lower Case | `String toLowerCase()` | `"A".toLowerCase(); // "a"` |
| `toUpperCase()` | To Upper Case | `String toUpperCase()` | `"a".toUpperCase(); // "A"` |
| `toCharArray()`| Convert to array | `char[] toCharArray()` | `"hi".toCharArray(); // ['h','i']` |
| `join()` | Join elements | `static String join(delim, elements)` | `String.join("-","a","b"); // "a-b"` |
| `valueOf()` | Primitive to Str | `static String valueOf(type v)` | `String.valueOf(10); // "10"` |
| `isEmpty()` | Check length 0 | `boolean isEmpty()` | `"".isEmpty(); // true` |
| `isBlank()` | Check blank (Java 11+) | `boolean isBlank()` | `"  ".isBlank(); // true` |
| `matches()` | Check Regex | `boolean matches(String regex)` | `"123".matches("\\d+"); // true` |
| `repeat()` | Repeat string (Java 11+) | `String repeat(int count)` | `"hi".repeat(3); // "hihihi"` |
| `format()` | Format string | `static String format(String fmt, ...)` | `String.format("%d", 42); // "42"` |
| `intern()` | Get pooled reference | `String intern()` | `new String("a").intern()` |
| `lines()` | Split by lines (Java 11+) | `Stream<String> lines()` | `"a\nb".lines().count(); // 2` |
| `stripLeading()` | Remove leading spaces (Java 11+) | `String stripLeading()` | `"  a".stripLeading(); // "a"` |
| `stripTrailing()` | Remove trailing spaces (Java 11+) | `String stripTrailing()` | `"a  ".stripTrailing(); // "a"` |
| `replaceFirst()` | Replace first match | `String replaceFirst(regex, repl)` | `"a1b2".replaceFirst("\\d", "#"); // "a#b2"` |

```java
String s = "  Hello World  ";

// 1. Basic Information
int len = s.length();                 // 15
char c = s.charAt(2);                 // 'H' (0-indexed, after space)
boolean empty = s.isEmpty();          // false
boolean blank = s.isBlank();          // false (Java 11+)

// 2. Modification (Returns NEW String)
String trimmed = s.trim();            // "Hello World"
String stripped = s.strip();          // "Hello World" (Java 11+, Unicode-aware)
String lower = trimmed.toLowerCase(); // "hello world"
String upper = trimmed.toUpperCase(); // "HELLO WORLD"
String replaced = trimmed.replace("World", "Java"); // "Hello Java"
String replacedAll = "a1b2c3".replaceAll("\\d", "#"); // "a#b#c#"
String replacedFirst = "a1b2c3".replaceFirst("\\d", "#"); // "a#b2c3"

// 3. Search & Comparison
boolean hasWorld = trimmed.contains("World"); // true
boolean starts = trimmed.startsWith("Hello");  // true
boolean ends = trimmed.endsWith("World");      // true
int startSpace = s.indexOf(" ");      // 0
int endSpace = s.lastIndexOf(" ");    // 14
int fromIndex = s.indexOf("o", 5);   // 7 (search from index 5)
boolean equals = "abc".equals("ABC"); // false
boolean eqIgCase = "abc".equalsIgnoreCase("ABC"); // true
int compare = "abc".compareTo("abd");  // -1 (lexicographic)

// 4. Substring & Split
String sub = trimmed.substring(0, 5); // "Hello" (end index exclusive)
String subToEnd = trimmed.substring(6); // "World" (from index to end)
String[] parts = trimmed.split(" ");  // ["Hello", "World"]
String[] limited = "a,b,c,d".split(",", 2); // ["a", "b,c,d"] (limit splits)

// 5. Conversion
char[] arr = trimmed.toCharArray();   // ['H','e','l','l','o',' ','W','o','r','l','d']
String val = String.valueOf(100);     // "100"
String joined = String.join("-", "a", "b", "c"); // "a-b-c"

// 6. Additional Methods (Java 11+)
String repeated = "hi".repeat(3);     // "hihihi"
String formatted = String.format("Name: %s, Age: %d", "John", 25);
String leading = "  hello".stripLeading();   // "hello"
String trailing = "hello  ".stripTrailing(); // "hello"
long lineCount = "line1\nline2\nline3".lines().count(); // 3

// 7. Regex Matching
boolean matches = "123abc".matches("\\d+[a-z]+"); // true

// 8. Additional Important Methods (Java 11+)
String repeated = "hello".repeat(3);                        // "hellohellohello"
String formatted = String.format("Name: %s, Age: %d", "John", 25); // "Name: John, Age: 25"
String interned = new String("test").intern();              // Returns pooled reference
long lineCount = "line1\nline2\nline3".lines().count();    // 3
String indented = "hello".indent(2);                        // "  hello\n" (Java 12+)
String transformed = "hello".transform(s -> s.toUpperCase()); // "HELLO" (Java 12+)
```

## 4. StringBuilder & StringBuffer

*   **Why Preferred in Loops**: `String s += "a"` inside a loop copies the array every iteration ($O(N^2)$). `StringBuilder` appends to an internal array ($O(N)$ total).
*   **Comparison**: See table in Section 1. `StringBuilder` is the default choice unless thread safety is required.
*   **Methods**:

```java
StringBuilder sb = new StringBuilder("Start");

// Append (most common in CP)
sb.append("End");        // "StartEnd" (O(1) amortized)
sb.append(123);          // "StartEnd123"
sb.append('!');          // "StartEnd123!"

// Insert at position
sb.insert(5, "-");       // "Start-End123!" (O(N) shift)

// Delete
sb.delete(0, 5);         // "-End123!" (O(N) shift)
sb.deleteCharAt(0);       // "End123!" (O(N) shift)

// Reverse (crucial for palindrome problems)
sb.reverse();            // "!321dnE" (O(N) in-place swap)

// Replace
sb.replace(0, 3, "XYZ"); // "XYZ21dnE" (O(N))

// Character access
char c = sb.charAt(0);   // 'X'
sb.setCharAt(0, 'A');    // "AYZ21dnE"

// Substring (doesn't modify)
String sub = sb.substring(0, 3); // "AYZ"

// Length and Capacity
int len = sb.length();           // Current length
int cap = sb.capacity();         // Current capacity
sb.ensureCapacity(100);          // Ensure minimum capacity
sb.setLength(5);                 // Truncate to length 5

// Convert to String
String res = sb.toString();

// Performance: Pre-allocate capacity
StringBuilder optimized = new StringBuilder(1000); // Pre-allocate for better performance
```

### 4.1 Performance Comparison Example

```java
public class StringBuilderDemo {
    public static void main(String[] args) {
        int n = 100000;
        
        // SLOW: O(n²) - creates new string each iteration
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) {
            s += "a"; // Copies entire string each time!
        }
        long time1 = System.currentTimeMillis() - start;
        
        // FAST: O(n) - appends to internal buffer
        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        String result = sb.toString();
        long time2 = System.currentTimeMillis() - start;
        
        System.out.println("String concatenation: " + time1 + "ms");
        System.out.println("StringBuilder: " + time2 + "ms");
        // StringBuilder is typically 100-1000x faster for large n
    }
}
```

## 5. High-Yield String Patterns (DSA)

### 5.1 Reverse String
```java
public void reverseString(char[] s) {
    int i = 0, j = s.length - 1;
    while (i < j) {
        char temp = s[i];
        s[i++] = s[j];
        s[j--] = temp;
    }
}
```

### 5.2 Palindrome Check
```java
public boolean isPalindrome(String s) {
    int i = 0, j = s.length() - 1;
    while (i < j) {
        if (s.charAt(i++) != s.charAt(j--)) return false;
    }
    return true;
}
```

### 5.3 Anagram Check
```java
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;
    int[] count = new int[26];
    for (char c : s.toCharArray()) count[c - 'a']++;
    for (char c : t.toCharArray()) count[c - 'a']--;
    for (int i : count) if (i != 0) return false;
    return true;
}
```

### 5.4 Character Frequency
```java
public int[] getFreq(String s) {
    int[] freq = new int[26]; // or 128/256 for ASCII
    for (char c : s.toCharArray()) freq[c - 'a']++;
    return freq;
}
```

### 5.5 Remove Duplicates
```java
public String removeDuplicates(String s) {
    boolean[] seen = new boolean[128];
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
        if (!seen[c]) {
            seen[c] = true;
            sb.append(c);
        }
    }
    return sb.toString();
}
```

### 5.6 First Non-Repeating Character
```java
public int firstUniqChar(String s) {
    int[] count = new int[26];
    for (int i = 0; i < s.length(); i++) 
        count[s.charAt(i) - 'a']++;
    // Second pass to check counts
    for (int i = 0; i < s.length(); i++) 
        if (count[s.charAt(i) - 'a'] == 1) return i;
    return -1;
}
```

### 5.7 Longest Substring Without Repeating Characters
```java
public int lengthOfLongestSubstring(String s) {
    int[] map = new int[128]; // Tracks last seen index + 1
    int max = 0, left = 0;
    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        left = Math.max(left, map[c]); // Jump left if char repeat seen
        max = Math.max(max, right - left + 1);
        map[c] = right + 1;
    }
    return max;
}
```

### 5.8 Palindrome with Alphanumeric Filter (LeetCode Style)
```java
public boolean isPalindromeFiltered(String s) {
    int left = 0, right = s.length() - 1;
    while (left < right) {
        // Skip non-alphanumeric characters
        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
            left++;
        }
        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
            right--;
        }
        // Compare (case-insensitive)
        if (Character.toLowerCase(s.charAt(left++)) != 
            Character.toLowerCase(s.charAt(right--))) {
            return false;
        }
    }
    return true;
}
```

### 5.9 Group Anagrams
```java
import java.util.*;

public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        String key = new String(arr);
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
    }
    return new ArrayList<>(map.values());
}
```

### 5.10 Valid Parentheses
```java
public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
        if (c == '(' || c == '[' || c == '{') {
            stack.push(c);
        } else {
            if (stack.isEmpty()) return false;
            char top = stack.pop();
            if ((c == ')' && top != '(') ||
                (c == ']' && top != '[') ||
                (c == '}' && top != '{')) {
                return false;
            }
        }
    }
    return stack.isEmpty();
}
```

### 5.11 String Compression
```java
// Compress "aabbccc" to "a2b2c3"
public String compress(String s) {
    if (s.length() == 0) return s;
    StringBuilder sb = new StringBuilder();
    int count = 1;
    for (int i = 1; i < s.length(); i++) {
        if (s.charAt(i) == s.charAt(i - 1)) {
            count++;
        } else {
            sb.append(s.charAt(i - 1));
            if (count > 1) sb.append(count);
            count = 1;
        }
    }
    sb.append(s.charAt(s.length() - 1));
    if (count > 1) sb.append(count);
    return sb.toString();
}
```

### 5.12 Reverse Words in String
```java
// "the sky is blue" -> "blue is sky the"
public String reverseWords(String s) {
    String[] words = s.trim().split("\\s+");
    StringBuilder sb = new StringBuilder();
    for (int i = words.length - 1; i >= 0; i--) {
        sb.append(words[i]);
        if (i > 0) sb.append(" ");
    }
    return sb.toString();
}
```

### 5.13 Longest Common Prefix
```java
public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) return "";
    String prefix = strs[0];
    for (int i = 1; i < strs.length; i++) {
        while (!strs[i].startsWith(prefix)) {
            prefix = prefix.substring(0, prefix.length() - 1);
            if (prefix.isEmpty()) return "";
        }
    }
    return prefix;
}
```

### 5.14 String to Integer (atoi)
```java
public int myAtoi(String s) {
    s = s.trim();
    if (s.isEmpty()) return 0;
    
    int sign = 1, i = 0;
    if (s.charAt(0) == '-' || s.charAt(0) == '+') {
        sign = s.charAt(0) == '-' ? -1 : 1;
        i++;
    }
    
    long result = 0;
    while (i < s.length() && Character.isDigit(s.charAt(i))) {
        result = result * 10 + (s.charAt(i) - '0');
        if (sign * result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (sign * result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        i++;
    }
    return (int)(sign * result);
}
```

### 5.15 Minimum Window Substring
```java
// Find minimum window in s containing all chars of t
public String minWindow(String s, String t) {
    int[] need = new int[128];
    for (char c : t.toCharArray()) need[c]++;
    
    int left = 0, right = 0, count = t.length();
    int minLen = Integer.MAX_VALUE, start = 0;
    
    while (right < s.length()) {
        if (need[s.charAt(right++)]-- > 0) count--;
        while (count == 0) {
            if (right - left < minLen) {
                minLen = right - left;
                start = left;
            }
            if (need[s.charAt(left++)]++ == 0) count++;
        }
    }
    return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
}
```

### 5.16 Count Palindromic Substrings
```java
public int countSubstrings(String s) {
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
        count += expandAroundCenter(s, i, i);      // odd length
        count += expandAroundCenter(s, i, i + 1);  // even length
    }
    return count;
}

private int expandAroundCenter(String s, int left, int right) {
    int count = 0;
    while (left >= 0 && right < s.length() && 
           s.charAt(left) == s.charAt(right)) {
        count++;
        left--;
        right++;
    }
    return count;
}
```

### 5.17 Remove Adjacent Duplicates
```java
// Remove adjacent duplicates: "abbaca" -> "ca"
public String removeDuplicates(String s) {
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) {
            sb.deleteCharAt(sb.length() - 1);
        } else {
            sb.append(c);
        }
    }
    return sb.toString();
}
```

### 5.18 String Matching - Naive Pattern Search
```java
// Find all occurrences of pattern in text
public List<Integer> naiveSearch(String text, String pattern) {
    List<Integer> indices = new ArrayList<>();
    int n = text.length(), m = pattern.length();
    
    for (int i = 0; i <= n - m; i++) {
        int j = 0;
        while (j < m && text.charAt(i + j) == pattern.charAt(j)) {
            j++;
        }
        if (j == m) indices.add(i);
    }
    return indices;
}
```

### 5.19 KMP Algorithm (Pattern Matching)
```java
// Knuth-Morris-Pratt algorithm for efficient pattern matching
public List<Integer> kmpSearch(String text, String pattern) {
    List<Integer> indices = new ArrayList<>();
    int n = text.length(), m = pattern.length();
    int[] lps = computeLPS(pattern);
    
    int i = 0, j = 0; // i for text, j for pattern
    while (i < n) {
        if (text.charAt(i) == pattern.charAt(j)) {
            i++;
            j++;
        }
        if (j == m) {
            indices.add(i - j);
            j = lps[j - 1];
        } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
            if (j != 0) j = lps[j - 1];
            else i++;
        }
    }
    return indices;
}

// Compute Longest Proper Prefix which is also Suffix
private int[] computeLPS(String pattern) {
    int m = pattern.length();
    int[] lps = new int[m];
    int len = 0, i = 1;
    
    while (i < m) {
        if (pattern.charAt(i) == pattern.charAt(len)) {
            lps[i++] = ++len;
        } else {
            if (len != 0) len = lps[len - 1];
            else lps[i++] = 0;
        }
    }
    return lps;
}
```

### 5.20 String Rotation Check
```java
// Check if s2 is rotation of s1
public boolean isRotation(String s1, String s2) {
    if (s1.length() != s2.length()) return false;
    String doubled = s1 + s1;
    return doubled.contains(s2);
}
```

### 5.21 String Permutations
```java
// Check if s2 contains permutation of s1
public boolean checkInclusion(String s1, String s2) {
    int[] freq = new int[26];
    for (char c : s1.toCharArray()) freq[c - 'a']++;
    
    int left = 0, count = s1.length();
    for (int right = 0; right < s2.length(); right++) {
        if (freq[s2.charAt(right) - 'a']-- > 0) count--;
        if (count == 0) return true;
        if (right - left + 1 == s1.length()) {
            if (freq[s2.charAt(left++) - 'a']++ >= 0) count++;
        }
    }
    return false;
}
```

### 5.22 Character Class Methods (Important Utilities)

```java
char c = 'A';

// Character type checks
Character.isLetter(c);              // true
Character.isDigit(c);               // false
Character.isLetterOrDigit(c);       // true
Character.isWhitespace(' ');        // true
Character.isUpperCase(c);           // true
Character.isLowerCase('a');         // true

// Character conversion
Character.toUpperCase('a');         // 'A'
Character.toLowerCase('A');         // 'a'
int digit = Character.getNumericValue('5'); // 5

// Useful in CP for filtering
String s = "a1b2c3";
for (char ch : s.toCharArray()) {
    if (Character.isDigit(ch)) {
        // Process digit
    } else if (Character.isLetter(ch)) {
        // Process letter
    }
}
```

## 6. Core DSA Techniques on Strings

*   **Two Pointers**:
    *   **Use Cases**: Reversing, Palindrome, Partitioning, Merging strings.
    *   **Pattern**: Initialize `left=0`, `right=n-1` and move inward.
*   **Sliding Window**:
    *   **Use Cases**: Longest substring with K distinct chars, Anagram search in string.
    *   **Pattern**: Expand `right`, shrink `left` when condition violates (e.g., duplicate found).
*   **Frequency Array vs HashMap**:
    *   **Array (`int[26]`)**: **Preferred** for lowercase English letters. O(1) space, faster access than Map.
    *   **HashMap**: Essential for general Unicode characters or when range is unknown/sparse.

### 6.1 String Formatting (Important for CP Output)

```java
// String.format() - very useful in competitive programming
String name = "Alice";
int score = 95;
double avg = 87.5;

// Basic formatting
String.format("Name: %s, Score: %d", name, score);
// Output: "Name: Alice, Score: 95"

// Decimal formatting
String.format("Average: %.2f", avg);  // "Average: 87.50"
String.format("Average: %.1f", avg);  // "Average: 87.5"

// Padding
String.format("%10s", name);          // "     Alice" (right pad)
String.format("%-10s", name);         // "Alice     " (left pad)
String.format("%05d", score);         // "00095" (zero padding)

// Multiple values
String.format("%s scored %d out of 100 (%.1f%%)", name, score, avg);
// "Alice scored 95 out of 100 (87.5%)"

// Using printf (same as format)
System.out.printf("Case #%d: %s\n", testCase, answer);
```

### 6.2 Competitive Programming Tips

#### Fast I/O for Strings
```java
import java.io.*;
import java.util.*;

// Fast reading
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String line = br.readLine();
String[] tokens = line.split("\\s+");

// Fast writing
PrintWriter pw = new PrintWriter(System.out);
pw.println("Result");
pw.flush();

// Using Scanner (slower but convenient)
Scanner sc = new Scanner(System.in);
String s = sc.next();      // Reads until whitespace
String line = sc.nextLine(); // Reads entire line
```

#### Common CP String Patterns
```java
// Pattern 1: Process string character by character
for (char c : s.toCharArray()) {
    // Process each character
}

// Pattern 2: Two pointers for palindrome/partition
int left = 0, right = s.length() - 1;
while (left < right) {
    // Process
}

// Pattern 3: Sliding window for substring problems
int left = 0;
for (int right = 0; right < s.length(); right++) {
    // Expand window
    while (/* condition */) {
        // Shrink window
        left++;
    }
}

// Pattern 4: Frequency array for character counting
int[] freq = new int[26]; // or 128 for ASCII
for (char c : s.toCharArray()) {
    freq[c - 'a']++;
}
```

#### String Input Parsing
```java
// Parse space-separated strings
String[] parts = input.split("\\s+");

// Parse comma-separated values
String[] csv = input.split(",");

// Parse with multiple delimiters
String[] tokens = input.split("[,\\s]+");

// Read until specific character
int index = input.indexOf('|');
String first = input.substring(0, index);
String second = input.substring(index + 1);
```

## 6.3 Complete Runnable Examples

### Example 1: String Manipulation Demo
```java
public class StringDemo {
    public static void main(String[] args) {
        String s = "Hello World";
        
        // Basic operations
        System.out.println("Length: " + s.length());
        System.out.println("Char at 0: " + s.charAt(0));
        System.out.println("Substring: " + s.substring(0, 5));
        
        // Transformation
        System.out.println("Upper: " + s.toUpperCase());
        System.out.println("Lower: " + s.toLowerCase());
        System.out.println("Trim: '" + "  hello  ".trim() + "'");
        
        // Searching
        System.out.println("Index of 'o': " + s.indexOf('o'));
        System.out.println("Contains 'World': " + s.contains("World"));
        
        // Splitting
        String[] words = s.split(" ");
        System.out.println("Words: " + Arrays.toString(words));
        
        // Joining
        String joined = String.join("-", words);
        System.out.println("Joined: " + joined);
    }
}
```

### Example 2: Palindrome Checker
```java
public class PalindromeChecker {
    public static boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man a plan a canal Panama")); // true
        System.out.println(isPalindrome("race a car")); // false
    }
}
```

### Example 3: Anagram Checker
```java
public class AnagramChecker {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }
        
        for (int count : freq) {
            if (count != 0) return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(isAnagram("listen", "silent")); // true
        System.out.println(isAnagram("rat", "car"));      // false
    }
}
```

### Example 4: String Reversal Techniques
```java
public class StringReversal {
    // Method 1: StringBuilder
    public static String reverse1(String s) {
        return new StringBuilder(s).reverse().toString();
    }
    
    // Method 2: Two Pointers on char[]
    public static String reverse2(String s) {
        char[] arr = s.toCharArray();
        int left = 0, right = arr.length - 1;
        while (left < right) {
            char temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
        return new String(arr);
    }
    
    // Method 3: Recursive
    public static String reverse3(String s) {
        if (s.length() <= 1) return s;
        return reverse3(s.substring(1)) + s.charAt(0);
    }
    
    public static void main(String[] args) {
        String s = "hello";
        System.out.println(reverse1(s)); // "olleh"
        System.out.println(reverse2(s)); // "olleh"
        System.out.println(reverse3(s)); // "olleh"
    }
}
```

### Example 5: Character Frequency Counter
```java
import java.util.*;

public class FrequencyCounter {
    // Using array (for lowercase only)
    public static int[] getFrequencyArray(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                freq[Character.toLowerCase(c) - 'a']++;
            }
        }
        return freq;
    }
    
    // Using HashMap (for all characters)
    public static Map<Character, Integer> getFrequencyMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }
    
    public static void main(String[] args) {
        String s = "hello world";
        
        // Array method
        int[] freq = getFrequencyArray(s);
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                System.out.println((char)('a' + i) + ": " + freq[i]);
            }
        }
        
        // HashMap method
        Map<Character, Integer> map = getFrequencyMap(s);
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
```

## 6.4 Key Techniques Summary

| Technique | When to Use | Example Problems |
|-----------|-------------|------------------|
| **Two Pointers** | Palindrome, Reverse, Partition | Valid Palindrome, Reverse String |
| **Sliding Window** | Substring problems, K distinct | Longest Substring, Min Window |
| **Frequency Array** | Anagram, Character count | Valid Anagram, First Unique |
| **HashMap** | Unicode, Dynamic keys | Group Anagrams |
| **StringBuilder** | String building in loops | Build string efficiently |

## 6.5 Advanced String Techniques

### 6.5.1 String Interning
```java
// intern() adds string to String Pool
String s1 = new String("test");
String s2 = s1.intern();
String s3 = "test";

System.out.println(s1 == s2); // false (s1 is in heap)
System.out.println(s2 == s3); // true (both in pool)

// Use case: Memory optimization when many duplicate strings
String[] arr = new String[1000];
for (int i = 0; i < 1000; i++) {
    arr[i] = new String("common").intern(); // All point to same pool object
}
```

### 6.5.2 String Tokenization
```java
// Using StringTokenizer (legacy, but sometimes useful)
import java.util.StringTokenizer;
StringTokenizer st = new StringTokenizer("a,b,c", ",");
while (st.hasMoreTokens()) {
    System.out.println(st.nextToken());
}

// Modern approach: split()
String[] tokens = "a,b,c".split(",");
```

### 6.5.3 String Comparison for Sorting
```java
String[] arr = {"banana", "apple", "cherry"};

// Natural order
Arrays.sort(arr);
// Result: ["apple", "banana", "cherry"]

// Custom comparator
Arrays.sort(arr, (a, b) -> b.compareTo(a)); // Reverse order
Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER); // Case-insensitive
```

### 6.5.4 Efficient String Building
```java
// Pre-allocate capacity for better performance
StringBuilder sb = new StringBuilder(1000); // Pre-allocate 1000 chars

// Chain operations
String result = new StringBuilder()
    .append("Hello")
    .append(" ")
    .append("World")
    .toString();
```

### 6.5.5 Pattern Matching with Regex
```java
import java.util.regex.*;

String text = "Contact: email@example.com or phone: 123-456-7890";

// Pattern matching
Pattern emailPattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
Matcher matcher = emailPattern.matcher(text);
while (matcher.find()) {
    System.out.println("Found: " + matcher.group());
}

// Simple regex checks
String s = "abc123";
s.matches("[a-z]+\\d+"); // true
```

### 6.5.6 String Validation
```java
// Check if string contains only digits
public boolean isNumeric(String s) {
    return s.matches("\\d+");
}

// Check if string is valid email
public boolean isValidEmail(String email) {
    return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
}

// Check if string is palindrome (ignoring case and non-alphanumeric)
public boolean isValidPalindrome(String s) {
    s = s.toLowerCase().replaceAll("[^a-z0-9]", "");
    return s.equals(new StringBuilder(s).reverse().toString());
}
```

## 7. Time & Space Complexity Cheat Sheet

| Operation | Implementation | Time | Space |
| :--- | :--- | :--- | :--- |
| **Access** | `s.charAt(i)` | $O(1)$ | $O(1)$ |
| **Concatenate** | `s1 + s2` | $O(N+M)$ | $O(N+M)$ |
| **Append** | `sb.append(s)` | $O(1)$ (Amortized) | $O(N)$ |
| **Substring** | `s.substring(i,j)`| $O(N)$ (Java 7+) | $O(N)$ |
| **Search** | `s.indexOf(sub)` | $O(N \cdot M)$ | $O(1)$ |
| **Split** | `s.split()` | $O(N)$ | $O(N)$ |
| **Sort** | `Arrays.sort(arr)`| $O(N \log N)$ | $O(\log N)$ |

## 8. Common Interview Pitfalls

*   **Reference Comparison**: Using `==` instead of `.equals()` usually results in false negatives.
*   **String Concatenation in Loops**: Doing `str += char` is $O(N^2)$. ALWAYS use `StringBuilder`.
*   **Immutability Ignorance**: Calling `s.trim()` without assigning back (`s = s.trim()`) does nothing to `s`.
*   **Array vs String Length**: Coding `str.length` (error) instead of `str.length()`. Arrays use `.length`.
*   **Regex Special Chars**: `s.split(".")` breaks because `.` matches everything. Use `s.split("\\.")`.
*   **Substring Index**: `substring(start, end)` includes `start` indices but **excludes** `end`.
*   **Null Strings**: Always null-check before calling methods: `if (s != null && s.length() > 0)`
*   **Off-by-one Errors**: `charAt(s.length())` throws `IndexOutOfBoundsException`. Valid range: `[0, length-1]`
*   **Case Sensitivity**: `"A" != "a"`. Use `equalsIgnoreCase()` or convert to same case before comparison.
*   **Empty vs Null**: `""` is empty string, `null` is no object. Check both: `s == null || s.isEmpty()`

## 9. Quick Reference Templates

### 9.1 Frequency-Based Problems Template
```java
// For lowercase letters only
int[] freq = new int[26];
for (char c : s.toCharArray()) {
    freq[c - 'a']++;
}

// For all ASCII characters
int[] freq = new int[128];
for (char c : s.toCharArray()) {
    freq[c]++;
}

// For Unicode or sparse data
Map<Character, Integer> freq = new HashMap<>();
for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
```

### 9.2 Two-Pointer Template
```java
// Template for two-pointer string problems
int left = 0, right = s.length() - 1;
while (left < right) {
    // Process s.charAt(left) and s.charAt(right)
    // Example: palindrome check
    if (s.charAt(left) != s.charAt(right)) {
        return false;
    }
    left++;
    right--;
}
```

### 9.3 Sliding Window Template
```java
// Template for sliding window
int left = 0;
int result = 0; // or maxLen, minLen, etc.

for (int right = 0; right < s.length(); right++) {
    // Expand window: add s.charAt(right)
    // Update frequency or other state
    
    while (/* window invalid condition */) {
        // Shrink window: remove s.charAt(left)
        left++;
    }
    
    // Update result
    result = Math.max(result, right - left + 1);
}
```

### 9.4 String Building Template
```java
// Template for string building
StringBuilder sb = new StringBuilder();
for (int i = 0; i < n; i++) {
    sb.append(/* value */);
}
return sb.toString();

// With conditional appending
StringBuilder sb = new StringBuilder();
for (char c : s.toCharArray()) {
    if (/* condition */) {
        sb.append(c);
    }
}
return sb.toString();
```

### 9.5 String Parsing Template
```java
// Parse space-separated input
String[] parts = input.split("\\s+");
int n = Integer.parseInt(parts[0]);
String s = parts[1];

// Parse line by line
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String line;
while ((line = br.readLine()) != null) {
    // Process each line
}

// Parse with Scanner
Scanner sc = new Scanner(System.in);
String s = sc.next();      // Reads until whitespace
String line = sc.nextLine(); // Reads entire line
```

### 9.6 Common Utility Functions
```java
// Check if character is valid
Character.isLetter(c);
Character.isDigit(c);
Character.isLetterOrDigit(c);
Character.isWhitespace(c);

// Convert character
Character.toUpperCase(c);
Character.toLowerCase(c);
int digit = Character.getNumericValue(c);

// String checks
s.isEmpty();
s.isBlank(); // Java 11+
s.length() == 0;

// String conversions
Integer.parseInt(s);
Long.parseLong(s);
Double.parseDouble(s);
String.valueOf(num);
```

## 10. Useful Java String Methods Cheat Sheet

```
length()              - Get string length
charAt(i)             - Get character at index
substring(i, j)       - Extract substring (end exclusive)
indexOf(ch/str)       - Find first occurrence
lastIndexOf(ch/str)   - Find last occurrence
contains(str)         - Check if contains substring
startsWith(str)       - Check prefix
endsWith(str)         - Check suffix
toLowerCase()         - Convert to lowercase
toUpperCase()         - Convert to uppercase
trim()                - Remove leading/trailing spaces
strip()               - Unicode-aware trim (Java 11+)
replace(old, new)     - Replace characters/strings
replaceAll(regex, repl) - Regex replace all
replaceFirst(regex, repl) - Regex replace first
split(regex)           - Split into array
join(delimiter, arr)   - Join array elements
equals(str)           - Content comparison
equalsIgnoreCase(str) - Case-insensitive comparison
compareTo(str)         - Lexicographic comparison
matches(regex)         - Regex matching
valueOf(obj)           - Convert to string
repeat(count)          - Repeat string (Java 11+)
format(fmt, ...)       - Format string
intern()               - Get pooled reference
lines()                - Split by lines (Java 11+)
stripLeading()         - Remove leading spaces (Java 11+)
stripTrailing()        - Remove trailing spaces (Java 11+)
isEmpty()              - Check if length is 0
isBlank()              - Check if blank (Java 11+)
```

## 11. Complete Problem Solutions

### 11.1 LeetCode Style: Valid Anagram
```java
public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }
        
        for (int count : freq) {
            if (count != 0) return false;
        }
        return true;
    }
}
```

### 11.2 LeetCode Style: Longest Substring Without Repeating
```java
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] lastSeen = new int[128];
        Arrays.fill(lastSeen, -1);
        int maxLen = 0, left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (lastSeen[c] >= left) {
                left = lastSeen[c] + 1;
            }
            lastSeen[c] = right;
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
```

### 11.3 LeetCode Style: Group Anagrams
```java
import java.util.*;

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        
        return new ArrayList<>(map.values());
    }
}
```

## 12. Additional Resources & Notes

### 12.1 Memory Considerations
- String objects are immutable and stored in heap
- String Pool (SCP) stores string literals to save memory
- Use `StringBuilder` for dynamic string building to avoid creating many intermediate strings
- Pre-allocate `StringBuilder` capacity when size is known

### 12.2 Performance Tips
1. **Use `charAt()` instead of `toCharArray()`** when you only need to read characters
2. **Pre-allocate `StringBuilder` capacity** when you know approximate size
3. **Use frequency arrays** instead of HashMap for limited character sets (a-z, 0-9)
4. **Avoid `substring()` in loops** - it creates new strings
5. **Use `StringBuilder.reverse()`** for simple reversals

### 12.3 Common Interview Questions
- Reverse a string
- Check palindrome
- Valid anagram
- Longest substring without repeating characters
- Group anagrams
- String to integer (atoi)
- Minimum window substring
- Longest palindromic substring
- String compression
- Valid parentheses

### 12.4 Useful Java String Methods Cheat Sheet
```
length()              - Get string length
charAt(i)             - Get character at index
substring(i, j)       - Extract substring
indexOf(ch/str)       - Find first occurrence
lastIndexOf(ch/str)   - Find last occurrence
contains(str)         - Check if contains substring
startsWith(str)       - Check prefix
endsWith(str)         - Check suffix
toLowerCase()         - Convert to lowercase
toUpperCase()         - Convert to uppercase
trim()                - Remove leading/trailing spaces
replace(old, new)     - Replace characters/strings
split(regex)          - Split into array
join(delimiter, arr)  - Join array elements
equals(str)           - Content comparison
compareTo(str)        - Lexicographic comparison
matches(regex)        - Regex matching
valueOf(obj)          - Convert to string
```

## 13. Performance Tips & Best Practices

1. **Use `charAt()` instead of `toCharArray()`** when you only need to read characters
2. **Pre-allocate `StringBuilder` capacity** when you know approximate size
3. **Use frequency arrays** instead of HashMap for limited character sets (a-z, 0-9)
4. **Avoid `substring()` in loops** - it creates new strings
5. **Use `StringBuilder.reverse()`** for simple reversals
6. **Use `String.format()` or `printf()`** for formatted output in CP
7. **Prefer `StringBuilder` over `String` concatenation** in loops
8. **Use `Character` class methods** for character type checking
9. **Consider `intern()`** for memory optimization with many duplicate strings
10. **Use regex carefully** - it can be slow for simple operations
