import java.io.StringWriter;

public class StringWriterExample {

	public static void main(String[] args) {

		StringWriter writer = new StringWriter();

		writer.write("StringWriter builds text in memory.");
		writer.write(System.lineSeparator());
		writer.write("It is useful for formatting reports before sending them somewhere.");
		writer.write(System.lineSeparator());
		writer.append("Total characters written: ");
		writer.append(String.valueOf(writer.getBuffer().length()));

		System.out.println("Generated text:");
		System.out.println(writer.toString());
	}
}
