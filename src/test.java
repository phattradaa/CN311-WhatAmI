import java.io.File;
import java.util.Random;

public class test {
    public static void main(String[] args) {
        File folder = new File("/Users/spy/Desktop/CN311-TheBigBagHave/resource/countries"); // Replace with the path to your folder

        // Check if the specified path is a directory
        if (!folder.isDirectory()) {
            System.out.println("Invalid folder path.");
            return;
        }

        File[] files = folder.listFiles();

        // Check if the folder is empty
        if (files == null || files.length == 0) {
            System.out.println("Folder is empty.");
            return;
        }

        Random random = new Random();
        File randomFile = files[random.nextInt(files.length)];

        // Get the name of the randomly selected file
        String randomFileName = randomFile.getName();

        // Use the randomly selected file name for further processing
        System.out.println("Randomly selected picture file name: " + randomFileName);
    }
}
