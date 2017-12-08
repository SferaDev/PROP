package presentation.terminal.controller;

import presentation.terminal.model.Board;
import presentation.terminal.model.Turn;
import presentation.terminal.view.BoardView;

/**
 * The type Board controller.
 *
 * @author Elena Alonso Gonzalez
 */
public class BoardController {
    private final Board mModel;
    private final BoardView mView;

    /**
     * Instantiates a new Board controller.
     *
     * @param mModel the m model
     * @param mView  the m view
     */
    public BoardController(Board mModel, BoardView mView) {
        this.mModel = mModel;
        this.mView = mView;
    }

    /**
     * Add turn.
     *
     * @param turn the turn
     */
    public void addTurn(Turn turn) {
        mModel.addTurn(turn);
    }

    /**
     * Print terminal.
     */
    public void printTerminal() {
        mView.printTerminal(mModel);
    }

    /**
     * Add control last turn.
     *
     * @param blacks the blacks
     * @param whites the whites
     */
    public void addControlLastTurn(int blacks, int whites) {
        mModel.addControlLastTurn(blacks, whites);
    }

    /**
     * Add blacks last turn.
     *
     * @param result the result
     */
    public void addBlacksLastTurn(int result) {
        mModel.addBlacksLastTurn(result);
    }

    /**
     * Add whites last turn.
     *
     * @param result the result
     */
    public void addWhitesLastTurn(int result) {
        mModel.addWhitesLastTurn(result);
    }
}
