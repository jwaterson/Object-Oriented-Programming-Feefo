package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import standardizingjobtitle.JobStandardizer;
import standardizingjobtitle.Pairifier;

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
                () -> assertEquals("Quantity surveyor", s.standardize("Quantatty sirveyer")),
                () -> assertEquals("Software engineer", s.standardize("SOFTWEAR")),
                () -> assertEquals("Architect", s.standardize("AArchtiect")),

                // contended

                /*
                 Accountant -
                 because more pairs match
                */
                () -> assertEquals("Accountant", s.standardize("Accountant Engineer")),
                /*
                 Software engineer -
                 both "Software engineer" and "Quantity surveyor" have same number of matches
                 however, "Quantity surveyor" matches more characters.
                */
                () -> assertEquals("Software engineer", s.standardize("Quantity Engineer")),
                () -> assertEquals("Accountant", s.standardize("Accountant Engineer")),
                () -> assertEquals("Accountant", s.standardize("Accountant Engineer")),

                // no matches

                () -> assertEquals("Accountant", s.standardize("Astronaut")),
                () -> assertEquals("Accountant", s.standardize("234234234234")),
                () -> assertEquals("Accountant", s.standardize("234234234234"))
        );
    }


}