import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileInputStreamExample {

	private static Path sampleFile() {
		return Path.of("file-input-stream-sample.txt");
	}

	public static void main(String[] args) {
		Path file = sampleFile();
		String sampleText = "FileInputStream reads bytes from a file.\n"
				+ "It is useful for binary data and simple file reads.\n";

		try {
			Files.writeString(file, sampleText, StandardCharsets.UTF_8);
		}
		catch (IOException e) {
			System.out.println("Could not prepare sample file: " + e.getMessage());
			return;
		}

		int bytesRead = 0;
		StringBuilder text = new StringBuilder();

		try (FileInputStream input = new FileInputStream(file.toFile())) {
			int value;
			while ((value = input.read()) != -1) {
				text.append((char) value);
				bytesRead++;
			}
		}
		catch (IOException e) {
			System.out.println("FileInputStream example failed: " + e.getMessage());
			return;
		}

		System.out.println("File: " + file);
		System.out.println("Bytes read: " + bytesRead);
		System.out.println("Read back text:\n" + text);
	}
}
