/**
 * Class of operations on integer arrays.
 * You MUST NOT change the signatures of the methods supplied. 
 */
 
// IN1002 Introduction to Algorithms
// Coursework 2024/2025
//
// Submission by
// Ekaterina Arutyunyants
// ekaterina.arutyunyants@city.ac.uk

public class iAnalytics {

    // Task 1: Count unique elements in an ordered array
    // time complexity is O(n)
    public int countUnique(int[] arr) {
        if (arr.length == 0) return 0;
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                count++;
            }
        }
        return count;
    }

    // Task 2: Find least frequent value in an ordered array
    // time complexity is O(n)
    public int leastFrequent(int[] arr) {
        if (arr.length == 0) return -1;
        int leastFrequent = Integer.MAX_VALUE;
        int current = 1;
        int leastValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                current++;
            } else {
                if (current < leastFrequent || (current == leastFrequent && arr[i - 1] < leastValue)) {
                    leastFrequent = current;
                    leastValue = arr[i - 1];
                }
                current = 1;
            }
        }

        if (current < leastFrequent || (current == leastFrequent && arr[arr.length - 1] < leastValue)) {
            leastValue = arr[arr.length - 1];
        }
        return leastValue;
    }

    // Task 3: Count elements in an ordered array less than num
    // time complexity is O(log n)
    public int countLess(int[] arr, int num) {
        int elemNum = 0, max = arr.length;
        while (elemNum < max) {
            int mid = (elemNum + max) / 2;
            if (arr[mid] < num) {
                elemNum = mid + 1;
            } else {
                max = mid;
            }
        }
        return elemNum;
    }

    // Task 4: Count elements in an ordered array between low and high (inclusive)
    // time complexity is O(n)
    public int countBetween(int[] arr, int low, int high) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= low && arr[i] <= high) {
                count++;
            }
            else if (arr[i] > high) {
                break;
            }
        }
        return count;
    }

    // Task 5: Find top K most frequent elements in an ordered array
    // time complexity is O(nk), k - num of elem to return
    public int[] topKFrequent(int[] arr, int k) {
        if (arr.length == 0 || k == 0) return new int[0];
        int[] counts = new int[arr.length];
        int[] frequencies = new int[arr.length];
        int index = 0, count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                count++;
            } else {
                counts[index] = arr[i - 1];
                frequencies[index] = count;
                index++;
                count = 1;
            }
        }
        counts[index] = arr[arr.length - 1];
        frequencies[index] = count;
        index++;
        int[] result;
        if (k < index) {
            result = new int[k];
        } else {
            result = new int[index];
        }
        boolean[] chosen = new boolean[index];
        for (int i = 0; i < result.length; i++) {
            int maxFrequent = -1;
            int value = Integer.MAX_VALUE;
            int maxIndex = -1;

            for (int j = 0; j < index; j++) {
                if (!chosen[j]) {
                    if (frequencies[j] > maxFrequent ||
                            (frequencies[j] == maxFrequent && counts[j] < value)) {
                        maxFrequent = frequencies[j];
                        value = counts[j];
                        maxIndex = j;
                    }
                }
            }
            chosen[maxIndex] = true;
            result[i] = counts[maxIndex];
        }
        return result;
    }

    // Task 6: Longest contiguous subarray in ascending order
    // time complexity is O(n)
    public int[] longestAscSubarray(int[] arr) {
        if (arr.length == 0) return new int[0];
        int start = 0, maxStart = 0, maxLength = 1, length = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                length++;
                if (length > maxLength) {
                    maxLength = length;
                    maxStart = start;
                }
            } else {
                length = 1;
                start = i;
            }
        }

        int[] result = new int[maxLength];
        for (int i = 0; i < maxLength; i++) {
            result[i] = arr[maxStart + i];
        }
        return result;
    }

    // Task 7: Maximum sum of a contiguous subarray with exactly k elements
    // time complexity is O(n)
    public int maxSubarraySum(int[] arr, int k) {
        if (arr.length == 0) return 0;
        if (arr.length < k) {
            int totalSum = 0;
            for (int val : arr) totalSum += val;
            return totalSum;
        }
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum += arr[i];
        }
        int sum = maxSum;
        for (int i = k; i < arr.length; i++) {
            sum = sum - arr[i - k] + arr[i];
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return maxSum;
    }
}
