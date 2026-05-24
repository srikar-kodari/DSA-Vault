import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class OutputStreamExample {

	public static void main(String[] args) {

		String text = "OutputStream writes raw bytes to a destination.";
		byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);

		try (ByteArrayOutputStream storage = new ByteArrayOutputStream();
				OutputStream output = storage) {
			output.write(textBytes);
			output.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
			output.write("Second line written with write(byte[]).".getBytes(StandardCharsets.UTF_8));

			System.out.println("Bytes written: " + storage.size());
			System.out.println("Stored data:");
			System.out.println(storage.toString(StandardCharsets.UTF_8));
		}
		catch (IOException e) {
			System.out.println("OutputStream example failed: " + e.getMessage());
		}
	}
}
