# Byte Streams in Java

Concise revision notes for Java byte streams: binary/raw I/O, common classes, comparisons, and interview points. For character-stream concepts, see [CharacterStreams.md](../CharacterStreams/CharacterStreams.md). Runnable practice examples live in this folder (`InputStreamExample.java`, `FileInputStreamExample.java`, and others).

---

## 1. Introduction to Byte Streams

### What are byte streams?

Byte streams are Java I/O classes that read and write **raw bytes** (`byte`, 8 bits). They work directly with the binary content of a file or data source without automatic character encoding.

- **Input side**: `InputStream` and subclasses (`FileInputStream`, `BufferedInputStream`, `ByteArrayInputStream`, …)
- **Output side**: `OutputStream` and subclasses (`FileOutputStream`, `BufferedOutputStream`, `ByteArrayOutputStream`, …)

### Difference between byte streams and character streams

| Aspect | Byte streams | Character streams |
|--------|--------------|-------------------|
| Base classes | `InputStream`, `OutputStream` | `Reader`, `Writer` |
| Unit of I/O | `byte` (8 bits) | `char` (16-bit in Java) |
| Best for | Binary data (images, PDF, ZIP) | Text files, logs, config |
| Encoding | You manage bytes ↔ text yourself | Stream decodes/encodes using a charset |
| Typical classes | `FileInputStream`, `BufferedInputStream` | `FileReader`, `BufferedReader` |

> **Revision point:** Use byte streams for **binary** data. Use character streams for **human-readable text** when you want encoding handled for you.

### Bytes on disk and text

- Files on disk are always stored as **bytes**.
- For **text**, a **charset** (encoding) defines how bytes map to characters (for example UTF-8).
- Byte streams give you those raw bytes; if the content is text, you convert explicitly:

```java
byte[] bytes = text.getBytes(StandardCharsets.UTF_8);   // String → bytes
String text = new String(bytes, StandardCharsets.UTF_8); // bytes → String
```

### Why byte streams are used

- Correct handling of **binary** files (no accidental character conversion).
- Exact control over file content (copy, merge, hash, compress).
- Natural fit for images, audio, video, PDF, ZIP, serialized objects, and network protocols.
- Foundation for higher-level APIs that wrap bytes (compression, encryption).

---

## 2. InputStream and OutputStream

### Purpose of InputStream and OutputStream classes

- **`InputStream`** — abstract base for **reading bytes** from a source (file, byte array, network wrapper, etc.).
- **`OutputStream`** — abstract base for **writing bytes** to a destination.

Concrete subclasses provide the actual source or sink (`FileInputStream` → file, `ByteArrayInputStream` → `byte[]` in memory).

### Common methods

| Method | Class | Purpose |
|--------|-------|---------|
| `read()` | `InputStream` | Read one byte; returns `int` or `-1` at EOF |
| `read(byte[] b)` | `InputStream` | Read into array; returns count or `-1` |
| `read(byte[] b, int off, int len)` | `InputStream` | Read into part of array |
| `write(int b)` | `OutputStream` | Write single byte (low 8 bits) |
| `write(byte[] b)` | `OutputStream` | Write entire array |
| `write(byte[] b, int off, int len)` | `OutputStream` | Write part of array |
| `flush()` | `OutputStream` | Push buffered data to destination |
| `close()` | Both | Release resources; output usually flushes first |

### read(), write(), close(), flush()

**`read()`**

- Returns the next byte as an **`int`** in range `0`–`255`, or **`-1`** at end of stream.
- Returns `int` (not `byte`) so **`-1` is not confused** with a valid byte value (`255` as signed `byte` is `-1).

**`write()`**

- Writes bytes to the destination (may be buffered internally).

**`flush()`**

- Forces buffered output to be written immediately.
- Important before relying on file content while the stream is still open.

**`close()`**

- Closes the stream and releases resources.
- On `OutputStream`, `close()` typically calls `flush()` first.

> **Exam fact:** `read()` returns **`int`**, not `byte`, because **`-1` means EOF**.

### Simple examples

**Reading with InputStream (ByteArrayInputStream as source):**

```java
byte[] sourceBytes = "InputStream reads raw bytes.".getBytes(StandardCharsets.UTF_8);

try (InputStream input = new ByteArrayInputStream(sourceBytes)) {
    int value;
    while ((value = input.read()) != -1) {
        System.out.print((char) value);  // OK for ASCII demo only
    }
}
```

**Writing with OutputStream (ByteArrayOutputStream as destination):**

```java
try (ByteArrayOutputStream storage = new ByteArrayOutputStream();
        OutputStream output = storage) {
    output.write("Hello, OutputStream!".getBytes(StandardCharsets.UTF_8));
    output.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
    // use storage.toByteArray() or storage.toString(UTF_8)
}
```

### Important notes

- Do not cast `read()` to `byte` before checking for `-1` — signed `byte` `-1` is valid data (`255`).
- Prefer **`read(byte[] buffer)`** or **`BufferedInputStream`** over one-byte loops for files.
- **`OutputStream`** is for raw bytes; for text files prefer `Writer` unless you need byte-level control.
- Use **try-with-resources** so `close()` runs even when an exception occurs.
- When converting bytes to text, always specify **`StandardCharsets.UTF_8`**.

> **Practice:** See `InputStreamExample.java` and `OutputStreamExample.java` in this folder.

---

## 3. FileInputStream

### What it is

`FileInputStream` is a convenience class that reads **raw bytes from a file**. It extends `InputStream` and is the standard choice for binary files and byte-for-byte file copying.

### Reading bytes from files

- Opens a file for **byte** input.
- Reads the file exactly as stored on disk — no charset decoding.
- For text displayed as `String`, decode explicitly with a charset after reading bytes.

### Common methods

Inherits from `InputStream`: `read()`, `read(byte[])`, `read(byte[], int, int)`, `close()`. No line-based API — use `BufferedReader` on a text file, or read bytes and split manually.

### Example explanation

```java
try (FileInputStream input = new FileInputStream("data.bin")) {
    int value;
    StringBuilder content = new StringBuilder();

    while ((value = input.read()) != -1) {
        content.append((char) value);  // only for simple ASCII text demos
    }

    System.out.println(content);
}
```

- `read()` returns one byte per call (or `-1` at EOF).
- Simple but **slow for large files** because each `read()` may trigger underlying disk I/O.

**Chunk read (faster):**

```java
byte[] buffer = new byte[1024];
int bytesRead;

try (FileInputStream input = new FileInputStream("large.bin")) {
    while ((bytesRead = input.read(buffer)) != -1) {
        // process buffer[0 .. bytesRead-1]
    }
}
```

### Advantages and limitations

| Advantages | Limitations |
|------------|-------------|
| Easy API for any file | No built-in text/line handling |
| Preserves exact binary content | One-byte reads are inefficient |
| Works with try-with-resources | Not charset-aware |
| Good for copy, images, learning | Should wrap in `BufferedInputStream` for real use |

### When to use

- Images, PDF, ZIP, and other binary formats.
- Copying files byte-for-byte.
- As the **underlying stream** inside `BufferedInputStream` for production-style file reading.

> **Practice:** See `FileInputStreamExample.java` in this folder.

---

## 4. FileOutputStream

### Writing bytes into files

`FileOutputStream` writes **raw bytes** to a file. It extends `OutputStream`. Text must be converted to bytes before writing.

### Overwriting vs append mode

| Mode | Constructor pattern | Behavior |
|------|----------------------|----------|
| Overwrite (default) | `new FileOutputStream(path)` | Truncates file if it exists; writes from start |
| Append | `new FileOutputStream(path, true)` | Writes at **end** of file; keeps existing content |

```java
// Overwrite
try (FileOutputStream output = new FileOutputStream("log.bin")) {
    output.write(data);
}

// Append
try (FileOutputStream output = new FileOutputStream("log.bin", true)) {
    output.write(moreData);
}
```

### Writing text as bytes

```java
String message = "Hello Java";
output.write(message.getBytes(StandardCharsets.UTF_8));
```

Always specify the charset when converting `String` → `byte[]`.

### flush() and close()

- **`flush()`** — sends buffered bytes to the OS/file without closing the stream.
- **`close()`** — flushes (if buffered) and releases the file handle.

Use `flush()` when another process must see data **before** closing.

### Example explanation

```java
Path file = Path.of("report.bin");

try (FileOutputStream output = new FileOutputStream(file.toFile())) {
    output.write("Title: Sales Report\n".getBytes(StandardCharsets.UTF_8));
    output.write("Status: Draft\n".getBytes(StandardCharsets.UTF_8));
}
```

After this block, the file contains only what was written (previous content was replaced).

### Best practices

- Use **`StandardCharsets.UTF_8`** when writing text as bytes.
- Use **append mode** for logs and audit trails that must keep old data.
- Wrap in **`BufferedOutputStream`** for many small writes.
- Use try-with-resources.

> **Practice:** See `FileOutputStreamExample.java` in this folder.

---

## 5. BufferedInputStream

### Why buffering is needed

Each `read()` on an unbuffered `FileInputStream` can cause a **disk read**. Reading one byte at a time is expensive. **Buffering** reads a **chunk** of bytes into memory and serves `read()` calls from that chunk.

### Internal working of buffering

1. `BufferedInputStream` wraps another `InputStream` (for example `FileInputStream`).
2. It fills an internal **byte array** (default size **8192** bytes).
3. `read()` / `read(byte[])` take data from the buffer until empty, then refill from the wrapped stream.

```mermaid
flowchart LR
  app[Your_code] --> bis[BufferedInputStream]
  bis --> buffer[Internal_byte_buffer]
  buffer --> fis[FileInputStream]
  fis --> file[File_bytes_on_disk]
```

### Chunk reads

- `read(byte[] buffer)` fills your array from the internal buffer (refilling from disk as needed).
- Returns the number of bytes read, or **`-1`** at EOF.
- Use only `buffer[0 .. bytesRead - 1]` when processing — the rest may be stale.

```java
byte[] buffer = new byte[24];
int bytesRead;

try (BufferedInputStream input = new BufferedInputStream(
        new FileInputStream("data.bin"))) {

    while ((bytesRead = input.read(buffer)) != -1) {
        String part = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
        // process part
    }
}
```

### Performance benefits

- Far fewer system calls than byte-by-byte `FileInputStream`.
- Essential for large files and copy operations.

### Example explanation

```java
byte[] buffer = new byte[1024];
int count;

try (BufferedInputStream input = new BufferedInputStream(
        new FileInputStream("large.bin"))) {

    while ((count = input.read(buffer)) != -1) {
        output.write(buffer, 0, count);  // write only valid bytes
    }
}
```

### Common interview questions

1. **Why wrap FileInputStream in BufferedInputStream?** — To reduce I/O calls and improve performance.
2. **What does read(byte[]) return at EOF?** — `-1`.
3. **Must you use the full buffer after read?** — No; use only the first `bytesRead` elements.
4. **Default buffer size?** — 8192 bytes (know “about 8K” for revision).

> **Practice:** See `BufferedInputStreamExample.java` in this folder.

---

## 6. BufferedOutputStream

### Buffered writing concept

`BufferedOutputStream` wraps an `OutputStream` (for example `FileOutputStream`), accumulates writes in an internal buffer, and writes to the underlying stream in **larger chunks**.

### How it works

- Small `write()` calls go into the buffer.
- When the buffer is full (or on `flush()` / `close()`), data is written to the wrapped stream in one larger operation.

```java
try (BufferedOutputStream output = new BufferedOutputStream(
        new FileOutputStream("out.bin"))) {

    output.write(chunk1);
    output.write(chunk2);
}
```

### Performance advantages

- Many small `write()` calls become fewer disk writes.
- Essential for generating large binary files, logs as bytes, or exports.

### Example explanation

```java
try (BufferedOutputStream output = new BufferedOutputStream(
        new FileOutputStream("report.bin"))) {

    output.write("Section 1\n".getBytes(StandardCharsets.UTF_8));
    output.write("Section 2\n".getBytes(StandardCharsets.UTF_8));
    output.flush();  // optional: make data visible before close
}
```

### flush() importance

- Data may sit in the buffer until it is full or the stream is closed.
- Call **`flush()`** when other code must read the file **immediately** while the stream is still open.
- **`close()`** flushes automatically; do not rely on flush alone to release resources.

> **Practice:** See `BufferedOutputStreamExample.java` in this folder.

---

## 7. ByteArrayInputStream

### Reading bytes from memory

`ByteArrayInputStream` treats a **`byte[]` already in memory** as an input source. It extends `InputStream` and does not touch the file system.

### Use cases

- Parsing data from an API response already in a `byte[]`.
- Unit tests without creating temp files.
- Feeding `InputStream`-based APIs when data is not on disk.
- Replaying serialized or compressed bytes in memory.

### Example explanation

```java
byte[] data = "ByteArrayInputStream reads bytes from memory."
        .getBytes(StandardCharsets.UTF_8);

try (ByteArrayInputStream input = new ByteArrayInputStream(data)) {
    int value;
    while ((value = input.read()) != -1) {
        System.out.print((char) value);
    }
}
```

Same `read()` loop as `FileInputStream`, but the source is RAM, not disk.

### Difference from FileInputStream

| | ByteArrayInputStream | FileInputStream |
|---|----------------------|-----------------|
| Source | `byte[]` in memory | File on disk |
| Persistence | None (array must exist) | Reads from filesystem |
| IOException on read | Unusual for normal use | Common (missing file, permissions) |
| Typical use | Tests, APIs, in-memory pipelines | Images, PDF, file copy |

> **Practice:** See `ByteArrayInputStreamExample.java` in this folder.

---

## 8. ByteArrayOutputStream

### Writing data into memory using bytes

`ByteArrayOutputStream` collects written bytes in an internal **growable byte array**. Nothing is written to disk until you copy the result elsewhere.

### Internal buffer concept

- Each `write()` adds to the buffer.
- The buffer grows automatically as needed.
- Efficient for building binary or text-as-bytes output step by step in memory.

### toByteArray() and size()

| Method | Returns | Notes |
|--------|---------|-------|
| `toByteArray()` | `byte[]` | Copy of all bytes written so far; common final step |
| `size()` | `int` | Number of bytes currently in the buffer |
| `toString(Charset)` | `String` | Decode buffer as text (when content is text) |

```java
ByteArrayOutputStream output = new ByteArrayOutputStream();
output.write("Hello".getBytes(StandardCharsets.UTF_8));
output.write(", world!".getBytes(StandardCharsets.UTF_8));

byte[] result = output.toByteArray();
String text = new String(result, StandardCharsets.UTF_8);
```

### Use cases

- Building HTTP/request bodies before sending.
- Collecting compressed or serialized bytes in memory.
- Testing `OutputStream` logic without file I/O.
- Temporary storage before writing once to `FileOutputStream`.

### Example explanation

```java
try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
    output.write("Line 1".getBytes(StandardCharsets.UTF_8));
    output.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
    output.write("Line 2".getBytes(StandardCharsets.UTF_8));

    byte[] result = output.toByteArray();
    System.out.println(new String(result, StandardCharsets.UTF_8));
    System.out.println("Total bytes: " + result.length);
}
```

### Important notes

- Call **`toByteArray()`** when you need the raw bytes for another API or file write.
- Use **`new String(bytes, UTF_8)`** only when you know the content is text.
- **`toByteArray()`** returns a **copy** — safe to use after closing the stream.

> **Practice:** See `ByteArrayOutputStreamExample.java` and `OutputStreamExample.java` in this folder.

---

## 9. Comparison Sections

### InputStream vs Reader

| | InputStream | Reader |
|---|-------------|--------|
| Unit | `byte` (8-bit) | `char` (16-bit) |
| Data type | Binary or raw bytes | Text |
| Encoding | Not handled | Handled by character stream |
| `read()` return | `int` byte value or `-1` | `int` char value or `-1` |
| Typical use | Images, PDF, ZIP | `.txt`, logs, config |

### OutputStream vs Writer

| | OutputStream | Writer |
|---|--------------|--------|
| Unit | `byte` | `char` |
| Data type | Binary | Text |
| Methods | `write(byte[])`, `write(int)` | `write(String)`, `append` |
| Typical use | Binary files, exact byte control | Text files, reports |

### FileInputStream vs BufferedInputStream

| | FileInputStream | BufferedInputStream |
|---|-----------------|---------------------|
| Role | Direct file → bytes | Wraps another InputStream |
| Performance | Slower for many reads | Much faster |
| Typical pattern | Learning / small files | `new BufferedInputStream(new FileInputStream(...))` |

### FileOutputStream vs BufferedOutputStream

| | FileOutputStream | BufferedOutputStream |
|---|------------------|----------------------|
| Role | Direct bytes → file | Wraps another OutputStream |
| Performance | Slower for many small writes | Faster |
| Typical pattern | Simple writes | `new BufferedOutputStream(new FileOutputStream(...))` |

### ByteArrayInputStream vs FileInputStream

| | ByteArrayInputStream | FileInputStream |
|---|----------------------|-----------------|
| Source | In-memory `byte[]` | File |
| Persistence | None | Reads from disk |
| Best for | Tests, APIs, parsers | Binary files on disk |

### ByteArrayOutputStream vs FileOutputStream

| | ByteArrayOutputStream | FileOutputStream |
|---|-----------------------|------------------|
| Destination | Memory (`byte[]`) | File |
| Persistence | Until you write bytes elsewhere | On disk after flush/close |
| Best for | Building bytes in RAM | Saving to filesystem |

---

## 10. Important Methods Cheat Sheet

| Method | Class | Purpose | Return type | Important notes |
|--------|-------|---------|-------------|-----------------|
| `read()` | `InputStream` | Read one byte | `int` or `-1` | Do not cast to `byte` before EOF check |
| `read(byte[] b)` | `InputStream` | Read into array | `int` count or `-1` | Use count when processing buffer |
| `read(byte[] b, int off, int len)` | `InputStream` | Read into part of array | `int` count or `-1` | Same as above |
| `write(int b)` | `OutputStream` | Write one byte | `void` | Only low 8 bits used |
| `write(byte[] b)` | `OutputStream` | Write array | `void` | — |
| `write(byte[] b, int off, int len)` | `OutputStream` | Write part of array | `void` | — |
| `flush()` | `OutputStream` | Push buffer to destination | `void` | Does not close stream |
| `close()` | `InputStream`/`OutputStream` | Release resources | `void` | Output flushes first; use try-with-resources |
| `available()` | `InputStream` | Estimate bytes readable | `int` | Not always accurate for files |
| `toByteArray()` | `ByteArrayOutputStream` | Get built bytes | `byte[]` | Returns a copy |
| `size()` | `ByteArrayOutputStream` | Bytes written so far | `int` | Before `toByteArray()` |

---

## 11. Common Mistakes

### Forgetting close()

Streams hold OS resources. Without `close()`, files may stay locked and data may be lost.

```java
// Wrong: leak if exception before close()
FileInputStream input = new FileInputStream("a.bin");
input.read();

// Right
try (FileInputStream input = new FileInputStream("a.bin")) {
    // use input
}
```

### Not using flush()

Another program reads the file while your `BufferedOutputStream` is still open — file looks empty until buffer fills or stream closes.

```java
output.write(importantData);
output.flush();  // make visible now
```

### Writing the full buffer when only part was read

```java
// Wrong: may write garbage after last read
output.write(buffer);

// Right
output.write(buffer, 0, bytesRead);
```

### Using FileInputStream for text without charset

Reading bytes and casting to `char` breaks multi-byte UTF-8 characters. Use `Reader` with UTF-8, or decode with `new String(bytes, 0, count, UTF_8)`.

### Using byte streams for text when Reader is simpler

For line-by-line text files, `BufferedReader` is clearer. Byte streams are still valid if you need raw byte control.

### Incorrect append usage

```java
// Wrong: overwrites previous log entries
new FileOutputStream("app.log");

// Right for logs
new FileOutputStream("app.log", true);
```

### Other mistakes

- Treating `read()` result as `byte` without checking for `-1`.
- Assuming one byte equals one character in UTF-8.
- Reading `.jpg` or `.pdf` as text with `(char) value`.
- Swallowing `IOException` in real applications.

---

## 12. Best Practices

### try-with-resources

```java
try (BufferedInputStream input = new BufferedInputStream(
        new FileInputStream(path))) {
    // work with input
} catch (IOException e) {
    // handle
}
```

Automatically calls `close()` on all resources declared in the try header.

### Buffering for performance

- **Read:** `BufferedInputStream` → `FileInputStream`
- **Write:** `BufferedOutputStream` → `FileOutputStream`
- Use sensible array sizes (1024–8192) for chunk reads.

### Proper exception handling

- Catch **`IOException`** (and subclasses) for I/O code.
- Do not swallow exceptions in real applications.
- Fail with a clear message or rethrow wrapped in a domain exception.

### Choosing the correct stream class

| Need | Choice |
|------|--------|
| Binary file, copy byte-for-byte | `BufferedInputStream` + `FileInputStream` |
| Write binary / many small byte writes | `BufferedOutputStream` + `FileOutputStream` |
| Build bytes in memory | `ByteArrayOutputStream` |
| Read existing `byte[]` | `ByteArrayInputStream` |
| Text file, line by line | `BufferedReader` + `FileReader` (character streams) |

### Charset when mixing bytes and text

- Prefer **`StandardCharsets.UTF_8`** when converting `String` ↔ `byte[]`.
- Be explicit; do not rely on platform default encoding for portable apps.

---

## 13. Interview Questions and Answers

**Q1. What is the difference between byte streams and character streams?**  
Byte streams handle raw 8-bit bytes for binary data. Character streams handle 16-bit `char` units and convert to/from bytes using a charset for text.

**Q2. Why does `InputStream.read()` return `int` instead of `byte`?**  
So that **`-1`** can represent end-of-stream without conflicting with any valid byte value (0–255).

**Q3. What does `read(byte[])` return when there is no more data?**  
`-1`.

**Q4. Why use BufferedInputStream instead of FileInputStream alone?**  
Buffering reduces disk I/O by reading chunks into memory, which improves performance.

**Q5. What is the difference between `flush()` and `close()`?**  
`flush()` pushes buffered data to the destination but keeps the stream open. `close()` flushes (for output streams), releases resources, and ends use of the stream.

**Q6. How do you append to a file with FileOutputStream?**  
Use the constructor with append flag `true`: `new FileOutputStream(path, true)`.

**Q7. Can you use byte streams for image files?**  
Yes. Images are binary; byte streams are the correct choice.

**Q8. When would you use ByteArrayInputStream or ByteArrayOutputStream?**  
When the source or destination is already in memory (tests, API byte arrays, building output before saving or sending).

**Q9. Why must you use `write(buffer, 0, bytesRead)` when copying?**  
The last `read()` often fills only part of the buffer; writing the whole array would include stale bytes.

**Q10. Does try-with-resources call close() if an exception occurs?**  
Yes. `close()` is called on all declared resources in reverse order, with suppressed exceptions attached as needed.

---

## 14. Summary

### Key takeaways

- Byte streams are for **binary** and raw bytes; character streams are for **text** with encoding.
- **`InputStream` / `OutputStream`** are abstract bases; **`File*`** connects to files; **`Buffered*`** adds performance; **`ByteArray*`** uses memory.
- **`read()` returns `int`**; **`-1`** is EOF.
- When copying with buffers, write only **`bytesRead`** bytes, not the full array.
- Always **close** streams (prefer try-with-resources), **flush** when data must be visible early, and use **UTF-8** when converting text to bytes.
- Wrap file streams in **buffered** classes for real programs.

### Which stream to use in which situation

| Situation | Recommended approach |
|-----------|------------------------|
| Copy image/PDF/ZIP | `BufferedInputStream` + `BufferedOutputStream` over file streams |
| Read large file in chunks | `BufferedInputStream.read(byte[])` |
| Write many small byte writes | `BufferedOutputStream` wrapping `FileOutputStream` |
| Append bytes to a log file | `FileOutputStream` with append `true` |
| Parse bytes already in memory | `ByteArrayInputStream` |
| Build bytes before sending/saving | `ByteArrayOutputStream` → `toByteArray()` |
| Read text file line by line | `BufferedReader` (see CharacterStreams guide) |

### Quick Revision

- **EOF:** `read()` → `-1`; `read(byte[])` → `-1`.
- **Performance:** buffer file I/O (`BufferedInputStream` / `BufferedOutputStream`).
- **Append:** second argument `true` on `FileOutputStream`.
- **Text as bytes:** `getBytes(UTF_8)` / `new String(bytes, UTF_8)`.
- **Safety:** try-with-resources + handle `IOException`.
- **Copy:** always `write(buffer, 0, bytesRead)`.

**One-line picks**

| Goal | Class |
|------|-------|
| Read file (production) | `BufferedInputStream` |
| Write file (production) | `BufferedOutputStream` |
| Read `byte[]` | `ByteArrayInputStream` |
| Build `byte[]` | `ByteArrayOutputStream` |
| Read text / lines | `BufferedReader` (see CharacterStreams guide) |

---

*End of byte streams revision guide.*
