# Arithmetic Expression Conversions (Stack-Based)

This note explains **Infix, Prefix, Postfix** and all 6 conversions used in `ImpArithmeticExp.java`.

## 1. Basic Definitions

- **Infix**: Operator is written between operands.
  - Example: `a+b`, `(a+b)*c`
- **Postfix** (Reverse Polish): Operator comes after operands.
  - Example: `ab+`, `ab+c*`
- **Prefix** (Polish): Operator comes before operands.
  - Example: `+ab`, `*+abc`

## 2. Why Stack Intuition Works

A stack helps us **delay operators** until the correct time.

- Operands (`a`, `b`, `1`, etc.) are immediate values, so they can go directly to output (or stack as expression strings).
- Operators must follow precedence and brackets, so we temporarily store them in a stack.
- For reverse conversions (like postfix → infix), stack stores partial expressions and combines them when an operator appears.

## 3. Assumptions in This Code

- Input expression is valid.
- Operands are single characters (`a-z`, `A-Z`, `0-9`).
- Operators are binary.
- No whitespace handling.

## 4. Precedence and Associativity

- `^` has highest priority (`3`) and is **right-associative**.
- `* / %` have priority `2`.
- `+ -` have priority `1`.

Right-associative means for same-priority `^`, expression groups right-to-left:
- `a^b^c` means `a^(b^c)`.

---

## 5. Conversion 1: infixToPostfix

### Intuition
Read left to right. Send operands to output. Use stack for operators and brackets.

### Steps
1. Create empty operator stack and output string.
2. For each character:
   - If operand: append to output.
   - If `(`: push to stack.
   - If `)`: pop until `(` and append popped operators.
   - If operator:
     - Pop while stack top has higher priority, or same priority with left-associative rule.
     - Push current operator.
3. After scan, pop remaining operators to output.
4. Output is postfix.

### Example
- Infix: `(a+b*c-d)`
- Postfix: `abc*+d-`

---

## 6. Conversion 2: infixToPrefix

### Intuition
Direct infix→prefix is tricky. So we:
1) reverse infix,
2) swap brackets,
3) do infix→postfix-like process,
4) reverse result.

### Steps
1. Reverse the infix string.
2. Swap every `(` with `)` and every `)` with `(`.
3. Run infix-to-postfix logic on this modified string.
   - In equal-priority tie, associativity check differs for this reversed flow.
4. Reverse produced postfix string.
5. Result is prefix.

### Example
- Infix: `(a+b*c-d)`
- Prefix: `-+a*bcd`

---

## 7. Conversion 3: postfixToInfix

### Intuition
Postfix gives operator after operands, so when an operator appears, two latest expressions are ready on stack.

### Steps
1. Create stack of strings.
2. Scan postfix left to right.
3. If operand: push as string.
4. If operator:
   - Pop `top1` (first pop), then pop `top2` (second pop).
   - Build `"(" + top2 + op + top1 + ")"`.
   - Push back.
5. Final stack top is infix.

### Example
- Postfix: `abc*+d-`
- Infix: `((a+(b*c))-d)`

---

## 8. Conversion 4: prefixToInfix

### Intuition
In prefix, operator comes before operands, so scan from right to left.

### Steps
1. Create stack of strings.
2. Scan prefix right to left.
3. If operand: push.
4. If operator:
   - Pop `top1` (first pop), then pop `top2` (second pop).
   - Build `"(" + top1 + op + top2 + ")"`.
   - Push back.
5. Final stack top is infix.

### Example
- Prefix: `-+a*bcd`
- Infix: `((a+(b*c))-d)`

---

## 9. Conversion 5: postfixToPrefix

### Intuition
Build prefix pieces directly from postfix using expression stack.

### Steps
1. Create stack of strings.
2. Scan postfix left to right.
3. If operand: push.
4. If operator:
   - Pop `top1` (first pop), then pop `top2` (second pop).
   - Build `op + top2 + top1`.
   - Push back.
5. Final stack top is prefix.

### Example
- Postfix: `abc*+d-`
- Prefix: `-+a*bcd`

---

## 10. Conversion 6: prefixToPostfix

### Intuition
Build postfix pieces directly from prefix by scanning from right to left.

### Steps
1. Create stack of strings.
2. Scan prefix right to left.
3. If operand: push.
4. If operator:
   - Pop `top1` (first pop), then pop `top2` (second pop).
   - Build `top1 + top2 + op`.
   - Push back.
5. Final stack top is postfix.

### Example
- Prefix: `-+a*bcd`
- Postfix: `abc*+d-`

---

## 11. Complexity Summary

For all 6 methods in this implementation:

- **Time Complexity**: `O(n)`
- **Space Complexity**: `O(n)`

where `n` is expression length.

---

## 12. Quick Notes / Common Mistakes

- Wrong pop order breaks expression (`left` and `right` matter).
- In this code, variables are named `top1` and `top2`; which one is left/right depends on conversion direction.
- Forgetting to pop until `(` in infix conversions gives wrong output.
- Not handling `^` associativity correctly can fail on `a^b^c`.
- Multi-digit numbers / multi-letter variables are not supported in this version.
