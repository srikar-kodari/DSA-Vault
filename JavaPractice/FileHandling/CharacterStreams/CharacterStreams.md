# Character Streams in Java

Concise revision notes for Java character streams: text-oriented I/O, common classes, comparisons, and interview points. For byte-stream concepts, see [ByteStreams.md](../ByteStreams.md). Runnable practice examples live in this folder (`FileReaderExample.java`, `BufferedReaderExample.java`, and others).

---

## 1. Introduction to Character Streams

### What are character streams?

Character streams are Java I/O classes that read and write **text** as **characters** (`char`, 16-bit Unicode code units in Java). They sit above raw bytes and handle **character encoding** (for example UTF-8) when talking to files or byte-based sources.

- **Input side**: `Reader` and subclasses (`FileReader`, `BufferedReader`, `StringReader`, …)
- **Output side**: `Writer` and subclasses (`FileWriter`, `BufferedWriter`, `StringWriter`, …)

### Difference between byte streams and character streams

| Aspect | Byte streams | Character streams |
|--------|--------------|-------------------|
| Base classes | `InputStream`, `OutputStream` | `Reader`, `Writer` |
| Unit of I/O | `byte` (8 bits) | `char` (16-bit in Java) |
| Best for | Binary data (images, PDF, ZIP) | Text files, logs, config |
| Encoding | You manage bytes ↔ text yourself | Stream decodes/encodes using a charset |
| Typical classes | `FileInputStream`, `BufferedInputStream` | `FileReader`, `BufferedReader` |

> **Revision point:** Use byte streams for **binary** data. Use character streams for **human-readable text** when you want encoding handled for you.

### Unicode and text handling

- **Unicode** is a standard that assigns a code point to every character.
- Files on disk are stored as **bytes**; a **charset** (encoding) defines how bytes map to characters.
- **UTF-8** is the most common encoding for text files and is a good default.
- `FileReader` / `FileWriter` use a charset when you pass one (for example `StandardCharsets.UTF_8`); otherwise the platform default may apply.

```java
// Explicit charset avoids surprises on different machines
FileReader reader = new FileReader("data.txt", StandardCharsets.UTF_8);
```

### Why character streams are used

- Simpler text processing (read/write `String`, line-by-line, append text).
- Correct handling of multi-byte characters (for example emoji, non-English scripts) when charset is set properly.
- Higher-level APIs: `readLine()`, `append()`, `newLine()`.
- Natural fit for config files, logs, CSV, JSON as text, reports, and user input.

---

## 2. Reader and Writer

### Purpose of Reader and Writer classes

- **`Reader`** — abstract base for **reading characters** from a source (file, string, network wrapper, etc.).
- **`Writer`** — abstract base for **writing characters** to a destination.

Concrete subclasses provide the actual source or sink (`FileReader` → file, `StringReader` → `String` in memory).

### Common methods

| Method | Class | Purpose |
|--------|-------|---------|
| `read()` | `Reader` | Read one character; returns `int` or `-1` at EOF |
| `read(char[] cbuf)` | `Reader` | Read into array; returns count or `-1` |
| `read(char[] cbuf, int off, int len)` | `Reader` | Read into part of array |
| `write(int c)` | `Writer` | Write single character |
| `write(char[] cbuf)` | `Writer` | Write array |
| `write(String str)` | `Writer` | Write string |
| `append(CharSequence csq)` | `Writer` | Append text (returns `Writer`) |
| `flush()` | `Writer` | Push buffered data to destination |
| `close()` | Both | Release resources; `Writer` usually flushes first |

### read(), write(), close(), flush()

**`read()`**

- Returns the next character as an **`int`** in range `0`–`65535`, or **`-1`** at end of stream.
- Returns `int` (not `char`) so **`-1` is not confused** with a valid character.

**`write()`**

- Writes characters to the destination (may be buffered internally).

**`flush()`**

- Forces buffered output to be written immediately.
- Important before relying on file content while the stream is still open.

**`close()`**

- Closes the stream and releases resources.
- On `Writer`, `close()` typically calls `flush()` first.

> **Exam fact:** `read()` returns **`int`**, not `char`, because **`-1` means EOF**.

### Simple examples

**Reading with Reader (StringReader as source):**

```java
try (Reader reader = new StringReader("Hello, Reader!")) {
    int value;
    while ((value = reader.read()) != -1) {
        System.out.print((char) value);
    }
}
```

**Writing with Writer (StringWriter as destination):**

```java
try (Writer writer = new StringWriter()) {
    writer.write("Hello, Writer!");
    writer.write(System.lineSeparator());
    writer.append("Appended line.");
    // use writer.toString() if StringWriter
}
```

### Important notes

- Always treat **`(char) value`** only after checking `value != -1`.
- Prefer **`read(char[] buffer)`** or **`BufferedReader`** over one-character loops for files.
- **`Writer`** is for text only; do not use it for binary files.
- Use **try-with-resources** so `close()` runs even when an exception occurs.

---

## 3. FileReader

### What it is

`FileReader` is a convenience class that reads **characters from a text file**. It extends `Reader` and bridges the file’s bytes to Java `char` values using a charset.

### Reading characters from text files

- Opens a file for **character** input.
- Without a charset argument, uses the **platform default encoding** (can differ across OS).
- With a charset: `new FileReader(file, StandardCharsets.UTF_8)` — **recommended**.

### Common methods

Inherits from `Reader`: `read()`, `read(char[])`, `read(char[], int, int)`, `close()`. No `readLine()` — use `BufferedReader` for that.

### Example explanation

```java
try (FileReader reader = new FileReader("notes.txt", StandardCharsets.UTF_8)) {
    int value;
    StringBuilder content = new StringBuilder();

    while ((value = reader.read()) != -1) {
        content.append((char) value);
    }

    System.out.println(content);
}
```

- `read()` returns one character per call (or `-1` at EOF).
- Simple but **slow for large files** because each `read()` may trigger underlying byte I/O.

### Advantages and limitations

| Advantages | Limitations |
|------------|-------------|
| Easy API for text files | No built-in `readLine()` |
| Charset can be specified | One-char reads are inefficient |
| Works with try-with-resources | Not for binary files |
| Good for small files / learning | Should wrap in `BufferedReader` for real use |

### When to use

- Small text files, quick scripts, learning demos.
- As the **underlying reader** inside `BufferedReader` for production-style file reading.

> **Practice:** See `FileReaderExample.java` in this folder.

---

## 4. FileWriter

### Writing text into files

`FileWriter` writes **characters** to a text file. It extends `Writer` and encodes characters to bytes using a charset.

### Overwriting vs append mode

| Mode | Constructor pattern | Behavior |
|------|----------------------|----------|
| Overwrite (default) | `new FileWriter(path, charset)` | Truncates file if it exists; writes from start |
| Append | `new FileWriter(path, charset, true)` | Writes at **end** of file; keeps existing content |

```java
// Overwrite
try (FileWriter writer = new FileWriter("log.txt", StandardCharsets.UTF_8)) {
    writer.write("First run.\n");
}

// Append
try (FileWriter writer = new FileWriter("log.txt", StandardCharsets.UTF_8, true)) {
    writer.write("Second run.\n");
}
```

### flush() and close()

- **`flush()`** — sends buffered characters to the OS/file without closing the stream.
- **`close()`** — flushes (if buffered) and releases the file handle.

Use `flush()` when another process (or you, in another step) must see data **before** closing.

### Example explanation

```java
Path file = Path.of("report.txt");

try (FileWriter writer = new FileWriter(file.toFile(), StandardCharsets.UTF_8)) {
    writer.write("Title: Sales Report");
    writer.write(System.lineSeparator());
    writer.write("Status: Draft");
}
```

After this block, the file contains only what was written (previous content was replaced).

### Best practices

- Always specify **charset** (`StandardCharsets.UTF_8`).
- Use **append mode** for logs and audit trails.
- Wrap in **`BufferedWriter`** for many small writes.
- Prefer **`System.lineSeparator()`** or `BufferedWriter.newLine()` over hardcoded `\n` when portability matters.
- Use try-with-resources.

> **Practice:** See `FileWriterExample.java` and `AppendToFileExample.java`.

---

## 5. BufferedReader

### Why buffering is needed

Each `read()` on an unbuffered `FileReader` can cause a **disk read**. Reading one character at a time is expensive. **Buffering** reads a **chunk** of characters into memory and serves `read()` calls from that chunk.

### Internal working of buffering

1. `BufferedReader` wraps another `Reader` (for example `FileReader`).
2. It fills an internal **char array** (default size **8192** characters).
3. `read()` / `read(char[])` take data from the buffer until empty, then refill from the wrapped reader.

```mermaid
flowchart LR
  app[Your_code] --> br[BufferedReader]
  br --> buffer[Internal_char_buffer]
  buffer --> fr[FileReader]
  fr --> file[Text_file_bytes]
```

### readLine()

- Reads until `\n`, `\r`, or `\r\n`, or end of stream.
- Returns a **`String` without the line terminator**, or **`null`** at EOF.
- Ideal for logs, CSV rows, config lines, and interview “read file line by line” questions.

```java
try (BufferedReader reader = new BufferedReader(
        new FileReader("data.txt", StandardCharsets.UTF_8))) {

    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
}
```

Also useful: `read(char[] buffer)`, and in Java 8+ `reader.lines()` for stream-style processing.

### Performance benefits

- Far fewer system calls than char-by-char `FileReader`.
- `readLine()` avoids manual newline handling in application code.

### Example explanation (chunk read)

```java
char[] buffer = new char[1024];
int count;

try (BufferedReader reader = new BufferedReader(
        new FileReader("large.txt", StandardCharsets.UTF_8))) {

    while ((count = reader.read(buffer)) != -1) {
        String chunk = new String(buffer, 0, count);
        // process chunk
    }
}
```

### Common interview questions

1. **Why wrap FileReader in BufferedReader?** — To reduce I/O calls and improve performance.
2. **What does readLine() return at EOF?** — `null`.
3. **Does readLine() include `\n`?** — No; line separators are stripped.
4. **Default buffer size?** — 8192 characters (know “about 8K” for revision).

> **Practice:** See `BufferedReaderExample.java` and `ReadLineByLineExample.java`.

---

## 6. BufferedWriter

### Buffered writing concept

`BufferedWriter` wraps a `Writer` (for example `FileWriter`), accumulates writes in an internal buffer, and writes to the underlying stream in **larger chunks**.

### newLine()

- Writes a **platform-dependent line separator** (`\n` on Unix, `\r\n` on Windows).
- Prefer over hardcoded `"\n"` when code must run on multiple OSes.

```java
try (BufferedWriter writer = new BufferedWriter(
        new FileWriter("out.txt", StandardCharsets.UTF_8))) {

    writer.write("Line one");
    writer.newLine();
    writer.write("Line two");
    writer.newLine();
}
```

### Performance advantages

- Many small `write()` calls become fewer disk writes.
- Essential for generating large reports, logs, or exports.

### Example explanation

```java
try (BufferedWriter writer = new BufferedWriter(
        new FileWriter("report.txt", StandardCharsets.UTF_8))) {

    writer.write("Section 1");
    writer.newLine();
    writer.write("Section 2");
    writer.flush();  // optional: make data visible before close
}
```

### flush() importance

- Data may sit in the buffer until it is full or the stream is closed.
- Call **`flush()`** when other code must read the file **immediately** while the writer is still open.
- **`close()`** flushes automatically; do not rely on flush alone to release resources.

> **Practice:** See `BufferedWriterExample.java`.

---

## 7. StringReader

### Reading characters from strings

`StringReader` treats a **`String` already in memory** as a character source. It extends `Reader` and does not touch the file system.

### Use cases

- Parsing text from an API response already in a `String`.
- Unit tests without creating temp files.
- Processing form input, XML/JSON snippets, or tokenizer input.
- Reusing `Reader`-based APIs when data is not in a file.

### Example explanation

```java
String data = "alpha\nbeta\ngamma\n";

try (StringReader reader = new StringReader(data)) {
    int value;
    while ((value = reader.read()) != -1) {
        System.out.print((char) value);
    }
}
```

Same `read()` loop as `FileReader`, but the source is RAM, not disk.

### Difference from FileReader

| | StringReader | FileReader |
|---|--------------|------------|
| Source | `String` in memory | File on disk |
| Encoding | Not applicable (already Java chars) | Bytes decoded via charset |
| IOException on read | Unusual for normal use | Common (missing file, permissions) |
| Typical use | Tests, in-memory pipelines | Persistent text files |

> **Practice:** See `StringReaderExample.java`.

---

## 8. StringWriter

### Writing data into memory using strings

`StringWriter` collects written characters in an internal **`StringBuffer`**. Nothing is written to disk until you read the result as a `String`.

### Internal buffer concept

- Each `write()` / `append()` adds to the buffer.
- No charset conversion on write (you work in Java `char` / `String`).
- Efficient for building text step by step in memory.

### getBuffer() and toString()

| Method | Returns | Notes |
|--------|---------|-------|
| `toString()` | `String` | Full content built so far; common for final output |
| `getBuffer()` | `StringBuffer` | Live buffer; changes if you keep writing |

```java
StringWriter writer = new StringWriter();
writer.write("Hello");
writer.append(", world!");
String result = writer.toString();           // "Hello, world!"
StringBuffer buf = writer.getBuffer();       // same underlying buffer
```

### Use cases

- Building HTTP/HTML/XML bodies before sending.
- Formatting reports in memory, then writing once to file.
- Logging or debug builders.
- Testing `Writer` logic without file I/O.

### Example explanation

```java
StringWriter writer = new StringWriter();

writer.write("Line 1");
writer.write(System.lineSeparator());
writer.write("Line 2");

System.out.println(writer.toString());
System.out.println("Length: " + writer.getBuffer().length());
```

### Important notes

- Call **`toString()`** when you need an immutable `String` for APIs that expect `String`.
- **`getBuffer()`** exposes mutable state — avoid sharing it if other code still writes to the writer.

> **Practice:** See `StringWriterExample.java` and `WriterExample.java`.

---

## 9. Comparison Sections

### Reader vs InputStream

| | Reader | InputStream |
|---|--------|-------------|
| Unit | `char` (16-bit) | `byte` (8-bit) |
| Data type | Text | Binary or raw bytes |
| Encoding | Handled by character stream | Not handled |
| `read()` return | `int` char value or `-1` | `int` byte value or `-1` |
| Typical use | `.txt`, logs, config | Images, PDF, ZIP, serialized bytes |

### Writer vs OutputStream

| | Writer | OutputStream |
|---|--------|--------------|
| Unit | `char` | `byte` |
| Data type | Text | Binary |
| Methods | `write(String)`, `append`, `newLine` (in subclasses) | `write(byte[])`, `write(int)` |
| Typical use | Text files, reports | Binary files, network bytes |

### FileReader vs BufferedReader

| | FileReader | BufferedReader |
|---|------------|------------------|
| Role | Direct file → chars | Wraps another Reader |
| `readLine()` | No | Yes |
| Performance | Slower for many reads | Much faster |
| Typical pattern | Learning / small files | `new BufferedReader(new FileReader(...))` |

### FileWriter vs BufferedWriter

| | FileWriter | BufferedWriter |
|---|------------|----------------|
| Role | Direct chars → file | Wraps another Writer |
| `newLine()` | No (use `System.lineSeparator()`) | Yes |
| Performance | Slower for many small writes | Faster |
| Typical pattern | Simple writes | `new BufferedWriter(new FileWriter(...))` |

### StringReader vs FileReader

| | StringReader | FileReader |
|---|--------------|------------|
| Source | In-memory `String` | File |
| Charset | N/A | Required for correct byte decoding |
| Persistence | None | Reads from disk |
| Best for | Tests, APIs, parsers | Config, logs on disk |

### StringWriter vs FileWriter

| | StringWriter | FileWriter |
|---|--------------|------------|
| Destination | Memory (`StringBuffer`) | File |
| Persistence | Until you copy `toString()` | Immediate on disk (after flush/close) |
| Charset on write | N/A | Encodes to bytes with charset |
| Best for | Building text in RAM | Saving text to filesystem |

---

## 10. Important Methods Cheat Sheet

| Method | Class | Purpose | Return type | Important notes |
|--------|-------|---------|-------------|-----------------|
| `read()` | `Reader` | Read one character | `int` or `-1` | Cast to `char` only if not `-1` |
| `read(char[] cbuf)` | `Reader` | Read into array | `int` count or `-1` | Use valid count when converting to `String` |
| `readLine()` | `BufferedReader` | Read one line | `String` or `null` | `null` = EOF; no newline in result |
| `write(int c)` | `Writer` | Write one character | `void` | — |
| `write(String str)` | `Writer` | Write string | `void` | — |
| `write(char[] cbuf)` | `Writer` | Write char array | `void` | — |
| `append(CharSequence s)` | `Writer` | Append text | `Writer` | Fluent chaining |
| `flush()` | `Writer` | Push buffer to destination | `void` | Does not close stream |
| `close()` | `Reader`/`Writer` | Release resources | `void` | Writer flushes first; use try-with-resources |
| `newLine()` | `BufferedWriter` | Platform line ending | `void` | Prefer over hardcoded `\n` |
| `getBuffer()` | `StringWriter` | Access internal buffer | `StringBuffer` | Mutable; still grows if writer used |
| `toString()` | `StringWriter` | Get built text | `String` | Common final step |

---

## 11. Common Mistakes

### Forgetting close()

Streams hold OS resources. Without `close()`, files may stay locked and data may be lost.

```java
// Wrong: leak if exception before close()
FileReader reader = new FileReader("a.txt");
reader.read();

// Right
try (FileReader reader = new FileReader("a.txt", StandardCharsets.UTF_8)) {
    // use reader
}
```

### Not using flush()

Another program reads the file while your `BufferedWriter` is still open — file looks empty until buffer fills or stream closes.

```java
writer.write("important");
writer.flush();  // make visible now
```

### Using FileReader for binary files

Reading a `.jpg` or `.pdf` as characters **corrupts interpretation**. Use `FileInputStream` instead.

### Ignoring exceptions

```java
// Wrong
try { reader.read(); } catch (IOException e) { }

// Better
catch (IOException e) {
    System.err.println("Read failed: " + e.getMessage());
}
```

### Incorrect append usage

```java
// Wrong: overwrites previous log entries
new FileWriter("app.log", StandardCharsets.UTF_8);

// Right for logs
new FileWriter("app.log", StandardCharsets.UTF_8, true);
```

### Other mistakes

- Using `(char) read()` without checking for `-1`.
- Hardcoding `\n` on all platforms instead of `newLine()` or `System.lineSeparator()`.
- Using platform default charset in production instead of `UTF_8`.
- Reading large files one `read()` at a time without `BufferedReader`.

---

## 12. Best Practices

### try-with-resources

```java
try (BufferedReader reader = new BufferedReader(
        new FileReader(path, StandardCharsets.UTF_8))) {
    // work with reader
} catch (IOException e) {
    // handle
}
```

Automatically calls `close()` on all resources declared in the try header.

### Buffering for performance

- **Read:** `BufferedReader` → `FileReader`
- **Write:** `BufferedWriter` → `FileWriter`
- Use sensible array sizes (1024–8192) for chunk reads if not using `readLine()`.

### Proper exception handling

- Catch **`IOException`** (and subclasses) for I/O code.
- Do not swallow exceptions in real applications.
- Fail with a clear message or rethrow wrapped in a domain exception.

### Choosing the correct stream class

| Need | Choice |
|------|--------|
| Text file, line by line | `BufferedReader` + `FileReader` |
| Text file, write report | `BufferedWriter` + `FileWriter` |
| Build text in memory | `StringWriter` |
| Parse existing `String` | `StringReader` |
| Image, PDF, binary | `FileInputStream` / `FileOutputStream` (byte streams) |

### Charset

- Prefer **`StandardCharsets.UTF_8`** for files exchanged across systems.
- Be explicit in constructors; do not rely on default encoding for portable apps.

---

## 13. Interview Questions and Answers

**Q1. What is the difference between byte streams and character streams?**  
Byte streams handle raw 8-bit bytes for binary data. Character streams handle 16-bit `char` units and convert to/from bytes using a charset for text.

**Q2. Why does `Reader.read()` return `int` instead of `char`?**  
So that **`-1`** can represent end-of-stream without conflicting with any valid character value (0–65535).

**Q3. What does `readLine()` return when there is no more data?**  
`null`.

**Q4. Why use BufferedReader instead of FileReader alone?**  
Buffering reduces disk I/O by reading chunks into memory, which improves performance and provides `readLine()`.

**Q5. What is the difference between `flush()` and `close()`?**  
`flush()` pushes buffered data to the destination but keeps the stream open. `close()` flushes (for writers), releases resources, and ends use of the stream.

**Q6. How do you append to a file with FileWriter?**  
Use the constructor with append flag `true`: `new FileWriter(path, charset, true)`.

**Q7. Can you use character streams for image files?**  
No. Images are binary; use `InputStream` / `OutputStream` subclasses.

**Q8. What is the role of charset in FileReader/FileWriter?**  
It defines how file bytes are decoded to characters (read) or characters encoded to bytes (write). UTF-8 is a common choice.

**Q9. When would you use StringReader or StringWriter?**  
When the source or destination is already in memory (tests, API strings, building a report before saving or sending).

**Q10. Does try-with-resources call close() if an exception occurs?**  
Yes. `close()` is called on all declared resources in reverse order, with suppressed exceptions attached as needed.

---

## 14. Summary

### Key takeaways

- Character streams are for **text**; byte streams are for **binary**.
- **`Reader` / `Writer`** are abstract bases; **`File*`** connects to files; **`Buffered*`** adds performance; **`String*`** uses memory.
- **`read()` returns `int`**; **`-1`** is EOF.
- **`readLine()`** returns `null` at EOF and excludes line terminators.
- Always **close** streams (prefer try-with-resources), **flush** when data must be visible early, and specify **UTF-8** for portable text files.
- Wrap file readers/writers in **buffered** classes for real programs.

### Which stream to use in which situation

| Situation | Recommended approach |
|-----------|------------------------|
| Read text file line by line | `BufferedReader` wrapping `FileReader` |
| Read large text file in chunks | `BufferedReader.read(char[])` |
| Write many text lines to file | `BufferedWriter` wrapping `FileWriter` |
| Append to a log file | `FileWriter` (or buffered) with append `true` |
| Parse text already in a `String` | `StringReader` |
| Build text before sending/saving | `StringWriter` → `toString()` |
| Copy or read images/PDF/ZIP | Byte streams (`FileInputStream`, etc.) |

### Quick Revision

- **EOF:** `read()` → `-1`; `readLine()` → `null`.
- **Performance:** buffer file I/O (`BufferedReader` / `BufferedWriter`).
- **Append:** third argument `true` on `FileWriter`.
- **Encoding:** `StandardCharsets.UTF_8` on file streams.
- **Safety:** try-with-resources + handle `IOException`.
- **Binary:** never `FileReader` / `FileWriter`.

**One-line picks**

| Goal | Class |
|------|-------|
| Read file (production) | `BufferedReader` |
| Write file (production) | `BufferedWriter` |
| Read `String` | `StringReader` |
| Build `String` | `StringWriter` |
| Read bytes / binary | `FileInputStream` (see ByteStreams guide) |

---

*End of character streams revision guide.*
