import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriterExample {

	private static Path sampleFile() {
		return Path.of("file-writer-sample.txt");
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

		try (FileWriter writer = new FileWriter(file.toFile(), StandardCharsets.UTF_8)) {
			writer.write("FileWriter writes text to a file.");
			writer.write(System.lineSeparator());
			writer.write("This first pass overwrites any old content.");
		}
		catch (IOException e) {
			System.out.println("Write example failed: " + e.getMessage());
			return;
		}

		try (FileWriter writer = new FileWriter(file.toFile(), StandardCharsets.UTF_8, true)) {
			writer.write(System.lineSeparator());
			writer.write("This line was appended later.");
		}
		catch (IOException e) {
			System.out.println("Append example failed: " + e.getMessage());
			return;
		}

		try {
			System.out.println("File: " + file);
			System.out.println("Content after write and append:");
			System.out.println(Files.readString(file, StandardCharsets.UTF_8));
		}
		catch (IOException e) {
			System.out.println("Could not read back file: " + e.getMessage());
		}
	}
}