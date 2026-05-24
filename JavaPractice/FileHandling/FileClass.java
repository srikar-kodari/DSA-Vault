import java.io.File;

public class FileClass {
    public static void main(String[] args) {
        
        String path = "demofile.txt";
        File file = new File(path); // "Parameters: path_to_file"

        // FILE CLASS METHODS
        System.out.println("File name: " + file.getName());
        System.out.println("Path: " + file.getPath());
        System.out.println("Absolute path: " + file.getAbsolutePath());
        System.out.println("Parent: " + file.getParent());
        
        System.out.println("Exists: " + file.exists());
        if(file.exists()) {
            System.out.println("Is writable: " + file.canWrite());
            System.out.println("Is readable: " + file.canRead());
            System.out.println("Is a directory: " + file.isDirectory());
            System.out.println("File size in bytes: " + file.length());
        }

    }
}
