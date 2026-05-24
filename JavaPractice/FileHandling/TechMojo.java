import java.io.*;
import java.util.*;

public class TechMojo {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        // Read input filename
        String filename = scan.nextLine();

        // Store hostname counts
        Map<String, Integer> map = new HashMap<>();

        // Read file
        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line;
        while ((line = br.readLine()) != null) {

            // Hostname is first word in each line
            String[] parts = line.split(" ");

            String host = parts[0];

            map.put(host, map.getOrDefault(host, 0) + 1);
        }

        br.close();

        // Output filename
        String outputFile = "records_" + filename;

        // Write results to output file
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            bw.write(entry.getKey() + " " + entry.getValue());
            bw.newLine();
        }

        bw.close();
    }
}
