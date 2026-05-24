import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class BufferedWriterExample {

	private static Path sampleFile() {
		return Path.of("buffered-writer-sample.txt");
	}

	public static void main(String[] args) {
		Path file = sampleFile();

		try {
			Files.deleteIfExists(file);
		}
		catch (IOException e) {
			System.out.println("Could not prepare sample file: " + e.getMessage());
			return;
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile(), StandardCharsets.UTF_8))) {
			writer.write("BufferedWriter groups many small writes into larger chunks.");
			writer.newLine();
			writer.write("That is helpful when you write logs, reports, or large text files.");
			writer.newLine();
			writer.write("flush() pushes buffered text to the file before close().");
			writer.flush();
			writer.newLine();
			writer.write("This line is written after flush().");
		}
		catch (IOException e) {
			System.out.println("BufferedWriter example failed: " + e.getMessage());
			return;
		}

		try {
			System.out.println("File: " + file);
			System.out.println("Content:");
			System.out.println(Files.readString(file, StandardCharsets.UTF_8));
		}
		catch (IOException e) {
			System.out.println("Could not read back file: " + e.getMessage());
		}
	}
}
