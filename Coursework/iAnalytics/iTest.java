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
            Arrays.sort(arr); // Sorted array required for most tasks
            System.out.println("Task 1 - countUnique: " + sa.countUnique(arr));

            // Task 2: leastFrequent
            System.out.println("Task 2 - leastFrequent: " + sa.leastFrequent(arr));

            // Task 3: countLess
            int testNum = 10; // change as needed for testing
            System.out.println("Task 3 - countLess (" + testNum + "): " + sa.countLess(arr, testNum));

            // Task 4: countBetween
            int low = 5, high = 20; // change as needed
            System.out.println("Task 4 - countBetween (" + low + " to " + high + "): " + sa.countBetween(arr, low, high));

            // Task 5: topKFrequent
            int k = 3; // top-k most frequent
            int[] topK = sa.topKFrequent(arr, k);
            System.out.print("Task 5 - topKFrequent (" + k + "): ");
            for (int val : topK) {
                System.out.print(val + " ");
            }
            System.out.println();

            // Task 6: longestAscSubarray (original array, not sorted)
            int[] arrOriginal = readArrayFromFile("data/" + file); // get unsorted version again
            int[] longestAsc = sa.longestAscSubarray(arrOriginal);
            System.out.print("Task 6 - longestAscSubarray: ");
            for (int val : longestAsc) {
                System.out.print(val + " ");
            }
            System.out.println();

            // Task 7: maxSubarraySum (exactly k elements)
            int windowSize = 5;
            int maxSum = sa.maxSubarraySum(arrOriginal, windowSize);
            System.out.println("Task 7 - maxSubarraySum (k=" + windowSize + "): " + maxSum);

            System.out.println();
        }
    }
}