import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class InputStreamExample {

	public static void main(String[] args) {

		byte[] sourceBytes = "InputStream reads raw bytes one by one.".getBytes(StandardCharsets.UTF_8);
		StringBuilder text = new StringBuilder();
		int totalBytes = 0;

		try (InputStream input = new ByteArrayInputStream(sourceBytes)) {
			int value;

			// read() returns a byte value (0-255) as an int.
			while ((value = input.read()) != -1) {
				text.append((char) value);
				totalBytes++;
			}
		}
		catch (IOException e) {
			System.out.println("InputStream example failed: " + e.getMessage());
			return;
		}

		System.out.println("Source text: " + text);
		System.out.println("Bytes read: " + totalBytes);
	}
}
