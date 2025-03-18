import java.io.*;
import java.util.Arrays;

/**
 * The iTest class is responsible for testing all methods in the
 * iAnalytics class using predefined input files containing integer data.
 * It reads test data from files and runs all analytical functions.
 */
public class iTest {

    // List of four test files with different dataset sizes
    private static final String files[] = {
        "tiny.txt", "small.txt", "medium.txt", "large.txt"
    };

    /**
     * Reads an integer array from a specified file.
     * Each file contains a sequence of space-separated integers.
     *
     * @param filename the name of the file to read
     * @return an array of integers read from the file
     */
    public static int[] readArrayFromFile(String filename) {
        int[] arr = new int[0]; // Default empty array in case of an error
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            if (line != null) {
                String[] numbers = line.split(" ");
                arr = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    arr[i] = Integer.parseInt(numbers[i]);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return arr;
    }

    /**
     * Main test program for the iAnalytics class.
     * It tests all available methods using test files.
     *
     * @param args the command line arguments (unused)
     * @throws java.io.IOException if an error occurs while reading the input files
     */
    public static void main(String[] args) throws IOException {
        // Create an instance of iAnalytics to perform tests
        final iAnalytics sa = new iAnalytics();

        // Iterate over all predefined test files
        for (String file : files) {
            System.out.println("Testing on " + file + ":");
            int[] arr = readArrayFromFile("data/" + file);

            // Test methods that do not require ordered inputs on the test data

            Arrays.sort(arr);

            // Test methods that require ordered inputs on the test data

            System.out.println(); // Print a blank line for readability
        }
    }
}
