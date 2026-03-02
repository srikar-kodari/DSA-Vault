# Java Multithreading Locks — Beginner to Advanced

This guide explains core Java locking primitives and lock design choices from practical engineering and interview perspectives.

## Core Mental Model

- A **lock** coordinates access to shared mutable state.
- Correct locking gives:
  - **Mutual exclusion** (only allowed threads proceed)
  - **Visibility** (writes by one thread become visible to others)
  - **Ordering** (happens-before guarantees)
- In Java, locking is available through:
  - **Intrinsic monitor locks** via `synchronized`
  - **Explicit locks** via `java.util.concurrent.locks`
  - **Permit-based coordination** via `Semaphore`

---

## 1. Intrinsic Lock (`synchronized`)

### Definition

`synchronized` uses an object's monitor lock. A thread entering a synchronized method/block acquires that monitor; others block until it is released.

### Internal Working

- Each object has an associated monitor.
- Entering `synchronized(obj)` acquires monitor of `obj`.
- Exiting block (or method) releases monitor automatically.
- Monitor operations establish happens-before relationships.

### Important Keywords/Methods

- `synchronized` method/block: mutual exclusion.
- `wait()`: release monitor and wait.
- `notify()`, `notifyAll()`: wake waiting threads on same monitor.

### When to Use

- Simple critical sections.
- Low-complexity producer-consumer coordination.
- Legacy codebases or minimal dependencies.

### Real-Life Analogy

Single-key meeting room: whoever has the key enters; others wait outside.

### Real-World Use Cases

- Protecting in-memory counters.
- Guarding simple mutable structures in services.
- Classic monitor-based producer-consumer examples.

### Advantages

- Built into language, very readable.
- Automatic unlock on scope exit.
- Hard to forget unlock.

### Disadvantages

- No timed lock attempt (`tryLock(timeout)` equivalent unavailable).
- One implicit condition queue per monitor.
- Less flexible than explicit locks.

### Performance Considerations

- Modern JVMs optimize monitors heavily.
- Under high contention, monitor inflation/context switches increase cost.

### Common Mistakes

- Calling `wait/notify` outside synchronized block (throws `IllegalMonitorStateException`).
- Using `notify()` when `notifyAll()` is required for correctness.
- Synchronizing on public/shared objects (`this`, interned strings) unintentionally.

### Interview Insight

`synchronized` is reentrant and provides visibility + mutual exclusion; it is not just "blocking code".

---

## 2. ReentrantLock

### Definition

`ReentrantLock` is an explicit mutual exclusion lock with advanced APIs: interruptible lock acquisition, timed acquisition, fairness policy, and conditions.

### Internal Working

- Backed by AQS (AbstractQueuedSynchronizer).
- Tracks owner thread and hold count (reentrancy).
- Contending threads queue in synchronization queue.

### Important Methods

- `lock()`: acquire, block indefinitely.
- `unlock()`: release.
- `tryLock()`: non-blocking attempt.
- `tryLock(long, TimeUnit)`: timed attempt.
- `lockInterruptibly()`: acquisition can be interrupted.
- `newCondition()`: create condition variable(s).
- `isHeldByCurrentThread()`, `getHoldCount()`: diagnostics.

### When to Use

- Need more control than `synchronized`.
- Need timeout/cancelability.
- Need multiple wait queues (`Condition`).

### Real-Life Analogy

VIP desk with queue system where people can leave if waiting too long.

### Real-World Use Cases

- Rate-limiters and schedulers with timed acquisition.
- Complex workflows requiring multiple condition queues.
- Lock ordering strategies in infrastructure services.

### Advantages

- Rich API and operational introspection.
- Interruptible and timed lock acquisition.
- Multiple conditions per lock.

### Disadvantages

- Must release manually (`finally` mandatory).
- Easier to leak lock and deadlock if misused.

### Performance Considerations

- Non-fair mode often higher throughput.
- Fair mode reduces starvation risk but increases context-switch overhead.

### Common Mistakes

- Forgetting `unlock()` in `finally`.
- Mixing lock and monitor semantics accidentally.
- Creating lock per method call (no shared protection).

### Interview Insight

Use `ReentrantLock` when you need control (timeouts, interrupts, conditions), not by default for every critical section.

---

## 3. ReadWriteLock (`ReentrantReadWriteLock`)

### Definition

Provides two lock modes:

- **Read lock (shared):** multiple readers can enter concurrently.
- **Write lock (exclusive):** single writer, blocks readers/writers.

### Internal Working

- Shared read count + exclusive write ownership.
- Read and write lock state managed in AQS state bits.
- Supports optional fairness policy.

### Important Methods

- `readLock().lock()/unlock()`
- `writeLock().lock()/unlock()`
- `writeLock().tryLock(...)`

### When to Use

- Read-heavy workloads with infrequent writes.
- Large structures where concurrent reads matter.

### Real-Life Analogy

Library: many readers can browse together; catalog editor gets exclusive access.

### Real-World Use Cases

- In-memory configuration maps.
- Metadata caches.
- Routing table lookups with occasional updates.

### Advantages

- Better read throughput than exclusive lock under read-heavy load.
- Clear semantic separation of read vs write operations.

### Disadvantages

- More complex than single lock.
- Poor benefits when writes are frequent.
- Potential writer starvation in non-fair settings.

### Performance Considerations

- Benefit depends on read/write ratio and critical-section size.
- Tiny critical sections can lose gains due to lock overhead.

### Common Mistakes

- Acquiring write lock for read-only path.
- Lock upgrade assumptions (read to write) causing deadlock patterns.
- Holding lock during slow I/O.

### Interview Insight

Read-write locks help only when reads are frequent and contention exists; otherwise a simple lock may be faster and safer.

---

## 4. StampedLock

### Definition

`StampedLock` supports:

- **Write lock** (exclusive)
- **Read lock** (shared)
- **Optimistic read** (non-blocking, validate-before-use)

### Internal Working

- `tryOptimisticRead()` returns a **stamp** (version token).
- Read fields without locking, then call `validate(stamp)`.
- If invalid, fall back to read lock and re-read safely.

### Important Methods

- `writeLock()`, `unlockWrite(stamp)`
- `readLock()`, `unlockRead(stamp)`
- `tryOptimisticRead()`, `validate(stamp)`
- `tryConvertToWriteLock(stamp)`

### When to Use

- Very read-heavy scenarios where occasional retries are acceptable.
- Performance-sensitive data structures with short write sections.

### Real-Life Analogy

Glance at a dashboard snapshot and verify timestamp before acting.

### Real-World Use Cases

- Geometry/state snapshot reads.
- Cache/index metadata lookup with infrequent updates.

### Advantages

- Optimistic read can outperform read-lock paths under low write contention.
- Flexible lock conversion APIs.

### Disadvantages

- More complex correctness model.
- Not reentrant.
- Misuse can read inconsistent data if validation discipline is weak.

### Performance Considerations

- Great when writes are rare; degrades toward lock path with frequent writes.
- Validation/retry cost must be measured in production-like loads.

### Common Mistakes

- Forgetting to validate optimistic stamp.
- Using optimistic read for multi-step logic without re-check.
- Assuming reentrancy.

### Interview Insight

Optimistic read is an optimization pattern, not a correctness shortcut; always validate and fallback.

---

## 5. Semaphore

### Definition

`Semaphore` controls access using a fixed number of permits.

- Binary semaphore (1 permit) ~ mutex behavior.
- Counting semaphore (N permits) allows up to N concurrent entrants.

### Internal Working

- AQS-based permit counter.
- `acquire()` decrements permit or blocks if none available.
- `release()` increments permit and may unblock waiters.

### Important Methods

- `acquire()`, `acquireUninterruptibly()`
- `tryAcquire()`, `tryAcquire(timeout, unit)`
- `release()`
- `availablePermits()`

### When to Use

- Bound concurrent usage of finite resources.
- Throttle parallelism.

### Real-Life Analogy

Parking lot with 50 spots; entry allowed only if a slot is free.

### Real-World Use Cases

- Connection pool concurrency caps.
- API call concurrency limits.
- Controlled worker throughput.

### Advantages

- Simple concurrency throttling.
- Works for resource pools and backpressure.

### Disadvantages

- Not tied to ownership thread (can release from different thread).
- Miscounting permits can break limits.

### Performance Considerations

- Good for bulk throughput control.
- Excessive acquire/release in tiny sections can add overhead.

### Common Mistakes

- Forgetting release on exceptional path.
- Using semaphore where data consistency requires mutex/lock discipline.

### Interview Insight

Semaphore is about capacity control; lock is about critical-section ownership and consistency.

---

## 6. Exclusive Lock (Concept)

### Definition

Only one thread can hold lock and access the protected section at a time.

### Examples

- `synchronized`
- `ReentrantLock`
- `ReadWriteLock.writeLock()`
- `StampedLock.writeLock()`

### Use Case

Updates requiring strict consistency of shared mutable state.

### Pitfall

Overusing exclusivity in read-heavy systems reduces throughput.

---

## 7. Shared Lock (Concept)

### Definition

Multiple threads can hold lock simultaneously for read-only operations.

### Examples

- `ReadWriteLock.readLock()`
- `StampedLock.readLock()`

### Use Case

High-volume reads where writers are relatively rare.

### Pitfall

Writer starvation and longer write latency under constant readers.

---

## 8. Optimistic Lock (Concept)

### Definition

Assume conflicts are rare: read/update without immediate blocking, then verify (version/stamp/CAS). Retry on conflict.

### Internal Patterns

- Version number check.
- Compare-and-set loops.
- `StampedLock` optimistic stamp validate.

### Use Case

Low-conflict, high-read systems.

### Pitfall

Frequent conflicts can cause many retries and worse latency.

---

## 9. Pessimistic Lock (Concept)

### Definition

Assume conflicts are likely: acquire lock first, then operate, blocking competing threads.

### Examples

- `synchronized`
- `ReentrantLock.lock()`
- Database `SELECT ... FOR UPDATE`

### Use Case

High contention, expensive conflicts, strict ordering needs.

### Pitfall

Reduced concurrency and potential deadlocks if lock ordering is poor.

---

## 10. Fair vs Non-Fair Locks

### Definition

- **Fair lock:** grants access roughly in waiting order (FIFO tendency).
- **Non-fair lock:** allows barging; newly arriving thread may acquire lock before queued thread.

### In Java

- `new ReentrantLock(true)` => fair
- `new ReentrantLock(false)` => non-fair (default)
- `new ReentrantReadWriteLock(true|false)`

### Trade-Off

- Fair: better starvation behavior, usually lower throughput.
- Non-fair: better throughput, less predictable wait times.

### Mistake

Choosing fairness globally without measuring impact.

---

## 11. Condition Variables (`await/signal`)

### Definition

`Condition` lets threads wait for specific predicates while using `ReentrantLock`.

### Internal Working

- `await()`:
  - must hold lock
  - atomically releases lock and parks thread
  - on signal, re-acquires lock before returning
- `signal()` wakes one waiter; `signalAll()` wakes all.

### Important Methods

- `await()`, `awaitUninterruptibly()`, `awaitNanos(...)`
- `signal()`, `signalAll()`

### When to Use

- Multiple wait conditions with one lock (e.g., `notEmpty` and `notFull`).

### Analogy

Different waiting lines at same service counter (billing vs support).

### Common Mistakes

- Using `if` instead of `while` around `await` (spurious wakeups).
- Calling `signal` without lock held.
- Forgetting to re-check predicate after wakeup.

### Interview Insight

`Condition` is the explicit-lock counterpart of monitor wait/notify, but supports multiple independent wait queues.

---

## 12. Comparison Table

| Concept / Lock | Access Model | Reentrant | Timed/Interruptible Acquire | Shared Reads | Optimistic Mode | Typical Strength | Typical Risk |
|---|---|---|---|---|---|---|---|
| `synchronized` | Exclusive monitor | Yes | No / No | No | No | Simplicity, safety | Limited flexibility |
| `ReentrantLock` | Exclusive explicit | Yes | Yes / Yes | No | No | Control and diagnostics | Missing unlock in finally |
| `ReadWriteLock` | Shared read + exclusive write | Yes | Yes (write) | Yes | No | Read-heavy throughput | Write starvation/complexity |
| `StampedLock` | Read/write + optimistic | No | Limited patterns | Yes | Yes | Read-mostly performance | Complexity, validation errors |
| `Semaphore` | Permit-based gating | N/A | Yes / Yes | N/A | No | Resource throttling | Permit leaks/miscounts |
| Optimistic lock (concept) | Validate-and-retry | Varies | Varies | Varies | Yes | High concurrency when conflicts rare | Retry storms |
| Pessimistic lock (concept) | Lock-before-work | Varies | Varies | Varies | No | Strong consistency under contention | Blocking/latency |

---

## 13. Performance Considerations (Practical)

- Measure under representative load; lock performance is workload dependent.
- Prefer shorter critical sections.
- Keep blocking I/O outside locks.
- Non-fair often improves throughput.
- Read-write locks help only with significant read dominance and contention.
- Optimistic techniques win when conflicts are rare and retries are cheap.

### Microbenchmark Caution

- Use JMH for lock benchmarking; ad-hoc loops are misleading due to JIT warmup and scheduling effects.

---

## 14. Common Developer Mistakes Across Locking

1. Forgetting `unlock()` in error path.
2. Locking on mutable/public monitor objects.
3. Nested locks without strict global ordering.
4. Long critical sections including sleep/I/O.
5. Assuming fairness means strict deterministic ordering.
6. Ignoring interruption/cancellation semantics.
7. Using lock-heavy design where immutability/message passing would be cleaner.

---

## 15. Decision Tree — Which Lock Should I Use?

```text
Need to protect shared mutable state?
├─ No -> No lock needed (prefer immutable / thread-confined design)
└─ Yes
   ├─ Need only simple mutual exclusion?
   │  ├─ Yes -> synchronized
   │  └─ No
   │     ├─ Need timeout/interruptible lock or multiple wait queues?
   │     │  ├─ Yes -> ReentrantLock (+ Condition)
   │     │  └─ No
   │     │     ├─ Read-heavy workload with rare writes?
   │     │     │  ├─ Yes -> ReadWriteLock or StampedLock
   │     │     │  │  ├─ Need optimistic reads for performance? -> StampedLock
   │     │     │  │  └─ Prefer simpler reentrant API? -> ReadWriteLock
   │     │     │  └─ No -> ReentrantLock or synchronized
   └─ Need to limit parallel access count (N at once)?
      └─ Semaphore
```

---

## 16. Real-World Mapping Examples

- API gateway request throttling -> `Semaphore`
- Inventory/counter updates -> exclusive lock (`synchronized`/`ReentrantLock`)
- Config cache reads with occasional refresh -> `ReadWriteLock`
- High-frequency coordinate/state snapshots -> `StampedLock` optimistic reads
- Producer-consumer bounded queue -> `ReentrantLock` + two `Condition`s

---

## 17. Interview Q&A (10+)

### 1. Why choose `ReentrantLock` over `synchronized`?

Need features like timed lock acquisition, interruptible waits, fairness control, and multiple conditions.

### 2. Is `synchronized` reentrant?

Yes. Same thread can re-enter synchronized code guarded by same monitor.

### 3. What is the biggest risk with `ReentrantLock`?

Forgetting `unlock()` in `finally`, causing deadlock.

### 4. When does `ReadWriteLock` help most?

High contention + high read-to-write ratio + non-trivial critical section.

### 5. What is optimistic locking?

Proceed without immediate blocking, then verify version/stamp and retry on conflict.

### 6. Can `StampedLock` optimistic read be trusted without validation?

No. `validate(stamp)` is mandatory before using data as consistent.

### 7. Fair vs non-fair lock in one sentence?

Fair reduces starvation likelihood; non-fair usually improves throughput.

### 8. Difference between `wait/notify` and `Condition`?

`Condition` provides multiple explicit wait queues per lock; monitor has one implicit queue.

### 9. Why loop around `await()`/`wait()` predicate checks?

Spurious wakeups and competing threads can invalidate condition before resumed thread runs.

### 10. Can semaphore replace a mutex?

A binary semaphore can mimic mutual exclusion but lacks ownership semantics of lock/mutex.

### 11. What is lock contention cost?

Queued threads, context switches, cache coherence traffic, and latency spikes under load.

### 12. How to reduce deadlock risk?

Consistent lock ordering, minimize nested locks, timeouts/tryLock fallback paths, and monitoring.

---

## 18. Summary Comparison Table (Quick Reference)

| Need | Best Starting Choice | Why |
|---|---|---|
| Simple critical section | `synchronized` | Minimal and safe |
| Timed/interruptible lock | `ReentrantLock` | API control |
| Multiple wait conditions | `ReentrantLock` + `Condition` | Separate wait queues |
| Read-heavy data | `ReadWriteLock` | Shared reads |
| Extreme read-mostly optimization | `StampedLock` | Optimistic reads |
| Limit concurrent users/resources | `Semaphore` | Permit model |
| High conflict updates | Pessimistic locking | Predictable correctness |
| Low conflict updates | Optimistic locking | Better concurrency |

---

## 19. Demo Files in This Project

- `locks/ReentrantLockExample.java`
- `locks/ReadWriteLockExample.java`
- `locks/StampedLockExample.java`
- `locks/SemaphoreExample.java`
- `locks/SynchronizedLockExample.java`
- `locks/OptimisticLockExample.java`
- `locks/PessimisticLockExample.java`
- `locks/FairLockExample.java`
- `locks/ConditionExample.java`

Compile and run from project root:

```bash
javac locks/*.java
java locks.ReentrantLockExample
java locks.ReadWriteLockExample
java locks.StampedLockExample
java locks.SemaphoreExample
java locks.SynchronizedLockExample
java locks.OptimisticLockExample
java locks.PessimisticLockExample
java locks.FairLockExample
java locks.ConditionExample
```
