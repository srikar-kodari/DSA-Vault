import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderExample {

	private static Path sampleFile() {
		return Path.of("file-reader-sample.txt");
	}

	public static void main(String[] args) {

		Path file = sampleFile();
		String sampleText = "FileReader is a simple way to read text files.\nIt reads characters from a file.\n";

		try {
			Files.writeString(file, sampleText, StandardCharsets.UTF_8);
		}
		catch (IOException e) {
			System.out.println("Could not prepare sample file: " + e.getMessage());
			return;
		}

		StringBuilder content = new StringBuilder();
		int charactersRead = 0;

		try (FileReader reader = new FileReader(file.toFile(), StandardCharsets.UTF_8)) {
			int value;

			// FileReader reads text from the file one character at a time.
			while ((value = reader.read()) != -1) {
				content.append((char) value);
				charactersRead++;
			}
		}
		catch (IOException e) {
			System.out.println("FileReader example failed: " + e.getMessage());
			return;
		}

		System.out.println("File: " + file);
		System.out.println("Characters read: " + charactersRead);
		System.out.println("Content:");
		System.out.println(content);
	}
}
