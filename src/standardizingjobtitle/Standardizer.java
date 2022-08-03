package standardizingjobtitle;

/**
 * An abstract class whose only method {@code standardize} must be implemented by
 * a concrete subclass. Added so that code that uses a concrete class of
 * this interface is less tightly-coupled to that specific implementation.
 *
 * @author  Josh Waterson
 */
public abstract class Standardizer {

    /**
     * @param s     input String to be standardized
     * @return      standardized String
     */
    public abstract String standardize(String s);
}
