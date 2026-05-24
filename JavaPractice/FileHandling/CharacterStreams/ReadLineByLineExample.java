import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadLineByLineExample {

	private static Path sampleFile() {
		return Path.of("read-line-by-line-sample.txt");
	}

	public static void main(String[] args) {
		
		Path file = sampleFile();
		String sampleText = "First line of the report.\nSecond line with more details.\nThird line for summary.";

		try {
			Files.writeString(file, sampleText, StandardCharsets.UTF_8);
		}
		catch (IOException e) {
			System.out.println("Could not prepare sample file: " + e.getMessage());
			return;
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile(), StandardCharsets.UTF_8))) {
			String line;
			int lineNumber = 1;

			while ((line = reader.readLine()) != null) {
				System.out.println(lineNumber + ": " + line);
				lineNumber++;
			}
		}
		catch (IOException e) {
			System.out.println("Line-by-line example failed: " + e.getMessage());
		}
	}
}
