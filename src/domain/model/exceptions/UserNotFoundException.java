package domain.model.exceptions;

/**
 * Treats User Not Found exception
 * <p>
 * Happens when querying a User that doesn't exist in the system.
 *
 * @author Elena Alonso Gonzalez
 */
public class UserNotFoundException extends Exception {

    /**
     * Instantiates a new Finish game exception.
     */
    public UserNotFoundException() {
        super();
    }
}
