# Bit Manipulation (DSA + Interviews) — Complete Notes

Bit manipulation is about working directly with bits (0/1) of numbers.
In interviews, it helps you reduce time/space, optimize brute force, and solve tricky “pattern” problems elegantly.

---

## 1. Binary Basics (Foundation)

## 1.1 Bits, MSB, LSB

- **Bit**: single binary digit (0 or 1).
- **LSB (Least Significant Bit)**: rightmost bit (weight = $2^0$).
- **MSB (Most Significant Bit)**: leftmost bit in fixed-width representation.
- In Java `int` is 32-bit signed (two’s complement), `long` is 64-bit signed.

Example (8-bit view):

`13 = 00001101`

- MSB = leftmost `0`
- LSB = rightmost `1`

## 1.2 Powers of 2 and Positional Value

Binary position weights from right to left:

| Bit Index | 7 | 6 | 5 | 4 | 3 | 2 | 1 | 0 |
|---|---|---|---|---|---|---|---|---|
| Weight | $2^7$ | $2^6$ | $2^5$ | $2^4$ | $2^3$ | $2^2$ | $2^1$ | $2^0$ |

So:

`13 = 8 + 4 + 1 = 2^3 + 2^2 + 2^0`

## 1.3 Decimal to Binary (Mental Method)

Repeatedly divide by 2 and collect remainders bottom-up.

`13 / 2 = 6 r1`
`6 / 2 = 3 r0`
`3 / 2 = 1 r1`
`1 / 2 = 0 r1`

=> `1101`

## 1.4 Binary to Decimal

Add weights where bit = 1.

`10110 = 1*16 + 0*8 + 1*4 + 1*2 + 0*1 = 22`

## 1.5 Java Snippet (Binary Display)

```java
public class BinaryBasics {
	public static void main(String[] args) {
		int n = 13;
		System.out.println("Decimal: " + n);
		System.out.println("Binary : " + Integer.toBinaryString(n)); // 1101
	}
}
```

Complexity: $O(1)$ time, $O(1)$ space for fixed-width primitive operations.

---

## 2. Bitwise Operators (With Java)

## 2.1 AND (`&`)

Rule: `1 & 1 = 1`, else `0`

Use cases:
- Check if a bit is set.
- Keep only common set bits.

```java
int a = 12; // 1100
int b = 10; // 1010
int c = a & b; // 1000 = 8
```

Complexity: $O(1)$ time, $O(1)$ space.

## 2.2 OR (`|`)

Rule: if any bit is `1`, result is `1`.

```java
int a = 12; // 1100
int b = 10; // 1010
int c = a | b; // 1110 = 14
```

Complexity: $O(1)$ time, $O(1)$ space.

## 2.3 XOR (`^`)

Rule: bits differ => `1`, same => `0`.

Important properties:
- `x ^ x = 0`
- `x ^ 0 = x`
- Commutative + associative

```java
int a = 12; // 1100
int b = 10; // 1010
int c = a ^ b; // 0110 = 6
```

Complexity: $O(1)$ time, $O(1)$ space.

## 2.4 NOT (`~`)

Flips all bits.

In Java two’s complement:

`~x = -(x + 1)`

```java
int x = 5;       // ...00000101
int y = ~x;      // ...11111010 = -6
System.out.println(y); // -6
```

Complexity: $O(1)$ time, $O(1)$ space.

## 2.5 Left Shift (`<<`)

`x << k` shifts bits left by `k` and fills 0s on right.

For non-overflowing values: `x << k == x * 2^k`

```java
int x = 5;       // 0101
int y = x << 1;  // 1010 = 10
```

Complexity: $O(1)$ time, $O(1)$ space.

## 2.6 Right Shift (`>>`) and Unsigned Right Shift (`>>>`)

- `>>` = arithmetic shift (preserves sign bit)
- `>>>` = logical shift (fills 0 from left)

```java
int x = -8;
System.out.println(x >> 1);   // -4 (sign preserved)
System.out.println(x >>> 1);  // large positive (zero-filled)
```

Complexity: $O(1)$ time, $O(1)$ space.

## 2.7 Operator Precedence Pitfall

`==` has higher precedence than `&`.

Wrong:

```java
if (n & 1 == 1) { } // compile-time error / wrong intent
```

Correct:

```java
if ((n & 1) == 1) {
	// odd
}
```

---

## 3. Most Important Bit Manipulation Tricks

These are high-frequency interview templates.

## 3.1 Check if k-th bit is set

```java
boolean isSet(int n, int k) {
	return ((n & (1 << k)) != 0);
}
```

Complexity: $O(1)$ time, $O(1)$ space.

## 3.2 Set k-th bit

```java
int setBit(int n, int k) {
	return n | (1 << k);
}
```

Complexity: $O(1)$ time, $O(1)$ space.

## 3.3 Clear k-th bit

```java
int clearBit(int n, int k) {
	return n & ~(1 << k);
}
```

Complexity: $O(1)$ time, $O(1)$ space.

## 3.4 Toggle k-th bit

```java
int toggleBit(int n, int k) {
	return n ^ (1 << k);
}
```

Complexity: $O(1)$ time, $O(1)$ space.

## 3.5 Remove lowest set bit

`n & (n - 1)` removes rightmost `1`.

```java
int removeLowestSetBit(int n) {
	return n & (n - 1);
}
```

Complexity: $O(1)$ time, $O(1)$ space.

## 3.6 Isolate lowest set bit

`n & -n` keeps only rightmost `1`.

```java
int lowestSetBitValue(int n) {
	return n & -n;
}
```

Complexity: $O(1)$ time, $O(1)$ space.

## 3.7 Check power of two

Condition for `n > 0`: `(n & (n - 1)) == 0`

```java
boolean isPowerOfTwo(int n) {
	return n > 0 && (n & (n - 1)) == 0;
}
```

Complexity: $O(1)$ time, $O(1)$ space.

## 3.8 Count set bits (Method 1: built-in)

```java
int count = Integer.bitCount(n);
```

Complexity: effectively $O(1)$ for fixed-width `int`.

## 3.9 Count set bits (Method 2: Brian Kernighan)

Each iteration removes one set bit.

```java
int countSetBits(int n) {
	int count = 0;
	while (n != 0) {
		n = n & (n - 1);
		count++;
	}
	return count;
}
```

Complexity: $O(number\ of\ set\ bits)$, worst-case $O(32)$ for int.

## 3.10 Find unique element when others appear twice

Use XOR of all values.

```java
int singleNumber(int[] arr) {
	int ans = 0;
	for (int x : arr) ans ^= x;
	return ans;
}
```

Complexity: $O(n)$ time, $O(1)$ extra space.

## 3.11 Swap two numbers using XOR (Interview curiosity)

```java
void xorSwap() {
	int a = 5, b = 9;
	a = a ^ b;
	b = a ^ b;
	a = a ^ b;
}
```

Note: avoid in production for readability; normal temp variable is clearer.

Complexity: $O(1)$ time, $O(1)$ space.

## 3.12 Enumerate all subsets using bitmask

For array size `n`, masks from `0` to `(1 << n) - 1`.

```java
import java.util.*;

List<List<Integer>> allSubsets(int[] arr) {
	int n = arr.length;
	List<List<Integer>> result = new ArrayList<>();

	for (int mask = 0; mask < (1 << n); mask++) {
		List<Integer> subset = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if ((mask & (1 << i)) != 0) subset.add(arr[i]);
		}
		result.add(subset);
	}
	return result;
}
```

Complexity: $O(n \cdot 2^n)$ time, $O(n)$ auxiliary for each generated subset.

---

## 4. How to Identify Bit Manipulation Problems in Interviews

Look for these signals:

1. **Keywords**: binary, parity, odd/even, power of two, set bits, mask.
2. **Constraints**: need $O(1)$ space or faster than sorting/hashmap.
3. **Pair-cancel logic**: “all elements appear twice except one.”
4. **Subset/state compression**: small `n` (like `n <= 20`) with subset DP.
5. **Bit position operations**: set/clear/toggle/check index.

### Interview decision shortcut

- If you need to **store yes/no flags compactly** => use bitmask.
- If there is **pair cancellation** => try XOR.
- If problem asks **power of 2 / count set bits / rightmost set bit** => direct bit tricks.
- If brute force over subsets is needed and `n` is small => iterate masks.

---

## 5. Important Formulas and Properties

## 5.1 Core Identities

- `x & 0 = 0`
- `x | 0 = x`
- `x ^ 0 = x`
- `x ^ x = 0`
- `x & x = x`
- `x | x = x`
- `~x = -(x + 1)` (two’s complement)

## 5.2 Shift Equivalences (careful about overflow)

- `x << k` roughly `x * 2^k`
- `x >> k` roughly `x / 2^k` (for non-negative values)

## 5.3 Powerful Derived Forms

- `n & (n - 1)` => removes lowest set bit
- `n & -n` => lowest set bit value
- `n | (n + 1)` => sets lowest unset bit

## 5.4 Range / XOR Pattern

XOR of `1` to `n`:

| n % 4 | xor(1..n) |
|---|---|
| 0 | n |
| 1 | 1 |
| 2 | n + 1 |
| 3 | 0 |

```java
int xor1ToN(int n) {
	switch (n & 3) { // n % 4
		case 0: return n;
		case 1: return 1;
		case 2: return n + 1;
		default: return 0;
	}
}
```

Complexity: $O(1)$ time, $O(1)$ space.

---

## 6. Java-Specific Notes (Very Important)

1. `int` = 32-bit signed, `long` = 64-bit signed.
2. Use `1L << k` when `k` might be >= 31.
3. `>>` keeps sign, `>>>` zero-fills.
4. `Integer.bitCount(n)`, `Long.bitCount(n)` are interview-friendly.
5. For unsigned interpretations, often use `>>>` and masking.

Example:

```java
long mask = 1L << 40;   // correct for 64-bit shift
// int bad = 1 << 40;   // wrong expectation (shift on 32-bit int)
```

---

## 7. Easy Memorization Techniques and Mental Shortcuts

## 7.1 Mnemonics

- **AND = check/keep**
- **OR = set/add**
- **XOR = toggle/different**
- **`n & (n-1)` = delete rightmost 1**
- **`n & -n` = isolate rightmost 1**

## 7.2 Fast Mental Anchors

- Powers of 2 up to interview range:
  - $2^{10}=1024$, $2^{16}=65536$, $2^{20}\approx10^6$, $2^{30}\approx10^9$.
- Odd/even check: `n & 1`.
- Last `k` bits mask: `(1 << k) - 1`.

## 7.3 10-second sanity checks

Before submitting:

1. Did I use parentheses around bit checks?
2. Am I handling `n <= 0` for power-of-two?
3. Do I need `long` (`1L << k`) instead of `int`?
4. For negatives, did I intentionally choose `>>` vs `>>>`?

---

## 8. Common Mistakes to Avoid

1. **Precedence mistakes**
   - Always use `(n & mask) != 0` style.

2. **Forgetting signed behavior**
   - `>>` for negative numbers propagates sign bit.

3. **Ignoring overflow with shifts**
   - `1 << 31` in `int` becomes negative.
   - Use `1L << 31` when needed.

4. **Wrong bit index convention**
   - Clarify if k-th bit means 0-indexed from right.

5. **Using XOR swap unnecessarily**
   - Harder to read and easy to misuse when variables alias.

6. **Assuming bit tricks work unchanged for all domains**
   - Always check if numbers can be negative.

---

## 9. Quick Revision Cheat Sheet

| Task | Formula / Trick | Java Snippet | Complexity |
|---|---|---|---|
| Check odd/even | `n & 1` | `(n & 1) == 1` | $O(1), O(1)$ |
| Check k-th bit | `n & (1<<k)` | `((n & (1<<k)) != 0)` | $O(1), O(1)$ |
| Set k-th bit | `n | (1<<k)` | `n |= (1<<k)` | $O(1), O(1)$ |
| Clear k-th bit | `n & ~(1<<k)` | `n &= ~(1<<k)` | $O(1), O(1)$ |
| Toggle k-th bit | `n ^ (1<<k)` | `n ^= (1<<k)` | $O(1), O(1)$ |
| Remove lowest set bit | `n & (n-1)` | `n &= (n-1)` | $O(1), O(1)$ |
| Isolate lowest set bit | `n & -n` | `int l = n & -n;` | $O(1), O(1)$ |
| Power of 2 | `n>0 && (n&(n-1))==0` | same | $O(1), O(1)$ |
| Count set bits | Kernighan loop | `while(n!=0){n&=n-1;cnt++;}` | $O(setBits), O(1)$ |
| Unique element (pairs) | XOR all | `ans ^= x;` | $O(n), O(1)$ |

---

## 10. Practice Problems (Easy → Advanced)

Solve in this order. Focus on identifying pattern first, then coding.

## 10.1 Easy

1. Check if a number is odd/even.
2. Check if k-th bit is set.
3. Set/clear/toggle k-th bit.
4. Count set bits in an integer.
5. Check if number is power of two.

## 10.2 Easy-Medium

6. Single Number (every element appears twice except one).
7. Find rightmost set bit position.
8. XOR of numbers from `L` to `R`.
9. Reverse bits of a 32-bit integer.
10. Number of 1 bits (Hamming weight).

## 10.3 Medium

11. Single Number II (every element appears 3 times except one).
12. Two unique numbers when others appear twice.
13. Subsets generation using bitmask.
14. Maximum XOR of two numbers in array (Trie/bitwise).
15. Divide two integers without `*`, `/`, `%`.

## 10.4 Advanced

16. Minimum XOR pair / XOR basis style questions.
17. Bitmask DP (TSP, assignment, subset transitions).
18. Count set bits from `1` to `n` efficiently.
19. Maximum subset XOR.
20. SOS DP / subset convolution style tasks (for advanced contests).

Interview drill tip:
- For each problem, write: pattern tag, key identity used, time/space target.

---

## 11. Mini Interview Templates (Reusable)

```java
// 1) kth bit operations
boolean isSet(int n, int k) { return (n & (1 << k)) != 0; }
int setBit(int n, int k) { return n | (1 << k); }
int clearBit(int n, int k) { return n & ~(1 << k); }
int toggleBit(int n, int k) { return n ^ (1 << k); }

// 2) power of two
boolean isPowerOfTwo(int n) { return n > 0 && (n & (n - 1)) == 0; }

// 3) count set bits
int countSetBits(int n) {
	int count = 0;
	while (n != 0) {
		n &= (n - 1);
		count++;
	}
	return count;
}

// 4) single number (pairs)
int singleNumber(int[] arr) {
	int ans = 0;
	for (int x : arr) ans ^= x;
	return ans;
}
```

---

## 12. Final Summary

- Bit manipulation is mostly about **patterns + identities**, not raw memorization.
- In interviews, quickly detect whether XOR, mask checks, or subset bitmasking applies.
- Master these core expressions:
  - `n & 1`
  - `n & (n - 1)`
  - `n & -n`
  - `(n & (1 << k)) != 0`
- If you can combine these with clear complexity reasoning, you are interview-ready for most bit questions.
