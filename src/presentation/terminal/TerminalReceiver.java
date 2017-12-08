package presentation.terminal;

import domain.model.Receiver;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import presentation.terminal.controller.BoardController;
import presentation.terminal.controller.PresentationController;
import presentation.terminal.model.Board;
import presentation.terminal.model.Turn;
import presentation.terminal.utils.Constants;
import presentation.terminal.utils.TerminalMenuBuilder;
import presentation.terminal.utils.TerminalUtils;
import presentation.terminal.view.BoardView;

/**
 * The type Terminal input
 *
 * @author Alexis Rico Carreto
 */
public class TerminalReceiver implements Receiver {

    /**
     * Reads the number of blacks from the terminal
     *
     * @return the number of blacks
     * @throws FinishGameException       the finish game exception
     * @throws CommandInterruptException the command interrupt exception
     */
    @Override
    public int inputControlBlacks() throws FinishGameException, CommandInterruptException {
        TerminalUtils.getInstance().printLine("Introdueixi el numero de Negres");
        int result = TerminalUtils.getInstance().readGameInteger();
        PresentationController.getInstance().getCurrentBoard().addBlacksLastTurn(result);
        return result;
    }


    /**
     * Reads the number of whites from the terminal
     *
     * @return the number of whites
     * @throws FinishGameException       the finish fame exception
     * @throws CommandInterruptException the command interrupt exception
     */
    @Override
    public int inputControlWhites() throws FinishGameException, CommandInterruptException {
        TerminalUtils.getInstance().printLine("Introdueixi el numero de Blanques");
        int result = TerminalUtils.getInstance().readGameInteger();
        PresentationController.getInstance().getCurrentBoard().addWhitesLastTurn(result);
        return result;
    }


    /**
     * Reads a combination of color pegs from the terminal
     *
     * @param pegs   is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return the combination of color pegs
     * @throws FinishGameException       the finish game exception
     * @throws CommandInterruptException the command interrupt exception
     */
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

    /**
     * Writes in the terminal the number of blacks and whites of the control combination
     *
     * @param blacks the number of blacks in the combination
     * @param whites the number of whites int he combination
     */

    @Override
    public void outputControlRow(int blacks, int whites) {
        PresentationController.getInstance().getCurrentBoard().addControlLastTurn(blacks, whites);
        PresentationController.getInstance().getCurrentBoard().printTerminal();
    }


    /**
     * Writes in the terminal a color pegs' combination
     *
     * @param row the row
     */
    @Override
    public void outputColorRow(String row) {
        PresentationController.getInstance().getCurrentBoard().addTurn(new Turn(row.trim()));
        PresentationController.getInstance().getCurrentBoard().printTerminal();
    }

    /**
     * Writes in the terminal a hint to know the correct control peg
     *
     * @param blacks the number of blacks
     * @param whites the number of whites
     */
    @Override
    public void outputHintControlRow(int blacks, int whites) {
        outputMessage("Aquesta es la teva ajuda:");
        outputRow(blacks, whites);
    }

    /**
     * Writes in the terminal a color combination to help the Breaker
     *
     * @param row the combination to help de Breaker
     */
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

    /**
     * Writes in the terminal the message
     *
     * @param message the message
     */
    @Override
    public void outputMessage(String message) {
        TerminalUtils.getInstance().printLine(message);
    }

    /**
     * Notifies an invalid input
     */
    @Override
    public void notifyInvalidInput() {
        TerminalUtils.getInstance().printLine("Entrada invalida!");
    }

    /**
     * Notifies the game is finished because there are no more turns
     */
    @Override
    public void finishGame() {
        PresentationController.getInstance().setCurrentBoard(null);
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Ho sentim, t'has quedat sense torns!");
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }

    /**
     * Notifies the Breaker's victory and notifies his score
     *
     * @param score the score obtained in the game
     */
    public void finishGame(int score) {
        PresentationController.getInstance().setCurrentBoard(null);
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Enhorabona, has guanyat!");
        builder.addDescription("La teva puntuació és: " + score);
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }

    /**
     * Notifies an invalid control
     */
    @Override
    public void notifyInvalidControl() {
        TerminalUtils.getInstance().printLine("Això és mentida! Tranqui tots ho fem :)");
    }

    /**
     * Starts the game
     *
     * @param title the title
     */
    @Override
    public void startGame(String title) {
        PresentationController.getInstance().setCurrentBoard(new BoardController(new Board(title), new BoardView()));
        PresentationController.getInstance().getCurrentBoard().printTerminal();
    }
}
