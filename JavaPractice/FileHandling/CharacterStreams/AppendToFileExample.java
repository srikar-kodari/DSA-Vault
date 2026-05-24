import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class AppendToFileExample {

	private static Path sampleFile() {
		return Path.of("append-example.txt");
	}

	public static void main(String[] args) {
		Path file = sampleFile();

		try {
			Files.deleteIfExists(file);
			Files.writeString(file, "Morning log entry." + System.lineSeparator(), StandardCharsets.UTF_8);
		}
		catch (IOException e) {
			System.out.println("Could not prepare sample file: " + e.getMessage());
			return;
		}

		try (FileWriter writer = new FileWriter(file.toFile(), StandardCharsets.UTF_8, true)) {
			writer.write("Noon log entry.");
			writer.write(System.lineSeparator());
			writer.write("Evening log entry.");
			writer.write(System.lineSeparator());
		}
		catch (IOException e) {
			System.out.println("Append example failed: " + e.getMessage());
			return;
		}

		try {
			System.out.println("File: " + file);
			System.out.println("Content after append:");
			System.out.println(Files.readString(file, StandardCharsets.UTF_8));
		}
		catch (IOException e) {
			System.out.println("Could not read file: " + e.getMessage());
		}
	}
}
