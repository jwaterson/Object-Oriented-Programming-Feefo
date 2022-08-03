package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import standardizingjobtitle.JobStandardizer;

import static org.junit.jupiter.api.Assertions.*;

class JobStandardizerTest {

    JobStandardizer s;

    @BeforeEach
    void setUp() {
        s = new JobStandardizer();
    }

    @AfterEach
    void tearDown() {
        s = null;
    }

    @Test
    void sampleCodeTest() {
        assertAll(
                () -> assertEquals("Software engineer", s.standardize("Java engineer")),
                () -> assertEquals("Software engineer", s.standardize("C# engineer")),
                () -> assertEquals("Accountant", s.standardize("Accountant")),
                () -> assertEquals("Accountant", s.standardize("Chief Accountant"))
        );
    }

    @Test
    void pairifyTest() {
        //temporarily change pairify access modifier to public for testing
        assertAll(
                () -> assertArrayEquals(new String[]{"he", "ea", "ap", "me", "em", "mo", "or", "ry"},
                        s.pairify("heap memory")),
                () -> assertArrayEquals(new String[]{"l", "o", "n", "e", "l", "y", "p", "a", "i", "r", "s"},
                        s.pairify("l o n e l y p a i r s")),
                () -> assertArrayEquals(new String[]{"c", "de", "ev"},
                        s.pairify("C dev")),
                () -> assertArrayEquals(new String[]{"c#", "de", "ev"},
                        s.pairify("C# dev"))
        );
    }

}