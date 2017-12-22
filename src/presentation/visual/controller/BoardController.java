package presentation.visual.controller;

import presentation.visual.view.BoardPane;
import presentation.visual.view.ColorRow;
import presentation.visual.view.ControlRow;

/**
 * The type Board controller.
 *
 * @author Elena Alonso Gonzalez
 */
public class BoardController {
    private final BoardPane mView;

    /**
     * Instantiates a new Board controller.
     *
     * @param mView  the view
     */
    public BoardController(BoardPane mView) {
        this.mView = mView;
    }

    /**
     * Add turn.
     *
     * @param turn the turn
     */
    public void addTurn(ColorRow turn) {
        mView.addColorRow(turn);
    }

    /**
     * Add control last turn.
     *
     * @param blacks the blacks
     * @param whites the whites
     */
    public void addControlLastTurn(int blacks, int whites) {
        mView.addControlRow(new ControlRow(mView.getSize(), blacks, whites));
    }
}
