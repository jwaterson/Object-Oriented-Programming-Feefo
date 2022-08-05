package numberstatistics;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayStats {

    /**
     * Outputs basic statistics (median, mean, mode and range) about
     * the input array to the console.
     *
     * @param arr input array
     */
    public void getStats(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Cannot get stats for empty array");
        }
        double median = getMedian(arr);
        double mean = getMean(arr);
        int[] mode = getMode(arr);
        long range = getRange(arr);

        System.out.printf("Median: %s\nMean: %s\nMode: %s\nRange: %d\n",
                formatDouble(median),
                formatDouble(mean),
                IntStream.of(mode)
                        .mapToObj(Integer::toString)
                        .collect(Collectors.joining(", ")),
                range);
    }

    /**
     * gets statistical range of input array (min element subtracted
     * from max element)
     *
     * @param arr   input array
     * @return      difference between max and min elements in the array
     */
    private long getRange(int[] arr) {
        long max = Arrays.stream(arr).max().orElse(0);
        long min = Arrays.stream(arr).min().orElse(0);
        return max - min;
    }

    /**
     * gets mode of input array elements.
     *
     * @param arr   input array
     * @return      most frequently occurring element(s) in the input array
     */
    private int[] getMode(int[] arr) {
        Map<Integer, Long> freqMap = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return freqMap.entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(),
                        Objects.requireNonNull(freqMap.entrySet().stream()
                            .max(Map.Entry.comparingByValue()).orElse(null)).getValue()))
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }

    /**
     * gets median of input array elements using an implementation of
     * quickselect algorithm.
     *
     * @param arr   input array
     * @return      median of the input array elements
     */
    private double getMedian(int[] arr) {
        int first = 0;
        int last = arr.length - 1;
        int index = arr.length / 2;
        while (first < last) {
            int pivot = getPartition(arr, first, last);
            if (pivot < index) {
                first = pivot + 1;
            }
            else if (pivot > index) {
                last = pivot - 1;
            }
            else {
                first = pivot;
                break;
            }
        }
        return arr.length % 2 != 0 ?
                arr[first] : (arr[first - 1] + arr[first]) / 2.0;
    }

    /**
     * @param arr   input array
     * @param first first element in partitioned array
     * @param last  last element in partitioned array
     * @return      array element acting as partition
     */
    private int getPartition(int[] arr, int first, int last) {
        int pivot = first;
        int temp;
        while (first <= last) {
            while (first <= last && arr[first] <= arr[pivot]) {
                first++;
            }
            while (first <= last && arr[last] > arr[pivot]) {
                last--;
            }
            if (first > last) {
                break;
            }
            temp = arr[first];
            arr[first] = arr[last];
            arr[last] = temp;
        }
        temp = arr[last];
        arr[last] = arr[pivot];
        arr[pivot] = temp;
        return last;
    }

    /**
     * iteratively gets mean of input array elements.
     *
     * @param arr   input array
     * @return      mean of the input array elements
     */
    private double getMean(int[] arr) {
        double avg = 0;
        int d = 1;
        for (int i : arr) {
            avg += (i - avg) / d;
            ++d;
        }
        return avg;
    }

    /**
     * Helper method to output tidied representation of double value.
     *
     * @param d     double to be formatted
     * @return      formatted String of input double value
     */
    private String formatDouble(double d) {
        return String.format(d % 1.0 != 0 ? "%s" : "%.0f", d);
    }

    private static int[] generateSomeHugeArray() {
        // int[] arr = new int[1000];
        int[] arr = new int[new Random().nextInt(1, 1001)]; // limited to 1000 for example purposes only
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt() * (i % 2 == 0 ? 1 : -1);
            // arr[i] = new Random().nextInt(-1, 2) ;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] myBigArray = generateSomeHugeArray();

        ArrayStats arrayManipulator = new ArrayStats();
        arrayManipulator.getStats(myBigArray);

    }
}
