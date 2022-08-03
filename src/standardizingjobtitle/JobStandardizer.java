package standardizingjobtitle;

import java.util.Arrays;
import java.util.Locale;


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
    private final String[] jts = new String[]{
                    "Architect",
                    "Software engineer",
                    "Quantity surveyor",
                    "Accountant"};

    /**
     * 
     * 
     * @param jt    inputted job title
     * @return      the standardized job title that best matches 
     *              the 'ideal' standardized job titles
     */
    @Override
    public String standardize(String jt) {
        String[][] treatedJts = Arrays.stream(jts)
                .map(String::toLowerCase)
                .map(this::pairify)
                .toArray(String[][]::new);

        String[] pairs = pairify(jt);


        System.out.println(Arrays.toString(pairs));
        return "";
    }

    public String[] pairify(String jt) {
        String[] words = jt.toLowerCase(Locale.ROOT).split(" ");
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

    private double getSimilarity(String[] input, String[] standardizedTitle) {
        return 0.0;
    }

    public static void main(String[] args) {
        JobStandardizer js = new JobStandardizer();
        js.standardize("Heap Memory");
    }
}
