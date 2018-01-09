package domain.model.exceptions;

/**
 * Treats Finish Game exception
 * <p>
 * Happens when the user quits a non-finished game.
 *
 * @author Elena Alonso Gonzalez
 */
public class FinishGameException extends Exception {

    /**
     * Instantiates a new Finish game exception.
     */
    public FinishGameException() {
        super();
    }
}
