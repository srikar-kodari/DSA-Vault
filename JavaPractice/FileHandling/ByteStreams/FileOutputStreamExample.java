import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileOutputStreamExample {

	private static Path outputFile() {
		return Path.of("file-output-stream-sample.txt");
	}

	public static void main(String[] args) {
		Path file = outputFile();
		String text = "FileOutputStream writes bytes to a file.\n"
				+ "This is a simple byte-based write example.\n";

		try (FileOutputStream output = new FileOutputStream(file.toFile())) {
			output.write(text.getBytes(StandardCharsets.UTF_8));
			output.write("Final line added with write().\n".getBytes(StandardCharsets.UTF_8));
		}
		catch (IOException e) {
			System.out.println("FileOutputStream example failed: " + e.getMessage());
			return;
		}

		try {
			System.out.println("File created: " + file);
			System.out.println("File content:\n" + Files.readString(file, StandardCharsets.UTF_8));
		}
		catch (IOException e) {
			System.out.println("Could not read output file: " + e.getMessage());
		}
	}
}
