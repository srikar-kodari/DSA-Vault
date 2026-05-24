import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCopyExample {

	private static Path sourceFile() {
		return Path.of("copy-source.txt");
	}

	private static Path destinationFile() {
		return Path.of("copy-destination.txt");
	}

	public static void main(String[] args) {

		Path source = sourceFile();
		Path destination = destinationFile();
		String sampleText = "This file will be copied using character streams.\n"
				+ "The copy keeps the text content readable.\n";

		try {
			Files.writeString(source, sampleText, StandardCharsets.UTF_8);
			Files.deleteIfExists(destination);
		}
		catch (IOException e) {
			System.out.println("Could not prepare files: " + e.getMessage());
			return;
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(source.toFile(), StandardCharsets.UTF_8));
				BufferedWriter writer = new BufferedWriter(new FileWriter(destination.toFile(), StandardCharsets.UTF_8))) {
			char[] buffer = new char[64];
			int charactersRead;

			while ((charactersRead = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, charactersRead);
			}
		}
		catch (IOException e) {
			System.out.println("Copy example failed: " + e.getMessage());
			return;
		}

		try {
			System.out.println("Source file: " + source);
			System.out.println("Destination file: " + destination);
			System.out.println("Copied text:\n" + Files.readString(destination, StandardCharsets.UTF_8));
		}
		catch (IOException e) {
			System.out.println("Could not read copied file: " + e.getMessage());
		}
	}
}
