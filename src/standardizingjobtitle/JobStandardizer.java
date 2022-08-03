package standardizingjobtitle;


/**
 * A subclass of {@link Standardizer Standardizer} whose standardize method
 * operates on a given job title
 *
 * @author  Josh Waterson
 */
public class JobStandardizer extends Standardizer {

    private final String[] jts = {"architect", "software engineer", "quantity surveyor", "accountant"};

    @Override
    public String standardize(String jt) {
        return "";
    }
}
