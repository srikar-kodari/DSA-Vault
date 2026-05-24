import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ByteArrayInputStreamExample {

	public static void main(String[] args) {

		byte[] data = "ByteArrayInputStream reads bytes from memory.".getBytes(StandardCharsets.UTF_8);
		StringBuilder text = new StringBuilder();
		int totalBytes = 0;

		try (ByteArrayInputStream input = new ByteArrayInputStream(data)) {
			int value;
			while ((value = input.read()) != -1) {
				text.append((char) value);
				totalBytes++;
			}
		}
		catch (IOException e) {
			System.out.println("ByteArrayInputStream example failed: " + e.getMessage());
			return;
		}

		System.out.println("Bytes available initially: " + data.length);
		System.out.println("Bytes read: " + totalBytes);
		System.out.println("Text read: " + text);
	}
}
