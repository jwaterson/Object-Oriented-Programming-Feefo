package standardizingjobtitle;

import exceptions.NoMatchException;

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
    private static final String[] STANDARDIZED_JTS = new String[]{
                    "Architect",
                    "Software engineer",
                    "Quantity surveyor",
                    "Accountant"};

    /**
     * Standardized job titles stored in pairified form
     */
    String[][] pairifiedJts;

    /**
     * Standardized job titles stored in individualised form
     */
    String[][] individualLettersJts;

    public JobStandardizer() {
         pairifiedJts = Arrays.stream(STANDARDIZED_JTS)
                .map(StringDecomposer::pairify)
                .toArray(String[][]::new);
        individualLettersJts = Arrays.stream(STANDARDIZED_JTS)
                .map(s -> s.replaceAll("\\s", ""))
                .map(s -> s.split(""))
                .toArray(String[][]::new);

    }

    /**
     * Compares passed String with standardized job titles
     * and outputs the standardized job title that most closely
     * matches the passed input.<br><br>
     *
     * Similarity is determined by implementing an approximate string
     * matching technique where pairs of Strings are compared by pairs
     * of characters. If a pair occurs in both Strings, a match is
     * counted. The standardized String with the highest match count
     * is deemed to be the closest String and is therefore returned.<br><br>
     *
     * If no match is found, similarity is determined
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

        String[] pairifiedInput = StringDecomposer.pairify(jt);

        for (int i = 0; i < pairifiedJts.length; i++) {
            if (highest != (highest = Math.max(highest, getSimilarity(pairifiedInput, pairifiedJts[i])))) {
                index = i;
            }
        }
        if (index == -1) { //input matched none of the standardized job titles
            String[] individualLettersInput = StringDecomposer.individualise(jt);
            for (int i = 0; i < individualLettersJts.length; i++) {
                if (highest != (highest = Math.max(highest,
                        getSimilarity(individualLettersInput, individualLettersJts[i])))) {
                    index = i;
                }
            }

            if (index == -1) {
                throw new NoMatchException("Input cannot be matched to a standardized job title");
            }

        }
        return STANDARDIZED_JTS[index];
    }

    /**
     * Helper method that returns a similarity score based on the
     * number of matched elements in each of the two passed arrays.
     *
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
