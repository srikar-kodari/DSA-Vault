import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DemoFile {
    public static void main(String[] args) {

        String path = "demofile.txt";

        // CREATE FILE
        try {
            File obj = new File(path);

            if(obj.createNewFile()) {
                System.out.println("File Created: " + obj.getName());
            }
            else {
                System.out.println("File already exists: " + obj.getName());
            }

            System.out.println("File path: " + obj.getAbsolutePath());
        }
        catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }

        // WRITE OPERATIONS
        try {
            FileWriter writer = new FileWriter(path);

            writer.write("I am currently learning Java file handling.\n");
            writer.write("I am practicing reading from and writing to different types of files.");
            writer.close();

            System.out.println("Write operation performed.");
        }
        catch(Exception e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }

        // READ FILE
        try {
            File readObj = new File(path);
            Scanner reader = new Scanner(readObj);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
            System.out.println("Read operation performed.");
            reader.close();
        }
        catch(Exception e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }

    }
}
