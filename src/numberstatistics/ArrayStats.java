package numberstatistics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayStats {

    public void getStats(int[] arr) {
        double median = getMedian(arr);
        double mean = getMean(arr);
        int[] mode = getMode(arr);
        int range = getRange(arr);

        System.out.printf("Median: %s\nMean: %s\nMode: %s\nRange: %d\n",
                formatDouble(median),
                formatDouble(mean),
                IntStream.of(mode)
                        .mapToObj(Integer::toString)
                        .collect(Collectors.joining(", ")),
                range);
    }

    /**
     * @param arr   input array
     * @return      difference between max and min elements in the array
     */
    private int getRange(int[] arr) {
        return Arrays.stream(arr).max().orElse(0) -
                Arrays.stream(arr).min().orElse(0);
    }

    private int[] getMode(int[] arr) {
        Map<Integer, Long> freqMap = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return freqMap.entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), Objects.requireNonNull(freqMap.entrySet().stream()
                        .max(Map.Entry.comparingByValue()).orElse(null)).getValue()))
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }

    private double getMedian(int[] arr) {
        // TODO: implement
        return 1;
    }

    private double getMean(int[] arr) {
        double avg = 0;
        int d = 1;
        for (int i : arr) {
            avg += (i - avg) / d;
            ++d;
        }
        return avg;
    }

    private String formatDouble(double d) {
        return String.format(d % 1.0 != 0 ? "%s" : "%.0f", d);
    }
}
