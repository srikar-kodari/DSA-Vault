import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class WordCountExample {

	private static Path sampleFile() {
		return Path.of("word-count-sample.txt");
	}

	public static void main(String[] args) {
		Path file = sampleFile();
		String sampleText = "Java character streams are useful for text processing.\n"
				+ "They help you read and write readable content.\n"
				+ "Buffered readers make large text files easier to handle.";

		try {
			Files.writeString(file, sampleText, StandardCharsets.UTF_8);
		}
		catch (IOException e) {
			System.out.println("Could not prepare sample file: " + e.getMessage());
			return;
		}

		int lines = 0;
		int words = 0;
		int characters = 0;

		try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile(), StandardCharsets.UTF_8))) {
			String line;

			while ((line = reader.readLine()) != null) {
				lines++;
				characters += line.length();

				String trimmed = line.trim();
				if (!trimmed.isEmpty()) {
					words += trimmed.split("\\s+").length;
				}
			}
		}
		catch (IOException e) {
			System.out.println("Word count example failed: " + e.getMessage());
			return;
		}

		System.out.println("File: " + file);
		System.out.println("Lines: " + lines);
		System.out.println("Words: " + words);
		System.out.println("Characters (excluding line separators): " + characters);
	}
}
