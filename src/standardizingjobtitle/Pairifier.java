package standardizingjobtitle;

import java.util.Arrays;
import java.util.Objects;


/**
 * A utility class whose {@code pairify} method transforms an inputted
 * String into its 'pairified' equivalent, composed of each consecutive
 * pair of characters in the String.
 *
 * @author  Josh Waterson
 */
public final class Pairifier {

    private Pairifier(){}

    /**
     * Transforms an inputted String into its 'pairified' equivalent,
     * composed of each consecutive pair of characters in the String.
     *
     * @param str    inputted job title
     * @return      pairified String (stored in a String array)
     */
    public static String[] pairify(String str) {
        if (Objects.isNull(str) || str.length() == 0) {
            throw new IllegalArgumentException();
        }
        String[] words = str.toLowerCase().split("\\s+");
        String[][] arr = Arrays.stream(words)
                .map(s -> s.split(""))
                .toArray(String[][]::new);

        String[][] pairArray = new String[words.length][];

        for (int i = 0; i < arr.length; i++) {
            pairArray[i] = new String[words[i].length() - 1];
            if (words[i].length() == 1) {
                pairArray[i] = arr[i];
                continue;
            }
            for (int j = 0; j < arr[i].length - 1; j++) {
                pairArray[i][j] = arr[i][j] + arr[i][j + 1];
            }
        }

        return Arrays.stream(pairArray)
                .flatMap(Arrays::stream)
                .toArray(String[]::new);
    }
}
