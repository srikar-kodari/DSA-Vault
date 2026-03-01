# Floyd's Cycle Detection Algorithm

## Overview
This algorithm finds the starting node of a cycle in a linked list using the **Tortoise & Hare (Fast & Slow Pointers)** approach.

## Algorithm Phases

### Phase 1: Detect if Cycle Exists
- Use two pointers: **slow** (moves 1 step) and **fast** (moves 2 steps)
- If there's a cycle, they will eventually meet
- If fast reaches null, there's no cycle

### Phase 2: Find Cycle Start Point
- Once they meet, reset **slow** to head
- Move both pointers one step at a time
- Where they meet again is the cycle's starting node

## Java Implementation

```java
public static LLNode cycleBeginPoint2(LLNode head) {
    if(head == null || head.next == null) return null;
    
    LLNode fast = head;
    LLNode slow = head;
    
    // Phase 1: Detect cycle
    while(fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        
        if(fast == slow) {  // Cycle found
            // Phase 2: Find cycle start
            slow = head;
            while(slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;  // or return fast, both are equal
        }
    }
    
    return null;  // No cycle
}
```

## Mathematical Proof

**Definitions:**
- `a` = distance from head to cycle start
- `b` = distance from cycle start to meeting point
- `c` = remaining distance in cycle (back to cycle start)

**When pointers meet:**
- Slow traveled: `a + b`
- Fast traveled: `2(a + b)` (moves twice as fast)
- Fast also traveled: `a + b + k(b + c)` (where k ≥ 0 is number of complete cycles)

**Equation:**
```
2(a + b) = a + b + k(b + c)
a + b = k(b + c)
a = k(b + c) - b
a = b(k - 1) + kc
```

**Key Insight:** `a = b(k-1) + kc`

This means when you reset slow to head and move both pointers one step at a time, they meet after exactly `a` steps at the cycle start.

## Time & Space Complexity
- **Time:** O(n) - visits each node at most twice
- **Space:** O(1) - only uses two pointers

## Why This Works

The fast pointer completes at least one full cycle while slow is still in its first traversal. This creates a relationship where the distance from head to cycle start equals the remaining distance needed to complete the cycle when both pointers move simultaneously.

## Edge Cases
- Empty list or single node → return null
- No cycle exists → return null
- Cycle at very beginning → returns head
