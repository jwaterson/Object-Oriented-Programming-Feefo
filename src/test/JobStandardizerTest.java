package test;

import exceptions.NoMatchException;
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
    void standardizeTest() {
        assertAll(
                /*

                Basic tests -------------------------------------------------------------------------------

                */
                () -> assertEquals("Software engineer", s.standardize("Something fun")),
                () -> assertEquals("Accountant", s.standardize("Anything but accountancy")),
                () -> assertEquals("Quantity surveyor", s.standardize("Quantatty sirveyer")),
                () -> assertEquals("Software engineer", s.standardize("SOFTWEAR")),
                () -> assertEquals("Architect", s.standardize("AArchtiect")),

                /*

                Contended ---------------------------------------------------------------------------------

                 Returned:  Accountant
                 Reason:    input contains words common to two standardized job titles, however
                            "Accountant" is returned because more pairs match.
                */
                () -> assertEquals("Accountant", s.standardize("Accountant Engineer")),
                /*
                 Returned:  Software engineer
                 Reason:    both "Software engineer" and "Quantity surveyor" have same number of
                            matches however, "Quantity surveyor" matches more characters.
                */
                () -> assertEquals("Software engineer", s.standardize("Quantity Engineer")),

                /*

                No pairified matches ----------------------------------------------------------------------

                Returned:   Quantity surveyor
                Reason:     more individual characters match than is the case with any other
                            standardized job title.
                 */
                () -> assertEquals("Quantity surveyor", s.standardize("Astronaut")),
                /*
                Returned:   n/a
                Reason:     the inputted String bears no recognised similarity to any of the
                            standardized job titles.
                 */
                () -> assertThrows(NoMatchException.class, () -> s.standardize("234234234234"))
        );
    }


}