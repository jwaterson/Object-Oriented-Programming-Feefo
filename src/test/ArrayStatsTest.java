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
    private int[] basicArr;

    @BeforeEach
    void setUp() {
        // an array's length cannot exceed Integer.MAX_VALUE
//        arr = IntStream.generate(() -> new Random().nextInt()).limit(Integer.MAX_VALUE).toArray();
        basicArr = IntStream.range(0, 11).toArray();
        newOutStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutStream));
        arrayStats = new ArrayStats();
    }

    @AfterEach
    void tearDown() {
        basicArr = null;
        System.setOut(ogOutStream);
        newOutStream = null;
        arrayStats = null;
    }

    @Test
    void getStatsBasicInputTest() {
        arrayStats.getStats(basicArr);
        assertEquals("Median: 1\nMean: 5\nMode: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10\nRange: 10\n",
                newOutStream.toString());
    }


}