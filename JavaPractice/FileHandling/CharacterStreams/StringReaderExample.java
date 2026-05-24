import java.io.IOException;
import java.io.StringReader;

public class StringReaderExample {

	public static void main(String[] args) {
		
		String text = "StringReader works with text already in memory.\n"
				+ "It is useful when text comes from an API response, form field, or parser.\n";

		int characters = 0;
		int words = 0;
		boolean insideWord = false;
		StringBuilder copy = new StringBuilder();

		try (StringReader reader = new StringReader(text)) {
			int value;

			while ((value = reader.read()) != -1) {
				char current = (char) value;
				copy.append(current);
				characters++;

				if (Character.isWhitespace(current)) {
					insideWord = false;
				}
				else if (!insideWord) {
					insideWord = true;
					words++;
				}
			}
		}
		catch (IOException e) {
			System.out.println("StringReader example failed: " + e.getMessage());
			return;
		}

		System.out.println("Source text:\n" + text);
		System.out.println("Characters read: " + characters);
		System.out.println("Words counted: " + words);
		System.out.println("Read back text:\n" + copy);
	}
}
