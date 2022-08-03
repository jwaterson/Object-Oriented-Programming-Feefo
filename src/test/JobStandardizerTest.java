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
    void sampleTest() {
        assertAll(
                () -> assertEquals("Software engineer", s.standardize("Java engineer")),
                () -> assertEquals("Software engineer", s.standardize("C# engineer")),
                () -> assertEquals("Accountant", s.standardize("Accountant")),
                () -> assertEquals("Accountant", s.standardize("Chief Accountant"))
        );
    }

    @Test
    void standardizeTest() {
        assertAll(
                () -> assertEquals("Software engineer", s.standardize("Something fun")),
                () -> assertEquals("Accountant", s.standardize("Anything but accountancy")),
                () -> assertEquals("Quantity surveyor", s.standardize("Quantaty sirveyer")),
                () -> assertEquals("Architect", s.standardize("Astronaut")),
                () -> assertEquals("Software engineer", s.standardize("SOFTWEAR")),
                () -> assertEquals("Architect", s.standardize("AAAAArchtiect")),
                () -> assertEquals("Quantity surveyor", s.standardize("234234234234"))
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
                        s.pairify("C# dev")),
                () -> assertArrayEquals(new String[]{"up", "pp", "pe", "er", "ca", "as", "se"},
                        s.pairify("UP PP PE ER CA AS SE")),

                //edge cases
                () -> assertThrows(IllegalArgumentException.class,
                        () -> s.pairify("")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> s.pairify(null)),
                () -> assertArrayEquals(new String[]{"?", "12", "r2", "23", "3r", "2", "__", "__"},
                        s.pairify("?          12  r23r     2 ___")),
                () -> assertArrayEquals(new String[]{"\""},
                        s.pairify("\"")),
                () -> assertArrayEquals(new String[]{"'", "or", "1", "=", "1;"},
                        s.pairify("' OR 1 = 1;")) //should obviously be handled more robustly

        );
    }

}