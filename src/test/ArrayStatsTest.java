package test;

import numberstatistics.ArrayStats;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


class ArrayStatsTest {

    private final PrintStream ogOutStream = System.out;
    private ByteArrayOutputStream newOutStream;
    private ArrayStats arrayStats;
    private int[] arr;

    @BeforeEach
    void setUp() {
        // an array's length cannot exceed Integer.MAX_VALUE
        arr = IntStream.generate(() -> new Random().nextInt()).limit(Integer.MAX_VALUE).toArray();
        newOutStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutStream));
        arrayStats = new ArrayStats();
    }

    @AfterEach
    void tearDown() {
        arr = null;
        System.setOut(ogOutStream);
        newOutStream = null;
        arrayStats = null;
    }

    @Test
    void getStatsTest() {
        arrayStats.getStats(arr);
        assertEquals("Median: \nMean: \nMode: \nRange: \n", newOutStream.toString());
    }


}