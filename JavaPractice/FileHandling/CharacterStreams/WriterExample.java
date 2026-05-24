import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class WriterExample {

	public static void main(String[] args) {

		StringWriter storage = new StringWriter();

		try (Writer writer = storage) {
			writer.write("Writer stores text in memory.");
			writer.write(System.lineSeparator());
			writer.write("It is useful when you want to build text step by step.");
			writer.write(System.lineSeparator());
			writer.append("Final line written with append().");
		}
		catch (IOException e) {
			System.out.println("Writer example failed: " + e.getMessage());
			return;
		}

		System.out.println("Generated text:");
		System.out.println(storage.toString());
	}
}
