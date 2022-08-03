package standardizingjobtitle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A subclass of {@link Standardizer Standardizer} whose standardize method
 * operates on a given job title
 *
 * @author  Josh Waterson
 */
public class JobStandardizer extends Standardizer {
    
    /**
     * Array of standardized job titles
     */
    private final String[] standardizedJts = new String[]{
                    "Architect",
                    "Software engineer",
                    "Quantity surveyor",
                    "Accountant"};

    /**
     * Compares passed String with standardized job titles
     * and outputs the standardized job title that most closely
     * matches the passed input.<br><br>
     *
     * Similarity is determined by implementing an approximate string
     * matching technique where pairs of Strings are compared by pairs
     * of characters. If a pair occurs in both Strings, a match is
     * counted. The standardized String with the highest match count
     * is deemed to be the closest String and is therefore returned.
     * 
     * @param jt    inputted job title
     * @return      the standardized job title that best matches 
     *              the 'ideal' standardized job titles
     */
    @Override
    public String standardize(String jt) {
        double highest = 0.0;
        int index = 0;
        String[][] pairifiedJts = Arrays.stream(standardizedJts)
                .map(this::pairify)
                .toArray(String[][]::new);

        String[] pairifiedInput = pairify(jt);

        for (int i = 0; i < pairifiedJts.length; i++) {
            if (highest != (highest = Math.max(highest, getSimilarity(pairifiedInput, pairifiedJts[i])))) {
                index = i;
            }
        }
        return standardizedJts[index];
    }

    public String[] pairify(String jt) {
        String[] words = jt.toLowerCase().split(" ");
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

    private double getSimilarity(String[] input, String[] standardizedJt) {
        int matches = 0;
        String[] shortest = input.length > standardizedJt.length ? input : standardizedJt;
        String[] other = input == shortest ? standardizedJt : input;
        List<String> pairs = new ArrayList<>(Arrays.asList(shortest));

        for (int i = other.length - 1; i >= 0; i--) {
            for (String s : pairs) {
                if (s.equals(other[i])) {
                    pairs.remove(s);
                    matches++;
                    break;
                }
            }
        }

        return (double) matches / (input.length + standardizedJt.length);
    }

}
