package domain.model.exceptions;

/**
 * Treats User Already Exists exception
 * <p>
 * Happens when creating a User that already exists in the system.
 *
 * @author Elena Alonso Gonzalez
 */
public class UserAlreadyExistsException extends Exception {

    /**
     * Instantiates a new Finish game exception.
     */
    public UserAlreadyExistsException() {
        super();
    }
}
