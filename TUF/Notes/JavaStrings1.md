# Java Strings – DSA & Competitive Programming

## 1. Core Concepts

### 1.1 What is a String?
A `String` is an **immutable** sequence of characters. Internally backed by a `byte[]` (Java 9+) or `char[]`.

### 1.2 Immutability
```java
String s = "hello";
s.toUpperCase(); // Returns "HELLO", but 's' is still "hello"
s = s.toUpperCase(); // Now 's' is "HELLO"
```
**Why Immutable?**
- Thread-safe (no sync needed)
- Enables String Pool caching
- Safe for HashMap keys (hashCode never changes)

### 1.3 String Pool (SCP)
```java
String a = "hi";        // Goes to SCP
String b = "hi";        // Reuses same SCP reference
String c = new String("hi"); // New object in Heap

System.out.println(a == b); // true (same reference)
System.out.println(a == c); // false (different objects)
System.out.println(a.equals(c)); // true (same content)
```

### 1.4 String vs char[]
| Aspect | `String` | `char[]` |
|--------|----------|----------|
| Mutability | Immutable | Mutable |
| Memory | Object overhead | Pure array |
| Use Case | General text | In-place modification |

```java
// When to use char[]
char[] arr = s.toCharArray();
arr[0] = 'H'; // Direct modification
String result = new String(arr);
```

---

## 2. String vs StringBuilder vs StringBuffer

| Feature | String | StringBuilder | StringBuffer |
|---------|--------|---------------|--------------|
| Mutability | Immutable | Mutable | Mutable |
| Thread-Safe | Yes | **No** | Yes |
| Performance | Slow in loops | **Fastest** | Slower |
| Use Case | Constants | **Loops, DSA** | Multithreaded |

### Why StringBuilder in Loops?
```java
// BAD: O(n²) - creates new string each iteration
String s = "";
for (int i = 0; i < n; i++) {
    s += "a"; // Copies entire string each time!
}

// GOOD: O(n) - appends to internal buffer
StringBuilder sb = new StringBuilder();
for (int i = 0; i < n; i++) {
    sb.append("a");
}
String result = sb.toString();
```

---

## 3. Important String Methods

### 3.1 Basic Operations
```java
String s = "Hello World";

// Length
int len = s.length();           // 11

// Character access
char c = s.charAt(0);           // 'H'
char last = s.charAt(s.length() - 1); // 'd'

// Empty/Blank check
boolean empty = s.isEmpty();    // false
boolean blank = s.isBlank();    // false (Java 11+, checks whitespace)
```

### 3.2 Comparison
```java
String a = "abc", b = "ABC";

// Content equality
a.equals(b);            // false
a.equalsIgnoreCase(b);  // true

// Lexicographic comparison (for sorting)
a.compareTo(b);         // positive (a > b in ASCII)
a.compareToIgnoreCase(b); // 0 (equal ignoring case)

// Reference check (avoid for content)
a == b;                 // Use only for null check
```

### 3.3 Searching
```java
String s = "abcabc";

s.indexOf('b');         // 1 (first occurrence)
s.indexOf('b', 2);      // 4 (from index 2)
s.lastIndexOf('b');     // 4 (last occurrence)
s.indexOf("bc");        // 1 (substring search)

s.contains("cab");      // true
s.startsWith("ab");     // true
s.endsWith("bc");       // true
```

### 3.4 Extraction
```java
String s = "HelloWorld";

s.substring(5);         // "World" (from index 5 to end)
s.substring(0, 5);      // "Hello" (start inclusive, end exclusive)

s.toCharArray();        // ['H','e','l','l','o','W','o','r','l','d']
```

### 3.5 Transformation
```java
String s = "  Hello World  ";

s.trim();               // "Hello World" (removes leading/trailing whitespace)
s.strip();              // Same, but Unicode-aware (Java 11+)

s.toLowerCase();        // "  hello world  "
s.toUpperCase();        // "  HELLO WORLD  "

s.replace('l', 'x');    // "  Hexxo Worxd  "
s.replace("World", "Java"); // "  Hello Java  "
s.replaceAll("\\s+", "-");  // "-Hello-World-" (regex)
s.replaceFirst("\\s", "");  // " Hello World  " (first match only)
```

### 3.6 Splitting & Joining
```java
// Split
String csv = "a,b,c,d";
String[] parts = csv.split(",");     // ["a", "b", "c", "d"]
String[] limited = csv.split(",", 2); // ["a", "b,c,d"] (limit splits)

// Join
String joined = String.join("-", parts); // "a-b-c-d"
String fromArr = String.join("", arr);   // Concatenate char[] to String
```

### 3.7 Conversion
```java
// Primitives to String
String.valueOf(123);        // "123"
String.valueOf(3.14);       // "3.14"
String.valueOf(true);       // "true"
Integer.toString(123);      // "123"

// String to Primitives
Integer.parseInt("123");    // 123
Double.parseDouble("3.14"); // 3.14
Long.parseLong("999");      // 999L
```

### 3.8 Regex Methods
```java
String s = "abc123def456";

s.matches("[a-z]+\\d+[a-z]+\\d+"); // true (full match)
s.replaceAll("\\d+", "#");          // "abc#def#"
s.split("\\d+");                    // ["abc", "def"]
```

### 3.9 Additional Important Methods
```java
String s = "hello";

// Repeat (Java 11+)
s.repeat(3);                        // "hellohellohello"

// Format (like printf)
String.format("Name: %s, Age: %d", "John", 25); // "Name: John, Age: 25"

// Intern (adds to String Pool)
String a = new String("test");
String b = a.intern();              // Returns pooled reference if exists

// Lines (Java 11+) - split by newlines
String multi = "line1\nline2\nline3";
multi.lines().forEach(System.out::println); // Prints each line

// Strip variants (Java 11+)
String spaced = "  hello  ";
spaced.stripLeading();              // "hello  "
spaced.stripTrailing();             // "  hello"

// Indent (Java 12+)
"hello".indent(2);                  // "  hello\n"

// Transform (Java 12+)
String result = "hello".transform(s -> s.toUpperCase()); // "HELLO"
```

### 3.10 Character Class Methods
```java
char c = 'A';

Character.isLetter(c);              // true
Character.isDigit(c);               // false
Character.isLetterOrDigit(c);       // true
Character.isWhitespace(' ');        // true
Character.isUpperCase(c);           // true
Character.isLowerCase('a');         // true
Character.toUpperCase('a');         // 'A'
Character.toLowerCase('A');         // 'a'
Character.getNumericValue('5');     // 5
```

---

## 4. StringBuilder Methods

```java
StringBuilder sb = new StringBuilder("Hello");

// Append (most used in CP)
sb.append(" World");       // "Hello World"
sb.append(123);            // "Hello World123"
sb.append('!');            // "Hello World123!"

// Insert at position
sb.insert(5, ",");         // "Hello, World123!"

// Delete
sb.delete(5, 7);           // "HelloWorld123!" (removes ", ")
sb.deleteCharAt(0);        // "elloWorld123!"

// Reverse (crucial for palindrome problems)
sb.reverse();              // "!321dlroWolle"

// Replace
sb.replace(0, 3, "XYZ");   // "XYZdlroWolle"

// Get & Set character
char c = sb.charAt(0);     // 'X'
sb.setCharAt(0, 'A');      // "AYZdlroWolle"

// Substring
String sub = sb.substring(0, 3); // "AYZ" (doesn't modify sb)

// Length and Capacity
int len = sb.length();     // Current length
int cap = sb.capacity();   // Current capacity
sb.ensureCapacity(100);    // Ensure minimum capacity

// Convert to String
String result = sb.toString();

// Capacity management (optimization)
StringBuilder optimized = new StringBuilder(1000); // Pre-allocate
sb.setLength(5);           // Truncate to length 5
```

### 4.1 StringBuilder vs String Performance Example
```java
// Performance comparison
public class StringBuilderDemo {
    public static void main(String[] args) {
        int n = 100000;
        
        // Slow: O(n²)
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) {
            s += "a";
        }
        long time1 = System.currentTimeMillis() - start;
        
        // Fast: O(n)
        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        String result = sb.toString();
        long time2 = System.currentTimeMillis() - start;
        
        System.out.println("String concatenation: " + time1 + "ms");
        System.out.println("StringBuilder: " + time2 + "ms");
    }
}
```

---

## 4.2 String Formatting (Important for Output)

```java
// String.format() - very useful in CP for formatted output
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

// StringBuilder with format
StringBuilder sb = new StringBuilder();
sb.append(String.format("Result: %d", 42));
```

---

## 5. Common DSA Patterns

### 5.1 Reverse a String
```java
// Using StringBuilder
public String reverse(String s) {
    return new StringBuilder(s).reverse().toString();
}

// Using Two Pointers (in-place on char[])
public void reverseInPlace(char[] s) {
    int l = 0, r = s.length - 1;
    while (l < r) {
        char temp = s[l];
        s[l++] = s[r];
        s[r--] = temp;
    }
}
```

### 5.2 Check Palindrome
```java
// Basic
public boolean isPalindrome(String s) {
    int l = 0, r = s.length() - 1;
    while (l < r) {
        if (s.charAt(l++) != s.charAt(r--)) return false;
    }
    return true;
}

// With alphanumeric filtering (LeetCode style)
public boolean isPalindromeFiltered(String s) {
    int l = 0, r = s.length() - 1;
    while (l < r) {
        while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
        while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
        if (Character.toLowerCase(s.charAt(l++)) != 
            Character.toLowerCase(s.charAt(r--))) return false;
    }
    return true;
}
```

### 5.3 Check Anagram
```java
// Using frequency array (O(n) time, O(1) space for lowercase)
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
```

### 5.4 Character Frequency
```java
// For lowercase letters only
public int[] getFrequency(String s) {
    int[] freq = new int[26];
    for (char c : s.toCharArray()) {
        freq[c - 'a']++;
    }
    return freq;
}

// For all ASCII characters
public int[] getFrequencyASCII(String s) {
    int[] freq = new int[128];
    for (char c : s.toCharArray()) {
        freq[c]++;
    }
    return freq;
}

// Using HashMap (for Unicode or sparse data)
public Map<Character, Integer> getFrequencyMap(String s) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : s.toCharArray()) {
        map.merge(c, 1, Integer::sum);
    }
    return map;
}
```

### 5.5 First Non-Repeating Character
```java
public int firstUniqChar(String s) {
    int[] freq = new int[26];
    for (char c : s.toCharArray()) freq[c - 'a']++;
    for (int i = 0; i < s.length(); i++) {
        if (freq[s.charAt(i) - 'a'] == 1) return i;
    }
    return -1;
}
```

### 5.6 Longest Substring Without Repeating Characters
```java
// Sliding Window with HashMap
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
```

### 5.7 Remove Duplicate Characters
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

### 5.8 Group Anagrams
```java
// Group strings that are anagrams of each other
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

### 5.9 Valid Parentheses
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

### 5.10 String Compression
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

### 5.11 Reverse Words in String
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

### 5.12 Longest Common Prefix
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

### 5.13 String to Integer (atoi)
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

### 5.14 Minimum Window Substring
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

### 5.15 String Matching - Naive Pattern Search
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

### 5.16 KMP Algorithm (Pattern Matching)
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

### 5.17 Count Palindromic Substrings
```java
// Count all palindromic substrings
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

### 5.18 String Rotation Check
```java
// Check if s2 is rotation of s1
public boolean isRotation(String s1, String s2) {
    if (s1.length() != s2.length()) return false;
    String doubled = s1 + s1;
    return doubled.contains(s2);
}
```

### 5.19 Remove Adjacent Duplicates
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

### 5.20 String Permutations
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

---

## 5.21 Complete Runnable Examples

### Example 1: String Manipulation Demo
```java
public class StringDemo {
    public static void main(String[] args) {
        // Basic operations
        String s = "Hello World";
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

---

## 6. Key Techniques Summary

| Technique | When to Use | Example Problems |
|-----------|-------------|------------------|
| **Two Pointers** | Palindrome, Reverse, Partition | Valid Palindrome, Reverse String |
| **Sliding Window** | Substring problems, K distinct | Longest Substring, Min Window |
| **Frequency Array** | Anagram, Character count | Valid Anagram, First Unique |
| **HashMap** | Unicode, Dynamic keys | Group Anagrams |
| **StringBuilder** | String building in loops | Build string efficiently |

---

## 6.1 Advanced String Techniques

### 6.1.1 String Interning
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

### 6.1.2 String Tokenization
```java
// Using StringTokenizer (legacy, but sometimes useful)
StringTokenizer st = new StringTokenizer("a,b,c", ",");
while (st.hasMoreTokens()) {
    System.out.println(st.nextToken());
}

// Modern approach: split()
String[] tokens = "a,b,c".split(",");
```

### 6.1.3 Efficient String Building
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

### 6.1.4 String Comparison for Sorting
```java
String[] arr = {"banana", "apple", "cherry"};

// Natural order
Arrays.sort(arr);
// Result: ["apple", "banana", "cherry"]

// Custom comparator
Arrays.sort(arr, (a, b) -> b.compareTo(a)); // Reverse order
Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER); // Case-insensitive
```

### 6.1.5 Pattern Matching with Regex
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

### 6.1.6 String Validation
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

---

## 7. Complexity Cheat Sheet

| Operation | Time | Space | Notes |
|-----------|------|-------|-------|
| `s.length()` | O(1) | O(1) | Cached value |
| `s.charAt(i)` | O(1) | O(1) | Direct array access |
| `s.substring(i, j)` | O(n) | O(n) | Creates new string (Java 7+) |
| `s.indexOf(sub)` | O(n·m) | O(1) | Naive search |
| `s.split(regex)` | O(n) | O(n) | Depends on regex |
| `s + t` | O(n+m) | O(n+m) | Creates new string |
| `sb.append(s)` | O(k) | O(1)* | Amortized, may resize |
| `sb.reverse()` | O(n) | O(1) | In-place |
| `Arrays.sort(charArr)` | O(n log n) | O(log n) | For anagram sorting |

---

## 7.1 Competitive Programming Tips

### 7.1.1 Fast I/O for Strings
```java
import java.io.*;
import java.util.*;

// Fast reading
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String line = br.readLine();
String[] tokens = line.split(" ");

// Fast writing
PrintWriter pw = new PrintWriter(System.out);
pw.println("Result");
pw.flush();
```

### 7.1.2 Common CP String Patterns
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

### 7.1.3 String Input Parsing
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

### 7.1.4 Output Formatting for CP
```java
// Format output with specific precision
double result = 3.14159;
System.out.printf("%.2f\n", result); // "3.14"

// Format with padding
int num = 42;
System.out.printf("%05d\n", num); // "00042"

// Multiple values
System.out.printf("Case #%d: %s\n", testCase, answer);
```

---

## 8. Common Pitfalls

- **`==` vs `.equals()`**: Use `==` only for null checks, `.equals()` for content
- **Loop concatenation**: Use `StringBuilder`, not `+=` 
- **Immutability**: `s.trim()` returns new string; assign it back: `s = s.trim()`
- **`substring` end index**: Exclusive! `"hello".substring(0, 2)` → `"he"`
- **`split` with regex chars**: Escape special chars: `split("\\.")` for `.`
- **`length` vs `length()`**: Arrays use `.length`, Strings use `.length()`
- **Off-by-one**: `charAt(s.length())` throws `IndexOutOfBoundsException`
- **Null strings**: Always null-check before calling methods

---

## 9. Quick Reference Code

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

---

## 10. Complete Problem Solutions

### 10.1 LeetCode Style: Valid Anagram
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

### 10.2 LeetCode Style: Longest Substring Without Repeating
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

### 10.3 LeetCode Style: Group Anagrams
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

---

## 11. Additional Resources & Notes

### 11.1 Memory Considerations
- String objects are immutable and stored in heap
- String Pool (SCP) stores string literals to save memory
- Use `StringBuilder` for dynamic string building to avoid creating many intermediate strings
- Pre-allocate `StringBuilder` capacity when size is known

### 11.2 Performance Tips
1. **Use `charAt()` instead of `toCharArray()`** when you only need to read characters
2. **Pre-allocate `StringBuilder` capacity** when you know approximate size
3. **Use frequency arrays** instead of HashMap for limited character sets (a-z, 0-9)
4. **Avoid `substring()` in loops** - it creates new strings
5. **Use `StringBuilder.reverse()`** for simple reversals

### 11.3 Common Interview Questions
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

### 11.4 Useful Java String Methods Cheat Sheet
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
