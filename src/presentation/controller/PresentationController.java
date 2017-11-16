package presentation.controller;

public class PresentationController {
    private static final PresentationController mInstance = new PresentationController();
    private BoardController currentBoard;

    private PresentationController() {
    }

    public static PresentationController getInstance() {
        return mInstance;
    }

    public BoardController getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(BoardController currentBoard) {
        this.currentBoard = currentBoard;
    }
}
