# Monitor Lock Example (`ML*` files)

This document explains the intuition behind:
- `MLSharedResource.java`
- `MLConsumeTask.java`
- `MLProduceTask.java`
- `MLMain.java`

It also includes a sample output and why output order is non-deterministic.

---

## 1. Monitor Lock Intuition

In Java, every object has an **intrinsic lock** (also called a monitor lock).

When a method is marked `synchronized`:
- A thread must acquire that object’s monitor lock before entering the method.
- Only one thread can hold that monitor for that object at a time.
- Other threads trying to enter synchronized methods on the same object must wait.

In this example, `MLSharedResource` is the shared object. So both producer and consumer synchronize on the same monitor lock.

### `wait()` and `notifyAll()` in monitor terms
- `wait()` can be called only when the thread owns the monitor lock.
- `wait()` releases the monitor lock and puts the thread into waiting state.
- `notifyAll()` wakes waiting threads, but they must re-acquire the monitor lock before continuing.
- Because wakeups can be spurious, condition checks must be inside a `while` loop.

---

## 2. File-by-file intuition

## `MLSharedResource.java`
This is the **shared state + synchronization point**.

- `boolean itemAvailable = false;`
  - Represents whether a produced item is ready.

- `synchronized void addItem()`
  - Producer enters this method with monitor lock.
  - Sets `itemAvailable = true`.
  - Calls `notifyAll()` to wake waiting consumers.

- `synchronized void consumeItem()`
  - Consumer enters with monitor lock.
  - If item is not available, waits in `while (!itemAvailable)`.
  - After wakeup, checks condition again and consumes.
  - Sets `itemAvailable = false` after consuming.

Core role: enforce safe handoff between producer and consumer.

## `MLConsumeTask.java`
This is the **consumer runnable**.

- Holds reference to `MLSharedResource`.
- In `run()`, prints thread name and calls `sharedResource.consumeItem()`.

Core role: a thread that tries to consume; waits if item is not ready.

## `MLProduceTask.java`
This is the **producer runnable**.

- Holds reference to `MLSharedResource`.
- In `run()`:
  - Prints thread name.
  - Sleeps for `10000` ms (10 seconds).
  - Calls `sharedResource.addItem()`.

Core role: intentionally delayed producer so consumer likely reaches waiting state first.

## `MLMain.java`
This is the **orchestration/entry point**.

- Creates one shared object: `MLSharedResource`.
- Creates producer and consumer threads.
- Starts producer, then consumer.

Important intuition: `start()` only makes a thread runnable; it does not guarantee exact execution order.

---

## 3. Execution timeline (current code)

Typical flow for your current code:

1. Main prints start and end quickly.
2. Producer thread starts and prints its name.
3. Producer sleeps for ~10 seconds.
4. Consumer thread starts, enters `consumeItem()`, sees no item, and calls `wait()`.
5. Producer wakes, adds item, calls `notifyAll()`.
6. Consumer wakes, re-checks condition, consumes item.

---

## 4. Sample output

One valid run (captured from current code):

```text
Main Method Starts..
Main Method Ends..
Producer Thread: Thread-0
Consumer Thread: Thread-1
ConsumeItem method invoked by: Thread-1
Consumer Thread: Thread-1 is waiting now..
Producer Item added by: Thread-0 and invoking / notifying all threads which are waiting..
Item consumed by: Thread-1
```

Another valid run might swap the early producer/consumer print order.

---

## 5. Why output is non-deterministic

Output order is non-deterministic because thread scheduling is runtime-driven:

- `start()` does not mean “run immediately in this exact order”.
- JVM + OS scheduler decides which runnable thread gets CPU time first.
- Timing can vary due to CPU load, core scheduling, JIT compilation, and lock contention.
- Console prints from different threads can interleave differently between runs.

What is deterministic in this specific code:
- Producer will sleep ~10 seconds before calling `addItem()`.
- Consumer cannot pass the `while (!itemAvailable)` check until producer sets `itemAvailable = true`.

So correctness stays the same, but exact print sequence can vary.
