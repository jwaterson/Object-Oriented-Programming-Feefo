package standardizingjobtitle;

import java.util.*;


/**
 * A class whose standardize method operates on a given job title
 *
 * @author  Josh Waterson
 */
public class JobStandardizer {
    
    /**
     * Array of standardized job titles
     */
    private final String[] standardizedJts = new String[]{
                    "Architect",
                    "Software engineer",
                    "Quantity surveyor",
                    "Accountant"};

    String[][] pairifiedJts = Arrays.stream(standardizedJts)
            .map(this::pairify)
            .toArray(String[][]::new);

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
    public String standardize(String jt) {
        if (Objects.isNull(jt) || jt.length() == 0) {
            throw new IllegalArgumentException();
        }
        double highest = 0.0;
        int index = -1;

        String[] pairifiedInput = pairify(jt);

        for (int i = 0; i < pairifiedJts.length; i++) {
            if (highest != (highest = Math.max(highest, getSimilarity(pairifiedInput, pairifiedJts[i])))) {
                index = i;
            }
        }
        if (index == -1) { //input matched none of the standardized job titles
            index = 3; // TODO: replace this with a secondary arbitrary matcher (i.e. number of matching chars)
        }
        return standardizedJts[index];
    }

    /**
     * @param jt    inputted job title
     * @return      pairified String (stored in a String array)
     */
    public String[] pairify(String jt) {
        if (Objects.isNull(jt) || jt.length() == 0) {
            throw new IllegalArgumentException();
        }
        String[] words = jt.toLowerCase().split("\\s+");
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

    /**
     * @param input             pairified input
     * @param standardizedJt    pairified standardized job title
     * @return                  similarity score
     */
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
