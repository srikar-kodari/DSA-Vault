import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class BufferedReaderExample {

	private static Path sampleFile() {
		return Path.of("buffered-reader-sample.txt");
	}

	public static void main(String[] args) {
		Path file = sampleFile();
		String sampleText = "BufferedReader improves text reading performance.\n"
				+ "It reads chunks of characters instead of one character at a time.\n"
				+ "That makes repeated reads cheaper for larger files.\n";

		try {
			Files.writeString(file, sampleText, StandardCharsets.UTF_8);
		}
		catch (IOException e) {
			System.out.println("Could not prepare sample file: " + e.getMessage());
			return;
		}

		char[] buffer = new char[32];
		int chunk = 1;
		int totalCharacters = 0;

		try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile(), StandardCharsets.UTF_8))) {
			int charactersRead;

			while ((charactersRead = reader.read(buffer)) != -1) {
				String part = new String(buffer, 0, charactersRead);
				System.out.println("Chunk " + chunk + ": " + part.replace('\n', ' '));
				totalCharacters += charactersRead;
				chunk++;
			}
		}
		catch (IOException e) {
			System.out.println("BufferedReader example failed: " + e.getMessage());
			return;
		}

		System.out.println("File: " + file);
		System.out.println("Total characters read: " + totalCharacters);
	}
}
