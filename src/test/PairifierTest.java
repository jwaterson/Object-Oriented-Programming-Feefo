package test;

import org.junit.jupiter.api.Test;
import standardizingjobtitle.StringDecomposer;

import static org.junit.jupiter.api.Assertions.*;


class PairifierTest {

    @Test
    void pairifyTest() {
        assertAll(
                // basic tests
                () -> assertArrayEquals(new String[]{"he", "ea", "ap", "me", "em", "mo", "or", "ry"},
                        StringDecomposer.pairify("heap memory")),
                () -> assertArrayEquals(new String[]{"l", "o", "n", "e", "l", "y", "p", "a", "i", "r", "s"},
                        StringDecomposer.pairify("l o n e l y p a i r s")),
                () -> assertArrayEquals(new String[]{"c", "de", "ev"},
                        StringDecomposer.pairify("C dev")),
                () -> assertArrayEquals(new String[]{"c#", "de", "ev"},
                        StringDecomposer.pairify("C# dev")),
                () -> assertArrayEquals(new String[]{"up", "pp", "pe", "er", "ca", "as", "se"},
                        StringDecomposer.pairify("UP PP PE ER CA AS SE")),

                // edge cases
                () -> assertThrows(IllegalArgumentException.class,
                        () -> StringDecomposer.pairify("")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> StringDecomposer.pairify(null)),
                () -> assertArrayEquals(new String[]{"?", "12", "r2", "23", "3r", "2", "__", "__"},
                        StringDecomposer.pairify("?          12  r23r     2 ___")),
                () -> assertArrayEquals(new String[]{"\""},
                        StringDecomposer.pairify("\"")),
                () -> assertArrayEquals(new String[]{"'", "or", "1", "=", "1;"},
                        StringDecomposer.pairify("' OR 1 = 1;")) //should obviously be handled more robustly
        );
    }
}