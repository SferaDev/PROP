package presentation.controller;

import presentation.model.Board;
import presentation.model.Turn;
import presentation.view.BoardView;

public class BoardController {
    private final Board mModel;
    private final BoardView mView;

    public BoardController(Board mModel, BoardView mView) {
        this.mModel = mModel;
        this.mView = mView;
    }

    public void addTurn(Turn turn) {
        mModel.addTurn(turn);
    }

    public void printTerminal() {
        mView.printTerminal(mModel);
    }

    public void addControlLastTurn(int blacks, int whites) {
        mModel.addControlLastTurn(blacks, whites);
    }

    public void addBlacksLastTurn(int result) {
        mModel.addBlacksLastTurn(result);
    }

    public void addWhitesLastTurn(int result) {
        mModel.addWhitesLastTurn(result);
    }
}
