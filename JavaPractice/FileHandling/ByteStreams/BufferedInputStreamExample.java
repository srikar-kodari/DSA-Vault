import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class BufferedInputStreamExample {

	private static Path sampleFile() {
		return Path.of("buffered-input-stream-sample.txt");
	}

	public static void main(String[] args) {
		Path file = sampleFile();
		String sampleText = "BufferedInputStream improves read performance.\n"
				+ "It reads larger blocks of bytes internally.\n"
				+ "This example prints chunks from the file.\n";

		try {
			Files.writeString(file, sampleText, StandardCharsets.UTF_8);
		}
		catch (IOException e) {
			System.out.println("Could not prepare sample file: " + e.getMessage());
			return;
		}

		byte[] buffer = new byte[24];
		int chunk = 1;
		int totalBytes = 0;

		try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file.toFile()))) {
			int bytesRead;

			while ((bytesRead = input.read(buffer)) != -1) {
				String part = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
				System.out.println("Chunk " + chunk + ": " + part.replace('\n', ' '));
				totalBytes += bytesRead;
				chunk++;
			}
		}
		catch (IOException e) {
			System.out.println("BufferedInputStream example failed: " + e.getMessage());
			return;
		}

		System.out.println("File: " + file);
		System.out.println("Total bytes read: " + totalBytes);
	}
}
