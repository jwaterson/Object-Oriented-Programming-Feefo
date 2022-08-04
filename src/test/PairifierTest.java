package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import standardizingjobtitle.Pairifier;

import static org.junit.jupiter.api.Assertions.*;

class PairifierTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void pairifyTest() {
        assertAll(
                () -> assertArrayEquals(new String[]{"he", "ea", "ap", "me", "em", "mo", "or", "ry"},
                        Pairifier.pairify("heap memory")),
                () -> assertArrayEquals(new String[]{"l", "o", "n", "e", "l", "y", "p", "a", "i", "r", "s"},
                        Pairifier.pairify("l o n e l y p a i r s")),
                () -> assertArrayEquals(new String[]{"c", "de", "ev"},
                        Pairifier.pairify("C dev")),
                () -> assertArrayEquals(new String[]{"c#", "de", "ev"},
                        Pairifier.pairify("C# dev")),
                () -> assertArrayEquals(new String[]{"up", "pp", "pe", "er", "ca", "as", "se"},
                        Pairifier.pairify("UP PP PE ER CA AS SE")),

                //edge cases
                () -> assertThrows(IllegalArgumentException.class,
                        () -> Pairifier.pairify("")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> Pairifier.pairify(null)),
                () -> assertArrayEquals(new String[]{"?", "12", "r2", "23", "3r", "2", "__", "__"},
                        Pairifier.pairify("?          12  r23r     2 ___")),
                () -> assertArrayEquals(new String[]{"\""},
                        Pairifier.pairify("\"")),
                () -> assertArrayEquals(new String[]{"'", "or", "1", "=", "1;"},
                        Pairifier.pairify("' OR 1 = 1;")) //should obviously be handled more robustly
        );
    }
}