package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import standardizingjobtitle.JobStandardizer;
import standardizingjobtitle.Standardizer;

import static org.junit.jupiter.api.Assertions.*;

class JobStandardizerTest {

    Standardizer s;

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

}