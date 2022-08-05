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
    void getStatsBasicInputTest1() {
        basicArr = IntStream.range(0, 11).toArray();
        arrayStats.getStats(basicArr);
        assertEquals("Median: 5\nMean: 5\nMode: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10\nRange: 10\n",
                newOutStream.toString());
    }

    @Test
    void getStatsBasicInputTest2() {
        basicArr = IntStream.range(1, 11).toArray();
        arrayStats.getStats(basicArr);
        assertEquals("Median: 5.5\nMean: 5.5\nMode: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10\nRange: 9\n",
                newOutStream.toString());
    }

    @Test
    void getStatsBasicInputTest3() {
        basicArr = IntStream.range(0, 2).toArray();
        arrayStats.getStats(basicArr);
        assertEquals("Median: 0.5\nMean: 0.5\nMode: 0, 1\nRange: 1\n",
                newOutStream.toString());
    }

    @Test
    void getStatsBasicInputTest4() {
        basicArr = new int[]{1, 1, 1, 1, 1, 0, 0, 0, 0, 0};
        arrayStats.getStats(basicArr);
        assertEquals("Median: 0.5\nMean: 0.5\nMode: 0, 1\nRange: 1\n",
                newOutStream.toString());
    }

    @Test
    void getStatsBasicInputTest5() {
        basicArr = new int[]{5, 4, 4, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1, 1};
        arrayStats.getStats(basicArr);
        assertEquals("Median: 2\nMean: 2.3333333333333335\nMode: 1\nRange: 4\n",
                newOutStream.toString());
    }


}