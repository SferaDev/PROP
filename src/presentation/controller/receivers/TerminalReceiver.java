package presentation.controller.receivers;

import domain.model.Receiver;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import presentation.controller.BoardController;
import presentation.controller.PresentationController;
import presentation.model.Board;
import presentation.model.Turn;
import presentation.utils.Constants;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;
import presentation.view.BoardView;

/**
 * The type Terminal input output.
 */
public class TerminalReceiver implements Receiver {

    @Override
    public int inputControlBlacks() throws FinishGameException, CommandInterruptException {
        TerminalUtils.getInstance().printLine("Introdueixi el numero de Negres");
        int result = TerminalUtils.getInstance().readGameInteger();
        PresentationController.getInstance().getCurrentBoard().addBlacksLastTurn(result);
        return result;
    }

    @Override
    public int inputControlWhites() throws FinishGameException, CommandInterruptException {
        TerminalUtils.getInstance().printLine("Introdueixi el numero de Blanques");
        int result = TerminalUtils.getInstance().readGameInteger();
        PresentationController.getInstance().getCurrentBoard().addWhitesLastTurn(result);
        return result;
    }

    @Override
    public int[] inputColorRow(int pegs, int colors) throws FinishGameException, CommandInterruptException {
        int[] result = new int[pegs];
        StringBuilder row = new StringBuilder();
        TerminalUtils.getInstance().printLine("Introdueixi combinació de " + pegs + " fitxes i " + colors + " colors");
        for (int i = 0; i < pegs; ++i) {
            result[i] = TerminalUtils.getInstance().readGameInteger();
            row.append(result[i]).append(" ");
        }
        PresentationController.getInstance().getCurrentBoard().addTurn(new Turn(row.toString().trim()));
        return result;
    }

    @Override
    public void outputControlRow(int blacks, int whites) {
        PresentationController.getInstance().getCurrentBoard().addControlLastTurn(blacks, whites);
        PresentationController.getInstance().getCurrentBoard().printTerminal();
    }

    @Override
    public void outputColorRow(String row) {
        PresentationController.getInstance().getCurrentBoard().addTurn(new Turn(row.trim()));
        PresentationController.getInstance().getCurrentBoard().printTerminal();
    }

    @Override
    public void outputHintControlRow(int blacks, int whites) {
        outputMessage("Aquesta es la teva ajuda:");
        outputRow(blacks, whites);
    }

    @Override
    public void outputHintColorRow(String row) {
        outputMessage("Aquesta es la teva ajuda:");
        outputRow(row);
    }

    private void outputRow(int blacks, int whites) {
        TerminalUtils.getInstance().printLine("Negres: " + blacks + " | Blanques: " + whites);
    }

    private void outputRow(String row) {
        TerminalUtils.getInstance().printLine(row);
    }

    @Override
    public void outputMessage(String message) {
        TerminalUtils.getInstance().printLine(message);
    }

    @Override
    public void notifyInvalidInput() {
        TerminalUtils.getInstance().printLine("Entrada invalida!");
    }

    @Override
    public void finishGame() {
        PresentationController.getInstance().setCurrentBoard(null);
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Ho sentim, t'has quedat sense torns!");
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }

    public void finishGame(int score) {
        PresentationController.getInstance().setCurrentBoard(null);
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Enhorabona, has guanyat!");
        builder.addDescription("La teva puntuació és: " + score);
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }

    @Override
    public void notifyInvalidControl() {
        TerminalUtils.getInstance().printLine("Això és mentida! Tranqui tots ho fem :)");
    }

    @Override
    public void startGame(String title) {
        PresentationController.getInstance().setCurrentBoard(new BoardController(new Board(title), new BoardView()));
        PresentationController.getInstance().getCurrentBoard().printTerminal();
    }
}
