import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class BufferedOutputStreamExample {

	private static Path outputFile() {
		return Path.of("buffered-output-stream-sample.txt");
	}

	public static void main(String[] args) {
		Path file = outputFile();

		try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file.toFile()))) {
			output.write("BufferedOutputStream batches writes for efficiency.\n".getBytes(StandardCharsets.UTF_8));
			output.write("It is useful when writing many small pieces of data.\n".getBytes(StandardCharsets.UTF_8));
			output.write("Final line from buffered output stream.\n".getBytes(StandardCharsets.UTF_8));
		}
		catch (IOException e) {
			System.out.println("BufferedOutputStream example failed: " + e.getMessage());
			return;
		}

		try {
			System.out.println("File written: " + file);
			System.out.println("File content:\n" + Files.readString(file, StandardCharsets.UTF_8));
		}
		catch (IOException e) {
			System.out.println("Could not read output file: " + e.getMessage());
		}
	}
}
