import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class ReaderExample {

	public static void main(String[] args) {

		String text = "Reader reads text one character at a time.";
		StringBuilder copy = new StringBuilder();
		int charactersRead = 0;

		try (Reader reader = new StringReader(text)) {
			int value;

			// read() returns one character at a time as an int.
			while ((value = reader.read()) != -1) {
				copy.append((char) value);
				charactersRead++;
			}
		}
		catch (IOException e) {
			System.out.println("Reader example failed: " + e.getMessage());
			return;
		}

		System.out.println("Source text: " + text);
		System.out.println("Characters read: " + charactersRead);
		System.out.println("Read back text: " + copy);
	}
}
