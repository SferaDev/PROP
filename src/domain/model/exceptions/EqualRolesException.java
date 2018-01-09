package domain.model.exceptions;

/**
 * Treats Equal Roles exception Interruption
 * <p>
 * Happens when the system detects a new game is being created with two users of the same role.
 *
 * @author Elena Alonso Gonzalez
 */
public class EqualRolesException extends Exception {

    /**
     * Instantiates a new Command interrupt exception.
     */
    public EqualRolesException() {
        super();
    }
}
