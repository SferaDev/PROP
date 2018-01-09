package domain.model.exceptions;

/**
 * Treats Command Interruption
 * <p>
 * Happens when the system is expecting some input from the user and receives an in-game command instead.
 * System should stop the value input and act accordingly with the received command.
 *
 * @author Elena Alonso Gonzalez
 */
public class CommandInterruptException extends Exception {

    /**
     * Instantiates a new Command interrupt exception.
     */
    public CommandInterruptException() {
        super();
    }
}
