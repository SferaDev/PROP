package presentation.controller;

/**
 * The type Presentation controller.
 *
 * @author Alexis Rico Carreto
 */
public class PresentationController {
    private static final PresentationController mInstance = new PresentationController();
    private BoardController currentBoard;

    private PresentationController() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static PresentationController getInstance() {
        return mInstance;
    }

    /**
     * Gets current board.
     *
     * @return the current board
     */
    public BoardController getCurrentBoard() {
        return currentBoard;
    }

    /**
     * Sets current board.
     *
     * @param currentBoard the current board
     */
    public void setCurrentBoard(BoardController currentBoard) {
        this.currentBoard = currentBoard;
    }
}
