import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ByteArrayOutputStreamExample {

	public static void main(String[] args) {

		try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
			output.write("ByteArrayOutputStream stores bytes in memory.".getBytes(StandardCharsets.UTF_8));
			output.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
			output.write("Useful for building binary/text output dynamically.".getBytes(StandardCharsets.UTF_8));

			byte[] result = output.toByteArray();
			String text = new String(result, StandardCharsets.UTF_8);

			System.out.println("Total bytes stored: " + result.length);
			System.out.println("Built text:");
			System.out.println(text);
		}
		catch (IOException e) {
			System.out.println("ByteArrayOutputStream example failed: " + e.getMessage());
		}
	}
}
